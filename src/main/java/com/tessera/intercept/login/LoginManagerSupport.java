package com.tessera.intercept.login;

import java.util.*;

import javax.servlet.http.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.form.*;

/**
 * 
 * @author crawford
 *
 */

abstract public class LoginManagerSupport <FormType extends Form>
	implements LoginManager <FormType>
{
	protected
	LoginManagerSupport ()
	{
		this (new HashMap<String, String> ()); 
		return; 
	}
	
	protected
	LoginManagerSupport (final Map<String, String> props)
	{
		setProperties (props); 
		return;
	}	
	
	protected Map<String, String> props; 
	public Map<String, String> getProperties () { return this.props; }
	protected void setProperties (final Map<String, String> props) { this.props = props; return; } 
	public String getProperty (final String name) { return this.props.get (name); } 
	public void setProperty (final String name, final String value) { this.props.put (name, value); return; } 
	public void removeProperty (final String name) { this.props.remove (name); return; } 
	
	/**
	 * 
	 * @param req
	 * @param form
	 * @param dc
	 * @return
	 * @throws LoginException
	 */
	
	abstract protected
	LoginSession doLogin (final HttpServletRequest req, final FormType form, final DispatchContext dc)
		throws Exception; 
	
	/**
	 * @see LoginManager#login(HttpServletRequest)
	 */
	
	public
	LoginSession login (final HttpServletRequest req, final FormType form, final DispatchContext dc)
		throws Exception
	{
		if (isLoggedIn (req)) {
			form.addGlobalError ("Already logged in."); 
			return null; 
		}
		final LoginSession lsess = doLogin (req, form, dc);
		if (lsess != null) { 
			final HttpSession sess = req.getSession (); 
			sess.setAttribute (LoginManagerUtil.ATTR.LOGIN_SESSION, lsess); 
		} 
		return lsess; 
	}
	
	/**
	 * @see LoginManager#autologin(LoginSession, HttpServletRequest) 
	 */
	
	public
	void autologin (final LoginSession lsess, final HttpServletRequest req)
	{
		final HttpSession sess = req.getSession (); 
		sess.setAttribute (LoginManagerUtil.ATTR.LOGIN_SESSION, lsess); 
		return; 
	}
	
	/**
	 * @see LoginManager#getLoginSession(HttpServletRequest)
	 */
		
    public 
    LoginSession getLoginSession (final HttpServletRequest req)
    {
		final HttpSession sess = req.getSession (); 
	    return (LoginSession) sess.getAttribute (LoginManagerUtil.ATTR.LOGIN_SESSION); 
    }

	/**
	 * @see LoginManager#isLoggedIn(HttpServletRequest)
	 */
	
    public
    boolean isLoggedIn (HttpServletRequest req)
    {
	    return getLoginSession (req) != null; 
    }

	/**
	 * @see LoginManager#logout(HttpServletRequest)
	 */
	
    public 
    void logout (HttpServletRequest req)
		throws NotLoggedInException
    {
		if (! isLoggedIn (req)) { 
			throw new NotLoggedInException ("Not logged in."); 
		}
		final HttpSession sess = req.getSession (); 
		sess.removeAttribute (LoginManagerUtil.ATTR.LOGIN_SESSION); 
		return; 
    }
}

// EOF