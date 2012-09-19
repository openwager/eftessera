package com.tessera.intercept;

import javax.servlet.http.*;

/**
 * 
 * @author crawford
 *
 */

public interface PredicateInterceptor
    extends MappedInterceptor
{
	/**
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	
    public
    boolean evaluate (HttpServletRequest req, HttpServletResponse res)
    	throws Exception;
}

// EOF
