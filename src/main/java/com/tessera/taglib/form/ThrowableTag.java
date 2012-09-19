package com.tessera.taglib.form;

import javax.servlet.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import org.apache.log4j.*;

import com.tessera.dispatch.*; 

/**
 * 
 * @author Lee Crawford (lee.crawford@gmail.com)
 */

@SuppressWarnings("serial")
public class ThrowableTag
	extends TagSupport
{
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger (ThrowableTag.class); 
	
	public
	ThrowableTag ()
	{
		return; 
	}
	
	public static final AttributeScope DEFAULT_SCOPE = AttributeScope.PAGE; 
	private AttributeScope scope = DEFAULT_SCOPE;  
	public String getScope () { return this.scope.name (); } 
	public void setScope (final String scope) { this.scope = AttributeScope.valueOf (scope); return; }  

	public static final String DEFAULT_VAR = null; 
	protected String var = null; 
	public String getVar () { return this.var; } 
	public void setVar (final String var) { this.var = var; return; } 
	
	@Override
	public
	int doEndTag ()
		throws JspException
	{
        final ServletRequest req = pageContext.getRequest ();
        final Throwable throwable = (Throwable) req.getAttribute (Dispatcher.ATTR.THROWABLE);
        scope.set (pageContext, getVar (), throwable);
        return EVAL_PAGE;
	}
}

// EOF