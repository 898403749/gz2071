package com.shen.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class LoginFilter extends BaseFilter{

	
	@Override
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) {
		HttpSession session=req.getSession();
		StringBuffer path=req.getRequestURL();
		if(session.getAttribute("user")==null) {
			if("http://localhost:8080/pms/login.jsp".equals(path.toString())||
					"http://localhost:8080/pms/bootstrap3.3.7/js/jquery-3.1.1.min.js".equals(path.toString())||
					"http://localhost:8080/pms/bootstrap3.3.7/js/bootstrap.min.js".equals(path.toString())||
					"http://localhost:8080/pms/css/login_web.css".equals(path.toString())||
					"http://localhost:8080/pms/bootstrap3.3.7/css/bootstrap.min.css".equals(path.toString())||
					"http://localhost:8080/pms/jQ/jquery-1.11.1.min.js".equals(path.toString())||
					"http://localhost:8080/pms/img/loginweb_photo1.jpg".equals(path.toString())||
					"http://localhost:8080/pms/CodeServlet".equals(path.toString())||
					"http://localhost:8080/pms/LoginServlet/checkLogin".equals(path.toString())||
					"http://localhost:8080/pms/bootstrap3.3.7/fonts/glyphicons-halflings-regular.woff2".equals(path.toString())||
					"http://localhost:8080/pms/bootstrap3.3.7/fonts/glyphicons-halflings-regular.woff".equals(path.toString())||
					"http://localhost:8080/pms/bootstrap3.3.7/fonts/glyphicons-halflings-regular.ttf".equals(path.toString())||
					"http://localhost:8080/pms/bootstrap3.3.7/css/bootstrap.min.css.map".equals(path.toString())) {
				try {
					chain.doFilter(req, resp);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				String ctxPath = req.getContextPath();
				try {
					resp.sendRedirect(ctxPath+"/login.jsp");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}else {
			try {
				chain.doFilter(req, resp);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServletException e) {
				e.printStackTrace();
			}
		}
		
	}
}
