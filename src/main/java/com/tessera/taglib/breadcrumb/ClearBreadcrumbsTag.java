package com.tessera.taglib.breadcrumb;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import com.tessera.intercept.breadcrumb.*;

/**
 *
 * @author Lee Crawford (crawford@etherfirma.com)
 */

@SuppressWarnings ("serial")
public class ClearBreadcrumbsTag
	extends TagSupport
{
	public
	ClearBreadcrumbsTag ()
	{
		return;
	}
	
    public
    int doEndTag ()
    	throws JspException
	{
    	BreadcrumbUtil.clearBreadcrumbs (pageContext.getRequest ()); 
		return EVAL_PAGE; 
	}
}


// EOF