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

public class SetTemplateInterceptor
	extends InterceptorSupport
{
	public
	SetTemplateInterceptor (final Map<String, String> props)
	{
	    super (props);
	    return;
	}
	
	public interface ATTR
	{
	    public static final String TEMPLATE = Dispatcher.ATTR.PREFIX + "template";
	}
	
	protected JexlStringExpression templateE; 
	
	@Override
    public
	void init ()
	    throws Exception
	{
    	super.init (); 
	    templateE = new JexlStringExpression (require (PROP.TEMPLATE));
	    return;
	}
	
	interface PROP
	{
	    public static final String TEMPLATE = "template";
	}
	
	protected String template; 
	
	public
	Alteration intercept (final HttpServletRequest req, final HttpServletResponse res, DispatchContext dc)
		throws Exception
	{
		final String template = templateE.evaluate (req); 
	    req.setAttribute (ATTR.TEMPLATE, template);
	    return NO_ALTERATION;
	}
}

//EOF
