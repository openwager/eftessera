package com.tessera.intercept.login.cookie;

import java.util.*;

/**
 * 
 * @author crawford
 *
 */

public class RemoveBCookieInterceptor
	extends RemoveCookieInterceptor 
{
	public
	RemoveBCookieInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 
	}

	protected 
	String getCookie (CookieManager cm) 
	{
		return cm.getCCookieName ();
	}
}

// EOF
 