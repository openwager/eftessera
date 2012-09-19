package com.tessera.taglib.form;

import javax.servlet.*;
import javax.servlet.jsp.tagext.*;

import com.tessera.intercept.form.*;
import com.weaselworks.util.*;

/**
 * 
 * @author crawford
 *
 */

@SuppressWarnings("serial")
abstract class BodyTagFormSupport
	extends BodyTagSupport
{
	public
	BodyTagFormSupport ()
	{
		return; 
	}
	
	protected String bean; 
	public String getBean () { return this.bean; } 
	
	public void setBean (final String bean)
	{
		if (StringUtil.isEmpty (bean)) { 
			this.bean = null; 
		} else { 
			this.bean = bean; 			
		}
		return; 
	} 
	
	public
	Form getForm ()
	{
		return findForm (); 
	}
	
	protected
	Form findForm ()
	{
        String attr = getBean ();
        if (attr == null || "".equals(attr)) {
            final FormTag formTag = (FormTag) findAncestorTag(FormTag.class);
            if (formTag != null) {
                return formTag.getForm();
            } else {
                attr = "form";
            }
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
	
	/**
	 * 
	 * @return
	 */
	
	private
	TagStack getTagStack ()
	{
        final ServletRequest req = pageContext.getRequest();
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
        return; 
    }

    /**
     * 
     */
    
	protected 
	void popTag () 
	{
        getTagStack ().pop ();
        return; 
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