<%@ page language="java" %> 
<%@ page isELIgnored="false" %>

<%@ page import="com.tessera.intercept.util.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.weaselworks.com/tessera/form" prefix="tform"%>

<H1>Simple Form Results</H1>

<table class="formTable">
	<tr>
		<th>Input (text):</th>
		<td>${form.text}</td>
	</tr>
	<tr>
		<th>Input (password):</th>
		<td>${form.password}</td>
	</tr>
	<tr>
		<th>Checkbox:</th>
		<td>${form.checkbox}</td>
	</tr>
	<tr>
		<th>Radio Buttons:</th>
		<td>${form.radio}</td>
	</tr>
	<tr>
		<th>Select:</th>
		<td>${form.select}</td>
	</tr>
	<tr>
		<th>File Input:</th>
		<td>${form.file}</td>
	</tr>
	<tr>
		<th>Submit Button:</th>
		<td>${form.button}</td>
	</tr>
</table>

<%-- EOF --%> 