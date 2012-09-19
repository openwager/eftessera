package com.tessera.taglib.form;

import java.io.*;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import org.apache.commons.beanutils.*;

/**
 * 
 * @author crawford
 *
 */

public class OptionTag
	extends InputTag
{
	public 
	OptionTag ()
	{
		return; 
	}
	
	protected String value; 
	public String getValue () { return this.value; } 
	public void setValue (final String value) { this.value = value; return; }

	protected String text; 
	public String getText () { return this.text; } 
	public void setText (final String text) { this.text = text; return; } 
	
	protected boolean selected; 
	public boolean getSelected () { return this.selected; } 
	public void setSelected (final boolean selected) { this.selected = selected; return; } 
	
    public 
    void doTag ()
	    throws IOException, JspException
	{
		final JspWriter out = this.getJspContext ().getOut ();
		final JspTag parent = getParentTag ();

		if (parent instanceof OptionsTag) {
			final OptionsTag options = (OptionsTag) parent;
			SelectTag select = options.getSelect ();
			Object optionObject = options.getCurrentOption ();

			String text = null;
			String value = null;
			if (options.getTextProperty () == null) {
				text = optionObject.toString ();
			} else {
				try {
					text = BeanUtils.getProperty (optionObject, options.getTextProperty ());
				} catch (Exception e) {
					throw new JspException (e);
				}
			}
			if (options.getValueProperty () == null) {
				value = optionObject.toString ();
			} else {
				try {
					value = BeanUtils.getProperty (optionObject, options.getValueProperty ());
				} catch (Exception e) {
					throw new JspException (e);
				}
			}

			writeOption (out, value, text, select);
		} else {
			if (getValue () == null) {
				throw new JspException ("An <option> tag not defined by a Collection or an Enum in its parent <options> tag must provide a valueattribute.");
			}
			if (getText () == null) {
				throw new JspException ("An <option> tag not defined by a Collection or an Enum in its parent <options> tag must provide a textattribute.");
			}

			final JspTag tag = findAncestorTag (SelectTag.class);
			if (tag == null) {
				throw new JspException ("An <option> tag must have either an <options> tags as a parent or a <select> tag as an ancestor");
			}
			SelectTag select = (SelectTag) tag;
			writeOption (out, getValue (), getText (), select);
		}
		
		return; 
	}

    /**
     * 
     * @param out
     * @param value
     * @param text
     * @param select
     * @throws IOException
     * @throws JspException
     */
    
	protected
	void writeOption (final JspWriter out, final String value, final String text, final SelectTag select)
	    throws IOException, JspException
	{
		out.append ("<option");
		writeAttribute (out, "value", value);

		if (select != null) {
			if (!select.getMultiple ()) {
				if (select.getValue () != null) {
					if (value.equals (select.getValue ())) {
						writeAttribute (out, "selected", "true");
					}
				} else if (getSelected ()) {
					writeAttribute (out, "selected", "true");
				}
			} else {
				if (select.getMultivalue () != null) {
					if (select.matchValue (value)) {
						writeAttribute (out, "selected", "true");
					}
				} else if (getSelected ()) {
					writeAttribute (out, "selected", "true");
				}
			}
		}

		getDynamicAttrs ().remove ("selected");
		writeDynamicAttributes (out);
		out.append (">");
		if (text != null) {
			writeBody (out, text);
		}
		out.append ("</option>\n");
		return; 
	}
}

// EOF