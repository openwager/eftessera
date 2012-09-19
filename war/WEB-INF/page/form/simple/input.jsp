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
	<table class="formTable">
		<tr>
			<th>
				Input (text): 
			</th>
			<td>
				<tform:text name="text" /> 
			</td>
			<td>
				<tform:ifError name="text">
					<div class="fieldError">
						<tform:error name="text" />
					</div>					 
				</tform:ifError>
			</td>
		</tr>

		<tr>
			<th>
				Input (password): 
			</th>
			<td>
				<tform:password name="password" />  
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
			<th>
				Checkbox: 
			</th>
			<td>
				<tform:checkbox name="checkbox" value="true" /> 
			</td>
			<td>
				<tform:ifError name="checkbox">
					<div class="fieldError">
						<tform:error name="checkbox" />
					</div>					 
				</tform:ifError>
			</td>
		</tr>

		<tr>
			<th style="vertical-align: top">
				Radio Buttons: 
			</th>
			<td>
				<tform:radio value="a" name="radio" isDefault="true" /> Red 
				<br/>
				<tform:radio value="b" name="radio" /> Orange
				<br/>
				<tform:radio value="c" name="radio" /> Yellow 
			</td>
			<td>
				<tform:ifError name="radio">
					<div class="fieldError">
						<tform:error name="radio" />
					</div>					 
				</tform:ifError>
			</td>
		</tr>

		<tr>
			<th>
				Select: 
			</th>
			<td>
				<tform:select name="select">				
					<tform:option value="" text="" />
					<tform:option value="1" text="One" />
					<tform:option value="2" text="Two" />
					<tform:option value="3" text="Three" />
				</tform:select>
			</td>
			<td>
				<tform:ifError name="select">
					<div class="fieldError">
						<tform:error name="select" />
					</div>					 
				</tform:ifError>
			</td>
		</tr>

		<tr>
			<th>
				File Input:  
			</th>
			<td>
				<tform:file name="file" /> 
			</td>
			<td>
				<tform:ifError name="file">
					<div class="fieldError">
						<tform:error name="file" />
					</div>					 
				</tform:ifError>
			</td>
		</tr>

		<tr>
			<th>
				Image Input:  
			</th>
			<td>
				<c:url var="url" value="/r/i/button.png" /> 
				<tform:image value="a" name="image" src="${url}" /> 
			</td>
			<td>
				<tform:ifError name="image">
					<div class="fieldError">
						<tform:error name="image" />
					</div>					 
				</tform:ifError>
			</td>
		</tr>

		<tr>
			<td>
			</td>
			<td>
				<tform:submit name="button" value="Login" />  
				<input type="button" value="Cancel" onClick="history.go (-1);" /> 
			</td>		
		</tr>
	</table>
</form>



<%-- EOF --%> 