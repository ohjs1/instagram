<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>로그인 성공</h1>
세션에 저장된 회원번호 : ${sessionScope.member_no }<br>
세션에 저장된 아이디 : ${sessionScope.id }<br>
세션에 저장된 비밀번호 : ${sessionScope.pwd }<br>
<a href="${cp }/feed/myfeed.jsp">마이피드</a><br>
<a href="${cp }/member/memberInfo">프로필 편집</a><br>
<a href="${cp }/member/pwdupdate">비밀번호 변경</a><br>
<a href="${cp }/member/logout">로그아웃</a><br>
<a href="${ cp }/layout.jsp">홈으로</a>
</body>
</html>