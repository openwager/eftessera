package com.tessera.taglib.breadcrumb;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import com.tessera.intercept.breadcrumb.*;

/**
 *
 * @author Lee Crawford (crawford@etherfirma.com)
 */

@SuppressWarnings ("serial")
public class AddBreadcrumbTag
	extends TagSupport
{
	public
	AddBreadcrumbTag ()
	{
		return; 
	}

	protected String label; 
	public String getLabel () { return this.label; } 
	public void setLabel (final String label) { this.label = label; return; } 
	
	protected String path; 
	public String getPath () { return this.path; } 
	public void setPath (final String path) { this.path = path; return; } 

	public
    int doEndTag ()
    	throws JspException
	{
    	BreadcrumbUtil.addBreadcrumb (pageContext.getRequest (), getLabel (), getPath ()); 
		return EVAL_PAGE; 
	}
}

// EOF