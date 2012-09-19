package com.tessera.intercept.servlet;

import java.util.*;

import javax.servlet.http.*;

import com.tessera.intercept.*;

/**
 * An interceptor that is used to conditionally execute an enclosed interceptor chain
 * depending on whether a specified cookie is set or not. 
 * 
 * @author Lee Crawford (crawford@twofish.com)
 * @copyright Copyright (c) 2006-2009, Twofish, Inc.
 */

public class IfCookieSetInterceptor
	extends PredicateInterceptorSupport
{
	public
	IfCookieSetInterceptor (final Map<String, String> props) 
	{
		super (props);
		return;
	}

	interface PROP
	{
		public String NAME = "name";
	}

	@Override
	public
	void init ()
		throws Exception
	{
		super.init (); 
		this.name = require (PROP.NAME);
		return;
	}

	protected String name; 

	@Override
	public
	boolean evaluate (final HttpServletRequest req, final HttpServletResponse res) 
	{
		return CookieUtil.hasCookie (req, name);
	}
}

// EOF
