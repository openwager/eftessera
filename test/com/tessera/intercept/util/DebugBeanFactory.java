package com.tessera.intercept.util;

import java.util.*;

/**
 * 
 * @author crawford
 *
 */

public class DebugBeanFactory
	implements ClassFactory<DebugBean>
{
	public
	DebugBeanFactory ()
	{
		return; 
	}
	
    public 
    DebugBean newInstance (final Map<String, String> props)
        throws Exception
    {
	    return new DebugBean (props); 
    }
}

// EOF