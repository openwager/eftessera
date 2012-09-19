package com.tessera.site.form.structured;

import com.lattice.data.*;

/**
 * 
 * @author crawford
 *
 */

public class Money
{
	public
	Money ()
	{
		return; 
	}

	@NotNull (errmsg="Currency must be specified.")
	protected Currency currency;
	public Currency getCurrency () { return this.currency; }
	public void setCurrency (final Currency currency) { this.currency = currency; return; }
	
	@NotNull (errmsg="Amount must be specified.")
	@IntegerValidation (minValue=0, errmsg="Amount must be positive.")
	protected Integer amount; 
	public Integer getAmount () { return this.amount; } 
	public void setAmount (final Integer amount) { this.amount = amount; return; } 
}

// EOF