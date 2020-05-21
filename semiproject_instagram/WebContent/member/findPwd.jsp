<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.input{width: 258px;height: 36px;margin: 4px;}
	#btn{
		width: 260px;
		margin-top: 10px;
		/* background-color: #0095f6; */
		/* color: white; */
		/* border-radius: 10%; */
	}
	#findPwd_img{
		width: 96px;
		height: 96px;
		margin-top: 20px;
	}
	#findPwd_wrap{
		width: 100%;
		height: 500px;
		border: 1px #dbdbdb solid;
		text-align: center;
	}
	#findPwd{
		width: 380px;
		margin: auto;
		margin-top: 100px;
	}
	#findPwd_login{
		width: 100%;
		border: 1px #dbdbdb solid;
   	 	height: 35px;
	    text-align: center;
	    padding-top: 8px;
	}
	#findPwd_p{
		color: #8e8e8e;
		font-size: 0.8em;
	}
	h2{
		color: #262626;
		font-size: 1.2em;
	}
	.or{
		width: 119px;
		border-bottom: 2px solid #777676;
		display: inline-block;
	}
	#or{
		margin-bottom: 5px;
	}
	#or2{
		padding-top: 3px;
	}
	#or3{
		margin-bottom: 5px;
	}
</style>
<link href="${cp }/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>
<div id="findPwd">
	<div id="findPwd_wrap">
		<div>
			<img src="${cp }/images/d.PNG" id="findPwd_img">
			<h4>로그인에 문제가 있나요?</h4>
			<p id="findPwd_p">사용자 이름 또는 이메일을 입력하면 다시 계정<br>에 로그인할 수 있는 링크를 보내드립니다.</p>
		</div>
		<form>
			<input type="text" name="id" id="id" placeholder="아이디" class="input"><br>
			<input type="text" name="name" id="name" placeholder="이름" class="input"><br>
			<input type="button" value="비밀번호 찾기" id="btn" onclick="find()" class="btn btn-primary"><br>
		</form>
		<div id="or">
			<div class="or" id="or1"></div>
			<div id="or2">또는</div>
			<div class="or" id="or3"></div>
		</div>
		<div>
			<a href="${cp }/member/join.jsp">새 계정 만들기</a>
		</div>
	</div>
	<div id="findPwd_login">
		<a href="${cp }/member/login.jsp">로그인으로 돌아가기</a>
	</div>
</div>
<script type="text/javascript">
	var msg=`${msg}`;
	var xhrfind=null;
	function find(){
		var id=document.getElementById("id").value;
		var name=document.getElementById("name").value;
		xhrfind=new XMLHttpRequest;
		xhrfind.onreadystatechange=insertOk;
		xhrfind.open('post','/member/findpwd',true);
		xhrfind.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		xhrfind.send('id='+ id + '&name=' + name);
	}
	function insertOk() {
		if(xhrfind.readyState==4 && xhrfind.status==200){
			var data=xhrfind.responseText;
			var json=JSON.parse(data);
			alert(json.msg);
		}
	}
</script>
<script src="https://code.jquery.com/jquery.js"></script>
<script src="${cp }/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>