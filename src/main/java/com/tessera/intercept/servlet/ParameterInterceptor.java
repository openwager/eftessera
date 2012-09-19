package com.tessera.intercept.servlet;

import java.util.*;

import com.tessera.intercept.*;

/**
 * TODO: Comment goes here.
 *
 * @author Lee Crawford (crawford@twofish.com)
 * @copyright Copyright (c) 2006-2009, Twofish, Inc. 
 */

abstract class ParameterInterceptor
    extends PredicateInterceptorSupport
{
    protected
    ParameterInterceptor (final Map<String, String> props)
    {
        super (props);
        return;
    }

    interface PROP
    {
        public String PARAM = "param";
        public String VALUE = "value";
    }
}

// EOF
