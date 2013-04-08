package com.tessera.intercept.util;

import java.io.*;
import java.util.*;

import javax.servlet.http.*;

import org.apache.log4j.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.*;
import com.weaselworks.util.*;

/**
 * 
 * @author crawford
 *
 */

public class SendFormRedirectInterceptor
	extends InterceptorSupport
{
    protected static final Logger logger = Logger.getLogger (SendFormRedirectInterceptor.class);

    public
    SendFormRedirectInterceptor (final Map<String, String> props)
    {
        super (props);
        return;
    }

    /**
     * @see com.twofish.ludwig.intercept.Interceptor#init()
     */

    @Override
	public
    void init ()
        throws Exception
    {
    	super.init (); 
//    	uri = require (PROP.URI);
    	uri = new JexlStringExpression (require (PROP.URI)); 
    	method = getProperty (PROP.METHOD, "GET");
        
    	int cnt = 0;
        while (true) {
            final String param = getProperty (PROP.PARAM + cnt + "." + PROP.NAME);
            if (StringUtil.isEmpty (param)) {
                break;
            }
            final String value = getProperty (PROP.PARAM + cnt + "." + PROP.VALUE);
            if (StringUtil.isEmpty (value)) {
                throw new ConfigurationException ("Missing '" + PROP.VALUE + cnt + "' property.");
            }
            final RedirectParameter entry = new RedirectParameter (param, value);
            params.add (entry);
            cnt++;
        }
        return;
    }

    interface PROP
    {
        public static final String METHOD = "method";
        public static final String URI = "uri";
        public static final String PARAM = "param.";
        public static final String NAME = "name";
        public static final String VALUE = "value";
    }

    protected String method; 
//    protected String uri; 
    protected JexlStringExpression uri; 

    /**
     *
     */

    public static class RedirectParameter
    {
        public
        RedirectParameter (final String param, final String expr)
        	throws Exception
        {
            setParameter (param);
            setExpression (new JexlStringExpression (expr));
            return;
        }

        protected String param;
        public String getParameter () { return this.param; }
        public void setParameter (final String param) { this.param = param; return; }

        protected JexlStringExpression expr;
        public JexlStringExpression getExpression () { return this.expr; }
        public void setExpression (final JexlStringExpression expr) { this.expr = expr; return; }
    }

    /**
     *
     */

    public List<RedirectParameter> params = new ArrayList<RedirectParameter> ();

    /**
     * @see Interceptor#intercept(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.twofish.ludwig.action.Action)
     */

    public
    Alteration intercept (final HttpServletRequest req, final HttpServletResponse res, DispatchContext dc)
        throws Exception
    {
        res.reset ();
        res.setContentType ("text/html"); 
        final Writer writer= res.getWriter ();
        final PrintWriter out = new PrintWriter (writer);

        // Spit out an auto-submitting form.

        out.println ("<html><body>");
        out.println ("<form action=\"" + uri.evaluate (req) + "\" method=\"" + method + "\" name=\"redir\">");
        
        for (final RedirectParameter param : params) { 
        	try { 
        		String result = param.getExpression ().evaluate (req);
//        		result = URLEncoder.encode (result, "UTF-8");
//        		result = escape (result); 
        		result = HTMLUtil.escape (result); 
        		if (result != null) { 
        			out.println ("<input type=\"hidden\" name=\"" + param.getParameter () + "\" value=\"" + result + "\"/>");
        		}
            }
            catch (final NullPointerException npe) {
                logger.error ("Expression evaluation failed (" + param.getExpression ().getExpression () + ").");
            }
        }
        out.println ("</form>");
        out.println ("<script>");
        out.println ("  document.redir.submit ();");
        out.println ("</script>");
        out.println ("</body></html>");
        out.flush ();
        out.close ();

        return ABORT;
    }
    
    public static
    String escape (final String s) 
    {
    	return s.replace ("\"", "\\\\\""); 
    }
    
//    public static
//    void main (final String [] args)
//    {
//    	final String [] strs = { "a", "a\nb", "a \"wookie\" was here" }; 
//    	for (final String str : strs) { 
//    		System.err.println ("/" + str + "/ -> /" + escape (str) + "/"); 
//    	}
//    	return; 
//    }
}

// EOF