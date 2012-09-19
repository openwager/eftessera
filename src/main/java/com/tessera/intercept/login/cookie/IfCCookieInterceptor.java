package com.tessera.intercept.login.cookie;

import java.util.*;

/**
 * 
 * @author crawford
 *
 */

public class IfCCookieInterceptor
	extends IfCookieInterceptorSupport
{
	public
	IfCCookieInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 
	}

	protected 
	String getCookie (final CookieManager cm)
	{
		return cm.getCCookieName ();
	}
}

// EOF