package com.tessera.intercept.login.cookie;

import java.util.*;

import javax.servlet.http.*;

import com.tessera.intercept.login.*;

/**
 * 
 * @author crawford
 *
 */

abstract public class CookieManager
{
	protected
	CookieManager ()
	{
		return; 
	}
	
	public
	CookieManager (final Map<String, String> props)
	{
		String path = props.get (PROP.PATH); 
		if (path != null) { 
			setPath (path); 
		}
		final String domain  = props.get (PROP.DOMAIN); 
		if (domain != null) { 
			setDomain (domain); 
		}
		return; 
	}	
	
	interface PROP
	{
		public String PATH = "path"; 
		public String DOMAIN = "domain"; 
	}
	
	protected String path = "/"; 
	public String getPath () { return this.path; } 
	public void setPath (final String path) { this.path = path; return; } 
	
	protected String domain; 
	public String getDomain () { return this.domain; } 
	public void setDomain (final String domain) { this.domain = domain; return; } 
	
	public interface DEFAULT
	{
		public static final String A_COOKIE = "A"; 
		public static final String B_COOKIE = "B"; 
		public static final String C_COOKIE = "C"; 
	}
	
	protected String aCookieName = DEFAULT.A_COOKIE;
	public String getACookieName () { return this.aCookieName; }
	protected void setACookieName (final String aCookieName) { this.aCookieName = aCookieName; return; } 
		
	protected String bCookieName = DEFAULT.B_COOKIE; 
	public String getBCookieName () { return this.bCookieName; } 
	protected void setBCookieName (final String bCookieName) { this.bCookieName = bCookieName; return; } 
	
	protected String cCookieName = DEFAULT.C_COOKIE;
	public String getCCookieName () { return this.cCookieName; } 
	protected void setCCookieName (final String cCookieName) { this.cCookieName = cCookieName; return; }
	
    public
    String generateACookie (final HttpServletRequest req)
    {
		final StringBuffer buf = new StringBuffer (); 
		buf.append (req.getRemoteAddr ());
		buf.append ('.'); 
		buf.append (System.currentTimeMillis ()); 
		return buf.toString (); 
    }
	
    abstract public
    String generateBCookie (HttpServletRequest res, LoginSession lsess)
        throws Exception;
	
    abstract public 
    String generateCCookie (HttpServletRequest req, LoginSession lsess)
        throws Exception;
	
    abstract public 
    LoginSession verifyCCookie (String cookie, final HttpServletRequest req)
        throws Exception;
}

// EOF