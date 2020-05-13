<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>${id }</h1>
	<form action="">
		<input type="submit" value="팔로우">
	</form>
	<form action="${cp }/follow/select" method="post">
		<input type="submit" value="팔로워">
	</form>
	<form action="">
		<input type="submit" value="팔로잉">
	</form>
</body>
</html>