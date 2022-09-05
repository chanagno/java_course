<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<%@ page pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Teacher</title>
</head>
<body>	<p>	Teacher:  <br>
	 	${updatedTeacher.lastName} <br>
	 	${updatedTeacher.firstName} <br>
	   	successfully updated!
	

	<a href="/TeachersWebApp/jsps/teachersmenu.jsp">Επιστροφή στην αρχική</a>

</body>
</html>