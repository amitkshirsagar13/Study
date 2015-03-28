package com.MileStoneReporterTool.SQLConnector.sqldao;

import java.sql.Connection;
import java.sql.Statement;

public class AutomaticDataBaseCreator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GetDBConnection dbConnection = new GetDBConnection();
		Connection con = dbConnection.getDBConnection();
		try{
			Statement statement = con.createStatement();
			for (int i=0;i<1000;i++)
				statement.execute("insert into MileStone_Login values("+i+",'user"+i+"','289"+i+"',2)");
		}catch(Exception e){
			
		}
	}

}
