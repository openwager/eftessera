package com.tessera.intercept.login;

import java.util.*;

import javax.servlet.http.*;

import com.lattice.validate.*;
import com.tessera.dispatch.*;
import com.tessera.intercept.*;
import com.tessera.intercept.form.*;

/**
 * 
 * @author crawford
 *
 */

public class AjaxLoginInterceptor
	extends InterceptorSupport
{
	public
	AjaxLoginInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 
	}
	
	interface ERROR_CODE
	{
		public int SUCCESS = 0; 
		public int NO_LOGIN_MANAGER = 1; 
		public int NO_FORM = 2; 
		public int FORM_INVALID = 3;  
		public int AUTH_FAILED = 4; 
	}
		
	@SuppressWarnings ("unchecked")
	public
	Alteration intercept (final HttpServletRequest req, final HttpServletResponse res, final DispatchContext dc)
	    throws Exception
	{
		final AjaxResponse json = new AjaxResponse (req); 
		
		// Extract the login form 
		
		final Form form = findAttribute (Form.class, req, getProperty (PROP.FORM, "form"));
		if (form == null) {
			json.setResponse (ERROR_CODE.NO_FORM, "Form not bound."); 
			return NO_ALTERATION; 
		}			
		
		// Did the form validate
		
		try { 
			form.validate ();	
			if (form.getHasErrors ()) {
				json.setResponse (ERROR_CODE.FORM_INVALID, "Form invalid."); 
				// TODO: Consider putting more detail in the response
				return NO_ALTERATION; 
			}
		}
		catch (final ValidationException v_e) { 
			json.setResponse (ERROR_CODE.FORM_INVALID, "Form invalid."); 
			// TODO: Consider putting more detail in the response
			return NO_ALTERATION; 
		}
		
		// Make sure there's a registered login manager
		
		final LoginManager lm = LoginManagerUtil.getLoginManager (req); 
		if (lm == null) { 
			json.setResponse (ERROR_CODE.NO_LOGIN_MANAGER, "Login manager not registered."); 
			return NO_ALTERATION; 
		} 
		
		// Use the login manager to try and login 
	
		final LoginSession lsess = (LoginSession) lm.login (req, form, dc);
		if (lsess == null) {
			json.setResponse (ERROR_CODE.AUTH_FAILED, "Login failed."); 
			return NO_ALTERATION; 
		}

		json.setResponse (ERROR_CODE.SUCCESS); 
		return NO_ALTERATION; 		
	}
}

// EOF