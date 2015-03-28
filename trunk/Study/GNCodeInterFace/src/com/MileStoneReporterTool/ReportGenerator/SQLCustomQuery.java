/*
 * SQLGatewayServlet.java
 *
 * Created on August 30, 2007, 9:06 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.MileStoneReporterTool.ReportGenerator;

/**
 *
 * @author siddharth_asthana
 */
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.servlet.http.HttpSession;
import com.MileStoneReporterTool.SQLConnector.sqldao.GetDBConnection;

public class SQLCustomQuery extends HttpServlet{
        
    
    private Connection connection;
    public SQLCustomQuery(){
        GetDBConnection newConnection= new GetDBConnection();
        connection = newConnection.getDBConnection();
    }


    public void sqlCustomQuery(String CustomQueryStatement){
        
            String sqlStatement = CustomQueryStatement;
            String message = "";

            try{
                Statement statement = connection.createStatement();
                sqlStatement = sqlStatement.trim();
                String sqlType = sqlStatement.substring(0, 6);
                if  (sqlType.equalsIgnoreCase("select")){
                    ResultSet resultSet = statement.executeQuery(sqlStatement);
                    // create a string that contains a HTML-formatted result set
                    message = SQLUtil.getHtmlRows(resultSet);
                }
                else
                {
                    int i = statement.executeUpdate(sqlStatement);
                    if (i == 0) // this is a DDL statement
                      message =
                        "<tr><td>" +
                          "The statement executed successfully." +
                        "</td></tr>";
                    else // this is an INSERT, UPDATE, or DELETE statement
                        message =
                          "<tr><td>" +
                            "The statement executed successfully.<br>" +
                             i + " row(s) affected." +
                           "</td></tr>";
                }

                connection.close();
            }
            catch(SQLException e){
                message = "<tr><td>Error executing the SQL statement: <br>"
                        + e.getMessage() + "</tr></td>";
            }
           // HttpSession session = request.getSession();
          //  session.setAttribute("message", message);
         //   session.setAttribute("sqlStatement", sqlStatement);
    }
}


