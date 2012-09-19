<%@ page language="java" %> 
<%@ page isELIgnored="false" %>

<%@ page import="com.tessera.intercept.util.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.weaselworks.com/tessera/form" prefix="tform"%>

<H1>Simple Form</H1>

<c:url var="url" value="submit" /> 

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
				Gender: 
			</td>
			<td>
				<tform:select name="gender" >
					<option value="">
						- PICK ONE - 
					</option>
					<tform:options enumClass="com.tessera.site.form.enums.Gender" />
				</tform:select> 
			</td>
			<td>
				<tform:ifError name="gender">
					<div class="fieldError">
						<tform:error name="gender" />
					</div>					 
				</tform:ifError>
			</td>
		</tr>

		<tr>
			<td>
				Color: 
			</td>
			<td>
				<tform:select name="color" >
					<option value="">
						- PICK ONE - 
					</option>
					<tform:options enumClass="com.tessera.site.form.enums.PrettyColor" /> 
				</tform:select> 
			</td>
			<td>
				<tform:ifError name="color">
					<div class="fieldError">
						<tform:error name="color" />
					</div>					 
				</tform:ifError>
			</td>
		</tr>

		<tr>
			<td>
			</td>
			<td>
				<tform:submit name="button" value="Submit" />  
				<input type="button" value="Cancel" onClick="history.go (-1);" /> 
			</td>		
		</tr>
	</table>
</form>



<%-- EOF --%> 