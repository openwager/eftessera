package com.tessera.taglib.form;

import java.io.*;

import javax.servlet.jsp.*;

/**
 * 
 * @author crawford
 *
 */

public class TextAreaTag
	extends InputTag
{
	public
	TextAreaTag ()
	{
		return; 
	}
	
    public 
    void doTag() 
    	throws IOException, JspException 
    {
        final JspWriter out = this.getJspContext ().getOut ();

        out.append ("<textarea");
        writeAttribute (out, "name", getName ());
        writeDynamicAttributes(out);
        out.append (" />");

        String value = resolveValue ();
        if (value == null) {
            final Object o = getDynamicAttr ("value");
            if (o != null) {
                value = o.toString ();
            }
        }
        if (value != null) {
            writeBody (out, value);
        }

        out.append ("</textarea>");
        return; 
    }	
}

// EOF
