package com.system.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JdbcConMSS {
	private static final Logger LOG = LoggerFactory.getLogger(JdbcConMSS.class);
	private static String driver = null;

	static {
		driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		try {
			Class.forName(driver);
		} catch (Exception e) {
			LOG.error("加载数据库驱动异常", e);
		}
	}

	public static Connection getCon(String url, String userName, String password) throws ClassNotFoundException, SQLException {
		Connection con = DriverManager.getConnection(url, userName, password);
		return con;
	}

	public static ResultSet getRs(PreparedStatement ps) throws SQLException {
		ResultSet rs = ps.executeQuery();
		return rs;
	}

	public static PreparedStatement getPs(Connection con, String sql) throws SQLException {
		PreparedStatement ps = con.prepareStatement(sql);
		return ps;
	}

	public static void closeCon(ResultSet rs, PreparedStatement ps, Statement stmt, Connection con) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			LOG.error("关闭连接异常", e);
		}
	}
}
