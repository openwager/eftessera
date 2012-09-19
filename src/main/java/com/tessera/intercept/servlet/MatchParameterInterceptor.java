package com.tessera.intercept.servlet;

import java.util.*;
import java.util.regex.*;

import javax.servlet.http.*;

import com.tessera.intercept.*;

public class MatchParameterInterceptor 
	extends PredicateInterceptorSupport
{
	public
	MatchParameterInterceptor (final Map<String, String> props)
	{
		super (props); 
		return;
	}

	interface PROP
	{
		public String NAME = "name"; 
		public String PATTERN = "pattern"; 
	}
	
	@Override
	public
	void init ()
		throws Exception
	{
		super.init () ;
		name = require (PROP.NAME); 
		pattern = Pattern.compile (require (PROP.PATTERN)); 
		return; 
	}
	
	protected String name; 
	protected Pattern pattern; 
	
	@Override
	public 
	boolean evaluate (final HttpServletRequest req, final HttpServletResponse res) 
	{
		final String val = req.getParameter (name); 
		return val != null && pattern.matcher (val).matches (); 
	}
}

// EOF