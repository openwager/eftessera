package com.tessera.site.tessera.action;

import com.lattice.data.*;
import com.tessera.intercept.form.*;

/**
 * 
 * @author crawford
 *
 */

public class GetForm
	extends Form
{
	public
	GetForm ()
	{
		return; 
	}
	
	@StringValidation (regex=".+", errmsg="Action must be specified.")
	protected String action; 
	public String getAction () { return this.action; } 
	public void setAction (final String action) { this.action = action; return; } 
}

// EOF