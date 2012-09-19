package com.tessera.intercept.login;

import javax.servlet.http.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.form.*;

/**
 * 
 * @author crawford
 *
 */

public interface LoginManager <FormType extends Form>
{
	/**
	 * 
	 * @return
	 */
	
	public
	LoginSession getLoginSession (HttpServletRequest req);
	
	/**
	 * 
	 */
	
	public
	boolean isLoggedIn (HttpServletRequest req); 
	
	/**
	 * 
	 * @param props
	 */
	
	public 
	LoginSession login (HttpServletRequest req, FormType form, DispatchContext dc) 
		throws Exception; 
	
	/**
	 * 
	 * @param lsess
	 * @param req
	 */
	
	public
	void autologin (LoginSession lsess, HttpServletRequest req)
		throws Exception; 

	/**
	 * 
	 */
	
	public
	void logout (HttpServletRequest req) 
		throws Exception; 
}

// EOF