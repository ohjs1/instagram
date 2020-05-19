<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상대피드</title>
</head>
<body>
<h1>${id }</h1>
	<a href="${cp }/follow/insert?youmember_no=${member_no}">팔로우</a>
	<a href="${cp }/follow/select?mymember_no=${member_no}"  onclick="javascript:window.open(this.href,'new','left=50, top=50, width=600, height=600')">팔로워</a>
	<a href="${cp }/follow/select?youmember_no=${member_no}">팔로잉</a>
</body>
</html>