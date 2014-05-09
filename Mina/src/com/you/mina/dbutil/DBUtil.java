package com.you.mina.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * @author you 
 * 本类为连接sqlserver的工具类，以及关闭连接方�?
 * 配合SQLUtil使用
 */
public class DBUtil {
	private static DataSource ds = new ComboPooledDataSource();
	
	// sqlserver 驱动
	private static final String SQLSERVER_DRIVER = "com.mysql.jdbc.Driver";
	
	// 数据库地�?rl 前面的为固定写法，后面的分别为ip地址：端口号,数据库名
//	private static final String SQLSERVER_URL = "jdbc:microsoft:sqlserver://localhost:1433;databaseName=WM";  //用微软的jar包（3个）的情�?
	private static final String SQLSERVER_URL = "jdbc:mysql://localhost:3306/iotclouddb";  //用普通jar包（1个）的时�?
	
	private static final String SQLSERVER_USERNAME = "root";// sqlserver 数据库用户名
	private static final String SQLSERVER_PASSWORD = "123456";// sqlserver 数据库密�?
	/**
	 * 获取SQLSERVER数据库连�?
	 * @return 数据库的连接
	 */
	public static Connection getSQLSERVERConnection() {

		Connection conn = null;
		try {
			//自动创建驱动程序的实例且自动调用DriverManager来注册它
			//Class.forName(SQLSERVER_DRIVER);
			//conn = DriverManager.getConnection(SQLSERVER_URL, SQLSERVER_USERNAME, SQLSERVER_PASSWORD);
			conn = ds.getConnection();
			System.out.println(conn);
		} /*catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/ catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;
	}


	/**
	 * 关闭数据库连�?针对有参数的查询情况
	 * @param rs 返回的查询结�?
	 * @param pstmt 
	 * @param conn
	 */
	public static void closeConn(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 关闭数据库连接重载，针对没有参数的查询情�?
	 * @param rs
	 * @param ps
	 * @param conn
	 */
	public static void closeConn(ResultSet rs, Statement ps, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 关闭数据库连接重载，针对更新语句情况
	 * @param pstmt
	 * @param conn
	 */
	public static void closeConn(PreparedStatement pstmt, Connection conn) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 关闭数据库连接重载，针对同时执行两条条更新语�?
	 * @param pstmt1
	 * @param pstmt2
	 * @param conn
	 */
	public static void closeConn(PreparedStatement pstmt1, PreparedStatement pstmt2, Connection conn) {
		if (pstmt1 != null) {
			try {
				pstmt1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstmt2 != null) {
			try {
				pstmt2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}