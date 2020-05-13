<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	
</script>
</head>
<body>
<h1>비밀번호 찾기</h1>
<form method="post" action="${cp }/member/findpwd">
	<input type="text" name="id" placeholder="아이디"><br>
	<input type="text" name="name" placeholder="이름"><br>
	<input type="submit" value="비밀번호 찾기"><br>
	<span>${msg }</span>
</form>
계정이 없으신가요? <a href="${cp }/member/join.jsp">가입하기</a><br>
계정이 있으신가요? <a href="${cp }/member/login.jsp">로그인</a><br>
</body>
</html>