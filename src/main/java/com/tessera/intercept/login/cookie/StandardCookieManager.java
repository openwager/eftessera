package com.tessera.intercept.login.cookie;

import java.security.*;
import java.util.*;

import javax.servlet.http.*;

import org.apache.log4j.*;

import com.tessera.intercept.login.*;
import com.tessera.site.login.*;
import com.weaselworks.util.*;

/**
 * A concrete implementation of the {@linkplain CookieManager} that fully implements 
 * generation of B and C cookies and takes care of the signing, etc. 
 * 
 * @author crawford
 *
 */

abstract public class StandardCookieManager
	extends CookieManager
{
	protected static final Logger logger = Logger.getLogger (StandardCookieManager.class); 
	
	protected
	StandardCookieManager (final Map<String, String> props)
	{
		super (props); 
		
		// Set the cookie names
		
		final String prefix = getCookiePrefix (); 
		setACookieName (prefix + CookieManager.DEFAULT.A_COOKIE); 
		setBCookieName (prefix + CookieManager.DEFAULT.B_COOKIE); 
		setCCookieName (prefix + CookieManager.DEFAULT.C_COOKIE); 
		
		return; 
	}
	
	/**
	 * Used to retrieve the prefix for the various cookies. Allows us to have separate
	 * cookie infrastructure for multiple webapps on the same host (important primarily 
	 * for development environments). 
	 * 
	 * @return
	 */
	
	abstract protected
	String getCookiePrefix ();
	
	/**
	 * Field names for the elements that comprise the B and C cookies. 
	 */
	
	interface FIELD
	{
		interface B
		{
			public String ID = "i"; 
			public String USERNAME = "u"; 
			public String VERSION = "v"; 
		}
		
		interface C
		{
			public String ID = "i"; 
			public String VERSION = "v"; 
			public String SIGNATURE = "s"; 					
		}
	}

	/**
	 * The current version for B cookies. 
	 */
	
	private static final int B_COOKIE_VERSION = 0; 
	
	/**
	 * The current version for C cookies. 
	 */
	
	private static final int C_COOKIE_VERSION = 0; 
	
	public
	String generateBCookie (HttpServletRequest res, LoginSession lsess)
		throws Exception 
	{
		final StringBuffer buf = new StringBuffer ();
		buf.append ("i="); 
		buf.append (lsess.getId ()); 
		buf.append (',');
		buf.append ("u="); 
		buf.append (lsess.getUsername ());
		buf.append (",v=");
		buf.append (B_COOKIE_VERSION);
		return buf.toString (); 
	}

	public 
	String generateCCookie (HttpServletRequest req, LoginSession lsess)
		throws Exception 
	{
		final Object userId = lsess.getId ();
		final StringBuffer buf = new StringBuffer (); 
		buf.append ("v=");
		buf.append (C_COOKIE_VERSION);
		buf.append (",i="); 
		buf.append (userId.toString ());
		buf.append (",s="); 
		final String signature = generateSignature (C_COOKIE_VERSION, lsess);  
		buf.append (signature); 
		return buf.toString (); 
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	
	abstract protected
	LoginSession lookupUserById (final String userId)
		throws Exception; 
	
    public
	LoginSession verifyCCookie (String value, HttpServletRequest req)
		throws Exception 
	{
		// Break apart the cookie and see what version we're dealing with 
		
		final Map<String, String> map = SiteCookieUtil.parseCookie (value);
		final String vstr = map.get (FIELD.C.VERSION); 
		if (vstr == null) { 
			logger.warn ("Malformed C cookie: " + value);
			return null; 
		}
		final int version = Integer.parseInt (vstr); 
		
		// Lookup the specified user so we can verify the signature
		
		final String istr = map.get (FIELD.C.ID); 
		if (istr == null) {
			logger.warn ("Malformed C cookie: " + value);
			return null; 
		}
		
		// Lookup the user so we can use additional user fields to 
		// incorporate into the signature 

		final LoginSession lsess = lookupUserById (istr); 
		if (lsess == null) { 
			logger.warn ("Found unrecognized user in C cookie: " + istr); 
			return null; 			
		}
		
		// Generate a new signature and compare to the one on the cookie
		
		final String signature = map.get (FIELD.C.SIGNATURE);
		if (signature == null) { 
			logger.warn ("Malformed C cookie: " + value);
			return null; 
		}
		final String verify = generateSignature (version, lsess);
		if (! signature.equals (verify)) {
			logger.warn ("Rejected C cookie: " + value);
			return null; 
		}
		
		// Return the login session
		
		return lsess;   
	}
	
	/**
	 * 
	 * @return
	 */
	
	abstract protected
	String [] getSaltValues ();
	
	/**
	 * TODO: getEnvironment should be abstract and protected, and each application should implement their "own" version of it
	 * @return
	 */
	
	public
	String getEnvironment ()
		throws Exception
	{
		return null;
	}
	
	/**
	 * 
	 * @param slsess
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	
	protected 
	String generateSignature (final int version, final LoginSession lsess) 
		throws Exception 
	{
		final MessageDigest m = MessageDigest.getInstance ("MD5");
		final StringBuffer buf = new StringBuffer (); 

		final String [] salts = getSaltValues (); 
		final String salt = salts [version]; 
		final String env = getEnvironment ();
		
		// Add the appropriate fields to the hash depending on the signature 
		// version we're generating. 
		
		switch (version) { 
			case 0: 
				buf.append (lsess.getId ().toString ());
				buf.append (lsess.getUsername ());
				buf.append (env);
				
				// TODO: Add something else related to the installation enviroment
				buf.append (salt);
				break; 
				
			default:
				throw new Exception ("Invalid version: " + version); 
		}
		
		// Encode the hash for storage in the cookie
		
		final String str = buf.toString (); 
		final byte [] digest = m.digest (str.getBytes ());
		return HexUtil.encodeBytes (digest); 
	}
}

// EOF