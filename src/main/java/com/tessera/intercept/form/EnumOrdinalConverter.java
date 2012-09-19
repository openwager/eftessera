package com.tessera.intercept.form;

import java.lang.reflect.*;

import org.apache.commons.beanutils.*;

import com.weaselworks.util.*;

/**
 * 
 * @author crawford
 *
 * @param <T>
 */

public class EnumOrdinalConverter <T extends Enum<T>>
	implements Converter
{
	@SuppressWarnings("unchecked")
	public
	EnumOrdinalConverter (final Class<T> type)
	{
        this.type = type;
        try {
            final Method method = type.getMethod ("values", new Class [0]);
            values = (T[]) method.invoke( null, new Object [0] );
        }
        catch (final Exception e) {
            throw new IllegalStateException (e);
        }
        return;
	}
	
	protected Class<T> type;
	protected T [] values;
	
	/**
	 * 
	 */
	
	@SuppressWarnings("unchecked")
	public
	T convert (final Class arg0, final Object arg1)
	{
        if (arg1 instanceof String) {
            final String val = (String) arg1;
            if (StringUtil.isEmpty (val)) {
                return null;
            } else {
                return values [Integer.parseInt (val)];
            }
        } else if (arg1 instanceof Integer) {
            return values [(Integer) arg1];
        } else {
            throw new IllegalArgumentException ("Cannot convert: " +	arg1.getClass().getName ());
        }

        // NOT REACHED
	}
	
	/**
	 * A static method used to create a new converter for the specified enumeration type.
	 *
	 * @param <T>
	 * @param type
	 * @return
	 */
	
	public static <T extends Enum<T>>
	Converter newInstance (final Class<T> type)
	{
	    return new EnumOrdinalConverter<T> (type);
	}
}
// EOF