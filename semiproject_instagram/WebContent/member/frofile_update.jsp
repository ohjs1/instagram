<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:forEach var="vo" items="${vo }">
비밀번호:${vo.pwd }<br>
이름:${vo.name }<br>
닉네임:${vo.nickname }<br>
</c:forEach>
<form method="post" action="">
	<label for="frofile" ><img src="../images/frofile.jpg" width="120px" style="border: 1px solid" id="img"></label>
	<input type="file" name="frofile" id="frofile" style="display: none" onclick="test()"><br>
	<input type="text" name="id" placeholder="아이디"><br>
	<input type="text" name="name" placeholder="사용자 이름"><br>
	<input type="submit" value="제출">
</form>
</body>
</html>