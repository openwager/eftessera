package com.tessera.intercept.form;

import com.lattice.data.*;

/**
 * 
 * @author crawford
 *
 */

public class LongIdForm
	extends Form
{
	@NotNull (errmsg="Id must be specified.")
	protected Long id; 
	public Long getId () { return this.id; } 
	public void setId (final Long id) { this.id = id; return; } 
}

// EOF