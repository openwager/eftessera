package com.tessera.intercept.servlet;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tessera.intercept.*;


public class MapStringExpressionInterceptor 
	extends MappedInterceptorSupport
{

	public MapStringExpressionInterceptor (Map<String, String> props)
    {
	    super (props);
    }
	
	interface PROP
    {
        public static final String PARAM = "param";
    }
	
	private JexlStringExpression paramExpr;

    @Override
    public
    void init ()
        throws Exception
    {
    	super.init (); 
        paramExpr = new JexlStringExpression (require (PROP.PARAM));
        return;
    }

    public String map (HttpServletRequest req, HttpServletResponse res)
        throws Exception
    {
	    return paramExpr.evaluate (req);
    }
}

// EOF
