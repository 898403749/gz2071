package com.shen.service;

import java.sql.SQLException;
import java.util.List;

import com.shen.dao.DeptDao;
import com.shen.dao.JobDao;
import com.shen.dao.UserDao;
import com.shen.domain.Dept_Inf;
import com.shen.domain.Job_Inf;
import com.shen.domain.User_Inf;
import com.shen.utils.JdbcUtil;

public class JobService {

	public List<Job_Inf> jobQuery(String name,Integer pageIndex,Integer pageSize) throws SQLException {
		JdbcUtil.getConnection();
		JobDao jd=new JobDao();
		List<Job_Inf> joblist=jd.showPage_jobQueryJob_Inf(name,pageIndex, pageSize);
		JdbcUtil.closeConnection();
		return joblist;
	}
	
	public List<Job_Inf> alljobQuery(String name) throws SQLException {
		JdbcUtil.getConnection();
		JobDao jd=new JobDao();
		List<Job_Inf> joblist=jd.jobQueryJob_Inf(name);
		JdbcUtil.closeConnection();
		return joblist;
	}
	
	public boolean jobModify(Job_Inf ji) throws SQLException {
		JdbcUtil.getConnection();
		JobDao jd=new JobDao();
		JdbcUtil.closeConnection();
		return jd.jobModify(ji);
	}
	
	public boolean jobDelete(String id) throws SQLException {
		JdbcUtil.getConnection();
		JobDao jd=new JobDao();
		JdbcUtil.closeConnection();
		return jd.jobDelete(id);
	}
	
	public boolean jobAdd(Job_Inf ji) throws SQLException {
		JdbcUtil.getConnection();
		JobDao jd=new JobDao();
		JdbcUtil.closeConnection();
		return jd.jobAdd(ji);
	}
	
	public Job_Inf getJob_InfById(String id) throws SQLException {
		JdbcUtil.getConnection();
		JobDao jd=new JobDao();
		JdbcUtil.closeConnection();
		return jd.getJob_InfById(id);
	}
}
