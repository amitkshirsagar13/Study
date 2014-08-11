package com.sample.base.startupmonitors;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class SampleWebApplicationMonitor extends TimerTask implements
		ServletContextListener {

	/**
	 * Initiate the log4j Logger for debugging purposes.
	 * 
	 */
	private static Logger log4j = Logger
			.getLogger(SampleWebApplicationMonitor.class);

	public static long prev = 0L;
	public static long currentSampleTime = 0L;
	private ServletContext context = null;
	private int iCounter = 0;
	private Timer timer = null;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		log4j.debug("Stop Monitor...");
		timer.cancel();
	}

	@Override
	public void contextInitialized(ServletContextEvent contextEvent) {
		log4j.debug("Start Monitor...");
		this.context = contextEvent.getServletContext();
		try {
			TimerTask tasknew = this;
			timer = new Timer();
			long period = 1 * 60 * 1000;
			timer.scheduleAtFixedRate(tasknew, 500, period);
			log4j.debug("ApplicationMonitor Started...");
		} catch (Exception e) {
			log4j.error("ApplicationMonitor schedule failed!!!");
			log4j.error(e.getMessage());
		}
	}

	@Override
	public void run() {
		log4j.debug("Monitoring Application...");
		Context initContext;

		Context envContext = null;
		DataSource ds = null;
		Connection conn = null;
		try {
			initContext = new InitialContext();

			envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/App1Ds");
			conn = ds.getConnection();

			ResultSet resultSet = conn.createStatement().executeQuery(
					"select name, email, relation from authors");
			while (resultSet.next()) {
				log4j.debug(resultSet.getString("name") + "|"
						+ resultSet.getString("email") + "|"
						+ resultSet.getString("relation"));
			}
		} catch (Exception e) {
			log4j.error(e.getMessage());
		} finally {
			try {
				if (!conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				log4j.debug("Error closing connection...");
			}
		}
	}

	/**
	 * Print object values by default. Add required attributes of the object.
	 */

	@Override
	public String toString() {
		return "SampleWebApplicationMonitor: ";
	}

}