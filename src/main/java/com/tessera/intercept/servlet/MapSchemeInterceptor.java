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

public class MapSchemeInterceptor 
	extends MappedInterceptorSupport
{
	public 
	MapSchemeInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 
	}

//	@Override
	public
	String map (final HttpServletRequest req, final HttpServletResponse res) 
	{
		return req.getScheme (); 
	}
}

// EOF
