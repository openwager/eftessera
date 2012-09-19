package com.tessera.intercept.form;

import java.io.*;
import java.util.*;

import com.lattice.validate.*;
import com.weaselworks.io.*;

/**
 * 
 * @author crawford
 *
 */

public class FormUtil
{
	/**
	 * Private constructor to defeat instantiation. 
	 */
	
	private
	FormUtil ()
	{
		return; 
	}
	
	/**
	 * A utility method that is used to perform a comprehensive validation of the form 
	 * object and collecting all of the 
	 * @param form
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
    public static <Type extends Form>
	boolean validate (final Type form)
	{
		assert form != null; 
		form.reset (); 

		try { 
			final ObjectValidator<Type> vor = (ObjectValidator<Type>) ValidatorUtil.getValidator (form.getClass ());
//			ObjectValidatorUtil.dump (getClass (), vor, System.err);
			// Process each of the field-level validations only to the point where each is 
			// able to raise a single error

			final Map<String, List<FieldValidator<? super Type, ?>>> fvm = vor.getValidators (); 
			for (final String field : fvm.keySet ()) { 
				final List<FieldValidator<? super Type, ?>> fvs = fvm.get (field); 
				for (final FieldValidator<? super Type, ?> fv : fvs) { 
					try {
						fv.validateOn (form);
					}
					catch (final ValidationException v_e) {
						form.setError (field, v_e.getMessage ());
						break;
					}
				}
			}
			
			// Only process the class level validations if we haven't encountered any 
			// field level validations
			
			if (! form.getHasErrors ()) {
				final List<Validator<? super Type>> ovs = (List<Validator<? super Type>>) vor.getOtherValidators (); 
				for (final Validator<? super Type> ov : ovs) { 
					try { 
						ov.validate (form); 
					}
					catch (final ValidationException v_e) { 
						form.addGlobalError (v_e.getMessage ()); 
						break; 
					}
				}
			}
		}
		catch (final ValidationException v_e) { 
			form.addGlobalError (v_e.getMessage ()); 
		}
		
		return form.getHasErrors (); 
	}
	
	/**
	 * 
	 * @param form
	 */
	
	public static
	void dump (final Form form)
	{
		assert form != null; 
		
		dump (form, System.out); 
		return; 
	}
	
	/**
	 * 
	 * @param form
	 * @param os
	 */
	
	public static
	void dump (final Form form, final OutputStream os)	
	{
		assert form != null; 
		assert os != null; 
		
		final PrintStream out = IOUtil.getPrintStream (os); 
        out.println ("[" + form.getClass ().getName () + "]: " + (form.hasErrors ? "INVALID" : "VALID"));
        if (form.getHasErrors ()) {
            final List<String> globalErrs = form.getGlobalErrors ();

            if (globalErrs != null) {
                    out.println ("  GLOBAL:");
                    for (final String globalErr : globalErrs) {
                            out.println ("   - " + globalErr);
                    }
            }
            final Map<String, String> errs = form.getErrors ();
            if (errs != null) {
                    out.println ("  ERRORS:");
                    for (final String key : errs.keySet ()) {
                            out.println ("   - " + key + ": " + errs.get (key));
                    }
            }
            if (form.getThrowable () != null) {
                    out.println ("  THROWABLE:");
                    form.getThrowable ().printStackTrace (out);
            }
        }
        out.flush ();
		return; 	
	}	
}

// EOF