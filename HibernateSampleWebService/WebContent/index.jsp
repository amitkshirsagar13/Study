<%@ page errorPage="ShowError.jsp"%>

<!DOCTYPE html PUBLIC "-/W3C/DTD HTML 4.01 Transitional/EN" "http:/www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Employee Access Service</title>
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/resources/images/CRAS-Title.ico">

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-1.9.1.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-ui.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery.autocomplete.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/tabber.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery.blockUI.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/jquery.dataTables.css"
	type="text/css" media="screen" charset="utf-8" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/master.css"
	type="text/css" media="screen" charset="utf-8" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/jquery-ui.css"
	type="text/css" media="screen" charset="utf-8" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/jquery.autocomplete.css"
	type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/tab.css"
	type="text/css" MEDIA="screen" />
<script type="text/javascript">
	$(function() {
		var fn = function() {
			$.blockUI({
				message : $('#blockui')
			});
		};
		$('#viewCrasByCite').click(fn);
		setTimeout($.unblockUI, 300);
		$('#blockui').click(function() {
			$.unblockUI();
		});
	});
</script>


</head>

<body style="margin: 0px;">
	<div style="margin-top: 0px; padding-top: 0.5px">
		<div
			style="border: 1; padding-bottom: 33px; background-image: url('${pageContext.request.contextPath}/resources/images/Page_Background_Grey.png');">
			<h3 style="margin-top: 3px; margin-bottom: 0%">
				<label
					style="margin-left: 45px; font-family: Segoe UI; color: #b72814; font-size: 18px; padding-top: 20px;">View
					Employee Details</label>
			</h3>
			<div id="searchForm">
				<table border="2px" bordercolor="#b72814" cellpadding="0"
					cellspacing="0"
					style="margin-top: 10px; margin-left: 2%; margin-right: 2%; background-color: white; width: 96%; table-layout: fixed">
					<tr>
						<td style="padding-top: 25px; padding-bottom: 125px;">
							<form name="csrViewerForm" method="post"
								action="${pageContext.request.contextPath}/EmployeeServlet.do"
								enctype="text/xml"
								style="margin-top: 10px; margin-left: 2%; margin-right: 2%; background-color: white; width: 96%">
								<fieldset class="baseText" style="width: 87.8%; float: left;">
									<legend class="Heading">Get Employee Details</legend>
									<div class="tabber" id="table-wrapper">
										<div class="tabbertab" id="table-scroll">
											<h2 class="Heading">Employee Result</h2>
											<table width="90%" id="reportTable" class="reporttableborder">
												<thead>
													<tr>
														<th class="TableHeading" style="width: 10%">Srno</th>
														<th class="TableHeading" style="width: 20%">CiteType</th>
														<th class="TableHeading" style="width: 30%">CiteValue</th>
														<th class="TableHeading" style="width: 30%">Pguid</th>
														<th class="TableHeading" style="width: 10%">Delete?</th>
													</tr>
												</thead>
												<tbody>
													<tr>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
									<hr />
									<input type="submit" name="method" value="CrasByCite"
										id="viewCrasByCite" class="button red" /> <input
										type="button" name="method" value="Back"
										onClick="javascript:window.location.href='${pageContext.request.contextPath}'"
										class="button red" />
								</fieldset>
							</form>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>

	<div>
		<div
			style="height: 23px; background-image: url('${pageContext.request.contextPath}/resources/images/black.png'); background-repeat: repeat-x; background-position: left; width: 100%; margin: 0px; padding-bottom: 0px;">
			<table class="noLinks" align="center">
				<tr>
					<td><label style="text-align: center; color: white;">Copyright2013
							© Amit Kshirsagar</label></td>
				</tr>
			</table>
		</div>
	</div>
	<div id="blockuiHidden" style="visibility: hidden">
		<div id="blockui">
			<img
				src="${pageContext.request.contextPath}/resources/images/processing.gif" />
			<font
				style="font-size: 14px; font-style: normal; color: #b72814; font-weight: bold;">
				Please wait.... </font>
		</div>
	</div>
</body>
</html>