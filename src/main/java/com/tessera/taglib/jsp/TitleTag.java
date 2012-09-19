package com.tessera.taglib.jsp;

import java.io.*;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import com.tessera.intercept.jsp.*;

/**
 * 
 * @author crawford
 *
 */

@SuppressWarnings("serial")
public class TitleTag
    extends TagSupport
{
    public
    TitleTag ()
    {
        return;
    }

    @Override
	public
    int doEndTag ()
        throws JspException
    {
        try {
            emitTitle (pageContext); 
        }
        catch (final IOException io_e) {
            throw new JspException (io_e);
        }
        return EVAL_PAGE;
    }

    /**
     *
     * @param pageContext
     * @throws IOException
     */

    public static
    void emitTitle (final PageContext pageContext)
        throws IOException
    {
        final JspWriter out = pageContext.getOut ();
        final String title = (String) pageContext.getRequest ().getAttribute (SetTitleInterceptor.ATTR.TITLE);

        if (title != null) {
            out.println ("<title>" + title + "</title>");
        }
        return;
    }
}

// EOF