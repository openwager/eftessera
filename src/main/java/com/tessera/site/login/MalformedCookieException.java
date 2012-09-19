package com.tessera.site.login;

/**
 * 
 * @author crawford
 *
 */

@SuppressWarnings("serial")
public class MalformedCookieException
	extends Exception
{
	public
	MalformedCookieException (final String value)
	{
		super ("Malformed cookie: " + value); 
		setValue (value); 
		return; 
	}
	
	public String getValue () { return this.value; } 
	public void setValue (final String value) { this.value = value; return; }
	protected String value; 
}

// EOF
