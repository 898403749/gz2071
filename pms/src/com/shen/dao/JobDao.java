package com.shen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shen.domain.Dept_Inf;
import com.shen.domain.Job_Inf;
import com.shen.utils.JdbcUtil;

public class JobDao {          //提供对job_inf表的基本操作

	public List<Job_Inf> jobQueryJob_Inf(String name) throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		List<Job_Inf> joblist=new ArrayList<>();
		String sql = "select *from job_inf where NAME like ? ;";
		PreparedStatement psmt = null;
		psmt = conn.prepareStatement(sql);
		psmt.setString(1,"%"+name.replace(" ", "")+"%");
		ResultSet rs = psmt.executeQuery();
		while (rs.next()) {
			Job_Inf job=new Job_Inf(rs.getString("ID"),
					rs.getString("name"),
					rs.getString("REMARK"),
					rs.getString("state"));
			joblist.add(job);
		}
		JdbcUtil.closeResultSet(rs);
		JdbcUtil.closeStatement(psmt);
		return joblist;
	}
	
	public List<Job_Inf> showPage_jobQueryJob_Inf(String name,Integer pageIndex,Integer pageSize) throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		List<Job_Inf> joblist=new ArrayList<>();
		Integer startnumber=(pageIndex-1)*pageSize;
		String sql = "select *from job_inf where NAME like ? limit ?,?;";
		PreparedStatement psmt = null;
		psmt = conn.prepareStatement(sql);
		psmt.setString(1,"%"+name.replace(" ", "")+"%");
		psmt.setInt(2,startnumber);
		psmt.setInt(3,pageSize);
		ResultSet rs = psmt.executeQuery();
		while (rs.next()) {
			Job_Inf job=new Job_Inf(rs.getString("ID"),
					rs.getString("name"),
					rs.getString("REMARK"),
					rs.getString("state"));
			joblist.add(job);
		}
		JdbcUtil.closeResultSet(rs);
		JdbcUtil.closeStatement(psmt);
		return joblist;
	}
	
	public Job_Inf getJob_InfById(String id) throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		String sql = "select *from job_inf where ID=?;";
		PreparedStatement psmt = null;
		psmt = conn.prepareStatement(sql);
		psmt.setString(1,id);
		ResultSet rs = psmt.executeQuery();
		if (rs.next()) {
			Job_Inf job=new Job_Inf(rs.getString("ID"),
					rs.getString("name"),
					rs.getString("REMARK"),
					rs.getString("state"));
			JdbcUtil.closeResultSet(rs);
			JdbcUtil.closeStatement(psmt);
			return job;
		} else {
			JdbcUtil.closeResultSet(rs);
			JdbcUtil.closeStatement(psmt);
			return null;
		}
	}
	
	public boolean jobDelete(String id) throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		String sql = "delete from job_inf where ID=?;";
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
	
	public boolean jobAdd(Job_Inf ji) throws SQLException {
		if(!checkJobname(ji.getName())&&ji.getName()!=null&&!"".equals(ji.getName())) {
			Connection conn = JdbcUtil.getConnection();
			String sql = "insert into job_inf(NAME,REMARK) values(?,?);";
			PreparedStatement psmt = null;
			psmt = conn.prepareStatement(sql);
			psmt.setString(1,ji.getName());
			psmt.setString(2,ji.getRemark());
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
	
	public boolean checkJobname(String NAME) throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		String sql = "select *from job_inf where NAME=?;";
		PreparedStatement psmt = null;
		psmt = conn.prepareStatement(sql);
		psmt.setString(1,NAME);
		ResultSet rs = psmt.executeQuery();
		if (rs.next()) {
			JdbcUtil.closeResultSet(rs);
			JdbcUtil.closeStatement(psmt);
			return true;
		} else {
			JdbcUtil.closeResultSet(rs);
			JdbcUtil.closeStatement(psmt);
			return false;
		}
	}
	
	private static boolean isNumeric(String str){
		for (int i = 0; i < str.length(); i++){
			if (!Character.isDigit(str.charAt(i))){
				return false;
			}
		}
		return true;
	}
	
	public boolean jobModify(Job_Inf ji) throws SQLException {
		if(isNumeric(ji.getState())&&ji.getState()!=null&&!"".equals(ji.getState())) {
			Connection conn = JdbcUtil.getConnection();
			String sql = "update job_inf set REMARK=?,state=? where ID=?;";
			PreparedStatement psmt = null;
			psmt = conn.prepareStatement(sql);
			psmt.setString(1,ji.getRemark());
			psmt.setString(2,ji.getState());
			psmt.setString(3,ji.getId());
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
	
	public Map<String,String> getAllJobName() throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		Map<String,String> jobnamemap=new HashMap<>();
		String sql = "select ID,NAME from job_inf;";
		PreparedStatement psmt = null;
		psmt = conn.prepareStatement(sql);
		ResultSet rs = psmt.executeQuery();
		while (rs.next()) {
			jobnamemap.put(rs.getString("ID"),rs.getString("NAME"));
		} 
		JdbcUtil.closeResultSet(rs);
		JdbcUtil.closeStatement(psmt);
		return jobnamemap;
	}
}
