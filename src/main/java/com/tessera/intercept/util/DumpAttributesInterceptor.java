package com.tessera.intercept.util;

import java.util.*;

import javax.servlet.http.*;

import org.apache.log4j.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.*;

/**
 * 
 * @author crawford
 *
 */

abstract class DumpAttributesInterceptor
	extends InterceptorSupport
{
	private static final Logger logger = Logger.getLogger (DumpAttributesInterceptor.class); 
	
	public
	DumpAttributesInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 
	}

    public Alteration intercept (HttpServletRequest req, HttpServletResponse res, DispatchContext dc)
        throws Exception
    {
		logger.info (getLabel () + " attributes = {");
		final Enumeration<String> attrs = getAttributeNames (req); 
		while (attrs.hasMoreElements ()) {   
			final String key = attrs.nextElement ();
			final Object value = getAttribute (req, key); 
			logger.info ("  '" + key + "' := " + value); 
		}
		logger.info ("}"); 
	    return NO_ALTERATION;

    }

	/**
	 * 
	 * @return
	 */
		
	abstract 
	String getLabel ();
	
	/**
	 * 
	 * @return
	 */
	
	abstract
	Enumeration<String> getAttributeNames (HttpServletRequest req);
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	
	abstract
	Object getAttribute (HttpServletRequest req, String name); 
}

// EOF
