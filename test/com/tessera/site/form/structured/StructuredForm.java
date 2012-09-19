package com.tessera.site.form.structured;

import com.lattice.data.*;
import com.tessera.intercept.form.*;

/**
 * 
 * @author crawford
 *
 */

public class StructuredForm
	extends Form
{
	public
	StructuredForm ()
	{
		return; 
	}

	@ObjectValidation
	protected User user = new User (); 
	public void setUser (final User user) { this.user = user; return; } 
	public User getUser () { return this.user; } 
	
	@ObjectValidation
	protected Money money = new Money (); 
	public void setMoney (final Money money) { this.money = money; return; } 
	public Money getMoney () { return this.money; } 
}

// EOF