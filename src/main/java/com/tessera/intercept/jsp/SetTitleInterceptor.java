package com.tessera.intercept.jsp;

import java.util.*;

import javax.servlet.http.*;

import org.apache.log4j.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.*;

/**
 * 
 * @author crawford
 *
 */

public class SetTitleInterceptor
	extends InterceptorSupport
{
	protected static final Logger logger = Logger.getLogger (SetTitleInterceptor.class.getName ());
	
	public
	SetTitleInterceptor (final Map<String, String> props)
	{
	    super (props);
	    return;
	}
	
	/**
	 * @see com.twofish.ludwig.intercept.Interceptor#init()
	 */
	
	@Override
	public
	void init ()
	    throws Exception
	{
		super.init (); 
	    expr = new JexlStringExpression (require (PROP.TITLE));
	    return;
	}
	
	public interface PROP
	{
	    public static final String TITLE = "title";
	}
	
	public interface ATTR
	{
	    public static final String TITLE = Dispatcher.ATTR.PREFIX + "title";
	}
	
	protected JexlStringExpression expr;
	
	public
	Alteration intercept (final HttpServletRequest req, final HttpServletResponse res, DispatchContext dc)
	    throws Exception
	{
		final String title = expr.evaluate (req); 
	    req.setAttribute (ATTR.TITLE, title);
	    return NO_ALTERATION;
	}
}

//EOF