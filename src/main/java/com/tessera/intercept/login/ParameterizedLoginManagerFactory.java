package com.tessera.intercept.login;

import java.util.*;

import com.tessera.intercept.util.*;

/**
 * 
 * @author crawford
 *
 */

public class ParameterizedLoginManagerFactory
	implements ClassFactory<ParameterizedLoginManager>
{
	public
	ParameterizedLoginManagerFactory ()
	{
		return;
	}

	@Override
	public ParameterizedLoginManager newInstance (Map<String, String> props)
		throws Exception 
	{
		final ParameterizedLoginManager lm = new ParameterizedLoginManager (props); 
		return lm; 
	}
}

// EOF