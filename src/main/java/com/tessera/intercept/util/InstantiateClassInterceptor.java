package com.tessera.intercept.util;

import java.util.*;

import javax.servlet.http.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.*;
import com.tessera.intercept.servlet.*;

/**
 * 
 * @author crawford
 *
 */

public class InstantiateClassInterceptor
	extends ClassInterceptorSupport
{
	public
	InstantiateClassInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 
	}

	interface PROP
		extends ClassInterceptorSupport.PROP
	{
		public String ATTR = "attr"; 
		public String CONTEXT = "context";
	}

	@Override
	public
	void init ()
		throws Exception
	{
		super.init (); 
		attrExpr = new JexlStringExpression (require (PROP.ATTR));
		context = AttributeContext.valueOf (require (PROP.CONTEXT));
		return;	
	}
	
    public 
    Alteration intercept (HttpServletRequest req, HttpServletResponse res, DispatchContext dc)
        throws Exception
    {	
		final String attr = attrExpr.evaluate (req);
		final Object value = createObject (req); 
		if (req == null) { 
			context.setAttribute (dc.getDispatcher ().getServletContext (), attr, value); 
		} else {
			context.setAttribute (req, attr, value); 			
		}
		return NO_ALTERATION; 
    }
}

// EOF