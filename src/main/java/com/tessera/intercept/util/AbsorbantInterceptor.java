package com.tessera.intercept.util;

import java.util.*;

import javax.servlet.http.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.*;

/**
 * An interceptor that can be used to wrap an interceptor chain and silently absorb any exceptions 
 * that are thrown during interception. 
 * 
 * Instead of this, which would throw an exception: 
 * 
 * <pre>
 * &lt;interceptor class="com.tessera.intercept.util.ThrowThrowableInterceptor"> 
 *    &lt;property name="type" value="java.lang.NullPointerException" /> 
 *    &lt;property name="message" value="hiya!" /> 
 * &lt;/interceptor>
 * </pre>
 * 
 * You can do this: 
 * 
 * <pre>
 * &lt;interceptor name="com.tessera.intercept.util.AbsorbantInterceptor">
 *   &lt;interceptor class="com.tessera.intercept.util.ThrowThrowableInterceptor">
 *     &lt;property name="type" value="java.lang.NullPointerException" /> 
 *     &lt;property name="message" value="hiya!" /> 
 *   &lt;/interceptor>
 * &lt;/interceptor>
 * </pre>
 * 
 * @author crawford
 *
 */

public class AbsorbantInterceptor	
	extends MappedInterceptorSupport
{
	public
	AbsorbantInterceptor (final Map<String, String> map)
	{
		super (map); 
		return; 
	}

	public 
	String map (HttpServletRequest req, HttpServletResponse res)
		throws Exception 
	{
		return "true"; 
	}

	public
	Alteration intercept (HttpServletRequest req, HttpServletResponse res, DispatchContext dc)
	{
		try { 
			super.intercept (req, res, dc);	
		}
		catch (final Throwable t) { 
			// IGNORED
		}
		return NO_ALTERATION; 
	}
}

// EOF