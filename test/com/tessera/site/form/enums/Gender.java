package com.tessera.site.form.enums;

/**
 * 
 * @author crawford
 *
 */

public enum Gender
{
	MALE ("male"), 
	FEMALE ("female"); 
	
	private
	Gender (final String desc)
	{
		setDescription (desc); 
		return; 
	}
	
	private String desc; 
	protected String getDescription () { return this.desc; }
	public void setDescription (final String desc) { this.desc = desc; return; } 

	public String getName () { return this.name (); } 
	public int getOrdinal () { return this.ordinal (); } 
}

// EOF
