/*
 *  This file was created by AmitC_Kshirsagar
 *
 */
package com.MileStoneReporterTool.SQLConnector.sqldao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpSession;

import com.MileStoneReporterTool.ActionFiles.UpdateSessionAction;
import com.MileStoneReporterTool.DataBeans.LiveDataObject;
import com.MileStoneReporterTool.DataBeans.Message;
import com.MileStoneReporterTool.DataBeans.UserData;

/**
 * @author AmitC_Kshirsagar
 */
public class SQLOraUserDAO {

	public UserData loginMethod(String userID, String passWord) {
		GetDBConnection connectionObject = new GetDBConnection();
		Connection connection = connectionObject.getDBConnection();
		UserData userData = new UserData();
		try {

			Statement statement = connection.createStatement();
			ResultSet rs = statement
					.executeQuery("select * from MileStone_Login where USERID='"
							+ userID + "' and PASSWORD='" + passWord + "'");

			if (rs.next()) {
				userData.setUserID(rs.getString(1));
				userData.setUserName(rs.getString(2));
				userData.setRole(rs.getString(4));
			} else {
				Message.message="<br>No user found with name <font color=red><b>'" +
						userID+"'</b></font>. Please check the name.";
				userData.setUserID("-1");
				rs.close();

			}
			connection.close();
		} catch (Exception e) {
			Message.message="<br>SQLOraUserDAO.loginMethod :" + e.getMessage();
			System.out.println("SQLOraUserDAO.loginMethod :" + e.getMessage());
		}
		return userData;
	}

	public int addUserDetailsMethod(String userID, String passWord, String role) {
		int userDetailsAdded = 2;
		String message;
		int roleNum = Integer.parseInt(role);
		GetDBConnection connectionObject = new GetDBConnection();
		Connection connection = connectionObject.getDBConnection();
		try {
			PreparedStatement statement = connection
					.prepareStatement("insert into MileStone_Login (userid,password,role ) values('"
							+ userID + "','Info'," + roleNum + ")");
			userDetailsAdded = statement.executeUpdate();
			connection.close();
		} catch (Exception e) {
			System.out.println("SQLOraUserDAO.addUserDetailsMethod :"
					+ e.getMessage());
			message = "<tr><td>Error executing the SQL statement: <br>"
					+ e.getMessage() + "</tr></td>";
		}
		return userDetailsAdded;
	}

	public List listUsersMethod() {
		GetDBConnection connectionObject = new GetDBConnection();
		Connection connection = connectionObject.getDBConnection();
		List list = new ArrayList(50);
		try {
			Statement statement = connection.createStatement();

			ResultSet rs = statement
					.executeQuery("select * from MileStone_Login order by userID");

			while (rs.next()) {
				String userName = rs.getString(2);
				String passWord = rs.getString(3);
				String role = rs.getString(4);
				int userIdNum = rs.getInt(1);
				String userId = String.valueOf(userIdNum);

				UserData userList = new UserData();
				userList.setUserID(userId);
				userList.setUserName(userName);
				userList.setRole(role);
				list.add(userList);
			}
			connection.close();
		} catch (Exception e) {
			System.out
					.println("SQLOraUserDAO.listUsersMethod " + e.getMessage());
		}
		return list;
	}
	
	public List listUsersMethod(String qName, String qNameValue) {
		GetDBConnection connectionObject = new GetDBConnection();
		Connection connection = connectionObject.getDBConnection();
		List list = new ArrayList(50);
		try {
			Statement statement = connection.createStatement();

			ResultSet rs = statement
					.executeQuery("select "+qName+" from MileStone_Login where "+qName+" like '"+qNameValue+"%' order by "+qName);
			
			while (rs.next()) {
				String qValue = rs.getString(1);
				LiveDataObject liveData = new LiveDataObject();
				liveData.setDataValue(qValue);
				list.add(liveData);
			}
			connection.close();
		} catch (Exception e) {
			System.out
					.println("SQLOraUserDAO.listUsersMethod " + e.getMessage());
		}
		return list;
	}
	
	public int updateUsers(String userId, String userName, String role) {
		
		GetDBConnection connectionObject = new GetDBConnection();
		Connection connection = connectionObject.getDBConnection();
		List list = new ArrayList(50);
		int updateStatus = 0;
		try {
			Statement statement = connection.createStatement();
			String userUpdate = "update MileStone_Login set userid='"+userName+"' , role="+Integer.parseInt(role)+" where employeeID='"+userId+"'";
			
			System.out.println(userUpdate);
			
			updateStatus = statement
					.executeUpdate(userUpdate);

			connection.close();
		} catch (Exception e) {
			System.out
					.println("SQLOraUserDAO.updateUsers " + e.getMessage());
		}
		return updateStatus;
	}
	
	public List listReleaseUsers(String releaseId) {
		GetDBConnection connectionObject = new GetDBConnection();
		Connection connection = connectionObject.getDBConnection();
		List list = new ArrayList(50);
		try {
			Statement statement = connection.createStatement();
			String getReleaseAllocation = "select MileStone_ReleaseAllocation.AllocationID, MileStone_ReleaseAllocation.ReleaseId," +
			" MileStone_ReleaseAllocation.UserID, MileStone_ReleaseAllocation.POC, MileStone_Login.userid, " +
			"MileStone_ApplicationRelease.ReleaseName, MileStone_Application.ApplicationName " +
			"from MileStone_ReleaseAllocation, MileStone_Login, MileStone_ApplicationRelease, MileStone_Application " +
			"where MileStone_ReleaseAllocation.UserID = MileStone_Login.employeeID " +
			"and MileStone_ApplicationRelease.ReleaseID=MileStone_ReleaseAllocation.ReleaseID " +
			"and MileStone_ApplicationRelease.ApplicationID=MileStone_Application.ApplicationID " +
			"and MileStone_ReleaseAllocation.ReleaseID="+releaseId+" order by MileStone_ReleaseAllocation.UserID";
			
			ResultSet rs = statement
					.executeQuery(getReleaseAllocation);
			
			while (rs.next()) {
				String allocationId = String.valueOf(rs.getInt(1));
				releaseId = String.valueOf(rs.getInt(2));
				String userId = String.valueOf(rs.getInt(3));
				String poc = String.valueOf(rs.getInt(4));
				String userName = rs.getString(5);
				String releaseName = rs.getString(6);
				String applicationName = rs.getString(7);
				UserData userData = new UserData();
				userData.setAllocationId(allocationId);
				userData.setUserID(userId);
				userData.setUserName(userName);
				userData.setPoc(poc);
				userData.setReleaseId(releaseId);
				userData.setReleaseName(releaseName);
				userData.setApplicationName(applicationName);
				list.add(userData);
			}
			connection.close();
		} catch (Exception e) {
			System.out
					.println("SQLOraUserDAO.listReleaseUsers " + e.getMessage());
		}
		return list;
	}
	
	public int allocateUserForRelease(String releaseID, String userID) {
		GetDBConnection connectionObject = new GetDBConnection();
		Connection connection = connectionObject.getDBConnection();
		try {
			connection.createStatement().execute("insert into MileStone_ReleaseAllocation values(null,"+releaseID+","+userID+",0)");
		}catch(Exception e) {
			
		}
		return 0;
	}
	
	public int allocatePOCForRelease(String releaseID, String userID) {
		GetDBConnection connectionObject = new GetDBConnection();
		Connection connection = connectionObject.getDBConnection();
		try {
			System.out.println("Adding new POC");
			connection.createStatement().execute("insert into MileStone_ReleaseAllocation values(null,"+releaseID+","+userID+",1)");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return 0;
	}
	
	public int cleanAllocateUserForRelease(String releaseID) {
		GetDBConnection connectionObject = new GetDBConnection();
		Connection connection = connectionObject.getDBConnection();
		try {
			connection.createStatement().execute("delete from MileStone_ReleaseAllocation where ReleaseID="+releaseID+" " +
					"and POC=0");
		}catch(Exception e) {
			
		}
		return 0;
	}
}