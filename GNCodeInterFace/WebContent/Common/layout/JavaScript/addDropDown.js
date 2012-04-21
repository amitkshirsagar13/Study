function copyToList(from,to)
{
  fromList = eval('document.forms[0].' + from);
  toList = eval('document.forms[0].' + to);
  if (toList.options.length > 0 && toList.options[0].value == 'temp')
  {
    toList.options.length = 0;
  }
  var sel = false;
  for (i=0;i<fromList.options.length;i++)
  {
    var current = fromList.options[i];
    if (current.selected)
    {
      sel = true;
      if (current.value == 'temp')
      {
        alert ('You cannot move this text!');
        return;
      }
      
	  if (fromList.options[i].value == 'POC')
      {
        alert ('You cannot move POC!');
        return;
      }
      
	      txt = current.text;
	      val = current.value;
	      option = new Option(txt,val);
	      option.className="moved";
	      toList.options[toList.length] = option;
	      fromList.options[i] = null;
	      i--;
	 if (actionWord=="USER"){
	    var td = document.getElementById('userRole');
	  	var oldValues = td.innerHTML;
	  	var countUsers = (oldValues.split("checkbox").length - 1);  	
	  	td.innerHTML = oldValues + '<INPUT type=checkbox value="0" name=dynamicprops(relUserType'+countUsers+') disabled><BR>';
	 }else if (actionWord=="REMOVE"){
	 	var td = document.getElementById('<br>');
	  	var oldValues = td.innerHTML;
	  	var countUsers = (oldValues.split("checkbox").length - 1);  	
	  	td.innerHTML = oldValues + '<INPUT type=checkbox value="0" name=dynamicprops(relUserType'+countUsers+') disabled><BR>';
	 }
    }
  }
  var td = document.getElementById('userRole');
  if (!sel) alert ('You haven\'t selected any options!');
}

function allSelect()
{
  List = document.forms[0].relUserId;
  if (List.length && List.options[0].value == 'temp') return;
  for (i=0;i<List.length;i++)
  {
     List.options[i].selected = true;
  }
}