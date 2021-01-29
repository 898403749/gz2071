package com.shen.web;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shen.dao.MenuDao;

@WebServlet("/getMenu")
public class MenuController extends BaseController{
	
	public String getMenu(HttpServletRequest req,HttpServletResponse resp) {
		ServletContext ctx=req.getServletContext();
		ctx.setAttribute("menu",(new MenuDao()).getMenuMap());
		return "forward:/MenuList.jsp";
	}

}
