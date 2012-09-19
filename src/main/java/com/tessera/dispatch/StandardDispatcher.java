package com.tessera.dispatch;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.log4j.*;

import com.tessera.dispatch.mapping.*;
import com.tessera.intercept.*;
import com.tessera.util.*;

/**
 * TODO: Comment goes here. 
 * 
 * @author Lee Crawford (lee.crawford@gmail.com) 
 * @copyright Copyright (c) 2009, Weaselworks, Inc. 
 */

@SuppressWarnings("serial")
public class StandardDispatcher
	extends Dispatcher
{
	protected static final Logger logger = Logger.getLogger (StandardDispatcher.class);

	/** 
	 * Default constructor.
	 */
	
	public
	StandardDispatcher ()
	{
		// EMPTY
	    return;
	}
	
	/**
	 * Causes the dispatcher to reset its internal state and reload the mappings files. 
	 * 
	 * @throws ServletException
	 */
	
	public synchronized
	void reload ()
	    throws ServletException
	{
		logger.info ("Reloading mappings."); 		
	    actions.clear ();
	    
	    // Load the any static mappings that have been declared
		
	    int cnt = 0;
	
	    while (true) {
	        final String param = "mappings." + cnt;
	        final String path = getInitParameter (param);
	        if (path == null) {
	            if (cnt == 0) {
	                logger.error ("No static mappings declared.");
	            }
	            break;
	        }
	        
	        try { 
	            loadMappings (path);             	
	        }
	        catch (final Throwable t) { 
	        	logger.error ("Error loading mappings: " + path, t); 
	        }
	        cnt ++;           
	    }

	    // Install a reference to the dispatcher in the interceptors
	    
	    final DispatchNode root = actions.getRoot (); 
	    root.setDispatcher (this); 
	    
	    // Invoke any of the initialization actions that were registered as 
	    // part of the mappings files (e.g., actions that start with '%')
	    
	    final DispatchNode initActions = root.getNodes ().get ("%"); 
	    if (initActions != null) { 
	    	try { 
	    		init (initActions);        		
	    	}
	    	catch (final Exception e) { 
	    		throw new ServletException (e.getMessage (), e); 
	    	}
	    }
	
	    // Install a null global pre- and post-action if none were defined in the 
	    // mappings that were just loaded
	    
	    try { 
		    if (getGlobalPreAction () == null) { 
		    	logger.warn	("No global pre-action (^) defined.");
				actions.register (GLOBAL_PRE_ACTION, new InterceptorChain ()); 
		    }
		    
		    if (getGlobalPostAction () == null) {
		    	logger.warn ("No global post-action ($) defined."); 
				actions.register (GLOBAL_POST_ACTION, new InterceptorChain ()); 
		    }
	    }
	    catch (final MappingException m_e) { 
	    	logger.error (m_e.getMessage (), m_e); 
	    }

	    return;
	}
	
	
	/**
	 * Figures out the name of the action that will be used to handle the request. 
	 * 
	 * @param req
	 * @return
	 */
	
	private
	String getAction (final HttpServletRequest req)
	{
		// The action is the request uri with the context path and
		// servlet path trimmed off the front of it.
		// We can get this value either from the request using either getPathInfo() 
		// or getServletPath(), depending on how our servlet-mapping is defined in our web.xml
		
		// If the servlet mapping url-pattern is "/some-path/*" the path info holds
		// the value we need.
		// If the servlet mapping url-pattern is "/" or "*.some-extension" we get a null value
		// for path info, but getServletPath() returns the value we want.
		
		final String pathInfo = req.getPathInfo ();
		if (pathInfo == null) {
			return req.getServletPath();
		} else {
			return pathInfo;
		}
	}
	
	/**
	 * Servlet initialization; this is used to load the static mappings (if any)
	 * from a local resource file to bootstrap the system.
	 *
	 * @throws javax.servlet.ServletException
	 */
	
	@Override
	public
	void init (final ServletConfig sc)
	    throws ServletException
	{
		super.init (sc); 		
		reload (); 
	    return;
	}

	/**
	 * A utility method used to invoke all of the actions associated with the specified
	 * {@linkplain DispatchNode} and, recursively, its child nodes. 
	 * 
	 * @param node
	 */
	
	private
	void init (final DispatchNode node)
		throws Exception
	{
		assert node != null; 
		
		for (final Interceptor itor : node.getChains ().values ()) {     	     
	        final DispatchContext dc = new DispatchContext (this, null); 
	        dc.push (itor); 
	        dc.intercept (null, null); 	
		}
		
		for (final DispatchNode subnode : node.getNodes ().values ()) { 
			init (subnode); 
		}
		return; 
	}
	
	/**
	 * 
	 * @param path
	 */
	
	public
	void loadMappings (final String path)
		throws IOException
	{
		MappingsUtil.loadMappings (path, this); 
		return; 
	}
	
	/**
	 * The name of the attribute in the context where we'll store  a
	 * reference to the dispatcher.
	 */
	
	public static final String SRC_URI = Dispatcher.ATTR.PREFIX + "uri";
	
	/**
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	
	@Override
	public  
	void doGet (final HttpServletRequest req, final HttpServletResponse res)
	    throws ServletException, IOException
	{
	    doPost (req, res);
	    return;
	}
	
	/**
	 * @see HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	
	@Override
	public 
	void doPost (final HttpServletRequest req, final HttpServletResponse res)
	    throws ServletException, IOException
	{
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		final String action = getAction (req);  
	    intercept (action, req, res, true);
	    return;
	}
	
	/**
	 *
	 * @param uri
	 * @param req
	 * @param doGlobal
	 * @throws IOException
	 */
	
	protected
	void intercept (final String uri, final HttpServletRequest req, final HttpServletResponse res, final boolean doGlobal)
	    throws IOException
	{
	    // Look up the appropriate action mapping
	
		final List<String> path = PathUtil.parsePath (uri); 
		Interceptor action = actions.lookup (path);  
	    if (action == null) {
	    	final List<String> nullPath = Arrays.asList (new String [] { "null" }); 
	    	action = actions.lookup (nullPath); 
	    	if (action == null) { 
		    	logger.info ("no chain found for: " + uri); 
		        res.sendError (HttpServletResponse.SC_NOT_FOUND, "No chain for: " + uri);
		        return;
	    	}
	    }
	
	    // Assign the source URI as long as we're not being forwarded
	
	    if (req.getAttribute (SRC_URI) == null) {
	        req.setAttribute (SRC_URI, uri);
	    }
	
	    // Do the actual interception
	
	    try {
	        final DispatchContext dc = new DispatchContext (this, path); 
	        dc.push (getGlobalPostAction ()); 
	        dc.push (action); 
	        dc.push (getGlobalPreAction ());
	        DispatchContext.setDispatchContext (dc, req);
	        dc.intercept (req, res); 
	    }
	    catch (final Throwable t) {
	    	logger.warn (t.getMessage (), t);
	    }
	    return;
	}
	
	protected DispatchPath actions = new DispatchPath (); 
	
	public
	DispatchPath getDispatchPath ()
	{
		return actions; 
	}
	
	public static final String INIT_ACTIONS = "%"; 
	public static final String GLOBAL_PRE_ACTION = "^";
	public static final String GLOBAL_POST_ACTION = "$"; 
	
	public
	InterceptorChain getGlobalPreAction ()
	{
		return actions.lookup (GLOBAL_PRE_ACTION); 
	}
	
	public
	InterceptorChain getGlobalPostAction ()
	{
		return actions.lookup (GLOBAL_POST_ACTION); 
	}
	
	public
	InterceptorChain findAction (final String action)
	{
		return actions.lookup (action); 
	}
	
	public
	void dump (final PrintStream out)
	{
		out.println (this); 
		return; 
	}
	
	public
	void dump ()
	{
	    dump (System.err);  
	    return;
	}
}
// EOF