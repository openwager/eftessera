package com.tessera.intercept.util;

import java.io.*;
import java.util.*;

import javax.servlet.http.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.*;
import com.weaselworks.util.*;
import com.weaselworks.util.Base64;

/**
 * 
 * @author crawford
 *
 */

public class RecoverRedirectInterceptor
	extends InterceptorSupport
{
    public
    RecoverRedirectInterceptor (final Map<String, String> props)
    {
        super (props);
        return;
    }

	public 
	Alteration intercept (final HttpServletRequest req, final HttpServletResponse res, DispatchContext dc)
		throws Exception
	{
		if (! RecoverableRedirectInterceptor.hasRecoveryContext (req)) {  
			return NO_ALTERATION; 
		}

		String method = req.getParameter (RecoverableRedirectInterceptor.PARAM.METHOD);
		method = (method != null) ? new String (Base64.decode (method)) : "GET";
		String uri = req.getParameter (RecoverableRedirectInterceptor.PARAM.URI); 
		uri = new String (Base64.decode (uri)); 
				
		final PrintWriter out = res.getWriter ();
		res.setContentType ("text/html"); 
		if ("GET".equals(method)) {
			out.println("<html><head><script type=\"text/javascript\">document.location.replace(\"" + uri + "\");</script></head><body></body</html>");
		}
		else {
			out.println ("<html><body>"); 
			out.println ("<form method=\"" + method + "\" action=\"" + uri + "\">"); 
			int cnt = 0; 
			while (true) { 
				String name = req.getParameter (RecoverableRedirectInterceptor.PARAM.NAME + cnt); 
				if (StringUtil.isEmpty (name)) { 
					break; 
				}
				String value = req.getParameter (RecoverableRedirectInterceptor.PARAM.VALUE + cnt);
				name = new String (Base64.decode (name));
				value = new String (Base64.decode (value));
				out.println ("<input type=\"hidden\" name=\"" + name + "\" value=\"" + HTMLUtil.escape (value) + "\"/>"); 		
				cnt ++; 
			}
			out.println ("</form><script>document.forms[0].submit();</script>"); 
			out.println ("</body></html>"); 
		}
		out.flush (); 
		out.close (); 
		return ABORT; 
	}
}

// EOF