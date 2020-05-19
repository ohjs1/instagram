<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>팔로우 리스트</title>
</head>
<body>
	<table>
		<tr>
		<c:if test="${bl==true}">
		<h1>팔로워</h1>
			<c:forEach var="vo" items='${list }'>
				<td><a href="${cp }/follow/move?youmember_no=${vo.getMember_no()}">${vo.getNickname() }</a></td>
				<td><a href="${cp }/follow/delete?youmember_no=${vo.getMember_no() }">삭제</a></td>			
				</tr><tr>
			</c:forEach>
		</c:if>
		<c:if test="${bl==false}">
		<h1>팔로잉</h1>
			<c:forEach var="vo" items='${list }'>
				<td><a href="${cp }/follow/move?youmember_no=${vo.getMember_no()}">${vo.getNickname() }</a></td>
				<td><a href="${cp }/follow/insert?youmember_no=${vo.getMember_no() }">팔로우</a></td>			
				</tr><tr>
			</c:forEach>
		</c:if>
		</tr>
	</table><br>
	<a href="javascript:history.back()">뒤로가기</a>
</body>
</html>