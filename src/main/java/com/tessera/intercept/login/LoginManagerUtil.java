package com.tessera.intercept.login;

import javax.servlet.*;
import javax.servlet.http.*;

import com.tessera.dispatch.*;

/**
 * 
 * @author crawford
 *
 */

public class LoginManagerUtil
{
	private
	LoginManagerUtil ()
	{
		return; 
	}

	interface ATTR
	{
		public String PREFIX = Dispatcher.ATTR.PREFIX + "l";
		public String LOGIN_MANAGER = PREFIX + "M";
		public String LOGIN_SESSION = PREFIX + "S"; 
	}
	
	/**
	 * Utility method for retrieving the servlet context from a request object
	 * 
	 * @param req
	 * @return
	 */
	
	private static
	ServletContext getServletContext (final HttpServletRequest req)
	{
		return req.getSession ().getServletContext (); 
	}
	
	/**
	 * 
	 * @param req
	 * @return
	 */
	
	public static
	LoginManager<?> getLoginManager (final HttpServletRequest req)
	{
		assert req != null; 
		
		return (LoginManager<?>) getServletContext (req).getAttribute (ATTR.LOGIN_MANAGER); 
	}
	
	/**
	 * 
	 * @param req
	 * @param lm
	 */
	
	public static
	void setLoginManager (final HttpServletRequest req, final LoginManager<?> lm)
	{
		assert req != null;
		assert lm != null;
		
		setLoginManager (getServletContext (req), lm);  
		return; 
	}
	
	/**
	 * 
	 * @param sc
	 * @param lm
	 */
	
	public static
	void setLoginManager (final ServletContext sc, final LoginManager<?> lm)
	{
		sc.setAttribute (ATTR.LOGIN_MANAGER, lm);
		return; 
	}
	
	/**
	 * 
	 * @param req
	 */
	
	public static
	void removeLoginManager (final HttpServletRequest req)
	{
		assert req != null; 
	
		getServletContext (req).removeAttribute (ATTR.LOGIN_MANAGER); 
		return; 
	}
}

// EOF 