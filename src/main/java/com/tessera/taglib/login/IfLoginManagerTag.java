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
public class IfLoginManagerTag
	extends BodyTagSupport
{
	public
	IfLoginManagerTag ()
	{
		return; 
	}
	
	protected
	boolean showBody ()
	{
		final HttpServletRequest req = (HttpServletRequest) pageContext.getRequest (); 
		final LoginManager<?> lm = LoginManagerUtil.getLoginManager (req);
		return lm != null; 
	}
	
	public
	int doStartTag ()
		throws JspException
	{
		return showBody () ? EVAL_BODY_INCLUDE : SKIP_BODY; 
	}
}

// EOF