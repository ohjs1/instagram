<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Instagram MainPage 입니다.</title>
<link rel="stylesheet" type="text/css" href="${cp}/css/main.css">
<%
	String header = (String)session.getAttribute("header");
	String main = (String)request.getAttribute("main");

%>
</head>
<body>

<div id="warp">
	<div id="header">
		<jsp:include page="<%=header %>"/>
	</div>
	<div id="main">
		<jsp:include page="<%=main %>"/>
	</div>
</div>
</body>
</html>