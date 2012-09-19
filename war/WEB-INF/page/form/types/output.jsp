<%@ page language="java" %> 
<%@ page isELIgnored="false" %>

<%@ page import="com.tessera.intercept.util.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.weaselworks.com/tessera/form" prefix="tform"%>

<H1>Simple Form Results</H1>

<table class="formTable">
	<tr>
		<th>boolean:</th>
		<td>${form.boolean1}</td>
	</tr>
	<tr>
		<th>Boolean:</th>
		<td>${form.boolean2}</td>
	</tr>
	<tr>
		<th>short:</th>
		<td>${form.short1}</td>
	</tr>
	<tr>
		<th>Short:</th>
		<td>${form.short2}</td>
	</tr>
	<tr>
		<th>char:</th>
		<td>${form.char}</td>
	</tr>
	<tr>
		<th>Character:</th>
		<td>${form.character}</td>
	</tr>
	<tr>
		<th>float:</th>
		<td>${form.float1}</td>
	</tr>
	<tr>
		<th>Float:</th>
		<td>${form.float2}</td>
	</tr>
	<tr>
		<th>double:</th>
		<td>${form.double1}</td>
	</tr>
	<tr>
		<th>Double:</th>
		<td>${form.double2}</td>
	</tr>
	<tr>
		<th>int:</th>
		<td>${form.int}</td>
	</tr>
	<tr>
		<th>Integer:</th>
		<td>${form.integer}</td>
	</tr>
	<tr>
		<th>long:</th>
		<td>${form.long1}</td>
	</tr>
	<tr>
		<th>Long:</th>
		<td>${form.long2}</td>
	</tr>
	<tr>
		<th>String:</th>
		<td>${form.string}</td>
	</tr>
	
</table>

<%-- EOF --%> 