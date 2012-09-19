package com.tessera.intercept.login.cookie;

import javax.servlet.http.*;

import com.tessera.intercept.login.*;

/**
 * 
 * @author crawford
 *
 */

public interface BCookieGenerator
{
	/**
	 * 
	 * @param res
	 * @return
	 */
	
	public
	String generateBCookie (HttpServletRequest res, LoginSession lsess)
		throws Exception; 
}

// EOF