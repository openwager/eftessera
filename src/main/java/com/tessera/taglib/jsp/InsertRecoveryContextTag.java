package com.tessera.taglib.jsp;

import java.io.*;

import javax.servlet.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import com.tessera.intercept.util.*;

/**
 * 
 * @author crawford
 *
 */

@SuppressWarnings("serial")
public class InsertRecoveryContextTag
	extends TagSupport
{
	public
	InsertRecoveryContextTag ()
	{
		return; 
	}


    @Override
    public
    int doEndTag ()
        throws JspException
    {
    	try { 
		    final JspWriter out = pageContext.getOut ();
		    final ServletRequest req = pageContext.getRequest (); 
		    RecoverableRedirectInterceptor.addRecoveryContext (out, req); 
	        return EVAL_PAGE;
    	}
    	catch (final IOException io_e) { 
    		throw new JspException (io_e.getMessage (), io_e); 
    	}
    	
    	// NOT REACHED
    }	
}

// EOF