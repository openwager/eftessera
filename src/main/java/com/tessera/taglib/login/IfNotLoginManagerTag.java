package com.tessera.taglib.login;

/**
 * 
 * @author crawford
 *
 */

@SuppressWarnings("serial")
public class IfNotLoginManagerTag
	extends IfLoginManagerTag
{
	public
	IfNotLoginManagerTag ()
	{
		return; 
	}
	
	@Override
	public
	boolean showBody ()
	{
		return ! super.showBody (); 
	}
}

// EOF
