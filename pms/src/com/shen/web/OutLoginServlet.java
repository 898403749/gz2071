package com.shen.web;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shen.controller.BaseController;
import com.shen.domain.User_Inf;

@WebServlet("/OutLoginServlet/*")
public class OutLoginServlet extends BaseController{

	public String outLogin(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session=req.getSession();
		session.removeAttribute("user");
		return "redirect:/login.jsp";
	}
}
