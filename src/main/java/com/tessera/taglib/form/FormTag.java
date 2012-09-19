package com.tessera.taglib.form;

import java.io.*;
import java.util.*;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import com.tessera.intercept.form.*;
import com.weaselworks.util.*;

/**
 * 
 * @author crawford
 *
 */

@SuppressWarnings("serial")
public class FormTag
	extends BodyTagFormSupport
		implements DynamicAttributes
{
	public FormTag ()
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

	private Map<String, Object> dynamicAttrs = new java.util.HashMap<String, Object> ();

	public void setDynamicAttribute (final String uri, final String name, final Object value)
	    throws JspException
	{
		if (uri == null) {
			dynamicAttrs.put (name, value);
		}
		return;
	}

	protected Map<String, Object> getDynamicAttrs ()
	{
		return dynamicAttrs;
	}

	protected Object getDynamicAttr (final String name)
	{
		return dynamicAttrs.get (name);
	}

	public void setAttributes (Map<String, Object> attributes)
	{
		for (String key : attributes.keySet ()) {
			dynamicAttrs.put (key, attributes.get (key));
		}
	}

	protected void writeBody (JspWriter out, String body)
	    throws IOException
	{
		out.print (HTMLUtil.escape  (body));
		return;
	}

	protected void writeAttribute (JspWriter out, String attr, String value)
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
	}

	protected void writeDynamicAttributes (final JspWriter out)
	    throws IOException
	{
		for (final String key : dynamicAttrs.keySet ()) {
			out.print (' ');
			out.print (key);
			out.print ("=\"");
			final Object o = dynamicAttrs.get (key);
			if (o != null) {
				out.print (HTMLUtil.escape  (o.toString ()));
			}
			out.print ('"');
		}
	}

	protected Form findForm ()
	{
		String attr = getBean ();
		if (StringUtil.isEmpty (attr)) {
			attr = "form";
		}
		Object obj = pageContext.getAttribute (attr);
		if (obj == null) {
			obj = pageContext.getRequest ().getAttribute (attr);
		}
		if (! (obj instanceof Form)) {
			return null;
		} else {
			return (Form) obj;
		}
		
		// NOT REACHED
	}

	public int doStartTag ()
	    throws JspException
	{
		final JspWriter out = pageContext.getOut ();
		try {
			out.append ("<form");
			writeAttribute (out, "name", getName ());
			writeDynamicAttributes (out);
			out.append (">");
			pushTag ();
		} catch (IOException io_e) {
			throw new JspException (io_e);
		}
		return EVAL_BODY_INCLUDE;
	}

	public int doEndTag ()
	    throws JspException
	{
		final JspWriter out = pageContext.getOut ();
		try {
			out.append ("</form>");
			popTag ();
		} catch (final IOException io_e) {
			throw new JspException (io_e);
		}

		return EVAL_PAGE;
	}
}

// EOF