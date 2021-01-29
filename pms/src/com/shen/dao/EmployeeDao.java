package com.shen.dao;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.shen.domain.Employee_Inf;
import com.shen.domain.Job_Inf;
import com.shen.utils.JdbcUtil;

public class EmployeeDao {                  //提供对employee_inf表的基本操作

	public List<Employee_Inf> employeeQueryEmployee_Inf(String job_id,String name,String card_id,String sex,String phone,String dept_id) throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		List<Employee_Inf> employeelist=new ArrayList<>();
		String sql = "select *from employee_inf where NAME like ? and CARD_ID like ? and PHONE like ?";
		if(sex!=null&&!"".equals(sex)) {
			sql=sql+" and sex like ?";
		}
		if(dept_id!=null&&!"".equals(dept_id)) {
			sql=sql+" and dept_id like ?";
		}
		if(job_id!=null&&!"".equals(job_id)) {
			sql=sql+" and job_id like ?";
		}
		sql=sql+";";
		PreparedStatement psmt = null;
		psmt = conn.prepareStatement(sql);
		psmt.setString(1,"%"+name.replace(" ", "")+"%");
		psmt.setString(2,"%"+card_id.replace(" ", "")+"%");
		psmt.setString(3,"%"+phone.replace(" ", "")+"%");
		int num=0;
		if(sex!=null&&!"".equals(sex)) {
			num++;
			psmt.setString(3+num,"%"+sex+"%");
		}
		if(dept_id!=null&&!"".equals(dept_id)) {
			num++;
			psmt.setString(3+num,"%"+dept_id+"%");
		}
		if(job_id!=null&&!"".equals(job_id)) {
			num++;
			psmt.setString(3+num,"%"+job_id+"%");
		}
		ResultSet rs = psmt.executeQuery();
		while (rs.next()) {
			Employee_Inf employee=new Employee_Inf(rs.getString("ID"),
					rs.getString("NAME"),
					rs.getString("CARD_ID"),
					rs.getString("ADDRESS"),
					rs.getString("POST_CODE"),
					rs.getString("TEL"),
					rs.getString("PHONE"),
					rs.getString("QQ_NUM"),
					rs.getString("EMAIL"),
					getQuerySex(rs.getString("SEX")),
					rs.getString("PARTY"),
					rs.getTimestamp("BIRTHDAY"),
					rs.getString("RACE"),
					rs.getString("EDUCATION"),
					rs.getString("SPECIALITY"),
					rs.getString("HOBBY"),
					rs.getString("REMARK"),
					rs.getString("CREATE_DATE"),
					rs.getString("STATE"),
					rs.getString("dept_id"),
					rs.getString("job_id"));
			employeelist.add(employee);
		}
		JdbcUtil.closeResultSet(rs);
		JdbcUtil.closeStatement(psmt);
		return employeelist;
	}
	
	public List<Employee_Inf> showPage_employeeQueryEmployee_Inf(String job_id,String name,String card_id,String sex,String phone,String dept_id,Integer pageIndex,Integer pageSize) throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		List<Employee_Inf> employeelist=new ArrayList<>();
		Integer startnumber=(pageIndex-1)*pageSize;
		String sql = "select *from employee_inf where NAME like ? and CARD_ID like ? and PHONE like ?";
		if(sex!=null&&!"".equals(sex)) {
			sql=sql+" and sex like ?";
		}
		if(dept_id!=null&&!"".equals(dept_id)) {
			sql=sql+" and dept_id like ?";
		}
		if(job_id!=null&&!"".equals(job_id)) {
			sql=sql+" and job_id like ?";
		}
		sql=sql+" limit ?,?;";
		PreparedStatement psmt = null;
		psmt = conn.prepareStatement(sql);
		psmt.setString(1,"%"+name.replace(" ", "")+"%");
		psmt.setString(2,"%"+card_id.replace(" ", "")+"%");
		psmt.setString(3,"%"+phone.replace(" ", "")+"%");
		int num=0;
		if(sex!=null&&!"".equals(sex)) {
			num++;
			psmt.setString(3+num,"%"+sex+"%");
		}
		if(dept_id!=null&&!"".equals(dept_id)) {
			num++;
			psmt.setString(3+num,"%"+dept_id+"%");
		}
		if(job_id!=null&&!"".equals(job_id)) {
			num++;
			psmt.setString(3+num,"%"+job_id+"%");
		}
		psmt.setInt(4+num,startnumber);
		psmt.setInt(5+num,pageSize);
		ResultSet rs = psmt.executeQuery();
		while (rs.next()) {
			Employee_Inf employee=new Employee_Inf(rs.getString("ID"),
					rs.getString("NAME"),
					rs.getString("CARD_ID"),
					rs.getString("ADDRESS"),
					rs.getString("POST_CODE"),
					rs.getString("TEL"),
					rs.getString("PHONE"),
					rs.getString("QQ_NUM"),
					rs.getString("EMAIL"),
					getQuerySex(rs.getString("SEX")),
					rs.getString("PARTY"),
					rs.getTimestamp("BIRTHDAY"),
					rs.getString("RACE"),
					rs.getString("EDUCATION"),
					rs.getString("SPECIALITY"),
					rs.getString("HOBBY"),
					rs.getString("REMARK"),
					rs.getString("CREATE_DATE"),
					rs.getString("STATE"),
					rs.getString("dept_id"),
					rs.getString("job_id"));
			employeelist.add(employee);
		}
		JdbcUtil.closeResultSet(rs);
		JdbcUtil.closeStatement(psmt);
		return employeelist;
	}
	
	private String getQuerySex(String num) {
		if(num==null) {
			return null;
		}else if(num=="0"||"0".equals(num)) {
			return "女";
		}else if(num=="1"||"1".equals(num)) {
			return "男";
		}
		return null;
	}
	
	public boolean employeeAdd(Employee_Inf ei) throws SQLException {
		if(existence(ei.getName(),ei.getCard_id(),ei.getAddress(),ei.getPost_code(),ei.getTel(),
				ei.getPhone(),ei.getQq_num(),ei.getEmail(),ei.getSex(),ei.getParty(),ei.getRace(),
				ei.getEducation(),ei.getSpeciality(),ei.getHobby(),ei.getRemark(),ei.getDept_id(),
				ei.getJob_id())) {
			Connection conn = JdbcUtil.getConnection();
			String sql = "insert into employee_inf(NAME,CARD_ID,ADDRESS,POST_CODE,TEL,PHONE,QQ_NUM,EMAIL,SEX,PARTY,BIRTHDAY,RACE,EDUCATION,SPECIALITY,HOBBY,REMARK,dept_id,job_id) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
			PreparedStatement psmt = null;
			psmt = conn.prepareStatement(sql);
			psmt.setString(1,ei.getName());
			psmt.setString(2,ei.getCard_id());
			psmt.setString(3,ei.getAddress());
			psmt.setString(4,ei.getPost_code());
			psmt.setString(5,ei.getTel());
			psmt.setString(6,ei.getPhone());
			psmt.setString(7,ei.getQq_num());
			psmt.setString(8,ei.getEmail());
			psmt.setString(9,ei.getSex());
			psmt.setString(10,ei.getParty());
			psmt.setTimestamp(11,ei.getBirthday());
			psmt.setString(12,ei.getRace());
			psmt.setString(13,ei.getEducation());
			psmt.setString(14,ei.getSpeciality());
			psmt.setString(15,ei.getHobby());
			psmt.setString(16,ei.getRemark());
			psmt.setString(17,ei.getDept_id());
			psmt.setString(18,ei.getJob_id());
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
	
	private boolean existence(String... strs) {
		for (String str:strs) {
			if(str==null||"".equals(str)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean employeeDelete(String id) throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		String sql = "delete from employee_inf where ID=?;";
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
	
	public Employee_Inf getEmployee_InfById(String id) throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		String sql = "select *from employee_inf where ID=?;";
		PreparedStatement psmt = null;
		psmt = conn.prepareStatement(sql);
		psmt.setString(1,id);
		ResultSet rs = psmt.executeQuery();
		if (rs.next()) {
			Employee_Inf employee=new Employee_Inf(rs.getString("ID"),
					rs.getString("NAME"),
					rs.getString("CARD_ID"),
					rs.getString("ADDRESS"),
					rs.getString("POST_CODE"),
					rs.getString("TEL"),
					rs.getString("PHONE"),
					rs.getString("QQ_NUM"),
					rs.getString("EMAIL"),
					rs.getString("SEX"),
					rs.getString("PARTY"),
					rs.getTimestamp("BIRTHDAY"),
					rs.getString("RACE"),
					rs.getString("EDUCATION"),
					rs.getString("SPECIALITY"),
					rs.getString("HOBBY"),
					rs.getString("REMARK"),
					rs.getString("CREATE_DATE"),
					rs.getString("STATE"),
					rs.getString("dept_id"),
					rs.getString("job_id"));
			JdbcUtil.closeResultSet(rs);
			JdbcUtil.closeStatement(psmt);
			return employee;
		} else {
			JdbcUtil.closeResultSet(rs);
			JdbcUtil.closeStatement(psmt);
			return null;
		}
	}
	
	public boolean employeeModify(Employee_Inf ei) throws SQLException {
		if(existence(ei.getName(),ei.getCard_id(),ei.getAddress(),ei.getPost_code(),ei.getTel(),
				ei.getPhone(),ei.getQq_num(),ei.getEmail(),ei.getSex(),ei.getParty(),ei.getRace(),
				ei.getEducation(),ei.getSpeciality(),ei.getHobby(),ei.getRemark(),ei.getDept_id(),
				ei.getJob_id())) {
			Connection conn = JdbcUtil.getConnection();
			String sql = "update employee_inf set NAME=?,CARD_ID=?,ADDRESS=?,POST_CODE=?,TEL=?,PHONE=?,QQ_NUM=?,EMAIL=?,SEX=?,PARTY=?,BIRTHDAY=?,RACE=?,EDUCATION=?,SPECIALITY=?,HOBBY=?,REMARK=?,dept_id=?,job_id=? where ID=?;";
			PreparedStatement psmt = null;
			psmt = conn.prepareStatement(sql);
			psmt.setString(1,ei.getName());
			psmt.setString(2,ei.getCard_id());
			psmt.setString(3,ei.getAddress());
			psmt.setString(4,ei.getPost_code());
			psmt.setString(5,ei.getTel());
			psmt.setString(6,ei.getPhone());
			psmt.setString(7,ei.getQq_num());
			psmt.setString(8,ei.getEmail());
			psmt.setString(9,ei.getSex());
			psmt.setString(10,ei.getParty());
			psmt.setTimestamp(11,ei.getBirthday());
			psmt.setString(12,ei.getRace());
			psmt.setString(13,ei.getEducation());
			psmt.setString(14,ei.getSpeciality());
			psmt.setString(15,ei.getHobby());
			psmt.setString(16,ei.getRemark());
			psmt.setString(17,ei.getDept_id());
			psmt.setString(18,ei.getJob_id());
			psmt.setString(19,ei.getId());
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
