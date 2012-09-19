package com.tessera.intercept.util;

import java.util.*;

import javax.servlet.http.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.*;

/**
 * 
 * @author crawford
 *
 */

public class ReloadMappingsJsonInterceptor
	extends InterceptorSupport
{
	public
	ReloadMappingsJsonInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 
	}

	interface DEFAULT
	{
		public String ATTR = "results"; 
	}
	
	public 
	Alteration intercept (HttpServletRequest req, HttpServletResponse res, DispatchContext dc)
        throws Exception
    {
		final Map<String, Object> results = new HashMap<String, Object> (); 
		
		try {
			final Dispatcher disp = dc.getDispatcher ();
			disp.reload ();
			results.put ("success", true); 
		}
		catch (final Exception e) { 
			results.put ("success", false); 
			results.put ("error", e.getMessage ()); 
		}
		
		req.setAttribute (getProperty (PROP.ATTR, DEFAULT.ATTR), results); 
		return NO_ALTERATION; 
    }
}

// EOF