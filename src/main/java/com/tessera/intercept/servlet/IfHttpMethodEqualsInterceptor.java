package com.tessera.intercept.servlet;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tessera.intercept.*;
import com.weaselworks.util.*;

public class IfHttpMethodEqualsInterceptor
    extends PredicateInterceptorSupport
{

	public IfHttpMethodEqualsInterceptor (Map<String, String> props)
    {
	    super (props);
    }
	
	interface INPUT
	{
		public String METHOD = "method";
	}
	
	public void init () throws ConfigurationException
	{
		require (INPUT.METHOD);
		return; 
	}

	@Override
    public boolean evaluate (HttpServletRequest req, HttpServletResponse res)
        throws Exception
    {
		final String method = getProperty (INPUT.METHOD);
		return Util.isEqual (method.toLowerCase (), req.getMethod ().toLowerCase ());
    }
}

// EOF
