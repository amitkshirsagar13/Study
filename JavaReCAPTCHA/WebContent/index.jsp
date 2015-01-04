<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="net.tanesha.recaptcha.ReCaptcha"%>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory"%>

<html>
<head>
<title>CAPTCHA in Java using reCAPTCHA</title>
<script type="text/javascript">
	function verifyCardValidity(cardSelection) {
		var expiredCreditCardNumbers = '|32606|';

		var expiredCardMessageDiv = document
				.getElementById("expiredCardMessageDiv");
		var submitButton = document.getElementById("btnSubmitPayment");

		if (expiredCreditCardNumbers.indexOf(cardSelection.value) > 0
				&& cardSelection.value != 0) {
			expiredCardMessageDiv.style.display = 'block';
			submitButton.disabled = true;
		} else {
			expiredCardMessageDiv.style.display = 'none';
			submitButton.disabled = false;
		}

	}

	function randomDivGenerator() {
		var random = Math.floor(Math.random() * 1000 + 1000);
		while (random<1000 ||random>9999) {
			random = Math.floor(Math.random() * 1000 + 1000);
		}
		var randomNumberHidden = document.getElementById("randomNumberHidden");
		randomNumberHidden.value = random;
		randomNumber.innerHTML = random;
	}
</script>
</head>
<body>
	<h2>CAPTCHA in Java Application using reCAPTCHA</h2>
	<form action="./validate.jsp" method="post">
		<p>
			Username: <input type="text" name="user">
		</p>
		<p>
			Password: <input type="password" name="password">
		</p>
		<p>
			<%
				//ReCaptcha c = ReCaptchaFactory.newReCaptcha("6LdlHOsSAAAAAM8ypy8W2KXvgMtY2dFsiQT3HVq-",
				//		"6LdlHOsSAAAAACe2WYaGCjU2sc95EZqCI9wLcLXY", false);

				//String recaptchaString = c.createRecaptchaHtml(null, null);

				//out.print(recaptchaString);
				//recaptchaString = recaptchaString.replace("<", "&lt").replace(">", "&gt");
				//out.print(recaptchaString);
			%>
			<script type="text/javascript"
				src="https://api.recaptcha.net/challenge?k=6LdlHOsSAAAAAM8ypy8W2KXvgMtY2dFsiQT3HVq-"></script>
		<hr />
		Email: <input type="text" name="email">

		<jsp:include page="captcha.jsp" />

		<input type="submit" value="submit" /> <a
			href="http://www.google.com" target="_blank" class='btn'>Download
			PDF</a>

		</p>
	</form>

	<div id="expiredCardMessageDiv" style="display: none">
		<h3>Credit Card Expirated:</h3>
		<ul>
			<li><span>Your Credit Card are expired as below List.<br />****
					1111<br /></span></li>
		</ul>
	</div>
	<button value="Submit Payment" class="btnOrange" id="btnSubmitPayment"
		type="submit">
		<p>Submit Payment</p>
	</button>

<input type="button" class="defaultbutton" value="Return to Account Preferences Page" onclick="location.href='test'">


	<link rel="stylesheet" type="text/css"
		href="http://extranet.opinionlab.com/clients/terminix/onlineopinionV5/oo_style.css" />
	<script language="javascript" type="text/javascript"
		charset="windows-1252"
		src="http://extranet.opinionlab.com/clients/terminix/onlineopinionV5/oo_engine.min.js"></script>
	<script language="javascript" type="text/javascript"
		charset="windows-1252"
		src="http://extranet.opinionlab.com/clients/terminix/onlineopinionV5/oo_conf.js"></script>
	<noscript>This JavaScript enables OnlineOpinion, a method
		for collecting secure feedback data.</noscript>


	<table>
		<tr>
			<td><script type="text/javascript">
				function validateEmail(email) {
					//alert('::'+email.value+'::');
					var at = email.indexOf('@');
					var dot = email.lastIndexOf('.');
					if ((email != '')
							&& (at == -1 || at == 0 || dot == -1 || at > dot)) {
						alert('Failed validation Email');
					}
				}
			</script> <input type="text" id="email" onblur="validateEmail(this.value)" /></td>
		</tr>
	</table>

</body>
</html>