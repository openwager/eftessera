package com.tessera.intercept.servlet;

import java.util.*;
import java.util.regex.*;

import javax.servlet.http.*;

import com.tessera.intercept.*;

/**
 * TODO: Comment goes here. 
 *  
 * @author Lee Crawford (crawford@twofish.com)
 * @copyright Copyright (c) 2006-2009, Twofish, Inc.
 */

public class MatchAttributeTypeInterceptor
	extends PredicateInterceptorSupport
{
	public
	MatchAttributeTypeInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 
	}
	
	@Override
	public
	void init ()
		throws Exception
	{
		super.init (); 
		attr = require (PROP.ATTR); 
		pattern = Pattern.compile (require (PROP.PATTERN)); 
		return; 
	}

	protected String attr;
	protected AttributeContext context; 
	protected Pattern pattern; 
	
	@Override
	public boolean evaluate (final HttpServletRequest req, final HttpServletResponse res)
	{
		final Object val = context.getAttribute (req, attr);  
		
		return val != null && pattern.matcher (val.getClass ().getName ()).matches (); 
	}
}

// EOF
