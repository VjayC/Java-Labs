<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
	Object obj = session.getAttribute("error");

	if (obj != null) {
		String error = (String) obj;
		out.println(error);
		session.removeAttribute("error"); // Clear error after displaying
	}
	
	// if userbean is created than redirect to success page
	%>

	<form action="login" method="post">
		username : <input name="username"> <br> password : <input
			type="password" name="password"><br> <input
			type="submit" name="Login">
	</form>
</body>
</html>