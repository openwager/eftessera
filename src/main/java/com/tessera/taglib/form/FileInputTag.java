package com.tessera.taglib.form;

import java.io.*;

import javax.servlet.jsp.*;

/**
 * 
 * @author crawford
 *
 */

public class FileInputTag
	extends InputTag
{
	public
	FileInputTag ()
	{
		return; 
	}
	
    public void doTag ()
	    throws IOException
	{
		final JspWriter out = this.getJspContext ().getOut ();
		out.append ("<input");
		writeAttribute (out, "type", "file");
		writeAttribute (out, "name", getName ());
		writeValue (out);
		writeDynamicAttributes (out);
		out.append (" />");
		return;
	}
}

// EOF