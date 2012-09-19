package com.tessera.site.form.structured;

import com.lattice.data.*;

/**
 * 
 * @author crawford
 *
 */

public class User
{	
	public
	User ()
	{
		return; 
	}

	@StringValidation (regex=".+", errmsg="First name must be specified.")
	protected String firstName;
	public String getFirstName () { return this.firstName; } 
	public void setFirstName (final String firstName) { this.firstName = firstName; return; } 
	
	@StringValidation (regex=".+", errmsg="Last name must be specified.")
	protected String lastName;
	public String getLastName () { return this.lastName; } 
	public void setLastName (final String lastName) { this.lastName = lastName; return; } 
	
	@NotNull (errmsg="Gender must be specified.")
	protected Gender gender; 
	public Gender getGender () { return this.gender; } 
	public void setGender (final Gender gender) { this.gender = gender; return; } 
}

// EOF