package com.tessera.taglib.form;

import java.io.*;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import org.apache.log4j.*;

import com.tessera.intercept.form.*;

/**
 * 
 * @author crawford
 *
 */

public class IfErrorTag
	extends SimpleTagFormSupport
{
	private static final Logger logger = Logger.getLogger (IfErrorTag.class); 

	/**
	 * Default constructor. 
	 */
	
	public
	IfErrorTag ()
	{
		return; 
	}

	public static final AttributeScope DEFAULT_SCOPE = AttributeScope.PAGE; 
	private AttributeScope scope = DEFAULT_SCOPE;  
	public String getScope () { return this.scope.name (); } 
	public void setScope (final String scope) { this.scope = AttributeScope.valueOf (scope); return; }  

	public static final String DEFAULT_NAME = null; 
	protected String name = DEFAULT_NAME; 
	public String getName () { return this.name; } 
	public void setName (final String name) { this.name = name; return; } 
	
	public static final String DEFAULT_VAR = null; 
	protected String var = DEFAULT_VAR;
	public String getVar () { return this.var; } 
	public void setVar (final String var) { this.var = var; return; } 
	
	protected boolean global; 
	public boolean getGlobal () { return this.global; } 
	public void setGlobal (final boolean global) { this.global = global; return; } 
	
    @Override
    public
    void doTag ()
        throws JspException
    {
        final PageContext pageContext = (PageContext) getJspContext ();
        final String var = getVar();
        boolean output = false;

        try {
            final Form form = getForm();
            if (form != null) {
                if (getGlobal () && form.getGlobalErrors () != null && form.getGlobalErrors().size () > 0) {
                    if (var != null) {
                            scope.set (pageContext, var, form.getGlobalErrors());
                    }
                    output = true;
                } else if (form.getError (getName ()) != null) {
                    if (var != null) {
                            scope.set (pageContext, var, form.getError(getName()));
                    }
                    output = true;
                }

                if (output) {
                    final JspFragment body = getJspBody();
                    try {
                        if (body != null) {
                            pushTag();
                            body.invoke(null);
                            popTag();
                        }
                    }
                    catch (final IOException io_e) {
                        throw new JspException (io_e.getMessage (), io_e);
                    }
                }
            }
        }
        catch (ClassCastException cc_e) {
                logger.warn ("\"" + getForm() + "\" attribute is not a Form.");
        }
        
        return; 
    }
}

// EOF
