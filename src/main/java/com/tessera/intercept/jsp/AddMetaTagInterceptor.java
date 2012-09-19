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
public class AddMetaTagInterceptor 
	extends InterceptorSupport 
{

	public 
	AddMetaTagInterceptor (Map<String, String> props) 
	{
		super(props);
	}
	
	interface PROP
	{
		public String NAME_PREFIX = "name.";
		public String CONTENT_PREFIX = "content.";
	}
	
	private Map<JexlStringExpression, JexlStringExpression> exprMap;
	
	public
	void init ()
	{
		exprMap = new HashMap<JexlStringExpression, JexlStringExpression>();
		for (int cnt = 0; ; cnt ++) {
			
			final String propName = PROP.NAME_PREFIX + cnt;
			final String propContent = PROP.CONTENT_PREFIX + cnt;

			try
			{
				final String strKey = require(propName);
				final String strValue = require(propContent);
				
				if(StringUtil.isEmpty(strValue))
				{
					break;
				}
				
				if(StringUtil.isEmpty(strKey))
				{
					continue;
				}
				
				final JexlStringExpression name = new JexlStringExpression(require(propName)); 
				final JexlStringExpression content = new JexlStringExpression(require(propContent));
			
				exprMap.put(name, content);
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
		final Map<String,String> metaTags = new HashMap<String, String>();
		final Set<JexlStringExpression> names = exprMap.keySet();

		for (final JexlStringExpression exprName : names) 
		{
			final JexlStringExpression exprContent = exprMap.get(exprName);
			
			final String name = exprName.evaluate(req);
			final String content = exprContent.evaluate(req);
			metaTags.put(name, content);
		}
		req.setAttribute(JspWidgetUtil.ATTR.META_CUSTOM, metaTags);
		return NO_ALTERATION;
	}

}
