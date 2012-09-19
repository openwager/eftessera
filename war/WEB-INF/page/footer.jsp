<%@ page language="java" %>
<%@ page isELIgnored="false" %> 

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

Copyright &copy; 2009, <a href="http://www.etherfirma.com">Etherfirma, LLC.</a> 
<br/> 
All Rights Reserved.
<br/>
<br /> 

<c:url var="reloadUrl" value="/reload" />
<c:url var="managerUrl" context="/manager" value="/html" /> 

<a class="abutton" href="${reloadUrl}">
	reload
</a> 
&nbsp; 
<a class="abutton" href="${managerUrl}">
	manager
</a> 




<%-- EOF --%> 