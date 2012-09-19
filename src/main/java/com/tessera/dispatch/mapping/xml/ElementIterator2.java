package com.tessera.dispatch.mapping.xml;

import java.util.*;

import org.w3c.dom.*;

/**
 * TODO: Comment goes here. 
 * 
 * @author Lee Crawford (lee.crawford@gmail.com) 
 * @copyright Copyright (c) 2009, Weaselworks, Inc. 
 */

public class ElementIterator2
	implements Iterable<Element>, Iterator<Element>
{
	public
	ElementIterator2 (final Element parent)
	{
		this.parent = parent;
		final NodeList nlist = parent.getChildNodes (); 
		for (int i = 0; i < nlist.getLength (); i ++) { 
			final Node node = nlist.item (i); 
			if (node.getNodeType () == Node.ELEMENT_NODE) { 
				els.add ((Element) node); 
			}
		}
		return;
	}

	protected List<Element> els = new ArrayList<Element> (); 	
	protected Element parent;
	protected int index;
	
	public
	Iterator<Element> iterator ()
	{
		return this;
	}

	public
	boolean hasNext ()
	{
		return index < els.size (); 
	}

	public
	Element next ()
	{
		if (hasNext ()) { 
			return els.get (index ++); 
		} else { 
			return null; 
		}
	}

	public
	void remove ()
	{
		throw new IllegalStateException ("Unsupported operation.");
		// NOT REACHED
	}
}

// EOF 