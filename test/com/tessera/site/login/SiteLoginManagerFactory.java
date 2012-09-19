package com.tessera.site.login;

import java.util.*;

import com.tessera.intercept.util.*;


/**
 * 
 * @author crawford
 *
 */

public class SiteLoginManagerFactory
	implements ClassFactory<SiteLoginManager>
{
	public
	SiteLoginManagerFactory ()
	{
		return;
	}

    public SiteLoginManager newInstance (Map<String, String> props)
        throws Exception
    {
	    return new SiteLoginManager (props); 
    }
}

// EOF
