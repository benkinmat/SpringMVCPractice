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
<title>Manage User's Document</title>
</head>
<body>

	<div class="container">
		<h2>All of employee's documents</h2>

		<table class="table table-nonfluid">
			<thead>
				<tr>
					<th>NO</th>
					<th>Name</th>
					<th>Description</th>
					<th></th>
					<th></th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${ documents }" var="doc" varStatus="counter">
					<tr>
						<td>${ counter.index + 1 }</td>
						<td>${ doc.name }</td>
						<td>${ doc.description }</td>
						<td><a
							href='<c:url value="/download-document-${ employee.id }-${ doc.id}"></c:url>'
							class="btn btn-success custom-width">Download</a></td>
						<td><a
							href='<c:url value="/delete-document-${ employee.id }-${ doc.id}"></c:url>'
							class="btn btn-danger custom-width">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<form:form modelAttribute="fileBucker" method="POST"
			class="form-horizontal" enctype="multipart/form-data">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="file">Upload a
					document</label>
				<div class="col-md-10">
					<form:input type="file" path="file" id="file"
						class="form-control" />
				</div>
			</div>

			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="description">Description</label>
				<div class="col-md-10">
					<form:input type="text" path="description" id="description"
						class="form-control" />
				</div>
			</div>

			<div class="form-group col-md-12">
				<button type="submit" class="btn btn-default" value="Upload">Upload</button>
				<a href="<c:url value='/list' />" class="btn btn-default">Cancel</a>
			</div>
		</form:form>

	</div>

</body>
</html>