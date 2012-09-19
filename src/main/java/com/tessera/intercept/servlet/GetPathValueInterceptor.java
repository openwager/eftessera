package com.tessera.intercept.servlet;

import java.util.*;

import javax.servlet.http.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.*;


public class GetPathValueInterceptor
	extends InterceptorSupport 
{

	public GetPathValueInterceptor(Map<String, String> props) {
		super(props);
	}
	
	interface PROP extends InterceptorSupport.PROP {
		public static final String INDEX = "index";
	}
	
	interface DEFAULT {
		public static final String ATTR = "path";
	}

	private Integer index = null;
	private String attr;
	
	public void init() throws ConfigurationException {
		try {
			if (getProperty(PROP.INDEX) != null)
			index = Integer.parseInt(getProperty(PROP.INDEX));
		}
		catch (NumberFormatException nfe) {
			throw new ConfigurationException("Not a valid index value.  Couldn't convert to integer.");
		}
		attr = getProperty(PROP.ATTR, DEFAULT.ATTR);
	}
	
	
	public Alteration intercept(HttpServletRequest req, HttpServletResponse res, DispatchContext dc)
			throws Exception {
		
		if (index == null) {
			req.setAttribute(attr, req.getAttribute(Dispatcher.ATTR.PREFIX + "path"));
		}
		else {
			String value = ((List<String>)dc.getPath ()).get(index);
			req.setAttribute(attr, value);
		}

		return NO_ALTERATION;
	}
}

// EOF
