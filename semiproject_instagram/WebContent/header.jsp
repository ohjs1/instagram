<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<a href="${ cp }/story/story.jsp">스토리</a>
		<img src="${ cp }/images/logo.png" alt="logo" id="logo">
		<a href="${ cp }/board/insert">
			<img src="${ cp }/images/icon/writer.jpg" alt="글쓰기"/>
		</a>
		<a href="${ cp }/layout.jsp"><img src="${ cp }/images/icon/home.jpg" alt="홈"/></a>
		<a href="${ cp }/dm/inbox?member_no=${member_no}"><img src="${ cp }/images/icon/dm.jpg" alt="다이렉트 메시지"/></a>
		<a href="${ cp }/feed/myfeed.jsp">
			<img src="${ cp }/images/icon/location.jpg" alt="내피드로가게함(임시)"/>
		</a>
		<img src="${ cp }/images/icon/likes.jpg" alt="좋아요"/>
</body>
</html> 