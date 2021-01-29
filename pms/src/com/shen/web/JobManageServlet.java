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
import com.shen.domain.JobPageModel;
import com.shen.domain.Job_Inf;
import com.shen.domain.PageModel;
import com.shen.domain.User_Inf;
import com.shen.service.DeptService;
import com.shen.service.JobService;
import com.shen.service.UserService;
@WebServlet("/JobManageServlet/*")
public class JobManageServlet extends BaseController{

	static Integer pageSize=2;

	public String jobQuery(HttpServletRequest req, HttpServletResponse resp) {
		String name=req.getParameter("name");
		JobService js=new JobService();
		JSONObject json=new JSONObject();
		List<Job_Inf> joblist=null;
		List<Job_Inf> alljoblist=null;
		Integer pageIndex=1;
		try {
			joblist=js.jobQuery(name,pageIndex,pageSize);
			alljoblist=js.alljobQuery(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(joblist!=null) {
			JSONArray jobs_inf=new JSONArray();
			for (Job_Inf li:joblist) {
				jobs_inf.add(li);
			}
			json.put("jobs_inf", jobs_inf);
			Integer totalPageSum=alljoblist.size()%pageSize==0?alljoblist.size()/pageSize:alljoblist.size()/pageSize+1;
			if(totalPageSum==0) {
				totalPageSum=1;
			}
			JobPageModel pm=new JobPageModel(pageSize,pageIndex,totalPageSum,alljoblist.size(),pageIndex+1,pageIndex-1,name);
			json.put("pagemodel", pm);
		}else {
			json.put("jobs_inf", null);
		}
		return "data:"+json.toJSONString();
	}
	
	public String jobQuery_toPage(HttpServletRequest req, HttpServletResponse resp) {
		String name=req.getParameter("name");
		Integer pageSize=Integer.parseInt(req.getParameter("pageSize"));
		Integer pageIndex=Integer.parseInt(req.getParameter("pageIndex"));
		JobService js=new JobService();
		JSONObject json=new JSONObject();
		List<Job_Inf> joblist=null;
		List<Job_Inf> alljoblist=null;
		try {
			joblist=js.jobQuery(name,pageIndex,pageSize);
			alljoblist=js.alljobQuery(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(joblist!=null) {
			JSONArray jobs_inf=new JSONArray();
			for (Job_Inf li:joblist) {
				jobs_inf.add(li);
			}
			json.put("jobs_inf", jobs_inf);
			Integer totalPageSum=alljoblist.size()%pageSize==0?alljoblist.size()/pageSize:alljoblist.size()/pageSize+1;
			if(totalPageSum==0) {
				totalPageSum=1;
			}
			JobPageModel pm=new JobPageModel(pageSize,pageIndex,totalPageSum,alljoblist.size(),pageIndex+1,pageIndex-1,name);
			json.put("pagemodel", pm);
		}else {
			json.put("jobs_inf", null);
		}
		return "data:"+json.toJSONString();
	}
	
	public String jobModify(HttpServletRequest req, HttpServletResponse resp) {
		String id=req.getParameter("id");
		JobService js=new JobService();
		Job_Inf ji=null;
		try {
			ji=js.getJob_InfById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(ji!=null) {
			req.setAttribute("modify_job_inf",ji);
		}
		return "forward:/jobmodify.jsp";
	}
	
	public String doJobModify(HttpServletRequest req, HttpServletResponse resp) {
		String name=req.getParameter("name");
		String remark=req.getParameter("remark");
		String state=req.getParameter("state");
		HttpSession session = req.getSession();
		User_Inf user=(User_Inf)session.getAttribute("user");
		Job_Inf old_ji=(Job_Inf) session.getAttribute("old_job_inf");
		boolean modifyjob_result=false;
		try {
			Job_Inf ji=new Job_Inf(old_ji.getId(),name,remark,state);
			JobService js=new JobService();
			modifyjob_result=js.jobModify(ji);
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/modify"+modifyjob_result+".jsp";
	}
	
	public String jobDelete(HttpServletRequest req, HttpServletResponse resp) {
		String id=req.getParameter("id");
		JobService js=new JobService();
		JSONObject json=new JSONObject();
		boolean jobdelete_result=false;
		try {
			jobdelete_result=js.jobDelete(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		json.put("jobdelete_result", jobdelete_result);
		return "data:"+json.toJSONString();
	}
	
	public String jobAdd(HttpServletRequest req, HttpServletResponse resp) {
		String name=req.getParameter("name");
		String remark=req.getParameter("remark");
		JobService js=new JobService();
		boolean jobadd_result=false;
		try {
			jobadd_result=js.jobAdd(new Job_Inf(name,remark));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "redirect:/add"+jobadd_result+".jsp";
	}
}