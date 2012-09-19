package com.tessera.dispatch.mapping;

/**
 * TODO: Comment goes here. 
 * 
 * @author Lee Crawford (lee.crawford@gmail.com) 
 * @copyright Copyright (c) 2009, Weaselworks, Inc. 
 */

@SuppressWarnings("serial")
public class MappingNotFoundException 
	extends MappingException
{
	public
	MappingNotFoundException (final String msg)
	{
		super (msg); 
		return; 
	}
	
	public
	MappingNotFoundException (final String msg, final Throwable cause)
	{
		super (msg, cause); 
		return; 
	}
}

// EOF