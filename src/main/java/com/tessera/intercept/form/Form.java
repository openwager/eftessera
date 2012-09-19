package com.tessera.intercept.form;

import java.util.*;

import com.lattice.validate.*;
import com.weaselworks.util.*;

/**
 * 
 * @author crawford
 *
 */

public class Form
	implements Validateable
{
	public
	Form ()
	{
		return; 
	}
	
	public
	void reset ()
	{
		
	}

	protected Map<String, Object> other = new HashMap<String, Object> () ;
	public Map<String, Object> getOther () { return this.other; } 
	public void setOther (final Map<String, Object> other) { this.other = other; return; } 
	
	protected boolean bound;
	public boolean getBound () { return this.bound; } 
	public void setBound (final boolean bound) { this.bound = bound; return; } 
	
	protected boolean hasErrors; 
	public boolean getHasErrors () { return this.hasErrors; } 
	public void setHasErrors (final boolean hasErrors) { this.hasErrors = hasErrors; return; } 
	
	protected boolean toBoolean (final Boolean val) { return val != null && val.booleanValue (); } 
	
	public
	void setError (final String field, final String errmsg)
	{
		if (StringUtil.isEmpty (field)) { 
			throw new IllegalArgumentException ("Field cannot be null.");
		}
		if (StringUtil.isEmpty (errmsg)) { 
			throw new IllegalArgumentException ("Errmsg cannot be null."); 
		}
		if (errors == null) { 
			errors = new HashMap<String, String> (); 
		}
		errors.put (field, errmsg); 
		setHasErrors (true); 
		return; 
	}

	public
	String getError (final String field) 
	{
		return errors == null ? null : errors.get (field); 
	}
	
	protected List<String> globalErrors; 
	public List<String> getGlobalErrors () { return this.globalErrors; } 
	public void setGlobalErrors (final List<String> globalErrors) { this.globalErrors = globalErrors; return; } 
	
	public
	void addGlobalError (final String err) 	
	{
		if (globalErrors == null) { 
			globalErrors = new ArrayList<String> (); 
		}
		globalErrors.add (err); 
		setHasErrors (true);  
		return; 
	}
	
	protected Map<String, String> errors; 
	public Map<String, String> getErrors () { return this.errors; } 
	public void setErrors (final Map<String, String> errors) { this.errors = errors; return; } 
	
	protected Throwable throwable; 
	public Throwable getThrowable () { return this.throwable; } 
	public boolean hasThrowable () { return this.throwable != null; } 
	public void setThrowable (final Throwable throwable) { this.throwable = throwable; return; } 
	
	@Override
	public
	String toString ()
	{
		return StringUtil.toString (this); 
	}

	
	/**
	 * We're overriding the standard validation method to perform a more thorough validation 
	 * pass so we can stuff the error messages into the appropriate form fields. 
	 * 
	 * @see Validateable#validate()
	 */

    public void validate ()
        throws ValidationException
    {
		FormUtil.validate (this); 
		return; 
    }	
}

// EOF
