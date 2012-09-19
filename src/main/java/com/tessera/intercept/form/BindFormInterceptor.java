package com.tessera.intercept.form;

import java.io.*;
import java.lang.reflect.*;
import java.math.*;
import java.util.*;

import javax.servlet.http.*;

import org.apache.commons.beanutils.*;
import org.apache.commons.beanutils.converters.*;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.lang.*;
import org.apache.log4j.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.*;
import com.weaselworks.io.*;
import com.weaselworks.util.*;


/**
 * An {@linkplain Interceptor} that is responsible for binding the request parameters 
 * to a {@linkplain Form} object. 
 * 
 * @author crawford
 *
 */

public class BindFormInterceptor
	extends FormInterceptorSupport
{
	private static final Logger logger = Logger.getLogger (BindFormInterceptor.class); 
	
	public
	BindFormInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 
	}

    public interface PROP
	    extends FormInterceptorSupport.PROP
	{
	    public String ALLOWED = "allowed";
	    public String CUSTOM_EDITOR = "customEditor";
	    public String ENUM_NAME_CONVERTER = "enumNameConverter";
	    public String ENUM_ORDINAL_CONVERTER = "enumOrdinalConverter";
	    public String CONVERTER = "converter";
	    public String FIELD_CONVERTER = "fieldConverter."; 
	    public String TYPE = "type";
	    public String VALIDATE = "validate";
	}
    
    /**
     * Contains the local instance of the {@linkplain BeanUtilsBean} that we'll
     * use to register custom {@linkplain Converter Converters} and use to map
     * parameters into the form bean.
     */

    protected BeanUtilsBean binder = new BeanUtilsBean ();

    /**
     * Contains the map of field-specific converters that are used to pre-process 
     * values prior to the standard conversion process. 
     */
    
    protected Map<String, Converter> fieldConverters = new HashMap<String, Converter> (); 
    
    /**
     * 
     */
    
    @SuppressWarnings("unchecked")
	@Override
    public
    void init ()
        throws Exception
    {
        super.init ();         

        String val = getProperty (PROP.VALIDATE);
        if (! StringUtil.isEmpty (val)) {
            setValidate (Boolean.parseBoolean (val));
        }
        
        // See if they explicitly specified a clobber mode
        
        final String clobber = getProperty (PROP.CLOBBER); 
        if (! StringUtil.isEmpty (clobber)) { 
        	setClobberMode (ClobberMode.valueOf (clobber)); 
        }
        
        // Setup the default converters in the bean binder
        
        final ConvertUtilsBean cons = binder.getConvertUtils (); 
        cons.register (new ConverterWrapper (new DateConverter ()), Date.class); 
        cons.register (new ConverterWrapper (new LongConverter ()), Long.class); 
        cons.register (new ConverterWrapper (new IntegerConverter ()), Integer.class); 
        cons.register (new ConverterWrapper (new ShortConverter ()), Short.class); 
        cons.register (new ConverterWrapper (new BooleanConverter ()), Boolean.class);
        cons.register (new ConverterWrapper (new ClassConverter ()), Class.class); 
        cons.register (new ConverterWrapper (new BigDecimalConverter()), BigDecimal.class);
        cons.register (new ConverterWrapper (new FloatConverter ()), Float.class); 
        cons.register (new ConverterWrapper (new DoubleConverter ()), Double.class);

        // Load any custom converters that they've specified

        for (int i = 0; true; i++) { 
        	final String prefix = PROP.CONVERTER + "." + i + ".";  
        	final String conv = getProperty (prefix + PROP.CLASS); 
        	if (StringUtil.isEmpty (conv)) { 
        		break; 
        	}        	
        	final String type = getProperty (prefix + PROP.TYPE); 
        	if (StringUtil.isEmpty (type)) { 
        		throw new IllegalStateException ("Missing property: " + prefix + PROP.TYPE); 
        	}
        	// TODO: Clean this up later.
        	
        	final ClassLoader cload = Thread.currentThread ().getContextClassLoader (); 
        	final Class convtype = cload.loadClass (conv);
        	// TODO: check for assignability/castness
//        	if (convtype.isAssignableFrom (Converter.class)) {
//        		throw new IllegalStateException ("Not a converter: " + conv); 
//        	}
        	final Converter obj = (Converter) convtype.newInstance ();  
        	final Class converted = cload.loadClass (type); 
        	cons.register (obj, converted); 
        	if (getDebug ()) { 
        		logger.info ("Converter registered [" + conv + " -> " + type + "]"); 
        	}
        }

        // An load any custom enum converters
        
        for (int i = 0; true; i ++) { 
        	final String key = PROP.ENUM_NAME_CONVERTER + '.' + i;
        	final String etype = getProperty (key);
        	if (StringUtil.isEmpty (etype)) {
        		break; 
        	}
        	final ClassLoader cload = Thread.currentThread ().getContextClassLoader ();
        	final Class ctype = cload.loadClass (etype);
        	// TODO: check for enum-ness
        	final Converter conv = EnumNameConverter.newInstance (ctype); 
        	cons.register (conv, ctype);         
        	if (getDebug ()) { 
        		logger.info ("Enum name converter registered [" + etype + "]"); 
        	}
        }

        for (int i = 0; true; i ++) { 
        	final String key = PROP.ENUM_ORDINAL_CONVERTER + '.' + i;
        	final String etype = getProperty (key);
        	if (StringUtil.isEmpty (etype)) {
        		break; 
        	}
        	final ClassLoader cload = Thread.currentThread ().getContextClassLoader ();
        	final Class ctype = cload.loadClass (etype);
        	// TODO: check for enum-ness
        	final Converter conv = EnumOrdinalConverter.newInstance (ctype); 
        	cons.register (conv, ctype);         
        	if (getDebug ()) { 
        		logger.info ("Enum ordinal converter registered [" + etype + "]"); 
        	}
        }

        // Load any field-specific pre-converters that will run
        
        for (final String key : getProperties ().keySet ()) { 
        	if (key.startsWith (PROP.FIELD_CONVERTER)) { 
        		final String field = key.substring (PROP.FIELD_CONVERTER.length ());
        		final String className = getProperty (key);
            	final ClassLoader cload = Thread.currentThread ().getContextClassLoader ();
            	final Class ctype = cload.loadClass (className);
            	final Converter converter = (Converter) ctype.newInstance (); 
            	fieldConverters.put (field, converter); 
            	if (getDebug ()) { 
            		logger.info ("Registered field converter for '" + field + "' (" + className + ")."); 
            	}
        	}	
        }
                
        // Let them optionally limit the parameters that get processed to an explicit set

        val = getProperty (PROP.ALLOWED);
        if (! StringUtil.isEmpty (val)) {
            final List<String> allowed = new ArrayList<String> ();
            final StringTokenizer toker = new StringTokenizer (val, ",");
            while (toker.hasMoreTokens ()) {
                allowed.add (toker.nextToken ());    
            }
            setAllowed (allowed);
        }

        return;
    }

    interface DEFAULT
    	extends FormInterceptorSupport.DEFAULT
    {
    	public ClobberMode CLOBBER_MODE = ClobberMode.NONE; 
    	public boolean VALIDATE = true; 
    	public List<String> ALLOWED = null; 
    }
    
    protected ClobberMode clobberMode = DEFAULT.CLOBBER_MODE;  
    public ClobberMode getClobberMode () { return this.clobberMode; } 
    public void setClobberMode (final ClobberMode clobberMode) { this.clobberMode = clobberMode; return; } 

    protected boolean validate = DEFAULT.VALIDATE; 
    public boolean getValidate () { return this.validate; }
    public void setValidate (final boolean validate) { this.validate = validate; return; }

    protected List<String> allowed = DEFAULT.ALLOWED; 
    public List<String> getAllowed () { return this.allowed; }
    public void setAllowed (final List<String> allowed) { this.allowed = allowed; return; }

    /**
     * @see Interceptor#intercept(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.twofish.ludwig.action.Action)
     */

    public
    Alteration intercept (final HttpServletRequest req, final HttpServletResponse res, DispatchContext dc)
        throws Exception
    {
    	// Don't overwrite an existing bean if they've said not to 
    	
    	final String attr = getProperty (PROP.ATTR, DEFAULT.ATTR);
    	
    	switch (getClobberMode ()) { 
    		case ALWAYS:
    			break; 
    			
    		case NONE:
        		if (req.getAttribute (attr) != null) { 
        			if (getDebug ()) { 
        				logger.info ("Attribute ('" + attr + "') already present; skipping."); 
        			}
        			return NO_ALTERATION;
        		}
    			break; 
    			
    		case NOT_SAME:
    			logger.error ("ClobberMode.NOT_SAME is unimplemented."); 
    			break; 
    	}
    	
    	// Create a new bean to populate with the request parameters
    	
        final Form form = getFormFactory ().createForm ();
        storeForm (req, getProperty (PROP.ATTR, DEFAULT.ATTR), form); 

        if (getDebug ()) { 
        	logger.info ("Instantiated bean: " + form);
        } 

        // Determine whether we need to handle this as a multi-part upload or not
        
        final Alteration alt; 
        
        if (ServletFileUpload.isMultipartContent (req)) { 
        	alt = doMultipart (req, res, form); 
        } else { 
        	alt = doNormal (req, res, form); 
        }
        
        form.setBound (true);
        
        if (alt != null) { 
        	return alt;         	
        }

        // Validate the bean if they've asked us to and its validateable

        if (getValidate ()) { 
        	if (form instanceof Form) {
    			form.validate (); 
    			if (form.getHasErrors ()) { 
    				storeForm (req, attr, form); 
					if (getDebug ()) { 
						logger.info ("Validation failed: " + form); 
					}
        			return failure (req);
    			}
            } else {
                logger.info ("Can't validate bean, not an AnnotatedForm: " + form.getClass().getName ()); 
            }
        } else { 
        	if (getDebug ()) { 
        		logger.info ("Validation disabled");         	
        	}
        }

        // Save the results in the request context and proceed
        
        if (getDebug ()) { 
        	logger.info ("Bound bean (" + getProperty (PROP.ATTR, DEFAULT.ATTR) + "): " + form);         
        }
        
        return NO_ALTERATION;
    }


    /**
     * 
     * @param res
     * @param attr
     * @param form
     */
    
    protected
    void storeForm (final HttpServletRequest req, final String attr, final Form form)
    {
        if (getDebug ()) { 
        	logger.info ("Storing form in '" + attr + "'.");
        }
    	if (req.getAttribute (attr) != null) { 
    		logger.warn ("Overwriting existing attribute (" + attr + ")."); 
    	}
    	req.setAttribute (attr, form);
    	return; 
    }
    
    /**
     * 
     * @param req
     * @param res
     */
    
    @SuppressWarnings("unchecked")
	protected
    Alteration doNormal (final HttpServletRequest req, final HttpServletResponse res, final Form form)
    	throws Exception
    {    	
        boolean failed = false; 
        
        if (allowed == null) {
            final Enumeration<?> params = req.getParameterNames ();
            while (params.hasMoreElements ()) {
                final String param = (String) params.nextElement ();
                final String [] values = req.getParameterValues (param);
                for (String val : values) {
                    if (getDebug ()) { 
                    	logger.info ("Binding '" + val + "' to '" + param + "'."); 
                    }
                    try { 
                    	final String unescaped = unescape (val);
                    	final Object convertMe = preConvert (param, unescaped);  
                    	binder.setProperty (form, param, convertMe);                	
                    } catch (final ConversionException c_e) { 
                    	form.setError (param, c_e.getMessage ());
                    	failed = true;  
                    }                
                }
            }
            if (failed) { 
                if (getDebug ()) { 
                	logger.info ("Validation failed: " + form); 
                }
            	return failure (req); 
            }
        }

        // Otherwise, anything sent is fair game

        else {
             for (final String param : allowed) {
                final String val = req.getParameter (param);
                if (getDebug ()) { 
                	logger.info ("Binding '" + val + "' to '" + param + "'."); 
                }
                try { 
                	final String unescaped = unescape (val); 
                	final Object convertMe = preConvert (param, unescaped);  
                	binder.setProperty (form, param, convertMe);                	
                } catch (final ConversionException c_e) { 
                	form.setError (param, c_e.getMessage ());
                	failed = true;  
                }                
            }
            if (failed) { 
                if (getDebug ()) { 
                	logger.info ("Validation failed: " + form); 
                }
            	return failure (req); 
            }
        }
        
        // Now process any properties that the object might be carrying. 
        
        final List<Pair<Field, PropertiesField>> pairs = BeanBinderUtil.getFields (form.getClass ());
        if (pairs.size () > 0) { 
        	for (final Pair<Field, PropertiesField> pair : pairs) {
        		final Map<String, String> map = (Map<String, String>) pair.getLeft ().get (form);
        		final String keyPattern = pair.getRight ().keyPattern (); 
        		final String valPattern = pair.getRight ().valuePattern (); 
        		
        		for (int cnt = 0; ; cnt ++) { 
        			final String key = req.getParameter (String.format (keyPattern, cnt)); 
        			if (StringUtil.isEmpty (key)) { 
        				break; 
        			}
        			final String val = req.getParameter (String.format (valPattern, cnt));
        			if (StringUtil.isEmpty (val)) {
        				// TODO: Do we want to error? 
        				continue;
        			}
        			map.put (key, val); 
        		}
        	}	
        }        

        if (getDebug ()) { 
        	logger.info ("Binding successful: " + form); 
        }

        return null; 
    }
    
    /**
     * 
     * @param field
     * @param value
     * @return
     */
    
    protected
    Object preConvert (final String field, final String value)
    {
    	final Converter c = fieldConverters.get (field); 
    	if (c == null) { 
    		return value; 
    	} else { 
    		final Object converted = c.convert(null, value); 
    		return converted;
    	}
    	
    	// NOT REACHED
    }
    
    /**
     * Used to unescape the strings that are specified so that unicode
     * values may be specified. 
     *  
     * @param str
     * @return
     */
    
    protected static
    String unescape (final String str)
    {
    	return StringEscapeUtils.unescapeJava (str);  
    }
        
    /**
     * 
     * @param req
     * @param res
     */
    
    protected
    Alteration doMultipart (final HttpServletRequest req, final HttpServletResponse res, final Form form)
    	throws Exception
    {
		final ServletFileUpload upload = new ServletFileUpload (); 
		final FileItemIterator iter = upload.getItemIterator (req); 
		boolean failed = false; 
		
		while (iter.hasNext ()) { 
			final FileItemStream item = iter.next (); 
			final String name = item.getFieldName (); 
			final InputStream is = item.openStream ();
			
			if (item.isFormField ()) {
				final String value = IOUtil.readFully (is); 
                if (getDebug ()) { 
                	logger.info ("Binding '" + value + "' to '" + name + "'."); 
                }
                try { 
                	form.getOther ().put (name, value); 
                	binder.setProperty (form, name, value);                	
                } catch (final ConversionException c_e) { 
                	form.setError (name, c_e.getMessage ());
                	failed = true;  
                }                
			} else {
				final ByteArrayOutputStream baos = new ByteArrayOutputStream (); 
				IOUtil.copy (is, baos); 
				final byte [] bytes = baos.toByteArray (); 
				if (getDebug ()) { 
					logger.info ("Binding file (" + item.getName () + "; " + bytes.length + " bytes) to '" + name + "."); 
				}
				try {
					form.getOther ().put (name, bytes); 
					form.getOther ().put (name + "Filename", item.getName ()); 
					binder.setProperty (form, name, bytes);
					binder.setProperty (form, name + "Filename", item.getName ()); 
				}
				catch (final ConversionException c_e) {
					logger.warn("Failed to convert value when binding bean: " + c_e.getMessage ());
					// TODO: We used to set a global error here.  Is that necessary or is logging enough?
					failed = true;
				}
			}
		}

        if (failed) { 
        	return failure (req); 
        }

    	return null; 
    }    	
}

// EOF
