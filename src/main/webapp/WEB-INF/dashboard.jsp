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
	<div class="container col-lg-6 mt-4">
		<div class="mt-4 mb-2 d-flex flex-row align-items-center justify-content-between">
			<h1>Welcome, <c:out value="${user.name}"/></h1>
			<p><a href="/logout">logout</a></p>
		</div>
		<div class="my-2 d-flex flex-row align-items-center justify-content-between">
			<p>Books from everyone's shelves:</p>
			<p><a href="/books/new">+ Add a Book to My Shelf!</a></p>
		</div>
		<div class="row my-2">
			<table class="table table-striped table-hover table-bordered">
				<thead>
					<tr class="text-center">
						<th>ID</th>
						<th>Title</th>
						<th>Author Name</th>
						<th>Posted By</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="book" items="${allBooks}">
						<tr class="text-center">
							<td><c:out value="${book.id}"></c:out></td>
							<td><a href="/books/${book.id}"><c:out value="${book.title}"/></a></td>
							<td><c:out value="${book.author}"></c:out></td>
							<td><c:out value="${book.user.name}"></c:out></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>