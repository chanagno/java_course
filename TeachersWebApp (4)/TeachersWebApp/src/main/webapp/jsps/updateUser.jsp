<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<%@ page pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Επιτυχής Αλλαγή Password</title>
</head>
<body>
	<p>	User:  <br>
	 	${updatedUser.username} <br>
	 	${updatedUser.password} <br>
	   	successfully updated!
	
	<a href="/TeachersWebApp/jsps/login.jsp">Επιστροφή στην αρχική</a>

</body>
</html>