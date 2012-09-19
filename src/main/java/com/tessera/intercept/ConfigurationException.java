package com.tessera.intercept;

/**
 * 
 * @author crawford
 *
 */

@SuppressWarnings("serial")
public class ConfigurationException
    extends Exception
{
    public
    ConfigurationException (final String msg)
    {
        super (msg);
        return;
    }
    
    public
    ConfigurationException (final String msg, Throwable cause)
    {
    	super (msg, cause);
    	return;
    }
}

// EOF
