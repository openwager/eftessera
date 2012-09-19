package com.tessera.taglib.form;

import javax.servlet.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import com.tessera.dispatch.Dispatcher;

/**
 * 
 * @author crawford
 *
 */

@SuppressWarnings("serial")
public class IfThrowableTag
	extends BodyTagSupport
{
	public
	IfThrowableTag ()
	{
		return; 	
	}
	
	public static final AttributeScope DEFAULT_SCOPE = AttributeScope.PAGE; 
	private AttributeScope scope = DEFAULT_SCOPE;  
	public String getScope () { return this.scope.name (); } 
	public void setScope (final String scope) { this.scope = AttributeScope.valueOf (scope); return; }  
	
	public static final String DEFAULT_VAR = null; 
	private String var; 
	public String getVar () { return this.var; } 
	public void setVar (final String var) { this.var = var; return; } 
	
	/**
	 * @see BodyTagSupport#doStartTag()
	 */
	
	public
	int doStartTag ()
		throws JspException
	{
        final ServletRequest req = pageContext.getRequest ();
        final Throwable throwable = (Throwable) req.getAttribute (Dispatcher.ATTR.THROWABLE);

        if (getVar () != null) {
           scope.set (pageContext, getVar (), throwable);
        }

        if (throwable != null) {
            return EVAL_BODY_INCLUDE;
        } else {
            return SKIP_BODY;
        }
        
        // NOT REACHED
	}
}

// EOF 
