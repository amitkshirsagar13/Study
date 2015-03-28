<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<HTML>
    <HEAD>
        <title>DemoWebApplication</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <link rel="stylesheet" href="Common/layout/css/DemoApplication.css" type="text/css" />
        <link rel="stylesheet" href="Common/layout/css/calendar.css" type="text/css" />
        <script language="javascript" src="Common/layout/JavaScript/choosedate.js"></script>
        <script language="javascript" src="Common/layout/JavaScript/addDropDown.js"></script>
        <script language="javascript" src="Common/layout/JavaScript/dropdown.js"></script>
        <script language="javascript" src="Common/layout/JavaScript/date.js"></script>
        <script language="javascript" src="Common/layout/JavaScript/AjaxMain.js"></script>
        <script language="JavaScript" type="text/javascript">

            function about(){
            window.open("Pages/about.htm","About","width=400,height=220");
            }

        </script>
    </HEAD>
    <BODY bgColor="DimGray" onload="JavaScript:history.go(1);" >
        <TABLE id="Table1"  cellSpacing="0" cellPadding="0" width="98%" align="center" border="0" class="pageTable">
            <TR>
                <TD colSpan="5"><tiles:insert attribute="header" /></TD>
            </TR>
            <TR valign="top">
                <TD>            
                    <TABLE id="Table1"  cellSpacing="0" cellPadding="0" width="100%" align="center" bgcolor="#CODFFF" border="0">
                         <TR>
				<TD width="1%" rowspan="1">
				    &nbsp;
				</TD>
                         </TR>
                         <TR>
				<TD width="1%" rowspan="1">
				    &nbsp;
				</TD>
                         </TR>
                         <TR>
				<TD width="1%" rowspan="1">
				    &nbsp;
				</TD>
                         </TR>
                         <TR>
				<TD width="1%" rowspan="1">
				    &nbsp;
				</TD>
                         </TR>
                         <TR>
				<TD width="1%" rowspan="1">
				    &nbsp;
				</TD>
                         </TR>
                         <TR>
				<TD width="1%" rowspan="1">
				    &nbsp;
				</TD>
                         </TR>
                         <TR>
				<TD width="1%" rowspan="1">
				    &nbsp;
				</TD>
                         </TR>
                         <TR>
				<TD width="1%" rowspan="1">
				    &nbsp;
				</TD>
                         </TR>
                         <TR>
				<TD width="1%" rowspan="1">
				    &nbsp;
				</TD>
                         </TR>
                         <TR>
				<TD width="1%" rowspan="1">
				    &nbsp;
				</TD>
                         </TR>
                         <TR>
				<TD width="1%" rowspan="1">
				    &nbsp;
				</TD>
                         </TR>
                         <TR>
				<TD width="1%" rowspan="1">
				    &nbsp;
				</TD>
                         </TR>
                         <TR>
				<TD width="1%" rowspan="1">
				    &nbsp;
				</TD>
                         </TR>
                         <TR>
				<TD width="1%" rowspan="1">
				    &nbsp;
				</TD>
                         </TR>
                         <TR>
				<TD width="1%" rowspan="1">
				    &nbsp;
				</TD>
                         </TR>
                         <TR>
				<TD width="1%" rowspan="1">
				    &nbsp;
				</TD>
                         </TR>
                         <TR>
				<TD width="1%" rowspan="1">
				    &nbsp;
				</TD>
                         </TR>
                         <TR>
				<TD width="1%" rowspan="1">
				    &nbsp;
				</TD>
                         </TR>
                         <TR>
				<TD width="1%" rowspan="1">
				    &nbsp;
				</TD>
                         </TR>
                         <TR>
				<TD width="1%" rowspan="1">
				    &nbsp;
				</TD>
                         </TR>
                         <TR>
				<TD width="1%" rowspan="1">
				    &nbsp;
				</TD>
                         </TR>
                         <TR>
				<TD width="1%" rowspan="1">
				    &nbsp;
				</TD>
                         </TR>
                         <TR>
				<TD width="1%" rowspan="1">
				    &nbsp;
				</TD>
                         </TR>
                         <TR>
				<TD width="1%" rowspan="1">
				    &nbsp;
				</TD>
                         </TR>
                         <TR>
				<TD width="1%" rowspan="1">
				    &nbsp;
				</TD>
                         </TR>
                         <TR>
				<TD width="1%" rowspan="1">
				    &nbsp;
				</TD>
                         </TR>
                         <TR>
				<TD width="1%" rowspan="1">
				    &nbsp;
				</TD>
                         </TR>
                         <TR>
				<TD width="1%" rowspan="1">
				    &nbsp;
				</TD>
                         </TR>
                         <TR>
				<TD width="1%" rowspan="1">
				    &nbsp;
				</TD>
                         </TR>
                         <TR>
				<TD width="1%" rowspan="1">
				    &nbsp;
				</TD>
                         </TR>
                         <TR>
				<TD width="1%" rowspan="1">
				    &nbsp;
				</TD>
                         </TR>
                         <TR>
				<TD width="1%" rowspan="1">
				    &nbsp;
				</TD>
                         </TR>
                         <TR>
				<TD width="1%" rowspan="1">
				    &nbsp;
				</TD>
                         </TR>
                         <TR>
				<TD width="1%" rowspan="1">
				    &nbsp;
				</TD>
                         </TR>
                         <TR>
				<TD width="1%" rowspan="1">
				    &nbsp;
				</TD>
                         </TR>
                         <TR>
				<TD width="1%" rowspan="1">
				    &nbsp;
				</TD>
                         </TR>
                         <TR>
				<TD width="1%" rowspan="1">
				    &nbsp;
				</TD>
                         </TR>
                         <TR>
				<TD width="1%" rowspan="1">
				    &nbsp;
				</TD>
                         </TR>
                         <TR>
				<TD width="1%" rowspan="1">
				    &nbsp;
				</TD>
                         </TR>
                         <TR>
				<TD width="1%" rowspan="1">
				    &nbsp;
				</TD>
                         </TR>
                         <TR>
				<TD width="1%" rowspan="1">
				    &nbsp;
				</TD>
                         </TR>
                         <TR>
				<TD width="1%" rowspan="1">
				    &nbsp;
				</TD>
                         </TR>
                    </TABLE>
                    </TD>
                        <TD width="19%" rowspan="3">
                            <tiles:insert attribute="menu" />
                        </TD>
                        <TD width="5%">&nbsp;</TD>
                        <TD valign="top"><tiles:insert attribute="body"/></TD>
                        <TD width="5%">&nbsp;</TD>
            </TR>
            <tr >
                <td colspan="4">&nbsp;</td>
            </tr>
            <TR>
                <td colspan="4" width="80%">
                    <tiles:insert attribute="footer" />
                </td>
            </TR>
        </TABLE>
    </BODY>
</HTML>
