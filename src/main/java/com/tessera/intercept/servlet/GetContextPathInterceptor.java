package com.tessera.intercept.servlet;

import java.util.*;

import javax.servlet.http.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.*;

/**
 * TODO: Comment goes here. 
 * 
 * @author Lee Crawford (crawford@twofish.com). 
 * @copyright Copyright (c) 2006-2009, Twofish, Inc.
 */

public class GetContextPathInterceptor
	extends InterceptorSupport
{
	public
	GetContextPathInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 
	}

	interface DEFAULT
	{
		public String ATTR = "APP"; 
	}
	
	public
	Alteration intercept (final HttpServletRequest req, final HttpServletResponse res, DispatchContext dc)
		throws Exception
	{
		req.setAttribute (getProperty (PROP.ATTR, DEFAULT.ATTR), req.getContextPath ()); 
		return NO_ALTERATION;
	}	
}

// EOF