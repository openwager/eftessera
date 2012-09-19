package com.tessera.intercept.servlet;

import java.util.*;

import javax.servlet.http.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.*;

public class SetResponseHeaderInterceptor
    extends InterceptorSupport
{
    public
    SetResponseHeaderInterceptor (final Map<String, String> props)
    {
        super (props); 
        return;
    }

    /**
     * @see Interceptor#intercept(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.twofish.ludwig.action.Action)
     */

    public
    Alteration intercept (final HttpServletRequest req, final HttpServletResponse res, DispatchContext dc)
    {
    	for (final String header : props.keySet ()) { 
    		res.setHeader (header, props.get (header)); 
    	}
    	return NO_ALTERATION;
    }
}

// EOF