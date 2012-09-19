package com.tessera.intercept.util;

import java.util.*;

import javax.servlet.http.*;

/**
 * 
 * @author crawford
 *
 */

public class DumpSessionAttributesInterceptor
	extends DumpAttributesInterceptor
{
	public
	DumpSessionAttributesInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 
	}
	
	@Override
    Object getAttribute (HttpServletRequest req, String name)
    {
		return req.getSession ().getAttribute (name); 		
    }

	@SuppressWarnings("unchecked")
    @Override
    Enumeration<String> getAttributeNames (HttpServletRequest req)
    {
	    return req.getSession ().getAttributeNames (); 
    }

	@Override
    String getLabel ()
    {
		return "ServletContext"; 
    }
}

// EOF