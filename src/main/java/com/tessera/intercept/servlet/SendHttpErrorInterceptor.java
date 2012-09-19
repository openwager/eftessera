package com.tessera.intercept.servlet;

import java.util.*;

import javax.servlet.http.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.*;

/**
 * 
 * @author crawford
 *
 */

public class SendHttpErrorInterceptor
    extends InterceptorSupport
{
	public
	SendHttpErrorInterceptor (Map<String, String> props)
	{
		super (props);
		return; 
	}
	
	interface PROP {
		public static final String CODE = "code";
		public static final String MESSAGE = "message";
	}
	
	private int errorCode;
	private JexlExpression<String> messageExpr = null;

	public
	void init () 
		throws Exception 
	{
		if (getProperty(PROP.MESSAGE) != null) {
			messageExpr = new JexlExpression<String> (String.class, getProperty(PROP.MESSAGE));
		}
		try {
			errorCode = Integer.parseInt (require(PROP.CODE));
		}
		catch (NumberFormatException nf_e) {
			throw new ConfigurationException("Invalid error code specified in interceptor configuration.");
		}
	}
	
	public
	Alteration intercept (HttpServletRequest req, HttpServletResponse res, DispatchContext dc)
	    throws Exception
	{
		if (messageExpr == null) {
			res.sendError (errorCode);
		} else {
			final String message = messageExpr.evaluate (req); 
			res.sendError (errorCode, message);
		}
		return ABORT;
	}
}

// EOF
