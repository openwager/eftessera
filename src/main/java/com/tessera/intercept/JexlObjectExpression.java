package com.tessera.intercept;

/**
 * A convenient subclass of {@linkplain JexlExpression} used for expressions
 * that return {@linkplain Object} values. 
 * 
 * @author crawford
 *
 */

public class JexlObjectExpression
	extends JexlExpression<Object>
{
	public
	JexlObjectExpression (final String value)
		throws Exception
	{
		super (Object.class, value);
		return; 
	}
}

// EOF