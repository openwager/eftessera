package com.tessera.taglib.form;

import java.util.*;

import javax.servlet.jsp.tagext.*;

import com.tessera.dispatch.*;

/**
 * 
 * @author crawford
 *
 */

class TagStack
{
    public final static String ATTR = Dispatcher.ATTR.PREFIX + "ts";

    public
    TagStack () 
    {
    	return; 
    }

    private String bean = null;
    public String getBean () { return this.bean; } 
    public void setBean (final String bean) { this.bean = bean; return; } 

    private ArrayList<JspTag> stack = new ArrayList<JspTag> ();

    public
    JspTag findAncestor (Class<?> clazz) 
    {
        for (final JspTag tag : stack) {
            if (tag.getClass ().equals (clazz)) {
                return resolveTag (tag);
            }
        }
        return null;
    }

    public 
    JspTag getParent ()
    {
        return resolveTag (stack.get (0));
    }

    public
    void push (JspTag tag) 
    {
        stack.add (0, tag);
        return; 
    }

    public
    JspTag pop () 
    {
        if (stack.size () > 0) {
            final JspTag tag = stack.remove (0);
            return tag;
        } else {
            return null;
        }
    }

    protected static 
    JspTag resolveTag (final JspTag tag) 
    {
        if (tag instanceof TagAdapter) {
            return ((TagAdapter) tag).getAdaptee ();
        } else {
            return tag;
        }
        
        // NOT REACHED
    }
}

// EOF