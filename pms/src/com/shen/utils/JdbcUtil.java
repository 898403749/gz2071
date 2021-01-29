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
	
	//����mysql����
	static{
		try {
			Class.forName(driverName);
			System.out.println("{SERVER} ����mysql�����ɹ�");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("{SERVER} �������Ҳ����쳣");
		}
	}
	
	//����һ���̱߳�����һ����������һ���߳���
	private static ThreadLocal<Connection> local=new ThreadLocal();
	
	public static Connection getConnection() throws SQLException {
		Connection conn=local.get();      //��������Ҫ����
		if(conn==null) {          //���������û������
			conn=DriverManager.getConnection(url,username,password);   //��mysql��������Ҫ����
			local.set(conn);            //���߳�������
		}
		return conn;
	}
	
	//�ر�����
	public static void closeConnection() throws SQLException {
		Connection conn=local.get();      //��������Ҫ����
		if(conn!=null) {       //�����������
			conn.close();       //�ر�����
			local.remove();      //������
			System.out.println("{SERVER} �ɹ��ر�mysql����");
		}
	}
	
	//�رս����
	public static void closeResultSet(ResultSet rs) throws SQLException {
		if(rs!=null) {
			rs.close();
			System.out.println("{SERVER} �ɹ��رս����");
		}
	}
	
	//�ر�Statement����
	public static void closeStatement(Statement stmt) throws SQLException {
		if(stmt!=null) {
			stmt.close();
			System.out.println("{SERVER} �ɹ��ر�Statement����");
		}
	}

}