package com.tessera.intercept.login;

import java.util.*;

import javax.servlet.http.*;

import org.apache.log4j.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.*;

/**
 * 
 * @author crawford
 *
 */

public class GetLoginSessionInterceptor
	extends InterceptorSupport
{
	private static final Logger logger = Logger.getLogger (GetLoginSessionInterceptor.class); 
	
	public
	GetLoginSessionInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 
	}
	
	interface DEFAULT 
	{
		public static final String ATTR = "loginsess";
	}

	protected String attr; 
	
	public
	void init()
		throws ConfigurationException 
	{	
		attr = getProperty (PROP.ATTR, DEFAULT.ATTR);
		return; 
	}

    public Alteration intercept (HttpServletRequest req, HttpServletResponse res, DispatchContext dc)
        throws Exception
    {
		final LoginManager<?> lm = LoginManagerUtil.getLoginManager (req); 
		if (lm == null) { 
			logger.warn ("No LoginManager registered."); 
		} else { 
			final LoginSession ls = lm.getLoginSession (req); 
			if (ls != null) { 
				req.setAttribute (attr, ls);  
			}
		}
		return NO_ALTERATION;
    }
}

// EOF
