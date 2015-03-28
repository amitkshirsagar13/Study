            var store = new Array();
            var value = new Array();		

            <c:set var="i" value="${0}"/>
            <c:if test="${fn:length(release) > 0}">
                <c:forEach var="release" items="${release}">
                    store[${i}] = new Array(
                    <c:forEach var="release" items="${release}">
                        '${release.relName}',
                    </c:forEach>
                    '');
                    <c:set var="i" value="${i+1}"/>
                </c:forEach>
            </c:if>
            <c:set var="i" value="${0}"/>
            <c:if test="${fn:length(release) > 0}">
                <c:forEach var="release" items="${release}">
                    value[${i}] = new Array(
                    <c:forEach var="release" items="${release}">
                        '${release.relPhase}@${release.relId}#${release.relName}',
                    </c:forEach>
                    '');
                    <c:set var="i" value="${i+1}"/>
                </c:forEach>
            </c:if>
            function populate(){
                var box = document.forms[0].application;
                var number = box.selectedIndex;
                if (!number) return;
                var list = store[number-1];
                var val = value[number-1];
                var box2 = document.forms[0].release;
                box2.disabled=false;
                box2.options.length = 0;
                for(i=0;i<list.length;i++){
                    box2.options[i] = new Option(list[i],val[i]);
                }
            }
			
			            //Function to check whether the dropdowns are checked or not
            function check(){
            if(document.forms[0].applId.value=="--"){
            alert("Please select Application");
            return false;
            }
            if(document.forms[0].empId.value=="--"){
            alert("Please select POC");
            return false;
            }
            if(document.forms[0].relPhase.value=="--"){
            alert("Please select Phase");
            return false;
            }
            else
            return true;
            }