package com.tessera.intercept.login;

import java.util.*;

import javax.servlet.http.*;

import org.apache.log4j.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.*;
import com.tessera.intercept.form.*;

/**
 * 
 * @author crawford
 *
 */

public class LoginInterceptor
	extends InterceptorSupport
{
	private static final Logger logger =  Logger.getLogger (LoginInterceptor.class); 
	
	public
	LoginInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 
	}
	
	/**
	 * @see Interceptor#intercept(HttpServletRequest, HttpServletResponse, DispatchContext)
	 */
	
    @SuppressWarnings("unchecked")
	public Alteration intercept (final HttpServletRequest req, final HttpServletResponse res, final DispatchContext dc)
        throws Exception
    {
		// Extract the login form 
		
		final Form form = findAttribute (Form.class, req, getProperty (PROP.FORM, "form"));
		if (form == null) {
			logger.warn ("Login attempt with no bound form."); 
			return failure (req); 
		}			
		
		// Make sure there's a registered login manager
		
		final LoginManager lm = LoginManagerUtil.getLoginManager (req); 
		if (lm == null) { 
			form.addGlobalError ("No login manager registered."); 
			logger.warn ("No LoginManager registered.");
			return failure (req); 
		} 
		
		// Use the login manager to try and login 
	
		final LoginSession lsess = (LoginSession) lm.login (req, form, dc);
		if (lsess == null) {
			return failure (req); 
		}
		
		return NO_ALTERATION; 		
    }
}

// EOF
