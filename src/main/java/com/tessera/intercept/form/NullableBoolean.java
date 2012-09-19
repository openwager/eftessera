package com.tessera.intercept.form;

/**
 * 
 * @author crawford
 *
 */

public enum NullableBoolean
{
	EITHER (null),
	TRUE (true),
	FALSE (false);

	NullableBoolean (Boolean val)
	{
		setValue (val); 
		return; 		
	}
	
	protected Boolean value; 
	public Boolean getValue () { return this.value; } 
	public void setValue (final Boolean value) { this.value = value; return; } 

	protected static
	Boolean toBoolean (final NullableBoolean value)
	{
		if (value == null) { 
			return null; 
		} else { 
			return value.getValue (); 
		}
		// NOT REACHED
	}
	
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