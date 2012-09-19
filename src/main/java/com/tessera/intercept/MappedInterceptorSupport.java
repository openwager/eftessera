package com.tessera.intercept;

import java.util.*;

import javax.servlet.http.*;

import com.tessera.dispatch.*;

/**
 * 
 * @author crawford
 *
 */

abstract public class MappedInterceptorSupport
	extends InterceptorSupport
	    implements MappedInterceptor
{
	protected 
	MappedInterceptorSupport (final Map<String, String> props)
	{
	    super (props);
	    return;
	}
	
	@Override
	public
	void init ()
		throws Exception
	{
		super.init (); 
		this.dcase = getProperty ("default");
		return; 
	}
	
	@Override
	public
	void setDispatcher (final Dispatcher disp)
	{
		for (final InterceptorChain ic : cases.values ()) { 
			ic.setDispatcher (disp) ;
		}
		return;
	}
	
	/**
	 * Contains the default case
	 */
	
	protected String dcase; 
	
	/**
	 * Contains the case mappings.
	 */
	
	protected Map <String, InterceptorChain> cases = new LinkedHashMap <String, InterceptorChain> ();

	/**
	 * @see com.twofish.ludwig.intercept.MappedInterceptor#getCases()
	 */
	
	public
	Set<String> getCases ()
	{
	    return cases.keySet ();
	}
	
	/**
	 * @see MappedInterceptor#getCase(String)
	 */
	
	public
	InterceptorChain getCase (final String name)
	{
	    return cases.get (name);
	}
	
	/**
	 * @see MappedInterceptor#getCase(String, boolean)
	 */
	
	public
	InterceptorChain getCase (final String name, final boolean create)
	{
		InterceptorChain list = cases.get (name);
	    if (create) {
	        if (list == null) {
	            list = new InterceptorChain ();
	            cases.put (name, list);
	        }
	    }
	    return list;
	}
	
	/**
	 * @see MappedInterceptor#
	 * @param req
	 * @param res
	 * @param action
	 * @return
	 * @throws Exception
	 */
	
	public 
	Alteration intercept (final HttpServletRequest req, final HttpServletResponse res, DispatchContext dc)
	    throws Exception
	{
	    final String val = map (req, res);
	    if (val != null) {
	        final InterceptorChain list = getCase (val);
	        if (list != null) {
	            return list.intercept (req, res, dc);
	        }
	    }
	    if (dcase != null) { 
	        final InterceptorChain dlist = getCase (dcase); 
	        if (dlist != null) { 
	        	return dlist.intercept (req, res, dc); 
	        }   
	    }
	    return NO_ALTERATION;
	}
}

//EOF 