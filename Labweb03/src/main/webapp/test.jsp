<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
var httpXML=null;
	function click1() {
		var data="id=1&name="+encodeURI("貓貓蟲"); 
        httpXML=new XMLHttpRequest();
		httpXML.open("GET", "/LabWeb/pages/product.controller?"+data);
   	httpXML.send(null);
		
	//	httpXML.open("POST","/LabWeb/pages/product.controller");
	//	httpXML.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	//	httpXML.send(data);
	}
	function over() {
		var cat = document.getElementById("catbug");
		cat.style.color = "blue"
	}
	function out() {
		var cat = document.getElementById("catbug");
		cat.style.color = "red"
	}
	function doClick() {
		document.getElementById("img1").style.display = "inline";
		setTimeout(function() {
			document.getElementById("img1").style.display = "none"
		}, 10000);
	}
</script>
<title>Insert title here</title>
</head>
<body>
	<span id="catbug" onmouseover="over()" onmouseout="out()">this
		is a catbug</span>
	<input type="button" onclick="click1()" value="servlet">
	<input type="button" value="Load" onclick="doClick()">
	<img id="img1" src="img/ajax-loader.gif" style="display: none">


	<%@ page import="model.*"%>
	<%@ page import="java.util.List"%>
	<%
		CustomerService server1 = new CustomerService();
		CustomerBean bean1 = server1.login("Alex", "A");
		CustomerBean bean2 = server1.login("Babe", "B");
	%>
	<br>
	<%=bean1%>
	<br>
	<%=bean2%>


	<%@ page import="javax.naming.*"%>
	<%@ page import="javax.sql.DataSource"%>
	<%@ page import="java.sql.*"%>
	<%
		Context ctx = new InitialContext();
		DataSource dataSource = (DataSource) ctx
				.lookup("java:comp/env/jdbc/xxx");
		Connection conn = dataSource.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery("select * from dept");
		while (rset.next()) {
			String col1 = rset.getString(1);
			String col2 = rset.getString(2);
			out.println("<h1>" + col1 + "," + col2 + "</h1>");
		}
		rset.close();
		stmt.close();
		conn.close();
	%>
</body>
</html>
