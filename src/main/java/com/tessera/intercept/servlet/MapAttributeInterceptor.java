package com.tessera.intercept.servlet;

import java.util.*;

import javax.servlet.http.*;

import com.tessera.intercept.*;

/**
 * TODO: Comment goes here. 
 * 
 * @author Lee Crawford (crawford@twofish.com). 
 * @copyright Copyright (c) 2006-2009, Twofish, Inc.
 */

public class MapAttributeInterceptor
	extends MappedInterceptorSupport
{
	public
	MapAttributeInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 
	}
	
	interface PROP
	{
		public String ATTR = "attr";
		public String CONTEXT = "context";  
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
		attrExpr = new JexlObjectExpression (require (PROP.ATTR)); 
		context = AttributeContext.valueOf (getProperty (PROP.CONTEXT, DEFAULT.CONTEXT)); 
		return;
	}
	
	protected JexlObjectExpression attrExpr; 
	protected AttributeContext context; 
	
	public 
	String map (final HttpServletRequest req, final HttpServletResponse res)
		throws Exception
	{		
		final Object val = attrExpr.evaluate (req); 
		
		if (val == null) { 
			return null; 
		} else { 
			return val.toString (); 
		}
	}	
}

// EOF