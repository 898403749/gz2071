package com.shen.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {
	
	private final static String username="root";
	private final static String password="root";
	private final static String driverName="com.mysql.jdbc.Driver";
	private final static String url="jdbc:mysql://localhost:3306/hrm?useUnicode=true&characterEncoding=UTF-8";
	
	//加载mysql驱动
	static{
		try {
			Class.forName(driverName);
			System.out.println("{SERVER} 加载mysql驱动成功");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("{SERVER} 发生类找不到异常");
		}
	}
	
	//设置一个线程变量将一个连接捆绑到一个线程上
	private static ThreadLocal<Connection> local=new ThreadLocal();
	
	public static Connection getConnection() throws SQLException {
		Connection conn=local.get();      //向容器索要连接
		if(conn==null) {          //如果容器里没有连接
			conn=DriverManager.getConnection(url,username,password);   //向mysql连接器索要连接
			local.set(conn);            //绑定线程与连接
		}
		return conn;
	}
	
	//关闭连接
	public static void closeConnection() throws SQLException {
		Connection conn=local.get();      //向容器索要连接
		if(conn!=null) {       //如果有连接在
			conn.close();       //关闭连接
			local.remove();      //撤销绑定
			System.out.println("{SERVER} 成功关闭mysql连接");
		}
	}
	
	//关闭结果集
	public static void closeResultSet(ResultSet rs) throws SQLException {
		if(rs!=null) {
			rs.close();
			System.out.println("{SERVER} 成功关闭结果集");
		}
	}
	
	//关闭Statement对象
	public static void closeStatement(Statement stmt) throws SQLException {
		if(stmt!=null) {
			stmt.close();
			System.out.println("{SERVER} 成功关闭Statement对象");
		}
	}

}