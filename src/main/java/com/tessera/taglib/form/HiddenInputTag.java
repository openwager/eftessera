package com.tessera.taglib.form;

import java.io.*;

import javax.servlet.jsp.*;

/**
 * 
 * @author crawford
 *
 */

public class HiddenInputTag
	extends InputTag
{
    public
    HiddenInputTag ()
    {
    	return; 
    }
	
	private boolean showIfNull = true;
    public boolean getShowIfNull () { return showIfNull; } 
    public void setShowIfNull (final boolean showIfNull) { this.showIfNull = showIfNull; return; } 

    /**
     * 
     */
    
    @Override
    public 
    void doTag() 
    	throws IOException, JspException 
    {
		final JspWriter out = this.getJspContext ().getOut ();

		if (!getMultiple ()) {

			String value = resolveValue ();
			if (value != null || getDynamicAttr ("value") != null || getShowIfNull ()) {
				out.append ("<input");
				writeAttribute (out, "type", "hidden");
				writeAttribute (out, "name", getName ());
				if (value != null) {
					writeAttribute (out, "value", value);
				}
				writeDynamicAttributes (out);
				out.append (" />");
			}
		} else {
			final String[] values = resolveValues ();
			if (values != null && values.length > 0) {
				for (String val : values) {
					out.append ("<input");
					writeAttribute (out, "type", "hidden");
					writeAttribute (out, "name", getName ());
					writeAttribute (out, "value", val);
					writeDynamicAttributes (out);
					out.append (" />");
				}
			} else if (getDynamicAttr ("value") != null) {
				out.append ("<input");
				writeAttribute (out, "type", "hidden");
				writeAttribute (out, "name", getName ());
				Object o = getDynamicAttr ("value");
				String val = o != null ? o.toString () : null;
				writeAttribute (out, "value", val);
				writeDynamicAttributes (out);
				out.append (" />");
			}
		}
		
		return; 
	}
}

// EOF
