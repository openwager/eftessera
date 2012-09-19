package com.tessera.intercept.util;

import java.io.*;
import java.util.*;

import javax.servlet.http.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.*;
import com.weaselworks.util.*;

/**
 * 
 * @author crawford
 *
 */

public class SetFailureInterceptor
	extends InterceptorSupport
{
	public
	SetFailureInterceptor (final Map<String, String> props) 
	{
		super (props);
		return;
	}
	
	public interface PROP
	{
		public static final String URI = "uri";
	}
	
	public interface ATTR
	{
		public String FAIL_URI = Dispatcher.ATTR.PREFIX + "fail";
	}
	
	public static
	ForwardAlteration getFailureForward (final HttpServletRequest req)
	{
		if (req != null) {
			final String uri = (String) req.getAttribute (ATTR.FAIL_URI); 
			if (! StringUtil.isEmpty(uri)) { 
				return new ForwardAlteration (uri); 
			}
		}
		return null; 
	}
	
	public 
	Alteration intercept(final HttpServletRequest req, final HttpServletResponse res, DispatchContext dc)
		throws IOException
	{
		// TODO: Should call the property "action" or "uri" ?
		String uri = getProperty (PROP.URI);
		
		// If no URI property was provided, we default to using a URI derived from the referer.
		// TODO: Re-evaluate this.  Pretty sure we can't use referer to derive the return URL because of
		// issues that come up with form validation failures (where the referer will end up being the same
		// as the URL that you're submitting to).  Taking it out for now.
		/*
		if (StringUtil.isEmpty(uri)) {
			
			// We create a new URL instance to vaildate the referer URL
			try {
				final URL referer = new URL (req.getHeader("Referer"));
				
				// The failure handler does a forward() so we need just the path
				// TODO: What do we do with query string parameters?
				uri = referer.getPath ();
				
				// Remove the context/servlet prefix from the URI
				// the default error page
				final String prefix = req.getContextPath () + req.getServletPath ();
				if (uri.startsWith(prefix)) {
					uri = uri.substring (prefix.length());
				}
				else {
					uri = null;
				}
			}
			catch (MalformedURLException e) {
				uri = null;
			}
	
			// If the referer is empty, we use the default error URI.  You're  always going to have 
			// the potential that a bad client won't send a referer, and you would never want the 
			// request attribute to be empty at the end of this interceptor, since it may be assumed
			// to have a value by later interceptors.
			if (StringUtil.isEmpty(uri)) {
				uri = InterceptorSupport.DEFAULT_FAILURE;
			}
		}
		*/
	
		// If no uri was provided, we use the default error URI.
	//	if (StringUtil.isEmpty(uri)) {
	//		uri = InterceptorSupport.DEFAULT_FAILURE;
	//	}
	
		// TODO: Ultimately, we're  going to want to store a data object (or set of String attributes?) here
		// that encapsulates everything we might need to handle a failure (URI, GET/POST method, properties, etc.),
		// but for now, we're just storing the URI string.
		// Set the URI on the request.
		req.setAttribute (ATTR.FAIL_URI, uri);
		
		return NO_ALTERATION;
	}
}

// EOF 