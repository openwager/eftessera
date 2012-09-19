package com.tessera.site.form.structured;

import java.util.*;

import javax.servlet.http.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.*;
import com.tessera.intercept.form.*;

/**
 * 
 * @author crawford
 *
 */

public class StructuredInterceptor
	extends FormInterceptor<StructuredForm>
{
	public
	StructuredInterceptor (final Map<String, String> props)
	{
		super (props, StructuredForm.class); 
		return; 
	}

	@Override
    protected <Type extends StructuredForm> 
	Alteration intercept (final Type form, final HttpServletRequest req, final HttpServletResponse res, final DispatchContext dc)
        throws Exception
    {
		// UNIMPLEMENTED
	    return NO_ALTERATION;
    }
}

// EOF
