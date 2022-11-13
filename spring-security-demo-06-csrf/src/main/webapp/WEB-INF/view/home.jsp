<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		
		<!-- Add logout button -->
		<form:form action="${pageContext.request.contextPath}/logout">
			<input type="Submit" value = "Logout"/>
		</form:form>
	</body>
</html>