package com.tessera.intercept.jsp;

import java.util.*;

import javax.servlet.http.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.*;

/**
 * 
 * @author crawford
 *
 */

public class AddDescriptionInterceptor
	extends InterceptorSupport
{
	public
	AddDescriptionInterceptor (final Map<String, String> props)
	{
	    super (props);
	    return;
	}
	
	interface PROP
	{
	    public static final String CONTENT = "content";
	}
		
	protected JexlStringExpression contentE; 
	
	@Override
    public
	void init ()
	    throws Exception
	{
		super.init (); 
	    contentE = new JexlStringExpression (require (PROP.CONTENT));
	    return;
	}
	
	public
	Alteration intercept (final HttpServletRequest req, final HttpServletResponse res, DispatchContext dc)
	    throws Exception
	{
		final String content= contentE.evaluate (req); 
	    req.setAttribute (JspWidgetUtil.ATTR.DESCRIPTION, content);
	    return NO_ALTERATION;
	}
}

//EOF
