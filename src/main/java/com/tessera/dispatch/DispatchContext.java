package com.tessera.dispatch;

import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.log4j.*;

import com.tessera.intercept.*;
import com.tessera.intercept.util.*;

/**
 * TODO: Comment goes here. 
 * 
 * @author Lee Crawford (lee.crawford@gmail.com) 
 * @copyright Copyright (c) 2009, Weaselworks, Inc. 
 */

public class DispatchContext
{
	private static final Logger logger = Logger.getLogger (DispatchContext.class); 
	
	public
	DispatchContext (final Dispatcher dispatcher, final List<String> path)
	{
		setDispatcher (dispatcher); 
		setPath (path); 
		return; 
	}

	protected List<Interceptor> interceptors = new ArrayList<Interceptor> ();  
	public List<Interceptor> getInterceptors () { return this.interceptors; } 
	public void setInterceptor (final List<Interceptor> interceptors) { this.interceptors = interceptors; return; }
	
	protected List<String> path; 
	public List<String> getPath () { return this.path; }
	protected void setPath (final List<String> path) { this.path = path; return; } 
	
	public 
	Interceptor pop () 
	{
		return interceptors.remove (0); 
	}
	
	public
	Interceptor peek ()
	{
		return interceptors.get (0); 
	}
	
	public 
	boolean isEmpty ()
	{
		return interceptors.isEmpty (); 
	}
	
	public
	void push (final Interceptor itor)
	{
		if (itor == null) { 
			throw new NullPointerException (); 
		}
		interceptors.add (0, itor); 
		return; 
	}
	
	protected Dispatcher dispatcher; 
	public Dispatcher getDispatcher () { return this.dispatcher; } 
	public void setDispatcher (final Dispatcher dispatcher) { this.dispatcher = dispatcher; return; } 
	
//	protected Audit audit = new Audit (); 
//	public Audit getAudit () { return this.audit; } 
	
	/**
	 * 
	 * @param req
	 * @param res
	 * @throws Exception
	 */
	
	public
	void intercept (final HttpServletRequest req, final HttpServletResponse res)
		throws Exception
	{
		while (! isEmpty ()) {
			try {
				final Interceptor itor = pop ();
//				if (! itor.getEnabled ()) {
//					logger.info ("skipping disabled interceptor: " + itor.getClass().getSimpleName ()); 
//					continue; 
//				}
				final Alteration alt = itor.intercept (	req, res, this); 
				if (alt != null) { 
					if (alt instanceof ForwardAlteration) {
		       			final String newuri = ((ForwardAlteration) alt).getAction ();
		       			logger.info ("FORWARD: " + newuri); 
//		       			if (Util.isEqual (newuri, req.getAttribute (Dispatcher.PATH_URI))) {
//		       				logger.error ("Aborting recursive interception (" + newuri + ")."); 
//		       				return; 
//		       			}
		       			final Interceptor next = getDispatcher ().findAction (newuri);
		       			push (next);
		       			continue; 
					} else if (alt instanceof AbortAlteration || alt instanceof HaltAlteration) {
						return;
					}
				}
			}
	        catch (final Throwable t) {
	        	logger.warn ("There was an exception while processing the action");
	            logger.warn (t.getMessage (), t);
	        	final ForwardAlteration forward = (ForwardAlteration) SetFailureInterceptor.getFailureForward (req);
	        	if (forward != null) { 
	        		req.setAttribute (Dispatcher.ATTR.THROWABLE, t);
	        		final String newuri = forward.getAction ();
	        		logger.warn ("THROWABLE - " + newuri); 
//	       			if (Util.isEqual (newuri, req.getAttribute (Dispatcher.PATH_URI))) {
//	       				logger.error ("Aborting recursive interception (" + newuri + ")."); 
//	       				return; 
//	       			}
	       			final Interceptor next = getDispatcher ().findAction (newuri);
	       			if (next == null) {
	       				logger.error ("Error action does not exist: " + newuri);
	       				res.sendError (500);
	       				return;
	       			}
	       			push (next);
	       			continue; 
	        	} else { 
	        		return; 
	        	}
	        }
		}

		return; 
	}

	/**
	 * The attribute used to store the reference to the {@link DispatchContext}
	 * on the servlet request. 
	 */
	
	public static final String REQ_ATTR = "_dc"; 
	
	/**
	 * Retrieves a reference to the {@linkplain DispatchContext} that is currently
	 * associated with the specified servlet request. 
	 * 
	 * @see #setDispatchContext(DispatchContext, HttpServletRequest)
	 * @param req
	 * @return
	 */
	
	public static
	DispatchContext getDispatchContext (final ServletRequest req)
	{
		return (DispatchContext) req.getAttribute (REQ_ATTR); 
	}

	/**
	 * Stores a reference to the {@linkplain DispatchContext} on the 
	 * specified servlet request
	 * 
	 * @see #getDispatchContext(HttpServletRequest)
	 * @param dc
	 * @param req
	 */
	
	public static
	void setDispatchContext (final DispatchContext dc, final HttpServletRequest req)
	{
		req.setAttribute (REQ_ATTR, dc); 
		return; 
	}
}	

// EOF