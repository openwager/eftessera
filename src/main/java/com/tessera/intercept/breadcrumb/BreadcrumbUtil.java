package com.tessera.intercept.breadcrumb;

import java.util.*;

import javax.servlet.*;

/**
 *
 * @author Lee Crawford (crawford@etherfirma.com)
 */

public class BreadcrumbUtil
{
	private 
	BreadcrumbUtil ()
	{
		return; 
	}
	
	public static final String ATTR = "_bc"; 
	
	@SuppressWarnings ("unchecked")
	public static 
	List<BreadcrumbEntry> getBreadcrumbs (final ServletRequest req, final boolean create)
	{
		List<BreadcrumbEntry> list = (List<BreadcrumbEntry>) req.getAttribute (ATTR);
		if (list == null) { 
			if (create) { 
				list = new ArrayList<BreadcrumbEntry> (); 
				req.setAttribute (ATTR, list); 
			}
		}
		return list; 
	}
	
	public static
	void clearBreadcrumbs (final ServletRequest req)
	{
		req.removeAttribute (ATTR); 
		return; 
	}
	
	public static
	BreadcrumbEntry addBreadcrumb (final ServletRequest req, final String label, final String path)
	{
		final BreadcrumbEntry bc = new BreadcrumbEntry (label, path);
		final List<BreadcrumbEntry> bcs = getBreadcrumbs (req, true); 
		bcs.add (bc); 
		return bc; 
	}
}

// EOF