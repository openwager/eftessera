package com.tessera.intercept.breadcrumb;

/**
 *
 * @author Lee Crawford (crawford@etherfirma.com)
 */

public class BreadcrumbEntry
{
	public
	BreadcrumbEntry (final String label, final String path)
	{
		setLabel (label) ;
		setPath (path); 
		return; 
	}
	
	protected String label; 
	public String getLabel () { return this.label; } 
	public void setLabel (final String label) { this.label = label; return; }
	
	protected String path; 
	public String getPath () { return this.path; } 
	public void setPath (final String path) { this.path = path; return; } 

	public
	String toString ()
	{
		String s = getClass ().getName () + "[";
		s += "label=" + getLabel (); 
		s += ",path=" + getPath (); 
		return s + "]"; 
	}
}

// EOF