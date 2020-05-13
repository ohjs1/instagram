<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function validate(e) {
		var pw=document.getElementById("pw").value;
		var pwd=document.getElementById("pwd").value;
		var newPwd1=document.getElementById("newPwd1").value;
		var newPwd2=document.getElementById("newPwd2").value;
		if(newPwd1!=newPwd2){
			alert('두 비밀번호가 일치하는지 확인하세요.');
			e.preventDefault();
			return;
		}
		if(pw==pwd && pwd!=""){
			alert('비밀번호 변경 완료');
		}else{
			alert('이전 비밀번호가 잘못 입력되었습니다.');
			e.preventDefault();
		}
	}
</script>
</head>
<body>
<form method="post" action="${cp }/member/pwdupdate" onsubmit="validate(event)">
	<h3>닉네임</h3>
	<input type="text" id="pw" value="${sessionScope.pwd }">
	<input type="hidden" id="pw" value="${sessionScope.pwd }">
	<label for="pwd">이전 비밀번호</label> <input type="text" name="pwd" id="pwd"><br>
	<label for="newPwd1">새 비밀번호</label> <input type="text" name="newPwd1" id="newPwd1"><br>
	<label for="newPwd2">새 비밀번호 확인</label> <input type="text" name="newPwd2" id="newPwd2"><br>
	<input type="submit" value="비밀번호 변경">
</form>
<a href="${cp }/member/memberInfo">프로필 편집</a><br>
<a href="${cp }/member/pwdupdate">비밀번호 변경</a><br>
<a href="${cp }/member/logout">로그아웃</a><br>
</body>
</html>









