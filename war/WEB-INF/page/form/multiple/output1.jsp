<%@ page language="java" %> 
<%@ page isELIgnored="false" %>

<%@ page import="com.tessera.intercept.util.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.weaselworks.com/tessera/form" prefix="tform"%>

<H1>Multiple Form Results</H1>

<h2>Form 1</h2>

<table class="formTable">
	<tr>
		<th>Number:</th>
		<td>${form1.value}</td>
	</tr>
</table>

<%-- EOF --%> 