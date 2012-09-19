package com.tessera.intercept.jsp;

import java.io.*;

import javax.servlet.http.*;

/**
 * 
 * @author crawford
 *
 */

public class NullWidget
	implements Widget
{
	public
	NullWidget ()
	{
		// EMPTY
	    return;
	}
	
	public
	void render (final HttpServletRequest req, final HttpServletResponse res, final Writer out)
	    throws IOException
	{
	    out.write ("<!-- EMPTY WIDGET -->\n");
	    return;
	}
}

// EOF