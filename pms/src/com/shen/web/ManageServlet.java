package com.shen.web;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shen.controller.BaseController;
import com.shen.domain.Document_Inf;
import com.shen.domain.PageModel;
import com.shen.domain.UserPageModel;
import com.shen.domain.User_Inf;
import com.shen.service.FileService;
import com.shen.service.UserService;


@WebServlet("/ManageServlet/*")
public class ManageServlet extends BaseController{
	
	static Integer pageSize=2;

	public String userQuery(HttpServletRequest req, HttpServletResponse resp) {
		String loginname=req.getParameter("loginname");
		String username=req.getParameter("username");
		String status=req.getParameter("status");
		UserService us=new UserService();
		JSONObject json=new JSONObject();
		List<User_Inf> userlist=null;
		List<User_Inf> alluserlist=null;
		Integer pageIndex=1;
		try {
			userlist=us.userQuery(loginname,username,status,pageIndex,pageSize);
			alluserlist=us.alluserQuery(loginname, username, status);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(userlist!=null) {
			JSONArray users_inf=new JSONArray();
			for (User_Inf li:userlist) {
				users_inf.add(li);
			}
			json.put("users_inf", users_inf);
			Integer totalPageSum=alluserlist.size()%pageSize==0?alluserlist.size()/pageSize:alluserlist.size()/pageSize+1;
			if(totalPageSum==0) {
				totalPageSum=1;
			}
			UserPageModel pm=new UserPageModel(pageSize,pageIndex,totalPageSum,alluserlist.size(),pageIndex+1,pageIndex-1,loginname,username,status);
			json.put("pagemodel", pm);
		}else {
			json.put("users_inf", null);
		}
		return "data:"+json.toJSONString();
	}
	
	public String userQuery_toPage(HttpServletRequest req, HttpServletResponse resp) {
		String loginname=req.getParameter("loginname");
		String username=req.getParameter("username");
		String status=req.getParameter("status");
		Integer pageSize=Integer.parseInt(req.getParameter("pageSize"));
		Integer pageIndex=Integer.parseInt(req.getParameter("pageIndex"));
		UserService us=new UserService();
		JSONObject json=new JSONObject();
		List<User_Inf> userlist=null;
		List<User_Inf> alluserlist=null;
		try {
			userlist=us.userQuery(loginname,username,status,pageIndex,pageSize);
			alluserlist=us.alluserQuery(loginname, username, status);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(userlist!=null) {
			JSONArray users_inf=new JSONArray();
			for (User_Inf li:userlist) {
				users_inf.add(li);
			}
			json.put("users_inf", users_inf);
			Integer totalPageSum=alluserlist.size()%pageSize==0?alluserlist.size()/pageSize:alluserlist.size()/pageSize+1;
			if(totalPageSum==0) {
				totalPageSum=1;
			}
			UserPageModel pm=new UserPageModel(pageSize,pageIndex,totalPageSum,alluserlist.size(),pageIndex+1,pageIndex-1,loginname,username,status);
			json.put("pagemodel", pm);
		}else {
			json.put("users_inf", null);
		}
		return "data:"+json.toJSONString();
	}
	
	public String userModify(HttpServletRequest req, HttpServletResponse resp) {
		String id=req.getParameter("id");
		UserService us=new UserService();
		User_Inf ui=null;
		try {
			ui=us.getUser_InfById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(ui!=null) {
			req.setAttribute("modify_user_inf",ui);
		}
		return "forward:/usermodify.jsp";
	}
	
	public String doUserModify(HttpServletRequest req, HttpServletResponse resp) {
		String loginname=req.getParameter("loginname");
		String password=req.getParameter("password");
		String status=req.getParameter("status");
		String username=req.getParameter("username");
		HttpSession session = req.getSession();
		User_Inf user=(User_Inf)session.getAttribute("user");
		User_Inf old_ui=(User_Inf) session.getAttribute("old_user_inf");
		boolean modifyuser_result=false;
		try {
			User_Inf ui=new User_Inf(old_ui.getId(),loginname,password,status,old_ui.getCreatedate(),username);
			UserService us=new UserService();
			modifyuser_result=us.userModify(ui);
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/modify"+modifyuser_result+".jsp";
	}
	
	public String userDelete(HttpServletRequest req, HttpServletResponse resp) {
		String id=req.getParameter("id");
		UserService us=new UserService();
		JSONObject json=new JSONObject();
		boolean userdelete_result=false;
		try {
			userdelete_result=us.userDelete(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		json.put("userdelete_result", userdelete_result);
		return "data:"+json.toJSONString();
	}
	
	public String userAdd(HttpServletRequest req, HttpServletResponse resp) {
		String loginname=req.getParameter("loginname");
		String password=req.getParameter("password");
		String status=req.getParameter("status");
		String username=req.getParameter("username");
		UserService us=new UserService();
		boolean useradd_result=false;
		try {
			useradd_result=us.userAdd(new User_Inf(loginname,password,status,username));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "redirect:/add"+useradd_result+".jsp";
	}
}
