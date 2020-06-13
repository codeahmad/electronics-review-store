<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
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
<title>Electronics Store</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12 text-center">
				<h1>Welcome to computer store</h1>
				<hr />
				<hr />

				<security:authorize access="hasRole('MANAGER')"> 
					Welcome Back <b><security:authentication
							property="principal.username" /></b>
					<br />
					<hr />
					As your role is: <b><security:authentication
							property="principal.authorities" /></b>
					<br />
					You you are authorize to perform Add and Update.
					<hr />
					<hr />
				</security:authorize>

				<security:authorize access="hasRole('CEO')"> 
					Welcome Back <b><security:authentication
							property="principal.username" /></b>
					<br />
					<hr />
					As your role is: <b><security:authentication
							property="principal.authorities" /></b>
					<br />
					You you are authorize to perform All CRUD operations.
					<hr />
					<hr />
				</security:authorize>

				<security:authorize access="hasRole('EMPLOYEE')"> 
					Welcome Back <b><security:authentication
							property="principal.username" /></b>
					<br />
					<hr />
					As your role is: <b><security:authentication
							property="principal.authorities" /></b>
					<br />
					You you are only authorize to perform Update operation.
					<hr />
					<hr />
				</security:authorize>
			</div>

			<div class="col-md-12">
				<div class="row">
					<div class="col-md-6 text-center">
						<button class="btn-primary"
							onClick="window.location.href='addProduct'">Add Product</button>
					</div>


					<div class="col-md-6 text-center">
						<a href="${pageContext.request.contextPath}/loginForm">Login</a>
					</div>
				</div>
				<div class="text-center">
					<table class="table">
						<thead>
							<tr>
								<th>Product Id</th>
								<th>Product Name</th>
								<th>Customers and Reviews</th>
								<security:authorize
									access="hasAnyRole('MANAGER','EMPLOYEE','CEO')">
									<th>Add Customer</th>
									<th>Add Review</th>
									<th>Action</th>
								</security:authorize>
							</tr>
						</thead>

						<tbody>
							<core:forEach var="productVar" items="${productModel}">
								<core:url var="customerAndReview" value="customerAndReviewPage">
									<core:param name="productId" value="${productVar.id}"></core:param>
								</core:url>

								<core:url var="addCustomer" value="/customer/addCustomer">
									<core:param name="productId" value="${productVar.id}"></core:param>
								</core:url>

								<core:url var="addReview" value="review/addReview">
									<core:param name="productId" value="${productVar.id}"></core:param>
								</core:url>

								<core:url var="deleteProduct" value="/deleteProduct">
									<core:param name="productId" value="${productVar.id}"></core:param>
								</core:url>

								<core:url var="updateProduct" value="/updateProduct">
									<core:param name="productId" value="${productVar.id}"></core:param>
								</core:url>
								<tr>
									<td>${productVar.id}</td>
									<td>${productVar.name}</td>
									<td><a href="${customerAndReview}">Click here</a></td>
									<security:authorize
										access="hasAnyRole('MANAGER','EMPLOYEE','CEO')">
										<td><a href="${addCustomer}">Add Customer</a></td>
										<td><a href="${addReview}">Add Review</a></td>
										<td><a href="${deleteProduct}">Delete</a>| <a
											href="${updateProduct}">Edit</a></td>
									</security:authorize>
								</tr>
							</core:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div>
			<security:authorize access="hasAnyRole('MANAGER','EMPLOYEE','CEO')">
				<form:form action="${pageContext.request.contextPath}/logout"
					method="POST">
					<input type="submit" value="Logout" />
				</form:form>
			</security:authorize>
		</div>
</body>
</html>