package com.tessera.intercept.servlet;

import java.util.*;

import javax.servlet.http.*;

import com.tessera.intercept.*;

/**
 * A {@linkplain MappedInterceptor} that is used to dispatch base on the contents
 * of a specified request parameter. </p>
 *
 * Example: </p>
 *
 * <pre>
 * &lt;interceptor class="com.twofish.ludwig.interceptor.param.MapParameterInterceptor">
 *   &lt;property name="param" value="foo"/>
 *   &lt;case name="a">
 *     ... do something ...
 *   &lt;/case>
 *   &lt;case name="b">
 *     ... do something ...
 *   &lt;/case>
 * &lt;/interceptor>
 * &lt;/pre>
 *
 * @author Lee Crawford (crawford@twofish.com)
 * @copyright Copyright (c) 2006-2009, Twofish, Inc. 
 */

public class MapParameterInterceptor
    extends MappedInterceptorSupport
{
    public
    MapParameterInterceptor (final Map<String, String> props)
    {
        super (props);
        return;
    }

    interface PROP
    {
        public static final String PARAM = "param";
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

    public
    String map (final HttpServletRequest req, final HttpServletResponse res)
    {
        return req.getParameter (param);
    }
}

// EOF