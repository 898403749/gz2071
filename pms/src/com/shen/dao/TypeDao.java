package com.shen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import com.shen.domain.Job_Inf;
import com.shen.domain.Type_Inf;
import com.shen.utils.JdbcUtil;

public class TypeDao {

	public Map<String,String> getAllTypeName() throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		Map<String,String> typenamemap=new HashMap<>();
		String sql = "select id,name from type_inf;";
		PreparedStatement psmt = null;
		psmt = conn.prepareStatement(sql);
		ResultSet rs = psmt.executeQuery();
		while (rs.next()) {
			typenamemap.put(rs.getString("id"),rs.getString("name"));
		} 
		JdbcUtil.closeResultSet(rs);
		JdbcUtil.closeStatement(psmt);
		return typenamemap;
	}
	
	public Type_Inf getType_InfById(String id) throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		String sql = "select *from type_inf where id=?;";
		PreparedStatement psmt = null;
		psmt = conn.prepareStatement(sql);
		psmt.setString(1,id);
		ResultSet rs = psmt.executeQuery();
		if (rs.next()) {
			Type_Inf type=new Type_Inf(rs.getString("id"),
					rs.getString("name"),
					rs.getTimestamp("create_date"),
					rs.getString("state"),
					rs.getString("user_id"),
					rs.getTimestamp("modify_date"));
			JdbcUtil.closeResultSet(rs);
			JdbcUtil.closeStatement(psmt);
			return type;
		} else {
			JdbcUtil.closeResultSet(rs);
			JdbcUtil.closeStatement(psmt);
			return null;
		}
	}
}
