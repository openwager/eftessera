package com.tessera.intercept.jsp;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

//import com.tessera.dispatch.*;

/**
 * 
 * @author crawford
 *
 */

public class JspWidget
	implements Widget
{
	public
	JspWidget (final String path)
	{
		setPath (path);
		return;
	}
	
	protected String path;
	
	public
	String getPath ()
	{
		return this.path;
	}
	
	public
	void setPath (final String path)
	{
		this.path = path;
		return;
	}
	
	public
	void render (final HttpServletRequest req, final HttpServletResponse res, final Writer out)
		throws IOException
	{
		// Use the request dispatcher to delegate the render operation to the
		// actual JSP.
		
		final RequestDispatcher rd = req.getRequestDispatcher (getPath ());
//		final DispatchContext dc = DispatchContext.getDispatchContext (req);
//		final Audit audit = dc.getAudit ();
		
		//final Audit audit = LudwigUtil.getAudit (req);
//		audit.push ("Rendering '" + getPath () + "'");
		
		try {
		    rd.include (req, res);
		}
		catch (final Throwable t) {
//		    audit.log ("Exception: " + t.getMessage ());
		    throw new IOException (t.getMessage ());
		}
		finally {
//		    audit.pop ();
		}
		
		return;
	}
	
	@Override
	public
	String toString ()
	{
		String s = getClass().getName() + "[";
		s += "path=" + path;
		return s + "]";
	}
}

//EOF
