package com.tessera.taglib.form;

import java.io.*;

import javax.servlet.jsp.*;

import org.apache.log4j.*;

import com.tessera.intercept.form.*;

/**
 * 
 * @author crawford
 *
 */

@SuppressWarnings("serial")
public class ErrorTag
	extends BodyTagFormSupport
{
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger (ErrorTag.class); 

	public
	ErrorTag ()
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

    @Override
    public
    int doEndTag ()
        throws JspException
    {
        final Form form = getForm ();
        if (form != null) {
                final String errmsg = form.getError (getName ());
                if (var != null) {
                    scope.set (pageContext, var, errmsg);
                } else {
                    if (errmsg != null) {
                        try {
                            final JspWriter writer = pageContext.getOut ();
                            writer.write (errmsg);
                        }
                        catch (final IOException io_e) {
                            throw new JspException (io_e);
                        }
                    }
                }
        }

        return EVAL_PAGE;
    }	
}

// EOF 
