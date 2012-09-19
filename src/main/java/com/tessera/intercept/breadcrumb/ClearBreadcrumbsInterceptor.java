package com.tessera.intercept.breadcrumb;

import java.util.*;

import javax.servlet.http.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.*;

/**
 *
 * @author Lee Crawford (crawford@etherfirma.com)
 */

public class ClearBreadcrumbsInterceptor
	extends InterceptorSupport
{
	public ClearBreadcrumbsInterceptor (Map<String, String> props)
	{
		super (props);
		return; 
	}

	public 
	Alteration intercept (HttpServletRequest req, HttpServletResponse res, DispatchContext dc)
		throws Exception 
	{
		BreadcrumbUtil.clearBreadcrumbs (req);  
		return NO_ALTERATION; 
	}
}

// EOF