<%@ page language="java" %> 
<%@ page isELIgnored="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.weaselworks.com/tessera" prefix="tessera"%>
<%@ taglib uri="http://www.weaselworks.com/tessera/form" prefix="tform"%>

<H1>Show Action</H1>

<c:url var="url" value="showAction" /> 

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
				Action: 
			</th>
			<td>
				<tform:text name="action" size="48" /> 
			</td>
			<td>
				<tform:ifError name="action">
					<div class="fieldError">
						<tform:error name="action" />
					</div>					 
				</tform:ifError>
			</td>
		</tr>

		<tr>
			<td/>
			<td>
				<tform:submit name="button" value="Show Action" />  
			</td>		
		</tr>
	</table>
</form>

<%-- EOF --%> 