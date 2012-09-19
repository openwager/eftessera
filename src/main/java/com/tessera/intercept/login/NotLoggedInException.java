package com.tessera.intercept.login;

/**
 * 
 * @author crawford
 *
 */

@SuppressWarnings("serial")
public class NotLoggedInException
	extends LoginException
{
	public
	NotLoggedInException (final String msg)
	{
		super (msg); 
		return; 
	}
	
	public 
	NotLoggedInException (final String msg, final Throwable cause)
	{
		super (msg, cause); 
		return; 
	}
}

// EOF