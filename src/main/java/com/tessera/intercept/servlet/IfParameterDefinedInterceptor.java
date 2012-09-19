package com.tessera.intercept.servlet;

import java.util.*;

import javax.servlet.http.*;

/**
 * TODO: Comment goes here.
 *
 * @author Lee Crawford (crawford@twofish.com)
 * @copyright Copyright (c) 2006-2009, Twofish, Inc. 
 */

public class IfParameterDefinedInterceptor
    extends ParameterInterceptor
{
    public
    IfParameterDefinedInterceptor (final Map<String, String> props)
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
    	return; 
    }

    protected String param;

    @Override
    public
    boolean evaluate (final HttpServletRequest req, final HttpServletResponse res)
    {
        return req.getParameter (param) != null;
    }
}

// EOF
