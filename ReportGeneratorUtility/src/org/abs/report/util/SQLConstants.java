package org.abs.report.util;

import org.apache.log4j.Logger;

/**
 * <p>
 * <b>Overview:</b>
 * <p>
 * 
 * 
 * <pre>
 * Creation date: Sep 4, 2014
 * @author Amit Kshirsagar
 * @version 1.0
 * @since
 * 
 * <p><b>Modification History:</b><p>
 * 
 * 
 * </pre>
 */
public class SQLConstants implements ReportGeneratorConstants {

	/**
	 * Initiate the log4j Logger for debugging purposes.
	 * 
	 */
	private static Logger log4j = Logger.getLogger(SQLConstants.class);

	/**
	 * Print object values by default. Add required attributes of the object.
	 */

	@Override
	public String toString() {
		return "SQLConstants: ";
	}

	public static String BIT_Column = "BIT";
	public static String TINYINT_Column = "TINYINT";
	public static String BIGINT_Column = "BIGINT";
	public static String LONGVARBINARY_Column = "LONGVARBINARY";
	public static String VARBINARY_Column = "VARBINARY";
	public static String BINARY_Column = "BINARY";
	public static String LONGVARCHAR_Column = "LONGVARCHAR";
	public static String NULL_Column = "NULL";
	public static String CHAR_Column = "CHAR";
	public static String NUMERIC_Column = "NUMERIC";
	public static String DECIMAL_Column = "DECIMAL";
	public static String INTEGER_Column = "INTEGER";
	public static String SMALLINT_Column = "SMALLINT";
	public static String FLOAT_Column = "FLOAT";
	public static String REAL_Column = "REAL";
	public static String DOUBLE_Column = "DOUBLE";
	public static String VARCHAR_Column = "VARCHAR";
	public static String DATE_Column = "DATE";
	public static String TIME_Column = "TIME";
	public static String TIMESTAMP_Column = "TIMESTAMP";
	public static String OTHER_Column = "OTHER";

	public static int BIT_Value = -7;
	public static int TINYINT_Value = -6;
	public static int BIGINT_Value = -5;
	public static int LONGVARBINARY_Value = -4;
	public static int VARBINARY_Value = -3;
	public static int BINARY_Value = -2;
	public static int LONGVARCHAR_Value = -1;
	public static int NULL_Value = 0;
	public static int CHAR_Value = 1;
	public static int NUMERIC_Value = 2;
	public static int DECIMAL_Value = 3;
	public static int INTEGER_Value = 4;
	public static int SMALLINT_Value = 5;
	public static int FLOAT_Value = 6;
	public static int REAL_Value = 7;
	public static int DOUBLE_Value = 8;
	public static int VARCHAR_Value = 12;
	public static int DATE_Value = 91;
	public static int TIME_Value = 92;
	public static int TIMESTAMP_Value = 93;
	public static int OTHER_Value = 1111;

	public static void updateRecordType(int columnType, String[] recordType,
			int i) {
		if (columnType == BIT_Value || columnType == CHAR_Value
				|| columnType == VARCHAR_Value || columnType == OTHER_Value
				|| columnType == LONGVARCHAR_Value || columnType == NULL_Value) {
			recordType[i] = STRING;
		} else if (columnType == TINYINT_Value || columnType == BIGINT_Value
				|| columnType == NUMERIC_Value || columnType == DECIMAL_Value
				|| columnType == INTEGER_Value || columnType == SMALLINT_Value
				|| columnType == FLOAT_Value || columnType == REAL_Value
				|| columnType == DOUBLE_Value) {
			recordType[i] = NUMBER;
		} else if (columnType == DATE_Value || columnType == TIME_Value
				|| columnType == TIMESTAMP_Value) {
			recordType[i] = DATE;
		} else {
			recordType[i] = STRING;
		}
	}
}
