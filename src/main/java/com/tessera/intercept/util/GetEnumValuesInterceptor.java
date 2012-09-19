package com.tessera.intercept.util;

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

public class GetEnumValuesInterceptor
	extends InterceptorSupport
{
	public
	GetEnumValuesInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 
	}

	interface DEFAULT
	{
		public String ATTR = "values"; 
	}
	
	protected Object [] values; 
	
	@Override
	public
	void init ()
		throws Exception
	{
		super.init (); 
		final String type = require (PROP.CLASS); 
		values = ClassUtil.getEnumValues (type); 
		return; 
	}
	
    public
    Alteration intercept (HttpServletRequest req, HttpServletResponse res, DispatchContext dc)
        throws Exception
    {
		req.setAttribute (getProperty (PROP.ATTR, DEFAULT.ATTR), values); 
		return NO_ALTERATION; 		
    }
}

// EOF