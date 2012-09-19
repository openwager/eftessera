package com.tessera.taglib.jsp;

import java.io.*;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

/**
 * 
 * @author crawford
 *
 */

@SuppressWarnings("serial")
public class HeadTag
    extends TagSupport
{
    public
    HeadTag ()
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
            TitleTag.emitTitle (pageContext);
            MetaTag.emitMetaTags (pageContext);
            LinkTag.emitLinkTags(pageContext);
            StylesheetsTag.emitStylesheets (pageContext);
            JavascriptsTag.emitJavascripts (pageContext); 
        }
        catch (final IOException io_e) {
            throw new JspException (io_e.getMessage (), io_e);
        }

        return EVAL_PAGE;
    }
}

// EOF
