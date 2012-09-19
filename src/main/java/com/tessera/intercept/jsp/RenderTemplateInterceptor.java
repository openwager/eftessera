package com.tessera.intercept.jsp;

import java.io.*;
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

public class RenderTemplateInterceptor
	extends InterceptorSupport
{
	public
	RenderTemplateInterceptor (final Map<String, String> props)
	{
        super (props);
        return;
	}
	
	/**
	 * @see Interceptor#intercept(HttpServletRequest, HttpServletResponse, Action)
	 */
	
	public
	Alteration intercept (HttpServletRequest req, HttpServletResponse res, DispatchContext dc)
	        throws Exception
	{
		final String tmpl = (String) req.getAttribute (SetTemplateInterceptor.ATTR.TEMPLATE);
		
		if (tmpl == null) {
		    final Writer writer = res.getWriter ();
		    writer.write ("No template.");
		} else {
		    final RequestDispatcher rd = req.getRequestDispatcher (tmpl);
		    rd.forward (req, res);
		}
		
		return NO_ALTERATION;
	}
}

//EOF
