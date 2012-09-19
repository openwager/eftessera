package com.tessera.taglib.login;

import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import com.tessera.intercept.login.*;

/**
 * 
 * @author crawford
 *
 */

@SuppressWarnings("serial")
public class GetLoginSessionTag
	extends TagSupport
{
	public
	GetLoginSessionTag ()
	{
		return; 
	}

	protected String var; 
	public String getVar () { return this.var; } 
	public void setVar (final String var) { this.var = var; return; } 

	public
	int doEndTag ()
		throws JspException
	{
		final HttpServletRequest req = (HttpServletRequest) pageContext.getRequest (); 
		final LoginManager<?> lm = LoginManagerUtil.getLoginManager (req);
		LoginSession ls = null; 
		 
		if (lm == null) {
			pageContext.removeAttribute (var); 
		} else {
			ls = lm.getLoginSession (req);
			pageContext.setAttribute (var, ls); 			
		}
		return EVAL_PAGE; 
	}
}

// EOF