package com.tessera.intercept.util;

import java.io.*;
import java.util.*;

import javax.servlet.http.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.*;

/**
 * TODO: Comment goes here. 
 * 
 * @author Lee Crawford (lee.crawford@gmail.com) 
 * @copyright Copyright (c) 2009, Weaselworks, Inc. 
 */

abstract public class SystemInterceptor
    extends InterceptorSupport
{
    public
    SystemInterceptor (final Map<String, String> props)
    {
        super (props);
        return;
    }

    interface PROP
    {
        public String EXPRESSION = "expression";
    }

    /**
     * @see com.twofish.ludwig.intercept.Interceptor#init()
     */

    @Override
	public
    void init ()
        throws Exception
    {
    	super.init (); 
    	expr = new JexlObjectExpression (require (PROP.EXPRESSION));
        return;
    }
    
    protected JexlExpression<Object> expr; 

    /**
     * @see Interceptor#intercept(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.twofish.ludwig.action.Action)
     */

    public
    Alteration intercept (final HttpServletRequest req, final HttpServletResponse res, DispatchContext dc)
    	throws Exception
    {
    	try { 
    		final Object output = expr.evaluate (Object.class, req); 
        	getOutputStream ().println (output); 
    	}
    	catch (final Throwable t) { 
    		t.printStackTrace ();
    	}
        return NO_ALTERATION;
    }
    
    /**
     * To be implemented by subclasses which are responsible for retrieving the 
     * output stream that we'll use to emit the output message to. 
     * 
     * @return the appropriate output stream
     */
    
    abstract 
    PrintStream getOutputStream ();
}

// EOF