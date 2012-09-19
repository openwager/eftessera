package com.tessera.intercept.util;

import java.util.*;

import javax.servlet.http.*;

import org.apache.log4j.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.*;

/**
 * 
 * @author crawford
 *
 */

public class DumpDispatchContextInterceptor
	extends InterceptorSupport
{
	public static final Logger logger = Logger.getLogger (DumpDispatchContextInterceptor.class); 
	
	public
	DumpDispatchContextInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 
	}
	
    public Alteration intercept (final HttpServletRequest req, final HttpServletResponse res, final DispatchContext dc)
        throws Exception
    {
		logger.info ("DispatchContext = {");
		logger.info ("  path=" + dc.getPath ()); 
		logger.info ("  dispatcher=" + dc.getDispatcher ()); 
		logger.info ("}"); 		
	    return NO_ALTERATION;
    }
}

// EOF