package com.tessera.intercept.servlet;

import java.util.*;

import javax.servlet.http.*;

/**
 * TODO: Comment goes here.
 *
 * @author Lee Crawford (crawford@twofish.com)
 * @copyright Copyright (c) 2006-2009, Twofish, Inc. 
 */

public class IfParameterEqualsInterceptor
    extends ParameterInterceptor
{
    public
    IfParameterEqualsInterceptor (final Map<String, String> props)
    {
        super (props);
        return;
    }

    @Override
	public
    void init ()
        throws Exception
    {
        super.init ();
        param = require (PROP.PARAM); 
        value = require (PROP.VALUE); 
        return;
    }
    
    protected String param; 
    protected String value; 

    @Override
    public
    boolean evaluate (final HttpServletRequest req, final HttpServletResponse res)
    {
    	return value.equals (req.getParameter (param));
    }
}

// EOF
