package com.tessera.intercept.login;

/**
 * 
 * @author crawford
 *
 */

@SuppressWarnings("serial")
public class AlreadyLoggedInException
	extends LoginException 
{
	public
	AlreadyLoggedInException (final String msg)
	{
		super (msg); 
		return; 
	}
	
	public 
	AlreadyLoggedInException (final String msg, final Throwable cause)
	{
		super (msg, cause); 
		return; 
	}
}

// EOF