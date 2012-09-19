package com.tessera.dispatch.mapping.xml;

import java.util.*;

import org.w3c.dom.*;

import com.weaselworks.util.*;

/**
 * TODO: Comment goes here. 
 * 
 * @author Lee Crawford (lee.crawford@gmail.com) 
 * @copyright Copyright (c) 2009, Weaselworks, Inc. 
 */

public class XMLContext
{
	public
	XMLContext ()
	{
		return; 
	}

	protected Document doc; 
	public Document getDocument () { return this.doc; } 
	public void setDocument (final Document doc) { this.doc = doc; return; } 
	
	protected Map<String, XMLMacro> macros = new HashMap<String, XMLMacro> (); 
	public Map<String, XMLMacro> getMacros () { return macros; } 
	public XMLMacro getMacro (final String name) { return macros.get (name); } 
	public boolean hasMacro (final String name) { return macros.get (name) != null; } 
	public void setMacro (final String name, final XMLMacro macro) { macros.put (name, macro); return; } 
	
	protected List<Map<String, String>> params = new ArrayList<Map<String, String>> (); 
	public void pushParameters (final Map<String, String> scope) { params.add (0, scope); return; } 
	public void popParameters () { params.remove (0); return; } 
	
	public 
	boolean hasParameter (final String key)
	{
		return getParameter (key) != null; 
	}
	
	public
	String getParameter (final String key)
	{
		for (int i = 0; i < params.size (); i ++) { 
			final String val = params.get (i).get (key); 
			if (val != null) { 
				return val; 
			}
		}
		return null; 
	}	

	public
	void reset ()
	{
		doc = null; 
		macros.clear (); 
		params.clear (); 
		return; 
	}
	
	/**
	 * Expands a string with possible embedded escape sequences (iteratively) until 
	 * the string is fully expanded (contains no further sequences). Nested escapes are
	 * not supported. 
	 *  
	 * @param orig
	 * @return
	 * @throws Exception
	 */
	
	public
	String expand (final String orig)
		throws Exception
	{
		final List<String> seen = new ArrayList<String> ();
		String s = orig; 
		int i;
		
		while ((i = s.indexOf ("%{")) >= 0) { 
			int j = s.indexOf ('}', i + 2);
			if (j < 0) { 
				throw new Exception ("Syntax error: " + s); 
			}
			final String param = s.substring (i + 2, j);
			if (seen.contains (param)) {
				throw new Exception ("Recursively used parameter: " + param); 
			}
			seen.add (param); 
			final String repl = getParameter (param);
			if (repl == null) { 
				throw new Exception ("Missing parameter: " + param); 
			}
			s = StringUtil.replace (i, j + 1, s, repl);
		}
		return s; 
	}
	
	public
	String toString ()
	{
		String s = getClass().getName () + "["; 
		s += "doc=" + doc; 
		s += ",macros=" + macros;
		s += ",params=" + params; 
		return s + "]"; 
	}	
}

// EOF