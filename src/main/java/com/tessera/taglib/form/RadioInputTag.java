package com.tessera.taglib.form;

import java.io.*;

import javax.servlet.jsp.*;

/**
 * 
 * @author crawford
 *
 */

public class RadioInputTag
	extends InputTag
{
	public
	RadioInputTag ()
	{
		return; 
	}
	
	protected String value; 
	public String getValue () { return this.value; } 
	public void setValue (final String value) { this.value = value; return; }
	
	public boolean isDefault; 
	public boolean getIsDefault () { return this.isDefault; } 
	public void setIsDefault (final boolean isDefault) { this.isDefault = isDefault; return; } 
	
    public 
    void doTag() 
    	throws IOException
    {
        final JspWriter out = this.getJspContext ().getOut ();

        out.append ("<input");
        writeAttribute (out, "type", "radio");
        writeAttribute (out, "name", getName ());
        writeAttribute (out, "value", getValue ());

        final String value = resolveValue ();
        if (value != null) {
            if (value.equals (getValue ())) {
                writeAttribute (out, "checked", "true");
            }
        } else if (getIsDefault ()) {
            writeAttribute (out, "checked", "true");
        }

        writeDynamicAttributes(out);
        out.append (" />");
        return; 
    }
}

// EOF