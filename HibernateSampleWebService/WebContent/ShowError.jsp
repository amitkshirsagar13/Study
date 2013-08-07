<%@ page isErrorPage="true"%>



<!DOCTYPE html PUBLIC "-/W3C/DTD HTML 4.01 Transitional/EN" "http:/www.w3.org/TR/html4/loose.dtd">




<html>
<head>
<title>Cite Retrieval Access Service</title>
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/resources/images/CRAS-Title.ico">
</head>

<body style="margin: 0px;">
	<TABLE width="100%" height="100%" border="0">
		<TR>
			<TD valign="top" height="10px">
				<!DOCTYPE html PUBLIC "-/W3C/DTD HTML 4.01 Transitional/EN" "http:/www.w3.org/TR/html4/loose.dtd">




				<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/master.css"
	type="text/css" media="screen" charset="utf-8" />
</head>
<body>
	<div
		style="background-image: url('${pageContext.request.contextPath}/resources/images/black.png'); width: 100%; margin-top: 3px; margin-left: 0px; margin-right: 0px;">
		<img
			src="${pageContext.request.contextPath}/resources/images/Logo.png ">
	</div>
	<div
		style="background-image: url('${pageContext.request.contextPath}/resources/images/Header_Middle.png'); margin-top: 3px;">
		<div>
			<table id="1" height="10%" cellspacing="0" cellpadding="0"
				width="100%" style="vertical-align: top; table-layout: fixed"
				align="center">
				<tr id="2" height="100%">
					<td><a href="${pageContext.request.contextPath}/index.jsp"><img
							style="font-family: Segoe UI; font-size: 20px; margin-left: 43px; margin-top: 1px"
							src="${pageContext.request.contextPath}/resources/images/CRAS.png"></a></td>
				</tr>
			</table>
		</div>
	</div>

</body>
				</html>

			</TD>
		</TR>
		<TR>
			<TD valign="middle" align="left">
				<!DOCTYPE html PUBLIC "-/W3C/DTD HTML 4.01 Transitional/EN" "http:/www.w3.org/TR/html4/loose.dtd">




				<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/resources/js/indexjscript.js"></script>

<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/resources/js/jquery-1.9.1.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/resources/js/jquery-ui.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery.autocomplete.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/tabber.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery.blockUI.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/tab.css"
	type="text/css" MEDIA="screen" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/master.css"
	type="text/css" media="screen" charset="utf-8" />


</head>

<body style="margin: 0px; overflow-x: hidden; overflow-y: hidden;">
	<div style="margin-top: 0px; padding-top: 0.5px">
		<div
			style="padding-bottom: 33px; background-image: url('${pageContext.request.contextPath}/resources/images/Page_Background_Grey.png');">
			<h3 style="margin-top: 3px; margin-bottom: 0%">
				<label
					style="margin-left: 45px; font-family: Segoe UI; color: #b72814; font-size: 18px; padding-top: 20px;">Exception
					from CRAS</label>
			</h3>
			<div id="searchForm">
				<table border="2px" bordercolor="#b72814" cellpadding="0"
					cellspacing="0"
					style="margin-top: 10px; margin-left: 2%; margin-right: 2%; background-color: white; width: 96%;">
					<tr>
						<td style="padding-top: 25px; padding-bottom: 125px;">
							<form name="csrViewerForm" method="post"
								action="${pageContext.request.contextPath}/CrasWebServlet.do"
								enctype="text/xml"
								style="margin-top: 10px; margin-left: 2%; margin-right: 2%; background-color: white; width: 96%">
								<div id="getCrasDetails">
									<fieldset class="baseText" style="width: 86.9%; float: left;">
										<legend class="Heading">Exception happened while Cras
											Service</legend>
										<%
											ServletException servletException = (ServletException) request
													.getAttribute("servletException");
											pageContext.setAttribute("servletException", servletException);
											if (servletException != null) {
												exception = servletException;
											}
										%>

										<%
											if (servletException != null) {
										%>
										Where is this?<%
											}
										%>

										<p class=baseText>Sorry, an error occurred.</p>
										<p class=baseText>Here is the exception stack trace:</p>
										<div class="tabber" id="table-wrapper"
											style="padding: 0px 50px 0px 50px">
											<div class="tabbertab" id="table-scroll">
												<h2 class="Heading">
													Exception:<%=exception.getClass()%></h2>
												<div id="errorMessage" style="padding: 0px 30px 0px 30px">
													<pre>	<font style="color: red"><%=exception.getClass()%>: <strong><%=exception.getMessage()%></strong></font>
		<%=exception.getStackTrace()[0]%>
		<%=exception.getStackTrace()[1]%>
		<%=exception.getStackTrace()[2]%>
		<%=exception.getStackTrace()[3]%>
		<%=exception.getStackTrace()[4]%>
		<%=exception.getStackTrace()[5]%>
		<%=exception.getStackTrace()[6]%>
		<%=exception.getStackTrace()[7]%>
		<%=exception.getStackTrace()[8]%>
		<%=exception.getStackTrace()[9]%>
										</pre>
												</div>
												<hr>
												<input type="button" name="method" value="Back"
													onClick="javascript:window.location.href='${pageContext.request.contextPath}/index.jsp'"
													class="button red">
											</div>
										</div>
									</fieldset>
								</div>
							</form>

						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
				</html>


			</TD>
		</TR>
		<TR>
			<TD height="10px">
				<!DOCTYPE html PUBLIC "-/W3C/DTD HTML 4.01 Transitional/EN" "http:/www.w3.org/TR/html4/loose.dtd">


				<html>
<head>
<title>CitationFooter</title>
</head>

<body>
	<div
		style="height: 23px; background-image: url('${pageContext.request.contextPath}/resources/images/black.png'); background-repeat: repeat-x; background-position: left; width: 100%; margin: 0px; padding-bottom: 0px;">
		<table class="noLinks" align="center">
			<tr>
				<td><label style="text-align: center; color: white;">Copyright2013
						© LexisNexis</label></td>
			</tr>
		</table>
	</div>
</body>
				</html>

				<div id="blockuiHidden" style="visibility: hidden">
					<div id="blockui">
						<img
							src="${pageContext.request.contextPath}/resources/images/processing.gif" />
						<font
							style="font-size: 14px; font-style: normal; color: #b72814; font-weight: bold;">
							Please wait.... </font>
					</div>
				</div>

			</TD>
		</TR>
	</TABLE>
</body>
</html>