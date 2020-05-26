<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%-- <link rel="stylesheet" type="text/css" href="${cp }/css/member_profile.css" /> --%>
<style type="text/css">
	.input{width: 258px;height: 36px;margin: 4px;border: none;}
	#btn{
		width: 260px;
		margin-top: 10px;
		background-color: #0095f6;
		color: white;
		border-radius: 10%;
		opacity: 0.5;
	}
	#join_img{
		width: 170px;
		height: 70px;
		margin-top: 20px;
	}
	#join_wrar{
		width: 100%;
		height: 510px;
		border: 1px #dbdbdb solid;
		text-align: center;
	}
	#join{
		width: 350px;
		margin: auto;
		margin-top: 100px;
	}
	#join_login{
		width: 100%;
		border: 1px #dbdbdb solid;
		margin-top: 20px;
   	 	height: 50px;
   	 	text-align: center;
   	 	padding-top: 20px;
	}
	#join_p{
		color: #8e8e8e;
		font-size: 0.8em;
	}
	h2{
		color: #8e8e8e;
		font-size: 1.2em;
	}
	span{
		width: 20px;
		height: 20px;
		display: inline-block;
	}
	.inputForm{
		border: 1px solid #dbdbdb;
		width: 310px;
    	margin-left: 19px;
	}
</style>
</head>
<body onload="btnDisabled()">
<div id="join">
	<div id="join_wrar">
		<div>
			<img src="${cp }/images/logo.png" id="join_img">
			<h2>친구들의 사진과 동영상을 보려면<br>가입하세요.</h2>
		</div>
		<form name="joinForm" method="post" action="${cp }/member/insert">
			<div class="inputForm">
				<input type="text" id="id" name="id" placeholder="아이디" onblur="idcheck()" class="input">
				<span><img src="" id="check"></span>
			</div>
			<div class="inputForm">
				<input type="text" name="name" id="name" placeholder="성명" onblur="nameCheck()" class="input">
				<span><img src="" id="nameCheck2"></span>
			</div>
			<div class="inputForm">
				<input type="text" id="nickname" name="nickname" placeholder="닉네임" onblur="nickNamecheck()" class="input">
				<span><img src="" id="nickCheck"></span>
			</div>
			<div class="inputForm">
				<input type="password" name="pwd" id="pwd" placeholder="비밀번호" onblur="pwdCheck()" class="input">
				<span><img src="" id="pwdCheck2"></span>
			</div>
			<input type="submit" value="가입" id="btn" class="input"><br>
			<p id="join_p">가입하면 Instagram의 약관, 데이터 정책 및 쿠키<br> 정책에 동의하게 됩니다.</p>
		</form>
	</div>
	<div id="join_login">
		계정이 있으신가요? <a href="${cp }/member/login.jsp">로그인</a>
	</div>
</div>
<script type="text/javascript">
	var check=document.getElementById("check");
	var nickCheck=document.getElementById("nickCheck");
	var nameCheck2=document.getElementById("nameCheck2");
	var pwdCheck2=document.getElementById("pwdCheck2");
	
	var name="";
	var pwd="";
	
	var btn=document.getElementById("btn");
	var srcCheck="http://192.168.0.37:8081${cp}/images/o.PNG";
	var xhrId=null;
	
	function idcheck() {
		var id=document.getElementById("id").value;
		if(id!=""){
			xhrId=new XMLHttpRequest();
			xhrId.onreadystatechange=idcheckOk;
			xhrId.open('get','${cp}/member/idcheck?id='+id,true);
			xhrId.send();
		}else{
			check.src="${cp}/images/x.PNG";
		}
	}
	function idcheckOk() {
		if(xhrId.readyState==4 && xhrId.status==200){
			var data=xhrId.responseText;
			var json=JSON.parse(data);
			if(json.idcheck=="o"){
				check.src="${cp}/images/"+json.idcheck+".PNG";
				btnDisabled();
			}else{
				check.src="${cp}/images/"+json.idcheck+".PNG";
			}
		}
	}
	
	var xhrNickname=null;
	function nickNamecheck() {
		var nickname=document.getElementById("nickname").value;
		if(nickname!=""){
			xhrNickname=new XMLHttpRequest();
			xhrNickname.onreadystatechange=nickNamecheckOk;
			xhrNickname.open('get','${cp}/member/nicknameCheck?nickname='+nickname,true);
			xhrNickname.send();
		}else{
			nickCheck.src="${cp}/images/x.PNG";
		}
	}
	function nickNamecheckOk() {
		if(xhrNickname.readyState==4 && xhrNickname.status==200){
			var data=xhrNickname.responseText;
			var json=JSON.parse(data);
			if(json.nicknameCheck=="o"){
				nickCheck.src="${cp}/images/"+json.nicknameCheck+".PNG";
				btnDisabled();
			}else{
				nickCheck.src="${cp}/images/"+json.nicknameCheck+".PNG";
			}
		}
	}
	
	function nameCheck() {
		name=document.getElementById("name").value;
		if(name!=""){
			nameCheck2.src="${cp}/images/o.PNG";
			btnDisabled();
		}else{
			nameCheck2.src="${cp}/images/x.PNG";
		}
	}
	
	function pwdCheck() {
		pwd=document.getElementById("pwd").value;
		if(pwd!=""){
			pwdCheck2.src="${cp}/images/o.PNG";
			btnDisabled();
		}else{
			pwdCheck2.src="${cp}/images/x.PNG";
		}
	}
	
	function btnDisabled() {
		if(check.src==srcCheck && nickCheck.src==srcCheck && name!="" && pwd!=""){
			btn.disabled=false;
			btn.style.opacity=1;
		}else{
			btn.disabled='disabled';
		}
	}
	
	
</script>
</body>
</html>






