package com.tessera.intercept.util;

import java.lang.reflect.*;
import java.util.*;

import javax.servlet.http.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.*;

/**
 * 
 * @author crawford
 *
 */

public class ThrowThrowableInterceptor
	extends InterceptorSupport
{
	public
	ThrowThrowableInterceptor (final Map<String, String> props)
	{
	    super (props);
	    return;
	}
	
	interface PROP
	{
	    public static final String TYPE = "type";
	    public static final String MESSAGE = "message";
	}

	@Override
    public
	void init ()
	    throws Exception
	{
		super.init (); 
	    final String type = require (PROP.TYPE);
	    message = new JexlStringExpression (require (PROP.MESSAGE));
	    final Class<?> clazz = Class.forName (type);
	    if (! Exception.class.isAssignableFrom (clazz)) {
	        throw new ConfigurationException ("Not an Exception: " + type);
	    }
	    cons = clazz.getConstructor (String.class);
	    return;
	}

	protected JexlStringExpression message; 
	protected Constructor<?> cons = null;
	
	public
	Alteration intercept (final HttpServletRequest req, final HttpServletResponse res, DispatchContext dc)
	    throws Exception
	{
		final String text = message.evaluate (req); 
	    throw (Exception) cons.newInstance (text);
	}
}

//EOF