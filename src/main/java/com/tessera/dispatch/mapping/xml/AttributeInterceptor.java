package com.tessera.dispatch.mapping.xml;

import java.util.*;

import org.w3c.dom.*;

class AttributeIterator 
	implements Iterable<Node>, Iterator<Node>
{
	public 
	AttributeIterator (final Element parent)
	{
		this.parent = parent;
		this.nnm = parent.getAttributes (); 
		return;
	}
	
	protected Element parent;
	protected NamedNodeMap nnm;
	protected int index;
	protected Node next;
	
	public 
	Iterator<Node> iterator ()
	{
		return this;
	}
	
	public
	boolean hasNext ()
	{
		if (next != null) {
			return true;
		}
		while (index < nnm.getLength ()) {
			final Node node = nnm.item (index++);
			if (node.getNodeType () == Node.ATTRIBUTE_NODE) {
				next = node;
				return true;
			}
		}
		return false;
	}
	
	public 
	Node next ()
	{
		if (hasNext ()) {
			final Node ret = next;
			next = null;
			return ret;
		} else {
			return null;
		}
	}
	
	public 
	void remove ()
	{
		throw new IllegalStateException ("Operation unsupported."); 
		// NOT REACHED
	}
}

//EOF