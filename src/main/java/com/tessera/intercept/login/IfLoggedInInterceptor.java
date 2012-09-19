package com.tessera.intercept.login;

import java.util.*;

import javax.servlet.http.*;

import org.apache.log4j.*;

import com.tessera.intercept.*;

/**
 * 
 * @author crawford
 *
 */

public class IfLoggedInInterceptor
	extends PredicateInterceptorSupport
{
	private static final Logger logger = Logger.getLogger (IfLoggedInInterceptor.class); 
	
	public
	IfLoggedInInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 
	}

	@Override
    public boolean evaluate (final HttpServletRequest req, final HttpServletResponse res)
        throws Exception
    {
		final LoginManager<?> lm = LoginManagerUtil.getLoginManager (req); 
		if (lm == null) {
			logger.warn ("No LoginManager registered."); 
			return false; 
		} else {
			return lm.getLoginSession (req) != null;
		}
		
		// NOT REACHED
    }
}

// EOF