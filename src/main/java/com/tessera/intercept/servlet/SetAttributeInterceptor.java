package com.tessera.intercept.servlet;

import java.util.*;

import javax.servlet.http.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.*;

/**
 * TODO: Comment goes here. 
 * 
 * @author Lee Crawford (crawford@twofish.com)
 * @copyright Copyright (c) 2006-2009, Twofish, Inc.
 */

public class SetAttributeInterceptor
	extends InterceptorSupport
{
	public
	SetAttributeInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 
	}
	
	interface PROP
		extends InterceptorSupport.PROP
	{
		public static final String CONTEXT = "context";
		public static final String VALUE = "value"; 
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
		value = new JexlExpression<Object> (Object.class, require (PROP.VALUE)); 
		attr = require (PROP.ATTR);
		context = AttributeContext.valueOf (getProperty (PROP.CONTEXT, DEFAULT.CONTEXT)); 
		return; 
	}	

	protected AttributeContext context;
	protected String attr;
	protected JexlExpression<Object> value; 

	public
	Alteration intercept (final HttpServletRequest req, final HttpServletResponse res, DispatchContext dc)
		throws Exception
	{
		final Object val = value.evaluate (req); 
		context.setAttribute (req, attr, val); 
		return NO_ALTERATION; 
	}	
}

// EOF