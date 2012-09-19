package com.tessera.taglib.form;

import java.io.*;

import javax.servlet.jsp.*;

/**
 * 
 * @author crawford
 *
 */

public class ImageInputTag
	extends InputTag
{
	public
	ImageInputTag ()
	{
		return; 
	}

	protected String value; 
	public String getValue () { return this.value; } 
	public void setValue (final String value) { this.value = value; return; } 
	
    public void doTag ()
	    throws IOException, JspException
	{
		final JspWriter out = this.getJspContext ().getOut ();

		out.append ("<input");
		writeAttribute (out, "type", "image");
		if (getName () != null) {
			writeAttribute (out, "name", getName ());
		}
		writeAttribute (out, "value", getValue ());
		writeDynamicAttributes (out);
		out.append (" />");
		return;
	}
}

// EOF
