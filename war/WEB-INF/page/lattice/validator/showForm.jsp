<%@ page language="java" %> 
<%@ page isELIgnored="false" %>

<%@ page import="com.tessera.intercept.util.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.weaselworks.com/tessera/form" prefix="tform"%>

<H1>Simple Form</H1>

<c:url var="url" value="show" /> 

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
	<table class="formTable">
		<tr>
			<th>
				Validator Type: 
			</th>
			<td>
				<tform:text name="validatorType" size="48" /> 
			</td>
			<td>
				<tform:ifError name="validatorType">
					<div class="fieldError">
						<tform:error name="validatorType" />
					</div>					 
				</tform:ifError>
			</td>
		</tr>

		<tr>
			<td>
			</td>
			<td>
				<tform:submit name="button" value="Show Validator" />  
			</td>		
		</tr>
	</table>
</form>



<%-- EOF --%> 