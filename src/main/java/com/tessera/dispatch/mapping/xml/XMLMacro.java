package com.tessera.dispatch.mapping.xml;

import java.util.*;

import org.w3c.dom.*;

/**
 * TODO: Comment goes here. 
 * 
 * @author Lee Crawford (lee.crawford@gmail.com) 
 * @copyright Copyright (c) 2009, Weaselworks, Inc. 
 */

public class XMLMacro
{
	public
	XMLMacro ()
	{
		return; 
	}
	
	public
	XMLMacro (final String name)
	{
		setName (name); 
		return; 
	}
	
	private String name; 
	public String getName () { return this.name; } 
	public void setName (final String name) { this.name = name; return; } 

	private Map<String, String> defaults = new HashMap<String, String> (); 
	@SuppressWarnings("unchecked")
	public Map<String, String> getDefaults () { return (Map<String, String>) ((HashMap<String, String>) this.defaults).clone (); } 
	public String getDefault (final String param) { return defaults.get (param); } 
	public void setDefault (final String param, final String value) { defaults.put (param, value); return; } 
	
	private List<Element> ex; 
	public List<Element> getExpansion () { return ex; } 
	public void setExpansion (final List<Element> ex) { this.ex = ex; return; } 
	
	public
	void setExpansion (final String str)
	{ 
		return; 		
	}
	
	public
	String toString ()
	{
		String s = getClass().getName () + "[";
		s += "name=" + name; 
		s += ",defaults=" + defaults;
		s += ",ex=" + ex; 
		return s + "]"; 
	}
}

// EOF
