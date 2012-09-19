package com.tessera.taglib.form;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import org.apache.commons.beanutils.*;

import com.tessera.intercept.form.*;
import com.weaselworks.util.*;

/**
 * 
 * @author crawford
 * 
 */

abstract public class InputTag
    extends SimpleTagFormSupport
    implements DynamicAttributes
{
	protected InputTag ()
	{
		return;
	}

	protected String name;

	public String getName ()
	{
		return this.name;
	}

	public void setName (final String name)
	{
		this.name = name;
		return;
	}

	protected boolean multiple;

	public boolean getMultiple ()
	{
		return this.multiple;
	}

	public void setMultiple (final boolean multiple)
	{
		this.multiple = multiple;
		return;
	}

	protected String prop;

	public String getProperty ()
	{
		return this.prop;
	}

	public void setProperty (final String prop)
	{
		if (StringUtil.isEmpty (prop)) {
			this.prop = null;
		} else {
			this.prop = prop;
		}
		return;
	}

	private Map<String, Object> dynamicAttrs = new HashMap<String, Object> ();

	public void setDynamicAttribute (final String uri, final String name, final Object value)
	    throws JspException
	{
		if (uri == null) {
			dynamicAttrs.put (name, value);
		}
		return;
	}

	protected 
	Map<String, Object> getDynamicAttrs ()
	{
		return dynamicAttrs;
	}

	protected 
	Object getDynamicAttr (final String name)
	{
		return dynamicAttrs.get (name);
	}

	public
	void setAttributes (final Map<String, Object> attributes)
	{
		for (final String key : attributes.keySet ()) {
			dynamicAttrs.put (key, attributes.get (key));
		}
		return;
	}

	protected 
	void writeBody (final JspWriter out, final String body)
	    throws IOException
	{
		out.print (HTMLUtil.escape (body));
		return;
	}

	protected 
	void writeAttribute (final JspWriter out, final String attr, final String value)
	    throws IOException
	{
		out.print (' ');
		out.print (attr);
		out.print ("=\"");
		if (value != null) {
			out.print (HTMLUtil.escape (value));
		}
		dynamicAttrs.remove (attr);
		out.print ('"');
		return; 
	}

	protected 
	void writeDynamicAttributes (final JspWriter out)
	    throws IOException
	{
		for (String key : dynamicAttrs.keySet ()) {
			out.print (' ');
			out.print (key);
			out.print ("=\"");
			Object o = dynamicAttrs.get (key);
			if (o != null) {
				out.print (HTMLUtil.escape (o.toString ()));
			}
			out.print ('"');
		}
		return; 
	}

	/**
	 * 
	 * @param out
	 * @throws IOException
	 */
	
	protected
	void writeValue (final JspWriter out)
	    throws IOException
	{
		final String value = resolveValue ();
		if (value != null) {
			writeAttribute (out, "value", value);
		}
		return; 
	}

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	
	protected 
	String resolveValue ()
	    throws IOException
	{
		final String param = getName ();
		final String property = getProperty () != null ? getProperty () : getName ();

		final ServletRequest req = ((PageContext) getJspContext ()).getRequest ();
		final Form form = getForm ();
		if (form == null) {
			return null;
		}

		boolean getFromParameters = true;
		if (form != null) {
			if (!form.getBound ()) {
				getFromParameters = false;
			}
		}

		String value = null;
		if (getFromParameters) {
			value = req.getParameter (param);
		} else if (form != null) {
			try {
				value = BeanUtils.getProperty (form, property);
			} catch (final Exception e) {
				throw new IOException ("Failed to get specifiedproperty from form bean.");
			}
		}

		return value;
	}

	/**
	 * 
	 * @return
	 * @throws JspException
	 */
	
	protected 
	String[] resolveValues ()
	    throws JspException
	{
		String param = getName ();
		String property = getProperty () != null ? getProperty () : getName ();

		final ServletRequest req = ((PageContext) getJspContext ()).getRequest ();
		final Form form = getForm ();
		if (form == null) {
			return null;
		}

		boolean getFromParameters = true;
		if (form != null) {
			if (!form.getBound ()) {
				getFromParameters = false;
			}
		}

		String[] values = null;
		if (getFromParameters) {
			values = (String[]) req.getParameterMap ().get (param);
			if (values == null) {
				values = new String[0];
			}
		} else if (form != null) {
			try {
				values = BeanUtils.getArrayProperty (form, property);
			} catch (Exception e1) {
				try {
					Object o = PropertyUtils.getProperty (form, property);
					values = new String[1];
					values[0] = o.toString ();

				} catch (Exception e) {
					throw new JspException ("Failed to get specified property from form bean.", e);
				}
			}
		}

		return values;
	}

	/**
	 * 
	 * @param value
	 * @return
	 * @throws JspException
	 */
	
	protected boolean matchValue (final String value)
	    throws JspException
	{
		final String param = getName ();
		final String property = getProperty () != null ? getProperty () : getName ();

		final ServletRequest req = ((PageContext) getJspContext ()).getRequest ();
		final Form form = getForm ();
		if (form == null) {
			return false;
		}

		boolean getFromParameters = true;
		if (form != null) {
			if (! form.getBound ()) {
				getFromParameters = false;
			}
		}

		String[] values = null;
		if (getFromParameters) {
			values = (String[]) req.getParameterMap ().get (param);
		} else if (form != null) {
			try {
				values = BeanUtils.getArrayProperty (form, property);
			} catch (Exception e1) {
				try {
					Object o = PropertyUtils.getProperty (form, property);
					values = new String[1];
					values[0] = o.toString ();

				} catch (Exception e) {
					throw new JspException ("Failed to get specified property from form bean.", e);
				}
			}
		}

		if (values != null) {
			final Set<String> valueSet = new HashSet<String> ();
			for (String v : values) {
				valueSet.add (v);
			}
			return valueSet.contains (value);
		} else {
			return false;
		}
		
		// NOT REACHED
	}
}

// EOF