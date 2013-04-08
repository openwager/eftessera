package com.tessera.intercept.login.cookie;

import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import com.tessera.dispatch.*;
import com.tessera.intercept.*;
import com.tessera.intercept.util.*;

/**
 * 
 * @author crawford
 *
 */

public class InitCookieManagerFactoryInterceptor
	extends ClassFactoryInterceptorSupport
{
	private static final Logger logger = Logger.getLogger (InitCookieManagerFactoryInterceptor.class); 
	
	public
	InitCookieManagerFactoryInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 
	}
	
    public 
    Alteration intercept (HttpServletRequest req, HttpServletResponse res, DispatchContext dc)
        throws Exception
    {
	    final CookieManager cm = (CookieManager) createObject (req); 
	    if (cm == null) { 
	    	throw new Exception ("No object created."); 
	    } else { 
	    	logger.info ("Installed cookie manager: " + cm.getClass ().getName ()); 
	    }
		final ServletContext sc = dc.getDispatcher ().getServletContext (); 
	    CookieManagerUtil.setCookieManager (cm, sc); 
	    return NO_ALTERATION; 
    }
}

// EOF