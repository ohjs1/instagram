<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>로그인</h1>
<form method="post" action="${cp }/member/login">
	<input type="text" name="id" placeholder="아이디"><br>
	<input type="password" name="pwd" placeholder="비밀번호"><br>
	<input type="submit" value="로그인"><br>
</form>
<span>${errMsg }</span><br>
<a href="${cp }/member/findPwd.jsp">비밀번호를 잊으셨나요?</a><br>
계정이 없으신가요? <a href="${cp }/member/join.jsp">가입하기</a>
</body>
</html>