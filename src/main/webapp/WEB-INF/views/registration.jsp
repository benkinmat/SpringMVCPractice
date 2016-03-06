<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Form</title>
</head>
<body>

	<div class="container">
		<h2>Registration Form</h2>

		<form:form method="POST" modelAttribute="employee"
			class="form-horizontal">
			<fieldset>
				<div class="form-group" hidden="true">
					<label class="control-label	col-lg-2 col-lg-2" for="id">ID</label>
					<div class="col-lg-10">
						<form:input path="id" id="id" class="form-control" />
					</div>
				</div>

				<div class="form-group">
					<label class="control-label	col-lg-2 col-lg-2" for="name">Name</label>
					<div class="col-lg-10">
						<form:input path="name" id="name" class="form-control" />
					</div>
				</div>

				<div class="form-group">
					<label class="control-label	col-lg-2 col-lg-2" for="joiningDate">Joining
						Date</label>
					<div class="col-lg-10">
						<form:input path="joiningDate" id="joiningDate"
							class="form-control" />
					</div>
				</div>

				<div class="form-group">
					<label class="control-label	col-lg-2 col-lg-2" for="salary">Salary</label>
					<div class="col-lg-10">
						<form:input path="salary" id="salary" class="form-control" />
					</div>
				</div>

				<div class="form-group">
					<label class="control-label	col-lg-2 col-lg-2" for="ssn">SSN</label>
					<div class="col-lg-10">
						<form:input path="ssn" id="ssn" class="form-control" />
					</div>
				</div>

				<div class="form-group">
					<label class="control-label	col-lg-2 col-lg-2" for="userRoleSet">ROLE</label>
					<div class="col-lg-10">
						<form:select path="userRoleSet" items="${ roles }" multiple="true"
							itemValue="id" itemLabel="role" class="form-control input-sm" />
					</div>
				</div>

				<div class="form-group">
					<c:choose>
						<c:when test="${ edit }">
							<button type="submit" class="btn btn-default" value="Update">Update</button>
						</c:when>
						<c:otherwise>
							<button type="submit" class="btn btn-default" value="Register">Register</button>
						</c:otherwise>
					</c:choose>

					<a href="<c:url value='/list' />" class="btn btn-default">Cancel</a>
				</div>
				
				<div class="form-group">
					<c:if test="${ edit }">
						<a href='<c:url value='/upload-document-${ employee.id }'></c:url>'>Upload User's Documents</a>
					</c:if>
				</div>
			</fieldset>
		</form:form>
	</div>

</body>
</html>