<%@ page language="java" %> 
<%@ page isELIgnored="false" %>

<%@ page import="com.tessera.intercept.util.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.weaselworks.com/tessera/form" prefix="tform"%>

<H1>Validator Form</H1>

<h2>Rules</h2>

<ul>
  <li>A must be [0, 100]
  <li>B must be [50, 150]
  <li>C must be [100, 200]
  <li>A must be &lt;= B
  <li>B must be &lt;= C
  <li>(A + B + C) &lt;= 200
</ul>

<h2>Form</h2>

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
	<table class="formTable">
		<tr>
			<th>
				A: 
			</th>
			<td>
				<tform:text name="a" /> 
			</td>
			<td>
				<tform:ifError name="a">
					<div class="fieldError">
						<tform:error name="a" />
					</div>					 
				</tform:ifError>
			</td>
		</tr>

		<tr>
			<th>
				B: 
			</th>
			<td>
				<tform:text name="b" /> 
			</td>
			<td>
				<tform:ifError name="b">
					<div class="fieldError">
						<tform:error name="b" />
					</div>					 
				</tform:ifError>
			</td>
		</tr>

		<tr>
			<th>
				C: 
			</th>
			<td>
				<tform:text name="c" /> 
			</td>
			<td>
				<tform:ifError name="c">
					<div class="fieldError">
						<tform:error name="c" />
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