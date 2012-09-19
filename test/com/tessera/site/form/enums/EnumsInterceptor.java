package com.tessera.site.form.enums;

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

public class EnumsInterceptor
	extends FormInterceptor<EnumsForm>
{
	public
	EnumsInterceptor (final Map<String, String> props)
	{
		super (props, EnumsForm.class); 
		return; 
	}
	
	@Override
    public Alteration intercept (final EnumsForm form, final HttpServletRequest req, final HttpServletResponse res, final DispatchContext dc)
        throws Exception
    {		
		// UNIMPLEMENTED	
		return NO_ALTERATION; 
    }
}

// EOF
