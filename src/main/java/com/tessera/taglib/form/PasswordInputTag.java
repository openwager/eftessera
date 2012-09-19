package com.tessera.taglib.form;

import java.io.*;

import javax.servlet.jsp.*;

/**
 * 
 * @author crawford
 *
 */

public class PasswordInputTag
	extends InputTag
{
	public
	PasswordInputTag ()
	{
		return; 
	}
	
    private boolean showValue = false;
    public boolean getShowValue () { return showValue; }
    public void setShowValue (boolean showValue) { this.showValue = showValue; return; }

    /**
     * 
     * @throws IOException
     */
    
    public
    void doTag() 
    	throws IOException 
    {
        final JspWriter out = this.getJspContext ().getOut ();
        out.append ("<input");
        writeAttribute (out, "type", "password");
        writeAttribute (out, "name", getName ());
        if (getShowValue ()) {
            writeValue (out);
        } else {
            getDynamicAttrs ().remove ("value");
        }
        writeDynamicAttributes(out);
        out.append (" />");
        return;
    }
}
// EOF