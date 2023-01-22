<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Club</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="/css/main.css"/>
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container col-lg-5 mt-4">
		<div class="mt-4 mb-2 d-flex flex-row align-items-center justify-content-between">
			<h1>Add a Book to Your Shelf!</h1>
			<p><a href="/books">back to the shelves</a></p>
		</div>
		<div class="row mt-4 mb-2">
			<form:form action="/books/new" method="POST" modelAttribute="book">
				<div class="row my-2">
					<form:label path="title" class="col-sm-3 col-form-label">Title:</form:label>
					<div class="col-sm-9">
						<form:input type="text" class="form-control" path="title"/>
						<form:errors class="my-2 text-danger" path="title"></form:errors>
					</div>
				</div>
				<div class="row my-2">
					<form:label path="author" class="col-sm-3 col-form-label">Author:</form:label>
					<div class="col-sm-9">
						<form:input type="text" class="form-control" path="author"/>
						<form:errors class="my-2 text-danger" path="author"></form:errors>
					</div>
				</div>
				<div class="row my-2">
					<form:label path="thoughts" class="col-sm-3 col-form-label">My thoughts:</form:label>
					<div class="col-sm-9">
						<form:textarea class="form-control" path="thoughts" rows="4"/>
						<form:errors class="my-2 text-danger" path="thoughts"></form:errors>
					</div>
				</div>
				<div class="row my-2 justify-content-end">
					<input type="submit" class="btn btn-primary col-sm-2" value="Submit"/>
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>