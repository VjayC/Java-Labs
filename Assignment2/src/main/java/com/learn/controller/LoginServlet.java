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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AccountService accountService = new AccountService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = AccountUtil.validateRequest(request);
		HttpSession session = request.getSession();

		if (error == null) {

			AccountBean accountBean = new AccountBean();
			accountBean.setUsername(request.getParameter("username"));
			accountBean.setPassword(request.getParameter("password"));

			error = accountService.authenticateAndPopulateAccountBean(accountBean);

			if (error == null) {
				session.setAttribute("accountBean", accountBean);
				request.getRequestDispatcher("success.jsp").forward(request, response);
//				response.sendRedirect("success.jsp");
			}

		}
		if (error != null) {
			session.setAttribute("error", error);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}