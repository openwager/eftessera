package com.tessera.intercept.util;

import java.util.*;

import javax.servlet.http.*;

/**
 * 
 * @author crawford
 *
 */

public class DumpRequestAttributesInterceptor
	extends DumpAttributesInterceptor
{
	public
	DumpRequestAttributesInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 
	}

	@Override
    Object getAttribute (HttpServletRequest req, String name)
    {
	    return req.getAttribute (name); 
    }

	@SuppressWarnings("unchecked")
    @Override
    Enumeration<String> getAttributeNames (HttpServletRequest req)
    {
	    return req.getAttributeNames (); 
    }

	@Override
    String getLabel ()
    {
	    return "HttpServletRequest"; 
    }
}

// EOF