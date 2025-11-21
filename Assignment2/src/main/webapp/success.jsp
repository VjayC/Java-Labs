<%@page import="com.learn.bean.AccountBean"%>
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
	
	// if userbean is not created than redirect to login page
	
	AccountBean accountBean = (AccountBean) session.getAttribute("accountBean");

	out.println("Account Email : " + accountBean.getEmail());
	out.println("Account Status : " + accountBean.getStatus());
	%>
</body>
</html>