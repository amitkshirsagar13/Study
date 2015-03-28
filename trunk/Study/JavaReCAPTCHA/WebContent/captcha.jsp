<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<style>
.captcha {
	background-color: #c2b49a;
	color: #786e62;
	font: bold;
}

#randomDiv {
	background-color: #c2b49a;
	color: #786e62;
	font: bold;
}

#randomDivIn {
	background-color: #cbcdcc;
	color: #86858b;
	font: bold;
}
</style>
<table class="captcha">
	<tr>
		<td colspan="2" class="captcha"><strong>Enter code
				below:</strong></td>
	</tr>
	<tr>
		<td class="captcha" align="center"><div id="randomDivIn">
				<script type="text/javascript">
					var randomNumber = Math.floor(Math.random() * 1000 + 1000);
					while (randomNumber<1000 ||randomNumber>9999) {
						random = Math.floor(Math.random() * 1000 + 1000);
					}
					var randomNumberHidden = document
							.getElementById("randomNumberHidden");

					document
							.write("<input name='randomNumberHidden' type='hidden' size='5' value='"+randomNumber+"'/>");
					document.write("<strong>" + randomNumber + "</strong>");
				</script>
			</div></td>
		<td class="captcha"><div id="randomDiv">
				<input id="randomNumberInput" name="randomNumberInput" type="text"
					size="5" />
			</div></td>
</table>