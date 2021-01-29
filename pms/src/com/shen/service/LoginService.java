package com.shen.service;

import java.sql.SQLException;

import com.shen.dao.UserDao;
import com.shen.domain.User_Inf;
import com.shen.utils.JdbcUtil;

public class LoginService {

	public User_Inf login(String loginname,String password) throws SQLException {
		JdbcUtil.getConnection();
		UserDao ud=new UserDao();
		JdbcUtil.closeConnection();
		return ud.login(loginname, password);
	}
	
	public boolean checkUsername(String loginname) throws SQLException {
		JdbcUtil.getConnection();
		UserDao ud=new UserDao();
		JdbcUtil.closeConnection();
		return ud.checkUsername(loginname);
	}
}
