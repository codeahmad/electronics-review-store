<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Product</title>
</head>
<body>

	<div>
		<form:form action="${pageContext.request.contextPath}/saveProduct"
			modelAttribute="productModel" method="POST">

			<form:hidden path="id" />
			<td><label>Product Name</label>
				<form:input path="name" />
				<form:errors path="name" />
			</td>

			<td><label></label> <input type="submit" value="save" /></td>

		</form:form>
		<a href="${pageContext.request.contextPath}/list">Go back...</a>

	</div>

</body>
</html>