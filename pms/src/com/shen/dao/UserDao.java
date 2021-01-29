package com.shen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shen.domain.User_Inf;
import com.shen.utils.JdbcUtil;

public class UserDao {               //提供对user_inf表的基本操作

	public User_Inf login(String loginname,String password) throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		String sql = "select *from user_inf where loginname=? and PASSWORD=?;";
		PreparedStatement psmt = null;
		psmt = conn.prepareStatement(sql);
		psmt.setString(1,loginname);
		psmt.setString(2,password);
		ResultSet rs = psmt.executeQuery();
		if (rs.next()) {
			User_Inf user=new User_Inf(rs.getString("ID"),
					rs.getString("loginname"),
					rs.getString("PASSWORD"),
					rs.getString("STATUS"),
					rs.getString("createdate"),
					rs.getString("username"));
			JdbcUtil.closeResultSet(rs);
			JdbcUtil.closeStatement(psmt);
			return user;
		} else {
			JdbcUtil.closeResultSet(rs);
			JdbcUtil.closeStatement(psmt);
			return null;
		}
	}
	
	public boolean checkUsername(String loginname) throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		String sql = "select *from user_inf where loginname=?;";
		PreparedStatement psmt = null;
		psmt = conn.prepareStatement(sql);
		psmt.setString(1,loginname);
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
	
	public User_Inf getUser_InfByLoginname(String loginname) throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		String sql = "select *from user_inf where loginname=?;";
		PreparedStatement psmt = null;
		psmt = conn.prepareStatement(sql);
		psmt.setString(1,loginname);
		ResultSet rs = psmt.executeQuery();
		if (rs.next()) {
			User_Inf user=new User_Inf(rs.getString("ID"),
					rs.getString("loginname"),
					rs.getString("PASSWORD"),
					rs.getString("STATUS"),
					rs.getString("createdate"),
					rs.getString("username"));
			JdbcUtil.closeResultSet(rs);
			JdbcUtil.closeStatement(psmt);
			return user;
		} else {
			JdbcUtil.closeResultSet(rs);
			JdbcUtil.closeStatement(psmt);
			return null;
		}
	}
	
	public User_Inf getUser_InfById(String id) throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		String sql = "select *from user_inf where ID=?;";
		PreparedStatement psmt = null;
		psmt = conn.prepareStatement(sql);
		psmt.setString(1,id);
		ResultSet rs = psmt.executeQuery();
		if (rs.next()) {
			User_Inf user=new User_Inf(rs.getString("ID"),
					rs.getString("loginname"),
					rs.getString("PASSWORD"),
					rs.getString("STATUS"),
					rs.getString("createdate"),
					rs.getString("username"));
			JdbcUtil.closeResultSet(rs);
			JdbcUtil.closeStatement(psmt);
			return user;
		} else {
			JdbcUtil.closeResultSet(rs);
			JdbcUtil.closeStatement(psmt);
			return null;
		}
	}
	
	public List<User_Inf> userQueryUser_Inf(String loginname,String username,String status) throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		List<User_Inf> userlist=new ArrayList<>();
		String sql = "select *from user_inf where loginname like ? and username like ? and status like ?;";
		PreparedStatement psmt = null;
		psmt = conn.prepareStatement(sql);
		psmt.setString(1,"%"+loginname.replace(" ", "")+"%");
		psmt.setString(2,"%"+username.replace(" ", "")+"%");
		psmt.setString(3,"%"+status+"%");
		ResultSet rs = psmt.executeQuery();
		while (rs.next()) {
			User_Inf user=new User_Inf(rs.getString("ID"),
					rs.getString("loginname"),
					rs.getString("PASSWORD"),
					rs.getString("STATUS"),
					rs.getString("createdate"),
					rs.getString("username"));
			userlist.add(user);
		}
		JdbcUtil.closeResultSet(rs);
		JdbcUtil.closeStatement(psmt);
		return userlist;
	}
	
	public List<User_Inf> showPage_userQueryUser_Inf(String loginname,String username,String status,Integer pageIndex,Integer pageSize) throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		List<User_Inf> userlist=new ArrayList<>();
		Integer startnumber=(pageIndex-1)*pageSize;
		String sql = "select *from user_inf where loginname like ? and username like ? and status like ? limit ?,?;";
		PreparedStatement psmt = null;
		psmt = conn.prepareStatement(sql);
		psmt.setString(1,"%"+loginname.replace(" ", "")+"%");
		psmt.setString(2,"%"+username.replace(" ", "")+"%");
		psmt.setString(3,"%"+status+"%");
		psmt.setInt(4,startnumber);
		psmt.setInt(5,pageSize);
		ResultSet rs = psmt.executeQuery();
		while (rs.next()) {
			User_Inf user=new User_Inf(rs.getString("ID"),
					rs.getString("loginname"),
					rs.getString("PASSWORD"),
					rs.getString("STATUS"),
					rs.getString("createdate"),
					rs.getString("username"));
			userlist.add(user);
		}
		JdbcUtil.closeResultSet(rs);
		JdbcUtil.closeStatement(psmt);
		return userlist;
	}
	
	public boolean userDelete(String id) throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		String sql = "delete from user_inf where ID=?;";
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
	
	private static boolean isNumeric(String str){
		for (int i = 0; i < str.length(); i++){
			if (!Character.isDigit(str.charAt(i))){
				return false;
			}
		}
		return true;
	}
	
	public boolean userAdd(User_Inf ui) throws SQLException {
		if(ui.getLoginname()!=null&&!"".equals(ui.getLoginname())&&getUser_InfByLoginname(ui.getLoginname())==null&&ui.getPassword()!=null&&!"".equals(ui.getPassword())) {
			if(isNumeric(ui.getStatus())||ui.getStatus()==null) {
				Connection conn = JdbcUtil.getConnection();
				String sql_vn="insert into user_inf(loginname,PASSWORD,username";
				String sql_v=") values(?,?,?";
				String sql_end=");";
				int num=0;
				if(ui.getStatus()!=null&&!"".equals(ui.getStatus())) {
					sql_vn=sql_vn+",STATUS";
					sql_v=sql_v+",?";
				}
				String sql=sql_vn+sql_v+sql_end;
				PreparedStatement psmt = null;
				psmt = conn.prepareStatement(sql);
				psmt.setString(1,ui.getLoginname());
				psmt.setString(2,ui.getPassword());
				if(ui.getUsername()==null) {
					psmt.setString(3,"");
				}else {
					psmt.setString(3,ui.getUsername());
				}
				if(ui.getStatus()!=null&&!"".equals(ui.getStatus())) {
					num++;
					psmt.setString(3+num,ui.getStatus());
				}
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
		}else {
			return false;
		}
	}
	
	public boolean userModify(User_Inf ui) throws SQLException {
		if(ui.getPassword()!=null&&!"".equals(ui.getPassword())) {
			if(isNumeric(ui.getStatus())&&ui.getStatus()!=null&&!"".equals(ui.getStatus())) {
				Connection conn = JdbcUtil.getConnection();
				String sql_v="update user_inf set PASSWORD=?,username=?";
				String sql_end=" where ID=?;";
				int num=0;
				if(ui.getStatus()!=null&&!"".equals(ui.getStatus())) {
					sql_v=sql_v+",STATUS=?";
				}
				String sql=sql_v+sql_end;
				PreparedStatement psmt = null;
				psmt = conn.prepareStatement(sql);
				psmt.setString(1,ui.getPassword());
				if(ui.getUsername()==null) {
					psmt.setString(2,"");
				}else {
					psmt.setString(2,ui.getUsername());
				}
				if(ui.getStatus()!=null&&!"".equals(ui.getStatus())) {
					num++;
					psmt.setString(2+num,ui.getStatus());
				}
				psmt.setString(3+num,ui.getId());
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
		}else {
			return false;
		}
	}
}
