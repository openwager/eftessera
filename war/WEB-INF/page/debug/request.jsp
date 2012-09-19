<%@ page language="java" %> 
<%@ page isELIgnored="false" %> 

<%@ page import="java.lang.reflect.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>HttpServletRequest Properties</h1>


<table class="debug">
    <tr>
        <th scope="col">Index</th>
        <th scope="col">Name</th>
        <th scope="col">Value</th>
    </tr>

    <%
    for (int i = 0; i < attrs.length; i ++) {
        final String attr = attrs [i];
        final String val = getBeanValue (request, attr);
        out.println ("<tr>");
        out.println ("  <td>" + (i + 1) + ".</td>");
        out.println ("  <td>" + attr + "</td>");
        out.println ("  <td>" + val + "</td>");
        out.println ("</tr>");
    }
%>

</table>

<%!
    static final String [] attrs = {
         "AuthType",
         "CharacterEncoding",
         "ContentLength",
         "ContentType",
         "ContextPath",
//            "DateHeader",
         "LocalAddr",
         "Locale",
         "LocalPort",
         "Method",
         "PathInfo",
         "PathTranslated",
         "Protocol",
         "QueryString",
         "RemoteAddr",
         "RemotePort",
         "RemoteUser",
         "RequestedSessionId",
         "RequestURI",
         "RequestURL",
         "Scheme",
         "ServerName",
         "ServerPort",
         "ServerPath",
         "UserPrincipal"
     };

	 /**
	  * Super-simple utility method for using introspection to invoke the
	  * get<attr> method on an arbitrary object.
	  *
	  * @param o
	  * @param prop
	  * @return
	  */
	
	 public static
	 String getBeanValue (final Object o, final String prop)
	 {
	     try {
	         final Class<?> c = HttpServletRequest.class;
	         final String methodName = "get" + Character.toUpperCase (prop.charAt (0)) + prop.substring (1);
	         final Method m = c.getMethod (methodName, new Class [] { });
	         final Object v = m.invoke (o, new Object [] { });
	         if (v == null) {
	             return "";
	         }
	         return v.toString ();
	     }
	     catch (final Exception e) {
	         return "ERROR: " + e.getMessage ();
	     }
	     // NOT REACHED
	 }
%>

<%-- EOF --%> 