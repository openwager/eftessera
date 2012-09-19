package com.tessera.intercept;

import java.util.*;

import javax.servlet.http.*;

/**
 * 
 * @author crawford
 *
 */

public class IfInterceptor
	extends PredicateInterceptorSupport
{
	public
	IfInterceptor (final Map<String, String> props)
	{
		super (props); 
		return;
	}
	
	protected JexlExpression<Boolean> expr; 
	
	@Override
	public
	void init ()
		throws Exception
	{
		super.init (); 
		final String val = require (PROP.EXPRESSION);
		expr = new JexlExpression<Boolean> (Boolean.class, val);  
		return; 
	}
	
    public
    boolean evaluate (HttpServletRequest req, HttpServletResponse res)
        throws Exception
    {
    	return expr.evaluate (req); 
    }
}

// EOF