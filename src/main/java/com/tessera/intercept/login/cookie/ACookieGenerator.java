package com.tessera.intercept.login.cookie;

import javax.servlet.http.*;

/**
 * 
 * @author crawford
 *
 */

public interface ACookieGenerator
{
	/**
	 * 
	 * @param req
	 * @return
	 */
	
	public
	String generateACookie (HttpServletRequest req); 
}

// EOF
