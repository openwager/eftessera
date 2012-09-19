package com.tessera.taglib.form;

import java.io.*;
import java.util.*;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import org.apache.commons.beanutils.*;

import com.weaselworks.util.*;

/**
 * 
 * @author crawford
 *
 */

public class OptionsTag
	extends InputTag
{
	public
	OptionsTag ()
	{
		return; 
	}

	protected Collection<?> collection; 
	public Collection<?> getCollection() { return this.collection;  } 
	public void setCollection (final Collection<?> collection) { this.collection = collection; return; } 

	protected Object [] array; 
	public Object [] getArray () { return this.array; } 
	public void setArray (final Object [] array) { this.array = array; return; } 
	
	protected String enumClass; 
	public String getEnumClass () { return this.enumClass; } 
	public void setEnumClass (final String enumClass) { this.enumClass = enumClass; return; } 
	
	protected String valueProperty; 
	public String getValueProperty () { return this.valueProperty; } 
	public void setValueProperty (final String valueProperty) { this.valueProperty = valueProperty; return; } 
	
	protected String textProperty; 
	public String getTextProperty () { return this.textProperty; } 
	public void setTextProperty (final String textProperty) { this.textProperty = textProperty; return; } 
	
	protected Object currentOption; 
	public Object getCurrentOption () { return this.currentOption; } 
	public void setCurrentOption (final Object currentOption) { this.currentOption = currentOption; return; } 
	
	protected SelectTag select; 
	public SelectTag getSelect () { return this.select; } 
	public void setSelect (final SelectTag select) { this.select = select; return; } 
	
	/**
	 * 
	 */

    public void doTag ()
	    throws IOException, JspException
	{
		final JspWriter out = this.getJspContext ().getOut ();

		if (!(getParentTag () instanceof SelectTag)) {
			throw new JspException ("options tag must have a parent select tag.");
		}

		final SelectTag select = (SelectTag) getParentTag ();
		setSelect (select);
		final String selectValue = select.getValue ();

		final JspFragment fragment = getJspBody ();

		if (getCollection () != null) {
			if (fragment == null) {
				for (final Object o : getCollection ()) {
					writeOption (out, o, selectValue);
				}
			} else {
				for (final Object o : getCollection ()) {
					setCurrentOption (o);
					pushTag ();
					fragment.invoke (out);
					popTag ();
				}
			}
		} else if (getArray () != null) {
			if (fragment == null) {
				for (final Object o : getArray ()) {
					writeOption (out, o, selectValue);
				}
			} else {
				for (final Object o : getArray ()) {
					setCurrentOption (o);
					pushTag ();
					fragment.invoke (out);
					popTag ();
				}
			}
		} else if (getEnumClass () != null) {
			Object[] values;
			try {
				values = EnumUtil.getEnumValues (getEnumClass ());
			} catch (Exception e) {
				throw new IOException (e);
			}

			if (fragment == null) {
				for (final Object o : values) {
					writeOption (out, o, selectValue);

				}
			} else {
				for (final Object o : values) {
					setCurrentOption (o);
					pushTag ();
					fragment.invoke (out);
					popTag ();
				}
			}
		} else if (fragment != null) {
			pushTag ();
			fragment.invoke (out);
			popTag ();
		}

		return; 
	}

    /**
     * 
     * @param out
     * @param optionObject
     * @param selectValue
     * @throws IOException
     */
    
    protected void writeOption (JspWriter out, Object optionObject, String selectValue)
	    throws IOException
	{
		String name;
		String value;
		if (getTextProperty () == null) {
			name = optionObject.toString ();
		} else {
			try {
				name = BeanUtils.getProperty (optionObject, getTextProperty ());
			} catch (Exception e) {
				throw new IOException (e);
			}
		}
		if (getValueProperty () == null) {
			value = optionObject.toString ();
		} else {
			try {
				value = BeanUtils.getProperty (optionObject, getValueProperty ());
			} catch (Exception e) {
				throw new IOException (e);
			}
		}

		out.append ("<option");
		writeAttribute (out, "value", value);
		if (value.equals (selectValue)) {
			writeAttribute (out, "selected", "true");
		}
		out.append (">");
		out.append (name);
		out.append ("</option>\n");
	}
}

// EOF