package com.tessera.intercept.jsp;

import java.io.*;
import java.util.*;

import javax.servlet.http.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.*;
import com.weaselworks.util.*;

/**
 * 
 * @author crawford
 *
 */

public class CreateContainersInterceptor
	extends InterceptorSupport
{
    public
    CreateContainersInterceptor (final Map<String, String> props)
    {
        super (props);
        return;
    }

    interface PROP
    {
        public String SLOT = "slot";
    }

    @Override
    public
    void init ()
        throws Exception
    {
    	super.init (); 
    	slots = new ArrayList<String> (); 
    	for (int cnt = 0; ; cnt ++) { 
    		final String slot = getProperty (PROP.SLOT + "." + cnt); 
    		if (StringUtil.isEmpty (slot)) { 
    			break; 
    		}
    		slots.add (slot); 
    	}
    	
        return;
    }

    protected List<String> slots; 
    
    public
    Alteration intercept (final HttpServletRequest req, final HttpServletResponse res, DispatchContext dc)
        throws IOException
    {
        for (final String slot : slots) {
            req.setAttribute (JspWidgetUtil.ATTR.SLOT + slot, new WidgetContainer ());
        }
        return NO_ALTERATION;
    }
}

// EOF