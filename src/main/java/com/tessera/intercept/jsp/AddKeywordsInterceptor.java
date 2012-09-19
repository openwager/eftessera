package com.tessera.intercept.jsp;

import java.util.*;

import javax.servlet.http.*;

import com.tessera.dispatch.*;
import com.tessera.intercept.*;

/**
 * 
 * @author crawford
 *
 */

public class AddKeywordsInterceptor
	extends InterceptorSupport
{
	public
	AddKeywordsInterceptor (final Map<String, String> props)
	{
	    super (props);
	    return;
	}
	
	public interface PROP
	{
	    public static final String CONTENT = "content";
	}
	
	protected JexlStringExpression contentE; 
	
	@Override
	public
	void init ()
	    throws Exception
	{
		super.init (); 
	    contentE = new JexlStringExpression (require (PROP.CONTENT));
	    return;
	}
	
	@SuppressWarnings("unchecked")
	public
	Alteration intercept (final HttpServletRequest req, final HttpServletResponse res, DispatchContext dc)
	    throws Exception
	{
	    List<String> keywords = (List<String>) req.getAttribute (JspWidgetUtil.ATTR.KEYWORDS);
	    if (keywords == null) {
	        keywords = new ArrayList<String> ();
	        req.setAttribute (JspWidgetUtil.ATTR.KEYWORDS, keywords);
	    }
	    
	    final String content = contentE.evaluate (req);
	    keywords.add (content);
	    return NO_ALTERATION;
	}
}

//EOF