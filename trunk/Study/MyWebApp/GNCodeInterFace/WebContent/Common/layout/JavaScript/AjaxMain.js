function saveCourtGNCodeForm(formName){
	document.getElementById('court_name').disabled = false;
	document.getElementById('normalized_court').disabled = false;
	document.getElementById('gncode_id').disabled = false;
	document.getElementById(formName).submit();
}

function saveJVGNCodeForm(formName){
	document.getElementById('court').disabled = false;
	document.getElementById('city').disabled = false;
	document.getElementById('state_name').disabled = false;
	document.getElementById('normalized_court').disabled = false;
	document.getElementById('gncode_id').disabled = false;
	document.getElementById(formName).submit();
}


function resetGNCodeForm(formName){
	document.getElementById('reset').value='1';
	document.getElementById(formName).submit();
}

function showResult(str,qName,qTable) {
	
	if (str.length == 0) {
		document.getElementById(qName+"List").innerHTML = "&nbsp;";
		document.getElementById(qName+"List").style.border = "0px";
		return;
	}
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			document.getElementById(qName+"List").innerHTML = xmlhttp.responseText;
			document.getElementById(qName+"List").style.border = "1px solid #A5ACB2";
		}
	}
	if (qTable == 'GNCODE_JV_LOOKUP'){
		qName3 = 'state_name';
		qName2 = 'city';
		qName1 = 'court';
		qNameValue1 = document.getElementById(qName1).value;
		qNameValue2 = document.getElementById(qName2).value;
		qNameValue3 = document.getElementById(qName3).value;
		xmlhttp.open("POST", "LiveSearch.do", true);
		xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		
		xmlhttp.send("qName="+qName+"&qNameValue="+str+"&qTable="+qTable
				+"&qName1="+qName1+"&qName2="+qName2+"&qName3="+qName3
				+"&qNameValue1="+qNameValue1+"&qNameValue2="+qNameValue2+"&qNameValue3="+qNameValue3);
		
	}else if (qTable == 'GNCODE_LEGACY_JV_LOOKUP'){
		qName1 = 'court';
		qName2 = 'county';
		qName3 = 'county1';
		qName4 = 'parish';
		qName5 = 'publication';
		qName6 = 'state_name';
		
		
		qNameValue1 = document.getElementById(qName1).value;
		qNameValue2 = document.getElementById(qName2).value;
		qNameValue3 = document.getElementById(qName3).value;
		qNameValue4 = document.getElementById(qName4).value;
		qNameValue5 = document.getElementById(qName5).value;
		qNameValue6 = document.getElementById(qName6).value;
		xmlhttp.open("POST", "LiveSearch.do", true);
		xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		
		xmlhttp.send("qName="+qName+"&qNameValue="+str+"&qTable="+qTable
				+"&qName1="+qName1+"&qName2="+qName2+"&qName3="+qName3
				+"&qNameValue1="+qNameValue1+"&qNameValue2="+qNameValue2+"&qNameValue3="+qNameValue3
				+"&qName4="+qName4+"&qName5="+qName5+"&qName6="+qName6
				+"&qNameValue4="+qNameValue4+"&qNameValue5="+qNameValue5+"&qNameValue6="+qNameValue6);
		
	}else if (qTable == 'GNCODE_CASELAW_LOOKUP'){
		xmlhttp.open("POST", "LiveSearch.do", true);
		xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		
		xmlhttp.send("qName="+qName+"&qNameValue="+str+"&qTable="+qTable);
		
	}else if (qTable == 'gncode_mapping_info'){
		xmlhttp.open("POST", "LiveSearch.do", true);
		xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		
		xmlhttp.send("qName="+qName+"&qNameValue="+str+"&qTable="+qTable);
		
	}
	

}

function updateLiveDataBox(qName,qNameValue){	
	document.getElementById(qName).value = qNameValue;
	document.getElementById(qName+"List").innerHTML = "&nbsp;";
	document.getElementById(qName+"List").style.border = "0px";
}

function getGNCodeInfoData(searchTerm, qTable) {
	if (searchTerm=='GNCODEINFO'){
		qName=document.getElementById('normalized_court').value;
		if (qName==''){
			alert("Normalized Name is empty. Please select the court Value.");
			return;
		}
	}else if (searchTerm=='GNCODEID'){
		qName=document.getElementById('gncode_id').value;
		if (qName==''){
			alert("GNCode ID is empty. Please enter the required GNCode ID.");
			return;
		}
	}else if (searchTerm=='GNCODEVALUE'){
		qName=document.getElementById('gncode_value').value;
		if (qName==''){
			alert("GNCode Value is empty. Please enter the required GNCode Value.");
			return;
		}
	}
	
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			loadGNCodeData(xmlhttp);
		}
	}
	xmlhttp.open("POST", "GetGNCodeInfoServlet.do", true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	//alert(qTable);
	xmlhttp.send("qName="+qName+"&qSearchTerm="+searchTerm+"&forTable="+qTable);
}



function loadGNCodeData(xmlhttp){
	var data = eval('(' + xmlhttp.responseText + ')');
	if (data.gncode_id == '-1'){
		document.getElementById('gncode_id').value = '';
		alert('No GNCode Mapping Information found in Database for given search...')
	}else{
		document.getElementById('gncode_id').value = data.gncode_id;
	}
	document.getElementById('gncode_value').value = data.gncode_value;
	document.getElementById('state').value = data.state;
	document.getElementById('court_type').value = data.court_type;
	document.getElementById('system').value = data.system;
	document.getElementById('subject_matter').value = data.subject_matter;
	document.getElementById('location').value = data.location;
	document.getElementById('court_level').value = data.court_level;
	document.getElementById('court_long_name').value = data.court_long_name;
	document.getElementById('court_short_name').value = data.court_short_name;
}

function updateCourtDataBox(qName,courtNameValue,normalizedCourtName){	
	document.getElementById('court_name').value = courtNameValue;
	document.getElementById('normalized_court').value = normalizedCourtName;
	//document.getElementById('normalized_courtCell').title = normalizedCourtName;
	document.getElementById(qName+"List").innerHTML = "&nbsp;";
	document.getElementById(qName+"List").style.border = "0px";
}

function updateJVDataBox(qName,courtNameValue,normalizedCourtName){	
	document.getElementById(qName).value = courtNameValue;
	document.getElementById('normalized_court').value = normalizedCourtName;
	//document.getElementById('normalized_courtCell').title= normalizedCourtName;
	document.getElementById('normalized_court').title= normalizedCourtName;
	document.getElementById(qName+"List").innerHTML = "&nbsp;";
	document.getElementById(qName+"List").style.border = "0px";
}

function updateLegacyJVDataBox(qName,courtNameValue,normalizedCourtName){	
	document.getElementById(qName).value = courtNameValue;
	document.getElementById('normalized_court').value = normalizedCourtName;
	//document.getElementById('normalized_courtCell').title = normalizedCourtName;
	document.getElementById(qName+"List").innerHTML = "&nbsp;";
	document.getElementById(qName+"List").style.border = "0px";
}

function getGNCODEIDChange(checkBox, formValue){
	if (document.getElementById(checkBox).checked){
		if (formValue == 'CASELAW'){
			//document.getElementById('court_name').value = '';
			document.getElementById('court_name').disabled = true;
		}else if (formValue == 'JV') {
			//document.getElementById('state_name').value = '';
			document.getElementById('state_name').disabled = true;
			//document.getElementById('city').value = '';
			document.getElementById('city').disabled = true;
			//document.getElementById('court').value = '';
			document.getElementById('court').disabled = true;
		}else if (formValue == 'LEGACY') {
			//document.getElementById('state_name').value = '';
			document.getElementById('state_name').disabled = true;
			//document.getElementById('court').value = '';
			document.getElementById('court').disabled = true;
			document.getElementById('county').disabled = true;
			document.getElementById('county1').disabled = true;
			document.getElementById('parish').disabled = true;
			document.getElementById('publication').disabled = true;
		}
		//document.getElementById('normalized_court').value = '';
		document.getElementById('GNCODEINFO').value = 'Get GNCode Information from GNCode Value';
		document.getElementById('GNCODEINFO').id = 'GNCODEVALUE';
		document.getElementById('gncode_value').disabled = false;
	}else{
		document.getElementById('GNCODEVALUE').value = 'Get GNCode Information from Normalized Name';
		document.getElementById('GNCODEVALUE').id = 'GNCODEINFO';
		document.getElementById('gncode_value').disabled = true;
		//document.getElementById('gncode_value').value = '';
		if (formValue == 'CASELAW'){
			document.getElementById('court_name').disabled = false;
		}else if (formValue == 'JV') {
			document.getElementById('state_name').disabled = false;
			document.getElementById('city').disabled = false;
			document.getElementById('court').disabled = false;
		}else if (formValue == 'LEGACY') {
			document.getElementById('state_name').disabled = false;
			document.getElementById('court').disabled = false;
			document.getElementById('county').disabled = false;
			document.getElementById('county1').disabled = false;
			document.getElementById('parish').disabled = false;
			document.getElementById('publication').disabled = false;
		}
	}
}