package com.tessera.intercept.servlet;

import java.util.*;
import java.util.regex.*;

import javax.servlet.http.*;

import com.tessera.intercept.*;

/**
 * TODO: Comment goes here. 
 * 
 * @author Lee Crawford (crawford@twofish.com). 
 * @copyright Copyright (c) 2006-2009, Twofish, Inc.
 */

public class MatchAttributeInterceptor
	extends PredicateInterceptorSupport
{
	public
	MatchAttributeInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 
	}
	
	interface PROP
	{
		public String ATTR = "attr"; 
		public String PATTERN = "pattern";
		public String CONTEXT = "context"; 
	}	
	
	protected String attr; 
	protected Pattern pattern;
	protected AttributeContext context; 
	
	protected String DEFAULT_CONTEXT = "request"; 
	
	@Override
	public
	void init ()
		throws Exception
	{
		super.init (); 
		attr = require (PROP.ATTR); 
		context = AttributeContext.valueOf (getProperty (PROP.CONTEXT, DEFAULT_CONTEXT)); 
		pattern = Pattern.compile (require (PROP.PATTERN)); 
		return; 
	}
	
	@Override
	public 
	boolean evaluate (final HttpServletRequest req, final HttpServletResponse res)
	{
		final Object val = context.getAttribute (req, attr);  
		
		if (val != null) { 
			if (val instanceof String) { 
				final String str = (String) val; 
				return pattern.matcher (str).matches (); 
			}
		}
		return false; 
	}
}

// EOF