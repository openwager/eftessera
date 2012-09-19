package com.tessera.site.form.simple;

import com.lattice.data.*;
import com.lattice.validate.*;
import com.tessera.intercept.form.*;

/**
 * 
 * @author crawford
 *
 */

@CustomValidation (classes={
	SimpleForm.CustomValidator.class
})
public class SimpleForm
	extends Form
{
	public
	SimpleForm ()
	{
		return; 
	}
	
	@StringValidation (regex=".+", errmsg="Value must be specified.")
	protected String text; 
	public String getText () { return this.text; } 
	public void setText (final String text) { this.text = text; return; } 
	
	@StringValidation (regex=".+", errmsg="Value must be specified.")
	protected String password; 
	public String getPassword () { return this.password; }
	public void setPassword (final String password) { this.password = password; return; } 
	
	protected boolean checkbox; 
	public boolean getCheckbox () { return this.checkbox; }
	public void setCheckbox (final boolean checkbox) { this.checkbox = checkbox; return; } 

	@StringValidation (regex="b", errmsg="You must pick orange.")
	protected String radio;
	public String getRadio () { return this.radio; }
	public void setRadio (final String radio) { this.radio = radio; return; } 
	
	@StringValidation (regex=".+", errmsg="Value must be specified.")
	protected String select; 
	public String getSelect () { return this.select; }
	public void setSelect (final String select) { this.select = select; return; } 
	
	protected String file; 
	public String getFile () { return this.file; }
	public void setFile (final String file) { this.file = file; return; } 
	
//	@StringValidation (regex=".{0}", errmsg="Don't click this.")
//	protected String image; 
//	public String getImage () { return this.image; }
//	public void setImage (final String image) { this.image = image; return; } 

	protected String button; 
	public String getButton () { return this.button; } 
	public void setButton (final String button) { this.button = button; return; } 

	
	/**
	 * 
	 * @author crawford
	 *
	 */
	
	@SuppressWarnings("serial")
    public static class CustomValidator
		extends Validator<SimpleForm> 
	{
		public
		CustomValidator (Class<SimpleForm> type)
        {
	        super (type);
	        return;  
        }

		@Override
        public void validate (final SimpleForm value)
            throws ValidationException
        {
			if (value.getCheckbox ()) { 
				throw new ValidationException (value, this, "Don't check the checkbox!"); 
			}
			return; 	        
        }
	}
}


// EOF