package com.tessera.site.form.types;

import com.tessera.intercept.form.*;

/**
 * 
 * @author crawford
 *
 */

public class TypesForm
	extends Form
{
	public
	TypesForm ()
	{
		return; 
	}
	
	protected int int1; 
	public int getInt () { return int1; } 
	public void setInt (final int int1) { this.int1 = int1; return; } 
	
	protected Integer int2;  
	public Integer getInteger() { return int2; } 
	public void setInteger (final Integer int2) { this.int2 = int2; return; } 

	protected boolean bool1; 
	public boolean getBoolean1 () { return bool1; } 
	public void setBoolean1 (final boolean bool1) { this.bool1 = bool1; return; } 
	
	protected Boolean bool2;  
	public Boolean getBoolean2 () { return bool2; } 
	public void setBoolean2 (final Boolean bool2) { this.bool2 = bool2; return; } 

	protected long long1;  
	public long getLong1 () { return long1; } 
	public void setLong1 (final long long1) { this.long1 = long1; return; } 
	
	protected Long long2; 
	public Long getLong2 () { return long2; } 
	public void setLong2 (final Long long2) { this.long2 = long2; return; } 

	protected String string; 
	public String getString () { return string; } 
	public void setString (final String string) { this.string = string; return; } 
	
	// character
	
	protected char c = 'A'; 
	public char getChar () { return c; }
	public void setChar (final char c) { this.c = c; return; } 
	
	protected Character character; 
	public Character getCharacter () { return this.character; } 
	public void setCharacter (final Character character) { this.character = character; return; } 
	
	// short
	
	protected short short1; 
	public short getShort1 () { return this.short1; } 
	public void setShort1 (final short short1) { this.short1 = short1; return; } 
	
	protected Short short2; 
	public Short getShort2 () { return this.short2; } 
	public void setShort2 (final Short short2) { this.short2 = short2; return; } 
	
	// byte
	
	protected byte byte1; 
	public byte getByte1 () { return this.byte1; } 
	public void setByte1 (final byte byte1) { this.byte1 = byte1; return; } 
	
	protected Byte byte2; 
	public Byte getByte2 () { return this.byte2; } 
	public void setByte2 (final Byte byte2) { this.byte2 = byte2; return; } 

	// float
	
	protected float float1; 
	public float getFloat1 () { return this.float1; } 
	public void setFloat1 (final float float1) { this.float1 = float1; return; } 

	protected Float float2; 
	public Float getFloat2 () { return this.float2; } 
	public void setFloat2 (final Float float2) { this.float2 = float2; return; } 

	// double
	
	protected double double1; 
	public double getDouble1 () { return this.double1; } 
	public void setDouble1 (final double double1) { this.double1 = double1; return; } 

	protected Double double2; 
	public Double getDouble2 () { return this.double2; } 
	public void setDouble2 (final Double double2) { this.double2 = double2; return; } 
	
	

}

// EOF