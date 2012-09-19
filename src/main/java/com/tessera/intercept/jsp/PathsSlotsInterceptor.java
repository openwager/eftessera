package com.tessera.intercept.jsp;

import java.io.*;
import java.util.*;

import javax.servlet.http.*;

import com.tessera.intercept.*;
import com.weaselworks.util.*;

/**
 * 
 * @author crawford
 *
 */

abstract public class PathsSlotsInterceptor
	extends InterceptorSupport
{
	public
	PathsSlotsInterceptor (final Map<String, String> props)
	{
	    super (props);
	    return;
	}
	
	public interface PROP
	{
	    public String PATH = "path";
	    public String SLOT = "slot";
	}
	
	@Override
	public
	void init ()
	    throws Exception
	{
		super.init (); 
        for (int cnt = 0; ; cnt ++) { 
            final String s = getProperty (PROP.SLOT + "." + cnt);
            if (StringUtil.isEmpty (s)) {
                    break;
            }
            final String p = getProperty (PROP.PATH + "." + cnt);
            if (StringUtil.isEmpty (p)) {
                    throw new IllegalArgumentException ("Missing path (" + PROP.PATH + "." + cnt + ").");
            }
            slots.add (s);
            paths.add (p);
        }

        return;
	}
	
	protected final List<String> paths = new ArrayList<String> (); 
	public List<String> getPaths () { return this.paths; } 
	
	protected final List<String> slots = new ArrayList<String> (); 
	public List<String> getSlots () { return this.slots; } 	

    protected
    WidgetContainer getContainer (final HttpServletRequest req, final String slot)
        throws IOException
    {
        Object obj = req.getAttribute (slot);
        if (obj == null) {
            obj = new WidgetContainer ();
            req.setAttribute (slot, obj);
        } else if (! (obj instanceof WidgetContainer)) {
            throw new IOException ("Slot '" + slot + "' contains a " + obj.getClass().getName ());
        }
        return (WidgetContainer) obj;
    }
}

// EOF