<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#login_wrap{
		width: 850px;
		height: 650px;
		margin: auto;
		margin-top: 100px;
	}
	#login_phone{
		width: 50%;
		height: 100%;
		float: left;
	}
	#login_wrap img{
		width: 400px;
		height: 600px;
		padding: 10px;
		margin: 13px;
	}
	#form_r1{
		width: 49%;
		height: 100%;
		float: right;
		margin-top: 40px;
	}
	#form_r2{
		width: 100%;
		height: 60%;
		border: 1px solid gray;
		border-radius: 2%;
	}
	#login_logo img{
		width: 180px;
		height: 60px;
		padding: 16px;
		margin-left: 100px;
		margin-bottom: 20px;
    	margin-top: 30px;
	}
	#form{
		text-align: center;
		width: 70%;
		margin: auto;
	}
	input{
		width: 260px;
		height: 40px;
		margin-bottom: 10px;
	}
	#form_r3{
		margin-top: 20px;
		text-align: center;
	}
	#form_r4{
		border: 1px solid gray;
		text-align: center;
		padding: 20px;
		margin-top: 10px;
	}
	#btn{
		background-color: #0095f6;
		color: white;
		border-radius: 10%;
	}
</style>
</head>
<body>
<div id="login_wrap">
	<div id="login_phone">
		<img src="${cp }/images/instagram.png">
	</div>
	<div id="form_r1">
		<div id="form_r2">
			<div id="login_logo">
				<img src="${cp }/images/logo.png">
			</div>
			<div id="form">
				<form method="post" action="${cp }/member/login" name="frm">
					<input type="text" name="id" placeholder="아이디"><br>
					<input type="password" name="pwd" placeholder="비밀번호"><br>
					<input type="submit" value="로그인" id="btn"><br>
				</form>
			</div>
			<div id="form_r3">
				<span>${errMsg }</span><br>
				<a href="${cp }/member/findPwd.jsp">비밀번호를 잊으셨나요?</a><br>
			</div>
		</div>
		<div id="form_r4">
			계정이 없으신가요? <a href="${cp }/member/join.jsp">가입하기</a>
		</div>
	</div>
</div>
</body>
</html>