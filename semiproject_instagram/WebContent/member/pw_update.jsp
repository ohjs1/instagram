<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${cp }/css/member_profile.css" />
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
<div id="wrap">
	<div id="wrap2">
		<div id="menu">
			<ul>
				<li><a href="${cp }/member/memberInfo">프로필 편집</a></li>
				<li><a href="${cp }/member/pwdupdate">비밀번호 변경</a></li>
				<li><a href="${cp }/member/logout">로그아웃</a></li>
			</ul>
		</div>
		<div id="form">
			<form method="post" action="${cp }/member/pwdupdate" onsubmit="validate(event)">
				<div >
					<div id="div_imgs">
						<label><img id="img" src="${cp }/upload/${sessionScope.profile}"></label>
					</div>
					<div id="div_nic">
						<h1>${sessionScope.nickname }</h1>
					</div>
				</div>
				<div>
					<input type="hidden" id="pw" value="${sessionScope.pwd }">
					<label for="pwd"><strong>이전 비밀번호</strong></label>
					<input type="text" name="pwd" id="pwd" class="input"><br>
					<label for="newPwd1"><strong>새 비밀번호</strong></label>
					<input type="text" name="newPwd1" id="newPwd1" class="input"><br>
					<label for="newPwd2"><strong>새 비밀번호 확인</strong></label>
					<input type="text" name="newPwd2" id="newPwd2" class="input"><br>
					<label></label>
					<input type="submit" value="비밀번호 변경" id="btn">
				</div>
			</form>
		</div>
	</div><!-- wrap2 -->
</div><!-- wrap -->
</body>
</html>









