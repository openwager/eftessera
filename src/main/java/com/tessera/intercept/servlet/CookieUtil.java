package com.tessera.intercept.servlet;

import javax.servlet.http.*;

/**
 * A library of general-purpose methods for manipulating {@linkplain Cookie) values
 * in servlet requests and responses. 
 * 
 * @author Lee Crawford (crawford@twofish.com)
 * @copyright Copyright (c) 2006-2009, Twofish, Inc. 
 */

public class CookieUtil 
{
	/**
	 * Private constructor to defeat instantiation.
	 */
	
	private
	CookieUtil ()
	{
		return; 
	}

	/**
	 * Used to determine whether the servlet request bore a cookie of the specified 
	 * name. 
	 * 
	 * @param req the servlet request
	 * @param name the name of the cookie being looked for
	 * @return true if a cookie of the specified name was present
	 */
	
	public static 
	boolean hasCookie (final HttpServletRequest req, final String name)
	{
		return getCookie (req, name) != null; 
	}

	/**
	 * 
	 * @param req
	 * @param name
	 * @return
	 */
	
	public static
	String getCookie (final HttpServletRequest req, final String name)
	{
		final Cookie cs [] = req.getCookies ();
		if (cs != null) {
			for (final Cookie c : cs) {
				if (name.equals (c.getName ())) {
					return c.getValue ();
				}
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param res
	 * @param name
	 * @param domain
	 * @param path
	 */
	
    public static 
    void deleteCookie (final HttpServletResponse res, final String name, final String domain, final String path)
    {
    	System.err.println ("deleteCookie (" + name + ", " + domain + ", " + path + ")"); 
        final Cookie cookie = new Cookie (name, "");
    	if (domain != null) { 
    		cookie.setDomain (domain);
    	}
    	if (path != null) { 
        	cookie.setPath (path);    		
    	}
        cookie.setMaxAge (0);
        res.addCookie (cookie);
        return; 
    }
}

// EOF