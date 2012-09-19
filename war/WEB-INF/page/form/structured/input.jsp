<%@ page language="java" %> 
<%@ page isELIgnored="false" %>

<%@ page import="com.tessera.intercept.util.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.weaselworks.com/tessera/form" prefix="tform"%>

<H1>Structured Form</H1>

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

<c:url var="url" value="submit" /> 
<form action="${url}" method="GET">
	<table class="formTable">
		<tr>
			<td colspan="3">
				<b>
					User
				</b>
			</td>
		</tr>
		<tr>
			<th>
				Name:
			</th>
			<td>
				<tform:text name="user.firstName" size="8" />
				<tform:text name="user.lastName" size="16" /> 
			</td>
			<td>
				<tform:ifError name="user.firstName">
					<div class="fieldError">
						<tform:error name="user.firstName" />
					</div>					 
				</tform:ifError>
				<tform:ifError name="user.lastName">
					<div class="fieldError">
						<tform:error name="user.lastName" />
					</div>					 
				</tform:ifError>
			</td>
		</tr>
		<tr>
			<th>
				Gender:
			</th>
			<td>
				<tform:select name="user.gender">
					<tform:options enumClass="com.tessera.site.form.structured.Gender" /> 
				</tform:select> 
			</td>
			<td>
				<tform:ifError name="user.gender">
					<div class="fieldError">
						<tform:error name="user.gender" />
					</div>					 
				</tform:ifError>
			</td>
		</tr>

		<tr>
			<td colspan="3">
				<b>
					Money
				</b>
			</td>
		</tr>
		<tr>
			<th>
				Amount:
			</th>
			<td>
				<tform:text name="money.amount" size="12" />
				<tform:select name="money.currency">
					<tform:options enumClass="com.tessera.site.form.structured.Currency" /> 
				</tform:select> 
			</td>
			<td>
				<tform:ifError name="money.amount">
					<div class="fieldError">
						<tform:error name="money.amount" />
					</div>					 
				</tform:ifError>
				<tform:ifError name="money.currency">
					<div class="fieldError">
						<tform:error name="money.currency" />
					</div>					 
				</tform:ifError>
			</td>
		</tr>
		
		<tr>
			<td>
			</td>
			<td>
				<tform:submit name="button" value="Submit" />  
			</td>		
		</tr>
	</table>
</form>



<%-- EOF --%> 