<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
</head>
<body>
	<%
		// Optional: Redirect to login if user is not logged in
		if(session.getAttribute("username") == null) {
			response.sendRedirect("login.html");
		}
	%>

	<h2>Welcome, ${username}!</h2>
	<p>Login is successful. (This is from a JSP page).</p>

</body>
</html>