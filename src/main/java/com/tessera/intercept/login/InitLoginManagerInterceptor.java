package com.tessera.intercept.login;

import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.util.*;
import com.tessera.intercept.*;

/**
 * 
 * @author crawford
 *
 */

public class InitLoginManagerInterceptor
	extends ClassInterceptorSupport
{
	public
	InitLoginManagerInterceptor (final Map<String, String> props)
	{
		super (props);
		 return; 
	}
	
    public Alteration intercept (HttpServletRequest req, HttpServletResponse res, DispatchContext dc)
        throws Exception
    {
		final LoginManager<?> lm = (LoginManager<?>) createObject (req); 
		if (lm == null) { 
			throw new Exception ("No object created."); 
		}
		final ServletContext sc = dc.getDispatcher ().getServletContext (); 
		LoginManagerUtil.setLoginManager (sc, lm); 
	    return NO_ALTERATION;
    }
}

// EOF