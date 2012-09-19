package com.tessera.dispatch.mapping.xml;

import java.util.*;

import org.w3c.dom.*;

/**
 * TODO: Comment goes here. 
 * 
 * @author Lee Crawford (lee.crawford@gmail.com) 
 * @copyright Copyright (c) 2009, Weaselworks, Inc. 
 */

class ElementIterator 
	implements Iterable<Element>, Iterator<Element>
{
	public
	ElementIterator (final Element parent)
	{
		this.parent = parent;
		this.nlist = parent.getChildNodes ();
		return;
	}

	protected Element parent;
	protected NodeList nlist;
	protected int index;
	protected Element next;

	public
	Iterator<Element> iterator ()
	{
		return this;
	}

	public
	boolean hasNext ()
	{
		if (next != null) {
			return true;
		}
		while (index < nlist.getLength ()) {
			final Node node = nlist.item (index ++);
			if (node.getNodeType () == Node.ELEMENT_NODE) {
				next = (Element) node;
				return true;
			}
		}
		return false;
	}

	public
	Element next ()
	{
		if (hasNext ()) {
			final Element ret = next; 
			next = null; 
			return ret; 
		} else {
			return null;
		}
	}

	public
	void remove ()
	{
		if (next != null) {
			parent.removeChild (next);
		} else {
			throw new IllegalStateException ("No active element.");
		}
		return;
	}
}

// EOF