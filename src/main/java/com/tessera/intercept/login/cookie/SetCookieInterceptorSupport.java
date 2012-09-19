package com.tessera.intercept.login.cookie;

import java.util.*;

import javax.servlet.http.*;

import org.apache.log4j.*;

import com.tessera.intercept.*;

/**
 * 
 * @author crawford
 *
 */

// package
abstract class SetCookieInterceptorSupport <GeneratorType> 
	extends InterceptorSupport
{
	protected static final Logger logger = Logger.getLogger (SetCookieInterceptorSupport.class); 
	
	protected
	SetCookieInterceptorSupport (final Map<String, String> props)
	{
		super (props); 
		return; 
	}
	
	protected CookieManager cookieManager; 

	protected
	CookieManager getCookieManager (final HttpServletRequest req)
	{
		if (cookieManager == null) { 
			synchronized (this) { 
				final CookieManager cookieManager = CookieManagerUtil.getCookieManager (req);
				if (cookieManager == null) { 
					logger.error ("CookieManager not found."); 
				} else { 
					this.cookieManager = cookieManager; 
				}
			}
		}
		return cookieManager; 
	}
	
	abstract protected
	String getCookie (final CookieManager cm);
	
	interface PROP
		extends InterceptorSupport.PROP
	{
		public String OVERWRITE = "overwrite"; 
//		public String GENERATOR = "generator";
//		public String DOMAIN = "domain"; 
		public String MAX_AGE = "maxAge"; 
	}

//	protected 
//	String getDefaultGenerator ()
//	{
//		return null; 
//	}
	
	protected boolean overwrite;
	public boolean getOverwrite () { return this.overwrite; } 

//	protected GeneratorType generator; 
//	public GeneratorType getGenerator () { return this.generator; } 

//	protected String domain; 
//	public String getDomain () { return this.domain; } 
		
//	protected String path; 
//	public String getPath () { return this.path; } 
	
	protected int maxAge; 
	public int getMaxAge () { return this.maxAge; } 

//	@SuppressWarnings("unchecked")
    public
	void init ()
		throws Exception
	{	
		super.init ();
		if (hasProperty (PROP.OVERWRITE)) { 
			overwrite = Boolean.parseBoolean (getProperty (PROP.OVERWRITE)); 
		}
//		domain = getProperty (PROP.DOMAIN);
//		path = getProperty (PROP.PATH, "/"); 
		maxAge = Integer.parseInt (require (PROP.MAX_AGE)); 
//		final String className = getProperty (PROP.GENERATOR, getDefaultGenerator ());
//		if (className == null) { 
//            throw new ConfigurationException ("Required property '" + PROP.GENERATOR + "' empty.");
//		}
//		final Object obj = ClassUtil.newInstance (className);
//		generator = (GeneratorType) obj; 
		return; 
	}
}

// EOF