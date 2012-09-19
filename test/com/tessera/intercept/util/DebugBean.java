package com.tessera.intercept.util;

import java.util.*;

import org.apache.log4j.*;

/**
 * 
 * @author crawford
 *
 */

public class DebugBean
{
	private static final Logger logger = Logger.getLogger (DebugBean.class); 
	
	public
	DebugBean (final Map<String, String> props)
	{
		logger.info ("Creating a new DebugBean (" + props + ").");
		this.props = props; 
		return; 
	}
	
	protected Map<String, String> props; 
	
	public
	String toString ()
	{
		String s = getClass().getName () + "[";
		s += "props=" + props; 
		return s + "]"; 
	}
}

// EOF