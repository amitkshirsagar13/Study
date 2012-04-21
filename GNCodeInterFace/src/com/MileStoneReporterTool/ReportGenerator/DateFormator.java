/*
 * DateFormator.java
 *
 * Created on November 29, 2007, 7:42 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.MileStoneReporterTool.ReportGenerator;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;

/**
 *
 * @author AmitC_Kshirsagar
 */
public class DateFormator {
    
    /** Creates a new instance of DateFormator */
    public DateFormator() {
    }
    private String dateFormatted;

    public String getDateFormatted(String date){
        
        StringTokenizer stringTokenizer = new StringTokenizer(date,"-");
        String month = stringTokenizer.nextToken();
        String day = stringTokenizer.nextToken();
        String yyyy= stringTokenizer.nextToken();

        int intMonth = Integer.parseInt(month);
        int intDay = Integer.parseInt(day);
        int intYear = Integer.parseInt(yyyy);
        
        GregorianCalendar dateToFormat = new GregorianCalendar(intYear,intMonth,intDay);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        dateFormatted = simpleDateFormat.format(dateToFormat.getTime());
        return dateFormatted;
    }
    
}
