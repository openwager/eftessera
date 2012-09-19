package com.tessera.intercept.util;

import java.io.*;
import java.util.*;

import javax.servlet.http.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.*;
import com.weaselworks.util.*;

/**
 * 
 * @author crawford
 *
 */

public class ActionSubcallInterceptor
	extends InterceptorSupport
{
    public
    ActionSubcallInterceptor (final Map<String, String> props)
    {
        super (props);
        return;
    }

    public interface PROP
    {
        public static final String ACTION = "action";
        public static final String REPEATABLE = "repeatable";
    }

    @Override
    public
    void init ()
        throws Exception
    {
    	super.init (); 
        actionExpr = new JexlStringExpression (require (PROP.ACTION));
        final String val = getProperty (PROP.REPEATABLE);
        if (! StringUtil.isEmpty (val)) {
        	repeatable = Boolean.parseBoolean (val);
        }
        return;
    }

    protected JexlStringExpression actionExpr; 
    protected boolean repeatable = false;

    public
    Alteration intercept (final HttpServletRequest req, final HttpServletResponse res, DispatchContext dc)
        throws Exception
    {
        // Make sure we don't doubly invoke a subcall during forwarding by keeping
        // a record in the request context

        if (! repeatable) {
                final String key = Dispatcher.ATTR.SUBCALL + ((Object) this).hashCode ();
                if (req.getAttribute (key) != null) {
                    return NO_ALTERATION;
                } else {
                    req.setAttribute (key, this);
                }
        }

        // Get a reference to the action that we're making a subcall to

        final Dispatcher disp = dc.getDispatcher ();
        final String action = actionExpr.evaluate(req);
        final Interceptor sub = disp.findAction (action);
        if (sub == null) {
            throw new IOException ("Action '" + action + "' not found.");
        }

        return sub.intercept (req, res, dc);
    }
}

// EOF