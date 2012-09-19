package com.tessera.site.login;

import java.util.*;

import javax.servlet.http.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.login.*;

/**
 * 
 * @author crawford
 *
 */

public class SiteLoginManager
	extends LoginManagerSupport <LoginForm>
{
	public
	SiteLoginManager (final Map<String, String> props)
	{
		super (props); 
		return; 
	}

	/**
	 * 
	 */
	
	@Override
	protected
	LoginSession doLogin (final HttpServletRequest req, final LoginForm form, final DispatchContext dc)
		throws LoginException
	{	
		final String username = form.getUsername (); 
		final String password = form.getPassword (); 
		
		if ("fred".equals (username)) {
			form.addGlobalError ("You have been banned."); 
			return null; 
		} else if ("susan".equals (username)) { 			
			form.addGlobalError ("You have been banned."); 
			form.addGlobalError ("Really."); 
			return null; 
		} else if ("ralph".equals (username)) { 
			throw new LoginException ("Oh no!"); 
		} else if (username.equals (password)) {
			final LoginSession sess = new LoginSession (username.hashCode (), username); 
			return sess; 
		} else {
			form.addGlobalError ("Username/password invalid."); 
			return null; 
		}
		
		// NOT REACHED
	}
}

// EOF