<%@ page language="java" %> 
<%@ page isELIgnored="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://www.weaselworks.com/tessera" prefix="tessera"%>
<%@ taglib uri="http://www.weaselworks.com/tessera/login" prefix="tlogin"%>

<c:url var="homeUrl" value="/" /> 
<c:url var="loginUrl" value="/loginForm" /> 
<c:url var="logoutUrl" value="/logout" />

<table>
	<tr>
		<td>
			<div style="width: 300px">
				<a href="${homeUrl}">
					<c:url var="url" value="/r/i/tessera.png" /> 
					<img src="${url}" /> 
				</a>
			</div>
		</td>
		
		<td style="width: 300px">
		<% try {  %>
		
			<sql:query var="rs" dataSource="jdbc/db">
				select * from test 
			</sql:query>
			<c:forEach var="row" items="${rs.rows}">
				${row}
			</c:forEach>
			
			<% } catch (final Throwable t) { out.println (t); }  %>
		</td>
		
		<td style="text-align: right; width: 300px">
			<tlogin:ifLoggedIn>
				<tlogin:getLoginSession var="ls" /> 
				Hiya ${ls.username}! 					
				<br /> 
				<a class="abutton" href="${logoutUrl}">logout</a>
			</tlogin:ifLoggedIn>
			<tlogin:ifNotLoggedIn>
				<a class="abutton" href="${loginUrl}">login</a>		
				<a class="abutton">register</a>
			</tlogin:ifNotLoggedIn>			
		</td>
	</tr>
</table>

<%-- EOF --%> 