package com.tessera.intercept.login;

import java.util.*;

import javax.servlet.http.*;

/**
 * 
 * @author crawford
 *
 */

public class IfNotLoggedInInterceptor
	extends IfLoggedInInterceptor
{
	public
	IfNotLoggedInInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 	
	}
	
	@Override
    public boolean evaluate (final HttpServletRequest req, final HttpServletResponse res)
        throws Exception
    {
		return ! super.evaluate (req, res); 
    }
}

// EOF
