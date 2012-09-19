<%@ page language="java" %> 
<%@ page isELIgnored="false" %>

<%@ page import="java.util.*" %>
<%@ page import="com.tessera.intercept.util.*" %>
<%@ page import="com.lattice.validate.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/function" prefix="fn" %>
<%@ taglib uri="http://www.weaselworks.com/tessera/form" prefix="tform"%>

<h1>Registered Validations</h1>

<table class="xxx">
	<tr>
		<th>Index</th>
		<th>Type</th>
	</tr>	
	<c:choose>
		<c:when test="${not empty validators}">
			<c:forEach var="vor" items="${validators}" varStatus="status">
				<tr>
					<td>${status.count}.</td>
					<td>
						<c:url var="url" value="show">
							<c:param name="validatorType" value="${vor.type.name}" /> 
						</c:url>
						<a href="${url}">
							${vor.type.name}
						</a>
					</td>
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