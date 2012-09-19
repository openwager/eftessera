package com.tessera.taglib.jsp;

import java.io.*;
import java.util.*;

import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import com.tessera.intercept.jsp.*;

/**
 * 
 * @author crawford
 *
 */

@SuppressWarnings("serial")
public class JavascriptsTag
    extends TagSupport
{
    public
    JavascriptsTag ()
    {
        return;
    }

    @Override
	public
    int doEndTag ()
        throws JspException
    {
        try {
            emitJavascripts (pageContext);
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
    void emitJavascripts (final PageContext pageContext)
        throws IOException
    {
        final HttpServletRequest req = (HttpServletRequest) pageContext.getRequest ();
        final Set<String> paths = AddJavascriptsInterceptor.getJavascripts (req, false);

        if (paths != null) {
            final JspWriter out = pageContext.getOut ();
            for (final String path : paths) {
                out.println ("<script language=\"javascript\" type=\"text/javascript\" src=\"" + path + "\"></script>");
            }
        }
        return;
    }
}
