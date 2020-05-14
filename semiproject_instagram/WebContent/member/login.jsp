<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#login_wrap{
		width: 950px;
		height: 650px;
		border: 1px solid;
		margin: auto;
	}
	#login_phone{
		width: 50%;
		height: 100%;
		border:1px solid blue;
		float: left;
	}
	#login_wrap img{
		border:1px solid red;
		width: 400px;
		height: 600px;
		padding: 10px;
		margin: 20px;
	}
	#form_r{
		width: 49%;
		height: 100%;
		border:1px solid green;
		float: right;
	}
	#login_logo img{
		width: 180px;
		height: 60px;
		padding: 20px;
		margin-left: 110px;
	}
	#form{
		text-align: center;
		border: 1px solid;
		width: 70%;
		margin: auto;
	}
	input{
		width: 260px;
		height: 40px;
		margin-bottom: 10px;
	}
</style>
</head>
<body>
<h1>로그인</h1>
<div id="login_wrap">
	<div id="login_phone">
		<img src="../images/instagram.png">
	</div>
		<div id="form_r">
			<div id="login_logo">
				<img src="../images/logo.png">
			</div>
			<div id="form">
				<form method="post" action="${cp }/member/login">
					<input type="text" name="id" placeholder="아이디"><br>
					<input type="password" name="pwd" placeholder="비밀번호"><br>
					<input type="submit" value="로그인"><br>
				</form>
			</div>
			<span>${errMsg }</span><br>
			<a href="${cp }/member/findPwd.jsp">비밀번호를 잊으셨나요?</a><br>
			계정이 없으신가요? <a href="${cp }/member/join.jsp">가입하기</a>
		</div>
</div>
</body>
</html>