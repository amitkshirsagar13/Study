package com.tcp.db;

import java.sql.Connection;

import org.apache.log4j.Logger;

import com.tcp.util.TcpMissionUtil;

public class JDBCUtil {

	private static Logger log4j = Logger.getLogger(JDBCUtil.class);

	public static Connection getTcpConnection() throws Exception {
		return DBUtilities.getConnection(TcpMissionUtil.getProperties("tcp"));
	}

	public static Connection getMissionConnection() throws Exception {
		return DBUtilities.getConnection(TcpMissionUtil
				.getProperties("mission"));
	}

	public static Connection getDb2Connection() throws Exception {
		return DBUtilities.getConnection(TcpMissionUtil.getProperties("db2"));
	}

}
