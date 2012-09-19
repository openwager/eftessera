<%@ page language="java" %> 
<%@ page isELIgnored="false" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
    pageContext.setAttribute ("cs", request.getCookies ());
%>

<h1>
    HttpServletRequest Cookies
</h1>

<table class="debug">
    <tr>
        <th scope="col">Index</th>
        <th scope="col">Name</th>
        <th scope="col">Value</th>
    </tr>
    <c:forEach var="c" items="${cs}" varStatus="status">
        <tr>
            <td>${status.count}.</td>
            <td>${c.name}</td>
            <td>${c.value}</td>
        </tr>
    </c:forEach>
</table>
<%-- EOF --%> 