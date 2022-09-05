<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login</title>

<!--  
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/form.css">
    -->
<link rel="stylesheet" href="/TeachersWebApp/static/css/login.css">
</head>
<body>
	<div class="container-fluid">
		<div class="container">
			<div class="row">
				<h1 class="text-grey">Login</h1>
			</div>
			<form method="POST" action="/TeachersWebApp/LoginController">
				<div class="row">
					<input type="email" name="eMail" required placeholder="E-mail">
					<span></span>
				</div>
				<div class="row">
					<input type="password" name="password" required
						placeholder="Password"> <span></span>
				</div>
				<div class="row">
					<button type="submit">Sign In</button>
				</div>
			</form>
			<form method="POST" action="/TeachersWebApp/CreateUserController">
				<div class="row">
					<h1 class="text-grey">Create an account</h1>
					<input type="email" name="eMail" required placeholder="E-mail">
					<span></span>
				</div>
				<div class="row">
					<input type="password" name="password" required
						placeholder="Password"> <span></span>
				</div>
				<div class="row">
					<button type="submit">Sign up here!</button>
				</div>
			</form>

			<form method="POST" action="/TeachersWebApp/UpdateUserController">
				<div class="row">
					<h1 class="text-grey">Sign up</h1>
					<input type="email" name="eMail" required placeholder="E-mail">
					<span></span>
				</div>
				<div class="row">
					<input type="password" name="password" required
						placeholder="Password"> <span></span>
				</div>

				<div class="row">
					<input type="password" name="Newpassword" required
						placeholder="New Password"> <span></span>
				</div>
				<div class="row">
					<button type="submit">Change Password here!</button>
				</div>
			</form>
		</div>
	</div>

	<div style="color: red">
		<br> ${error}
	</div>

</body>
</html>