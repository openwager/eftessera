
package com.tessera.intercept.util;

import java.io.*;
import java.util.*;

/**
 * TODO: Comment goes here. 
 * 
 * @author Lee Crawford (lee.crawford@gmail.com) 
 * @copyright Copyright (c) 2009, Weaselworks, Inc. 
 */

public class SystemErrInterceptor
	extends SystemInterceptor
{
	public
	SystemErrInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 
	}
	
	public
	PrintStream getOutputStream ()
	{
		return System.err; 
	}
}

// EOF