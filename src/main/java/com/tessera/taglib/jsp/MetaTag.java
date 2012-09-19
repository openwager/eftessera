package com.tessera.taglib.jsp;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import com.tessera.intercept.jsp.*;

/**
 * 
 * @author crawford
 *
 */

@SuppressWarnings("serial")
public class MetaTag  
    extends TagSupport
{
    public
    MetaTag ()
    {
        return;
    }

    /**
     * @see javax.servlet.jsp.tagext.Tag#doEndTag()
     */

    @Override
	public
    int doEndTag ()
        throws JspException
    {
        try {
            emitMetaTags (pageContext);
        }
        catch (final IOException io_e) {
            throw new JspException (io_e.getMessage (), io_e);
        }

        return EVAL_PAGE;  
    }

    /**
     *
     * @param pageContext
     * @throws IOException
     */

    @SuppressWarnings("unchecked")
	public static
    void emitMetaTags (final PageContext pageContext)
        throws IOException
    {
        final ServletRequest req = pageContext.getRequest ();
        final JspWriter out = pageContext.getOut ();
        String val ;

        // Add the robots tag

        val = (String) req.getAttribute (JspWidgetUtil.ATTR.ROBOTS);
        if (val != null) {
            out.println ("<meta name=\"robots\" content=\"" + val + "\">");
        }

        // add the description tag

        val = (String) req.getAttribute (JspWidgetUtil.ATTR.DESCRIPTION);
        if (val != null) {
            out.println ("<meta name=\"description\" content=\"" + val + "\">");
        }

        // add the keywords

        final List<String> keywords = (List<String>) req.getAttribute (JspWidgetUtil.ATTR.KEYWORDS);
        if (val != null) {
            out.print ("<meta name=\"keywords\" content=\"");
            for (final String keyword : keywords) {
                out.print (keyword);
                out.print (',');
            }
            out.println ("\"/>");
        }
        
        // add custom meta tags
        final Map<String,String> metaTags = (Map<String,String>) req.getAttribute(JspWidgetUtil.ATTR.META_CUSTOM);
        if(metaTags != null && !metaTags.isEmpty())
        {
        	final Set<String> keys = metaTags.keySet();
        	for (String key : keys) 
        	{
        		out.print("<meta name=\"" + key + "\" content=\"" + metaTags.get(key) + "\" />\n");
			}
        }
        
        
        return;
    }
}

// EOF
