<%@ page language="java" %> 
<%@ page isELIgnored="false" %>

<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.7.0/build/fonts/fonts-min.css" /> 
<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.7.0/build/tabview/assets/skins/sam/tabview.css" /> 
<script type="text/javascript" src="http://yui.yahooapis.com/2.7.0/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
<script type="text/javascript" src="http://yui.yahooapis.com/2.7.0/build/element/element-min.js"></script> 
<script type="text/javascript" src="http://yui.yahooapis.com/2.7.0/build/tabview/tabview-min.js"></script> 

<div id="demo" class="yui-navset">
	<ul class="yui-nav">
		<li class="selected"><a href="#tab1">Forms</a></li>
		<li><a href="#tab2">Cookies</a></li>
		<li><a href="#tab3">Headers</a></li>
		<li><a href="#tab4">Request</a></li>
		<li><a href="#tab5">Attributes</a></li>
		<li><a href="#tab6">Parameters</a></li>
		<li><a href="#tab7">Session</a></li>
		<li><a href="#tab7">Servlet Context</a></li>
	</ul>
	<div class="yui-content">
		<div id="tab1">
			<%@ include file="forms.jsp" %> 			
		</div>
		<div id="tab2">
			<%@ include file="cookies.jsp" %> 			
		</div>
		<div id="tab3">
			<%@ include file="header.jsp" %> 			
		</div>
		<div id="tab4">
			<%@ include file="request.jsp" %> 			
		</div>
		<div id="tab5">
			<%@ include file="requestAttrs.jsp" %> 			
		</div>
		<div id="tab6">
			<%@ include file="parameters.jsp" %> 			
		</div>
		<div id="tab7">
			<%@ include file="session.jsp" %> 			
		</div>
		<div id="tab8">
			<%@ include file="servlet.jsp" %> 			
		</div>
	</div>
</div>

<script type="text/javascript"> 
    var tabView = new YAHOO.widget.TabView('demo'); 
</script> 

<%-- EOF --%> 