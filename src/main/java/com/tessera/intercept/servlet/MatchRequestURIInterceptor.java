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

public class MatchRequestURIInterceptor
	extends PredicateInterceptorSupport
{
	public
	MatchRequestURIInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 
	}
	
	interface PROP
	{
		public static final String PATTERN = "pattern"; 
	}
		
	@Override
	public void init ()
		throws Exception
	{
		final String regex = require (PROP.PATTERN);
		pattern = Pattern.compile (regex); 
		return; 
	}

	protected Pattern pattern; 
	
	@Override
	public
	boolean evaluate (final HttpServletRequest req, final HttpServletResponse res)
	{		 	
		return pattern.matcher (req.getRequestURI ()).matches (); 
	}
}

// EOF
