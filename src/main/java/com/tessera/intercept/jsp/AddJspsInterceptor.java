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

public class AddJspsInterceptor
	extends PathsSlotsInterceptor
{
	public
	AddJspsInterceptor (final Map<String, String> props)
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
	        String slot = JspWidgetUtil.ATTR.SLOT + slots.get (i); 
	        final WidgetContainer container = getContainer (req, slot);
	        final JspWidget w = new JspWidget (paths.get (i));
            container.addWidget(w);
	    }
	    return NO_ALTERATION;
	}
}

//EOF
