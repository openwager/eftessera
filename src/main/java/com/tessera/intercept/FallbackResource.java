package com.tessera.intercept;

/**
 * 
 * @author crawford
 *
 * @param <A>
 */

// package
abstract class FallbackResource<Type>
{
	public 
	FallbackResource (final Type resource)
	{
		setResource (resource); 
		return; 
	}
	
	public
	FallbackResource (final Type resource, final FallbackResource<?> next)
	{
		setResource (resource); 
		setNext (next); 
	}
	
	protected Type resource; 
	public Type getResource () { return this.resource; } 
	public void setResource (final Type resource) { this.resource = resource; return; } 
	
	protected FallbackResource<?> next; 
	public FallbackResource<?> getNext () { return this.next; } 
	public void setNext (final FallbackResource<?> next) { this.next = next; return; }
	
	public
	Object get (final String key)
	{
		Object o = doGet (key); 
		if (o != null) { 
			return o; 
		}
		if (next != null) { 
			return next.get (key); 
		}
		return null; 
	}
	
	abstract protected
	Object doGet (final String key); 
}
