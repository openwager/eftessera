package com.tessera.taglib.breadcrumb;

import java.util.*;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import com.tessera.intercept.breadcrumb.*;

/**
 *
 * @author Lee Crawford (crawford@etherfirma.com)
 */

@SuppressWarnings ("serial")
public class GetBreadcrumbsTag
	extends TagSupport
{
	public
	GetBreadcrumbsTag ()
	{
		return; 
	}

    protected String var;
    public String getVar () { return var; }
    public void setVar (final String var) { this.var = var; return; }

    public
    int doEndTag ()
    	throws JspException
	{
    	final List<BreadcrumbEntry> bcs = BreadcrumbUtil.getBreadcrumbs (pageContext.getRequest (), false); 
		pageContext.setAttribute (getVar (), bcs); 
		return EVAL_PAGE; 
	}
}


// EOF