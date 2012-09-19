package com.tessera.intercept.json;

import java.io.*;
import java.util.*;

import javax.servlet.http.*;
import org.apache.log4j.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.*;
import com.weaselworks.util.*;

/**
 * 
 * @author crawford
 *
 */

public class SendJsonInterceptor
	extends InterceptorSupport
{
	private static final Logger logger = Logger.getLogger (SendJsonInterceptor.class); 
	
	public
	SendJsonInterceptor (final Map<String, String> props)
	{
		super (props);
		return; 
	}

	public interface DEFAULT
	{
		public String ATTR = "json"; 
	}
	
	public static final String CONTENT_TYPE = "application/json"; 
	
	public
	Alteration intercept (HttpServletRequest req, HttpServletResponse res, DispatchContext dc)
		throws Exception
	{
		res.setContentType (CONTENT_TYPE); 
		final Object obj = req.getAttribute (getProperty (PROP.ATTR, DEFAULT.ATTR)); 
		final String json = JSONUtil.toJson (obj); 
		if (getDebug ()) { 
			logger.info ("Sending " + json); 
		}
		final PrintWriter writer = res.getWriter (); 
		writer.print (json); 
		writer.flush (); 	
		return ABORT;
	}
}

// EOF
