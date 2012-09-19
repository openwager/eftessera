package com.tessera.intercept.breadcrumb;

import java.util.*;

import javax.servlet.http.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.*;
import com.weaselworks.util.*;

/**
 *
 * @author Lee Crawford (crawford@etherfirma.com)
 */

public class AddBreadcrumbInterceptor
	extends InterceptorSupport
{
	public AddBreadcrumbInterceptor (Map<String, String> props)
	{
		super (props);
		return; 
	}

	interface PROP
	{
		public String LABEL = "label"; 
		public String PATH = "path"; 
	}
	
	JexlStringExpression labelExpr; 
	JexlStringExpression pathExpr; 
	
	public 
	void init ()
		throws Exception 
	{
		labelExpr = new JexlStringExpression (require (PROP.LABEL));
		final String val = getProperty (PROP.PATH); 
		if (! StringUtil.isEmpty (val)) {
			pathExpr = new JexlStringExpression (require (PROP.PATH));
		}
		return; 
	}
	
	public 
	Alteration intercept (HttpServletRequest req, HttpServletResponse res, DispatchContext dc)
		throws Exception 
	{
		final String label = labelExpr.evaluate (req); 
		final String path = pathExpr != null ? pathExpr.evaluate (req) : null;  
		BreadcrumbUtil.addBreadcrumb (req, label, path); 
		return NO_ALTERATION; 
	}
}

// EOF