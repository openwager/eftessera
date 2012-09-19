package com.tessera.intercept.jsp;

import com.tessera.dispatch.*;

/**
 * 
 * @author crawford
 *
 */

public class JspWidgetUtil
{
	private 
	JspWidgetUtil ()
	{
		return; 
	}
	
	public interface ATTR
	{
		public String PREFIX = Dispatcher.ATTR.PREFIX + "J";
		public String META = PREFIX + "m"; 
		public String JAVASCRIPT = PREFIX + "js"; 
		public String KEYWORDS = PREFIX + "k"; 
		public String ROBOTS = PREFIX + "r"; 
		public String STYLESHEETS = PREFIX + "s";
		public String DESCRIPTION = PREFIX + "d";
		public String SLOT = PREFIX + "S-"; 
		public String META_CUSTOM = META + "c";
		public String LINK_CUSTOM = PREFIX + "l";
	}	
}

// EOF
