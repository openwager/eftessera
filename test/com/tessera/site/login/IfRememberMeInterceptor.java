package com.tessera.site.login;

import java.util.*;

import javax.servlet.http.*;

import com.tessera.intercept.*;

/**
 * 
 * @author crawford
 *
 */

public class IfRememberMeInterceptor
	extends PredicateInterceptorSupport
{
	public
	IfRememberMeInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 
	}
	
	@Override
    public boolean evaluate (final HttpServletRequest req, final HttpServletResponse res)
        throws Exception
    {
		final LoginForm form = findAttribute (LoginForm.class, req, getProperty (PROP.FORM, "form"));  
		return form != null && form.getRememberMe (); 
    }
}

// EOF