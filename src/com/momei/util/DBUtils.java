package com.momei.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBUtils {
	
    private static DataSource dataSource=null;
	
	static {
		 /**
		  * ��ȡc3p0����Դ
		  * */
		dataSource=new ComboPooledDataSource("oracle_c3p0");
	  }
	
	/**
	 * �õ�����
	 */
	public static Connection getConnection(){
		
		Connection con=null;
		try {
			con=dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	/**
	 * ��������
	 * @param conn
	 */
	
	public static void beginTransaction(Connection conn){
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			throw new ServiceException("Can not begin transaction", e);
		}
	}
	
	/**
	 * �ύ����
	 * @param conn
	 */
	
	public static void commit(Connection conn){
		try{
			conn.commit();
			conn.setAutoCommit(true);
		
		}catch(SQLException e){
			throw new ServiceException("Can not commit transaction", e);
		}
		
		
	}
	
	/**
	 * �ع�����
	 * 
	 * @param conn
	 */
	public static void rollback(Connection conn) {
		try {
			conn.rollback();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			throw new ServiceException("Can not rollback transaction", e);
		}
	}
	/**
	 * �ͷŻ����ӳ�
	 * 
	 * @param conn
	 */
	public static void closeConnection(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			throw new ServiceException("Can not close connection", e);
		}
	}

	/**
	 * �ر�statement����
	 * 
	 * @param stmt
	 */
	public static void closeStatement(ResultSet rs, Statement stmt) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			throw new ServiceException("Can not close statement", e);
		}
	}
	

}
