<!DOCTYPE html>
<%@page import="java.math.BigDecimal"%>
<html>
<head>
<title>(Type a title for your page here)</title>
<style type="text/css">
body {
	min-height: 100%;
	background-color: #90FF90;
	overflow-y: scroll;
}

h3.error {
	color: red;
}

div#sub3 {
	position: absolute;
	left: 380px;
	top: 125px;
	background-color: #f1f1f1;
	color: black;
	padding: 5px;
	border: #0000bb 2px solid;
	border-radius: 5px;
	z-index: 10;
	display: none;
}

div#errorMessageBox {
	position: absolute;
	left: 380px;
	top: 125px;
	background-color: #f1f1f1;
	color: black;
	padding: 5px;
	border: #0000bb 2px solid;
	border-radius: 5px;
	z-index: 10;
	display: none;
}

html {
	min-height: 100%;
}

body {
	position: relative;
}

.overlay {
	position: fixed;
	top: 0;
	left: 0;
	opacity: 0.6;
	width: 100%;
	height: 100%;
	z-index: 5;
	background-color: rgba(0, 0, 0, 0.8); /*dim the background*/
}

.tooltip {
	display: inline;
	position: relative;
	text-decoration: none;
	top: 0px;
	left: 15px;
}

.tooltip:hover:before {
	border: solid;
	border-color: transparent black;
	border-width: 6px 6px 6px 0;
	bottom: 6px;
	content: "";
	left: 20px;
	position: absolute;
	z-index: 99;
}

.tooltip:hover:after {
	background: #333;
	background: rgba(0, 0, 0, .8);
	border-radius: 5px;
	bottom: -4px;
	color: #fff;
	content: attr(alt);
	left: 25px;
	padding: 5px 15px;
	position: absolute;
	z-index: 98;
	width: 150px;
}
</style>

<script language="JavaScript">
	function setVisibility(id, visibility) {
		document.getElementById(id).style.display = visibility;
		if (visibility == 'none') {
			document.getElementById("overlay").style.display = 'none';
		} else {
			document.getElementById("overlay").style.display = visibility;
		}
	}

	function changePartialPay(partialPayCheck, textboxName) {
		var partialPayCheckBox = document.getElementById('payPartial');
		var partialDuePaymentTextBox = document.getElementById(textboxName);
		//alert(partialPayCheck);
		//alert(partialDuePaymentTextBox.disabled);
		if (partialPayCheckBox.checked) {
			partialDuePaymentTextBox.disabled = false;
		} else {
			partialDuePaymentTextBox.disabled = true;
		}
	}
	function changeDiv(partialPayCheck, textboxName) {
		var partialPayCheckBox = document.getElementById(partialPayCheck);
		var div_to_disable = document.getElementById(textboxName);
		var children = div_to_disable.childNodes;

		if (partialPayCheckBox.checked) {
			for (var i = 0; i < children.length; i++) {
				children[i].disabled = false;
			}
		} else {
			for (var i = 0; i < children.length; i++) {
				children[i].disabled = true;
			}
		}
	}

	function show() {
		alert('ding');
		s = '<table width="100%" cellspacing="2" cellpadding="0" border="0">';
		s += '<tr><td><img src="http://upload.wikimedia.org/wikipedia/meta/2/2a/Nohat-logo-nowords-bgwhite-200px.jpg" border="0"/> </td><td valig="top">WikiPedia</td></tr>';
		s += '<tr><td colspan="2" class="Text"><hr/>this is a test for simple tooltip.<br/>You can add text and images to the tooltip</td></tr>';
		s += '</table>'
		toolTip(s)
	}

	function eventHandler(messageBox, messageDetails, visibility) {
		//alert(messageBox);
		document.getElementById('errorMessageBox').style.display = visibility;
		var messageHTML = "";
		if (messageDetails.hasOwnProperty('errorCode')) {
			messageHTML = "<h3 class='error'>" + messageDetails.errorCode
					+ "</h3>";
			messageHTML = messageHTML + "<p class='error'>";
			messageHTML = messageHTML + "<ul>";
			var msgArray = messageDetails.errorMessage;
			for (var i = 0; i < msgArray.length; i++) {
				if (msgArray[i] != null) {
					messageHTML = messageHTML + "<li>" + msgArray[i] + "</li>";
				}
			}
			messageHTML = messageHTML + "</ul>";

			messageHTML = messageHTML + "</p>";
		}

		document.getElementById(messageBox).innerHTML = messageHTML;

		if (visibility == 'none') {
			document.getElementById("overlay").style.display = 'none';
		} else {
			document.getElementById("overlay").style.display = visibility;
		}
	}

	function displayList(displayPara) {
		var para = document.getElementById(displayPara);
		//alert(displayPara+'|'+displayPara.value);
		if (displayPara == 'selCreditCard') {
			para.style.display = 'block';
			document.getElementById('selBankAccount').style.display = 'none';
		} else if (displayPara == 'selBankAccount') {
			para.style.display = 'block';
			document.getElementById('selCreditCard').style.display = 'none';
		}
	}
</script>

</head>
<body>
	<div id="overlay" class="overlay" style="display: none"></div>

	<input type=button name=type value='Show Layer'
		onclick="setVisibility('sub3', 'inline');";>
	<input type=button name=type value='Hide Layer'
		onclick="setVisibility('sub3', 'none');";>
	<hr />
	<input type='checkbox' id='payPartial'
		onClick="changePartialPay(this.id,'partialPayText')">
	<input id='partialPayText' disabled='true' />

	<hr />
	<input type='checkbox' id='myCheck'
		onClick="changeDiv(this.id,'inputDiv')">
	<div id='inputDiv'>
		$<input id='partialPayText' />
	</div>
	<hr />
	<%
		BigDecimal bigD = new BigDecimal("10.12");
	%>

	<%
		Double chgD = Double.parseDouble(bigD.toString());
	%>

	<%=bigD%>
	<%=chgD%>
	<%=bigD.intValue()%>

	<hr />
	<a href="#" alt="Please enter password" class="tooltip"><img
		src="./images/icon.png" /></a>
	<hr />
	<div id="sub3" onClick="setVisibility('sub3', 'none');">
		<img src="./images/message.jpg" />
	</div>
	<div id="errorMessageBox"
		onClick="setVisibility('errorMessageBox', 'none');">Message
		Box345345</div>


	<p>
		<a
			href="mailto:terminix@terminix.com?subject=Question Regarding Terminix Products&body=Hi,%0D%0A%0D%0AI have some question regarding the Terminix Products/Serviecs as below:%0D%0A%0D%0AThanks,%0D%0A">Have
			a Customer Service Question?</a>
	</p>

	<button
		onclick='eventHandler("errorMessageBox", {"errorCode":"AuthReq", "errorMessage":["Authentication Required","Access Denide"]}, "inline")'>Error</button>

	<hr />
	<form action="/RegisterTestServlet">
		Name: <input type="text" id="name" name="name" /> Password: <input
			type="password" id="passwd" name="passwd" />
		<hr />
		<input type="radio" value="0" name="group1"
			onClick="displayList('selCreditCard')" />Credit or Debit Card (Visa,
		MasterCard, American Express, Discover) <input type="radio" value="1"
			name="group1" onClick="displayList('selBankAccount')" />Bank Account

		<p id="selCreditCard" style="display: none">Credit Cards....</p>
		<p id="selBankAccount" style="display: none">Bank Accounts....</p>
		<hr />
		<button type="submit">Submit</button>
	</form>


</body>
</html>

