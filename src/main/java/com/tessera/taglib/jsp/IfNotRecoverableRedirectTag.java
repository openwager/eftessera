package com.tessera.taglib.jsp;

/**
 * 
 * @author crawford
 *
 */

@SuppressWarnings("serial")
public class IfNotRecoverableRedirectTag
	extends IfRecoverableRedirectTag 
{
	public
	IfNotRecoverableRedirectTag ()
	{
		return; 
	}
	
	@Override
	protected
	boolean showBody ()
	{
		return ! super.showBody (); 
	}
}
