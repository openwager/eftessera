<%@ page language="java" %> 
<%@ page isELIgnored="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.weaselworks.com/tessera" prefix="tessera"%>
<%@ taglib uri="http://www.weaselworks.com/tessera/form" prefix="tform"%>

<H1>Action '${form.action}'</H1>

<table class="xxx">
	<tr>
		<th>Index</th>
		<th>Type</th>
		<th>Details</th>
	</tr>
	<c:forEach var="i" varStatus="status" items="${action.underlying}">
		<tr>
			<td>${status.count}.</td> 
			<td>${i.class.simpleName}</td>
			<td>${i.properties}</td>
		</tr>
	</c:forEach>
</table>

<form action="showActionForm">
	<input type="hidden" name="action" value="${form.action}" /> 
	<input type="submit" value="Change" /> 
</form>



<%-- EOF --%> 