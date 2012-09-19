package com.tessera.site.form.multiple;

import com.lattice.data.*;
import com.lattice.validate.*;
import com.tessera.intercept.form.*;

/**
 * 
 * @author crawford
 *
 */

public class MultipleForm
	extends Form
{
	public
	MultipleForm ()
	{
		return; 
	}

	@NotNull (errmsg="Value must be specified.")
	@CustomFieldValidation (classes={
		MultipleForm.ValueValidator.class
	})
	protected Integer value; 
	public Integer getValue () { return this.value; } 
	public void setValue (final Integer value) { this.value = value; return; } 

	/**
	 * 
	 *
	 */
	
	@SuppressWarnings("serial")
    protected static class ValueValidator
		extends FieldValidator<MultipleForm, Integer>
	{
		public ValueValidator (Class<MultipleForm> c1, final Class<Integer> c2, final String field)
            throws SecurityException, NoSuchFieldException
        {
	        super (c1, c2, field); 
	        return; 
        }

		@Override
        public void validate (final Integer value)
            throws ValidationException
        {
			final int v = value.intValue (); 
			if (v % 2 == 1) { 
				throw new ValidationException (value, this, "Odd numbers are not permitted.");
			} 
			if (v == 2) { 
				throw new ValidationException (value, this, "Actually, we don't like 2. Try another."); 
			} 
	        return; 	        
        }		
	}
}

// EOF