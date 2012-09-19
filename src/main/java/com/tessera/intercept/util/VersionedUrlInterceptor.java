package com.tessera.intercept.util;

import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.*;

/**
 * 
 * @author crawford
 *
 */

public class VersionedUrlInterceptor
	extends InterceptorSupport
{
	public
	VersionedUrlInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 
	}
	
	@Override
	public Alteration intercept (HttpServletRequest req, HttpServletResponse res, DispatchContext dc)
		throws Exception 
	{
		// Figure out what the intended path was
		
		final List<String> path = dc.getPath (); 
		final StringBuffer buf = new StringBuffer (); 
		final int cnt = path.size (); 
		
		for (int i = 0; i < cnt; i ++) {
			if (i == 1 || i  == 2) { 
				continue; 
			}
			buf.append ("/");
			buf.append (path.get (i)); 
		}
		final String actual = buf.toString (); 
		
		// Forward to the intended path
		
		final RequestDispatcher rd = req.getRequestDispatcher (actual);
		rd.forward (req, res); 
		
		return ABORT;
	}
}

// EOF