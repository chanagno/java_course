<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<%@ page pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Teachers List</title>
</head>
<body>
	<table>
	<tr>
		<th>ID</th>
		<th>First Name</th>
		<th>Last Name</th>
		<th>Delete</th>
		<th>Go to Home Page</th>
	</tr>
	<c:forEach var = "row" items = "${teachers}">
		<tr>
			<td> ${row.id} </td>
			<td> ${row.firstName} </td>
			<td> ${row.lastName} </td>
			<td><a href="/TeachersWebApp/TeacherDeleteController?id=${row.id}">Delete</a></td>
			<td><a href="/TeachersWebApp/TeacherSearchController">Go back</a></td>
			<td><a href="/TeachersWebApp/jsps/UpdateTeacher.jsp">Update</a></td>
		</tr>
	</c:forEach>
	</table>
</body>
</html>
