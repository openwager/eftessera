package com.tessera.site.form.enums;

import com.lattice.data.*;
import com.tessera.intercept.form.*;

/**
 * 
 * @author crawford
 *
 */

public class EnumsForm
	extends Form
{
	public
	EnumsForm ()
	{
		return; 
	}
	
	@NotNull (errmsg="Color must be specified.")
	protected PrettyColor color; 
	public PrettyColor getColor () { return this.color; } 
	public void setColor (final PrettyColor color) { this.color = color; return; } 
	
	@NotNull (errmsg="Gender must be specified.")
	protected Gender gender; 
	public Gender getGender () { return this.gender; } 
	public void setGender (final Gender gender) { this.gender = gender; return; } 	
}

// EOF
