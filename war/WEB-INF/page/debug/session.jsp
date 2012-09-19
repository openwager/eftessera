<%@ page language="java" %> 
<%@ page isELIgnored="false" %> 

<%@ page import="java.util.*"%>
<%@ page import="com.weaselworks.util.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>HttpSession</h1>

<table class="debug">
    <tr>
        <th scope="col">Index</th>
        <th scope="col">Name</th>
        <th scope="col">Value</th>
    </tr>

<%
    {
	    final Enumeration<String> e = session.getAttributeNames ();
	    Iterator<String> iter = new EnumerationIterator<String> (e);
    	int cnt = 0;

	    while (iter.hasNext ()) {
	        out.println ("<tr>");
	        final String param = (String) iter.next ();
	        out.println ("<td>" + (++ cnt) + "</td>");
	        out.println ("<td>" + param + "</td>");
	        out.println ("<td>" + session.getAttribute (param) + "</td>");
	        out.println ("</tr>");
	    }
	
	    if (cnt == 0) {
	        out.println ("<tr><td colspan=\"3\">No session attributes.</td></tr>");
	    }
    }
%>
</table>

<%-- EOF --%> 