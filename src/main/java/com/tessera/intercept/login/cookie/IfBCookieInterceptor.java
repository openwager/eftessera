package com.tessera.intercept.login.cookie;

import java.util.*;

/**
 * 
 * @author crawford
 *
 */

public class IfBCookieInterceptor
	extends IfCookieInterceptorSupport
{
	public
	IfBCookieInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 
	}

	protected 
	String getCookie (CookieManager cm) 
	{
		return cm.getBCookieName (); 
	}
}

// EOF
