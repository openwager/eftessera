<%@ page language="java" %> 
<%@ page isELIgnored="false" %>

<%@ page import="com.tessera.intercept.util.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.weaselworks.com/tessera" prefix="tessera"%>
<%@ taglib uri="http://www.weaselworks.com/tessera/login" prefix="tlogin"%>

<H1>Login Manager</H1>


<ul>
	<li>
		<tlogin:ifLoginManager>
			<tlogin:getLoginManager var="lm" />
			Login manager is: <tt>${lm.class.name}</tt>
		</tlogin:ifLoginManager>
		<tlogin:ifNotLoginManager>
			No LoginManager!
		</tlogin:ifNotLoginManager>
	</li>
	<li>
		<tlogin:ifLoggedIn>
			<tlogin:getLoginSession var="ls" /> 
			Logged in as: ${ls.username} 					
		</tlogin:ifLoggedIn>
		<tlogin:ifNotLoggedIn>
			Not logged in		
		</tlogin:ifNotLoggedIn>			
	</li>
</ul>

<%-- EOF --%> 