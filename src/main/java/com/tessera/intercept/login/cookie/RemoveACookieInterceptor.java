package com.tessera.intercept.login.cookie;

import java.util.*;

/**
 * 
 * @author crawford
 *
 */

public class RemoveACookieInterceptor
	extends RemoveCookieInterceptor
{
	public
	RemoveACookieInterceptor (final Map<String, String> props)
	{
		super (props);  
		return; 
	}

	protected 
	String getCookie (CookieManager cm) 
	{
		return cm.getACookieName ();
	}
}

// EOF
