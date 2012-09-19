package com.tessera.taglib.form;

import javax.servlet.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import com.tessera.intercept.form.*;

/**
 * 
 * @author crawford
 *
 */

public class SimpleTagFormSupport
	extends SimpleTagSupport
{
	protected String bean; 
	public String getBean () { return this.bean; } 
	public void setBean (final String bean) { this.bean = bean; return; } 
	
	protected Form form = null;
	
	public Form getForm ()
	{
		if (form == null) { 
			form = findForm (); 
		}
		return form; 
	}

	/**
	 * 
	 * @return
	 */
	
    private 
    Form findForm ()
	{
		// If no form bean is specified, we look for an ancestor FormTag from which to
		// get the form. If we find one, we use its form bean, otherwise, we use a
		// default bean name: "form"
		String attr = getBean ();
		if (attr == null || "".equals (attr)) {
			FormTag formTag = (FormTag) findAncestorTag (FormTag.class);
			if (formTag != null) {
				return formTag.getForm ();
			} else {
				attr = "form";
			}
		}

		PageContext pageContext = (PageContext) getJspContext ();
		Object o = pageContext.getAttribute (attr);
		if (o == null) {
			o = pageContext.getRequest ().getAttribute (attr);
		}
		if (!(o instanceof Form)) {
			return null;
		} else {
			return (Form) o;
		}
	}

    /**
     * 
     * @return
     */
    
	private  
	TagStack getTagStack ()
	{
		final PageContext pc = (PageContext) getJspContext (); 
		final ServletRequest req = pc.getRequest ();
		final Object attr = req.getAttribute (TagStack.ATTR);

		if (attr == null) {
			TagStack stack = new TagStack ();
			req.setAttribute (TagStack.ATTR, stack);
			return stack;
		} else if (attr instanceof TagStack) {
			return (TagStack) attr;
		} else {
			return new TagStack ();
		}
		
		// NOT REACHED
	}

	/**
	 * 
	 */
	
	protected
	void pushTag ()
	{
		getTagStack ().push (this);
	}

	/**
	 * 
	 */
	
	protected 
	void popTag ()
	{
		getTagStack ().pop ();
	}

	/**
	 * 
	 * @return
	 */
	
	protected 
	JspTag getParentTag ()
	{
		return getTagStack ().getParent ();
	}

	/**
	 * 
	 * @param clazz
	 * @return
	 */
	
	protected 
	JspTag findAncestorTag (final Class<?> clazz)
	{
		return getTagStack ().findAncestor (clazz);
	}
}

// EOF