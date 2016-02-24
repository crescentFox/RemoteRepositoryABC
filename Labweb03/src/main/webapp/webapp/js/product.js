var request = null;
function doSendGetRequest(url, id, action) {
	request = new XMLHttpRequest();
	request.onreadystatechange = doReadyStateChange;

	var querystring = url + "?action=" + action + "&id=" + id + "&dummy="
			+ new Date().getTime();
	// console.log(querystring);
	request.open("GET", querystring);
	request.send(null);
	//顯示圖片
	document.getElementById("img1").style.display="inline";
}
function doSendPostRequest(url, id, action) {
	request = new XMLHttpRequest();
	request.onreadystatechange = doReadyStateChange;
	var querystring = "action=" + action + "&id=" + id;
	request.open("POST", url);
	request.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	request.send(querystring);
	//顯示圖片
	document.getElementById("img1").style.display="inline";
}
function processText(data) {
	var show = data;
	var index = data.indexOf(":");
	if (index != -1) {
		show = data.substring(0, index);
		var temp = data.substring(index + 1);
		var array = temp.split(",");
		document.forms[0].id.value = array[0];
		document.forms[0].name.value = array[1];
		document.forms[0].price.value = array[2];
		document.forms[0].make.value = array[3];
		document.forms[0].expire.value = array[4];
	}
	var showTextNode = document.createTextNode(show);
	var spanElement = document.getElementsByTagName("div")[2];
	spanElement.appendChild(showTextNode);
}
function processXML(data) {
	console.log(data);
}
function processJSON(data) {
	var json = JSON.parse(data);
	//console.log(json);
	// [{"text":"ID是必要欄位","hasMoreData":false}]
	var text = json[0].text;
	var hasMoreData = json[0].hasMoreData;
	console.log(hasMoreData);
	// {"id":1,"name":"Coca Cola","price":20.0,"make":"2007-01-01","expire":365}

	if (hasMoreData) {
		var id = json[1].id;
		var name = json[1].name;
		var price = json[1].price;
		var make = json[1].make;
		var expire = json[1].expire;
		document.forms[0].id.value = id;
		document.forms[0].name.value = name;
		document.forms[0].price.value = price;
		document.forms[0].make.value = make;
		document.forms[0].expire.value = expire;
	}
	var showTextNode = document.createTextNode(text);
	var spanElement = document.getElementsByTagName("div")[2];
	spanElement.appendChild(showTextNode);
	// console.log(spanElement.firstChild.nodeType);
	// spanElement.replaceChild(showTextNode,oldnodes);
	// var textnode1=document.createTextNode(nodes[1].firstChild.nodeValue);
	// nodes[0].replaceChild(textnode1,nodes[0].firstChild);
}
function doReadyStateChange() {
	if (request.readyState == 4) {
		if (request.status == 200) {
			// console.log(request.responseText);
			 processText(request.responseText);
			// processXML(request.responseXML);
			//processJSON(request.responseText);
			//隱藏圖片
			document.getElementById("img1").style.display="none";
		} else {
			console.log("error code=" + request.status + "message:"
					+ request.statusText);
		}
	}
}