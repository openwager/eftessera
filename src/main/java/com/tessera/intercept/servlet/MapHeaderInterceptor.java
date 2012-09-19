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

public class MapHeaderInterceptor 
	extends MappedInterceptorSupport
{
	public
	MapHeaderInterceptor (final Map<String, String> props)
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
		throws ConfigurationException
	{
		this.name = require (PROP.NAME); 
		return; 
	}
	
	protected String name;
	
//	@Override
	public
	String map (final HttpServletRequest req, final HttpServletResponse res)
	{
		return req.getHeader (name); 
	}
}

// EOF
