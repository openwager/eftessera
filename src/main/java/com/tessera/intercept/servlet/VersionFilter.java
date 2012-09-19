package com.tessera.intercept.servlet;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 * 
 * @author crawford
 *
 */

public class VersionFilter
	implements Filter
{
	private FilterConfig filterConfig = null;

	@Override
	public
	void init (final FilterConfig filterConfig) 
		throws ServletException 
	{
	  this.filterConfig = filterConfig;
	  return; 
	}
	
	@Override
	public
	void destroy () 
	{
	   this.filterConfig = null;
	   return; 
	}
	
	@Override
	public void doFilter (ServletRequest req, ServletResponse res, FilterChain chain)
		throws IOException, ServletException 
	{
		// Figure out where we really need to be
		
		final HttpServletRequest hsr = (HttpServletRequest) req; 
		String path = hsr.getRequestURI (); 
		
		path = path.substring (hsr.getContextPath ().length ()); 	
		if (! path.startsWith ("/v/")) { 
			chain.doFilter (req, res);
			return; 
		}
		
		for (int i = 0 ; i < 2; i ++) { 
			final int j = path.indexOf ('/', 1); 
			if (j < 0) {
				break; 
			} else { 
				path = path.substring (j); 
			}
		}
		
		// Forward to the intended destination
		
		final RequestDispatcher rd = filterConfig.getServletContext ().getRequestDispatcher (path); 
		rd.forward (req, res);
		return;
	}	  
}

// EOF
