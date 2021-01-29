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
import com.shen.domain.NoticePageModel;
import com.shen.domain.Notice_Inf;
import com.shen.domain.User_Inf;
import com.shen.service.EmployeeService;
import com.shen.service.NoticeService;

@WebServlet("/NoticeManageServlet/*")
public class NoticeManageServlet extends BaseController{

	static Integer pageSize=2;
	
	public String noticeQueryInit(HttpServletRequest req, HttpServletResponse resp) {
		JSONObject json=new JSONObject();
		NoticeService ns=new NoticeService();
		Map<String,String> typenamemap=null;
		try {
			typenamemap=ns.getAllTypeName();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		json.put("typenamemap", typenamemap);
		return "data:"+json.toJSONString();
	}
	
	public String noticeQuery(HttpServletRequest req, HttpServletResponse resp) {
		String name=req.getParameter("name");
		String type=req.getParameter("type");
		NoticeService ns=new NoticeService();
		JSONObject json=new JSONObject();
		List<Notice_Inf> noticelist=null;
		List<Notice_Inf> allnoticelist=null;
		Integer pageIndex=1;
		try {
			noticelist=ns.noticeQuery(name,type,pageIndex,pageSize);
			allnoticelist=ns.allnoticeQuery(name,type);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(noticelist!=null) {
			JSONArray notices_inf=new JSONArray();
			for (Notice_Inf li:noticelist) {
				notices_inf.add(li);
			}
			json.put("notices_inf", notices_inf);
			Integer totalPageSum=allnoticelist.size()%pageSize==0?allnoticelist.size()/pageSize:allnoticelist.size()/pageSize+1;
			if(totalPageSum==0) {
				totalPageSum=1;
			}
			NoticePageModel pm=new NoticePageModel(pageSize,pageIndex,totalPageSum,allnoticelist.size(),pageIndex+1,pageIndex-1,name,type);
			json.put("pagemodel", pm);
		}else {
			json.put("notices_inf", null);
		}
		return "data:"+json.toJSONString();
	}
	
	public String noticeQuery_toPage(HttpServletRequest req, HttpServletResponse resp) {
		String name=req.getParameter("name");
		String type=req.getParameter("type");
		Integer pageSize=Integer.parseInt(req.getParameter("pageSize"));
		Integer pageIndex=Integer.parseInt(req.getParameter("pageIndex"));
		NoticeService ns=new NoticeService();
		JSONObject json=new JSONObject();
		List<Notice_Inf> noticelist=null;
		List<Notice_Inf> allnoticelist=null;
		try {
			noticelist=ns.noticeQuery(name,type,pageIndex,pageSize);
			allnoticelist=ns.allnoticeQuery(name,type);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(noticelist!=null) {
			JSONArray notices_inf=new JSONArray();
			for (Notice_Inf li:noticelist) {
				notices_inf.add(li);
			}
			json.put("notices_inf", notices_inf);
			Integer totalPageSum=allnoticelist.size()%pageSize==0?allnoticelist.size()/pageSize:allnoticelist.size()/pageSize+1;
			if(totalPageSum==0) {
				totalPageSum=1;
			}
			NoticePageModel pm=new NoticePageModel(pageSize,pageIndex,totalPageSum,allnoticelist.size(),pageIndex+1,pageIndex-1,name,type);
			json.put("pagemodel", pm);
		}else {
			json.put("notices_inf", null);
		}
		return "data:"+json.toJSONString();
	}
	
	public String noticeAdd(HttpServletRequest req, HttpServletResponse resp) {
		String name=req.getParameter("name");
		String type_id=req.getParameter("type");
		String content=req.getParameter("content");
		HttpSession session = req.getSession();
		User_Inf user=(User_Inf)session.getAttribute("user");
		NoticeService ns=new NoticeService();
		boolean noticeadd_result=false;
		try {
			noticeadd_result=ns.noticeAdd(new Notice_Inf(name,type_id,content,user.getId()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "redirect:/add"+noticeadd_result+".jsp";
	}
	
	public String noticeDelete(HttpServletRequest req, HttpServletResponse resp) {
		String id=req.getParameter("id");
		NoticeService ns=new NoticeService();
		JSONObject json=new JSONObject();
		boolean noticedelete_result=false;
		try {
			noticedelete_result=ns.noticeDelete(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		json.put("noticedelete_result", noticedelete_result);
		return "data:"+json.toJSONString();
	}
	
	public String noticeModify(HttpServletRequest req, HttpServletResponse resp) {
		String id=req.getParameter("id");
		NoticeService ns=new NoticeService();
		Notice_Inf ni=null;
		try {
			ni=ns.getNotice_InfById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(ni!=null) {
			req.setAttribute("modify_notice_inf",ni);
		}
		return "forward:/noticemodify.jsp";
	}
	
	public String doNoticeModify(HttpServletRequest req, HttpServletResponse resp) {
		String name=req.getParameter("name");
		String type_id=req.getParameter("type");
		String content=req.getParameter("content");
		HttpSession session = req.getSession();
		User_Inf user=(User_Inf)session.getAttribute("user");
		Notice_Inf old_ni=(Notice_Inf) session.getAttribute("old_notice_inf");
		boolean modifynotice_result=false;
		try {
			Notice_Inf ni=new Notice_Inf(old_ni.getId(),name,old_ni.getCreate_date(),type_id,content,old_ni.getUser_id(),old_ni.getModify_date());
			NoticeService ns=new NoticeService();
			modifynotice_result=ns.noticeModify(ni);
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/modify"+modifynotice_result+".jsp";
	}
	
	public String noticeWatch(HttpServletRequest req, HttpServletResponse resp) {
		String id=req.getParameter("id");
		NoticeService ns=new NoticeService();
		Notice_Inf ni=null;
		try {
			ni=ns.getNotice_InfById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(ni!=null) {
			ni.setContent("<p>"+ni.getContent().replace("\r\n", "</p><p>")+"</p>");
			req.setAttribute("watch_notice_inf",ni);
		}
		return "forward:/noticewatch.jsp";
	}
}
