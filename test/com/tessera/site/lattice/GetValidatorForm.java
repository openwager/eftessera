package com.tessera.site.lattice;

import com.lattice.data.*;
import com.tessera.intercept.form.*;

/**
 * 
 * @author crawford
 *
 */

public class GetValidatorForm
	extends Form
{
	public
	GetValidatorForm ()
	{
		return; 
	}
	
	@NotNull (errmsg="Validator type must be specified.")
	protected Class<?> validatorType; 
	public Class<?> getValidatorType () { return this.validatorType; } 
	public void setValidatorType (final Class<?> validatorType) { this.validatorType = validatorType; return; } 
}

// EOF
