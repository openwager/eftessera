<%@ page language="java" %> 
<%@ page isELIgnored="false" %>

<%@ page import="com.tessera.intercept.util.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.weaselworks.com/tessera/form" prefix="tform"%>

<H1>Validator Form Results</H1>

<table class="formTable">
	<tr>
		<th>A:</th>
		<td>${form.a}</td>
	</tr>
	<tr>
		<th>B:</th>
		<td>${form.b}</td>
	</tr>
	<tr>
		<th>C:</th>
		<td>${form.c}</td>
	</tr>
</table>

<%-- EOF --%> 