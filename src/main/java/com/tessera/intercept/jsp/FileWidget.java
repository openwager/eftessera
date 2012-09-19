package com.tessera.intercept.jsp;

import java.io.*;

import javax.servlet.http.*;

import com.weaselworks.io.*;

/**
 * 
 * @author crawford
 *
 */

public class FileWidget
	implements Widget
{
	public
	FileWidget (final String path)
	{
		setFile (new File (path));
		return;
	}
	
	public
	FileWidget (final File file)
	{
		setFile (file);
		return;
	}
	
	// The 'File' attribute
	protected File file;
	public File getFile () { return file; }
	public void setFile (final File file) { this.file = file; return; }
	
	public
	void render (final HttpServletRequest req, final HttpServletResponse res, final Writer out)
		throws IOException
	{
		final File file = getFile ();
		
		if (! file.exists ()) {
		    throw new IOException ("File '" + getFile () + "' not found.");
		}
		
		final Reader reader = new FileReader (file);
		IOUtil.copy (reader, out);
		return;
	}
}

//EOF