package com.tessera.intercept.servlet;

import java.util.*;

import javax.servlet.http.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.*;

public class GetQueryStringInterceptor 
	extends InterceptorSupport
{
	public GetQueryStringInterceptor(Map<String, String> props) 
	{
		super(props);
		return; 
	}
	
	public interface DEFAULT 
	{
		public static final String ATTR = "queryString";
	}

	public Alteration intercept (final HttpServletRequest req, final HttpServletResponse res, final DispatchContext dc)
		throws Exception 
	{
		req.setAttribute (getProperty(PROP.ATTR, DEFAULT.ATTR), req.getQueryString ());
		return NO_ALTERATION;
	}
}

// EOF