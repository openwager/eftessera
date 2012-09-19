package com.tessera.intercept.util;

import java.util.*;

/**
 * 
 * @author crawford
 *
 * @param <T>
 */

public interface ClassFactory<T>
{
	
	/**
	 * 
	 * @param props
	 * @return
	 */
	
	public
	T newInstance (Map<String, String> props)
		throws Exception; 
}

// EOF