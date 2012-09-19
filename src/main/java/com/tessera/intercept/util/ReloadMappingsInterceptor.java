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

public class ReloadMappingsInterceptor
	extends InterceptorSupport
{
	public
	ReloadMappingsInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 
	}

    public 
    Alteration intercept (final HttpServletRequest req, final HttpServletResponse res, final DispatchContext dc)
        throws Exception
    {
		final Dispatcher disp = dc.getDispatcher ();
		disp.reload (); 
		return NO_ALTERATION;  	
    }
}

// EOF
