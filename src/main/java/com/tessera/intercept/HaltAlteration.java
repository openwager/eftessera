package com.tessera.intercept;

/**
 * 
 * @author crawford
 *
 */

public class HaltAlteration
    extends Alteration
{
    public
    HaltAlteration ()
    {
        return;
    }

    @Override
	protected
    String paramString ()
    {
        return "";
    }
}

// EOF