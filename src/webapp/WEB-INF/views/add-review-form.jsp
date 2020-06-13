<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Review</title>
</head>
<body>

	<div>
		<form:form
			action="${pageContext.request.contextPath}/review/saveReview"
			modelAttribute="reviewModel" method="POST">

			<td><label>Product Id</label> <input type="text"
				name="productId" value="${idModel}" readonly /></td>

			<td><label>Review</label> 
				<form:input path="reviewComment" />
				<form:errors path="reviewComment" /> 
			</td>

			<td><label></label> <input type="submit" value="save" /></td>

		</form:form>
		<a href="${pageContext.request.contextPath}/list">Go back...</a>
	</div>

</body>
</html>