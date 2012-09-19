package com.tessera.util;

import java.util.*;

public class PathUtil
{

	private
	PathUtil ()
	{
		return; 
	}
	
	/**
	 * The supported path separator. 
	 */
	
	public static final char SEPARATOR = '/'; 
	
	/**
	 * 
	 * @param path
	 * @return
	 */
	
	public static 
	List<String> parsePath (final String path)
	{
		final List<String> split = new ArrayList<String> ();
		int len = path.length (); 
		int start = 0; 
		
		for (int i = 0; i < len; i ++) { 
			if (path.charAt (i) == SEPARATOR) {
				if (i == start) {
					split.add ("");
					start ++; 
					continue; 
				} else { 
					split.add (path.substring (start, i));
					start = i + 1;
				}
			}
		}
		if (start != len) { 
			split.add (path.substring (start, len)); 
		}  else if (len > 0 && path.charAt (len - 1) == SEPARATOR) { 
			split.add (""); 
		}
		return split; 
	}
}

// EOF