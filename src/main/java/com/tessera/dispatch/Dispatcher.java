package com.tessera.dispatch;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.tessera.intercept.*;

/**
 * TODO: Comment goes here. 
 * 
 * @author Lee Crawford (lee.crawford@gmail.com) 
 * @copyright Copyright (c) 2009, Weaselworks, Inc. 
 */

@SuppressWarnings("serial")
abstract public class Dispatcher
	extends HttpServlet
{
	public interface ATTR 
	{ 
		public String PREFIX = "_"; 
		public String THROWABLE = PREFIX + "t";
		public String SUBCALL = PREFIX + "c"; 
	}

	abstract public
	void reload ()
	    throws ServletException;

//	abstract 	public
//	void init ()
//	    throws ServletException;

	abstract public
	void loadMappings (String path)
		throws IOException;

	abstract public
	InterceptorChain findAction (String action);
	
	abstract public  
	void doGet (HttpServletRequest req, HttpServletResponse res)
	    throws ServletException, IOException;
				
	abstract public 
	void doPost (HttpServletRequest req, HttpServletResponse res)
	    throws ServletException, IOException;

	abstract public
	DispatchPath getDispatchPath (); 

	/**
	 * Stores the attributes for the dispatcher. This is a map of values that can be used 
	 * for data sharing and communication by the interceptors that are registered with this dispatcher. 
	 */
	
	protected Map<String, Object> attrs = new HashMap<String, Object> (); 
	
	public 
	Map<String, Object> getAttributes () 
	{
		return attrs;
	}
	
	public 
	Object getAttribute (String key)
	{
		return attrs.get (key); 
	}
	
	public
	boolean hasAttribute (String key)
	{
		return attrs.containsKey (key); 
	}
	
	public 
	void setAttribute (String key, Object obj)
	{
		attrs.put (key, obj); 
		return; 
	}

	public
	Object removeAttribute (String key)
	{
		return attrs.remove (key); 
	}
}

// EOF