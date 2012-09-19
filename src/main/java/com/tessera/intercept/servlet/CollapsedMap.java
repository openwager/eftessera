package com.tessera.intercept.servlet;

import java.util.*;

/**
 * A very primitive wrapper that can be used to flatten the annoying parameter
 * maps that HttpServletRequest's carry.  
 * 
 * @author Lee Crawford (crawford@twofish.com). 
 * @copyright Copyright (c) 2006-2009, Twofish, Inc.
 */

// package
class CollapsedMap 
	implements Map<String, String> 
{
	// package
	CollapsedMap (final Map<String, String []> map)
	{
		this.map = map; 
		return; 
	}
	
	private Map<String, String []> map; 
	
	public
	void clear ()
	{
		map.clear (); 
		return; 
	}
	
	public
	boolean containsKey (final Object key)
	{
	  return map.containsKey (key); 
	}
	
	public
	boolean containsValue (final Object value)
	{
		// UNIMPLEMENTED
		return false; 
	}
	
	public
	Set<java.util.Map.Entry<String, String>> entrySet ()
	{
		// UNIMPLEMENTED
		return null; 
	}
	
	public 
	String get (final Object key)
	{
		final String [] vals = map.get (key); 
		if (vals != null && vals.length > 0) { 
			return vals [0]; 
		} else {  
			return null;
		}
	}
		
	public
	boolean isEmpty ()
	{
		return map.isEmpty (); 
	}
	
	public 
	Set<String> keySet ()
	{
		return map.keySet (); 
	}
	
	public 
	String put (String key, String value)
	{
		// UNIMPLEMENTED
		return null; 
	}
	
	public
	void putAll (Map<? extends String, ? extends String> m)
	{
		// UNIMPLEMENTED
		return; 			
	}
	
	public 
	String remove (final Object key)
	{
		// UNIMPLEMENTED
		return null; 
	}
	
	public
	int size ()
	{
		return map.size (); 
	}
	
	public 
	Collection<String> values ()
	{
		// UNIMPLEMENTED
		return null; 
	}
	
	/**
	 * @see String#toString()
	 */
	
	public
	String toString ()
	{
		final StringBuffer buf = new StringBuffer (); 
		buf.append ("{");
		for (final String key : map.keySet ()) { 
			buf.append (key + " = '" + get (key) + "', "); 			
		}
		buf.append ("}"); 
		return buf.toString (); 
	}
}

// EOF
