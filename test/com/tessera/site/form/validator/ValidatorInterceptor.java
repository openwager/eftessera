package com.tessera.site.form.validator;

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

public class ValidatorInterceptor
	extends FormInterceptor<ValidatorForm>
{
	public 
	ValidatorInterceptor (final Map<String, String> props)
	{
		super (props, ValidatorForm.class); 
		return; 
	}

	@Override
    protected <Type extends ValidatorForm> 
	Alteration intercept (final Type form, final HttpServletRequest req, final HttpServletResponse res, final DispatchContext dc)
        throws Exception
    {
		// Do some field-level validation
		
		if (form.getB () <= form.getA ()) { 
			form.setError ("b", "B must be > A."); 
		}
		if (form.getC () <= form.getB ()) { 
			form.setError ("c", "C must be > B."); 
		}
		if (form.getHasErrors ()) { 
			return failure (req); 
		}

		// Do some global validation
		
		if ((form.getA () + form.getB () + form.getC ()) > 200) { 		
			form.addGlobalError ("They can't add up to more than 200.");
		}
		if (form.getHasErrors ()) { 
			return failure (req); 
		}
		return NO_ALTERATION; 
    }
}

// EOF