<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Instagram MainPage 입니다.</title>
<link rel="stylesheet" type="text/css" href="${cp}/css/main.css">
</head>
<body>
<%
	String file=request.getParameter("file");
	if(file==null){
		file="homefeed.jsp";
	}
%>
<div id="warp">
	<div id="header">
		<a href="${ cp }/layout.jsp?file=/story/story.jsp">스토리</a>
		<img src="${ cp }/images/logo.png" alt="logo" id="logo">
		<a href="${ cp }/layout.jsp?file=/board/insert.jsp">
			<img src="${ cp }/images/icon/writer.jpg" alt="글쓰기"/>
		</a>
		<a href="${ cp }/home"><img src="${ cp }/images/icon/home.jpg" alt="홈"/></a>
		<a href="${ cp }/direct/inbox"><img src="${ cp }/images/icon/dm.jpg" alt="다이렉트 메시지"/></a>
		<a href="${ cp }/layout.jsp?file=/board/myfeed.jsp">
			<img src="${ cp }/images/icon/location.jpg" alt="내피드로가게함(임시)"/>
		</a>
		<img src="${ cp }/images/icon/likes.jpg" alt="좋아요"/>
	</div>
	<div id="main">
		<jsp:include page="<%=file %>"/>	
	</div>
	<div id="footer"></div>
</div>
</body>
</html>