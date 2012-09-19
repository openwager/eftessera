package com.tessera.intercept.login;

import java.util.*;

import javax.servlet.http.*;

/**
 * 
 * @author crawford
 *
 */

@SuppressWarnings ("serial")
public class AjaxResponse
	extends HashMap<String, Object>
{
	public
	AjaxResponse ()
	{
		return; 
	}

	interface DEFAULT
	{
		public String ATTR = "json"; 
	}
	
	public
	AjaxResponse (final HttpServletRequest req)
	{
		this (req, DEFAULT.ATTR); 
		return; 
	}
	
	public
	AjaxResponse (final HttpServletRequest req, final String attr)
	{
		req.setAttribute (attr, this); 
		return; 
	}
	
	interface FIELD
	{
		public String ERROR_CODE = "errorCode"; 
		public String ERROR_MESSAGE = "errorMessage"; 
	}
	
	protected
	void setResponse (final int errCode)
	{
		put (FIELD.ERROR_CODE, "" + errCode); 
		return; 
	}
	
	protected 
	void setResponse (final int errCode, final String errMessage)
	{
		put (FIELD.ERROR_CODE, "" + errCode); 
		put (FIELD.ERROR_MESSAGE, errMessage); 
		return; 
	}
	
	protected
	void setValue (final String field, final Object value)
	{
		put (field, value); 
		return; 
	}
}

// EOF