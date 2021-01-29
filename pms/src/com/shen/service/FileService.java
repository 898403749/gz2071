package com.shen.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.shen.dao.DocumentDao;
import com.shen.dao.UserDao;
import com.shen.domain.Document_Inf;
import com.shen.domain.Employee_Inf;
import com.shen.domain.User_Inf;
import com.shen.utils.JdbcUtil;

public class FileService {

	public boolean uploadFile(String realPath,Document_Inf di) throws SQLException, IOException {
		JdbcUtil.getConnection();
		DocumentDao dd=new DocumentDao();
		JdbcUtil.closeConnection();
		return dd.addDocument_Inf(realPath,di);
	}
	
	public List<Document_Inf> fileQuery(String title,Integer pageIndex,Integer pageSize) throws SQLException {
		JdbcUtil.getConnection();
		DocumentDao dd=new DocumentDao();
		UserDao ad=new UserDao();
		List<Document_Inf> filelist=dd.showPage_fileQueryDocument_Inf(title, pageIndex, pageSize);
		for(Document_Inf li:filelist) {
			if(li.getUser_id()!=null&&!"".equals(li.getUser_id())) {
				li.setUser_id(ad.getUser_InfById(li.getUser_id()).getLoginname());
			}
		}
		JdbcUtil.closeConnection();
		return filelist;
	}
	
	public List<Document_Inf> allfileQuery(String title) throws SQLException {
		JdbcUtil.getConnection();
		DocumentDao dd=new DocumentDao();
		List<Document_Inf> filelist=dd.fileQueryDocumnet_Inf(title);
		JdbcUtil.closeConnection();
		return filelist;
	}
	
	public boolean fileDelete(String id) throws SQLException {
		JdbcUtil.getConnection();
		DocumentDao dd=new DocumentDao();
		JdbcUtil.closeConnection();
		return dd.fileDelete(id);
	}
	
	public Document_Inf getDocument_InfById(String id) throws SQLException {
		JdbcUtil.getConnection();
		DocumentDao dd=new DocumentDao();
		JdbcUtil.closeConnection();
		return dd.getDocument_InfById(id);
	}
	
	public boolean fileModify(String realPath,Document_Inf di) throws SQLException, IOException {
		JdbcUtil.getConnection();
		DocumentDao dd=new DocumentDao();
		JdbcUtil.closeConnection();
		return dd.modifyDocument_Inf(realPath,di);
	}
}
