package com.shen.service;

import java.sql.SQLException;
import java.util.List;

import com.shen.dao.DeptDao;
import com.shen.dao.UserDao;
import com.shen.domain.Dept_Inf;
import com.shen.domain.User_Inf;
import com.shen.utils.JdbcUtil;

public class DeptService {

	public List<Dept_Inf> deptQuery(String name,Integer pageIndex,Integer pageSize) throws SQLException {
		JdbcUtil.getConnection();
		DeptDao dd=new DeptDao();
		List<Dept_Inf> deptlist=dd.showPage_deptQueryDept_Inf(name,pageIndex, pageSize);
		JdbcUtil.closeConnection();
		return deptlist;
	}
	
	public List<Dept_Inf> alldeptQuery(String name) throws SQLException {
		JdbcUtil.getConnection();
		DeptDao dd=new DeptDao();
		List<Dept_Inf> deptlist=dd.deptQueryDept_Inf(name);
		JdbcUtil.closeConnection();
		return deptlist;
	}
	
	public boolean deptModify(Dept_Inf di) throws SQLException {
		JdbcUtil.getConnection();
		DeptDao dd=new DeptDao();
		JdbcUtil.closeConnection();
		return dd.deptModify(di);
	}
	
	public boolean deptDelete(String id) throws SQLException {
		JdbcUtil.getConnection();
		DeptDao dd=new DeptDao();
		JdbcUtil.closeConnection();
		return dd.deptDelete(id);
	}
	
	public boolean deptAdd(Dept_Inf di) throws SQLException {
		JdbcUtil.getConnection();
		DeptDao dd=new DeptDao();
		JdbcUtil.closeConnection();
		return dd.deptAdd(di);
	}
	
	public Dept_Inf getDept_InfById(String id) throws SQLException {
		JdbcUtil.getConnection();
		DeptDao dd=new DeptDao();
		JdbcUtil.closeConnection();
		return dd.getDept_InfById(id);
	}
}
