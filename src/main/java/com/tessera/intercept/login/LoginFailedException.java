package com.tessera.intercept.login;

/**
 * 
 * @author crawford
 *
 */

@SuppressWarnings("serial")
public class LoginFailedException
	extends LoginException
{
	public
	LoginFailedException (final String msg)
	{
		super (msg); 
		return; 
	}
	
	public 
	LoginFailedException (final String msg, final Throwable cause)
	{
		super (msg, cause); 
		return; 
	}
}

// EOF