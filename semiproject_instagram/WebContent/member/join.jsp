<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%-- <link rel="stylesheet" type="text/css" href="${cp }/css/member_profile.css" /> --%>
<style type="text/css">
	.input{width: 258px;height: 36px;margin: 4px;}
	#btn{
		width: 260px;
		margin-top: 10px;
		background-color: #0095f6;
		color: white;
		border-radius: 10%;
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
</style>
</head>
<body>
<div id="join">
	<div id="join_wrar">
		<div>
			<img src="${cp }/images/logo.png" id="join_img">
			<h2>친구들의 사진과 동영상을 보려면<br>가입하세요.</h2>
		</div>
		<form name="joinForm" method="post" action="${cp }/member/insert">
			<input type="text" id="id" name="id" placeholder="아이디" onblur="idcheck()" onfocus="test()" class="input">
			<span></span><br>
			<input type="text" name="name" placeholder="성명" class="input"><br>
			<input type="text" name="nickname" placeholder="닉네임" class="input"><br>
			<input type="password" name="pwd" placeholder="비밀번호" class="input"><br>
			<input type="submit" value="가입" disabled="disabled" id="btn" class="input"><br>
			<p id="join_p">가입하면 Instagram의 약관, 데이터 정책 및 쿠키<br> 정책에 동의하게 됩니다.</p>
		</form>
	</div>
	<div id="join_login">
		계정이 있으신가요? <a href="${cp }/member/login.jsp">로그인</a>
	</div>
</div>
<script type="text/javascript">
	var xhr=null;
	var span=document.getElementsByTagName("span")[0];
	var btn=document.getElementById("btn");
	function idcheck() {
		var id=document.getElementById("id").value;
		xhr=new XMLHttpRequest();
		xhr.onreadystatechange=idcheckOk;
		xhr.open('get','${cp}/member/idcheck?id='+id,true);
		xhr.send();
	}
	function idcheckOk() {
		if(xhr.readyState==4 && xhr.status==200){
			var data=xhr.responseText;
			var json=JSON.parse(data);
			span.innerHTML=json.idcheck;
			if(json.idcheck=="사용가능"){
				btn.disabled=false;
			}
		}
	}
	function test() {
		span.innerHTML="";
	}
	
</script>
</body>
</html>






