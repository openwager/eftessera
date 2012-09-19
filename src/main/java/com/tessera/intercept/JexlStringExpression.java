package com.tessera.intercept;

/**
 * A convenient subclass of {@linkplain JexlExpression} used for expressions
 * that return {@linkplain String} values. 
 * 
 * @author crawford
 *
 */

public class JexlStringExpression
	extends JexlExpression<String>
{
	public
	JexlStringExpression (final String value)
		throws Exception
	{
		super (String.class, value); 
		return; 
	}
}

// EOF