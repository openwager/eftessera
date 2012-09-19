package com.tessera.taglib.jsp;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import com.tessera.intercept.jsp.JspWidgetUtil;

/**
 * 
 * @author mbarcelo
 *
 */

@SuppressWarnings("serial")
public class LinkTag 
	extends TagSupport 
{
	
	public
	LinkTag ()
	{
		super();
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
            emitLinkTags (pageContext);
        }
        catch (final IOException io_e) {
            throw new JspException (io_e.getMessage (), io_e);
        }

        return EVAL_PAGE;  
    }
    
    @SuppressWarnings("unchecked")
	public static
    void emitLinkTags (final PageContext pageContext)
        throws IOException
    {
        final ServletRequest req = pageContext.getRequest ();
        final JspWriter out = pageContext.getOut ();

        // add custom meta tags
        final Map<String,String> linkTags = (Map<String,String>) req.getAttribute(JspWidgetUtil.ATTR.LINK_CUSTOM);
        if(linkTags != null && !linkTags.isEmpty())
        {
        	final Set<String> keys = linkTags.keySet();
        	for (String key : keys) 
        	{
        		out.print("<link rel=\"" + key + "\" href=\"" + linkTags.get(key) + "\" />\n");
			}
        }
        
        return;
    }
	

}
