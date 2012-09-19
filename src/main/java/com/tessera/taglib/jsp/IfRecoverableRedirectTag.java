package com.tessera.taglib.jsp;

import javax.servlet.http.*;
import javax.servlet.jsp.tagext.*;

import com.tessera.intercept.util.*;

/**
 * 
 * @author crawford
 *
 */

@SuppressWarnings("serial")
public class IfRecoverableRedirectTag
	extends BodyTagSupport
{
	public
	IfRecoverableRedirectTag ()
	{
		return; 
	}
	
	protected
	boolean showBody ()
	{
		final HttpServletRequest req = (HttpServletRequest) pageContext.getRequest (); 
		return RecoverableRedirectInterceptor.hasRecoveryContext (req); 
	}

	public
	int doStartTag ()
	{
		return showBody () ? EVAL_BODY_INCLUDE : SKIP_BODY; 
	}
}

// EOF
