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
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import com.MileStoneReporterTool.DataBeans.ApplicationReleaseData;
import com.MileStoneReporterTool.DataBeans.ApplicationReleaseLists;

/**
 * @author AmitC_Kshirsagar
 */
public class SQLOraRelDAO {

	public int addApplicationReleaseMethod(String applicationId, String pOC,
			String date) {
		GetDBConnection connectionObject = new GetDBConnection();
		Connection connection = connectionObject.getDBConnection();
		int intApplicationId = Integer.parseInt(applicationId);
		int applicationReleaseAdded = 2;
		try {
			PreparedStatement statement = connection
					.prepareStatement("insert into MileStone_ApplicationRelease values (null,"
							+ intApplicationId
							+ ",'"
							+ date
							+ "','"
							+ pOC
							+ "')");

			applicationReleaseAdded = statement.executeUpdate();
			applicationReleaseAdded = 1;

			ResultSet rs = connection.createStatement().executeQuery(
					"select max(ReleaseID) from MileStone_ApplicationRelease");

			String releaseID = null;
			while (rs.next()) {
				releaseID = String.valueOf(rs.getInt(1));
			}

			statement = connection
					.prepareStatement("insert into MileStone_ReleaseAllocation values (null,"
							+ releaseID + "," + pOC + ",1)");
			statement.executeUpdate();

			System.out.println("applicationReleaseAdded "
					+ applicationReleaseAdded);
			connection.close();
		} catch (Exception e) {
			applicationReleaseAdded = 0;
			System.out.println("SQLOraRelDAO.addApplicationReleaseMethod :"
					+ e.getMessage());
		}
		return applicationReleaseAdded;
	}

	public List listReleaseMethod() {
		GetDBConnection connectionObject = new GetDBConnection();
		Connection connection = connectionObject.getDBConnection();
		List releaseDataList = new ArrayList();
		try {
			PreparedStatement statement = connection
					.prepareStatement("select ReleaseID, ApplicationID, ReleaseName, POC from MileStone_ApplicationRelease order by ReleaseID");
			ResultSet releaseData = statement.executeQuery();
			ApplicationReleaseData releaseDataObject = null;
			while (releaseData.next()) {
				releaseDataObject = new ApplicationReleaseData();
				releaseDataObject.setReleaseId(String.valueOf(releaseData
						.getInt(1)));
				releaseDataObject.setApplicationID(String.valueOf(releaseData
						.getInt(2)));
				releaseDataObject.setReleaseName(releaseData.getString(3));
				releaseDataObject.setApplicationReleasePOC(releaseData
						.getString(4));
				releaseDataList.add(releaseDataObject);
			}
			connection.close();
		} catch (Exception e) {
			System.out.println("SQLOraRelDAO.addApplicationReleaseMethod :"
					+ e.getMessage());
		}
		return releaseDataList;
	}

	public List listReleaseMapMethod(List applicationList) {
		GetDBConnection connectionObject = new GetDBConnection();
		Connection connection = connectionObject.getDBConnection();
		List releaseMap = new ArrayList();
		try {
			String applicationID = null;

			Iterator applicationItorator = applicationList.iterator();
			
			while (applicationItorator.hasNext()) {
				List releaseDataList = new ArrayList();
				ApplicationReleaseLists application = (ApplicationReleaseLists)applicationItorator.next();
				applicationID = application.getApplicationId();
				String sql="select ReleaseID, ApplicationID, ReleaseName, POC from MileStone_ApplicationRelease where ApplicationID='"
				+ applicationID + "' order by ReleaseID";
				PreparedStatement statement = connection
						.prepareStatement(sql);
				System.out.println(sql);
				ResultSet releaseData = statement.executeQuery();
				ApplicationReleaseData releaseDataObject = null;
				while (releaseData.next()) {
					releaseDataObject = new ApplicationReleaseData();
					releaseDataObject.setReleaseId(String.valueOf(releaseData
							.getInt(1)));
					releaseDataObject.setApplicationID(String
							.valueOf(releaseData.getInt(2)));
					releaseDataObject.setReleaseName(releaseData.getString(3));
					releaseDataObject.setApplicationReleasePOC(releaseData
							.getString(4));
					releaseDataList.add(releaseDataObject);
					System.out.println(releaseData.getString(3));
				}
				
				releaseMap.add(releaseDataList);
			}
			connection.close();
		} catch (Exception e) {
			System.out.println("SQLOraRelDAO.addApplicationReleaseMethod :"
					+ e.getMessage());
		}
		return releaseMap;
	}
}
