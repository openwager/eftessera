package com.tessera.intercept.login.cookie;

import java.util.regex.*;

/**
 * 
 * @author crawford
 *
 */

public class MaxAgeUtil
{
	private
	MaxAgeUtil ()
	{
		return; 
	}

	private static final Pattern pattern = Pattern.compile ("^(\\d+) (.+)$");
	
	interface UNITS
	{
		public int SECOND = 1; 
		public int MINUTE = SECOND * 60; 
		public int HOUR = MINUTE * 60; 
		public int DAY = HOUR * 24; 
		public int WEEK = DAY * 7;
		public int MONTH = DAY * 30; 
		public int YEAR = DAY * 365; 
	}
	
	public static
	int parseMaxAge (final String str)
	{
		assert str != null; 
		
		if ("forever".equals (str)) {
			return Integer.MAX_VALUE; 
		} 
		
		final Matcher matcher = pattern.matcher (str);
		if (matcher.matches ()) { 
			final int amount = Integer.parseInt (matcher.group (1));
			String unit = matcher.group (2).toLowerCase ();
			if (unit.endsWith ("s")) { 
				unit = unit.substring (0, unit.length () - 1); 
			}
			if ("second".equals (unit)) {
				return amount * UNITS.SECOND; 
			} else if ("minute".equals (unit)) { 
				return amount * UNITS.MINUTE; 
			} else if ("hour".equals (unit)) {
				return amount * UNITS.HOUR; 
			} else if ("day".equals (unit)) {
				return amount * UNITS.DAY; 
			} else if ("week".equals (unit)) {
				return amount * UNITS.WEEK; 
			} else if ("month".equals (unit)) {
				return amount * UNITS.MONTH; 
			} else if ("year".equals (unit)) { 
				return amount * UNITS.YEAR; 
			} else { 
				throw new IllegalArgumentException ("Not an elapsed time: " + str); 
			}
		} else { 
			return Integer.parseInt (str);  
		}
		
		// NOT REACHED 
	}

//	public static
//	void main (final String [] args)
//	{
//		final String [] ss = { 
//			"1", 
//			"2", 
//			"forever", 
//			"2 seconds", 
//			"3 weeks", 
//			"4 months", 
//			"5 years", 
//			"6 hours"
//		}; 
//		for (final String s : ss) {
//			System.out.print (s + "  : "); 
//			try { 
//				System.out.println (parseMaxAge (s)); 
//			}
//			catch (final Exception e) { 
//				System.out.println (e.getMessage ()); 
//			}
//		}
//		
//		return; 		
//	}
}

// EOF
