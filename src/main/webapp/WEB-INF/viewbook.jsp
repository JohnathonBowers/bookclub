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
	<div class="container mt-4 mb-2 col-lg-6">
		<div class="my-2 d-flex flex-row align-items-center justify-content-between">
			<h1><em><c:out value="${book.title}"/></em></h1>
			<p><a href="/books">back to the shelves</a></p>
		</div>
		<div class="row mt-4 mb-2">
			<c:if test="${book.user.id == userId}">
				<p><strong><span class="text-danger">You</span> read <span id="purple"><c:out value="${book.title}"/></span> by <span class="text-success"><c:out value="${book.author}"/></span></strong></p>
				<p>Here are your thoughts:</p>
			</c:if>
			<c:if test="${book.user.id != userId}">
				<p><strong><span class="text-danger"><c:out value="${book.user.name}"/></span> read <span id="purple"><c:out value="${book.title}"/></span> by <span class="text-success"><c:out value="${book.author}"/></span></strong></p>
				<p>Here are <c:out value="${book.user.name}"/>'s thoughts:</p>
			</c:if>
		</div>
		<div class="row mt-2 mx-4">
			<hr class="border border-dark opacity-75"/>
			<p><c:out value="${book.thoughts}"/></p>
			<hr class="border border-dark opacity-75"/>
		</div>
		<div class="d-flex flex-row align-items-center justify-content-end">
			<c:if test="${book.user.id == userId}">
				<a href="/books/${book.id}/edit" class="btn btn-warning me-2">Edit</a>
				<form action="/books/${book.id}/delete" method="POST">
					<input type="hidden" name="_method" value="delete"/>
					<input type="submit" class="btn btn-danger ms-2" value="Delete"/>
				</form>
			</c:if>
		</div>
	</div>
</body>
</html>