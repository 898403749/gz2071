package com.shen.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shen.utils.IOUtil;

@WebServlet("/DownloadFileServlet")
public class DownloadFileServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String filename=req.getParameter("filename");
			resp.setHeader("content-disposition", "attachment;filename="+getStr(req,filename));
			String realPath=this.getServletContext().getRealPath("/upload");
			try(OutputStream os=resp.getOutputStream();){
				IOUtil iou=new IOUtil();
				byte[] b=iou.input(realPath+File.separator+filename);
				os.write(b);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	private String getStr(HttpServletRequest req, String filename) {
		String browerName=null;
		String client=req.getHeader("User-agent");
		if(client!=null&&client.indexOf("MSIE")>0) {
			try {
				browerName=URLEncoder.encode(filename,"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}else {
			try {
				browerName=new String(filename.getBytes("utf-8"),"iso-8859-1");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return browerName;
	}
}
