package com.learn.util;

import jakarta.servlet.http.HttpServletRequest;

public class AccountUtil {
	
	public static String validateRequest(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(username.isBlank() || password.isBlank()) {
			return "username Or password should not be blank";
		}
		
		return null;
	}
	
	public static String validateRegistrationRequest(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		if(username == null || username.isBlank() || 
           password == null || password.isBlank() || 
           email == null || email.isBlank()) {
			return "All fields (username, password, email) are mandatory";
		}
		
		return null;
	}
}