package com.tessera.intercept.login;

import java.util.*;

import javax.servlet.http.*;

import org.apache.log4j.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.*;
import com.tessera.intercept.login.cookie.*;
import com.tessera.intercept.servlet.*;
import com.tessera.site.login.*;

/**
 * 
 * @author crawford
 *
 */

public class AutoLoginInterceptor
	extends InterceptorSupport
{
	private static final Logger logger = Logger.getLogger (AutoLoginInterceptor.class); 
	
	public
	AutoLoginInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 		
	}

	interface PROP
	extends InterceptorSupport.PROP
	{	
//		public String GENERATOR = "generator";
//		public String DOMAIN = "domain"; 
	}
	
//	protected String domain; 
//	public String getDomain () { return this.domain; } 
//		
//	protected String path; 
//	public String getPath() { return this.path; } 	
	
//	protected CCookieGenerator generator; 
//	public CCookieGenerator getGenerator () { return this.generator; } 
//	
//	public
//	void init ()
//		throws Exception
//	{	
//		domain = getProperty (PROP.DOMAIN);
//		path = getProperty (PROP.PATH, "/"); 
//		final String className = require (PROP.GENERATOR); 
//		final Object obj = ClassUtil.newInstance (className);
//		if (! (obj instanceof CCookieGenerator)) { 
//			logger.error ("Not a CCookieGenerator: " + className); 
//		}
//		generator = (CCookieGenerator) obj; 
//		return; 
//	}
	
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

	public 
	Alteration intercept (final HttpServletRequest req, final HttpServletResponse res, final DispatchContext dc)
        throws Exception
    {
		// Retrieve their C cookie and attempt to verify the signature
		
		final CookieManager cm = getCookieManager (req); 
		final String domain = cm.getDomain (); 
		final String path = cm.getPath (); 
		final String value = CookieUtil.getCookie (req, cm.getCCookieName ());
		
		if (value != null) { 
			try { 
				final LoginSession lsess = cm.verifyCCookie (value, req); 
				if (lsess == null) { 
					CookieUtil.deleteCookie (res, SiteCookies.C_COOKIE, domain, path);
				} else { 
					final LoginManager<?> lm = LoginManagerUtil.getLoginManager (req);
					lm.autologin (lsess, req); 
				}
			} catch (final MalformedCookieException mc_e) {
				logger.warn (mc_e.getMessage (), mc_e); 
				CookieUtil.deleteCookie (res, SiteCookies.C_COOKIE, domain, path);				
			}
		}
		
	    return NO_ALTERATION;
    }
}

// EOF
