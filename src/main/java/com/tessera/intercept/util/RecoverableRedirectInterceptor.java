package com.tessera.intercept.util;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.*;
import com.weaselworks.util.*;
import com.weaselworks.util.Base64;

/**
 * 
 * @author crawford
 *
 */

public class RecoverableRedirectInterceptor
	extends InterceptorSupport
{
    public
    RecoverableRedirectInterceptor (final Map<String, String> props)
    {
        super (props);
        return;
    }

	protected JexlStringExpression uriExpr = null; 
	
	@Override
	public
	void init ()
		throws Exception
	{
		super.init (); 
		uriExpr = new JexlStringExpression (require (PROP.URI)); 
		return; 
	}
	
	interface PROP
	{
		public String URI = "uri"; 
	}
	
	interface PARAM
	{
		public String PREFIX = "_r"; 
		public String URI = PREFIX + "u"; 
		public String METHOD = PREFIX + "m"; 
		public String NAME = PREFIX + "n";
		public String VALUE = PREFIX + "v"; 
	}
	
	@SuppressWarnings("unchecked")
	public
	Alteration intercept (final HttpServletRequest req, final HttpServletResponse res, DispatchContext dc)
		throws Exception
	{
		String uri = uriExpr.evaluate (req);
		
		// If it's a GET, we do a simple redirect instead of an auto-posting form
		if (req.getMethod() == "GET") {
			final String requestUri = req.getRequestURI ().toString () +
				((req.getQueryString () != null) ? ("?" + req.getQueryString ()) : "");
			
			final String encodedUri = new String (Base64.encode (requestUri));
			String redirectUrl = appendUrlParameter(req.getContextPath () + uri, PARAM.URI, encodedUri);
			res.sendRedirect(redirectUrl);
			return ABORT;
		}
		
		String postUrl = req.getContextPath () + uri;

		// If we're dealing with a POST, we construct a form to preserve all of the values
		final PrintWriter out = res.getWriter (); 
		out.println ("<html><body><form method=\"POST\" action=\"" + postUrl + "\">"); 
		outputParameter (out, PARAM.METHOD, req.getMethod ()); 
		outputParameter (out, PARAM.URI, req.getRequestURI ()); 
		
		final Enumeration<String> params = req.getParameterNames (); 
		int cnt = 0; 

		while (params.hasMoreElements ()) {
			final String key = (String) params.nextElement ();
			outputParameter (out, PARAM.NAME + cnt, key); 
			outputParameter (out, PARAM.VALUE + cnt, req.getParameter (key)); 			
			cnt ++; 
		}
		
		out.println ("</form><script>document.forms[0].submit();</script></body></html>"); 		
		out.flush (); 
		return ABORT; 
	}
	
	private 
	void outputParameter (final PrintWriter out, final String name, String value)
	{
		value = new String(Base64.encode(value));
		out.println ("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\" />"); 
		return;  
	}
	
	private
	String appendUrlParameter(String url, String name, String value) {
		return url + (url.contains("?") ? "&" : "?") + name + "=" + value;
	}
	
	/**
	 * 
	 * @param req
	 * @return
	 */
	
	public static
	boolean hasRecoveryContext (final ServletRequest req)
	{
		return ! StringUtil.isEmpty (req.getParameter (PARAM.URI)); 
	}

	/**
	 * 
	 * @param out
	 * @param req
	 * @throws IOException
	 */
	
 	@SuppressWarnings("unchecked")
	public static
 	void addRecoveryContext (final JspWriter out, final ServletRequest req)
		throws IOException
	{
		if (RecoverableRedirectInterceptor.hasRecoveryContext (req)) { 
			final Enumeration<String> params = req.getParameterNames (); 
			while (params.hasMoreElements ()) { 
				final String name = params.nextElement ();
				if (name.startsWith (PARAM.PREFIX)) {
					out.print ("<input type='hidden' name='" + name + "' value='" + req.getParameter (name) + "'/>");
				}
			}
		}
		
		return; 
	}   
}

// EOF
