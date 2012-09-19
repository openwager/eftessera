package com.tessera.intercept;

import java.util.*;

import javax.servlet.http.*;

/**
 * 
 * @author crawford
 *
 */

public class IfNotInterceptor
	extends IfInterceptor
{
	public
	IfNotInterceptor (final Map<String, String> props)
	{
		super (props); 
		return;  
	}

	@Override
    public
    boolean evaluate (HttpServletRequest req, HttpServletResponse res)
        throws Exception
    {
    	return ! super.evaluate (req, res);  
    }
}

// EOF
