package com.tessera.intercept;

import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.jexl.*;

/**
 * 
 * @author crawford
 *
 */

// package
class JexlUtil
{
	private
	JexlUtil ()
	{
		return; 
	}
	
	static
	JexlContext getContext (final HttpServletRequest req)
	{
		// Create the fallback chain
		
		final HttpSession sess = req.getSession (); 
		final ServletContext sc = sess.getServletContext (); 
		final ServletContextFallback scfb = new ServletContextFallback (sc); 
		final HttpSessionFallback sfb = new HttpSessionFallback (sess, scfb);
		final HttpServletRequestFallback srfb = new HttpServletRequestFallback (req, sfb); 
		
		// Create and populate the lookup map
	
		final Map<String, Object> map = new HashMap<String, Object> ();
		map.put ("_request", req); 
		map.put ("_session", sess); 
		map.put ("_application", sc); 
		final FallbackMap fbmap = new FallbackMap (map, srfb); 
		final JexlReplacementContext ctx = new JexlReplacementContext (fbmap); 
		return ctx; 		
	}
}

/**
 * 
 * @author crawford
 *
 */

@SuppressWarnings("rawtypes")
class JexlReplacementContext 
	implements JexlContext
{
    public
	JexlReplacementContext (final Map map)
	{
		this.map = map;  
		return; 
	}

	protected Map map; 
	
    public
    Map getVars ()
    {
    	return map; 
    }

	public
	void setVars (Map map)
    {
		throw new UnsupportedOperationException (); 
    }
}

/**
 * 
 * @author crawford
 *
 */

class HttpServletRequestFallback
	extends FallbackResource<HttpServletRequest>
{
	public 
	HttpServletRequestFallback (final HttpServletRequest req)
	{
	    super (req, new HttpSessionFallback (req.getSession ()));	    
	    return; 
	}
	
	public
	HttpServletRequestFallback (final HttpServletRequest req, final FallbackResource<HttpSession> fallback)
	{
		super (req, fallback); 
		return; 
	}
	
	protected 
	Object doGet (String key)
	{
		return resource.getAttribute (key); 
	}
}

/**
 * 
 * @author crawford
 *
 */

class HttpSessionFallback
	extends FallbackResource<HttpSession>
{
	public 
	HttpSessionFallback (final HttpSession sess)
	{
	    super (sess, new ServletContextFallback (sess.getServletContext ()));
		return;
	}
	
	public
	HttpSessionFallback (final HttpSession sess, final FallbackResource<ServletContext> fallback)
	{
		super (sess, fallback); 
		return; 
	}
	
	public
	Object doGet (String key)
	{
		return resource.getAttribute (key);
	}
}

/**
 * 
 * @author crawford
 *
 */

class ServletContextFallback
	extends FallbackResource<ServletContext>
{
	public 
	ServletContextFallback (final ServletContext a)
	{
	    super (a);
	    return; 
	}
	
	protected 
	Object doGet (final String key)
	{
		return resource.getAttribute (key); 
	}
}

// EOF