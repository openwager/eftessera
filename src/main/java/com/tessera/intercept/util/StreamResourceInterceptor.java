package com.tessera.intercept.util;

import java.io.*;
import java.util.*;

import javax.servlet.http.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.*;
import com.weaselworks.io.*;

/**
 * 
 * @author crawford
 *
 */

public class StreamResourceInterceptor
	extends InterceptorSupport
{
	public
	StreamResourceInterceptor (Map<String, String> props)
	{
		super (props);
		return; 
	}
	
	protected String contentType; 
	protected String resource; 
	
	public
	void init ()
		throws Exception
	{
		resource = require ("resource"); 
		contentType = getProperty ("contentType"); 
		return; 
	}
	
	@Override
	public Alteration intercept (HttpServletRequest req, HttpServletResponse res, DispatchContext dc)
		throws Exception 
	{
		final ClassLoader cl = Thread.currentThread ().getContextClassLoader (); 
		final InputStream is = cl.getResourceAsStream (resource); 
		if (is == null) { 
			throw new IOException ("Resource not found: " + resource); 
		}
		final String content = IOUtil.readFully (is);
		if (contentType != null) { 
			res.setContentType (contentType); 
		}
		final Writer writer = res.getWriter ();
		writer.write (content); 
		writer.flush (); 
		writer.close (); 
		return ABORT; 
	}
}

// EOF
