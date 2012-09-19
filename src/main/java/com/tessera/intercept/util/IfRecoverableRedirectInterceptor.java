package com.tessera.intercept.util;

import java.util.*;

import javax.servlet.http.*;

import com.tessera.intercept.*;

/**
 * 
 * @author crawford
 *
 */

public class IfRecoverableRedirectInterceptor
	extends PredicateInterceptorSupport
{
	public 
	IfRecoverableRedirectInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 
	}

	@Override
    public
    boolean evaluate (HttpServletRequest req, HttpServletResponse res)
        throws Exception
    {
		return RecoverableRedirectInterceptor.hasRecoveryContext (req);  
    }	
}

// EOF