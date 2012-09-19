package com.tessera.site.login;

import java.util.*;

/**
 * 
 * @author crawford
 *
 */

public class SiteCookieUtil
{
	private
	SiteCookieUtil ()
	{
		return; 
	}

	/**
	 * A utility method used to parse the standard cookie encoding into a map 
	 * for easy retrieval of key values. 
	 * 
	 * @param value
	 * @return
	 * @throws Exception
	 */
	
	public static
	Map<String, String> parseCookie (final String value)
		throws Exception
	{
		assert value != null; 
		
		final Map<String, String> map = new HashMap<String, String> (); 
		final StringTokenizer toker = new StringTokenizer (value, ","); 
		while (toker.hasMoreElements ()) { 
			final String elem = toker.nextToken ();
			final int index = elem.indexOf ('=');
			if (index < 1 || index == (elem.length () - 1)) { 
				throw new MalformedCookieException (value); 
			}
			map.put (elem.substring (0, index), elem.substring (index + 1)); 
		}
		return map; 
	}
	
	/**
	 * A utility method used to encode a map into the standard cookie
	 * encoding. 
	 * 
	 */
	
	public static
	String encodeCookie (final Map<String, String> map)
	{
		final StringBuffer buf = new StringBuffer ();
		for (final String key : map.keySet ()) { 
			final String value = map.get (key);
			buf.append (key); 
			buf.append ('='); 
			buf.append (value);
			buf.append (','); 
		}
		return buf.toString (); 
	}
	
//	public static
//	void main (final String [] args)
//		throws Exception
//	{
//		final String [] ss = {
//			"", 
//			"a=b", 
//			"a=b,c=d", 
//			"a=b,c=d,e=asdfasdasd"
//		}; 
//		for (final String s : ss) {
//			System.out.println ("====================");
//			System.out.println (s); 
//			final Map<String, String> map = parseCookie (s); 
//			System.out.println (map); 
//			final String s2 = encodeCookie (map);
//			System.out.println (s2); 
//			System.out.println (parseCookie (s2)); 
//		}
//		return; 
//	}
}

// EOF

