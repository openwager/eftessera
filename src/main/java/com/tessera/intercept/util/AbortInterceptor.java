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

public class AbortInterceptor
	extends InterceptorSupport
{
	public
	AbortInterceptor (final Map<String, String> props)
	{
		super (props); 
		return;  
	}

	public
	Alteration intercept (HttpServletRequest req, HttpServletResponse res, DispatchContext dc)
		throws Exception 
	{
		return ABORT; 
	}
}

// EOf
