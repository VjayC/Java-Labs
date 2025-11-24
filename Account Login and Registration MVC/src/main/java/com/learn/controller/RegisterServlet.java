package com.learn.controller;

import java.io.IOException;

import com.learn.bean.AccountBean;
import com.learn.service.AccountService;
import com.learn.util.AccountUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AccountService accountService = new AccountService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 * response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = AccountUtil.validateRegistrationRequest(request);
		HttpSession session = request.getSession();

		if (error == null) {

			AccountBean accountBean = new AccountBean();
			accountBean.setUsername(request.getParameter("username"));
			accountBean.setPassword(request.getParameter("password"));
			accountBean.setEmail(request.getParameter("email"));
			accountBean.setStatus("active"); // Default status for new users

			error = accountService.registerAccountBean(accountBean);

			if (error == null) {
				session.setAttribute("accountBean", accountBean);
				// Forward to success page or login page upon successful registration
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}

		}
		if (error != null) {
			session.setAttribute("error", error);
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
	}

}