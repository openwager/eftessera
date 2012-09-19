package com.tessera.site.tessera.action;

import java.util.*;

import javax.servlet.http.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.*;

/**
 * 
 * @author crawford
 *
 */

public class GetAllInterceptor
	extends InterceptorSupport
{
	public
	GetAllInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 
	}
	
	interface DEFAULT
	{
		public String ATTR = "actions"; 
	}

    public Alteration intercept (final HttpServletRequest req, final HttpServletResponse res, final DispatchContext dc)
        throws Exception
    {
	   final Dispatcher d = dc.getDispatcher ();
	   final DispatchPath dp = d.getDispatchPath (); 
	   req.setAttribute (getProperty (PROP.ATTR, DEFAULT.ATTR), dp); 
	    return NO_ALTERATION;
    }
}

// EOF