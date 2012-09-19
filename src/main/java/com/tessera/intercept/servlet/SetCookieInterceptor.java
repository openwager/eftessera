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

public class SetCookieInterceptor 
	extends InterceptorSupport
{
	public
	SetCookieInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 
	}
	
	interface PROP
	{
		public String NAME = "name"; 
		public String DOMAIN = "domain"; 
		public String PATH = "path"; 
		public String MAX_AGE = "maxAge"; 
		public String VALUE = "value"; 
	}
	
	@Override
	public
	void init ()
		throws Exception
	{
		this.name = require (PROP.NAME); 
		this.domain = require (PROP.DOMAIN); 
		this.path = getProperty (PROP.PATH, path); 
		this.maxAge = Integer.parseInt (require (PROP.MAX_AGE)); 
		this.valueExpr = new JexlStringExpression (require (PROP.VALUE)); 
		return; 
	}

	protected String name; 
	protected String domain; 
	protected String path = "/"; 
	protected int maxAge; 
	protected JexlExpression<String> valueExpr; 
	
	public 
	Alteration intercept (final HttpServletRequest req, final HttpServletResponse res, DispatchContext dc)
		throws Exception 
	{
		final String value = valueExpr.evaluate (req);
		final Cookie cookie = new Cookie (name, value); 
		cookie.setDomain (this.domain); 
		cookie.setPath (this.path); 
		cookie.setMaxAge (this.maxAge); 
		res.addCookie (cookie);
		return NO_ALTERATION;
	}
}

// EOF