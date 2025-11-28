package com.learn;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. Get all the parameters from the form
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		try {
			// 2. Get the database connection from the listener
			Connection con = (Connection) getServletContext().getAttribute("dbConnection");
			
			// 3. Create the SQL statement
			String sql = "INSERT INTO account (username, password, first_name, last_name) VALUES (?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, firstName);
			ps.setString(4, lastName);
			
			// 4. Execute the statement
			ps.executeUpdate();
			
			ps.close();
			
			// 5. Load the login page (as per instruction)
			response.sendRedirect("login.html");
			
		} catch (Exception e) {
			// Handle errors (like duplicate username)
			e.printStackTrace();
			// You could also forward back to register.html with an error message
			response.sendRedirect("register.html");
		}
	}
}