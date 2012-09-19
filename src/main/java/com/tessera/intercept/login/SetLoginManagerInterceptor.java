package com.tessera.intercept.login;

import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.*;
import com.tessera.intercept.servlet.*;

/**
 * 
 * @author crawford
 *
 */

public class SetLoginManagerInterceptor
	extends InterceptorSupport
{
	public
	SetLoginManagerInterceptor (Map<String, String> props)
	{
		super (props);
		return; 
	}

	interface PROP
		extends InterceptorSupport.PROP
	{
		public String ATTR = "attr"; 
		public String CONTEXT = "context";
	}

	protected JexlStringExpression attrExpr; 
	protected AttributeContext context; 
	
	public
	void init ()
		throws Exception
	{
		context = AttributeContext.valueOf (require (PROP.CONTEXT)); 
		attrExpr = new JexlStringExpression (require (PROP.ATTR)) ;
		return ;
	}
	
	@Override
	public Alteration intercept (HttpServletRequest req, HttpServletResponse res, DispatchContext dc)
		throws Exception 
	{
		// Get the object they'd like to install
		
		final String attr = attrExpr.evaluate (req); 
		final Object obj = context.getAttribute (req, attr);
		if (obj == null) { 
			throw new Exception ("No object found in " + context.name () + ":" + attr); 
		} else if (! (obj instanceof LoginManager<?>)) { 
			throw new Exception ("Object not a LoginManager: " + obj.getClass().getName ()); 
		}
		
		final LoginManager<?> lm = (LoginManager<?>) obj;
		final ServletContext sc = dc.getDispatcher ().getServletContext (); 
		LoginManagerUtil.setLoginManager (sc, lm); 

		return NO_ALTERATION; 
	}
}

// EOF