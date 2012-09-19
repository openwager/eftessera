package com.tessera.intercept.servlet;

import java.util.*;

import javax.servlet.http.*;

import org.apache.log4j.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.*;
import com.weaselworks.util.*;

/**
 * TODO: Comment goes here. 
 * 
 * @author Scott Barklow (scott@twofish.com)
 * @copyright Copyright (c) 2006-2009, Twofish, Inc.
 */

public class CopyAttributeInterceptor
	extends InterceptorSupport
{
	protected static final Logger logger = Logger.getLogger (CopyAttributeInterceptor.class.getName ());
	
	public	
	CopyAttributeInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 
	}

	interface PROP
		extends InterceptorSupport.PROP
	{
		public static final String SRC_ATTR = "srcAttr";  
		public static final String DEST_ATTR = "destAttr";  
		public static final String SRC_CONTEXT = "srcContext";  
		public static final String DEST_CONTEXT = "destContext";  
	}
	
	interface DEFAULT
	{
		public static final String SRC_CONTEXT = AttributeContext.REQUEST.name ();  
		public static final String DEST_CONTEXT = AttributeContext.REQUEST.name ();  
	}
	
	@Override
	public
	void init () 
		throws ConfigurationException
	{
		srcAttr = require (PROP.SRC_ATTR);
		srcContext = AttributeContext.valueOf (getProperty (PROP.SRC_CONTEXT, DEFAULT.SRC_CONTEXT)); 
		destContext = AttributeContext.valueOf (getProperty (PROP.DEST_CONTEXT, DEFAULT.DEST_CONTEXT));
		
		destAttr = getProperty(PROP.DEST_ATTR);
		if (StringUtil.isEmpty(destAttr)) {
			destAttr = srcAttr;
		}
		
		return;
	}

	protected AttributeContext srcContext;
	protected String srcAttr; 
	
	protected AttributeContext destContext;
	protected String destAttr; 
	
	public
	Alteration intercept (final HttpServletRequest req, final HttpServletResponse res, DispatchContext dc)		
	{
		final Object value = srcContext.getAttribute (req, srcAttr); 
		destContext.setAttribute (req, destAttr, value);	
		return NO_ALTERATION;
	}
}

// EOF