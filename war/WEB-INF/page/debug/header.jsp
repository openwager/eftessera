<%@ page language="java" %> 
<%@ page isELIgnored="false" %> 
<%@ page import="java.util.*"%>
<%@ page import="com.weaselworks.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>HttpServletResponse Headers</h1>

<table class="debug">
    <tr>
        <th scope="col">Index</th>
        <th scope="col">Name</th>
        <th scope="col">Value</th>
    </tr>

    <%
    	{ 
    	    final Enumeration<String> e = request.getHeaderNames ();
    	    final Iterator<String> iter = new CollatingIterator<String> (new EnumerationIterator<String> (e), new StringComparator ());
    	    int cnt = 1;

    	    while (iter.hasNext ()) {
    	        final String key = (String) iter.next ();
    	        out.println ("<tr>");
    	        out.println ("  <td>" + cnt + ".</td>");
    	        out.println ("  <td>" + key + "</td>");
    	        out.println ("  <td>" + request.getHeader (key) + "</td>");
    	        out.println ("</tr>");
    	        cnt++;
    	    }
    	} 
%>

</table>
<%-- EOF --%> 