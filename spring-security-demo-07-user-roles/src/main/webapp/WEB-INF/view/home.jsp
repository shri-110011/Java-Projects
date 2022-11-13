<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
	</head>
	<body>
		<h2>luv2code company homepage</h2>
		<hr>
		<p>
			Welcome to luv2code company Home Page!
		</p>
		
		<hr>
		
		<!-- Display user name and role -->
		<p>
			User: <security:authentication property="principal.username" />
			<br><br>
			<!-- Note: Spring security will by default add the prefix "ROLE_" while displaying the
			list of roles. -->
			Role(s): <security:authentication property="principal.authorities" />
		</p>
		
		<!-- Add a link to point to /leaders ... this is for the managers -->
		<security:authorize access="hasRole('MANAGER')">
			<p>
				<a href="${pageContext.request.contextPath}/leaders">Leadership Meeting</a>
				(Only for Manager folks)
			</p>
		</security:authorize>
		
		<!-- Add a link to point to /systems ... this is for the admins -->
		<security:authorize access="hasRole('ADMIN')">
			<p>
				<a href="${pageContext.request.contextPath}/systems">IT Systems Meeting</a>
				(Only for Admin folks)
			</p>
		</security:authorize>
		
		<hr>
		
		<!-- Add logout button -->
		<form:form action="${pageContext.request.contextPath}/logout">
			<input type="Submit" value = "Logout"/>
		</form:form>
	</body>
</html>