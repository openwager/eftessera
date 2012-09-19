package com.tessera.intercept.servlet;

import java.util.*;

import javax.servlet.http.*;

import com.tessera.intercept.*;
import com.weaselworks.net.*;

/**
 * A utility {@linkplain Interceptor} that is used to constrain chained execution depending on
 * the client IP of the requestor. </p>
 * 
 *  <pre>
 *  &lt;interceptor class="com.twofish.ludwig.intercept.util.SourceIPInterceptor">
 *    &lt;property name="addr" value="127.0.0.0"/>
 *    &lt;property name="mask" value="255.0.0.0"/> 
 *  &lt;/interceptor>
 *  </pre>
 * 
 * @author Lee Crawford (crawford@twofish.com). 
 * @copyright Copyright (c) 2006-2009, Twofish, Inc.
 */

public class SourceIPInterceptor
	extends PredicateInterceptorSupport
{
	public
	SourceIPInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 
	}

	interface PROP
	{
		public String ADDR = "addr"; 
		public String MASK = "mask"; 
	}
		
	protected int addr = 0; 
	protected int mask = 0; 
	
	@Override
	public void init ()
		throws Exception
	{
		addr = NetUtil.parseAddress (require (PROP.ADDR)); 
		mask = NetUtil.parseAddress (require (PROP.MASK)); 
		return; 
	}

	@Override
	public 
	boolean evaluate (final HttpServletRequest req, final HttpServletResponse res)
	{
		final int client = NetUtil.parseAddress (req.getRemoteAddr ()); 
		return (client & mask) == addr;  		
	}
}

// EOF
