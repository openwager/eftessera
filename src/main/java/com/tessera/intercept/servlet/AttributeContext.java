package com.tessera.intercept.servlet;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 * An enumeration type used to identify either the HttpServletRequest
 * or HttpSession contexts for attribute manipulation.  
 * 
 * @author Lee Crawford (crawford@twofish.com) 
 * @copyright Copyright (c) 2006-2009, Twofish, Inc. 
 */

public enum AttributeContext
{
	REQUEST, 
	SESSION, 
	APPLICATION; 
	
	public
	String getName () 
	{
		return name (); 
	}
	
	public
	int getOrdinal ()
	{
		return ordinal (); 
	}
	
	/**
	 * 
	 * @param req
	 * @param key
	 * @param value
	 */
	
	public
	void setAttribute (final HttpServletRequest req, final String key, final Object value)
	{		
		switch (this) { 
			case REQUEST: 
				req.setAttribute (key, value); 
				break; 
			case SESSION:
				req.getSession ().setAttribute (key, value); 
				break;
			case APPLICATION:
				req.getSession ().getServletContext ().setAttribute (key, value); 
				break; 
			default: 
				throw new IllegalStateException (); 
		}
		return; 
	}
	

	/**
	 * 
	 * @param req
	 * @param key
	 * @param value
	 */
	
	public
	void setAttribute (final ServletContext sc, final String key, final Object value)
	{		
		switch (this) { 
			case REQUEST: 
			case SESSION:
				throw new IllegalStateException ("No request in scope."); 
			case APPLICATION:
				sc.setAttribute (key, value); 
				break; 
			default: 
				throw new IllegalStateException (); 
		}
		return; 
	}

	/**
	 * 
	 * @param sc
	 * @param key
	 * @return
	 */
	
	public
	Object getAttribute (final ServletContext sc, final String key)
	{
		switch (this) { 
			case REQUEST: 
			case SESSION:
				throw new IllegalStateException ("No request in scope."); 
			case APPLICATION: 
				return sc.getAttribute (key); 
			default: 
				throw new IllegalStateException (); 
		}
	}
	
	/**
	 * 
	 * @param req
	 * @param key
	 * @return
	 */
	
	public
	Object getAttribute (final HttpServletRequest req, final String key)
	{
		switch (this) { 
			case REQUEST: 
				return req.getAttribute (key); 
			case SESSION: 
				return req.getSession ().getAttribute (key);
			case APPLICATION: 
				return req.getSession ().getServletContext ().getAttribute (key); 
			default: 
				throw new IllegalStateException (); 
		}
	}

	/**
	 * 
	 * @param req
	 * @param key
	 */
	
	public
	void removeAttribute (final HttpServletRequest req, final String key)
	{
		switch (this) { 
			case REQUEST: 
				req.removeAttribute (key); 
				break; 
			case SESSION: 
				req.getSession ().removeAttribute (key); 
				break; 
			case APPLICATION: 
				req.getSession ().getServletContext ().removeAttribute (key);
				break;
			default: 
				throw new IllegalStateException (); 
		}
	
		return; 
	}

	/**
	 * 
	 * @param sc
	 * @param key
	 */
	
	public
	void removeAttribute (final ServletContext sc, final String key)
	{
		switch (this) { 
			case REQUEST: 
			case SESSION:
				throw new IllegalStateException ("No request in scope."); 
			case APPLICATION: 
				sc.removeAttribute (key);
				break;
			default: 
				throw new IllegalStateException (); 
		}
	
		return; 
	}
}

// EOF 
