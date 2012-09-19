package com.tessera.intercept;

import java.util.*;

import com.weaselworks.util.*;

/**
 * 
 * @author crawford
 *
 */

// package
abstract class DispatchTarget
{
    protected
    DispatchTarget ()
    {
        setProperties (new HashMap<String, String> ());
        return;
    }

    @SuppressWarnings("unchecked")
	protected
    DispatchTarget (final Map<String, String> props)
    {
        final Map<String, String> copy = (Map<String, String>) ((HashMap) props).clone ();
        setProperties (copy);
        return;
    }
    
//    public
//    void init ()
//        throws Exception
//    {
//        // EMPTY
//        return;
//    }

    protected Map<String, String> props;

    /**
     * @see Interceptor#getProperties()
     */

    public
    Map<String, String> getProperties ()
    {
        return this.props;
    }

    protected
    void setProperties (final Map<String, String> props)
    {
        this.props = props;
        return;
    }

    /**
     * @see Interceptor#setProperty(String, String)
     */

    public
    void setProperty (String name, String value)
    {
        if (value == null) {
            props.remove (name);
        } else {
            props.put (name, value);
        }
        return;
    }

    public
    String getProperty (final String name)
    {
        return props.get (name);
    }

    public
    String getProperty (final String name, final String defaultValue)
    {
        final String val = getProperty (name);
        return val == null? defaultValue : val;
    }

    public
    void removeProperty (final String name)
    {
	    props.remove (name); 
	    return; 	    
    }

    public
    boolean hasProperty (final String name)
    {
    	return props.containsKey (name);     	
    }
    
	protected
    String require (final String name)
        throws ConfigurationException
    {
        final String val = props.get (name);
        if (StringUtil.isEmpty (val)) {
            throw new ConfigurationException ("Required property '" + name + "' empty.");
        }
        return val;
    }

    /**
     * @see Object#toString
     */

    @Override
	public
    String toString ()
    {
        String s = getClass ().getName () + "[";
        s += paramString ();
        return s + "]";
    }

    protected
    String paramString ()
    {
        return "props=" + props;
    }
}

// EOF