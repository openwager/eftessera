package com.tessera.intercept.login.cookie;

import javax.servlet.http.*;

import com.tessera.intercept.login.*;

/**
 * 
 * @author crawford
 *
 */

public interface CCookieGenerator
{
	/**
	 * 
	 * @param req
	 * @return
	 */
	
	public
	String generateCCookie (HttpServletRequest req, LoginSession lsess)
		throws Exception; 
	
	/**
	 * 
	 * @param cookie
	 * @param req
	 * @return
	 */
	
	public
	LoginSession verifyCCookie (String cookie, HttpServletRequest req)
		throws Exception; 
}

// EOF