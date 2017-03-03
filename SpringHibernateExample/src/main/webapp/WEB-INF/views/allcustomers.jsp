<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>University Enrollments</title>

	<style>
		tr:first-child{
			font-weight: bold;
			background-color: #C6C9C4;
		}
	</style>

</head>


<body>
	<h2>List of Customers</h2>	
	<table>
		<tr>
			<td>NAME</td><td>Street</td><td>City</td><td>SSN</td><td></td>
		</tr>
		<c:forEach items="${customers}" var="customer">
			<tr>
			<td>${customer.name}</td>
			<td>${customer.street}</td>
			<td>${customer.city}</td>
			<td><a href="<c:url value='/customer/edit-${customer.ssn}-customer' />">${customer.ssn}</a></td>
			<td><a href="<c:url value='/customer/delete-${customer.ssn}-customer' />">delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<br/>
	<a href="<c:url value='/customer/new' />">Add New Customer</a>
</body>
</html>