package com.tessera.site.form.validator;

import com.lattice.data.*;
import com.tessera.intercept.form.*;

/**
 * 
 * @author crawford
 *
 */

public class ValidatorForm
	extends Form
{
	public
	ValidatorForm ()
	{
		return; 
	}

	@NotNull (errmsg="A must be specified.")
	@IntegerValidation (minValue=0, maxValue=100, errmsg="A must be [0, 100].")
	protected Integer a; 
	public Integer getA() { return this.a; } 
	public void setA (final Integer a) { this.a = a; return; } 

	@NotNull (errmsg="B must be specified.")
	@IntegerValidation (minValue=50, maxValue=150, errmsg="A must be [50, 150].")
	protected Integer b; 
	public Integer getB () { return this.b; } 
	public void setB (final Integer b) { this.b = b; return; } 

	@NotNull (errmsg="C must be specified.")
	@IntegerValidation (minValue=100, maxValue=200, errmsg="A must be [100, 200].")
	protected Integer c; 
	public Integer getC () { return this.c; } 
	public void setC (final Integer c) { this.c = c; return; } 
}

// EOF