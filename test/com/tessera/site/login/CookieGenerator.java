package com.tessera.site.login;

import java.security.*;
import java.util.*;

import javax.servlet.http.*;

import org.apache.log4j.*;

import com.tessera.intercept.login.*;
import com.tessera.intercept.login.cookie.*;
import com.weaselworks.util.*;

/**
 * 
 * @author crawford
 *
 */

public class CookieGenerator
	implements BCookieGenerator, CCookieGenerator
{
	private static final Logger logger = Logger.getLogger (CookieGenerator.class); 
	
	public
	CookieGenerator ()
	{
		return; 
	}

	/**
	 * Interface containing symbols for the internal fields stored in each of the 
	 * B and C cookies. 
	 */
	
	public interface FIELD 
	{ 
		public interface B
		{
			public String ID = "i"; 
			public String USERNAME = "u"; 
			public String VERSION = "v"; 
		}
		
		public interface C 
		{
			public String ID = "i"; 
			public String VERSION = "v"; 
			public String SIGNATURE = "s"; 
		}
	}
	
	/**
	 * 
	 */
	
    public String generateBCookie (final HttpServletRequest res, final LoginSession ls)
		throws Exception
    {
		final StringBuffer buf = new StringBuffer ();
		buf.append ("i="); 
		buf.append (ls.getId ()); 
		buf.append (',');
		buf.append ("u="); 
		buf.append (ls.getUsername ());
		buf.append (",v=");
		buf.append (B_COOKIE_VERSION);
		return buf.toString (); 
    }

	/**
	 * 
	 */
	
    public
    String generateCCookie (final HttpServletRequest req, final LoginSession ls)
		throws Exception
    {
		final StringBuffer buf = new StringBuffer (); 
		buf.append ("v=");
		buf.append (C_COOKIE_VERSION);
		buf.append (",i="); 
		buf.append (ls.getId ().toString ());
		buf.append (",s="); 
		buf.append (generateSignature (C_COOKIE_VERSION, ls.getId ())); 
		return buf.toString (); 
    }
	
	/**
	 * The current version for B cookies. 
	 */
	
	private static final int B_COOKIE_VERSION = 0; 
	
	/**
	 * The current version for C cookies. 
	 */
	
	private static final int C_COOKIE_VERSION = 0; 
	
//	/**
//	 * The current salt version used to sign cookies. 
//	 */
//	
//	private static final int SALT_VERSION = 0; 
	
	/**
	 * The table of salt values used to sign the C cookies for each version of 
	 * the C cookie that exists.  
	 */
	
	private static final String SALT_VALUES [] = { 
		"h1pp1tyH0P" // C cookie, version=0
	};
	
	/**
	 * 
	 * @param slsess
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	
	protected 
	String generateSignature (final int version, final Object userId) 
		throws Exception 
	{
		final MessageDigest m = MessageDigest.getInstance ("MD5");
		final StringBuffer buf = new StringBuffer (); 

		// Add the appropriate fields to the hash depending on the signature 
		// version we're generating. 
		
		switch (version) { 
			case 0: 
				buf.append (userId.toString ());
				// TODO: Incorporate some other user-specific information
				buf.append (SALT_VALUES [version]);
				break; 
				
			default:
				throw new Exception ("Invalid version: " + version); 
		}
		
		// Encode the hash for storage in the cookie
		
		final String str = buf.toString (); 
		final byte [] digest = m.digest (str.getBytes ());
		return HexUtil.encodeBytes (digest); 
	}	

	/**
	 * 
	 */
	
    public
    LoginSession verifyCCookie (final String value, final HttpServletRequest req)
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
		final long id = Long.parseLong (istr); 
		
		// Lookup the user so we can use additional user fields to 
		// incorporate into the signature 
		
		// TODO: Unimplemented
		
		// Generate a new signature and compare to the one on the cookie
		
		final String signature = map.get (FIELD.C.SIGNATURE);
		if (signature == null) { 
			logger.warn ("Malformed C cookie: " + value);
			return null; 
		}
		final String verify = generateSignature (version, id);
		if (! signature.equals (verify)) {
			logger.warn ("Rejected C cookie: " + value);
			return null; 
		}
		
		// Create a new login session 
		
		final LoginSession lsess = new LoginSession (id, "unknown"); 
		return lsess;
    }
}

// EOF
