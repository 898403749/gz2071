package com.shen.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.shen.dao.DeptDao;
import com.shen.dao.EmployeeDao;
import com.shen.dao.JobDao;
import com.shen.domain.Dept_Inf;
import com.shen.domain.Employee_Inf;
import com.shen.domain.Job_Inf;
import com.shen.utils.JdbcUtil;

public class EmployeeService {

	public Map<String,String> getAllDeptName() throws SQLException {
		JdbcUtil.getConnection();
		DeptDao dd=new DeptDao();
		Map<String,String> deptnamemap=dd.getAllDeptName();
		JdbcUtil.closeConnection();
		return deptnamemap;
	}
	
	public Map<String,String> getAllJobName() throws SQLException {
		JdbcUtil.getConnection();
		JobDao jd=new JobDao();
		Map<String,String> jobnamemap=jd.getAllJobName();
		JdbcUtil.closeConnection();
		return jobnamemap;
	}
	
	public List<Employee_Inf> employeeQuery(String job_id,String name,String card_id,String sex,String phone,String dept_id,Integer pageIndex,Integer pageSize) throws SQLException {
		JdbcUtil.getConnection();
		EmployeeDao ed=new EmployeeDao();
		JobDao jd=new JobDao();
		DeptDao dd=new DeptDao();
		List<Employee_Inf> employeelist=ed.showPage_employeeQueryEmployee_Inf(job_id,name,card_id,sex,phone,dept_id,pageIndex, pageSize);
		for(Employee_Inf li:employeelist) {
			if(li.getJob_id()!=null&&!"".equals(li.getJob_id())) {
				li.setJob_id(jd.getJob_InfById(li.getJob_id()).getName());
			}
			if(li.getDept_id()!=null&&!"".equals(li.getDept_id())) {
				li.setDept_id(dd.getDept_InfById(li.getDept_id()).getName());
			}
		}
		JdbcUtil.closeConnection();
		return employeelist;
	}
	
	public List<Employee_Inf> allemployeeQuery(String job_id,String name,String card_id,String sex,String phone,String dept_id) throws SQLException {
		JdbcUtil.getConnection();
		EmployeeDao ed=new EmployeeDao();
		JobDao jd=new JobDao();
		DeptDao dd=new DeptDao();
		List<Employee_Inf> employeelist=ed.employeeQueryEmployee_Inf(job_id,name,card_id,sex,phone,dept_id);
		for(Employee_Inf li:employeelist) {
			if(li.getJob_id()!=null&&!"".equals(li.getJob_id())) {
				li.setJob_id(jd.getJob_InfById(li.getJob_id()).getName());
			}
			if(li.getDept_id()!=null&&!"".equals(li.getDept_id())) {
				li.setDept_id(dd.getDept_InfById(li.getDept_id()).getName());
			}
		}
		JdbcUtil.closeConnection();
		return employeelist;
	}
	
	public boolean employeeAdd(Employee_Inf ei) throws SQLException {
		JdbcUtil.getConnection();
		EmployeeDao ed=new EmployeeDao();
		JdbcUtil.closeConnection();
		return ed.employeeAdd(ei);
	}
	
	public boolean employeeDelete(String id) throws SQLException {
		JdbcUtil.getConnection();
		EmployeeDao ed=new EmployeeDao();
		JdbcUtil.closeConnection();
		return ed.employeeDelete(id);
	}
	
	public boolean employeeModify(Employee_Inf ei) throws SQLException {
		JdbcUtil.getConnection();
		EmployeeDao ed=new EmployeeDao();
		JdbcUtil.closeConnection();
		return ed.employeeModify(ei);
	}
	
	public Employee_Inf getEmployee_InfById(String id) throws SQLException {
		JdbcUtil.getConnection();
		EmployeeDao ed=new EmployeeDao();
		JdbcUtil.closeConnection();
		return ed.getEmployee_InfById(id);
	}
}
