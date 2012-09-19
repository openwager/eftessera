package com.tessera.site.lattice;

import java.util.*;

import javax.servlet.http.*;

import com.lattice.validate.*;
import com.tessera.dispatch.*;
import com.tessera.intercept.*;

/**
 * 
 * @author crawford
 *
 */

public class GetRegisteredValidatorsInterceptor
	extends InterceptorSupport
{
	public
	GetRegisteredValidatorsInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 
	}

	interface DEFAULT
	{
		public String ATTR = "validators"; 
	}
	
    public Alteration intercept (final HttpServletRequest req, final HttpServletResponse res, final DispatchContext dc)
        throws Exception
    {
		final Collection<ObjectValidator<?>> validators = ValidatorUtil.getRegisteredValidators ();
		req.setAttribute (getProperty (PROP.ATTR, DEFAULT.ATTR), validators); 
		return NO_ALTERATION; 
    }	
}

// EOF
