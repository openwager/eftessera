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

public class NullInterceptor
	extends InterceptorSupport
{
	public
	NullInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 		
	}
	
    public 
    Alteration intercept (final HttpServletRequest req, final HttpServletResponse res, final DispatchContext dc)
        throws Exception
    {
		// EMPTY
	    return NO_ALTERATION;
    }
}

// EOF
