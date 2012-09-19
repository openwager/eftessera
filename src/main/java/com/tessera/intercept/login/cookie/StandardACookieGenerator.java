package com.tessera.intercept.login.cookie;

import javax.servlet.http.*;

/**
 * 
 * @author crawford
 *
 */

public class StandardACookieGenerator
	implements ACookieGenerator
{
	public
	StandardACookieGenerator ()
	{
		return; 
	}

	/**
	 * @see ACookieGenerator#generateACookie(HttpServletRequest)
	 */
	
    public 
    String generateACookie (final HttpServletRequest req)
    {
		final StringBuffer buf = new StringBuffer (); 
		buf.append (req.getRemoteAddr ());
		buf.append ('.'); 
		buf.append (System.currentTimeMillis ()); 
		return buf.toString (); 
    }
}

// EOF