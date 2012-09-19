package com.tessera.intercept.form;

import java.lang.reflect.*;

/**
 * 
 * @author crawford
 *
 */

public class ClassFormFactory
	implements FormFactory
{
	@SuppressWarnings("unchecked")
    public
	ClassFormFactory (final Class<?> type)
		throws NoSuchMethodException
	{
		cons = (Constructor <? extends Form>) type.getConstructor (); 
		return; 
	}
	
	protected Constructor<? extends Form> cons; 

    public Form createForm ()
        throws Exception
    {
		return cons.newInstance (); 
    }
}

// EOF