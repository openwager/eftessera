package com.tessera.intercept.json;

import java.util.*;

import javax.servlet.http.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.*;

/**
 * 
 * @author crawford
 *
 */

public class ObjectToJsonInterceptor
	extends InterceptorSupport
{
	public
	ObjectToJsonInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 
	}
	
	interface PROP
	{
		public String IN_ATTR = "in"; 
		public String OUT_ATTR = "out"; 
	}
	
	protected String inAttr; 
	protected String outAttr; 
	
	@Override
	public
	void init ()
		throws Exception
	{
		super.init (); 
		inAttr = require (PROP.IN_ATTR); 
		outAttr = require (PROP.OUT_ATTR); 
		return; 
	}
	
	public 
	Alteration intercept (HttpServletRequest req, HttpServletResponse res, DispatchContext dc)
		throws Exception 
	{
		final Map<String, Object> map; 
		final Object obj = req.getAttribute (inAttr); 
		
		if (obj != null) { 
			map = new HashMap<String, Object> (); 
		} else { 
			map = null; 
		}

		req.setAttribute (outAttr, map); 
		return null;
	}
}

// EOF