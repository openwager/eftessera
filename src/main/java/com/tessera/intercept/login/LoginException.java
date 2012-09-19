package com.tessera.intercept.login;

/**
 * 
 * @author crawford
 *
 */

@SuppressWarnings("serial")
public class LoginException
	extends Exception
{
	public 
	LoginException (final String msg)
	{
		super (msg); 
		return; 
	}
	
	public
	LoginException (final String msg, final Throwable cause)
	{
		super (msg, cause); 
		return; 
	}
}

// EOF