package com.tessera.intercept.form;

import java.util.*;

import com.tessera.intercept.*;
import com.weaselworks.util.*;

/**
 * 
 * @author crawford
 *
 */

// package
abstract class FormInterceptorSupport
	extends InterceptorSupport
{
	protected
	FormInterceptorSupport (final Map<String, String> props)
	{
		super (props); 
		return; 
	}

	interface PROP
		extends InterceptorSupport.PROP
	{
		public String FACTORY = "factory";
		public String CLOBBER = "clobber"; 
	}
	
	interface DEFAULT
	{
		public String ATTR = "form"; 
	}
	
	protected FormFactory factory; 
	public FormFactory getFormFactory () { return this.factory; } 
	public void setFormFactory (final FormFactory factory) { this.factory = factory; return; } 
	
	@Override
	public
	void init ()
		throws Exception
	{
        super.init ();
        final String c = getProperty (PROP.CLASS);
        final String f = getProperty (PROP.FACTORY);

        if (c != null && f != null) {
            throw new IllegalArgumentException ("Both '" + PROP.CLASS + "' and '" + PROP.FACTORY + "' specified.");
        } else if (c != null) {
            setFormFactory (new ClassNameFormFactory (c));
        } else if (f != null) {
            setFormFactory ((FormFactory) ClassUtil.newInstance (f));
        } else {
            throw new IllegalArgumentException ("Either '" + PROP.CLASS + "' or '" + PROP.FACTORY + "' must be specified.");
        }

        return;	
	}
}	

// EOF