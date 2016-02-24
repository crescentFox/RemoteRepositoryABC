<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="<c:url value="/css/table.css" />" />
<title>Display</title>
</head>
<body>
	<h3>Select Product Table Result : XXX row(s) selected</h3>
	<c:if test="${not empty select}">
		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Price</th>
					<th>Make</th>
					<th>Expire</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="abc" items="${select}">
					<c:url value="/pages/product.jsp" var="path" scope="page">
						<c:param name="id" value="${abc.id}" />
						<c:param name="name" value="${abc.name}" />
						<c:param name="price" value="${abc.price}" />
						<c:param name="make" value="${abc.make}"/>
						<c:param name="expire" value="${abc.expire}" />
					</c:url>
					<tr>
						<td><a href="${path}">${abc.id}</a></td>
						<td>${abc.name}</td>
						<td>${abc.price}</td>
						<td>${abc.make}</td>
						<td>${abc.expire}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
	<h3>
	
		<a href="<c:url value='/pages/product.jsp' /> ">Product Table</a>
	</h3>
</body>
</html>