package com.learn;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// NO init() OR destroy() METHODS NEEDED!
	// The listener handles the connection.

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String accountUsername = request.getParameter("username");
		String accountPassword = request.getParameter("password");
		
		try {
			// 1. Get the shared database connection
			Connection con = (Connection) getServletContext().getAttribute("dbConnection");
			
			// 2. Create and execute the query
			PreparedStatement ps = con.prepareStatement("select * from account where username=? and password=?");
			ps.setString(1, accountUsername);
			ps.setString(2, accountPassword);
			ResultSet rs = ps.executeQuery();
			
			// 3. Check if a user was found
			if(rs.next()) {
				// SUCCESS (as per instruction)
				
				// Create a session to store the username
				HttpSession session = request.getSession();
				session.setAttribute("username", accountUsername);
				
				// Load the home page
				RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
				rd.forward(request, response);
			} else {
				// FAILURE (as per instruction)
				
				// Send back to login page
				response.sendRedirect("login.html");
			}
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}