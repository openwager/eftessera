<%@ page language="java" %> 
<%@ page isELIgnored="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<table>
	<tr>
		<td>
<h2>Some Links</h2>

<ul>
	<li>
		<c:url var="loginUrl" value="/loginForm" /> 
		<a href="${loginUrl}">
			Login
		</a>
	</li>	
	<li>
		<c:url var="logoutUrl" value="/logout" /> 
		<a href="${logoutUrl}">
			Logout
		</a>
	</li>	
	<li>
		<c:url var="clearCookiesUrl" value="/login/clearCookies" /> 
		<a href="${clearCookiesUrl}">
			Clear Cookies
		</a>
	</li>	
	<li>
		<c:url var="loginStatus" value="/loginStatus" /> 
		<a href="${loginStatus}">
			Show Login Status
		</a>
	</li>
	<li>
		<c:url var="test1Url" value="/test/1" /> 
		<a href="${test1Url}">
			Test page
		</a>
	</li>	
	<li>
		<c:url var="test2Url" value="test/2" /> 
		<a href="${test2Url}">
			Test page (requires login)
		</a>
	</li>	
</ul>

		</td><td>

<h2>Forms</h2>

<ul>
	<li>
		<c:url var="url" value="/form/simple/form" /> 
		<a href="${url}">Simple Form</a> - examples of each control 		
	</li>
	<li>
		<c:url var="url" value="/form/types/form" /> 
		<a href="${url}">Types Form</a> - primitive type conversions		
	</li>
	<li>
		<c:url var="url" value="/form/enums/form" /> 
		<a href="${url}">Enums Form</a> - working with enums 		
	</li>
	<li>
		<c:url var="url" value="/form/validator/form" /> 
		<a href="${url}">Validator Form</a> - form with custom validator 		
	</li>
	<li>
		<c:url var="url" value="/form/multiple/form" /> 
		<a href="${url}">Multiple Forms</a> - multiple forms on one page 		
	</li>
	<li>
		<c:url var="url" value="/form/structured/form" /> 
		<a href="${url}">Strucured Form</a> - structured data forms 		
	</li>
	<li>
		<c:url var="url" value="/form/wizard/form" /> 
		<a href="${url}">Wizard Form</a> - multipage forms 		
	</li>
</ul>

		</td>
	</tr>
	<tr>
		<td>

<h2>Lattice</h2>

<ul>
	<li>
		<c:url var="url" value="/lattice/validator/showForm" /> 
		<a href="${url}">Show Validator</a> - dump validator for a class
	</li>
	<li>
		<c:url var="url" value="/lattice/validator/showValidators" /> 
		<a href="${url}">Show Validators</a> - list registered validators
	</li>
</ul>

		</td><td>
		
<h2>Tessera</h2>		
		
<ul>
	<li>
		<c:url var="url" value="/tessera/action/showActionForm" /> 
		<a href="${url}">Show Action</a> - show an action
	</li>
	<li>
		<c:url var="url" value="/tessera/action/showActions" /> 
		<a href="${url}">Show Actions</a> - show all actions
	</li>
</ul>		
		
		
		</td>
	</tr>
</table>

<%-- EOF --%> 