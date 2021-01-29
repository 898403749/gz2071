package com.shen.web;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import com.shen.controller.BaseController;
import com.shen.dao.UserDao;
import com.shen.domain.User_Inf;
import com.shen.service.LoginService;

@WebServlet("/LoginServlet/*")
public class LoginServlet extends BaseController{
	
	public String checkLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginService ls=new LoginService();
		JSONObject json=new JSONObject();
		String u=req.getParameter("username");
		String p=req.getParameter("password");
		String v=req.getParameter("validate");
		String remember=req.getParameter("remember");
		String rememberinfor=null;
		HttpSession session=req.getSession();
		String codeStr=(String) session.getAttribute("codeStr");
		if(v==null||codeStr.equalsIgnoreCase(v)) {
			try {
				User_Inf user_inf=ls.login(u,p);
				if(user_inf!=null) {
					json.put("login_status","true");
					session.setAttribute("user", user_inf);
					session.setAttribute("logintime", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(session.getLastAccessedTime()));
					json.put("reason","success");
					rememberinfor=user_inf.getLoginname()+":";
					if(!"".equals(remember)&&remember!=null) {
						rememberinfor=user_inf.getLoginname()+":"+user_inf.getPassword();
					}
					setRememberCookie(req,resp,"remember",rememberinfor,60*60*24*7,"/");
					return "data:"+json.toString();
				}
				if(ls.checkUsername(u)) {
					json.put("reason","ÃÜÂë´íÎó");
				}else {
					json.put("reason","ÎÞ´ËÕËºÅ");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			json.put("login_status","false");
			json.put("reason","ÑéÖ¤Âë´íÎó");
			return "data:"+json.toString();
		}
		json.put("login_status","false");
		return "data:"+json.toString();
	}
	
	public String loginStatus(HttpServletRequest req, HttpServletResponse resp) {
		String login_status=req.getParameter("login_status");
        HttpSession session=req.getSession();
        session.setAttribute("login_status", login_status);
		return "data: ";
	}
	
	private void setRememberCookie(HttpServletRequest req,HttpServletResponse resp,String cookiename,String rememberinfor,int cookietime,String cookiepath) {
		Cookie ck=null;
		Cookie[] cookies = req.getCookies();
		if(cookies != null && cookies.length > 0){
		     for (Cookie cookie : cookies){
		    	 if(cookiename.equals(cookie.getName())) {
		    		 ck=cookie;
		    	 }
		     }
		}
		if(ck==null) {
			ck=new Cookie("remember",rememberinfor);
			ck.setMaxAge(cookietime);
			ck.setPath(cookiepath);
			resp.addCookie(ck);
		}else {
			ck.setValue(rememberinfor);
    		ck.setMaxAge(cookietime);
			ck.setPath(cookiepath);
			resp.addCookie(ck);
		}
	} 

}
