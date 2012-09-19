<%@ page language="java" %> 
<%@ page isELIgnored="false" %>

<%@ page import="com.tessera.intercept.util.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.weaselworks.com/tessera/form" prefix="tform"%>

<H1>Simple Form Results</H1>

<table class="formTable">
	<tr>
		<th>User (firstName, LastName):</th>
		<td>${form.user.firstName} ${form.user.lastName}</td>
	</tr>
	<tr>
		<th>User (gender):</th>
		<td>${form.user.gender}</td>
	</tr>
	<tr>
		<th>Money (amount, currency):</th>
		<td>${form.money.amount} ${form.money.currency.name}</td>
	</tr>	
</table>

<%-- EOF --%> 