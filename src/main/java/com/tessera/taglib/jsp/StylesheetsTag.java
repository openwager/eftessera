package com.tessera.taglib.jsp;

import java.io.*;
import java.util.*;

import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import com.tessera.intercept.jsp.*;
import com.tessera.intercept.jsp.AddStylesheetsInterceptor.StylesheetData;


/**
 * 
 * @author crawford
 *
 */

@SuppressWarnings("serial")
public class StylesheetsTag
    extends TagSupport
{
    public
    StylesheetsTag ()
    {
        return;
    }

    @Override
	public
    int doEndTag ()
        throws JspException
    {
        try {
            emitStylesheets (pageContext);
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
    void emitStylesheets (final PageContext pageContext)
        throws IOException
    {
        final HttpServletRequest req = (HttpServletRequest) pageContext.getRequest ();
        final Set<StylesheetData> sheets = AddStylesheetsInterceptor.getStylesheets (req, false);

        if (sheets != null) {
            final JspWriter out = pageContext.getOut ();
            for (final StylesheetData sheet: sheets) {
                out.println ("<link rel=\"stylesheet\" href=\"" + sheet.path + "\"" + (sheet.media != null ? " media=\"" + sheet.media + "\"" : "") + "/>");
            }
        }
        return;
    }
}

// EOF
