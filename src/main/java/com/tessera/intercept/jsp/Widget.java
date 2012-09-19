package com.tessera.intercept.jsp;

import java.io.*;

import javax.servlet.http.*;

/**
 * 
 * @author crawford
 *
 */

public interface Widget
{
	/**
	 * 
	 * @param req
	 * @param res
	 * @param out
	 * @throws IOException
	 */
	
    public
    void render (HttpServletRequest req, final HttpServletResponse res, Writer out)
        throws IOException;
}

// EOF