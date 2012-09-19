package com.tessera.intercept;

import java.util.*;

import javax.servlet.http.*;

import com.tessera.dispatch.*;
import com.weaselworks.util.*;

/**
 * TODO: Comment goes here. 
 * 
 * @author Lee Crawford (lee.crawford@gmail.com) 
 * @copyright Copyright (c) 2009, Weaselworks, Inc. 
 */

public class InterceptorChain
	extends InterceptorSupport
		implements Interceptor
{
	public
	InterceptorChain ()
	{
		this (new HashMap<String, String> ()); 
		return; 
	}
	
	public 
	InterceptorChain (final Map<String, String> props)
	{
		super (props); 
		return; 
	}
	

	public
	void setDispatcher (final Dispatcher disp)
	{
		for (final Interceptor itor : is) { 
			itor.setDispatcher (disp); 
		}
		return ;
	}
	
	protected List<Interceptor> is = new ArrayList<Interceptor> (); 
	
	public
	List<Interceptor> getUnderlying ()
	{
	    return is;
	}
	
	public
	int getInterceptorCount ()
	{
	    return is.size ();
	}
	
	public
	Interceptor getInterceptor (final int i)
	{
	    return is.get (i); 
	}
	
	public
	Object removeInterceptor (final int i)
	{
		return is.remove (i); 
	}
	
	public
	void addInterceptor (final Interceptor i)
	{
		is.add (i); 
		return; 
	}
	
	public
	Iterator<Interceptor> getInterceptors ()
	{
	    return is.iterator ();
	}
	
	public
	void raise (final int index)
	{
	    if (index <= 0 || index >= is.size ()) {
	        throw new IllegalArgumentException ("Index out of bounds.");
	    }
	    is.add (index - 1, is.remove (index));
	    return;
	}
	
	
	public
	void lower (final int index)
	{
	    if (index < 0 || index >= is.size () -1) {
	        throw new IllegalArgumentException ("Index out of bounds.");
	    }
	    is.add (index + 1, is.remove (index));
	    return;
	}
	
	/**
	 * @see Interceptor#intercept(HttpServletRequest, HttpServletResponse, Action)
	 */
	
	public 
	Alteration intercept (HttpServletRequest req, HttpServletResponse res, DispatchContext dc)
		throws Exception
	{
//		final Audit audit = dc.getAudit (); 
		for (final Interceptor i : is) {
			try { 
//				audit.push ("Action " + i.getDescription ());
				final Alteration alt = i.intercept (req, res, dc);
				if (alt != NO_ALTERATION) {
					return alt;  
				}
			}
			finally { 
//				audit.pop (); 
			}
		}
		
		return NO_ALTERATION; 
	}
	
	/**
	 * @see Object#toString()
	 */
	
	@Override
	public
	String toString ()
	{
	    String s = getClass ().getName () + "[";
	    s += "is=" + is;
	    return s + "]";
	}
	
	/**
	 * @see Object#hashCode()
	 */
	
	@Override
	public
	int hashCode ()
	{
	    final HashBuilder h = new HashBuilder ();
	    h.add (is);
	    return h.getHashCode ();
	}
	
	/**
	 * @see Object#equals(Object)
	 */
	
	@Override
	public
	boolean equals (Object obj)
	{
		if (! (obj instanceof InterceptorChain)) { 
	        return false;
	    }
	    final InterceptorChain il = (InterceptorChain) obj;
	    return Util.isEqual (is, il.is);
	}

	@Override
    public void removeProperty (String name)
    {
		props.remove (name); 
		return; 	    
    }
}

//EOF
