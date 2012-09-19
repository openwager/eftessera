package com.tessera.taglib.jsp;

import java.io.*;

import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import org.apache.log4j.*;

import com.tessera.intercept.jsp.*;

/**
 * 
 * @author crawford
 *
 */

@SuppressWarnings("serial")
public class SlotTag
    extends TagSupport
{
    private static final Logger logger = Logger.getLogger (SlotTag.class.getName ());

    public
    SlotTag ()
    {
        return;
    }

    protected String name;
    public String getName () { return this.name; }
    public void setName (final String name) { this.name = name; return; }

    protected String defaultContent;
    public String getDefaultContent () { return this.defaultContent; }
    public void setDefaultContent (final String defaultContent) { this.defaultContent = defaultContent; return; }

    /**
     * @see javax.servlet.jsp.tagext.Tag#doEndTag()
     */

    @Override
	public
    int doEndTag ()
        throws JspException
    {
        final HttpServletRequest req = (HttpServletRequest) pageContext.getRequest ();
        final HttpServletResponse res = (HttpServletResponse) pageContext.getResponse ();
        final String slot = JspWidgetUtil.ATTR.SLOT + getName ();
        final Object obj = req.getAttribute (slot);

        try {
            if (obj == null) {
            	final JspWriter out = pageContext.getOut (); 
                if (getDefaultContent () != null) {
                    out.write (getDefaultContent ());
                } else {
                	out.write ("<!-- SLOT IS EMPTY (" + getName () + ") -->");  
                	if (logger.isDebugEnabled()) {
                		logger.debug ("Empty slot: " + getName ());
                	}
                }
            } else {
                final Widget widget = (Widget) obj;
                final JspWriter out = pageContext.getOut ();
                out.flush ();
                widget.render (req, res, out);
            }
        }
        catch (final IOException io_e) {
            throw new JspException (io_e);
        }

        return EVAL_PAGE;
    }
}

// EOF