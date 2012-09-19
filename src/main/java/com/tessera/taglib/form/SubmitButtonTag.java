package com.tessera.taglib.form;

import java.io.*;

import javax.servlet.jsp.*;

/**
 * 
 * @author crawford
 *
 */

public class SubmitButtonTag
	extends InputTag
{
	public
	SubmitButtonTag ()
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

		out.append ("<button");
		writeAttribute (out, "type", "submit");
		if (getName () != null) {
			writeAttribute (out, "name", getName ());
			writeAttribute (out, "value", getValue ());
		}
		writeDynamicAttributes (out);
		out.append (">");
		out.append (getValue ());
		out.append ("</button>");
		return; 
	}	
}

// EOF