package com.tessera.intercept.breadcrumb;

import java.util.*;

import javax.servlet.http.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.*;

/**
 *
 * @author Lee Crawford (crawford@etherfirma.com)
 */

public class GetBreadcrumbsInterceptor
extends InterceptorSupport
{
	public GetBreadcrumbsInterceptor (Map<String, String> props)
	{
		super (props);
		return; 
	}


	interface DEFAULT
	{
		public String ATTR = "breadcrumbs"; 
	}
	
	public 
	Alteration intercept (HttpServletRequest req, HttpServletResponse res, DispatchContext dc)
		throws Exception 
	{
		final List<BreadcrumbEntry> bcs = BreadcrumbUtil.getBreadcrumbs (req, false); 
		req.setAttribute (getProperty (PROP.ATTR, DEFAULT.ATTR), bcs);
		return NO_ALTERATION; 
	}
}

// EOF