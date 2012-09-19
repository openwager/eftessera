package com.tessera.intercept;

import java.lang.reflect.*;
import java.util.*;

import javax.servlet.http.*;

import org.apache.commons.beanutils.*;
import org.apache.log4j.*;

import com.weaselworks.util.*;

import com.tessera.dispatch.*; 
import com.tessera.intercept.util.*;

/**
 * TODO: Comment goes here. 
 * 
 * @author Lee Crawford (lee.crawford@gmail.com) 
 * @copyright Copyright (c) 2009, Weaselworks, Inc. 
 */

abstract public class InterceptorSupport
	extends DispatchTarget
	    implements Interceptor
{
	private static final Logger logger = Logger.getLogger (InterceptorSupport.class);
	
	protected
	InterceptorSupport (final Map<String, String> props)
	{
	    super (props); 
	    return;
	}
	
	public
	void init ()
		throws Exception
	{
	    if ("true".equals (getProperty (PROP.DEBUG))) {
	        setDebug (true);
	    }
	    return;
	}

	protected Dispatcher dispatcher; 
	
	public
	void setDispatcher (final Dispatcher dispatcher)
	{
		this.dispatcher = dispatcher; 
		return; 
	}
	
	protected boolean enabled;
	public boolean getEnabled () { return enabled; }
	public void setEnabled (final boolean enabled) { this.enabled = enabled; return; }
	
	protected boolean debug;
	public boolean getDebug () { return this.debug; }
	public void setDebug (final boolean debug) { this.debug = debug; return; }
	
	@Override
	protected
	String paramString ()
	{
		String s = super.paramString (); 
		s += ",enabled=" + enabled; 
		s += ",debug=" + debug; 
		return s; 
	}
	
	public interface PROP
	{
		public String CLASS = "class"; 
		public String IN = "in";
		public String OUT = "out"; 
		public String ERR = "err"; 
	    public String DEBUG = "debug";
	    public String FORM = "form";
	    public String ATTR = "attr";
	    public String PATH = "path";
	    public String PARAMETER = "param"; 
	    public String ON_FAIL = "onFail";
	    public String PATTERN = "pattern"; 
		public String EXPRESSION = "expression"; 
		public String EMAIL_AGENT_ID = "emailAgentId";
		public String EMAIL_TEMPLATE_ID = "emailTemplateId"; 
	}
	
	/**
	 * 
	 * @param req
	 * @return
	 */
	
	protected
	Alteration failure (final HttpServletRequest req)
	{
		// Forward to a local failure action if one was specified
		    	
		final String val = getProperty (PROP.ON_FAIL); 
		if (! StringUtil.isEmpty (val)) {
			return new ForwardAlteration (getProperty (PROP.ON_FAIL));
		} 
		
		// Otherwise, use the global failure handler if one was set
		
		final Alteration forward = SetFailureInterceptor.getFailureForward (req);
		if (forward != null) { 
			return forward; 
		}
	
		// Otherwise, give up and abandon the connection
		
		logger.warn ("Failure unrouteable: " + req.getRequestURI ()); 
		return ABORT;
	}
	
	/**
	 * Sentinel value to indicate no alteration to interceptor flow. 
	 */
	
	public static final Alteration NO_ALTERATION = null;
	public static final Alteration ABORT = new AbortAlteration ();
	public static final Alteration HALT = new HaltAlteration (); 
	
	@Override
	public
	boolean equals (final Object o)
	{
	    if (! (o instanceof Iterator<?>)) {
	        return false;
	    }
	    final Interceptor i = (Interceptor) o;
	    return Util.isEqual (getProperties (), i.getProperties ());
	}
	
	/**
	 * 
	 * @param req
	 * @param path
	 * @return
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	
	protected static
	Object findProperty (final HttpServletRequest req, final String path)
		throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
	{
		final int index = path.indexOf ('.'); 
		if (index != -1) { 
			final String attr = path.substring (0, index);
			final Object obj = req.getAttribute (attr); 
			if (obj == null) { 
				return null; 
			}
			return PropertyUtils.getProperty (obj, path.substring (index + 1)); 
		} else { 			
			return req.getAttribute (path); 
		}
	}
		
	/**
	 * Used to retrieve the <i>index</i>th element of the action mapping
	 * that was used to address this interceptor. Note that the zero-th element 
	 * contains any optional internal action prefix.  </p> 
	 * 
	 *  Some examples for a Ludwig-based web application that dispatches 
	 *  all URIs beginning with: "/example/-/" </p> 
	 *  
	 *  Example 1: "/example/-/foo/bar/baz"
	 *  
	 *  <ul>
	 *  <li>0 - ""
	 *  <li>1 - "foo"
	 *  <li>2 - "bar"
	 *  <li>3 - "baz"
	 *  </ul>
	 *  
	 *  Example 2: "^/internal/setup"
	 *  
	 *  <ul>
	 *  <li>0 - "^"
	 *  <li>1 - "internal"
	 *  <li>2 - "setup"
	 *  </ul>
	 *  
	 * @param index
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public
	String getPathElement (final HttpServletRequest req, final int index)
	{
		final List<String> path = (List<String>) req.getAttribute (Dispatcher.ATTR.PREFIX + "path");
		return path.get (index); 
	}

	/**
	 * 
	 * @param <Type>
	 * @param type
	 * @param req
	 * @param attr
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
    protected <T> 
	T findAttribute (final Class<T> type, final HttpServletRequest req, final String attr)
	{
		return (T) req.getAttribute (attr);  
	}
	
	/**
	 * 
	 * @param prop
	 * @param required
	 * @param defaultValue
	 * @return
	 * @throws ConfigurationException
	 */
	
	protected
	Long getLong (final String prop, final boolean required, final Long defaultValue)
		throws ConfigurationException
	{
		final String val; 
		if (required) { 
			val = require (prop);
		} else { 
			val = getProperty (prop);
		}
		if (val == null) { 
			return defaultValue; 
		} else { 
			return Long.parseLong (val); 
		}
		// NOT REACHED
	}
	
	/**
	 * 
	 * @param prop
	 * @param required
	 * @param defaultValue
	 * @return
	 * @throws ConfigurationException
	 */
	
	protected 
	Integer getInteger (final String prop, final boolean required, final Integer defaultValue)
		throws ConfigurationException
	{
		final String val; 
		if (required) { 
			val = require (prop); 
		} else { 
			val = getProperty (prop); 
		}
		if (val == null) { 
			return defaultValue; 
		} else {
			return Integer.parseInt (val); 
		}
		// NOT REACHED
	}
	
	/**
	 * 
	 * @param <Type>
	 * @param type
	 * @param prop
	 * @param required
	 * @param defaultValue
	 * @return
	 */
	
	protected <T extends Enum<T>>
	T getEnum (final Class<T> type, final String prop, final boolean required, final T defaultValue)
		throws ConfigurationException
	{
		final String val; 
		if (required) { 
			val = require (prop); 
		} else { 
			val = getProperty (prop); 
		}
		if (val == null) { 
			return defaultValue; 
		} else { 
			return Enum.valueOf (type, val); 
		}
		// NOT REACHED
	}
}

// EOF