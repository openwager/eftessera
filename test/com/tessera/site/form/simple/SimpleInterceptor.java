package com.tessera.site.form.simple;

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

public class SimpleInterceptor
	extends FormInterceptor<SimpleForm>
{
	public
	SimpleInterceptor (final Map<String, String> props)
	{
		super (props, SimpleForm.class); 
		return; 
	}

	interface DEFAULT
	{
		public String FORM = "form"; 
	}
	
	@Override
    public Alteration intercept (final SimpleForm form, HttpServletRequest req, HttpServletResponse res, DispatchContext dc)
        throws Exception
    {
		// UNIMPLEMENTED		
		return NO_ALTERATION; 
    }
}

// EOF
