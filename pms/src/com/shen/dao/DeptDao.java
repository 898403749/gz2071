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
import com.shen.domain.User_Inf;
import com.shen.utils.JdbcUtil;

public class DeptDao {            //提供对dept_inf表的基本操作

	public List<Dept_Inf> deptQueryDept_Inf(String name) throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		List<Dept_Inf> deptlist=new ArrayList<>();
		String sql = "select *from dept_inf where NAME like ? ;";
		PreparedStatement psmt = null;
		psmt = conn.prepareStatement(sql);
		psmt.setString(1,"%"+name.replace(" ", "")+"%");
		ResultSet rs = psmt.executeQuery();
		while (rs.next()) {
			Dept_Inf dept=new Dept_Inf(rs.getString("ID"),
					rs.getString("NAME"),
					rs.getString("REMARK"),
					rs.getString("state"));
			deptlist.add(dept);
		}
		JdbcUtil.closeResultSet(rs);
		JdbcUtil.closeStatement(psmt);
		return deptlist;
	}
	
	public List<Dept_Inf> showPage_deptQueryDept_Inf(String name,Integer pageIndex,Integer pageSize) throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		List<Dept_Inf> deptlist=new ArrayList<>();
		Integer startnumber=(pageIndex-1)*pageSize;
		String sql = "select *from dept_inf where NAME like ? limit ?,?;";
		PreparedStatement psmt = null;
		psmt = conn.prepareStatement(sql);
		psmt.setString(1,"%"+name.replace(" ", "")+"%");
		psmt.setInt(2,startnumber);
		psmt.setInt(3,pageSize);
		ResultSet rs = psmt.executeQuery();
		while (rs.next()) {
			Dept_Inf dept=new Dept_Inf(rs.getString("ID"),
					rs.getString("NAME"),
					rs.getString("REMARK"),
					rs.getString("state"));
			deptlist.add(dept);
		}
		JdbcUtil.closeResultSet(rs);
		JdbcUtil.closeStatement(psmt);
		return deptlist;
	}
	
	public Dept_Inf getDept_InfById(String id) throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		String sql = "select *from dept_inf where ID=?;";
		PreparedStatement psmt = null;
		psmt = conn.prepareStatement(sql);
		psmt.setString(1,id);
		ResultSet rs = psmt.executeQuery();
		if (rs.next()) {
			Dept_Inf dept=new Dept_Inf(rs.getString("ID"),
					rs.getString("NAME"),
					rs.getString("REMARK"),
					rs.getString("state"));
			JdbcUtil.closeResultSet(rs);
			JdbcUtil.closeStatement(psmt);
			return dept;
		} else {
			JdbcUtil.closeResultSet(rs);
			JdbcUtil.closeStatement(psmt);
			return null;
		}
	}
	
	public boolean deptDelete(String id) throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		String sql = "delete from dept_inf where ID=?;";
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
	
	public boolean deptAdd(Dept_Inf di) throws SQLException {
		if(!checkDeptname(di.getName())&&di.getName()!=null&&!"".equals(di.getName())) {
			Connection conn = JdbcUtil.getConnection();
			String sql = "insert into dept_inf(NAME,REMARK) values(?,?);";
			PreparedStatement psmt = null;
			psmt = conn.prepareStatement(sql);
			psmt.setString(1,di.getName());
			psmt.setString(2,di.getRemark());
			int count = psmt.executeUpdate();
			if(count==1) {
				return true;
			}else {
				JdbcUtil.closeStatement(psmt);
				return false;
			}
		}else {
			return false;
		}
	}
	
	public boolean checkDeptname(String NAME) throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		String sql = "select *from dept_inf where NAME=?;";
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
	
	public boolean deptModify(Dept_Inf di) throws SQLException {
		if(isNumeric(di.getState())&&di.getState()!=null&&!"".equals(di.getState())) {
			Connection conn = JdbcUtil.getConnection();
			String sql = "update dept_inf set REMARK=?,state=? where ID=?;";
			PreparedStatement psmt = null;
			psmt = conn.prepareStatement(sql);
			psmt.setString(1,di.getRemark());
			psmt.setString(2,di.getState());
			psmt.setString(3,di.getId());
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
	
	public Map<String,String> getAllDeptName() throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		Map<String,String> deptnamemap=new HashMap<>();
		String sql = "select ID,NAME from dept_inf;";
		PreparedStatement psmt = null;
		psmt = conn.prepareStatement(sql);
		ResultSet rs = psmt.executeQuery();
		while (rs.next()) {
			deptnamemap.put(rs.getString("ID"),rs.getString("NAME"));
		} 
		JdbcUtil.closeResultSet(rs);
		JdbcUtil.closeStatement(psmt);
		return deptnamemap;
	}
}
