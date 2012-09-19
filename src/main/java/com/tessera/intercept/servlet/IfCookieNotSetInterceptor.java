package com.tessera.intercept.servlet;

import java.util.*;

import javax.servlet.http.*;

/**
 * An interceptor that negates the behavior of the {@linkplain IfCookieSetInterceptor}.  
 * Provided simply as a convenience to permit mappings files to be written in a more readable
 * fashion. 
 * 
 * @author Lee Crawford (crawford@twofish.com)
 * @copyright Copyright (c) 2006-2009, Twofish, Inc.
 */

public class IfCookieNotSetInterceptor 
	extends IfCookieSetInterceptor
{
	public
	IfCookieNotSetInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 
	}
	
	@Override
	public
	boolean evaluate (final HttpServletRequest req, final HttpServletResponse res) 
	{
		return ! super.evaluate (req, res); 
	}
}

// EOF
