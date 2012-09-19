package com.tessera.dispatch;

import java.util.*;
import java.util.concurrent.*;

import com.tessera.dispatch.mapping.*;
import com.tessera.intercept.*;

/**
 * TODO: Comment goes here. 
 * 
 * @author Lee Crawford (lee.crawford@gmail.com) 
 * @copyright Copyright (c) 2009, Weaselworks, Inc. 
 */

public class DispatchNode
{
	public
	DispatchNode ()
	{
		return; 
	}
	
	/**
	 * The symbol that is recognized as the wildcard path (accepts any
	 * textual content as a match). 
	 */
	
	public static final String WILDCARD = "*";
	
	/**
	 * Contains the wildcard interceptor if this is a wildcard node. 
	 */
	
	protected boolean wildcard;
	
	public
	boolean isWildcard ()
	{
		return wildcard; 
	}
	
	/**
	 * 
	 * @param path
	 * @param chain
	 * @throws MappingException
	 */
	
	public synchronized
	void register (final Iterator<String> iter, final InterceptorChain chain) 
		throws MappingException
	{
		assert iter != null; 
		assert chain != null; 
		
		final String elem = iter.next (); 
		if (elem == null) { 
			throw new MappingException ("Null element in path.");  
		}
		if (WILDCARD.equals (elem)) {
			if (! wildcard) { 
				if (chains.size () > 0 || nodes.size () > 0) { 
					throw new MappingException ("Can't create wildcard over non-wildcard action.");
				}
			}
			wildcard = true;
		}		

		if (iter.hasNext ()) {
			DispatchNode node = nodes.get (elem); 
			if (node == null) { 
				node = new DispatchNode (); 
				nodes.put (elem, node); 
			} 
			node.register (iter, chain); 
		} else {
			chains.put (elem, chain);  
		}
		return; 
	}

	/**
	 * 
	 * @param iter
	 * @return
	 */
	
	public
	InterceptorChain lookup (final Iterator<String> iter)
	{ 
		assert iter.hasNext (); 
		
		String elem = iter.next ();
		if (wildcard) { 
			elem = WILDCARD; 
		}
		if (iter.hasNext ()) { 
			final DispatchNode node = nodes.get (elem);
			if (node == null) { 
				return null; 
			} else { 
				return node.lookup (iter); 
			}
		} else { 
			return chains.get (elem); 
		}
		
		// NOT REACHED
	}	
	
	/**
	 * Stores the mappings to the next element in the hierarchy
	 */
	
	protected Map<String, InterceptorChain> chains = new ConcurrentHashMap<String, InterceptorChain> ();
	
	public 
	Map<String, InterceptorChain> getChains ()
	{
		return chains; 
	}
	
	/**
	 * Stores the mappings to the next level in the hierarchy.
	 */
	
	protected Map<String, DispatchNode> nodes = new ConcurrentHashMap<String, DispatchNode> ();
	
	public
	Map<String, DispatchNode> getNodes ()
	{
		return nodes; 
	}

	/**
	 * 
	 * @param disp
	 */
	
	public
	void setDispatcher (final Dispatcher disp)
	{
	    for (final InterceptorChain chain : getChains ().values ()) { 
	    	chain.setDispatcher (disp);
	    }
	    for (final DispatchNode node : getNodes ().values ()) { 
	    	node.setDispatcher (disp); 
	    }
	    return;
	}
}

// EOF