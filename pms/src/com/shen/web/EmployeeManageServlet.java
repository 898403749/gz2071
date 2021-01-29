package com.shen.web;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shen.controller.BaseController;
import com.shen.domain.EmployeePageModel;
import com.shen.domain.Employee_Inf;
import com.shen.domain.JobPageModel;
import com.shen.domain.Job_Inf;
import com.shen.domain.User_Inf;
import com.shen.service.EmployeeService;
import com.shen.service.JobService;

@WebServlet("/EmployeeManageServlet/*")
public class EmployeeManageServlet extends BaseController {

	static Integer pageSize=2;
	
	public String employeeQueryInit(HttpServletRequest req, HttpServletResponse resp) {
		JSONObject json=new JSONObject();
		EmployeeService es=new EmployeeService();
		Map<String,String> deptnamemap=null;
		Map<String,String> jobnamemap=null;
		try {
			deptnamemap=es.getAllDeptName();
			jobnamemap=es.getAllJobName();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		json.put("deptnamemap", deptnamemap);
		json.put("jobnamemap", jobnamemap);
		return "data:"+json.toJSONString();
	}
	
	public String employeeQuery(HttpServletRequest req, HttpServletResponse resp) {
		String job_id=req.getParameter("job");
		String name=req.getParameter("name");
		String card_id=req.getParameter("card_id");
		String sex=req.getParameter("sex");
		String phone=req.getParameter("phone");
		String dept_id=req.getParameter("dept");
		EmployeeService es=new EmployeeService();
		JSONObject json=new JSONObject();
		List<Employee_Inf> employeelist=null;
		List<Employee_Inf> allemployeelist=null;
		Integer pageIndex=1;
		try {
			employeelist=es.employeeQuery(job_id,name,card_id,sex,phone,dept_id,pageIndex,pageSize);
			allemployeelist=es.allemployeeQuery(job_id,name,card_id,sex,phone,dept_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(employeelist!=null) {
			JSONArray employees_inf=new JSONArray();
			for (Employee_Inf li:employeelist) {
				employees_inf.add(li);
			}
			json.put("employees_inf", employees_inf);
			Integer totalPageSum=allemployeelist.size()%pageSize==0?allemployeelist.size()/pageSize:allemployeelist.size()/pageSize+1;
			if(totalPageSum==0) {
				totalPageSum=1;
			}
			EmployeePageModel pm=new EmployeePageModel(pageSize,pageIndex,totalPageSum,allemployeelist.size(),pageIndex+1,pageIndex-1,name,card_id,phone,sex,dept_id,job_id);
			json.put("pagemodel", pm);
		}else {
			json.put("employees_inf", null);
		}
		return "data:"+json.toJSONString();
	}
	
	public String employeeQuery_toPage(HttpServletRequest req, HttpServletResponse resp) {
		String job_id=req.getParameter("job");
		String name=req.getParameter("name");
		String card_id=req.getParameter("card_id");
		String sex=req.getParameter("sex");
		String phone=req.getParameter("phone");
		String dept_id=req.getParameter("dept");
		Integer pageSize=Integer.parseInt(req.getParameter("pageSize"));
		Integer pageIndex=Integer.parseInt(req.getParameter("pageIndex"));
		EmployeeService es=new EmployeeService();
		JSONObject json=new JSONObject();
		List<Employee_Inf> employeelist=null;
		List<Employee_Inf> allemployeelist=null;
		try {
			employeelist=es.employeeQuery(job_id,name,card_id,sex,phone,dept_id,pageIndex,pageSize);
			allemployeelist=es.allemployeeQuery(job_id,name,card_id,sex,phone,dept_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(employeelist!=null) {
			JSONArray employees_inf=new JSONArray();
			for (Employee_Inf li:employeelist) {
				employees_inf.add(li);
			}
			json.put("employees_inf", employees_inf);
			Integer totalPageSum=allemployeelist.size()%pageSize==0?allemployeelist.size()/pageSize:allemployeelist.size()/pageSize+1;
			if(totalPageSum==0) {
				totalPageSum=1;
			}
			EmployeePageModel pm=new EmployeePageModel(pageSize,pageIndex,totalPageSum,allemployeelist.size(),pageIndex+1,pageIndex-1,name,card_id,phone,sex,dept_id,job_id);
			json.put("pagemodel", pm);
		}else {
			json.put("employees_inf", null);
		}
		return "data:"+json.toJSONString();
	}
	
	public String employeeAdd(HttpServletRequest req, HttpServletResponse resp) {
		String name=req.getParameter("name");
		String card_id=req.getParameter("card_id");
		String sex=req.getParameter("sex");
		String job_id=req.getParameter("job");
		String education=req.getParameter("education");
		String email=req.getParameter("email");
		String phone=req.getParameter("phone");
		String tel=req.getParameter("tel");
		String party=req.getParameter("party");
		String qq_num=req.getParameter("qq_num");
		String address=req.getParameter("address");
		String post_code=req.getParameter("post_code");
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		Timestamp birthday=null;
		boolean employeeadd_result=false;
		if(checkdate(req.getParameter("birthday"))) {
			try {
				birthday = new Timestamp(sf.parse(req.getParameter("birthday")).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else {
			return "redirect:/add"+employeeadd_result+".jsp";
		}
		String race=req.getParameter("race");
		String speciality=req.getParameter("speciality");
		String hobby=req.getParameter("hobby");
		String remark=req.getParameter("remark");
		String dept_id=req.getParameter("dept");
		EmployeeService es=new EmployeeService();
		try {
			employeeadd_result=es.employeeAdd(new Employee_Inf(name,card_id,address,post_code,tel,phone,qq_num,email,sex,party,birthday,race,education,speciality,hobby,remark,dept_id,job_id));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "redirect:/add"+employeeadd_result+".jsp";
	}
	
	private boolean checkdate(String date) {
		return date.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}");
	}
	
	public String employeeDelete(HttpServletRequest req, HttpServletResponse resp) {
		String id=req.getParameter("id");
		EmployeeService es=new EmployeeService();
		JSONObject json=new JSONObject();
		boolean employeedelete_result=false;
		try {
			employeedelete_result=es.employeeDelete(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		json.put("employeedelete_result", employeedelete_result);
		return "data:"+json.toJSONString();
	}
	
	public String employeeModify(HttpServletRequest req, HttpServletResponse resp) {
		String id=req.getParameter("id");
		EmployeeService es=new EmployeeService();
		Employee_Inf ei=null;
		try {
			ei=es.getEmployee_InfById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(ei!=null) {
			req.setAttribute("modify_employee_inf",ei);
		}
		return "forward:/employeemodify.jsp";
	}
	
	public String doEmployeeModify(HttpServletRequest req, HttpServletResponse resp) {
		String name=req.getParameter("name");
		String card_id=req.getParameter("card_id");
		String sex=req.getParameter("sex");
		String job_id=req.getParameter("job");
		String education=req.getParameter("education");
		String email=req.getParameter("email");
		String phone=req.getParameter("phone");
		String tel=req.getParameter("tel");
		String party=req.getParameter("party");
		String qq_num=req.getParameter("qq_num");
		String address=req.getParameter("address");
		String post_code=req.getParameter("post_code");
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		Timestamp birthday=null;
		boolean modifyemployee_result=false;
		if(checkdate(req.getParameter("birthday"))) {
			try {
				birthday = new Timestamp(sf.parse(req.getParameter("birthday")).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else {
			return "redirect:/modify"+modifyemployee_result+".jsp";
		}
		String race=req.getParameter("race");
		String speciality=req.getParameter("speciality");
		String hobby=req.getParameter("hobby");
		String remark=req.getParameter("remark");
		String dept_id=req.getParameter("dept");
		HttpSession session = req.getSession();
		User_Inf user=(User_Inf)session.getAttribute("user");
		Employee_Inf old_ei=(Employee_Inf) session.getAttribute("old_employee_inf");
		try {
			Employee_Inf ei=new Employee_Inf(old_ei.getId(),name,card_id,address,post_code,tel,phone,qq_num,email,sex,party,birthday,race,education,speciality,hobby,remark,old_ei.getCreate_date(),old_ei.getState(),dept_id,job_id);
			EmployeeService es=new EmployeeService();
			modifyemployee_result=es.employeeModify(ei);
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/modify"+modifyemployee_result+".jsp";
	}
}
