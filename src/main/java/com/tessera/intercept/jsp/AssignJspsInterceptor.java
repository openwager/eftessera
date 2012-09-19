package com.tessera.intercept.jsp;

import java.io.*;
import java.util.*;

import javax.servlet.http.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.*;

/**
 * 
 * @author crawford
 *
 */

public class AssignJspsInterceptor
	extends PathsSlotsInterceptor
{
	public
	AssignJspsInterceptor (final Map<String, String> props)
	{
	    super (props);
	    return;
	}
	
	public
	Alteration intercept (final HttpServletRequest req, final HttpServletResponse res, DispatchContext dc)
	    throws IOException
	{
	    final int cnt = paths.size (); 
	
	    for (int i = 0; i < cnt; i ++) {
//	        final Widget w = new JspWidget (paths.get (i));
//	        final String slot = JspWidgetUtil.ATTR.SLOT + slots.get (i);
//	        req.setAttribute (slot, w);
	    	assignJsp (req, paths.get (i), slots.get (i)); 
	    }
	    return NO_ALTERATION;
	}
	
	public static
	void assignJsp (final HttpServletRequest req, final String path, final String slot)
	{
        final Widget w = new JspWidget (path);
        final String attr = JspWidgetUtil.ATTR.SLOT + slot;
        req.setAttribute (attr, w);
		return; 
	}
}

//EOF