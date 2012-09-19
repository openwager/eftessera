<%@ page language="java" %> 
<%@ page isELIgnored="false" %>

<%@ page import="com.tessera.intercept.util.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.weaselworks.com/tessera/form" prefix="tform"%>

<H1>Multiple Forms</H1>

<c:url var="url1" value="submit1" /> 
<c:url var="url2" value="submit2" /> 

<tform:ifThrowable var="thrown2" >
	THROWABLE: 
	<tform:throwable var="thrown" /> 
	${thrown2}
</tform:ifThrowable>

<h2>Form 1</h2>

<tform:ifError global="true" bean="form1">
	<div class="globalErrors">
		<ul>
			<tform:globalErrors var="err" bean="form1">
				<li>
					${err}
				</li>
			</tform:globalErrors>
		</ul>
	</div>
</tform:ifError>

<form action="${url1}" method="GET">
	<table>
		<tr>
			<td>
				Value: 
			</td>
			<td>
				<tform:text name="value"  bean="form1"/> 
			</td>
			<td>
				<tform:ifError name="value"  bean="form1">
					<div class="fieldError">
						<tform:error name="value" bean="form1" />
					</div>					 
				</tform:ifError>
			</td>
		</tr>

		<tr>
			<td />
			<td>
				<tform:submit name="button" value="Submit" />  
			</td>		
		</tr>
	</table>
</form>

<h2>Form 2</h2>

<tform:ifError global="true" bean="form2">
	<div class="globalErrors">
		<ul>
			<tform:globalErrors var="err" bean="form2">
				<li>
					${err}
				</li>
			</tform:globalErrors>
		</ul>
	</div>
</tform:ifError>

<form action="${url2}" method="GET">
	<table>
		<tr>
			<td>
				Value: 
			</td>
			<td>
				<tform:text name="value" bean="form2" /> 
			</td>
			<td>
				<tform:ifError name="value" bean="form2">
					<div class="fieldError">
						<tform:error name="value" bean="form2"/>
					</div>					 
				</tform:ifError>
			</td>
		</tr>

		<tr>
			<td />
			<td>
				<tform:submit name="button" value="Submit" />  
			</td>		
		</tr>
	</table>
</form>



<%-- EOF --%> 