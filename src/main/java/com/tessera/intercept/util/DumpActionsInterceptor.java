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

public class DumpActionsInterceptor
	extends InterceptorSupport
{
	public DumpActionsInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 
	}

    public
    Alteration intercept (HttpServletRequest req, HttpServletResponse res, DispatchContext dc)
        throws Exception
    {
		final Dispatcher disp = dc.getDispatcher ();
		DispatcherUtil.dump (disp); 
		return NO_ALTERATION; 
    }
}

// EOF