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

public class IfAttributeInterceptor
	extends PredicateInterceptorSupport
{
	public
	IfAttributeInterceptor (final Map<String, String> props)
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
		public String CONTEXT = AttributeContext.REQUEST.name ();
	}

	@Override
	public void init ()
		throws ConfigurationException
	{
		attr = require (PROP.ATTR);
		context = AttributeContext.valueOf (getProperty (PROP.CONTEXT, DEFAULT.CONTEXT));
		return;
	}

	protected AttributeContext context;
	protected String attr;

	@Override
	public 
	boolean evaluate (final HttpServletRequest req, final HttpServletResponse res)
	{
		return context.getAttribute (req, attr) != null; 
	}
}

// EOF