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

public class AddJavascriptsInterceptor
	extends InterceptorSupport
{
    public class JavascriptData
    {
        public final String path;
        public final String context; 
        public boolean absolute; 
        
        public JavascriptData (String path, String context)
        {
            this.path = path;
            this.context = context;
            this.absolute = path.contains (":");
            return; 
        }
        
        public 
        String getActual (final HttpServletRequest req)
        {
        	if (absolute) { 
        		return path; 
        	} else { 
        		return req.getContextPath () + path; 
        	}
        	// NOT REACHED
        }
    }

    public
	AddJavascriptsInterceptor (final Map<String, String> props)
	  {
	      super (props);
	      return;
	  }
		
	@Override
	public
	void init ()
		throws Exception
	{
		super.init (); 
	    int cnt = 0;
	
	    while (true) {
	        final String path = getProperty ("path." + cnt);
	        if (StringUtil.isEmpty (path)) {
	            break;
	        }
	        final String context = getProperty ("context." + cnt);  // can be null 
	        final JavascriptData data = new JavascriptData (path, context); 
	        paths.add (data);
	        cnt ++;
	    }
	
	    return;
	}
	
	/**
	 * Contains the list of stylesheet paths to be addded.
	 */
	
	protected List <JavascriptData> paths = new ArrayList <JavascriptData> ();
	
	/**
	 *
	 * @param req
	 * @param create
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public static
	Set<String> getJavascripts (final HttpServletRequest req, final boolean create)
	{
	    Object obj = req.getAttribute (JspWidgetUtil.ATTR.JAVASCRIPT);
	    if (obj == null) {
	        if (create ) {
	            obj = new LinkedHashSet<String> ();
	            req.setAttribute (JspWidgetUtil.ATTR.JAVASCRIPT, obj);
	        } else {
	            return null;
	        }
	    }
	    return (Set <String>) obj;
	}
	
	public
	Alteration intercept (final HttpServletRequest req, final HttpServletResponse res, DispatchContext dc)
        throws IOException
    {
        if (paths.size () > 0) {
            final Set <String> output = getJavascripts (req, true);
            for (final JavascriptData path : paths) {
                final String uri = path.getActual (req); 
            	output.add (uri);
            }
        }
        return NO_ALTERATION;
    }
}

// EOF