<%@ page language="java" %> 
<%@ page isELIgnored="false" %>

<%@ page import="java.util.*" %>
<%@ page import="com.tessera.intercept.util.*" %>
<%@ page import="com.lattice.validate.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/function" prefix="fn" %>
<%@ taglib uri="http://www.weaselworks.com/tessera/form" prefix="tform"%>

<H1>${form.validatorType}</H1>

<h2>Field Validations</h2>

<table class="xxx">
	<tr>
		<th>Index</th>
		<th>Field</th>
		<th>Validator</th>
	</tr>
	<c:choose>
		<c:when test="${not empty validator.fields}">
			<c:forEach var="field" items="${validator.fields}" varStatus="status">
				<c:set var="fvs" value="${validator.validators[field]}" />
				<%
					final List<?> fvs = (List<?>) pageContext.findAttribute ("fvs");
					pageContext.setAttribute ("fvl", fvs.size ()); 
				%> 
				<c:forEach var="fv" items="${fvs}" varStatus="status2" >
					<tr>
						<c:if test="${status2.count eq 1}">
							<td rowspan="${fvl}">${status.count}.</td>
							<td rowspan="${fvl}">${field}</td>
						</c:if>
						<td>
							${fv}
						</td>
					</tr>
				</c:forEach>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr>
				<td colspan="3">
					None found.
				</td>
			</tr>			
		</c:otherwise>
	</c:choose>
</table>

<h2>Object Validations</h2>

<table class="xxx">
	<tr>
		<th>Index</th>
		<th>Implementation</th>
		<th>Target</th>
	</tr>	
	<c:choose>
		<c:when test="${not empty validator.otherValidators}">
			<c:forEach var="ov" items="${validator.otherValidators}" varStatus="status">
				<tr>
					<td>${status.count}.</td>
					<td>${ov.class.name}</td>
					<td>${ov.type}</td>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr>
				<td colspan="3">
					None found.
				</td>
			</tr>
		</c:otherwise>
	</c:choose>	
</table>


<%-- EOF --%> 