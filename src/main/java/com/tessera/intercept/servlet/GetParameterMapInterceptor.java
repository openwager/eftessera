package com.tessera.intercept.servlet;

import java.util.*;

import javax.servlet.http.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.*;
import com.weaselworks.util.*;

/**
 * TODO: Comment goes here. 
 * 
 * @author Lee Crawford (crawford@twofish.com). 
 * @copyright Copyright (c) 2006-2009, Twofish, Inc.
 */

public class GetParameterMapInterceptor
	extends InterceptorSupport
{
	public
	GetParameterMapInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 
	}
	
	public
	void init ()
	{
		final String val = getProperty ("collapse"); 
		if (! StringUtil.isEmpty (val)) { 
			collapse = Boolean.parseBoolean (val); 
		}
		return; 
	}

	protected boolean collapse = true; 
	
	interface DEFAULT
	{
		public String ATTR = "param"; 
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public
	Alteration intercept (final HttpServletRequest req, final HttpServletResponse res, DispatchContext dc)
		throws Exception
	{
		Map map = req.getParameterMap (); 
		if (collapse) {
			map = new CollapsedMap (map); 
		} 
		req.setAttribute (getProperty (PROP.ATTR, DEFAULT.ATTR), map); 
		return NO_ALTERATION; 
	}
}

// EOF
