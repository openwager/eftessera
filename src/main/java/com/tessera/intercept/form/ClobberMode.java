package com.tessera.intercept.form;

/**
 * 
 * @author crawford
 *
 */

public enum ClobberMode
{
	NONE, NOT_SAME, ALWAYS; 
	
	public
	String getName ()
	{
		return name (); 
	}
	
	public
	int getOrdinal ()
	{
		return ordinal (); 
	}
}

// EOF