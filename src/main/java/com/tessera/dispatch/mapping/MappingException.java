package com.tessera.dispatch.mapping;

/**
 * TODO: Comment goes here. 
 * 
 * @author Lee Crawford (lee.crawford@gmail.com) 
 * @copyright Copyright (c) 2009, Weaselworks, Inc. 
 */

@SuppressWarnings("serial")
public class MappingException 
	extends Exception
{
	public 
	MappingException (final String msg)
	{
		super (msg); 
		return; 
	}
	
	public
	MappingException (final String msg, final Throwable cause)
	{
		super (msg, cause); 
		return; 
	}
}

// EOF