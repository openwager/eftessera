package com.tessera.intercept.login.cookie;

import java.util.*;

import javax.servlet.http.*;

import org.apache.log4j.*;

import com.tessera.intercept.*;
import com.tessera.intercept.servlet.*;

/**
 * 
 * @author crawford
 *
 */

abstract class IfCookieInterceptorSupport
	extends PredicateInterceptorSupport
{
	protected static final Logger logger = Logger.getLogger (IfCookieInterceptorSupport.class); 
	
	protected
	IfCookieInterceptorSupport (final Map<String, String> props)
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
	
	@Override
    public final  
    boolean evaluate (final HttpServletRequest req, final HttpServletResponse res)
        throws Exception
    {
		final CookieManager cm = getCookieManager (req); 
		final String cookie = getCookie (cm); 
		if (cookie == null) { 
			return false; 
		} else { 
			return CookieUtil.hasCookie (req, cookie);
		}
		// NOT REACHED
    }
}

// EOF