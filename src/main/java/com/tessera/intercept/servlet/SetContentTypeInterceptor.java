package com.tessera.intercept.servlet;

import java.util.*;

import javax.servlet.http.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.*;

/**
 * TODO: Comment goes here.
 *
 * @author Lee Crawford (crawford@twofish.com)
 * @copyright Copyright (c) 2006-2009, Twofish, Inc. 
 */

public class SetContentTypeInterceptor
    extends InterceptorSupport
{
    public
    SetContentTypeInterceptor (final Map<String, String> props)
    {
        super (props);
        return;
    }

    @Override
	public
    void init ()
        throws Exception
    {
        require (PROP.CONTENT_TYPE);
        return;
    }

    interface PROP
    {
        public static final String CONTENT_TYPE = "contentType";
    }

    public
    Alteration intercept (final HttpServletRequest req, final HttpServletResponse res, DispatchContext dc)
        throws Exception
    {
        res.setContentType (getProperty (PROP.CONTENT_TYPE));
        return NO_ALTERATION; 
    }
}


// EOF
