package com.MileStoneReporterTool.customTag;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.MileStoneReporterTool.DataBeans.UserData;

public class DisplayCustomTag extends TagSupport{
	
	private String name = null;
	private String cssClass = null;
	

	public int doStartTag() throws JspException{
		super.doStartTag();
		
		ArrayList names = (ArrayList) pageContext.getSession().getAttribute(name);
		
		int namesCount = names.size();
		StringBuffer tagText = new StringBuffer();
		tagText.append("<br>" +
						"<table border=\"0\" align=\"left\" width=\"75%\" class=\"reportTable\">	" +
							"<thead>		" +
								"<tr height=\"10\" >			" +
									"<td class=\"reportHeaders\">&nbsp;SrNo</td>			" +
									"<td class=\"reportHeaders\">&nbsp;UserId</td>			" +
									"<td class=\"reportHeaders\">&nbsp;Name</td>			" +
									"<td class=\"reportHeaders\">&nbsp;Role</td>		" +
								"</tr>	" +
							"</thead>	" +
						"<tbody>");		
		int j=0;
		for (int i=0;i<namesCount;i++){
			j=i+1;
			tagText.append("<tr height=\"5\">					" +
								"<td class=\"reportCells\">"+j+"</td>					" +
								"<td class=\"reportCells\">"+((UserData)names.get(i)).getUserID()+"&nbsp;&nbsp;&nbsp;</td>					" +
								"<td class=\"reportCells\">"+((UserData)names.get(i)).getUserName()+"&nbsp;&nbsp;&nbsp;</td>				" +
								"<td class=\"reportCells\">"+((UserData)names.get(i)).getRole()+"&nbsp;&nbsp;&nbsp;</td>					" +
							"</tr>");		
			}
		tagText.append("</tbody>		" +
				"</table>");
		JspWriter out = pageContext.getOut();
		try {
			out.println(tagText.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SKIP_BODY;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCssClass() {
		return cssClass;
	}


	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
}
