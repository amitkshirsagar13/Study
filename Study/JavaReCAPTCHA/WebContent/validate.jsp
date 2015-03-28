
<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@ page import="net.tanesha.recaptcha.ReCaptchaImpl"%>
<%@ page import="net.tanesha.recaptcha.ReCaptchaResponse"%>

<html>
<head>
<title>CAPTCHA in Java using reCAPTCHA</title>
</head>
<body>
	<h2>CAPTCHA in Java Application using reCAPTCHA</h2>
	<p>
		<%
			String randomNumberHidden = request.getParameter("randomNumberHidden");
			String randomNumberInput = request.getParameter("randomNumberInput");
			StringBuffer responseString = new StringBuffer("--");
			responseString.append("<br/>");
			responseString.append("--" + randomNumberHidden + "::" + randomNumberInput + "--");

			if (randomNumberHidden == null) {
				responseString.append("Empty Security Text...");
			}
			if (randomNumberInput == null) {
				responseString.append("Empty Security Text...");
			}
			if (!randomNumberHidden.equalsIgnoreCase(randomNumberInput)) {
				responseString.append("Security Text not matching...");
			}
			responseString.append("Successfully Security Text matching...");
			out.print(responseString.toString());

			String email = request.getParameter("email");
			System.out.println("Before Encoded value is " + new String(email));
			String converted = new String(Base64.encodeBase64(email.toString().getBytes()));
			String stringFromBase = new String(Base64.decodeBase64(converted.toString().getBytes()));
			System.out.println("Encoded value is " + new String(converted));
			System.out.println("Decoded value is " + new String(stringFromBase));
			System.out.println("Original Value:"+new String(Base64.decodeBase64("MzI4NDQ5NjI=".getBytes())));
			
			
		%>
	</p>
	<a href=".">Home</a>
</body>
</html>