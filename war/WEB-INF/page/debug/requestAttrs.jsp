<%@ page language="java" %> 
<%@ page isELIgnored="false" %> 

<%@ page import="java.util.*" %>
<%@ page import="com.weaselworks.util.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>HttpServletRequest Attributes</h1>

<table class="debug">
    <tr class="debugHeader">
        <th scope="col">Index</th>
        <th scope="col">Name</th>
        <th scope="col">Value</th>
    </tr>

<%!
        protected
        String safeToString (final Object obj)
        {
                try {
                        return obj.toString ();
                } catch (final Throwable t) {
                        return "<font color=\"#f00\">threw [" + t.getClass().getName () + ": " + t.getMessage () + "]</font>";
                }
        }
%>

<%
	{
	    final Enumeration<String> e = request.getAttributeNames ();
	    final Iterator<String> iter = new CollatingIterator<String> (new EnumerationIterator<String> (e), new StringComparator ()); 
	 
	    int cnt = 0;
	
	    while (iter.hasNext ()) {
	        out.println ("<tr>");
	        final String param = (String) iter.next ();
	        out.println ("<td>" + (++cnt) + ".</td>");
	        out.println ("<td>" + param + "</td>");
	        out.println ("<td>" + safeToString (request.getAttribute (param)) + "</td>");
	        out.println ("</tr>");
	    }
	}
%>

</table>

<%-- EOF --%> 