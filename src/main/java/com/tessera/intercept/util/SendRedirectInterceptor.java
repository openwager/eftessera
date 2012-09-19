package com.tessera.intercept.util;

import java.io.*;
import java.util.*;

import javax.servlet.http.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.*;

/**
 * 
 * @author crawford
 *
 */

public class SendRedirectInterceptor
	extends InterceptorSupport
{
	public
	SendRedirectInterceptor (final Map<String, String> props)
	{
	   super (props);
	   return;
	}
	
	interface PROP
	{
	   public static final String PAGE = "page";
	   public static final String URL = "url";
	}
	
	/**
	* @see com.twofish.ludwig.intercept.Interceptor#init()
	*/
	
	private JexlStringExpression page;
	private JexlStringExpression url;
	
	@Override
	public
	void init ()
	   throws Exception
	{
		super.init (); 
		String pageValue = getProperty(PROP.PAGE);
		String urlValue = getProperty(PROP.URL);
		
		if (pageValue != null && urlValue == null) {
			page = new JexlStringExpression (pageValue);
		} else if (urlValue != null && pageValue == null) {
			url = new JexlStringExpression (urlValue);
		} else if (pageValue == null && urlValue == null) {
			throw new ConfigurationException("'" + PROP.PAGE + "' and '" + PROP.URL + "' properties cannot both be empty.");
		} else {
			throw new ConfigurationException("Must specify either the '" + PROP.PAGE + "' property or the '" + PROP.URL + "' property.  Cannot specify both.");
		}
	   
	   return;
	}
	
	/**
	* @see Interceptor#intercept(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.twofish.ludwig.action.Action)
	*/
	
	public
	Alteration intercept (final HttpServletRequest req, final HttpServletResponse res, DispatchContext dc)
	   throws IOException
	{
		final String value;
		try {
			if (page != null) {
				value = req.getContextPath () + page.evaluate (req);
			} else {
				value = url.evaluate(req);
			}
		}
		catch (Exception e) {
			throw new IOException("Couldn't evaluate expression property value.", e);
		}
	
	   res.sendRedirect (value);
	   return ABORT;
	}
}

// EOF