package com.tessera.intercept.servlet;

import java.util.*;

import javax.servlet.http.*;

import com.tessera.intercept.*;

/**
 * TODO: Comment goes here. 
 *  
 * @author Lee Crawford (crawford@twofish.com)
 * @copyright Copyright (c) 2006-2009, Twofish, Inc.
 */

public class MapAttributeTypeInterceptor
	extends MappedInterceptorSupport
{
//	private static final Logger logger = Logger.getLogger (MapAttributeTypeInterceptor.class); 
	
	public
	MapAttributeTypeInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 
	}
	
	interface PROP
	{
		public String ATTR = "attr";
		public String CONTEXT = "context";
		public String PRUNE = "prune"; 
	}

	interface DEFAULT
	{
		public static final String CONTEXT = AttributeContext.REQUEST.name ();  
	}

	@Override
	public
	void init ()
		throws Exception
	{
		super.init (); 
		attr = require (PROP.ATTR); 
		context = AttributeContext.valueOf (getProperty (PROP.CONTEXT, DEFAULT.CONTEXT)); 
        prune = "true".equals (getProperty (PROP.PRUNE));
        return; 
	}
	
	protected String attr;
	protected AttributeContext context;
	protected boolean prune; 
	
	public 
	String map (final HttpServletRequest req, final HttpServletResponse res)
	{		
		final Object val = context.getAttribute (req, attr);  
		
		if (val == null) { 
			return "null"; 
		} else if (prune) { 
			return val.getClass().getSimpleName (); 
		} else { 
			return val.getClass ().getName (); 
		}

		// NOT REACHED
	}	
}

// EOF
