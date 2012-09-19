<%@ page language="java" %> 
<%@ page isELIgnored="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
</head>
<body>
	<h1>Hi</h1>
	
	<c:url var="url" value="/">
		<c:param name="a" value="b" /> 
	</c:url>
	<a href="${url}">
		[url]
	</a>
</body>
</html>

<%-- EOF --%> 