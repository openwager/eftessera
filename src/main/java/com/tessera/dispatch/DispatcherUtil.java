package com.tessera.dispatch;

import java.util.*;

import org.apache.log4j.*;

import com.tessera.intercept.*;
import com.weaselworks.util.*;

/**
 * 
 * @author crawford
 *
 */

public class DispatcherUtil
{
	protected static final Logger logger = Logger.getLogger (DispatcherUtil.class); 
	
	private
	DispatcherUtil ()
	{
		return; 
	}
	
	/**
	 * 
	 * @param disp
	 * @param os
	 */
	
	public static
	void dump (final Dispatcher disp)
	{
		assert disp != null; 
		
		logger.info ("Dispatcher (" + disp.getClass ().getSimpleName () + ") {");
		final StandardDispatcher sd = (StandardDispatcher) disp; 
		final DispatchPath dp = sd.getDispatchPath (); 
		final DispatchNode node = dp.getRoot (); 
		dump ("$ROOT", node, 1); 
		logger.info ("}"); 
		return; 
	}

	protected static final String PREFIX = "  "; 
	
	/**
	 * 
	 * @param label
	 * @param node
	 * @param indent
	 * @param out
	 */
	
	protected static
	void dump (final String label, final DispatchNode node, final int indent)
	{
		assert label != null; 
		assert node != null; 
		assert indent > 0; 
		
		final String p1 = StringUtil.repeat (PREFIX, indent);
		final String p2 = StringUtil.repeat (PREFIX, indent + 1);
		
		logger.info (p1 + "\"" + label + "\"" + " {");
		final Map<String, DispatchNode> subnodes = node.getNodes (); 
		for (final String key : subnodes.keySet ()) { 
			final DispatchNode subnode = subnodes.get (key);
			dump (key, subnode, indent + 1); 
		}
		final Map<String, InterceptorChain> subchains = node.getChains ();
		for (final String key : subchains.keySet ()) { 
			final InterceptorChain subchain = subchains.get (key);
			logger.info (p2 + "\"" + key + "\" -> [" + subchain.getInterceptorCount () + "]"); 
		}
		logger.info (p1 + "}");
		return; 
	}
}

// EOF
