package com.tessera.intercept;

import java.util.*;

import javax.servlet.http.*;


public interface MappedInterceptor
    extends Interceptor
{
    /**
     *
     * @return
     */

    public Set<String> getCases ();

    /**
     *
     * @param name
     * @return
     */

    public
    InterceptorChain getCase (String name);

    /**
     * 
     * @param name
     * @param create
     * @return
     */

    public
    InterceptorChain getCase (String name, boolean create);

    /**
     *
     * @param req
     * @param res
     * @param action
     * @return
     */

    public
    String map (final HttpServletRequest req, final HttpServletResponse res)
    	throws Exception;
}

// EOF 