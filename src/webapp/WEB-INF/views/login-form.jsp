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
<title>Authority login</title>
</head>
<body>

	<div class="container">
		<div class="col-md-12 text-center" >
			<core:if test="${param.logout != null}">
				<hr />
				<i class="test-success"> Thank you for visiting</i>
				<hr />
				<hr />
			</core:if>

			<core:if test="${param.error != null}">
				<hr />
				<i class="text-danger"> Sorry invalid credentials </i>
				<hr />
				<hr />
			</core:if>

			<form:form
				action="${pageContext.request.contextPath}/checkUserCredentials"
				class="form-group" method="POST">
				<td><label>User Name</label> <input type="text" name="username" />
				</td>

				<td><label>Password </label> <input type="password"
					name="password" /></td>

				<td><label></label> <input type="submit" value="Login" /></td>

			</form:form>

			Go to <a href="${pageContext.request.contextPath}/list"> products</a>
		</div>
	</div>
</body>
</html>