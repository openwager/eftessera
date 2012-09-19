package com.tessera.site.login;

import com.lattice.data.*;
import com.tessera.intercept.form.*;

/**
 * 
 * @author crawford
 *
 */

public class LoginForm
	extends Form
{
	public
	LoginForm ()
	{
		return; 
	}
	
	@StringValidations (validations={
		@StringValidation (regex=".+", errmsg="Username must be specified."),
		@StringValidation (regex=".{3,32}", errmsg="Invalid username specified.")
	})
	protected String username; 
	public String getUsername () { return this.username; } 
	public void setUsername (final String username) { this.username = username; return; } 
		
	@StringValidations (validations={
		@StringValidation (regex=".+", errmsg="Password must be specified."),
		@StringValidation (regex=".{3,32}", errmsg="Invalid password specified.")
	})
	protected String password; 
	public String getPassword () { return this.password; } 
	public void setPassword (final String password) { this.password = password; return; } 
	
	protected boolean rememberMe; 
	public boolean getRememberMe () { return this.rememberMe; } 
	public void setRememberMe (final boolean rememberMe) { this.rememberMe = rememberMe; return; } 

	protected String payload; 
	public String getPayload () { return this.payload; } 
	public void setPayload (final String payload) { this.payload = payload; return; } 
	
	protected String currentUrl;
	public String getCurrentUrl() { return currentUrl; }
	public void setCurrentUrl(String currentUrl) { this.currentUrl = currentUrl; }
	
}

// EOF
