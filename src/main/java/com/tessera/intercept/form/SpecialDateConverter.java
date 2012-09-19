package com.tessera.intercept.form;

import java.text.*;
import java.util.regex.*;

import org.apache.commons.beanutils.*;

/**
 * 
 * @author crawford
 *
 */

public class SpecialDateConverter
	implements Converter
{
	public 
	SpecialDateConverter ()
	{
		return; 
	}

	
	protected Pattern p1 = Pattern.compile ("\\d+"); 
	protected Pattern p2 = Pattern.compile ("\\d{1,2}/\\d{1,2}/\\d{2,4}"); 
	protected Pattern p3 = Pattern.compile ("\\d{1,2}/\\d{1,2}/\\d{2,4} \\d{1,2}:\\d{1,2}:\\d{1,2}"); 
	
	protected DateFormat f2 = new SimpleDateFormat ("MM/dd/yyyy"); 
	protected DateFormat f3 = new SimpleDateFormat ("MM/dd/yyyy hh:mm:ss"); 
	
	@SuppressWarnings ("unchecked")
	public 
	Object convert (Class arg0, Object arg1) 
	{
		try { 
			final String s = (String) arg1; 
			if (p1.matcher (s).matches ()) { 
				return Long.parseLong (s); 
			} else if (p2.matcher (s).matches ()) { 
				return f2.parse (s); 
			} else if (p3.matcher (s).matches ()) {
				return f3.parse (s); 
			} 
		}
		catch (final ParseException p_e) { 
			// IGNORED
		}
			
		return arg1; 
	}
}

// EOF