package com.tessera.intercept.login.cookie;

import java.util.*;

import javax.servlet.http.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.*;
import com.tessera.intercept.servlet.*;

/**
 * 
 * @author crawford
 *
 */

public class SetACookieInterceptor
	extends SetCookieInterceptorSupport<ACookieGenerator>
{
//	private static final Logger logger = Logger.getLogger (SetACookieInterceptor.class); 
	
	public
	SetACookieInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 
	}
		
	protected 
	String getCookie (CookieManager cm)
	{
		return cm.getACookieName ();
	}

	public
    Alteration intercept (final HttpServletRequest req, final HttpServletResponse res, final DispatchContext dc)
        throws Exception
    {
		final CookieManager cm = getCookieManager (req); 
		final String name = getCookie (cm);
		
		// If they've specified not to overwrite and there's already a cookie
		// there then we'll just skip this step
		
		if (CookieUtil.hasCookie (req, name)) {
			if (! overwrite) { 
				return NO_ALTERATION; 
			}					
		}
		
		// Otherwise, use the generator to generate a new A cookie and set it on the response object
		
		final String value = cm.generateACookie (req);
		if (value != null) { 
			final Cookie cookie = new Cookie (name, value); 
			cookie.setMaxAge (maxAge); 
			final String domain = cm.getDomain (); 
			if (domain != null) { 
				cookie.setDomain (domain); 
			}		
			final String path = cm.getPath (); 
			if (path != null) { 
				cookie.setPath (path);
			}
			res.addCookie (cookie); 
		}
		
		return NO_ALTERATION; 
    }
}

// EOF