package com.tessera.site.form.types;

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

public class TypesInterceptor
	extends FormInterceptor<TypesForm>
{
	public
	TypesInterceptor (final Map<String, String> props)
	{
		super (props, TypesForm.class); 
		return; 
	}

	interface DEFAULT
	{
		public String FORM = "form"; 
	}
	
	@Override
    public Alteration intercept (final TypesForm form, final HttpServletRequest req, final HttpServletResponse res, final DispatchContext dc)
        throws Exception
    {
		// UNIMPLEMENTED		
		return NO_ALTERATION; 
    }
}

// EOF
