package com.shen.dao;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shen.domain.Document_Inf;
import com.shen.domain.User_Inf;
import com.shen.utils.IOUtil;
import com.shen.utils.JdbcUtil;

public class DocumentDao {                   //提供对doucment_inf表的基本操作

	public boolean addDocument_Inf(String realPath,Document_Inf di) throws SQLException, IOException {
		if(di.getTitle()!=null&&!"".equals(di.getTitle())) {
			Connection conn = JdbcUtil.getConnection();
			IOUtil it=new IOUtil();
			byte[] filebytes=it.input(realPath+File.separator+di.getFilename());
			String sql = "insert into document_inf(TITLE,filename,filetype,filebytes,REMARK,USER_ID) values(?,?,?,?,?,?);";
			PreparedStatement psmt=conn.prepareStatement(sql);
			psmt.setString(1,di.getTitle());
			psmt.setString(2,di.getFilename());
			psmt.setString(3,di.getFiletype());
			psmt.setBytes(4,filebytes);
			psmt.setString(5,di.getRemark());
			psmt.setString(6,di.getUser_id());
			int count =psmt.executeUpdate();
			if(count==1) {
				JdbcUtil.closeStatement(psmt);
				return true;
			}else {
				JdbcUtil.closeStatement(psmt);
				return false;
			}
		}else {
			return false;
		}
	}
	
	public List<Document_Inf> fileQueryDocumnet_Inf(String title) throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		List<Document_Inf> filelist=new ArrayList<>();
		String sql = "select *from document_inf where title like ?;";
		PreparedStatement psmt = null;
		psmt = conn.prepareStatement(sql);
		psmt.setString(1,"%"+title.replace(" ", "")+"%");
		ResultSet rs = psmt.executeQuery();
		while (rs.next()) {
			Document_Inf file=new Document_Inf(rs.getString("ID"),
					rs.getString("TITLE"),
					rs.getString("filename"),
					rs.getString("filetype"),
					rs.getString("REMARK"),
					rs.getString("CREATE_DATE"),
					rs.getString("USER_ID"));
			filelist.add(file);
		}
		JdbcUtil.closeResultSet(rs);
		JdbcUtil.closeStatement(psmt);
		return filelist;
	}
	
	public List<Document_Inf> showPage_fileQueryDocument_Inf(String title,Integer pageIndex,Integer pageSize) throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		List<Document_Inf> filelist=new ArrayList<>();
		Integer startnumber=(pageIndex-1)*pageSize;
		String sql = "select *from document_inf where title like ? limit ?,?;";
		PreparedStatement psmt = null;
		psmt = conn.prepareStatement(sql);
		psmt.setString(1,"%"+title.replace(" ", "")+"%");
		psmt.setInt(2,startnumber);
		psmt.setInt(3,pageSize);
		ResultSet rs = psmt.executeQuery();
		while (rs.next()) {
			Document_Inf file=new Document_Inf(rs.getString("ID"),
					rs.getString("TITLE"),
					rs.getString("filename"),
					rs.getString("filetype"),
					rs.getString("REMARK"),
					rs.getString("CREATE_DATE"),
					rs.getString("USER_ID"));
			filelist.add(file);
		}
		JdbcUtil.closeResultSet(rs);
		JdbcUtil.closeStatement(psmt);
		return filelist;
	}
	
	public boolean fileDelete(String id) throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		String sql = "delete from document_inf where id=?;";
		PreparedStatement psmt = null;
		psmt = conn.prepareStatement(sql);
		psmt.setString(1,id);
		int count = psmt.executeUpdate();
		if(count==1) {
			JdbcUtil.closeStatement(psmt);
			return true;
		}else {
			JdbcUtil.closeStatement(psmt);
			return false;
		}
	}
	
	public Document_Inf getDocument_InfById(String id) throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		String sql = "select *from document_inf where ID=?;";
		PreparedStatement psmt = null;
		psmt = conn.prepareStatement(sql);
		psmt.setString(1,id);
		ResultSet rs = psmt.executeQuery();
		if (rs.next()) {
			Document_Inf file=new Document_Inf(rs.getString("ID"),
					rs.getString("TITLE"),
					rs.getString("filename"),
					rs.getString("filetype"),
					rs.getString("REMARK"),
					rs.getString("CREATE_DATE"),
					rs.getString("USER_ID"));
			JdbcUtil.closeResultSet(rs);
			JdbcUtil.closeStatement(psmt);
			return file;
		} else {
			JdbcUtil.closeResultSet(rs);
			JdbcUtil.closeStatement(psmt);
			return null;
		}
	}
	
	public boolean modifyDocument_Inf(String realPath,Document_Inf di) throws SQLException, IOException {
		Connection conn = JdbcUtil.getConnection();
		String sql = "update document_inf set REMARK=? where ID=?;";
		PreparedStatement psmt=conn.prepareStatement(sql);
		psmt.setString(1,di.getRemark());
		psmt.setString(2,di.getId());
		int count =psmt.executeUpdate();
		if(count==1) {
			JdbcUtil.closeStatement(psmt);
			return true;
		}else {
			JdbcUtil.closeStatement(psmt);
			return false;
		}
	}
}
