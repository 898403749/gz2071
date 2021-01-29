package com.shen.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.shen.dao.DeptDao;
import com.shen.dao.EmployeeDao;
import com.shen.dao.JobDao;
import com.shen.dao.NoticeDao;
import com.shen.dao.TypeDao;
import com.shen.dao.UserDao;
import com.shen.domain.Employee_Inf;
import com.shen.domain.Notice_Inf;
import com.shen.utils.JdbcUtil;

public class NoticeService {

	public Map<String,String> getAllTypeName() throws SQLException {
		JdbcUtil.getConnection();
		TypeDao td=new TypeDao();
		Map<String,String> typenamemap=td.getAllTypeName();
		JdbcUtil.closeConnection();
		return typenamemap;
	}
	
	public List<Notice_Inf> noticeQuery(String name,String type,Integer pageIndex,Integer pageSize) throws SQLException {
		JdbcUtil.getConnection();
		NoticeDao nd=new NoticeDao();
		TypeDao td=new TypeDao();
		UserDao ud=new UserDao();
		List<Notice_Inf> noticelist=nd.showPage_noticeQueryNotice_Inf(name,type,pageIndex, pageSize);
		for(Notice_Inf li:noticelist) {
			if(li.getType_id()!=null&&!"".equals(li.getType_id())) {
				li.setType(td.getType_InfById(li.getType_id()).getName());
			}
			if(li.getUser_id()!=null&&!"".equals(li.getUser_id())) {
				li.setUser(ud.getUser_InfById(li.getUser_id()).getLoginname());
			}
		}
		JdbcUtil.closeConnection();
		return noticelist;
	}
	
	public List<Notice_Inf> allnoticeQuery(String name,String type) throws SQLException {
		JdbcUtil.getConnection();
		NoticeDao nd=new NoticeDao();
		TypeDao td=new TypeDao();
		UserDao ud=new UserDao();
		List<Notice_Inf> noticelist=nd.noticeQueryNotice_Inf(name,type);
		for(Notice_Inf li:noticelist) {
			if(li.getType_id()!=null&&!"".equals(li.getType_id())) {
				li.setType(td.getType_InfById(li.getType_id()).getName());
			}
			if(li.getUser_id()!=null&&!"".equals(li.getUser_id())) {
				li.setUser(ud.getUser_InfById(li.getUser_id()).getLoginname());
			}
		}
		JdbcUtil.closeConnection();
		return noticelist;
	}
	
	public boolean noticeAdd(Notice_Inf ni) throws SQLException {
		JdbcUtil.getConnection();
		NoticeDao nd=new NoticeDao();
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		JdbcUtil.closeConnection();
		return nd.noticeAdd(ni,sd.format(cal.getTime()),sd.format(cal.getTime()));
	}
	
	public boolean noticeDelete(String id) throws SQLException {
		JdbcUtil.getConnection();
		NoticeDao nd=new NoticeDao();
		JdbcUtil.closeConnection();
		return nd.noticeDelete(id);
	}
	
	public Notice_Inf getNotice_InfById(String id) throws SQLException {
		JdbcUtil.getConnection();
		NoticeDao nd=new NoticeDao();
		TypeDao td=new TypeDao();
		UserDao ud=new UserDao();
		Notice_Inf ni=nd.getNotice_InfById(id);
		if(ni.getType_id()!=null&&!"".equals(ni.getType_id())) {
			ni.setType(td.getType_InfById(ni.getType_id()).getName());
		}
		if(ni.getUser_id()!=null&&!"".equals(ni.getUser_id())) {
			ni.setUser(ud.getUser_InfById(ni.getUser_id()).getLoginname());
		}
		JdbcUtil.closeConnection();
		return ni;
	}
	
	public boolean noticeModify(Notice_Inf ni) throws SQLException {
		JdbcUtil.getConnection();
		NoticeDao nd=new NoticeDao();
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		JdbcUtil.closeConnection();
		return nd.noticeModify(ni,sd.format(cal.getTime()));
	}
}
