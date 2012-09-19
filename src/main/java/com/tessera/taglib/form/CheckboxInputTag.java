package com.tessera.taglib.form;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.jsp.*;

import org.apache.commons.beanutils.*;

import com.tessera.intercept.form.*;
import com.weaselworks.util.*;

/**
 * 
 * @author crawford
 *
 */

public class CheckboxInputTag
	extends InputTag
{
	public
	CheckboxInputTag ()
	{
		return; 
	}
	
	private String value; 
	public String getValue () { return this.value; } 
	public void setValue (final String value) { this.value = value; return; } 
	
	public boolean checked; 
	public boolean getChecked () { return this.checked; } 
	public void setChecked (final boolean checked) { this.checked = checked; return; } 
	
	public
    void doTag()
    	throws IOException, JspException 
    {
    	final JspWriter out = this.getJspContext ().getOut ();

        out.append ("<input");
        writeAttribute (out, "type", "checkbox");
        writeAttribute (out, "name", getName ());
        writeAttribute (out, "value", getValue ());
    
        if (getMultiple ()) {
            if (matchValue (getValue ())) {
                writeAttribute (out, "checked", "true");
            }
        } else {
            final Boolean value = resolveBooleanValue ();
            if (value != null) {
                if (value == true) {
                    writeAttribute (out, "checked", "true");
                }
            } else if (getChecked ()) {
                writeAttribute (out, "checked", "true");
            }
        }
        writeDynamicAttributes(out);
        out.append (" />");
        return; 
    }

	@SuppressWarnings ("unchecked")
	protected Boolean resolveBooleanValue ()
	    throws IOException
	{
		final String param = getName ();
		final String property = getProperty () != null ? getProperty () : getName ();
		final Form form = getForm ();
		
		if (form != null) {
			if (form.getBound ()) {
				ServletRequest req = ((PageContext) getJspContext ()).getRequest ();
				Map<String, String[]> parameterMap = (Map<String, String[]>) req.getParameterMap ();

				if (parameterMap != null) {
					if (parameterMap.containsKey (param)) {
						String[] values = parameterMap.get (param);
						if (values != null && values.length > 0 && !StringUtil.isEmpty (values[0])) {
							return true;
						} else {
							return false;
						}
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else {
				try {
					String value = BeanUtils.getProperty (form, property);
					if (value == null) {
						return null;
					} else if (value.length () == 0 || "false".equals (value.toLowerCase ()) || "0".equals (value)) {
						return false;
					} else {
						return true;
					}
				} catch (final Exception e) {
					throw new IOException ("Failed to get specified property from form bean.");
				}
			}
		} else {
			return null;
		}
		
		// NOT REACHED
	}                        
}

// EOF