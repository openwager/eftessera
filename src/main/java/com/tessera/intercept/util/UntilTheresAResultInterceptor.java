package com.tessera.intercept.util;

import java.util.*;

import javax.servlet.http.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.*;

/**
*
* @author Lee Crawford (crawford@etherfirma.com)
*/

public class UntilTheresAResultInterceptor
	extends MappedInterceptorSupport
{
	public
	UntilTheresAResultInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 
	}
	
	interface DEFAULT
	{
		public String ATTR = "mfresult";
	}
	
	public 
	String map (HttpServletRequest req, HttpServletResponse res)
		throws Exception 
	{
		throw new IllegalStateException ("This method should not be invoked.");
		// NOT REACHED
	}
	
	public 
	Alteration intercept (final HttpServletRequest req, final HttpServletResponse res, DispatchContext dc)
	    throws Exception
   {
		final String attr = getProperty (PROP.ATTR, DEFAULT.ATTR);
		
		for (final String name : getCases ()) {
			final InterceptorChain c = getCase (name); 
			final Alteration alt = c.intercept (req, res, dc); 
			if (alt != NO_ALTERATION) { 
				return alt; 
			}
			if (req.getAttribute (attr) != null) { 
				break; 
			}
		}
		return NO_ALTERATION;
	}
}

// EOF
