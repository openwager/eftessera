package com.tessera.intercept;

/**
 * TODO: Comment goes here. 
 * 
 * @author Lee Crawford (lee.crawford@gmail.com) 
 * @copyright Copyright (c) 2009, Weaselworks, Inc. 
 */

public class ForwardAlteration
	extends Alteration
{
	public
	ForwardAlteration (final String action)
	{
	    setAction (action); 
	    return;
	}
	
	protected String action;
	public String getAction () { return this.action; }
	public void setAction (final String action) { this.action = action; return; }
	
	@Override
	protected
	String paramString ()
	{
	    return "action=" + getAction ();
	}
}

//EOF