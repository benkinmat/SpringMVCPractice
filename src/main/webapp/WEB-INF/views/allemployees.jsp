<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.2/js/bootstrap.min.js"></script>
<title>All employees</title>
</head>
<body>

	<div class="container">
		<h2>List of Employees</h2>

		<table class="table">
			<tr>
				<td>ID</td>
				<td>NAME</td>
				<td>JOINING DATE</td>
				<td>SALARY</td>
				<td>SSN</td>
				<td>Edit</td>
				<td>Delete</td>
			</tr>

			<c:forEach items="${ employees }" var="employee">
				<tr>
					<td>${ employee.id }</td>
					<td>${ employee.name }</td>
					<td>${ employee.joiningDate }</td>
					<td>${ employee.salary }</td>
					<td>${ employee.ssn }</td>
					<td><a
						href='<c:url value="/edit-${ employee.ssn }-employee"></c:url>'>Edit</a></td>
					<td><a
						href='<c:url value="/delete-${ employee.ssn }-employee"></c:url>'>Delete</a></td>
				</tr>
			</c:forEach>
		</table>

		<br /> <br /> <a href="<c:url value='/new' />">Add New</a>
	</div>

</body>
</html>