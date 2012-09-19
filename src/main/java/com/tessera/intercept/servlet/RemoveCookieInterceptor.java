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

public class RemoveCookieInterceptor 
	extends InterceptorSupport
{
	public
	RemoveCookieInterceptor (final Map<String, String> props)
	{
		super (props); 
		return;
	}
	
	interface PROP
	{
		public String NAME = "name"; 
		public String DOMAIN = "domain"; 
		public String PATH = "path"; 
	}
	
	@Override
	public
	void init ()
		throws Exception
	{
		this.name = require (PROP.NAME); 
		this.domain = require (PROP.DOMAIN); 
		this.path = getProperty (PROP.PATH, path); 
		return; 
	}

	protected String name; 
	protected String domain; 
	protected String path = "/"; 
	
	public 
	Alteration intercept (final HttpServletRequest req, final HttpServletResponse res, DispatchContext dc)
		throws Exception 
	{
		CookieUtil.deleteCookie (res, name, domain, path); 
		return NO_ALTERATION; 
	}
}

// EOF
