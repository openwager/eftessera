package com.tessera.taglib.form;

import javax.servlet.jsp.*;

/**
 * 
 * @author crawford
 *
 */

public enum AttributeScope
{
	PAGE, REQUEST, SESSION; 
	
	/**
	 * 
	 * @param pageContext
	 * @param key
	 * @param value
	 */
	
	public
	void set (final PageContext pageContext, final String key, final Object value)
	{
		switch (this) { 
			case PAGE:
				pageContext.setAttribute (key, value); 
				break; 
			case REQUEST:
				pageContext.getRequest ().setAttribute (key, value);
				break;
			case SESSION:
				pageContext.getSession ().setAttribute(key, value); 
				break; 
			default: 
				throw new IllegalStateException (); 
		}
		return; 
	}
	
	/**
	 * 
	 * @param pageContext
	 * @param key
	 * @return
	 */
	
	public
	Object get (final PageContext pageContext, final String key)
	{
		switch (this) { 
			case PAGE: 
				return pageContext.getAttribute (key); 
			case REQUEST: 
				return pageContext.getRequest ().getAttribute (key); 				
			case SESSION:
				return pageContext.getSession ().getAttribute (key);
			default:
				throw new IllegalStateException (); 
		}
		
		// NOT REACHED
	}
	 
}

// EOF