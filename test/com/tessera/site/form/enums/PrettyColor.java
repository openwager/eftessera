package com.tessera.site.form.enums;

/**
 * 
 * @author crawford
 *
 */

public enum PrettyColor
{	
	RED ("Red"), 
	ORANGE ("Orange"), 
	YELLOW ("Yellow"), 
	GREEN ("Green"), 
	BLUE ("Blue"), 
	PURPLE ("Purple"), 
	BROWN ("Brown"), 
	BLACK ("Black"); 
		
	private
	PrettyColor (final String descr)
	{
		setDescription (descr); 
		return; 
	}
	
	private String descr; 
	public String getDescription () { return this.descr; } 
	public void setDescription (final String descr) { this.descr = descr; return; } 	

	public String getName () { return this.name (); } 
	public int getOrdinal () { return this.ordinal (); } 
}

// EOF