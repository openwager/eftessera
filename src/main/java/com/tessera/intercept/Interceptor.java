package com.tessera.intercept;

import java.util.*;

import javax.servlet.http.*;

import com.tessera.dispatch.*;

/**
 * TODO: Comment goes here. 
 * 
 * @author Lee Crawford (lee.crawford@gmail.com) 
 * @copyright Copyright (c) 2009, Weaselworks, Inc. 
 */

public interface Interceptor
{
	/**
	 * 
	 * @throws Exception
	 */
	
    public
    void init ()
        throws Exception;

    /**
     * 
     * @param req
     * @param res
     * @param dc
     * @return
     * @throws Exception
     */
    
    public
    Alteration intercept (HttpServletRequest req, HttpServletResponse res, DispatchContext dc)
        throws Exception;

    /**
     * Used to return a textual description of the processing activity performed by the
     * interceptor. This is included in the request audit trail which may optional be viewed during
     * develop/debug cycles.
     *
     * @return
     */

//    public
//    String getDescription ();

    /**
     * 
     * @return
     */
    
    public
    Map<String, String> getProperties ();

    /**
     * 
     * @param name
     * @param value
     */
    
    public
    void setProperty (String name, String value);

    /**
     * 
     * @param name
     * @return
     */
    
    public
    String getProperty (String name); 
    
    /**
     * 
     */
    
    public
    void removeProperty (String name); 
    
    /**
     * TODO: Comment me
     * @param enabled
     */
    
    public void setEnabled (boolean enabled);
    public boolean getEnabled (); 

    /**
     * 
     * @param disp
     */
    
    public
    void setDispatcher (Dispatcher disp); 
}

// EOF