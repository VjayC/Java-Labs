package com.learn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.learn.bean.AccountBean;
import com.learn.service.AccountService;
import com.learn.util.AccountUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class AccountController {

	private AccountService accountService = new AccountService();

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView showIndex() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView showLogin() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView showRegister() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("register");
		return modelAndView;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView processLogin(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		
		String error = AccountUtil.validateRequest(request);
		HttpSession session = request.getSession();

		if (error == null) {
			AccountBean accountBean = new AccountBean();
			accountBean.setUsername(request.getParameter("username"));
			accountBean.setPassword(request.getParameter("password"));

			error = accountService.authenticateAndPopulateAccountBean(accountBean);

			if (error == null) {
				session.setAttribute("accountBean", accountBean);
				modelAndView.setViewName("success");
				return modelAndView;
			}
		}
		
		if (error != null) {
			session.setAttribute("error", error);
			modelAndView.setViewName("login");
		}
		
		return modelAndView;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView processRegister(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		
		String error = AccountUtil.validateRegistrationRequest(request);
		HttpSession session = request.getSession();

		if (error == null) {
			AccountBean accountBean = new AccountBean();
			accountBean.setUsername(request.getParameter("username"));
			accountBean.setPassword(request.getParameter("password"));
			accountBean.setEmail(request.getParameter("email"));
			accountBean.setStatus("active");

			error = accountService.registerAccountBean(accountBean);

			if (error == null) {
				session.setAttribute("accountBean", accountBean);
				modelAndView.setViewName("login");
				return modelAndView;
			}
		}
		
		if (error != null) {
			session.setAttribute("error", error);
			modelAndView.setViewName("register");
		}
		
		return modelAndView;
	}
}