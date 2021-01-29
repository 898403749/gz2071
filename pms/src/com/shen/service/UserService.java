package com.shen.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shen.dao.UserDao;
import com.shen.domain.User_Inf;
import com.shen.utils.JdbcUtil;

public class UserService {
	
	public List<User_Inf> userQuery(String loginname,String username,String status,Integer pageIndex,Integer pageSize) throws SQLException {
		JdbcUtil.getConnection();
		UserDao ud=new UserDao();
		List<User_Inf> userlist=ud.showPage_userQueryUser_Inf(loginname, username, status, pageIndex, pageSize);
		JdbcUtil.closeConnection();
		return userlist;
	}
	
	public List<User_Inf> alluserQuery(String loginname,String username,String status) throws SQLException {
		JdbcUtil.getConnection();
		UserDao ud=new UserDao();
		List<User_Inf> userlist=ud.userQueryUser_Inf(loginname, username, status);
		JdbcUtil.closeConnection();
		return userlist;
	}
	
	public boolean userModify(User_Inf ui) throws SQLException {
		JdbcUtil.getConnection();
		UserDao ud=new UserDao();
		JdbcUtil.closeConnection();
		return ud.userModify(ui);
	}
	
	public boolean userDelete(String id) throws SQLException {
		JdbcUtil.getConnection();
		UserDao ud=new UserDao();
		JdbcUtil.closeConnection();
		return ud.userDelete(id);
	}
	
	public boolean userAdd(User_Inf ui) throws SQLException {
		JdbcUtil.getConnection();
		UserDao ud=new UserDao();
		JdbcUtil.closeConnection();
		return ud.userAdd(ui);
	}
	
	public User_Inf getUser_InfById(String id) throws SQLException {
		JdbcUtil.getConnection();
		UserDao ud=new UserDao();
		JdbcUtil.closeConnection();
		return ud.getUser_InfById(id);
	}

}
