package com.tessera.intercept.util;

import java.util.*;

import javax.servlet.http.*;

/**
 * 
 * @author crawford
 *
 */

public class DumpApplicationAttributesInterceptor
	extends DumpAttributesInterceptor
{
	public
	DumpApplicationAttributesInterceptor (final Map<String, String> props)
	{
		super (props);
		return; 
	}

	@Override
    Object getAttribute (HttpServletRequest req, String name)
    {
		return req.getSession ().getServletContext ().getAttribute (name); 		
    }

	@SuppressWarnings("unchecked")
    @Override
    Enumeration<String> getAttributeNames (HttpServletRequest req)
    {
	    return req.getSession ().getServletContext ().getAttributeNames (); 
    }

	@Override
    String getLabel ()
    {
		return "ServletContext"; 
    }
}

// EOF