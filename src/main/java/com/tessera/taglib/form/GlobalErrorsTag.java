package com.tessera.taglib.form;

import java.io.*;
import java.util.*;

import javax.servlet.jsp.*;

import com.tessera.intercept.form.*;

/**
 * 
 * @author crawford
 *
 */

@SuppressWarnings("serial")
public class GlobalErrorsTag
	extends BodyTagFormSupport
{	
	public
	GlobalErrorsTag ()
	{
		return; 
	}

	public static final AttributeScope DEFAULT_SCOPE = AttributeScope.PAGE; 
	private AttributeScope scope = DEFAULT_SCOPE;  
	public String getScope () { return this.scope.name (); } 
	public void setScope (final String scope) { this.scope = AttributeScope.valueOf (scope); return; }  

	public static final String DEFAULT_VAR = null; 
	protected String var = DEFAULT_VAR;
	public String getVar () { return this.var; } 
	public void setVar (final String var) { this.var = var; return; } 

	protected List<String> errors; 
	protected int index; 
	
    @Override
    public
    int doStartTag ()
        throws JspException
    {
        final Form form = getForm ();
        if (form != null) {
            index = 0;
            errors = form.getGlobalErrors ();
            if (errors != null && errors.size () > 0) {
            	pushTag ();
            	return EVAL_BODY_BUFFERED;
            }
        }
        return SKIP_BODY;
    }

    @Override
    public
    void doInitBody ()
      throws JspException
    {
        pageContext.setAttribute (getVar (), errors.get (index));
        return;
    }

    @Override
    public
    int doAfterBody ()
        throws JspException
    {
        index ++;
        if (index < errors.size ()) {
            pageContext.setAttribute (getVar (), errors.get (index));
            return EVAL_BODY_BUFFERED;
        } else {
            return SKIP_BODY;
        }

        // NOT REACHED
    }

    @Override
    public
    int doEndTag () 
    	throws JspException
    {
        popTag ();
        try {
            if (bodyContent != null) {
                bodyContent.writeOut (bodyContent.getEnclosingWriter ());
            }
        }
        catch (final IOException io_e) {
            throw new JspException (io_e);
        }
        finally {
            errors = null;
        }
        return EVAL_PAGE;
    }	
}

// EOF