package com.tessera.taglib.form;

import java.io.*;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

/**
 * 
 * @author crawford
 *
 */

public class SelectTag
	extends InputTag
{
	public
	SelectTag ()
	{
		return; 
	}
	
	protected String value; 
	public String getValue () { return this.value; } 
	public void setValue (final String value) { this.value = value; return; } 
	
	protected String [] multivalue; 
	public String [] getMultivalue () { return this.multivalue; } 
	public void setMultivalue (final String [] multivalue) { this.multivalue = multivalue; return; } 
	
	protected boolean disabled = false; 
	public boolean getDisabled () { return this.disabled; } 
	public void setDisabled (final boolean disabled) { this.disabled = disabled; return; } 

    public 
    void doTag() 
    	throws IOException, JspException 
    {
        final JspWriter out = this.getJspContext ().getOut ();

        out.append ("<select");
        writeAttribute (out, "name", getName ());
        if (getMultiple ()) {
            writeAttribute (out, "multiple", "true");
        }
        if (getDisabled ()) {
            writeAttribute (out, "disabled", "true");
        }
        writeDynamicAttributes (out);
        out.append (">");

        if (!getMultiple ()) {
            String value = resolveValue ();
            if (value != null) {
                setValue (value);
            }
        } else {
            setMultivalue (resolveValues ());
        }

        pushTag ();

        JspFragment fragment = getJspBody ();
        if (fragment != null) {
            try {
                fragment.invoke (out);
            }
            catch (final Exception e) {
                throw new IOException (e);
            }
        }

        popTag ();
        out.append ("</select>");
        return; 
    }
}

// EOF