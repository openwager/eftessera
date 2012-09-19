package com.tessera.intercept.form;

import java.util.*;

import javax.servlet.http.*;

import org.apache.log4j.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.*;
import com.weaselworks.util.*;

/**
 * 
 * @author crawford
 *
 */

public class InstantiateFormInterceptor
	extends FormInterceptorSupport
{
	private static final Logger logger = Logger.getLogger (InstantiateFormInterceptor.class); 
	
	public
	InstantiateFormInterceptor (final Map<String, String> props)
	{
		super (props); 
		return; 
	}
	
	public static final ClobberMode DEFAULT_CLOBBER_MODE = ClobberMode.NONE; 
	protected ClobberMode clobber = DEFAULT_CLOBBER_MODE;   
	public ClobberMode getClobberMode () { return this.clobber; } 
	public void setClobberMode (final ClobberMode clobber) { this.clobber = clobber; return; } 
	
	@Override
	public
	void init ()
		throws Exception
	{
		super.init (); 
		final String cstr = getProperty (PROP.CLOBBER); 
		if (! StringUtil.isEmpty (cstr)) { 
			setClobberMode (ClobberMode.valueOf (cstr)); 
		}
		return; 
	}
	
	/**
	 * @see InterceptorSupport#intercept(HttpServletRequest, HttpServletResponse, DispatchContext)
	 */
	
    public Alteration intercept (final HttpServletRequest req, final HttpServletResponse res, final DispatchContext dc)
        throws Exception
    {
		final String attr = getProperty (PROP.ATTR, DEFAULT.ATTR); 
		switch (getClobberMode ()) { 
			case ALWAYS: 
				break; 
				
			case NONE: 
				if (req.getAttribute (attr) != null) { 
					return NO_ALTERATION; 
				}
				break; 
				
			case NOT_SAME: 
				throw new IllegalStateException ("Unimplemented."); 			
		}
		
		final Form form = getFormFactory ().createForm ();
		logger.info ("Instantiated form " + form.getClass().getName ()); 
		req.setAttribute (attr, form); 		
		return NO_ALTERATION; 
    }
}

// EOF