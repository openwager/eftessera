package com.tessera.util;

import java.util.*;

import org.testng.annotations.*;

public class PathUtilTest
{
	public static final String [] PATHS = { 
		"", 
		"/",
		"a/b", 
		"/a/b", 
		"/a/b/" 
	}; 
	
	@Test
//	public void test1 ()
	public static void main (final String [] args) 
	{
		for (final String path : PATHS) { 
			final List<String> split = PathUtil.parsePath (path); 
			System.out.println ("[" + path + "] -> " + split); 
		}
		return; 
	}
}

// EOF