package com.tessera.intercept.form;

import com.lattice.data.*;

/**
 * 
 * @author crawford
 *
 */

public class IntegerIdForm
	extends Form
{
	@NotNull (errmsg="Id must be specified.")
	protected Integer id; 
	public Integer getId () { return this.id; } 
	public void setId (final Integer id) { this.id = id; return; } 
}

// EOF