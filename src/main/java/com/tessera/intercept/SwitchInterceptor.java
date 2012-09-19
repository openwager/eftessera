package com.tessera.intercept;

import java.util.*;

import javax.servlet.http.*;

/**
 * 
 * @author crawford
 *
 */

public class SwitchInterceptor
	extends MappedInterceptorSupport
{
	public
	SwitchInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 
	}
	
	interface PROP
	{
		public String EXPRESSION = "expression"; 
	}
	
	protected JexlStringExpression expr; 
	
	@Override
	public
	void init ()
		throws Exception
	{
		super.init (); 
		final String val = require (PROP.EXPRESSION);
		expr = new JexlStringExpression (val);  
		return; 
	}
	
    public
    String map (final HttpServletRequest req, final HttpServletResponse res)
        throws Exception
    {
    	return expr.evaluate (req);     	
    }	
}

// EOF