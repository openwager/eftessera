package com.tessera.intercept.form;

/**
 * 
 * @author crawford
 *
 */

public class ClassNameFormFactory
	extends ClassFormFactory
		implements FormFactory
{
	/**
	 * 
	 * @param className
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 */
	
	public
	ClassNameFormFactory (final String className)
		throws ClassNotFoundException, NoSuchMethodException
	{
		super (Thread.currentThread ().getContextClassLoader ().loadClass (className)); 
		return; 
	}
}

// EOF