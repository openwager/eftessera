<%@ page language="java" %> 
<%@ page isELIgnored="false" %>

<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="com.tessera.dispatch.*" %>
<%@ page import="com.tessera.intercept.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.weaselworks.com/tessera" prefix="tessera"%>
<%@ taglib uri="http://www.weaselworks.com/tessera/form" prefix="tform"%>

<c:set var="root" value="${actions.root}" /> 

<H1>Registered Actions</H1>

<%!
	protected 
	String toPath (final String a, final List<String> path) 
	{
		String s = a; 
		if (path.size () != 0) { 
			for (int i = path.size () - 1; i >= 0; i --) { 
				s = path.get (i) + '/' + s; 
			}
		}
		return s; 
	}
 

	protected
	void dump (final DispatchNode node, final boolean hasChain, final List<String> path, final JspWriter out)
		throws IOException
	{
		final Map<String, InterceptorChain> chains = node.getChains ();
		final Map<String, DispatchNode> nodes = node.getNodes();

		out.println ("<ul>"); 
		if (chains != null) { 
			for (final String key : chains.keySet ()) {
				if (! nodes.containsKey(key)) {
					out.println ("<li>"); 
					out.println ("<a href='showAction?action=" + toPath (key, path) + "'>");
					out.println (key); 
					out.println ("</a>"); 
					out.println ("</li>"); 
				}
			}
		}
	
		if (nodes != null) { 
			for (final String key : nodes.keySet ()) { 
				if (hasChain) { 
					out.println ("<li>"); 
					out.println ("<a href='showAction?action=" + toPath (key, path) + "'>");
					out.println (key); 
					out.println ("</a>"); 
					out.println ("</li>"); 
				} else { 
					out.println ("<li>" + key + "/</li>");
				}
				path.add (key); 
				dump (nodes.get (key), chains.containsKey (key), path, out);
				path.remove (path.size() - 1); 
			}			
		}
		out.println ("</ul>"); 

		return; 
	}
%>

<ul>
<% 
	final DispatchNode node = (DispatchNode) pageContext.findAttribute ("root");
	
	if (node == null) { 
		out.println ("No nodes found."); 	
	} else {
		dump (node, false, new ArrayList<String> (), out); 
	}
%>
</ul>
	
<%-- EOF --%> 