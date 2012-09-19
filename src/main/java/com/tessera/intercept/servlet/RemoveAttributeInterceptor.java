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

public class RemoveAttributeInterceptor
	extends InterceptorSupport
{
	public
	RemoveAttributeInterceptor (final Map<String, String> props)
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
		attr = require (PROP.ATTR); 
		context = AttributeContext.valueOf (getProperty (PROP.CONTEXT, DEFAULT.CONTEXT)); 
		return; 
	}

	protected AttributeContext context; 
	protected String attr; 
	
	public
	Alteration intercept (final HttpServletRequest req, final HttpServletResponse res, DispatchContext dc)
		throws Exception
	{		
		if (req == null) { 
			context.removeAttribute (dc.getDispatcher ().getServletContext (), attr); 
		} else { 
			context.removeAttribute (req, attr); 
		}
		return NO_ALTERATION; 
	}
}

// EOF
