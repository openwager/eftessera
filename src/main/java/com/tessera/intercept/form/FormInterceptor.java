package com.tessera.intercept.form;

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

abstract public class FormInterceptor <FormType>
	extends InterceptorSupport
{
	protected static final Logger logger = Logger.getLogger (FormInterceptor.class); 

	/**
	 * 
	 * @param props
	 * @param ftype
	 */
	
	public 
	FormInterceptor (final Map<String, String> props, final Class<FormType> ftype)
	{
		super (props); 
		setFormType (ftype); 
		return; 
	}
	
	protected Class<FormType> ftype; 
	public Class<FormType> getFormType () { return this.ftype; } 
	protected void setFormType (final Class<FormType> ftype) { this.ftype = ftype; return; } 

	/**
	 * @see Interceptor#intercept(HttpServletRequest, HttpServletResponse, DispatchContext)
	 */
	
    public
    Alteration intercept (final HttpServletRequest req, final HttpServletResponse res, final DispatchContext dc)
        throws Exception
    {
		final FormType form = findAttribute (ftype, req, getProperty (PROP.FORM, "form"));
		if (form == null) {			
			logger.warn ("Login attempt with no bound form."); 
			return failure (req); 
		}			
		
		return intercept (form, req, res, dc); 		
    }
	
	/**
	 * 
	 * @param form
	 * @param req
	 * @param res
	 * @param dc
	 * @return
	 */
	
	abstract protected <Type extends FormType>
	Alteration intercept (final Type form, final HttpServletRequest req, final HttpServletResponse res, final DispatchContext dc)
		throws Exception;
	
}

// EOF