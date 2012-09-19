package com.tessera.intercept.util;

import java.util.*;

import javax.servlet.http.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.*;

/**
 * 
 * @author crawford
 *
 */

public class LoadMappingsInterceptor
	extends InterceptorSupport
{
	public LoadMappingsInterceptor (Map<String, String> props)
	{
		super (props);
		return; 
	}

	protected JexlStringExpression pathExpr; 

	public
	void init ()
		throws Exception
	{
		pathExpr = new JexlStringExpression (require (PROP.PATH)); 
		return; 
	}
	
	@Override
	public Alteration intercept (HttpServletRequest req, HttpServletResponse res, DispatchContext dc)
		throws Exception 
	{
		final String path = pathExpr.evaluate (req); 
		final Dispatcher d = dc.getDispatcher (); 
		d.loadMappings (path); 
		return null;
	}
}


// EOF