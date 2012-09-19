package com.tessera.intercept;

/**
 * TODO: Comment goes here. 
 * 
 * @author Lee Crawford (lee.crawford@gmail.com) 
 * @copyright Copyright (c) 2009, Weaselworks, Inc. 
 */

abstract public class Alteration
{
    public
    Alteration ()
    {
        return;
    }

    @Override
	public
    String toString ()
    {
        String s = getClass ().getName () + "[";
        s += paramString ();
        return s + "]";
    }

    abstract protected
    String paramString ();
}

// EOF