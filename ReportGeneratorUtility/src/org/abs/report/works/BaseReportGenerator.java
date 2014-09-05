package org.abs.report.works;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import jxl.write.WritableSheet;

import org.abs.report.db.DBUtilities;
import org.abs.report.exception.ReportException;
import org.abs.report.util.ReportConfigUtil;
import org.abs.report.util.ReportGeneratorConstants;
import org.abs.report.util.SQLConstants;
import org.abs.report.writer.RecordCreator;
import org.abs.report.writer.RecordElementWriter;
import org.abs.report.writer.ReportRecord;
import org.abs.report.writer.ReportRecordWriter;
import org.abs.report.writer.ReportWriter;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.log4j.Logger;

/**
 * <p>
 * <b>Overview:</b>
 * <p>
 * 
 * 
 * <pre>
 * Creation date: Sep 3, 2014
 * @author Amit Kshirsagar
 * @version 1.0
 * @since
 * 
 * <p><b>Modification History:</b><p>
 * 
 * 
 * </pre>
 */
public class BaseReportGenerator implements ReportGeneratorConstants {

	/**
	 * Initiate the log4j Logger for debugging purposes.
	 * 
	 */
	private static Logger log4j = Logger.getLogger(BaseReportGenerator.class);

	/**
	 * Print object values by default. Add required attributes of the object.
	 */

	@Override
	public String toString() {
		return "BaseReportGenerator: " + reportQuery;
	}

	String reportDb = null;
	String reportName = null;
	String reportConfig = null;
	String reportQuery = null;
	String reportColumns = null;
	String reportParams = null;
	String reportFilePath = null;
	String reportDate = null;
	CommandLine commandLine = null;

	public void processOptions(String[] args) {
		try {
			constructReportOptions();
			commandLine = new BasicParser().parse(reportOptions, args);

			if (commandLine.hasOption('h')) {
				log4j.debug(reportOptions);
				System.exit(0);
			}
			if (!commandLine.hasOption('c')) {
				throw new ReportException(missingParamMessage("c"));
			} else {
				reportConfig = commandLine.getOptionValue('c');
			}

			if (!commandLine.hasOption('r')) {
				throw new ReportException(missingParamMessage("r"));
			} else {
				reportName = commandLine.getOptionValue('r');
			}

			if (!commandLine.hasOption('f')) {
				throw new ReportException(missingParamMessage("f"));
			} else {
				reportFilePath = commandLine.getOptionValue('f');
			}

			reportParams = commandLine.getOptionValue('p');
			reportDate = commandLine.getOptionValue('d');
		} catch (Exception e) {
			log4j.error(e.getMessage(), e);
		}
	}

	final Options reportOptions = new Options();

	public Options constructReportOptions() {

		reportOptions
				.addOption("r", "reportName", true, "Report Name to be run")
				.addOption("f", "fileName", true, "Output file path")
				.addOption("c", "configFile", true, "Config file Name")
				.addOption("p", "reportParam", true,
						"Report Parameters with | delimited")
				.addOption("d", "date", true, "Date in yyyyMMDD format")
				.addOption("h", "help", false, "Show Required Arguments");
		return reportOptions;
	}

	private String missingParamMessage(String param) {
		return "Missing Parameter: " + param;
	}

	public void prepareQuery() {
		Properties reportProperties = ReportConfigUtil
				.getProperties(reportConfig);
		reportDb = reportProperties.getProperty(REPORTDB);
		reportQuery = reportProperties.getProperty(REPORTQUERY);
		dfm = new SimpleDateFormat(
				reportProperties.getProperty(REPORTDATEFORMAT));
		String reportParameters = reportProperties.getProperty(REPORTPARAMS);
		log4j.debug(reportParams);
		StringTokenizer reportParamTkn = new StringTokenizer(reportParams,
				"\\|");

		Map<String, String> reportParamMap = new HashMap<String, String>();

		while (reportParamTkn.hasMoreTokens()) {
			String reportParam = reportParamTkn.nextToken();
			String[] splitParam = reportParam.split("=");
			reportParamMap.put(splitParam[0], splitParam[1]);
		}
		StringTokenizer reportParamCfg = new StringTokenizer(reportParameters,
				"\\|");
		while (reportParamCfg.hasMoreTokens()) {
			String reportParam = reportParamCfg.nextToken();
			reportQuery = reportQuery.replaceAll("\\|" + reportParam + "\\|",
					reportParam + "=" + reportParamMap.get(reportParam));
		}
		log4j.debug(this);
	}

	Connection conn;
	Statement statement;
	ResultSet resultSet;
	SimpleDateFormat dfm = null;

	public void runQuery() {
		try {
			conn = DBUtilities.getConnection(ReportConfigUtil
					.getProperties(reportDb));
			statement = conn.createStatement();
			resultSet = statement.executeQuery(getSql());
		} catch (Exception e) {
			log4j.error(e.getMessage());
		}
	}

	public String getSql() {
		return reportQuery;
	}

	ReportWriter reportWriter = null;

	public void createReport() {
		reportWriter = new ReportWriter(new File(reportFilePath + "/"
				+ reportName + ".xls"));
		WritableSheet memberSheet = reportWriter.getWorkSheet(reportName);

		ReportRecordWriter reportRecordWriter = new ReportRecordWriter();

		RecordElementWriter elementWriter = new RecordElementWriter();

		RecordCreator recordCreator = new RecordCreator();

		try {

			ResultSetMetaData metadata = resultSet.getMetaData();

			int columnCount = metadata.getColumnCount();

			String recordHeader[] = new String[columnCount];

			String[] recordType = new String[columnCount];
			String[] recordHeaderType = new String[columnCount];

			for (int i = 0; i < columnCount; i++) {
				recordHeader[i] = metadata.getColumnName(i + 1);
				recordHeaderType[i] = COLUMN;
				SQLConstants.updateRecordType(metadata.getColumnType(i + 1),
						recordType, i);
			}

			ReportRecord headerRecord = recordCreator.createRecord(
					recordHeaderType, recordHeader);
			reportRecordWriter.writeRecord(memberSheet, elementWriter,
					headerRecord, 0);

			ReportRecord record = null;
			int recordCounter = 1;
			while (resultSet.next()) {
				String[] recordArray = new String[columnCount];
				for (int i = 0; i < columnCount; i++) {
					if (recordType[i].equalsIgnoreCase(STRING)) {
						recordArray[i] = resultSet.getString(i + 1);
					} else if (recordType[i].equalsIgnoreCase(NUMBER)) {
						if (resultSet.getString(i + 1) != null) {
							recordArray[i] = resultSet.getInt(i + 1) + "";
						} else {
							recordArray[i] = resultSet.getString(i + 1);
						}
					} else if (recordType[i].equalsIgnoreCase(DATE)) {
						recordArray[i] = dfm.parse(
								resultSet.getDate(i + 1).toString() + "")
								.getTime()
								+ "";
					}
				}
				record = recordCreator.createRecord(recordType, recordArray);
				reportRecordWriter.writeRecord(memberSheet, elementWriter,
						record, recordCounter);
				recordCounter++;
			}
			reportWriter.getReportWorkbook().write();
			reportWriter.getReportWorkbook().close();
		} catch (Exception e) {
			log4j.error(e.getMessage(), e);
		}
	}

	public void printResults() {
		try {
			while (resultSet.next()) {
				log4j.debug(resultSet.getInt(1) + "|" + resultSet.getString(2)
						+ "|" + resultSet.getString(3));
			}
		} catch (SQLException e) {
			log4j.error(e.getMessage(), e);
		}
	}
}
