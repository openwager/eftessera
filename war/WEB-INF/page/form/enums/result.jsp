<%@ page language="java" %> 
<%@ page isELIgnored="false" %>

<%@ page import="com.tessera.intercept.util.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.weaselworks.com/tessera/form" prefix="tform"%>

<H1>Enums Form Results</H1>

<table class="formTable">
	<tr>
		<th>Gender:</th>
		<td>${form.gender}</td>
	</tr>
	<tr>
		<th>Color:</th>
		<td>${form.color}</td>
	</tr>
</table>

<%-- EOF --%> 