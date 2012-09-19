<%@ page language="java" %> 
<%@ page isELIgnored="false" %>

<%@ page import="com.tessera.intercept.util.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.weaselworks.com/tessera/form" prefix="tform"%>

<H1>Types Form</H1>

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
			<th>boolean:</th>
			<td>
				<tform:text name="boolean1" /> 
			</td>
			<td>
				<tform:ifError name="boolean1">
					<div class="fieldError">
						<tform:error name="boolean1" />
					</div>					 
				</tform:ifError>
			</td>
		</tr>
		<tr>
			<th>Boolean:</th>
			<td>
				<tform:text name="boolean2" /> 
			</td>
			<td>
				<tform:ifError name="boolean2">
					<div class="fieldError">
						<tform:error name="boolean2" />
					</div>					 
				</tform:ifError>
			</td>
		</tr>
	
		<!-- short -->
		
		<tr>
			<th>short:</th>
			<td>
				<tform:text name="short1" /> 
			</td>
			<td>
				<tform:ifError name="short1">
					<div class="fieldError">
						<tform:error name="short1" />
					</div>					 
				</tform:ifError>
			</td>
		</tr>
		<tr>
			<th>Short:</th>
			<td>
				<tform:text name="short2" /> 
			</td>
			<td>
				<tform:ifError name="short2">
					<div class="fieldError">
						<tform:error name="short2" />
					</div>					 
				</tform:ifError>
			</td>
		</tr>

		<!-- character -->
	
		<tr>
			<th>char:</th>
			<td>
				<tform:text name="char" /> 
			</td>
			<td>
				<tform:ifError name="char">
					<div class="fieldError">
						<tform:error name="char" />
					</div>					 
				</tform:ifError>
			</td>
		</tr>
		<tr>
			<th>Character:</th>
			<td>
				<tform:text name="character" /> 
			</td>
			<td>
				<tform:ifError name="character">
					<div class="fieldError">
						<tform:error name="character" />
					</div>					 
				</tform:ifError>
			</td>
		</tr>

		<!-- float -->
		<tr>
			<th>float:</th>
			<td>
				<tform:text name="float1" /> 
			</td>
			<td>
				<tform:ifError name="float1">
					<div class="fieldError">
						<tform:error name="float1" />
					</div>					 
				</tform:ifError>
			</td>
		</tr>
		<tr>
			<th>Float:</th>
			<td>
				<tform:text name="float2" /> 
			</td>
			<td>
				<tform:ifError name="float2">
					<div class="fieldError">
						<tform:error name="float2" />
					</div>					 
				</tform:ifError>
			</td>
		</tr>

		<!-- double -->
		<tr>
			<th>double:</th>
			<td>
				<tform:text name="double1" /> 
			</td>
			<td>
				<tform:ifError name="double1">
					<div class="fieldError">
						<tform:error name="double1" />
					</div>					 
				</tform:ifError>
			</td>
		</tr>
		<tr>
			<th>Double:</th>
			<th>
				<tform:text name="double2" /> 
			</th>
			<td>
				<tform:ifError name="double2">
					<div class="fieldError">
						<tform:error name="double2" />
					</div>					 
				</tform:ifError>
			</td>
		</tr>

		<!-- integer -->
	
		<tr>
			<th>int:</th>
			<td>
				<tform:text name="int" /> 
			</td>
			<td>
				<tform:ifError name="int">
					<div class="fieldError">
						<tform:error name="int" />
					</div>					 
				</tform:ifError>
			</td>
		</tr>
		<tr>
			<th>Integer:</th>
			<td>
				<tform:text name="integer" /> 
			</td>
			<td>
				<tform:ifError name="integer">
					<div class="fieldError">
						<tform:error name="integer" />
					</div>					 
				</tform:ifError>
			</td>
		</tr>
		
		<tr>
			<th>long:</th>
			<td>
				<tform:text name="long1" /> 
			</td>
			<td>
				<tform:ifError name="long1">
					<div class="fieldError">
						<tform:error name="long1" />
					</div>					 
				</tform:ifError>
			</td>
		</tr>
		<tr>
			<th>Long:</th>
			<td>
				<tform:text name="long2" /> 
			</td>
			<td>
				<tform:ifError name="long2">
					<div class="fieldError">
						<tform:error name="long2" />
					</div>					 
				</tform:ifError>
			</td>
		</tr>

		<tr>
			<th>String:</th>
			<td>
				<tform:text name="string" /> 
			</td>
			<td>
				<tform:ifError name="string">
					<div class="fieldError">
						<tform:error name="string" />
					</div>					 
				</tform:ifError>
			</td>
		</tr>

		<tr>
			<td>
			</td>
			<td>
				<tform:submit value="Submit" />  
				<input type="button" value="Cancel" onClick="history.go (-1);" /> 
			</td>		
		</tr>
	</table>
</form>



<%-- EOF --%> 