/*
 *  This file was created by AmitC_Kshirsagar
 *
 */
package com.MileStoneReporterTool.SQLConnector.sqldao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.MileStoneReporterTool.DataBeans.GNCodeCourtData;
import com.MileStoneReporterTool.DataBeans.GNCodeJVData;
import com.MileStoneReporterTool.DataBeans.GNCodeLegacyJVData;
import com.MileStoneReporterTool.DataBeans.GNCodeMappingInfoData;
import com.MileStoneReporterTool.DataBeans.LiveDataObject;
import com.MileStoneReporterTool.DataBeans.LiveDataStorage;
import com.MileStoneReporterTool.Util.CustomUtil;

/**
 * @author AmitC_Kshirsagar
 */
public class SQLOraGNCodeDAO {

	/*
	 * public List listCourtNamesMethod(String qName, String qNameValue) {
	 * GetDBConnection connectionObject = new GetDBConnection(); Connection
	 * connection = connectionObject.getDBConnection(); List list = new
	 * ArrayList(50); try { Statement statement = connection.createStatement();
	 * 
	 * ResultSet rs = statement
	 * .executeQuery("select * from GNCODE_CASELAW_LOOKUP where "
	 * +qName+" like '"+qNameValue+"%' order by "+qName);
	 * //System.out.println("select * from GNCODE_CASELAW_LOOKUP where "
	 * +qName+" like '"+qNameValue+"%' order by "+qName); while (rs.next()) {
	 * int id = rs.getInt(1); String court_name = rs.getString(2); String
	 * normalized_name = rs.getString(4); GNCodeCourtData liveData = new
	 * GNCodeCourtData();
	 * 
	 * //System.out.println(id+":"+court_name+":"+normalized_name);
	 * 
	 * liveData.setId(id); liveData.setCourt_name(court_name);
	 * liveData.setNormalized_court(normalized_name); list.add(liveData); } if
	 * (list.size()==0){ GNCodeCourtData liveData = new GNCodeCourtData();
	 * qNameValue = qNameValue.replace("''", "\\'");
	 * liveData.setCourt_name(qNameValue);
	 * liveData.setNormalized_court(CustomUtil.getNormalizedString(qNameValue));
	 * list.add(liveData); } connection.close(); } catch (Exception e) {
	 * System.out .println("SQLOraGNCodeDAO.listCourtNamesMethod " +
	 * e.getMessage()); } return list; }
	 */

	public List listCourtNamesMethod(String qName, String qNameValue) {

		if (LiveDataStorage.CourtMap == null) {
			updateLiveDataStorage();
		}
		List list = new ArrayList(50);
		qNameValue = qNameValue.toUpperCase();

		Set keySet = LiveDataStorage.CourtMap.keySet();
		System.out.println("KeySize: " + keySet.size());
		Iterator iter = keySet.iterator();
		String key = null;
		GNCodeCourtData courtData = null;
		int counter = 0;
		while (iter.hasNext()) {
			key = (String) iter.next();
			courtData = (GNCodeCourtData) LiveDataStorage.CourtMap.get(key);
			if (qName.equalsIgnoreCase("court_name") && courtData != null) {
				if (courtData.getCourt_name().toUpperCase().startsWith(
						qNameValue)) {
					list.add(courtData);
					counter++;
					if (counter > 5) {
						break;
					}
				}
			}
		}
		if (list.size() == 0) {
			GNCodeCourtData liveData = new GNCodeCourtData();
			qNameValue = qNameValue.replace("''", "\\'");
			liveData.setCourt_name(qNameValue);
			liveData.setNormalized_court(CustomUtil
					.getNormalizedString(qNameValue));
			list.add(liveData);
		}
		return list;
	}

	/*
	 * public List listJVMethod(String qName1, String qNameValue1, String
	 * qName2, String qNameValue2, String qName3, String qNameValue3) {
	 * GetDBConnection connectionObject = new GetDBConnection(); Connection
	 * connection = connectionObject.getDBConnection(); List list = new
	 * ArrayList(50); try { Statement statement = connection.createStatement();
	 * 
	 * ResultSet rs = statement
	 * .executeQuery("select * from GNCODE_JV_LOOKUP where "
	 * +qName1+" like '"+qNameValue1+"%' " +
	 * "and "+qName2+" like '"+qNameValue2+
	 * "%' and "+qName3+" like '"+qNameValue3+"%'");
	 * System.out.println("select * from GNCODE_JV_LOOKUP where "
	 * +qName1+" like '"+qNameValue1+"%' " +
	 * "and "+qName2+" like '"+qNameValue2+
	 * "%' and "+qName3+" like '"+qNameValue3+"%'"); while (rs.next()) { int id
	 * = rs.getInt(1); String state_name = rs.getString(2); String city_name =
	 * rs.getString(3); String county_name = rs.getString(4); String
	 * normalized_name = rs.getString(5); GNCodeJVData liveData = new
	 * GNCodeJVData();
	 * 
	 * //System.out.println(id+":"+court_name+":"+normalized_name);
	 * 
	 * liveData.setId(id); liveData.setState_name(state_name);
	 * liveData.setCity_name(city_name); liveData.setCounty_name(county_name);
	 * liveData.setNormalized_name(normalized_name); list.add(liveData); } if
	 * (list.size()==0){ GNCodeJVData liveData = new GNCodeJVData(); qNameValue1
	 * = qNameValue1.replace("''", "\\'"); liveData.setState_name(qNameValue1);
	 * qNameValue2 = qNameValue2.replace("''", "\\'");
	 * liveData.setCity_name(qNameValue2); qNameValue3 =
	 * qNameValue3.replace("''", "\\'"); liveData.setCounty_name(qNameValue3);
	 * liveData
	 * .setNormalized_name(CustomUtil.getNormalizedString(qNameValue3+qNameValue2
	 * +qNameValue1)); list.add(liveData); } connection.close(); } catch
	 * (Exception e) { System.out .println("SQLOraGNCodeDAO.listJVMethod " +
	 * e.getMessage()); } return list; }
	 */

	public List listJVMethod(String qName1, String qNameValue1, String qName2,
			String qNameValue2, String qName3, String qNameValue3) {

		qNameValue1 = qNameValue1.toUpperCase();
		qNameValue2 = qNameValue2.toUpperCase();
		qNameValue3 = qNameValue3.toUpperCase();

		if (LiveDataStorage.JVMap == null) {
			updateLiveDataStorage();
		}
		List list = new ArrayList(50);

		Set keySet = LiveDataStorage.JVMap.keySet();
		System.out.println("KeySize: " + keySet.size());
		Iterator iter = keySet.iterator();
		String key = null;
		GNCodeJVData courtData = null;
		boolean court = false, city = false, state = false;
		int counter = 0;
		while (iter.hasNext()) {
			key = (String) iter.next();
			courtData = (GNCodeJVData) LiveDataStorage.JVMap.get(key);

			if (qNameValue1.equals("")
					|| courtData.getCounty_name() != null
					&& courtData.getCounty_name().toUpperCase().startsWith(
							qNameValue1)) {
				court = true;
			} else {
				court = false;
			}

			if (qNameValue2.equals("")
					|| courtData.getCity_name() != null
					&& courtData.getCity_name().toUpperCase().startsWith(
							qNameValue2)) {
				city = true;
			} else {
				city = false;
			}

			if (qNameValue3.equals("")
					|| courtData.getState_name() != null
					&& courtData.getState_name().toUpperCase().startsWith(
							qNameValue3)) {
				state = true;
			} else {
				state = false;
			}

			System.out.println(court + ":" + city + ":" + state);
			if (court && city && state) {
				list.add(courtData);
				counter++;
				if (counter > 5) {
					break;
				}
			}

		}

		if (list.size() == 0) {
			GNCodeJVData liveData = new GNCodeJVData();
			qNameValue3 = qNameValue3.replace("''", "\\'");
			liveData.setState_name(qNameValue3);
			qNameValue2 = qNameValue2.replace("''", "\\'");
			liveData.setCity_name(qNameValue2);
			qNameValue1 = qNameValue1.replace("''", "\\'");
			liveData.setCounty_name(qNameValue1);
			liveData.setNormalized_name(CustomUtil
					.getNormalizedString(qNameValue1 + qNameValue2
							+ qNameValue3));
			list.add(liveData);
		}
		return list;
	}

	public List listLegacyJVMethod(String qName1, String qNameValue1,
			String qName2, String qNameValue2, String qName3,
			String qNameValue3, String qName4, String qNameValue4,
			String qName5, String qNameValue5, String qName6, String qNameValue6) {

		qNameValue1 = qNameValue1.toUpperCase();
		qNameValue2 = qNameValue2.toUpperCase();
		qNameValue3 = qNameValue3.toUpperCase();
		qNameValue4 = qNameValue4.toUpperCase();
		qNameValue5 = qNameValue5.toUpperCase();
		qNameValue6 = qNameValue6.toUpperCase();

		if (LiveDataStorage.LegacyMap == null) {
			updateLiveDataStorage();
		}
		List list = new ArrayList(50);

		Set keySet = LiveDataStorage.LegacyMap.keySet();
		System.out.println("KeySize: " + keySet.size());
		Iterator iter = keySet.iterator();
		String key = null;
		GNCodeLegacyJVData courtData = null;
		boolean court = false, county = false, county1 = false, parish = false, publication = false, state = false;
		int counter = 0;
		while (iter.hasNext()) {
			key = (String) iter.next();
			courtData = (GNCodeLegacyJVData) LiveDataStorage.LegacyMap.get(key);

			if (qNameValue1.equals("")
					|| courtData.getCourt_name() != null
					&& courtData.getCourt_name().toUpperCase().startsWith(
							qNameValue1)) {
				court = true;
			} else {
				court = false;
			}

			if (qNameValue2.equals("")
					|| courtData.getCounty_name() != null
					&& courtData.getCounty_name().toUpperCase().startsWith(
							qNameValue2)) {
				county = true;
			} else {
				county = false;
			}

			if (qNameValue3.equals("")
					|| courtData.getCounty1_name() != null
					&& courtData.getCounty1_name().toUpperCase().startsWith(
							qNameValue3)) {
				county1 = true;
			} else {
				county1 = false;
			}

			if (qNameValue4.equals("")
					|| courtData.getParish_name() != null
					&& courtData.getParish_name().toUpperCase().contains(
							qNameValue4)) {
				parish = true;
			} else {
				parish = false;
			}

			if (qNameValue5.equals("")
					|| courtData.getPublication_name() != null
					&& courtData.getPublication_name().toUpperCase().contains(
							qNameValue5)) {
				publication = true;
			} else {
				publication = false;
			}

			if (qNameValue6.equals("")
					|| courtData.getState_name() != null
					&& courtData.getState_name().toUpperCase().contains(
							qNameValue6)) {
				state = true;
			} else {
				state = false;
			}

			System.out.println(court + ":" + county + ":" + state);
			if (court && county && county1 && parish && publication && state) {
				list.add(courtData);
				counter++;
				if (counter > 5) {
					break;
				}
			}

		}

		if (list.size() == 0) {
			GNCodeLegacyJVData liveData = new GNCodeLegacyJVData();
			qNameValue6 = qNameValue6.replace("''", "\\'");
			liveData.setState_name(qNameValue6);
			qNameValue1 = qNameValue1.replace("''", "\\'");
			liveData.setCourt_name(qNameValue1);
			qNameValue2 = qNameValue2.replace("''", "\\'");
			liveData.setCounty_name(qNameValue2);
			qNameValue3 = qNameValue3.replace("''", "\\'");
			liveData.setCounty1_name(qNameValue3);
			qNameValue4 = qNameValue4.replace("''", "\\'");
			liveData.setParish_name(qNameValue4);
			// remove Trademark and publication specific chars.
			qNameValue5 = qNameValue5.replace("''", "\\'");
			qNameValue5 = qNameValue5.toUpperCase().replaceFirst("COPYRIGHT",
					"");
			qNameValue5 = qNameValue5.toUpperCase().replaceFirst("\\(C\\)", "");

			liveData.setPublication_name(qNameValue5);
			liveData.setNormalized_name(CustomUtil
					.getNormalizedString(qNameValue1 + qNameValue2
							+ qNameValue3 + qNameValue4 + qNameValue5
							+ qNameValue6));
			list.add(liveData);
		}
		return list;
	}

	/*
	 * public List listGNCodeMappingInfoDataMethod(String qName, String
	 * qNameValue) { GetDBConnection connectionObject = new GetDBConnection();
	 * Connection connection = connectionObject.getDBConnection(); List list =
	 * new ArrayList(50); try { Statement statement =
	 * connection.createStatement();
	 * 
	 * ResultSet rs = statement
	 * .executeQuery("select distinct "+qName+" from gncode_definition_info where "
	 * +qName+" like '"+qNameValue+"%' order by "+qName);
	 * 
	 * while (rs.next()) { String qValue = rs.getString(1); LiveDataObject
	 * liveData = new LiveDataObject(); liveData.setDataValue(qValue);
	 * list.add(liveData); } if (list.size()==0){ LiveDataObject liveData = new
	 * LiveDataObject(); qNameValue = qNameValue.replace("''", "\\'");
	 * liveData.setDataValue(qNameValue); list.add(liveData); }
	 * connection.close(); } catch (Exception e) { System.out
	 * .println("SQLOraGNCodeDAO.listGNCodeMappingInfoDataMethod " +
	 * e.getMessage()); } return list; }
	 */

	public List listGNCodeMappingInfoDataMethod(String qName, String qNameValue) {
		if (LiveDataStorage.CourtMap == null) {
			updateLiveDataStorage();
		}
		List list = new ArrayList(50);

		ArrayList filterData = null;
		if (qName.equalsIgnoreCase("state")) {
			filterData = LiveDataStorage.State;
		} else if (qName.equalsIgnoreCase("system")) {
			filterData = LiveDataStorage.System;
		} else if (qName.equalsIgnoreCase("court_type")) {
			filterData = LiveDataStorage.CourtType;
		} else if (qName.equalsIgnoreCase("subject_matter")) {
			filterData = LiveDataStorage.SubjectMatter;
		} else if (qName.equalsIgnoreCase("location")) {
			filterData = LiveDataStorage.Location;
		} else if (qName.equalsIgnoreCase("gncode_value")) {
			filterData = LiveDataStorage.GNCodeValueList;
		}

		int count = filterData.size();
		String data = null;
		int counter = 0;
		qNameValue = qNameValue.toUpperCase();
		for (int i = 0; i < count; i++) {
			data = (String) filterData.get(i);
			if (data.toUpperCase().startsWith(qNameValue)) {
				LiveDataObject liveData = new LiveDataObject();
				liveData.setDataValue(data);
				list.add(liveData);
				counter++;
				if (counter > 5) {
					break;
				}
			}
		}
		if (list.size() == 0) {
			LiveDataObject liveData = new LiveDataObject();
			qNameValue = qNameValue.replace("''", "\\'");
			liveData.setDataValue(qNameValue);
			list.add(liveData);
		}
		return list;
	}

	public GNCodeMappingInfoData getGNCodeInfoFromNormalizedNameMethod(
			String qName, String table) {
		GetDBConnection connectionObject = new GetDBConnection();
		Connection connection = connectionObject.getDBConnection();
		GNCodeMappingInfoData liveData = null;
		try {
			Statement statement = connection.createStatement();
			System.out.println();
			System.out
					.println("select gmi.gncode_id,gmi.GNCODE_VALUE,gmi.STATE,gmi.COURT_TYPE,gmi.SYSTEM,"
							+ "gmi.SUBJECT_MATTER,gmi.LOCATION,gmi.COURT_LEVEL,gmi.COURT_LONG_NAME,gmi.COURT_SHORT_NAME  "
							+ "from GNCODE_"
							+ table
							+ "_LOOKUP gm, gncode_definition_info gmi where gm.normalized_court='"
							+ qName + "' " + "and gm.gncode_id=gmi.gncode_id");
			ResultSet rs = statement
					.executeQuery("select gmi.gncode_id,gmi.GNCODE_VALUE,gmi.STATE,gmi.COURT_TYPE,gmi.SYSTEM,"
							+ "gmi.SUBJECT_MATTER,gmi.LOCATION,gmi.COURT_LEVEL,gmi.COURT_LONG_NAME,gmi.COURT_SHORT_NAME  "
							+ "from GNCODE_"
							+ table
							+ "_LOOKUP gm, gncode_definition_info gmi where gm.normalized_court='"
							+ qName + "' " + "and gm.gncode_id=gmi.gncode_id");

			while (rs.next()) {
				int gncodeId = rs.getInt(1);
				String gncodeValue = rs.getString(2);
				String state = rs.getString(3);
				String courtType = rs.getString(4);
				String system = rs.getString(5);
				String subjectMatter = rs.getString(6);
				String location = rs.getString(7);
				String courtLevel = rs.getString(8);
				String courtLongName = rs.getString(9);
				String courtShortName = rs.getString(10);

				liveData = new GNCodeMappingInfoData();

				liveData.setGncode_id(gncodeId);
				liveData.setGncode_value(gncodeValue);
				liveData.setState(state);
				liveData.setCourt_type(courtType);
				liveData.setSystem(system);
				liveData.setSubject_matter(subjectMatter);
				liveData.setLocation(location);
				liveData.setCourt_level(courtLevel);
				liveData.setCourt_long_name(courtLongName);
				liveData.setCourt_short_name(courtShortName);
				liveData.setJsonObject();
			}
			connection.close();
		} catch (Exception e) {
			System.out
					.println("SQLOraGNCodeDAO.getGNCodeInfoFromNormalizedNameMethod "
							+ e.getMessage());
		}
		return liveData;
	}

	public GNCodeMappingInfoData getGNCodeInfoFromGNCodeIDMethod(String qName) {
		GetDBConnection connectionObject = new GetDBConnection();
		Connection connection = connectionObject.getDBConnection();
		GNCodeMappingInfoData liveData = null;
		try {
			Statement statement = connection.createStatement();

			ResultSet rs = statement
					.executeQuery("select gncode_id,GNCODE_VALUE,STATE,COURT_TYPE,SYSTEM,SUBJECT_MATTER,LOCATION,COURT_LEVEL,COURT_LONG_NAME,COURT_SHORT_NAME  from gncode_definition_info where gncode_id='"
							+ qName + "'");
			// System.out.println("select gncode_id,GNCODE_VALUE,STATE,COURT_TYPE,SYSTEM,SUBJECT_MATTER,LOCATION,COURT_LEVEL,COURT_LONG_NAME,COURT_SHORT_NAME  from gncode_definition_info where gncode_id='"+qName+"'");
			while (rs.next()) {
				int gncodeId = rs.getInt(1);
				String gncodeValue = rs.getString(2);
				String state = rs.getString(3);
				String courtType = rs.getString(4);
				String system = rs.getString(5);
				String subjectMatter = rs.getString(6);
				String location = rs.getString(7);
				String courtLevel = rs.getString(8);
				String courtLongName = rs.getString(9);
				String courtShortName = rs.getString(10);

				liveData = new GNCodeMappingInfoData();

				liveData.setGncode_id(gncodeId);
				liveData.setGncode_value(gncodeValue);
				liveData.setState(state);
				liveData.setCourt_type(courtType);
				liveData.setSystem(system);
				liveData.setSubject_matter(subjectMatter);
				liveData.setLocation(location);
				liveData.setCourt_level(courtLevel);
				liveData.setCourt_long_name(courtLongName);
				liveData.setCourt_short_name(courtShortName);
				liveData.setJsonObject();
			}
			connection.close();
		} catch (Exception e) {
			System.out
					.println("SQLOraGNCodeDAO.getGNCodeInfoFromGNCodeIDMethod "
							+ e.getMessage());
		}
		return liveData;
	}

	public GNCodeMappingInfoData getGNCodeInfoFromGNCodeValueMethod(String qName) {
		GetDBConnection connectionObject = new GetDBConnection();
		Connection connection = connectionObject.getDBConnection();
		GNCodeMappingInfoData liveData = null;
		try {
			Statement statement = connection.createStatement();

			ResultSet rs = statement
					.executeQuery("select gncode_id,GNCODE_VALUE,STATE,COURT_TYPE,SYSTEM,SUBJECT_MATTER,LOCATION,COURT_LEVEL,COURT_LONG_NAME,COURT_SHORT_NAME  from gncode_definition_info where gncode_value='"
							+ qName + "'");
			// System.out.println("select gncode_id,GNCODE_VALUE,STATE,COURT_TYPE,SYSTEM,SUBJECT_MATTER,LOCATION,COURT_LEVEL,COURT_LONG_NAME,COURT_SHORT_NAME  from gncode_definition_info where gncode_id='"+qName+"'");
			while (rs.next()) {
				int gncodeId = rs.getInt(1);
				String gncodeValue = rs.getString(2);
				String state = rs.getString(3);
				String courtType = rs.getString(4);
				String system = rs.getString(5);
				String subjectMatter = rs.getString(6);
				String location = rs.getString(7);
				String courtLevel = rs.getString(8);
				String courtLongName = rs.getString(9);
				String courtShortName = rs.getString(10);

				liveData = new GNCodeMappingInfoData();

				liveData.setGncode_id(gncodeId);
				liveData.setGncode_value(gncodeValue);
				liveData.setState(state);
				liveData.setCourt_type(courtType);
				liveData.setSystem(system);
				liveData.setSubject_matter(subjectMatter);
				liveData.setLocation(location);
				liveData.setCourt_level(courtLevel);
				liveData.setCourt_long_name(courtLongName);
				liveData.setCourt_short_name(courtShortName);
				liveData.setJsonObject();
			}
			connection.close();
		} catch (Exception e) {
			System.out
					.println("SQLOraGNCodeDAO.getGNCodeInfoFromGNCodeIDMethod "
							+ e.getMessage());
		}
		return liveData;
	}

	public String createNewGNCodeID(GNCodeMappingInfoData dataObject) {
		GetDBConnection connectionObject = new GetDBConnection();
		Connection connection = connectionObject.getDBConnection();
		int maxGNCodeId = -1;

		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement
					.executeQuery("select max(gncode_id) from gncode_definition_info");

			while (rs.next()) {
				maxGNCodeId = rs.getInt(1);
			}
			maxGNCodeId = maxGNCodeId + 1;
			String insertString = "INSERT INTO gncode_definition_info "
					+ "(gncode_id, GNCODE_VALUE, STATE, COURT_TYPE, SYSTEM, "
					+ "SUBJECT_MATTER, LOCATION, COURT_LEVEL, COURT_LONG_NAME, "
					+ "COURT_SHORT_NAME) VALUES " + "('"
					+ maxGNCodeId
					+ "', '"
					+ dataObject.getGncode_value()
					+ "', '"
					+ dataObject.getState()
					+ "', '"
					+ dataObject.getCourt_type()
					+ "', '"
					+ dataObject.getSystem()
					+ "', '"
					+ dataObject.getSubject_matter()
					+ "', "
					+ "'"
					+ dataObject.getLocation()
					+ "', '"
					+ dataObject.getCourt_level()
					+ "', "
					+ "'"
					+ dataObject.getCourt_long_name()
					+ "', '"
					+ dataObject.getCourt_short_name() + "');";
			System.out.println(insertString);
			// statement.execute(insertString);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return maxGNCodeId + "";
	}

	public void updateGNCodeMapping(GNCodeCourtData dataObject, int gnCodeID) {
		GetDBConnection connectionObject = new GetDBConnection();
		Connection connection = connectionObject.getDBConnection();
		int oldGNCodeId = -1;

		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement
					.executeQuery("select gncode_id from gncode_mapping where normalized_court='"
							+ dataObject.getNormalized_court() + "'");

			while (rs.next()) {
				oldGNCodeId = rs.getInt(1);
			}
			if (oldGNCodeId < 1) {
				String insertString = "INSERT INTO gncode_mapping (NORMALIZED_COURT, GNCODE_ID) "
						+ "VALUES ('"
						+ dataObject.getNormalized_court()
						+ "', '" + gnCodeID + "');";
				System.out.println(insertString);
				// statement.execute(insertString);
			} else {
				String updateString = "UPDATE gncode_mapping SET GNCODE_ID='"
						+ gnCodeID
						+ "' WHERE NORMALIZED_COURT='CIRCUITCOURTDCOLORADO';";
				System.out.println(updateString);
				// statement.execute(updateString);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void updateGNCodeMapping(GNCodeJVData dataObject, int gnCodeID) {
		GetDBConnection connectionObject = new GetDBConnection();
		Connection connection = connectionObject.getDBConnection();
		int oldGNCodeId = -1;

		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement
					.executeQuery("select gncode_id from gncode_mapping where normalized_court='"
							+ dataObject.getNormalized_name() + "'");

			while (rs.next()) {
				oldGNCodeId = rs.getInt(1);
			}
			if (oldGNCodeId < 1) {
				String insertString = "INSERT INTO gncode_mapping (NORMALIZED_COURT, GNCODE_ID) "
						+ "VALUES ('"
						+ dataObject.getNormalized_name()
						+ "', '" + gnCodeID + "');";
				System.out.println(insertString);
				// statement.execute(insertString);
			} else {
				String updateString = "UPDATE gncode_mapping SET GNCODE_ID='"
						+ gnCodeID
						+ "' WHERE NORMALIZED_COURT='CIRCUITCOURTDCOLORADO';";
				System.out.println(updateString);
				// statement.execute(updateString);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void updateGNCodeCourtData(GNCodeCourtData dataObject) {
		GetDBConnection connectionObject = new GetDBConnection();
		Connection connection = connectionObject.getDBConnection();
		String courtName = null;

		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement
					.executeQuery("select court_name, normalized_court from gncode_caselaw_lookup where normalized_court='"
							+ dataObject.getNormalized_court() + "'");

			while (rs.next()) {
				courtName = rs.getString(1);
			}

			if (courtName == null
					|| !courtName.equals(dataObject.getCourt_name())) {
				rs = statement
						.executeQuery("select max(id) from gncode_caselaw_lookup");
				int id = -1;
				while (rs.next()) {
					id = rs.getInt(1);
				}
				id = id + 1;
				String insertString = "INSERT INTO gncode_caselaw_lookup (ID, COURT_NAME, NORMALIZED_COURT) "
						+ "VALUES ('"
						+ id
						+ "', '"
						+ dataObject.getCourt_name()
						+ "', '"
						+ dataObject.getNormalized_court() + "');";
				System.out.println(insertString);
				// statement.execute(insertString);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void updateGNCodeJVData(GNCodeJVData dataObject) {
		GetDBConnection connectionObject = new GetDBConnection();
		Connection connection = connectionObject.getDBConnection();
		String state_name = null;
		String city_name = null;
		String county_name = null;
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement
					.executeQuery("select state_name, city_name, county_name, normalized_name from gncode_jv_lookup where normalized_name='"
							+ dataObject.getNormalized_name() + "'");

			while (rs.next()) {
				state_name = rs.getString(1);
				city_name = rs.getString(2);
				county_name = rs.getString(3);
			}

			if (state_name == null
					|| !state_name.equals(dataObject.getState_name())
					|| city_name == null
					|| !city_name.equals(dataObject.getCity_name())
					|| county_name == null
					|| !county_name.equals(dataObject.getCounty_name())) {
				rs = statement
						.executeQuery("select max(id) from gncode_jv_lookup");
				int id = -1;
				while (rs.next()) {
					id = rs.getInt(1);
				}
				id = id + 1;
				String insertString = "INSERT INTO gncode_jv_lookup "
						+ "VALUES ('" + id + "', '"
						+ dataObject.getState_name() + "', '"
						+ dataObject.getCity_name() + "', '"
						+ dataObject.getCounty_name() + "', '"
						+ dataObject.getNormalized_name() + "');";
				System.out.println(insertString);
				// statement.execute(insertString);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void updateGNCodeMappingInfo(GNCodeMappingInfoData dataObject) {
		GetDBConnection connectionObject = new GetDBConnection();
		Connection connection = connectionObject.getDBConnection();
		try {
			Statement statement = connection.createStatement();
			String updateString = "UPDATE gncode_definition_info SET gncode_value='"
					+ dataObject.getGncode_value()
					+ "', STATE='"
					+ dataObject.getState()
					+ "', "
					+ "COURT_TYPE='"
					+ dataObject.getCourt_type()
					+ "', SYSTEM='"
					+ dataObject.getSystem()
					+ "', "
					+ "SUBJECT_MATTER='"
					+ dataObject.getSubject_matter()
					+ "', LOCATION='"
					+ dataObject.getLocation()
					+ "', "
					+ "COURT_LEVEL='"
					+ dataObject.getCourt_level()
					+ "', COURT_LONG_NAME='"
					+ dataObject.getCourt_long_name()
					+ "', "
					+ "COURT_SHORT_NAME='"
					+ dataObject.getCourt_short_name()
					+ "' WHERE gncode_id='" + dataObject.getGncode_id() + "';";
			System.out.println(updateString);
			// statement.execute(updateString);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void updateLiveDataStorage() {
		GetDBConnection connectionObject = new GetDBConnection();
		Connection connection = connectionObject.getDBConnection();
		try {
			Statement statement = connection.createStatement();
			System.out
					.println("select distinct system from gncode_definition_info");
			String query = "select distinct system from gncode_definition_info";
			ResultSet rs = statement.executeQuery(query);
			LiveDataStorage.System = new ArrayList();
			while (rs.next()) {
				LiveDataStorage.System.add(rs.getString(1));
			}

			query = "select distinct state from gncode_definition_info";
			rs = statement.executeQuery(query);
			LiveDataStorage.State = new ArrayList();
			while (rs.next()) {
				LiveDataStorage.State.add(rs.getString(1));
			}

			query = "select distinct location from gncode_definition_info";
			rs = statement.executeQuery(query);
			LiveDataStorage.Location = new ArrayList();
			while (rs.next()) {
				LiveDataStorage.Location.add(rs.getString(1));
			}

			query = "select distinct Court_Type from gncode_definition_info";
			rs = statement.executeQuery(query);
			LiveDataStorage.CourtType = new ArrayList();
			while (rs.next()) {
				LiveDataStorage.CourtType.add(rs.getString(1));
			}

			query = "select distinct subject_matter from gncode_definition_info";
			rs = statement.executeQuery(query);
			LiveDataStorage.SubjectMatter = new ArrayList();
			while (rs.next()) {
				LiveDataStorage.SubjectMatter.add(rs.getString(1));
			}

			query = "select distinct gncode_value from gncode_definition_info";
			rs = statement.executeQuery(query);

			LiveDataStorage.GNCodeValueList = new ArrayList();
			while (rs.next()) {
				LiveDataStorage.GNCodeValueList.add(rs.getString(1));
			}

			// query = "select * from GNCODE_JV_LOOKUP";
			// rs = statement.executeQuery(query);
			//
			// LiveDataStorage.JVMap = new HashMap();
			// while (rs.next()) {
			// int id = rs.getInt(1);
			// String state_name = rs.getString(4);
			// String city_name = rs.getString(2);
			// String county_name = rs.getString(3);
			// String normalized_name = rs.getString(6);
			// GNCodeJVData liveData = new GNCodeJVData();
			//
			// liveData.setId(id);
			// liveData.setState_name(state_name);
			// liveData.setCity_name(city_name);
			// liveData.setCounty_name(county_name);
			// liveData.setNormalized_name(normalized_name);
			//
			// LiveDataStorage.JVMap.put(normalized_name, liveData);
			// }
			query = "select * from GNCODE_CASELAW_LOOKUP";
			rs = statement.executeQuery(query);
			LiveDataStorage.CourtMap = new HashMap();
			while (rs.next()) {
				int id = Integer.parseInt(rs.getString(1));
				String court_name = rs.getString(2);
				String normalized_name = rs.getString(4);
				GNCodeCourtData liveData = new GNCodeCourtData();

				liveData.setId(id);
				liveData.setCourt_name(court_name);
				liveData.setNormalized_court(normalized_name);

				// System.out.println(id);

				LiveDataStorage.CourtMap.put(normalized_name, liveData);
			}

			// query = "select * from GNCODE_LEGACY_JV_LOOKUP";
			// rs = statement.executeQuery(query);
			// LiveDataStorage.LegacyMap = new HashMap();
			// while (rs.next()) {
			// int id = Integer.parseInt(rs.getString(1));
			// String county_name = rs.getString(2);
			// String county1_name = rs.getString(3);
			// String parish_name = rs.getString(4);
			// String publication_name = rs.getString(6);
			// String court_name = rs.getString(5);
			// String state_name = rs.getString(7);
			// String normalized_name = rs.getString(9);
			// GNCodeLegacyJVData liveData = new GNCodeLegacyJVData();
			//
			// liveData.setId(id);
			// liveData.setCourt_name(court_name);
			// liveData.setCounty_name(county_name);
			// liveData.setCounty1_name(county1_name);
			// liveData.setParish_name(parish_name);
			// liveData.setPublication_name(publication_name);
			// liveData.setState_name(state_name);
			// liveData.setNormalized_name(normalized_name);
			//
			// // System.out.println(id);
			//
			// LiveDataStorage.LegacyMap.put(normalized_name, liveData);
			// }

			System.out.println("select * from GNCODE_LEGACY_JV_LOOKUP");

		} catch (SQLException sql) {
			System.out.println(sql);
		}
	}

}