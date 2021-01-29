package com.shen.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shen.controller.BaseController;
import com.shen.domain.DocumentPageModel;
import com.shen.domain.Document_Inf;
import com.shen.domain.PageModel;
import com.shen.domain.User_Inf;
import com.shen.service.FileService;
import com.shen.service.UserService;
import com.shen.utils.IOUtil;

@WebServlet("/UploadFileServlet/*")
@MultipartConfig
public class UploadFileServlet extends BaseController{

	public String uploadFile(HttpServletRequest req, HttpServletResponse resp) {
		Part part=null;
		String filetitle=req.getParameter("filetitle");
		String remark=req.getParameter("filedescribe");
		HttpSession session = req.getSession();
		User_Inf user=(User_Inf)session.getAttribute("user");
		boolean uploadFile_result=false;
		try {
			part = req.getPart("filename");
			String filename=part.getSubmittedFileName();
			if(!"".equals(filename)&&filename!=null) {
				String realPath=req.getServletContext().getRealPath("/upload");
				part.write(realPath+File.separator+filename);
				Document_Inf di=new Document_Inf(filetitle,filename,part.getContentType(),remark,user.getId());
				FileService fs=new FileService();
				uploadFile_result=fs.uploadFile(realPath,di);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "redirect:/upload"+uploadFile_result+".jsp";
	}
	
	static Integer pageSize=2;

	public String fileQuery(HttpServletRequest req, HttpServletResponse resp) {
		String title=req.getParameter("title");
		FileService fs=new FileService();
		JSONObject json=new JSONObject();
		List<Document_Inf> filelist=null;
		List<Document_Inf> allfilelist=null;
		Integer pageIndex=1;
		try {
			filelist=fs.fileQuery(title,pageIndex,pageSize);
			allfilelist=fs.allfileQuery(title);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(filelist!=null) {
			JSONArray documents_inf=new JSONArray();
			for (Document_Inf li:filelist) {
				documents_inf.add(li);
			}
			json.put("documents_inf", documents_inf);
			Integer totalPageSum=allfilelist.size()%pageSize==0?allfilelist.size()/pageSize:allfilelist.size()/pageSize+1;
			if(totalPageSum==0) {
				totalPageSum=1;
			}
			DocumentPageModel pm=new DocumentPageModel(pageSize,pageIndex,totalPageSum,allfilelist.size(),pageIndex+1,pageIndex-1,title);
			json.put("pagemodel", pm);
		}else {
			json.put("documents_inf", null);
		}
		return "data:"+json.toJSONString();
	}
	
	public String fileQuery_toPage(HttpServletRequest req, HttpServletResponse resp) {
		String title=req.getParameter("title");
		Integer pageSize=Integer.parseInt(req.getParameter("pageSize"));
		Integer pageIndex=Integer.parseInt(req.getParameter("pageIndex"));
		FileService fs=new FileService();
		JSONObject json=new JSONObject();
		List<Document_Inf> filelist=null;
		List<Document_Inf> allfilelist=null;
		try {
			filelist=fs.fileQuery(title,pageIndex,pageSize);
			allfilelist=fs.allfileQuery(title);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(filelist!=null) {
			JSONArray documents_inf=new JSONArray();
			for (Document_Inf li:filelist) {
				documents_inf.add(li);
			}
			json.put("documents_inf", documents_inf);
			Integer totalPageSum=allfilelist.size()%pageSize==0?allfilelist.size()/pageSize:allfilelist.size()/pageSize+1;
			if(totalPageSum==0) {
				totalPageSum=1;
			}
			DocumentPageModel pm=new DocumentPageModel(pageSize,pageIndex,totalPageSum,allfilelist.size(),pageIndex+1,pageIndex-1,title);
			json.put("pagemodel", pm);
		}else {
			json.put("documents_inf", null);
		}
		return "data:"+json.toJSONString();
	}
	
	public String fileDelete(HttpServletRequest req, HttpServletResponse resp) {
		String id=req.getParameter("id");
		System.out.println("id:"+id);
		FileService fs=new FileService();
		JSONObject json=new JSONObject();
		boolean filedelete_result=false;
		try {
			filedelete_result=fs.fileDelete(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		json.put("filedelete_result", filedelete_result);
		return "data:"+json.toJSONString();
	}
	
	public String fileModify(HttpServletRequest req, HttpServletResponse resp) {
		String id=req.getParameter("id");
		FileService fs=new FileService();
		Document_Inf di=null;
		try {
			di=fs.getDocument_InfById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(di!=null) {
			req.setAttribute("modify_document_inf",di);
		}
		return "forward:/filemodify.jsp";
	}
	
	public String doFileModify(HttpServletRequest req, HttpServletResponse resp) {
		String remark=req.getParameter("filedescribe");
		System.out.println("filedescribe:"+remark+"|");
		HttpSession session = req.getSession();
		User_Inf user=(User_Inf)session.getAttribute("user");
		Document_Inf old_di=(Document_Inf) session.getAttribute("old_Document_Inf");
		boolean modifyFile_result=false;
		try {
				String realPath=req.getServletContext().getRealPath("/upload");
				Document_Inf di=new Document_Inf(old_di.getId(),old_di.getTitle(),old_di.getFilename(),old_di.getFiletype(),remark,old_di.getCreate_date(),user.getId());
				FileService fs=new FileService();
				modifyFile_result=fs.fileModify(realPath,di);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "redirect:/modify"+modifyFile_result+".jsp";
	}
}
