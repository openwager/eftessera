package com.tessera.intercept.form;

import org.apache.commons.beanutils.*;

import com.weaselworks.util.*;

/**
 * 
 * @author crawford
 *
 * @param <T>
 */
public class EnumNameConverter <T extends Enum<T>>
	implements Converter
{
	public
	EnumNameConverter (final Class<T> type)
	{
	        setType (type);
	        return;
	}
	
	protected Class<T> type;
	public void setType (final Class<T> type) { this.type = type; return; }
	public Class<T> getType () { return this.type; }
	
	@SuppressWarnings("unchecked")
	public
	Object convert (Class arg0, Object arg1)
	{
        if (arg1 instanceof String) {
            final String val = (String) arg1;
            if (StringUtil.isEmpty (val)) {
                    return null;
            } else {
                    return Enum.valueOf (type, (String) arg1);
            }
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
	        return new EnumNameConverter<T> (type);
	}
}

//EOF
