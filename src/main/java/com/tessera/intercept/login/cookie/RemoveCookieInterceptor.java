package com.tessera.intercept.login.cookie;

import java.util.*;

import javax.servlet.http.*;

import org.apache.log4j.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.*;
import com.tessera.intercept.servlet.*;

/**
 * 
 * @author crawford
 *
 */

abstract class RemoveCookieInterceptor
	extends InterceptorSupport
{
	protected static final Logger logger = Logger.getLogger (RemoveCookieInterceptor.class); 
	
	protected
	RemoveCookieInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 
	}
		
	protected CookieManager cookieManager; 

	protected
	CookieManager getCookieManager (final HttpServletRequest req)
	{
		if (cookieManager == null) { 
			synchronized (this) { 
				final CookieManager cookieManager = CookieManagerUtil.getCookieManager (req);
				if (cookieManager == null) { 
					logger.error ("CookieManager not found."); 
				} else { 
					this.cookieManager = cookieManager; 
				}
			}
		}
		return cookieManager; 
	}
	
	abstract protected
	String getCookie (final CookieManager cm);

    public final 
    Alteration intercept (final HttpServletRequest req, final HttpServletResponse res, final DispatchContext dc)
        throws Exception
    {
		final CookieManager cm = getCookieManager (req); 
		final String cookie = getCookie (cm); 
		final String domain = cm.getDomain (); 
		final String path = cm.getPath (); 
	    CookieUtil.deleteCookie (res, cookie, domain, path);
	    return NO_ALTERATION;
    }	
}

// EOF