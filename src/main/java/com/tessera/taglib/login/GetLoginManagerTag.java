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
public class GetLoginManagerTag
	extends TagSupport
{
	public
	GetLoginManagerTag ()
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
		if (lm == null) {
			pageContext.removeAttribute (var); 
		} else { 
			pageContext.setAttribute (var, lm); 			
		}
		return EVAL_PAGE; 
	}
}

// EOF