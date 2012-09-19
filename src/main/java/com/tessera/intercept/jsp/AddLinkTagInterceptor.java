package com.tessera.intercept.jsp;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tessera.dispatch.DispatchContext;
import com.tessera.intercept.Alteration;
import com.tessera.intercept.InterceptorSupport;
import com.tessera.intercept.JexlStringExpression;
import com.weaselworks.util.StringUtil;

/**
 * 
 * @author mbarcelo
 *
 */
public class AddLinkTagInterceptor 
	extends InterceptorSupport 
{

	
	public 
	AddLinkTagInterceptor (Map<String, String> props) 
	{
		super(props);
	}
	
	interface PROP
	{
		public String REL_PREFIX = "rel.";
		public String HREF_PREFIX = "href.";
	}
	
	private Map<String,String> linkTags;
	private Map<JexlStringExpression, JexlStringExpression> exprMap;
	
	public
	void init ()
	{
		exprMap = new HashMap<JexlStringExpression, JexlStringExpression>();
		for (int cnt = 0; ; cnt ++) { 
			final String propRel = PROP.REL_PREFIX + cnt;
			final String propHref = PROP.HREF_PREFIX + cnt;
			
			try 
			{
				final String strKey = require(propRel);
				final String strValue = require(propHref);

				if(StringUtil.isEmpty(strValue))
				{
					break;
				}
				if(StringUtil.isEmpty(strKey))
				{
					continue;
				}
				
				final JexlStringExpression rel = new JexlStringExpression(strKey);
				final JexlStringExpression href = new JexlStringExpression(strValue);
				
				exprMap.put(rel, href);
			}
			catch (Exception e) 
			{
				break;
			}
		}
	}

	@Override
	public 
	Alteration intercept (HttpServletRequest req, HttpServletResponse res, DispatchContext dc) 
		throws Exception 
	{
		final Map<String,String> linkTags = new HashMap<String, String>();
		final Set<JexlStringExpression> rels = exprMap.keySet();
		
		for (JexlStringExpression exprRel : rels) 
		{
			final JexlStringExpression exprHref = exprMap.get(exprRel);
			
			final String rel = exprRel.evaluate(req);
			final String href = exprHref.evaluate(req);
			linkTags.put(rel, href);
		}
		req.setAttribute(JspWidgetUtil.ATTR.LINK_CUSTOM, linkTags);
		return NO_ALTERATION;
	}

}
