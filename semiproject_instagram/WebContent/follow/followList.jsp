<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>팔로워 리스트</title>
</head>
<body>
	<table>
		<tr>
		${list }
		<c:forEach var="vo" items='${list }'>
			<td>${vo.getId() }</td>
			<td>${vo.getNickname() }</td>
			<td><a href="#">삭제</a></td>			
			</tr><tr>
		</c:forEach>
		</tr>
	</table>
</body>
</html>