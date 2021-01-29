package com.shen.web;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shen.controller.BaseController;
import com.shen.domain.DeptPageModel;
import com.shen.domain.Dept_Inf;
import com.shen.domain.PageModel;
import com.shen.domain.User_Inf;
import com.shen.service.DeptService;
import com.shen.service.UserService;
@WebServlet("/DeptManageServlet/*")
public class DeptManageServlet extends BaseController{

	static Integer pageSize=2;

	public String deptQuery(HttpServletRequest req, HttpServletResponse resp) {
		String name=req.getParameter("name");
		DeptService ds=new DeptService();
		JSONObject json=new JSONObject();
		List<Dept_Inf> deptlist=null;
		List<Dept_Inf> alldeptlist=null;
		Integer pageIndex=1;
		try {
			deptlist=ds.deptQuery(name,pageIndex,pageSize);
			alldeptlist=ds.alldeptQuery(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(deptlist!=null) {
			JSONArray depts_inf=new JSONArray();
			for (Dept_Inf li:deptlist) {
				depts_inf.add(li);
			}
			json.put("depts_inf", depts_inf);
			Integer totalPageSum=alldeptlist.size()%pageSize==0?alldeptlist.size()/pageSize:alldeptlist.size()/pageSize+1;
			if(totalPageSum==0) {
				totalPageSum=1;
			}
			DeptPageModel pm=new DeptPageModel(pageSize,pageIndex,totalPageSum,alldeptlist.size(),pageIndex+1,pageIndex-1,name);
			json.put("pagemodel", pm);
		}else {
			json.put("depts_inf", null);
		}
		return "data:"+json.toJSONString();
	}
	
	public String deptQuery_toPage(HttpServletRequest req, HttpServletResponse resp) {
		String name=req.getParameter("name");
		Integer pageSize=Integer.parseInt(req.getParameter("pageSize"));
		Integer pageIndex=Integer.parseInt(req.getParameter("pageIndex"));
		DeptService ds=new DeptService();
		JSONObject json=new JSONObject();
		List<Dept_Inf> deptlist=null;
		List<Dept_Inf> alldeptlist=null;
		try {
			deptlist=ds.deptQuery(name,pageIndex,pageSize);
			alldeptlist=ds.alldeptQuery(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(deptlist!=null) {
			JSONArray depts_inf=new JSONArray();
			for (Dept_Inf li:deptlist) {
				depts_inf.add(li);
			}
			json.put("depts_inf", depts_inf);
			Integer totalPageSum=alldeptlist.size()%pageSize==0?alldeptlist.size()/pageSize:alldeptlist.size()/pageSize+1;
			if(totalPageSum==0) {
				totalPageSum=1;
			}
			DeptPageModel pm=new DeptPageModel(pageSize,pageIndex,totalPageSum,alldeptlist.size(),pageIndex+1,pageIndex-1,name);
			json.put("pagemodel", pm);
		}else {
			json.put("depts_inf", null);
		}
		return "data:"+json.toJSONString();
	}
	
	public String deptModify(HttpServletRequest req, HttpServletResponse resp) {
		String id=req.getParameter("id");
		DeptService ds=new DeptService();
		Dept_Inf di=null;
		try {
			di=ds.getDept_InfById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(di!=null) {
			req.setAttribute("modify_dept_inf",di);
		}
		return "forward:/deptmodify.jsp";
	}
	
	public String doDeptModify(HttpServletRequest req, HttpServletResponse resp) {
		String name=req.getParameter("name");
		String remark=req.getParameter("remark");
		String state=req.getParameter("state");
		HttpSession session = req.getSession();
		User_Inf user=(User_Inf)session.getAttribute("user");
		Dept_Inf old_di=(Dept_Inf) session.getAttribute("old_dept_inf");
		boolean modifydept_result=false;
		try {
			Dept_Inf di=new Dept_Inf(old_di.getId(),name,remark,state);
			DeptService ds=new DeptService();
			modifydept_result=ds.deptModify(di);
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/modify"+modifydept_result+".jsp";
	}
	
	public String deptDelete(HttpServletRequest req, HttpServletResponse resp) {
		String id=req.getParameter("id");
		DeptService ds=new DeptService();
		JSONObject json=new JSONObject();
		boolean deptdelete_result=false;
		try {
			deptdelete_result=ds.deptDelete(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		json.put("deptdelete_result", deptdelete_result);
		return "data:"+json.toJSONString();
	}
	
	public String deptAdd(HttpServletRequest req, HttpServletResponse resp) {
		String name=req.getParameter("name");
		String remark=req.getParameter("remark");
		DeptService ds=new DeptService();
		boolean deptadd_result=false;
		try {
			deptadd_result=ds.deptAdd(new Dept_Inf(name,remark));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "redirect:/add"+deptadd_result+".jsp";
	}
}
