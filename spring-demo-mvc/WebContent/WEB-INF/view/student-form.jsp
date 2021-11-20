<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Student Registration Form</title>
	</head>
	<body>
		<form:form action="processForm" modelAttribute="student">
		
			First Name: <form:input path="firstName" />
			
			<br><br>
			
			Last Name: <form:input path="lastName" />
			
			<br><br>
			
			Country:
			
			<form:select path="country">
				<!--<form:option value="Brazil" label="Brazil"></form:option>
				<form:option value="Germany" label="Germany"></form:option>
				<form:option value="Japan" label="Japan"></form:option>
				<form:option value="India" label="India"></form:option>
				<form:option value="USA" label="United States of America"></form:option>-->
				<form:options items="${student.countryOptions}"></form:options>
			</form:select>
			
			<br><br>
			
			Java <form:radiobutton path="favouriteLanguage" value="Java"/>
			Python <form:radiobutton path="favouriteLanguage" value="Python"/>
			PHP <form:radiobutton path="favouriteLanguage" value="PHP"/>
			C++ <form:radiobutton path="favouriteLanguage" value="C++"/>
			
			<br><br>
			
			Operating Systems:
			
			Linux <form:checkbox path="operatingSystems" value="Linux"/>
			MAC OS <form:checkbox path="operatingSystems" value="MAC OS"/>
			MS Windows <form:checkbox path="operatingSystems" value="MS Windows"/>
			
			<br><br>
		
			<button type="Submit">Submit</button>
	
		</form:form>
	</body>
</html>