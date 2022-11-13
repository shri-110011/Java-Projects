<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
		<style type="text/css">
			.login-failed {
				color: red;
			}
		</style>
	</head>
	<body>
		<h3>My Custom Login Page</h3>
		
		<!-- Note spring will automatically pass the error parameter in the url when the login fails. -->
		<!-- Check for login error -->
		<c:if test="${param.error != null}">
			<i class="login-failed">Sorry! You entered invalid username/password</i>
		</c:if>
		
		<form:form action="${pageContext.request.contextPath}/authenticateTheUser" method="POST">
			<p>
				User name: <input type="text" name="username" />
			</p>
			<p>
				Password: <input type="text" name="password" />
			</p>
			
			<input type="submit" value="Login" />
		</form:form>
	</body>
</html>