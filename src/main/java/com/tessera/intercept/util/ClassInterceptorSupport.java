package com.tessera.intercept.util;

import java.lang.reflect.*;
import java.util.*;

import javax.servlet.http.*;

import com.tessera.intercept.*;
import com.tessera.intercept.servlet.*;

/**
 * 
 * @author crawford
 *
 */

abstract public class ClassInterceptorSupport
	extends InterceptorSupport
{
	protected
	ClassInterceptorSupport (Map<String, String> props)
	{
		super (props); 
		return; 
	}

	interface PROP 
	{
		public String CLASS = "class"; 
		public String PROPERTIES = "properties"; 
	}
	
	protected JexlStringExpression typeExpr; 
	protected JexlStringExpression attrExpr; 
	protected AttributeContext context;
	protected Map<String, JexlObjectExpression> propExprs; 
	
	public
	void init ()
		throws Exception
	{
		super.init (); 
		typeExpr = new JexlStringExpression (require (PROP.CLASS)); 
		propExprs = new HashMap<String, JexlObjectExpression> ();
		// TODO: Get the properties populated. 
		return;	
	}
	
    protected 
    Object createObject (final HttpServletRequest req)
        throws Exception
    {
		// Lookup the class type and find the appropriate constructor 
		
		final String type = typeExpr.evaluate (req);
		final ClassLoader cl = Thread.currentThread ().getContextClassLoader (); 
		final Class<?> klass = cl.loadClass (type); 
		// TODO: Error checking for class load
		final Constructor<?> cons = klass.getConstructor (Map.class);
		// TODO: err checking? 
		
		// Create a set of properties given the current context
		
		final Map<String, Object> props = new HashMap<String, Object> (); 
		for (final String key : propExprs.keySet ()) { 
			final JexlObjectExpression expr = propExprs.get (key);
			props.put (key, expr.evaluate (req));  
		}
		
		// Create the new object
		
		final Object value = cons.newInstance (props);
		// TODO: error checking

    	return value; 
   	}
}

// EOF