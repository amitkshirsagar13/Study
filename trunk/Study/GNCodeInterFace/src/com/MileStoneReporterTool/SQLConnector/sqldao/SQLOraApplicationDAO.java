/*
 *  This file was created by AmitC_Kshirsagar
 *
 */
package com.MileStoneReporterTool.SQLConnector.sqldao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.MileStoneReporterTool.DataBeans.ApplicationReleaseData;
import com.MileStoneReporterTool.DataBeans.ApplicationReleaseLists;

/**
 * @author AmitC_Kshirsagar
 */
public class SQLOraApplicationDAO {

	public int addApplicationMethod(String applicationName, String owner) {
		GetDBConnection connectionObject = new GetDBConnection();
		Connection connection = connectionObject.getDBConnection();
		System.out.println(" Getting Application Name " + applicationName
				+ " Owner " + owner);
		int applicationAdded = 2;
		try {
			PreparedStatement statement = connection
					.prepareStatement("insert into MileStone_Application values (null,'"
							+ applicationName + "','" + owner + "')");
			applicationAdded = statement.executeUpdate();
			applicationAdded = 1;
			connection.close();
		} catch (Exception e) {
			applicationAdded = 0;
			System.out.println("SQLOraApplicationDAO.addApplicationMethod :"
					+ e.getMessage());
		}
		return applicationAdded;
	}

	public List listApplicationMethod() {
		GetDBConnection connectionObject = new GetDBConnection();
		Connection connection = connectionObject.getDBConnection();
		List list = new ArrayList(50);
		try {
			Statement statement = connection.createStatement();

			ResultSet rs = statement
					.executeQuery("select * from MileStone_Application order by APPLICATIONNAME");

			while (rs.next()) {
				String applicationId = rs.getString(1);
				String applicationName = rs.getString(2);
				String owner = rs.getString(3);

				ApplicationReleaseLists application = new ApplicationReleaseLists();
				application.setApplicationId(applicationId);
				application.setApplicationName(applicationName);
				application.setOwner(owner);
				list.add(application);
			}
			connection.close();
		} catch (Exception e) {
			System.out.println("SQLOraApplicationDAO.listApplicationMethod :"
					+ e.getMessage());
		}
		return list;
	}
}
