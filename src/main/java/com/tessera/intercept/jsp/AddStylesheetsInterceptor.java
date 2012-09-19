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

public class AddStylesheetsInterceptor
	extends InterceptorSupport
{
	public static class StylesheetEntry 
	{
        public final String path;
        public final String media;
        public final String context; 
        public final boolean absolute; 
        
        public StylesheetEntry (String path, String media, String context)
        {
            this.path = path;
            this.media = media;
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
	
    public static class StylesheetData
    {
        public final String path;
        public final String media;
        
        public StylesheetData(String path, String media)
        {
            this.path = path;
            this.media = media;
            return; 
        }
    }


    public
    AddStylesheetsInterceptor (final Map<String, String> props)
    {
    	super (props);
    	return;
    }

    /**
     * @see InterceptorSupport#init
     */

    @Override
    public
    void init ()
    	throws Exception
    {
    	super.init (); 
    	
        try {
            int cnt = 0;
            while (true) {
                final String path = getProperty ("path." + cnt);
                if (StringUtil.isEmpty (path)) {
                    break;
                }
            	final String context = getProperty ("context." + cnt);   // can be null
                final String media = getProperty ("media." + cnt); // can be null 
                sheets.add (new StylesheetEntry(path, media, context));
                cnt ++;
            }

            return;
        } catch (final Throwable t) {
            t.printStackTrace ();
        }
    }

    /**
     * Contains the list of stylesheet to be added.
     */

    protected List<StylesheetEntry> sheets = new ArrayList <StylesheetEntry> ();

    /**
     * Get the stylesheets configured for this request.
     * @param req
     * @param create
     * @return a set of stylesheet descriptors for this request, presumably to be rendered
     */

    @SuppressWarnings("unchecked")
    public static
    Set <StylesheetData> getStylesheets (final HttpServletRequest req, final boolean create)
    {
        Object obj = req.getAttribute (JspWidgetUtil.ATTR.STYLESHEETS);
        if (obj == null) {
            if (create) {
                obj = new LinkedHashSet <Pair<String, String>> ();
                req.setAttribute (JspWidgetUtil.ATTR.STYLESHEETS, obj);
            } else {
                return null;
            }
        }
        return (Set <StylesheetData>) obj;
    }
    
    public
    Alteration intercept (final HttpServletRequest req, final HttpServletResponse res, DispatchContext dc)
        throws IOException
    {
        if (sheets.size () > 0) {
            final Set <StylesheetData> output = getStylesheets (req, true);
            for (final StylesheetEntry sheet: sheets) {
                final String uri = sheet.getActual (req);
                final StylesheetData sdata = new StylesheetData (uri, sheet.media);  
            	output.add (sdata);
            }
        }
        return NO_ALTERATION;
    }    
}

// EOF
