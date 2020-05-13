<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 피드</title>
</head>
<body>
	<h1>${id }</h1>
	<a href="${cp }/follow/select?mymember_no=${member_no}">팔로워</a>
	<a href="${cp }/follow/select?youmember_no=${member_no}">팔로잉</a>
</body>
</html>