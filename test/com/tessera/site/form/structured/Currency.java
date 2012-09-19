package com.tessera.site.form.structured;

/**
 * 
 * @author crawford
 *
 */

public enum Currency
{
	USD, EUR, GBP; 

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