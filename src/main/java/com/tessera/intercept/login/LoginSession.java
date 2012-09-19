package com.tessera.intercept.login;

import java.util.*;

/**
 * 
 * @author crawford
 *
 */

public class LoginSession
{
	public
	LoginSession ()
	{
		return; 
	}
	
	public
	LoginSession (final Object id, final String username) 
	{
		setId (id); 
		setUsername (username); 
		return; 
	}
	
	protected Object id; 
	public Object getId () { return this.id; } 
	public void setId (final Object id) { this.id = id; return; } 
	
	protected String username; 
	public String getUsername () { return this.username; } 
	public void setUsername (final String username) { this.username = username; return; }
	
	protected long created = System.currentTimeMillis ();  
	public long getCreated () { return this.created; } 
	public long getAge () { return System.currentTimeMillis () - created; } 

	protected Map<String, Object> attrs = new HashMap<String, Object> (); 
	public Map<String, Object> getAttributes () { return this.attrs; } 
	public Object getAttribute (final String key) { return this.attrs.get (key); }
	public void removeAttribute (final String key) { this.attrs.remove (key); return; } 
	public void setAttribute (final String key, final Object value) { this.attrs.put (key, value); return; } 
	public boolean hasAttribute (final String key) { return this.attrs.containsKey (key); } 
	
	@Override
	public final 
	String toString ()
	{
		final StringBuffer buf = new StringBuffer (); 
		buf.append (getClass().getName ());
		buf.append ('['); 
		paramString (buf); 
		buf.append (']'); 
		return buf.toString (); 
	}

	protected
	void paramString (final StringBuffer buf)
	{
		buf.append ("created="); 
		buf.append (created); 
		buf.append (",id=");
		buf.append (id);
		buf.append (",username=");
		buf.append (username);
		buf.append (",attrs="); 
		buf.append (attrs); 
		return; 
	}
}

// EOF