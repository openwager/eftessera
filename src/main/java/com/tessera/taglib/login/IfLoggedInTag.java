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
public class IfLoggedInTag
	extends BodyTagSupport
{
	public
	IfLoggedInTag ()
	{
		return; 
	}
	
	protected
	boolean showBody ()
	{
		final HttpServletRequest req = (HttpServletRequest) pageContext.getRequest (); 
		final LoginManager<?> lm = LoginManagerUtil.getLoginManager (req); 
		if (lm == null) { 
			return false; 
		} else { 
			return lm.isLoggedIn (req);
		}
	}
	
	public
	int doStartTag ()
		throws JspException
	{
		return showBody () ? EVAL_BODY_INCLUDE : SKIP_BODY;
	}
}

// EOF