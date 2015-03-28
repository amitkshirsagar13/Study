package com.tcp.store;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;

import com.tcp.db.JDBCUtil;

/**
 * public class UserCustomerMap {
 * 
 * }
 */

public class UserCustomerMap extends HashMap<Long, UserCustomer> {

	/**
	 * Initiate the log4j Logger for debugging purposes.
	 * 
	 */
	private static Logger log4j = Logger.getLogger(UserCustomerMap.class);

	/**
	 * Print object values by default. Add required attributes of the object.
	 */

	@Override
	public String toString() {
		return "UserCustomerMap Size:" + this.size();
	}

	public UserCustomer getUserCustomer(Long customerId) {
		return this.get(customerId);
	}

	public void addUserCustomer(UserCustomer userCustomer) {
		if (!this.containsKey(userCustomer.getCustomerId())) {
			this.put(userCustomer.getCustomerId(), userCustomer);
		}
	}

	public void updateUserCustomer(UserCustomer userCustomer) {
		if (this.containsKey(userCustomer.getCustomerId())) {
			this.get(userCustomer.getCustomerId()).setMissionCustomer(
					userCustomer.getCustomerNumber(),
					userCustomer.getCustomerName());
		}
	}

	public void populateUserCustomerFromTcp() {
		String sql = "select distinct customerId, customerNumber, customerName from usercustomers";
		setSql(sql);
		Connection conn = null;
		ResultSet resultSet = null;

		try {
			conn = JDBCUtil.getTcpConnection();
			resultSet = executeQuery(conn);

			log4j.debug("Populate Tcp UserCustomers");
			while (resultSet.next()) {
				this.addUserCustomer(new UserCustomer(resultSet.getLong(1),
						resultSet.getLong(2), resultSet.getString(3)));
			}
		} catch (SQLException e) {
			log4j.error(e.getMessage());
		} catch (Exception e) {
			log4j.error(e.getMessage());
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (Throwable t) {
				log4j.error(t.getMessage());
			}
		}

	}

	public void populateUserCustomerFromMission() {
		String sql = "select distinct cusptyid, cusnum, cusnam from pycus where cusptyid in ";

		Set<Long> keySet = this.keySet();
		ArrayList<Long> ptyIdList = new ArrayList<Long>();
		ptyIdList.addAll(keySet);

		StringBuffer buf = new StringBuffer("(");
		Long ptyId = null;
		for (int index = ptyIdList.size() - 1; index >= 0; index--) {
			ptyId = ptyIdList.get(index);
			buf.append(ptyId);
			if (index > 0) {
				buf.append(", ");
			}
		}

		buf.append(")");

		sql = sql + buf.toString();

		setSql(sql);
		Connection conn = null;
		ResultSet resultSet = null;

		try {
			conn = JDBCUtil.getMissionConnection();
			resultSet = executeQuery(conn);

			while (resultSet.next()) {
				this.updateUserCustomer(new UserCustomer(resultSet
						.getLong("cusptyid"), resultSet.getLong("cusnum"),
						resultSet.getString("cusnam")));
			}
		} catch (SQLException e) {
			log4j.error(e.getMessage());
		} catch (Exception e) {
			log4j.error(e.getMessage());
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (Throwable t) {
				log4j.error(t.getMessage());
			}
		}

	}

	public ResultSet executeQuery(Connection conn) {
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = conn.createStatement();
			resultSet = statement.executeQuery(getSql());
		} catch (Exception e) {
			log4j.error(e.getMessage());
		}

		return resultSet;
	}

	private String sql = null;

	private String getSql() {
		return sql;
	}

	private void setSql(String sql) {
		this.sql = sql;
	}

	public void updateUserCustomerInTcp() {
		// Statement statement = null;
		PreparedStatement statement = null;
		Connection conn = null;
		try {
			conn = JDBCUtil.getTcpConnection();
			// statement = conn.createStatement();
			statement = conn
					.prepareStatement("update usercustomers set customernumber=? , customername = ? where customerId=?");
			// statement.addBatch("SET SQL_SAFE_UPDATES = 0");

			Set<Long> keySet = this.keySet();
			for (Iterator iterator = keySet.iterator(); iterator.hasNext();) {
				Long customerId = (Long) iterator.next();
				statement.setLong(1, this.get(customerId).getCustomerNumber());
				statement.setString(2, this.get(customerId).getCustomerName());
				statement.setLong(3, this.get(customerId).getCustomerId());
				statement.addBatch();
			}

			int[] recordsAffected = statement.executeBatch();

			log4j.debug("Records Updated:" + recordsAffected.length + "/"
					+ this.size());
		} catch (Exception e) {
			log4j.error(e.getMessage());
		} finally {
			if (statement != null)
				try {
					statement.close();
				} catch (SQLException e) {
				}
		}

	}
}