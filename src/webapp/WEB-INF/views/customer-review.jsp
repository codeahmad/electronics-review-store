<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
<meta charset="ISO-8859-1">
<meta charset="ISO-8859-1">
<title>Customers and Reviews</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12 text-center">
				<h1 class="text-primary">Welcome to customers and reviews page</h1>
				<hr />
				<hr />
			</div>

			<div class="col-md-4 border-left border-right">
				<h3 class="text-success">${productModel.name}</h3>
			</div>

			<div class="col-md-4 border-right">
				<core:forEach var="customerVar" items="${customerModel}">
				${customerVar.name}<br />
				</core:forEach>
			</div>

			<div class="col-md-4 border-right">
				<core:forEach var="reviewVar" items="${reviewModel}">
				${reviewVar.reviewComment}<br />
				</core:forEach>
				<br />
			</div>

			<a href="${pageContext.request.contextPath}/list">Go Back...</a>
		</div>
	</div>
</body>
</html>