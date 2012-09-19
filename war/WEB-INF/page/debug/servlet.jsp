<%@ page language="java" %> 
<%@ page isELIgnored="false" %> 

<%@ page import="java.util.*"%>
<%@ page import="com.weaselworks.util.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>
  ServletConfig (${pageContext.servletConfig.servletName})
</h1>

<table class="debug">
    <tr>
        <th>Index</th>
        <th>Parameter</th>
        <th>Value</th>
    </tr>

<%
    {
        final ServletConfig sconfig = pageContext.getServletConfig ();
        Iterator<String> iter = new EnumerationIterator<String> (sconfig.getInitParameterNames ());
        int i = 0;

        while (iter.hasNext ()) {
            final String param = (String) iter.next ();
            out.print ("<tr><td>" + (++i) + "</td>");
            out.print ("<td>" + param + "</td>");
            out.print ("<td>" + sconfig.getInitParameter (param) + "</td></tr>");
        }
    }
%>
</table>


<h1>
    ServletContext
</h1>

<table class="debug">
    <tr>
        <th>Index</th>
        <th>Parameter</th>
        <th>Value</th>
    </tr>

    <tr>
        <td>1.</td>
        <td>majorVersion</td>
        <td>${pageContext.servletContext.majorVersion}</td>
    </tr>
    <tr>
        <td>2.</td>
        <td>minorVersion</td>
        <td>${pageContext.servletContext.minorVersion}</td>
    </tr>
    <tr>
        <td>3.</td>
        <td>serverInfo</td>
        <td>${pageContext.servletContext.serverInfo}</td>
    </tr>
    <tr>
        <td>4.</td>
        <td>servletContextName</td>
        <td>${pageContext.servletContext.servletContextName}</td>
    </tr>
    <tr>
        <td>5.</td>
        <td>servlets</td>
        <td>${pageContext.servletContext.servletNames}</td>
    </tr>

<%
	{
        final ServletContext sc = pageContext.getServletContext ();
        final Enumeration<String> e1 = sc.getInitParameterNames ();
         int i1 = 5;

        while (e1.hasMoreElements ()) {
            final String s = (String) e1.nextElement ();
            out.println ("<tr><td>" + (++i1) + ".</td><td>INIT (" + s + ")</td><td>");
            out.println (sc.getInitParameter (s) + "</td></tr>");
        }

        final Enumeration<String> e2 = sc.getAttributeNames () ;

        while (e2.hasMoreElements ()) {
            final String s = (String) e2.nextElement ();
            final Object attr = sc.getAttribute (s);
            out.println ("<tr><td>" + (++i1) + ".</td><td>attribute [" + s + "]</td><td>");
            if (attr instanceof String []) {
                for (final String str : (String []) attr) {
                    out.println (str + "<br/>"); 
                }
            } else { 
                out.println (attr);
            }
            out.println ("</td></tr>");
        }
	}
%>
</table>

<%-- EOF --%> 