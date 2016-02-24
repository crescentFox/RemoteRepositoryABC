<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/main.css"/>" />

<title>Product</title>
<script type="text/javascript" src="../js/json2.js"></script>
<script type="text/javascript" src="../js/product.js"></script>
<script type="text/javascript">
	var request = new XMLHttpRequest();
	function clearForm() {
		var inputs = document.getElementsByTagName("input");
		for (var i = 0; i < inputs.length; i++) {
			if (inputs[i].type == "text") {
				inputs[i].value = "";
			}
		}
		var divElement =document.getElementsByTagName("div")[2];
		divElement.removeChild(divElement.firstChild);
	}

	//	function doffocus() {
	//		var nodes=document.getElementsByTagName("h3");
	// 	var text0 = nodes[0].firstChild.cloneNode(false);
    // 	var text1 = nodes[1].firstChild.cloneNode(false);
    // 	nodes[0].replaceChild(text1, nodes[0].firstChild);
    // 	nodes[1].replaceChild(text0, nodes[1].firstChild);
    // 	var formElement = document.getElementsByTagName("form")[0];
    // 	alert("action="+formElement.getAttribute("action"));	
    // 	formElement.setAttribute("action", "/LabWeb01/pages/product.view");
    
    
	//		var textnode0=document.createTextNode(nodes[0].firstChild.nodeValue);
	//		var textnode1=document.createTextNode(nodes[1].firstChild.nodeValue);
	//		nodes[0].replaceChild(textnode1,nodes[0].firstChild);
	//		nodes[1].replaceChild(textnode0,nodes[1].firstChild);
	//      var n=math.floor(math.random())
	//	}

	function doBlur() {
		var id = document.getElementsByName("id")[0].value;
		var url = "/LabWeb/pages/product.view";
		//		var action="textText";
		  //   var action = "textXml";
		var action = "textJson";
		//		doSendPostRequest(url, id, action);
		doSendGetRequest(url, id, action);
	}
</script>
</head>
<body>
	<h3>Welcome,</h3>
	<h3>Product Table</h3>
	<form action="product.controller" method="get">
		<table>
			<tr>
				<td><div style="color: red; display: inline">${success}</div>
					<div class="error">${exception}</div></td>
			</tr>
			<tr>
				<td>ID :</td>
				<td><input type="text" name="id" value="${param.id}"
					onblur="doBlur()" onfocus="clearForm()"></td>
				<td><div class="error">${ErrorMsg.id}</div><img id="img1" style="display:none" src="../img/ajax-loader.gif"></td>
			</tr>
			<tr>
				<td>Name :</td>
				<td><input type="text" name="name" value="${param.name}"></td>
				<td><div class="error">${ErrorMsg.name}</div></td>
			</tr>

			<tr>
				<td>Price :</td>
				<td><input type="text" name="price" value="${param.price}"></td>
				<td><div class="error">${ErrorMsg.price}</div></td>
			</tr>
			<tr>
				<td>Make :</td>
				<td><input type="text" name="make" value="${param.make}"></td>
				<td><div class="error">${ErrorMsg.make}</div></td>
			</tr>
			<tr>
				<td>Expire :</td>
				<td><input type="text" name="expire" value="${param.expire}"></td>
				<td><div class="error">${ErrorMsg.expire}</div></td>
			</tr>
			<tr>
				<td><input type="submit" name="prodaction" value="Insert">
					<input type="submit" name="prodaction" value="Update"> <input
					type="submit" name="prodaction" value="Delete"></td>
				<td><input type="submit" name="prodaction" value="Select">
					<input type="button" value="Clear" onclick="clearForm()"></td>
			</tr>
		</table>

	</form>
</body>
</html>