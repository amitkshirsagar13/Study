  function getEmployeeNumber(String)
  {
  var xmlHttp;

	xmlHttp=createXMLObject();
  
    xmlHttp.onreadystatechange=function()
      {
      if(xmlHttp.readyState==4)
        {
        	document.getElementById("EmployeeDetails").innerHTML=xmlHttp.responseText;
        }
      }
      	var url="../SqlGatewayServlet.do";
    xmlHttp.open("GET",url,true);
    xmlHttp.send(null);
  }