package com.tessera.intercept;

import java.util.*;

/**
 * 
 * @author crawford
 *
 * @param <A>
 */

@SuppressWarnings("rawtypes")
// package
class FallbackMap
	implements Map
{
	public
	FallbackMap (final Map map, final FallbackResource<?> a)
	{
		setMap (map);
		setFallback (a); 
		return; 
	}
	
	protected FallbackResource<?> fallback; 
	public FallbackResource<?> getFallback () { return this.fallback; } 
	public void setFallback (final FallbackResource<?> fallback) { this.fallback = fallback; return; } 
	
	protected Map map; 
	public Map getMap () { return this.map; } 
	public void setMap (final Map map) { this.map = map; return; }
	
    public
    void clear ()
    {
    	map.clear (); 
    	return; 
    }
    
	public
	boolean containsKey (final Object key)
    {
    	return get (key) != null;  
    }	
	
    public 
    Object get (final Object key)
    {
    	Object o = map.get (key); 
    	if (o == null) { 
    		o = fallback.get ((String) key); 
    	}
    	return o; 
    }
	
    public 
    boolean containsValue (final Object value)
    {
    	return map.containsValue (value);  
    }
	    
    public Set entrySet ()
    {
    	return map.entrySet ();
    }
	    
    public
    boolean isEmpty ()
    {
    	throw new IllegalStateException ("Unimplemented."); 
    }
    
    public Set keySet ()
    {
    	throw new IllegalStateException ("Unimplemented."); 
    }
	
    public
    Object put (final Object key, final Object value)
    {
    	return map.put (key, value); 
    }
	
    public
    void putAll (final Map m)
    {
    	map.putAll (m); 
    	return; 
    }
	
    public
    Object remove (final Object key)
    {
    	return map.remove (key); 
    }
	
    public 
    int size ()
    {
    	return map.size ();  
    }
   
    public 
    Collection values ()
    {
    	return map.values ();  
    }
}

// EOF