package com.tessera.intercept.form;

import org.apache.commons.beanutils.*;

import com.weaselworks.util.*;

/**
 * 
 * @author crawford
 *
 */

public class ConverterWrapper
	implements Converter
{
	public
	ConverterWrapper (final Converter conv) 
	{
		assert conv != null; 
		setConverter (conv); 
		return; 
	}
	
	protected Converter conv; 
	public Converter getConverter () { return this.conv; } 
	public void setConverter (final Converter conv) { this.conv = conv; return; } 
	
	@SuppressWarnings("unchecked")
    public
    Object convert (final Class type, final Object value)
    {
		if (value == null) { 
			return null; 
		} else if (value instanceof String) { 
			final String str = (String) value; 
			if (StringUtil.isEmpty (str)) { 
				return null; 
			}
		}
		return getConverter ().convert (type, value); 
    }
}

// EOF