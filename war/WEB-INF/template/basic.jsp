<%@ page language="java"%>
<%@ page isELIgnored="false"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.weaselworks.com/tessera" prefix="tessera"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">

<html>
<head>
	<tessera:head /> 
</head> 

<body>
	<div id="wrapper">
		<div id="header">
			<tessera:slot name="header" /> 
		</div>
		<div id="body">
			<tessera:slot name="body" />  
		</div>
		<div id="footer">
			<tessera:slot name="footer" /> 
		</div>
	</div>
	
	<div id="debug">
		<tessera:slot name="debug" />  
	</div>

<%@ include file="googa.jsp" %>
</body>
</html>

<%-- EOF --%> 