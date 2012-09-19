package com.tessera.intercept.form;

import com.weaselworks.svc.*;

/**
 * 
 * @author crawford
 *
 */

public class FetchRequestForm
	extends Form
{
	public
	FetchRequestForm ()
	{
		return; 
	}
	
	protected FetchRequest fetchRequest = new FetchRequest (0, 20, false); 
	public FetchRequest getFetchRequest () { return this.fetchRequest; } 
	public void setFetchRequest (final FetchRequest fetchRequest) { this.fetchRequest = fetchRequest; return; } 
}

// EOF