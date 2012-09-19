package com.tessera.intercept.login.cookie;

import java.util.*;

/**
 * 
 * @author crawford
 *
 */

public class RemoveCCookieInterceptor
	extends RemoveCookieInterceptor
{
	public
	RemoveCCookieInterceptor (final Map<String, String> props)
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