package com.tessera.dispatch;

import java.util.*;
import java.util.concurrent.*;

import com.tessera.dispatch.mapping.*;
import com.tessera.intercept.*;
import com.tessera.util.*;

/**
 * TODO: Comment goes here. 
 * 
 * @author Lee Crawford (lee.crawford@gmail.com) 
 * @copyright Copyright (c) 2009, Weaselworks, Inc. 
 */

public class DispatchPath
{
	public
	DispatchPath ()
	{
		return; 
	}
	
	/**
	 * Stores the mappings. 
	 */
	
	protected DispatchNode node = new DispatchNode (); 

	/**
	 * Returns the root {@linkplain DispatchNode} used to store the tree of 
	 * actions that are mapped. 
	 * 
	 * @return the root DispatchNode
	 */
	
	public
	DispatchNode getRoot () 
	{
		return node; 
	}
	
	public synchronized
	void clear ()
	{
		node = new DispatchNode (); 
		clearCache (); 
		return; 
	}
	
	protected boolean cacheUpToDate = true; 
	protected Map<String, InterceptorChain> cache = new ConcurrentHashMap<String, InterceptorChain> (); 

	/**
	 * 
	 *
	 */
	
	public synchronized
	void clearCache ()
	{
		cache.clear (); 
		cacheUpToDate = false; 
		return; 
	}
	
	/**
	 * 
	 *
	 */
	
	public synchronized
	void warmCache ()
	{
		if (cacheUpToDate) { 
			fillCache (new ArrayList<String> (), node, cache); 
		}
		return; 
	}
	
	/**
	 * 
	 * @param path
	 * @param node
	 * @param cache
	 */
	
	protected synchronized
	void fillCache (final List<String> path, final DispatchNode node, final Map<String, InterceptorChain> cache) 
	{
		if (node.isWildcard ()) {
			return; 
		}
		final Map<String, InterceptorChain> chains = node.getChains (); 
		for (final String elem : chains.keySet ()) { 
			path.add (elem); 
			cache.put (toPath (path), chains.get (elem)); 
			path.remove (path.size () - 1); 
		}
		final Map<String, DispatchNode> nodes = node.getNodes (); 
		for (final String elem : nodes.keySet ()) {
			path.add (elem); 
			fillCache (path, nodes.get (elem), cache);
			path.remove (path.size () - 1); 
		}
		return; 
	}
	
	/**
	 * 
	 *
	 */
	
	protected 
	void dumpCache ()
	{
		System.err.println ("DispatchPath.cache {"); 
		for (final String key : cache.keySet ()) { 
			System.err.println ("  " + key); 
		}
		System.err.println ("}"); 
		return; 
	}
	
	/**
	 * 
	 * @param path
	 * @return
	 */

	protected static
	String toPath (final List<String> path) 
	{
		final StringBuffer buf = new StringBuffer ();
		final int len = path.size ();
		if (len == 0) { 
			buf.append ('/'); 
		} else { 
			for (int i = 0; i < len; i ++) { 
				if (i != 0 && i != len) { 
					buf.append ('/'); 				
				}
				buf.append (path.get (i)); 
			}
		}
		return buf.toString (); 
	}
	
	/**
	 * 
	 * @param path
	 * @param chain
	 * @throws MappingException
	 */
	
	public 
	void register (final String path, final InterceptorChain chain) 
		throws MappingException
	{
		assert path != null; 
		assert chain != null; 
		register (PathUtil.parsePath (path), chain);
		return; 
	}

	/**
	 * 
	 * @param path
	 * @param chain
	 * @throws MappingException
	 */
	
	public
	void register (final List<String> path, final InterceptorChain chain)
		throws MappingException
	{
		assert path != null; 
		assert chain != null; 
		node.register (path.iterator (), chain);
		return; 
	}
	
	/**
	 * 
	 * @param path
	 * @return
	 */
	
	public 
	InterceptorChain lookup (final String path)
	{		
		assert path != null; 
		return lookup (PathUtil.parsePath (path)); 
	}
	
	/**
	 * 
	 * @param path
	 * @return
	 */
	
	public
	InterceptorChain lookup (final List<String> path)
	{
		assert path != null; 
		assert path.size () > 0; 
		return node.lookup (path.iterator ()); 
	}	
}

// EOF