package com.tessera.intercept.login;

import java.util.*;

import javax.servlet.http.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.*;

/**
 * 
 * @author crawford
 *
 */

public class AjaxLogoutInterceptor
	extends InterceptorSupport
{
	public
	AjaxLogoutInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 
	}
	
	interface ERROR_CODE
	{
		public int SUCCESS = 0; 
		public int NO_LOGIN_MANAGER = 1; 
	}
		
	public
	Alteration intercept (final HttpServletRequest req, final HttpServletResponse res, final DispatchContext dc)
	    throws Exception
	{
		final AjaxResponse json = new AjaxResponse (req); 
		
		// Make sure there's a registered login manager
		
		final LoginManager lm = LoginManagerUtil.getLoginManager (req); 
		if (lm == null) { 
			json.setResponse (ERROR_CODE.NO_LOGIN_MANAGER, "Login manager not registered."); 
			return NO_ALTERATION; 
		} 
		
		// Use the login manager to try and login 
	
		if (lm.isLoggedIn (req)) { 
			lm.logout (req); 
		}
		
		json.setResponse (ERROR_CODE.SUCCESS); 
		return NO_ALTERATION; 		
	}
}

// EOF