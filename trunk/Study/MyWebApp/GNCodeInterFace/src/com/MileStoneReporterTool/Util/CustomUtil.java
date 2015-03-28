package com.MileStoneReporterTool.Util;

import java.util.StringTokenizer;

import com.MileStoneReporterTool.ConnectorForms.SaveUpdateGNCodeForm;
import com.MileStoneReporterTool.DataBeans.GNCodeCourtData;
import com.MileStoneReporterTool.DataBeans.GNCodeJVData;
import com.MileStoneReporterTool.DataBeans.GNCodeMappingInfoData;

public class CustomUtil {
	public static String getNormalizedString(String normalizedString){
		normalizedString = normalizedString.toUpperCase().trim();
		normalizedString = removePunctuation( normalizedString );
		normalizedString = removeWhiteSpace( normalizedString );
		return normalizedString;
	}
    private static String removePunctuation (String inString) 
    {
    	StringBuffer sb = new StringBuffer();
 		
 		for (int i = 0; i < inString.length(); i++) 
 		{
 			if ( Character.isLetterOrDigit(inString.charAt(i)) ||
 				 Character.isWhitespace(inString.charAt(i)) )
 			{ 			
 			   sb = sb.append(inString.charAt(i));
 		    }// end if
 			
 		}// end for
 		
 		return sb.toString();
 		
 	}
    
    private static String removeWhiteSpace(String inString) 
    {
        StringTokenizer st = new StringTokenizer(inString," ",false);
        
    	String t="";
    	while (st.hasMoreElements()) t += st.nextElement();
    	  
    	return t;
    	  
    }
    
    public static GNCodeMappingInfoData getGNCodeMappingInfoDataObject(SaveUpdateGNCodeForm form){
    	GNCodeMappingInfoData object = new GNCodeMappingInfoData();
    	object.setGncode_id(Integer.parseInt(form.getGncode_id()));
    	object.setGncode_value(form.getGncode_value());
    	object.setState(form.getState());
    	object.setSubject_matter(form.getSubject_matter());
    	object.setSystem(form.getSystem());
    	object.setCourt_level(form.getCourt_level());
    	object.setCourt_type(form.getCourt_type());
    	object.setCourt_long_name(form.getCourt_long_name());
    	object.setCourt_short_name(form.getCourt_short_name());
    	object.setLocation(form.getLocation());
    	return object;
    }
    
    public static GNCodeCourtData getGNCodeCourtDataObject(SaveUpdateGNCodeForm form){
    	GNCodeCourtData object = new GNCodeCourtData();
    	object.setCourt_name(form.getCourt_name());
    	object.setNormalized_court(form.getNormalized_court());
    	object.setId(form.getId());
    	return object;
    }
    
    public static GNCodeJVData getGNCodeJVDataObject(SaveUpdateGNCodeForm form){
    	GNCodeJVData object = new GNCodeJVData();
    	object.setState_name(form.getState_name());
    	object.setCity_name(form.getCity());
    	object.setCounty_name(form.getCourt());
    	object.setNormalized_name(form.getNormalized_court());
    	object.setId(form.getId());
    	return object;
    }
}
