<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>비밀번호 찾기</h1>
<form>
	<input type="text" name="id" placeholder="아이디"><br>
	<input type="password" name="name" placeholder="비밀번호"><br>
	<input type="submit" value="비밀번호 찾기"><br>
</form>
계정이 있으신가요? <a href="${cp }/member/login.jsp">로그인</a><br>
계정이 없으신가요? <a href="${cp }/member/join.jsp">가입하기</a>
</body>
</html>