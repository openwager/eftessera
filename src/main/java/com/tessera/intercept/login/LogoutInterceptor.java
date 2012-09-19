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

public class LogoutInterceptor
	extends InterceptorSupport
{
	private static final Logger logger = Logger.getLogger (LogoutInterceptor.class); 
	
	public
	LogoutInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 
	}
	
    public Alteration intercept (HttpServletRequest req, HttpServletResponse res, DispatchContext dc)
        throws Exception
    {
		final LoginManager<?> lm = LoginManagerUtil.getLoginManager (req); 
		if (lm == null) { 
			logger.warn ("No LoginManager registered."); 
		} else { 
			lm.logout (req); 
		}
		
		return NO_ALTERATION; 
    }
}

// EOF
