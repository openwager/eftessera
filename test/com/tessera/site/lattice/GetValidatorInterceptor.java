package com.tessera.site.lattice;

import java.util.*;

import javax.servlet.http.*;

import com.lattice.validate.*;
import com.tessera.dispatch.*;
import com.tessera.intercept.*;
import com.tessera.intercept.form.*;



/**
 * 
 * @author crawford
 *
 */

public class GetValidatorInterceptor
	extends FormInterceptor<GetValidatorForm>
{
	public
	GetValidatorInterceptor (final Map<String, String> props)
	{
		super (props, GetValidatorForm.class); 
		return; 
	}

	interface DEFAULT
	{
		public String ATTR = "validator"; 
	}
	
	@Override
    protected <Type extends GetValidatorForm> 
	Alteration intercept (Type form, HttpServletRequest req, HttpServletResponse res, DispatchContext dc)
        throws Exception
    {		 
		final ObjectValidator<?> vor = ValidatorUtil.getValidator (form.getValidatorType ()); 
		req.setAttribute (getProperty (PROP.ATTR, DEFAULT.ATTR), vor); 
		return NO_ALTERATION; 
    }
}	

// EOF
