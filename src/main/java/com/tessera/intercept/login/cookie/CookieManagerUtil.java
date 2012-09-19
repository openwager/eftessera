package com.tessera.intercept.login.cookie;

import javax.servlet.*;
import javax.servlet.http.*;

import com.tessera.dispatch.*;

/**
 * 
 * @author crawford
 *
 */

public class CookieManagerUtil
{
	private
	CookieManagerUtil ()
	{
		return; 
	}

	interface ATTR
	{
		public String PREFIX = Dispatcher.ATTR.PREFIX + "l";
		public String COOKIE_MANAGER = PREFIX + "C";
	}

	public static
	CookieManager getCookieManager (final HttpServletRequest req)
	{
		return getCookieManager (req.getSession ().getServletContext ()); 
	}
	
	public static
	CookieManager getCookieManager (final ServletContext sc)
	{
		return (CookieManager) sc.getAttribute (ATTR.COOKIE_MANAGER);  
	}
	
	public static
	boolean hasCookieManager (final ServletContext sc)
	{
		return getCookieManager (sc) != null; 
	}

	public static
	void setCookieManager (final CookieManager cm, final ServletContext sc)
	{
		sc.setAttribute (ATTR.COOKIE_MANAGER, cm); 
		return; 
	}
	
	public static
	void removeCookieManager (final ServletContext sc)
	{
		sc.removeAttribute (ATTR.COOKIE_MANAGER); 
		return; 
	}
}

// EOF