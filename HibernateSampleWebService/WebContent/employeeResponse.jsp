<%@page import="com.sample.hibernate.bean.Address"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.sample.hibernate.bean.Employee"%>
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
	$(document).ready(
			function() {
				$('table.reporttableborder tbody tr:even').css(
						"background-color", "#F4F4F8");
				$('table.reporttableborder tbody tr:odd').css(
						"background-color", "#FFFFE9");
			});
</script>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#reportTable1 #id').click(function(e) {
			var sort = this;
			var rows = $('#reportTable1 tbody  tr').get();
			rows.sort(function(a, b) {
				var A = $(a).children('td').eq(0).text().toUpperCase();
				var B = $(b).children('td').eq(0).text().toUpperCase();
				if (sort.toString().indexOf('asc') > 0) {
					if (A < B) {
						return -1;
					}
					if (A > B) {
						return 1;
					}
				} else {
					if (B < A) {
						return -1;
					}
					if (B > A) {
						return 1;
					}
				}
				return 0;
			});

			if (sort.toString().indexOf('asc') > 0) {
				this.href = '#dsc';
			} else {
				this.href = '#asc';
			}

			$.each(rows, function(index, row) {
				$('#reportTable1').children('tbody').append(row);
			});
			e.preventDefault();
		});

		$('#reportTable1 #name').click(function(e) {
			var sort = this;
			var rows = $('#reportTable1 tbody  tr').get();
			rows.sort(function(a, b) {
				var A = $(a).children('td').eq(1).text().toUpperCase();
				var B = $(b).children('td').eq(1).text().toUpperCase();
				if (sort.toString().indexOf('asc') > 0) {
					if (A < B) {
						return -1;
					}
					if (A > B) {
						return 1;
					}
				} else {
					if (B < A) {
						return -1;
					}
					if (B > A) {
						return 1;
					}
				}
				return 0;
			});

			if (sort.toString().indexOf('asc') > 0) {
				this.href = '#dsc';
			} else {
				this.href = '#asc';
			}

			$.each(rows, function(index, row) {
				$('#reportTable1').children('tbody').append(row);
			});
			e.preventDefault();
		});

		$('#reportTable1 #salary').click(function(e) {
			var sort = this;
			var rows = $('#reportTable1 tbody  tr').get();
			rows.sort(function(a, b) {
				var A = $(a).children('td').eq(3).text().toUpperCase();
				var B = $(b).children('td').eq(3).text().toUpperCase();
				if (sort.toString().indexOf('asc') > 0) {
					if (A < B) {
						return -1;
					}
					if (A > B) {
						return 1;
					}
				} else {
					if (B < A) {
						return -1;
					}
					if (B > A) {
						return 1;
					}
				}
				return 0;
			});

			if (sort.toString().indexOf('asc') > 0) {
				this.href = '#dsc';
			} else {
				this.href = '#asc';
			}

			$.each(rows, function(index, row) {
				$('#reportTable1').children('tbody').append(row);
			});
			e.preventDefault();
		});

	});
</script>
<style>
#table-wrapper {
	position: relative;
}

#table-scroll {
	height: 500px;
	overflow-x: auto;
	overflow-y: auto;
	margin-top: 05px;
}

#table-wrapper table {
	width: 100%;
}
</style>

</head>

<body style="margin: 0px;">
	<div style="margin-top: 0px; padding-top: 0.5px">
		<div
			style="border: 1; padding-bottom: 33px; background-image: url('${pageContext.request.contextPath}/resources/images/Page_Background_Grey.png');">
			<h3 style="margin-top: 3px; margin-bottom: 0%">
				<label
					style="margin-left: 45px; font-family: Segoe UI; color: #b72814; font-size: 18px; padding-top: 20px;">Employee
					Details</label>
			</h3>
			<div id="resultForm">
				<table border="2px" bordercolor="#b72814" cellpadding="0"
					cellspacing="0"
					style="margin-top: 10px; margin-left: 2%; margin-right: 2%; background-color: white; width: 96%; table-layout: fixed">
					<tr>
						<td style="padding-top: 25px; padding-bottom: 125px;">
							<%
								List<Employee> employeeList = (List<Employee>) request
										.getAttribute("employeeList");
							%>
							<form name="csrViewerForm" method="post"
								action="${pageContext.request.contextPath}"
								style="margin-top: 10px; margin-left: 2%; margin-right: 2%; background-color: white; width: 96%">
								<fieldset class="baseText" style="width: 87.8%; float: left;">
									<legend class="Heading">Employee</legend>
									<div class="tabber" id="table-wrapper">
										<div class="tabbertab" id="table-scroll">
											<h2 class="Heading">Employee Result</h2>
											<table width="90%" id="reportTable1"
												class="reporttableborder">
												<thead>
													<tr>
														<th class="TableHeading" style="width: 5%"><a
															href="#asc" id="id">ID</a></th>
														<th class="TableHeading" style="width: 40%"><a
															href="#asc" id="name">FirstName</a></th>
														<th class="TableHeading" style="width: 40%">LastName</th>
														<th class="TableHeading" style="width: 15%"><a
															href="#asc" id="salary">Salary</a></th>
													</tr>
												</thead>
												<tbody>
													<%
														if (employeeList != null) {
															for (Iterator<Employee> iterator = employeeList.iterator(); iterator
																	.hasNext();) {
																Employee employee = iterator.next();
													%>

													<tr>
														<td class="ReadOnlyData"><%=employee.getId()%></td>
														<td class="ReadOnlyData"><%=employee.getFirstName()%></td>
														<td class="ReadOnlyData"><%=employee.getLastName()%></td>
														<td class="ReadOnlyData"><%=employee.getSalary()%></td>
													</tr>
													<%
														}
														}
													%>



												</tbody>
											</table>
										</div>
										<div class="tabbertab" id="table-scroll">
											<h2 class="Heading">Address</h2>
											<table width="90%" id="reportTable2"
												class="reporttableborder">
												<thead>
													<tr>
														<th class="TableHeading" style="width: 5%">ID</th>
														<th class="TableHeading" style="width: 15%">FirstName</th>
														<th class="TableHeading" style="width: 15%">LastName</th>
														<th class="TableHeading" style="width: 25%">FirstLine</th>
														<th class="TableHeading" style="width: 25%">City</th>
														<th class="TableHeading" style="width: 15%">Zip</th>
													</tr>
												</thead>
												<tbody>
													<%
														if (employeeList != null) {
															for (Iterator<Employee> iterator = employeeList.iterator(); iterator
																	.hasNext();) {
																Employee employee = iterator.next();

																List<Address> addressList = employee.getAddresses();
																
																for (int index = 0;index<employee.getAddresses().size();index++ ) {
													%>

													<tr>
														<td class="ReadOnlyData"><%=employee.getAddresses().get(index)
								.getAddressid()%></td>
														<td class="ReadOnlyData"><%=employee.getFirstName()%></td>
														<td class="ReadOnlyData"><%=employee.getLastName()%></td>
														<td class="ReadOnlyData"><%=employee.getAddresses().get(index).getFirstLine()%></td>
														<td class="ReadOnlyData"><%=employee.getAddresses().get(index).getCity()%></td>
														<td class="ReadOnlyData"><%=employee.getAddresses().get(index).getZip()%></td>
													</tr>
													<%
																}
															}
														}
													%>
												</tbody>
											</table>
										</div>
									</div>
									<hr />
									<input type="button" name="method" value="Back"
										onClick="javascript:window.location.href='${pageContext.request.contextPath}/index.jsp'"
										class="button red">

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
</body>

</html>