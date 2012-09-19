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

abstract public class ClassFactoryInterceptorSupport
	extends InterceptorSupport
{
	public
	ClassFactoryInterceptorSupport (final Map<String, String> props)
	{
		super (props); 
		return; 
	}
	
	interface PROP 
	{
		public String CLASS = "class"; 
		public String PROPERTY = "property."; 
	}

	protected JexlStringExpression attrExpr; 
	protected AttributeContext context; 
	protected JexlStringExpression typeExpr; 
	protected Map<String, JexlStringExpression> propExprs; 
	
	public
	void init ()
		throws Exception
	{
		super.init (); 
		typeExpr = new JexlStringExpression (require (PROP.CLASS)); 
		propExprs = new HashMap<String, JexlStringExpression> ();

		for (final String key : getProperties ().keySet ()) { 
			if (key.startsWith (PROP.PROPERTY)) { 
				final String name = key.substring (PROP.PROPERTY.length ()); 
				final String value = getProperties ().get (key); 
				final JexlStringExpression valueE = new JexlStringExpression (value); 
				propExprs.put (name, valueE); 
			}
		}
		
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
		final Constructor<?> cons = klass.getConstructor ();
		// TODO: err checking? 
		
		// Create a set of properties given the current context
		
		final Map<String, String> props = new HashMap<String, String> (); 
		for (final String key : propExprs.keySet ()) { 
			final JexlStringExpression expr = propExprs.get (key);
			props.put (key, expr.evaluate (req));  
		}
		
		// Create the new object
		
		final ClassFactory<?> factory = (ClassFactory<?>) cons.newInstance ();
		final Object value = factory.newInstance (props); 		
		// TODO: error checking
		
		return value; 		
	}
}

// EOF