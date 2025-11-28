<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
</head>
<body>
	<%
	Object obj = session.getAttribute("error");
	if (obj != null) {
		String error = (String) obj;
		out.println(error);
		session.removeAttribute("error"); // Clear error after displaying
	}
	%>

	<form action="register" method="post">
		username : <input name="username"> <br> 
		password : <input type="password" name="password"><br> 
		email : <input name="email"><br>
		<input type="submit" name="Register">
	</form>
</body>
</html>