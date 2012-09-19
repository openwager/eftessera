package com.tessera.taglib.login;

/**
 * 
 * @author crawford
 *
 */

@SuppressWarnings("serial")
public class IfNotLoggedInTag
	extends IfLoggedInTag
{
	public
	IfNotLoggedInTag ()
	{
		return; 
	}
	
	protected
	boolean showBody ()
	{
		return ! super.showBody (); 
	}
}

// EOF