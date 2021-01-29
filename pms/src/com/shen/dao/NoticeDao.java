package com.shen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shen.domain.Employee_Inf;
import com.shen.domain.Notice_Inf;
import com.shen.utils.JdbcUtil;

public class NoticeDao {

	public List<Notice_Inf> noticeQueryNotice_Inf(String name,String type_id) throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		List<Notice_Inf> noticelist=new ArrayList<>();
		String sql = "select *from notice_inf where name like ?";
		if(type_id!=null&&!"".equals(type_id)) {
			sql=sql+" and type_id like ?";
		}
		sql=sql+";";
		PreparedStatement psmt = null;
		psmt = conn.prepareStatement(sql);
		psmt.setString(1,"%"+name+"%");
		int num=0;
		if(type_id!=null&&!"".equals(type_id)) {
			num++;
			psmt.setString(1+num,"%"+type_id+"%");
		}
		ResultSet rs = psmt.executeQuery();
		while (rs.next()) {
			Notice_Inf notice=new Notice_Inf(rs.getString("id"),
					rs.getString("name"),
					rs.getTimestamp("create_date"),
					rs.getString("type_id"),
					rs.getString("content"),
					rs.getString("user_id"),
					rs.getTimestamp("modify_date"));
			noticelist.add(notice);
		}
		JdbcUtil.closeResultSet(rs);
		JdbcUtil.closeStatement(psmt);
		return noticelist;
	}
	
	public List<Notice_Inf> showPage_noticeQueryNotice_Inf(String name,String type_id,Integer pageIndex,Integer pageSize) throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		List<Notice_Inf> noticelist=new ArrayList<>();
		Integer startnumber=(pageIndex-1)*pageSize;
		String sql = "select *from notice_inf where name like ?";
		if(type_id!=null&&!"".equals(type_id)) {
			sql=sql+" and type_id like ?";
		}
		sql=sql+" limit ?,?;";
		PreparedStatement psmt = null;
		psmt = conn.prepareStatement(sql);
		psmt.setString(1,"%"+name+"%");
		int num=0;
		if(type_id!=null&&!"".equals(type_id)) {
			num++;
			psmt.setString(1+num,"%"+type_id+"%");
		}
		psmt.setInt(2+num,startnumber);
		psmt.setInt(3+num,pageSize);
		ResultSet rs = psmt.executeQuery();
		while (rs.next()) {
			Notice_Inf notice=new Notice_Inf(rs.getString("id"),
					rs.getString("name"),
					rs.getTimestamp("create_date"),
					rs.getString("type_id"),
					rs.getString("content"),
					rs.getString("user_id"),
					rs.getTimestamp("modify_date"));
			noticelist.add(notice);
		}
		JdbcUtil.closeResultSet(rs);
		JdbcUtil.closeStatement(psmt);
		return noticelist;
	}
	
	public boolean noticeAdd(Notice_Inf ni,String create_date,String modify_date) throws SQLException {
		if(ni.getName()!=null&&!"".equals(ni.getName())&&ni.getType_id()!=null&&!"".equals(ni.getType_id())) {
			Connection conn = JdbcUtil.getConnection();
			String sql = "insert into notice_inf(name,type_id,content,user_id,create_date,modify_date) values(?,?,?,?,?,?);";
			PreparedStatement psmt = null;
			psmt = conn.prepareStatement(sql);
			psmt.setString(1,ni.getName());
			psmt.setString(2,ni.getType_id());
			psmt.setString(3,ni.getContent());
			psmt.setString(4,ni.getUser_id());
			psmt.setString(5,create_date);
			psmt.setString(6,modify_date);
			int count = psmt.executeUpdate();
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
	
	public boolean noticeDelete(String id) throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		String sql = "delete from notice_inf where id=?;";
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
	
	public Notice_Inf getNotice_InfById(String id) throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		String sql = "select *from notice_inf where id=?;";
		PreparedStatement psmt = null;
		psmt = conn.prepareStatement(sql);
		psmt.setString(1,id);
		ResultSet rs = psmt.executeQuery();
		if (rs.next()) {
			Notice_Inf notice=new Notice_Inf(rs.getString("id"),
					rs.getString("name"),
					rs.getTimestamp("create_date"),
					rs.getString("type_id"),
					rs.getString("content"),
					rs.getString("user_id"),
					rs.getTimestamp("modify_date"));
			JdbcUtil.closeResultSet(rs);
			JdbcUtil.closeStatement(psmt);
			return notice;
		} else {
			JdbcUtil.closeResultSet(rs);
			JdbcUtil.closeStatement(psmt);
			return null;
		}
	}
	
	public boolean noticeModify(Notice_Inf ni,String modify_date) throws SQLException {
		if(ni.getType_id()!=null&&!"".equals(ni.getType_id())) {
			Connection conn = JdbcUtil.getConnection();
			String sql = "update notice_inf set type_id=?,content=?,modify_date=? where id=?;";
			PreparedStatement psmt = null;
			psmt = conn.prepareStatement(sql);
			psmt.setString(1,ni.getType_id());
			psmt.setString(2,ni.getContent());
			psmt.setString(3,modify_date);
			psmt.setString(4,ni.getId());
			int count = psmt.executeUpdate();
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
}
