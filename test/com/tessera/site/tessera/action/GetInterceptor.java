package com.tessera.site.tessera.action;

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

public class GetInterceptor
	extends FormInterceptor<GetForm>
{
	public
	GetInterceptor (final Map<String, String> props)
	{
		super (props, GetForm.class); 
		return;
	}

	interface DEFAULT
	{
		public String ATTR = "action"; 
	}
	
	@Override
    protected <Type extends GetForm> 
	Alteration intercept (final Type form, final HttpServletRequest req, final HttpServletResponse res, final DispatchContext dc)
        throws Exception
    {
		final Dispatcher d = dc.getDispatcher (); 
		final InterceptorChain chain = d.findAction (form.getAction ());
		if (chain == null) { 
			form.setError ("action", "Action not found."); 
			return failure (req); 
		}
		req.setAttribute (getProperty (PROP.ATTR, DEFAULT.ATTR), chain); 
		return NO_ALTERATION; 
    }
}

// EOF
