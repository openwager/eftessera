<%@ page language="java" %> 
<%@ page isELIgnored="false" %>

<%@ page import="com.tessera.intercept.util.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.weaselworks.com/tessera" prefix="tessera"%>
<%@ taglib uri="http://www.weaselworks.com/tessera/form" prefix="tform"%>

<H1>Login</H1>

<c:url var="url" value="/login" /> 

<tessera:ifRecoveryContext>
	<p class="fieldError" style="display: inline; margin: 20px;">
		NOTE: You must first login to go to that page. 
	</p> 
	<p></p>
</tessera:ifRecoveryContext>

<tform:ifThrowable var="thrown2" >
	THROWABLE: 
	<tform:throwable var="thrown" /> 
	${thrown2}
</tform:ifThrowable>

<tform:ifError global="true">
	<div class="globalErrors">
		<ul>
			<tform:globalErrors var="err">
				<li>
					${err}
				</li>
			</tform:globalErrors>
		</ul>
	</div>
</tform:ifError>

<form action="${url}" method="GET">
	<table>
		<tr>
			<td>
				Username:
			</td>
			<td>
				<tform:text name="username" /> 
			</td>
			<td>
				<tform:ifError name="username">
					<div class="fieldError">
						<tform:error name="username" />
					</div>					 
				</tform:ifError>
			</td>
		</tr>
		<tr>
			<td>
				Password:
			</td>
			<td>
				<tform:password name="password" showValue="true" /> 
			</td>
			<td>
				<tform:ifError name="password">
					<div class="fieldError">
						<tform:error name="password" />
					</div> 
				</tform:ifError>
			</td>
		</tr>
		
		<tr>
			<td />
			<td>
				<tform:checkbox name="rememberMe" value="true" /> 
				remember me 
			</td>
			<td>
				<tform:ifError name="rememberMe">
					<div class="fieldError">
						<tform:error name="rememberMe" /> 
					</div>
				</tform:ifError>
			</td>
		</tr>

		<tr>
			<td>
			</td>
			<td>
				<tform:submit value="Login" />  
				<input type="button" value="Cancel" onClick="history.go (-1);" /> 
			</td>		
		</tr>
	</table>
	
	<tessera:insertRecoveryContext /> 
</form>

<%-- EOF --%> 