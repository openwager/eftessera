package com.tessera.intercept.servlet;

import java.util.*;

import javax.servlet.http.*;

import com.tessera.intercept.*;

/**
 * 
 * @author crawford
 *
 */

public class MapMethodInterceptor
	extends MappedInterceptorSupport
{
	public
	MapMethodInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 
	}
	
    public String map (HttpServletRequest req, HttpServletResponse res)
        throws Exception
    {
		return req.getMethod (); 
    }
}

// EOF
