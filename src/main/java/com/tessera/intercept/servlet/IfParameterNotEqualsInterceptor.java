package com.tessera.intercept.servlet;

import java.util.*;

import javax.servlet.http.*;

/**
 * TODO: Comment goes here.
 *
 * @author Lee Crawford (crawford@twofish.com)
 * @copyright Copyright (c) 2006-2009, Twofish, Inc. 
 */

public class IfParameterNotEqualsInterceptor
    extends IfParameterEqualsInterceptor
{
    public
    IfParameterNotEqualsInterceptor (final Map<String, String> props)
    {
        super (props);
        return;
    }

    @Override
    public
    boolean evaluate (final HttpServletRequest req, final HttpServletResponse res)
    {
        return ! super.evaluate (req, res);
    }
}

// EOF
