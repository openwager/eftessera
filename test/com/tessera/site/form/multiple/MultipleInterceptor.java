package com.tessera.site.form.multiple;

import java.util.*;

import javax.servlet.http.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.*;

/**
 * 
 * @author crawford
 *
 */

public class MultipleInterceptor
	extends InterceptorSupport
{
	public
	MultipleInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 
	}

    public Alteration intercept (final HttpServletRequest req, final HttpServletResponse res, final DispatchContext dc)
        throws Exception
    {
		// UNIMPLEMENTED
	    return NO_ALTERATION;
    }	
}

// EOF