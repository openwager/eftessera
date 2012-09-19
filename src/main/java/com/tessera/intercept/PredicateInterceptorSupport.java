package com.tessera.intercept;

import java.util.*;

import javax.servlet.http.*;

import org.apache.log4j.*;

abstract public class PredicateInterceptorSupport
	extends MappedInterceptorSupport
    	implements PredicateInterceptor
{
	protected static final Logger logger = Logger.getLogger (PredicateInterceptorSupport.class.getName ());
	
	public
	PredicateInterceptorSupport (final Map<String, String> props)
	{
	    super (props);
	    return;
	}
	
	/**
	 * @see MappedInterceptor#map(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.twofish.ludwig.action.Action)
	 */
	
	public final
	String map (final HttpServletRequest req, final HttpServletResponse res)
		throws Exception
	{
	    final boolean val = evaluate (req, res);
	    return Boolean.toString (val);
	}
	
	/**
	 *
	 * @param req
	 * @param res
	 * @param action
	 * @return
	 */
	
	abstract public
	boolean evaluate (final HttpServletRequest req, final HttpServletResponse res)
		throws Exception;
}

//EOF
