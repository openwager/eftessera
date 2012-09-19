package com.tessera.intercept.login.cookie;

import java.util.*;

import com.tessera.intercept.util.*;

/**
 * 
 * @author crawford
 *
 */

public class ParameterizedCookieManagerFactory
	implements ClassFactory<ParameterizedCookieManager>
{
	public
	ParameterizedCookieManagerFactory ()
	{
		return ;
	}

	@Override
	public ParameterizedCookieManager newInstance (Map<String, String> props)
		throws Exception 
	{
		final ParameterizedCookieManager cm = new ParameterizedCookieManager (props); 
		return cm; 
	}
}

// EOF