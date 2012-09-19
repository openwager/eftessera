package com.tessera.intercept.login.cookie;

import java.util.*;

/**
 * 
 * @author crawford
 *
 */

public class IfACookieInterceptor
	extends IfCookieInterceptorSupport
{
	public
	IfACookieInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 
	}

	protected 
	String getCookie (final CookieManager cm) 
	{
		return cm.getACookieName (); 
	}
}

// EOF
