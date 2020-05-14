<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>회원가입</h1>
<form name="joinForm" method="post" action="${cp }/member/insert">
	<input type="text" id="id" name="id" placeholder="아이디" onblur="idcheck()" onfocus="test()">
	<span></span><br>
	<input type="text" name="name" placeholder="성명"><br>
	<input type="text" name="nickname" placeholder="닉네임"><br>
	<input type="password" name="pwd" placeholder="비밀번호"><br>
	<input type="submit" value="가입" disabled="disabled"><br>
</form>
<a href="${cp }/member/findPwd.jsp">비밀번호를 잊으셨나요?</a><br>
계정이 있으신가요? <a href="${cp }/member/login.jsp">로그인</a>
<script type="text/javascript">
	var xhr=null;
	var span=document.getElementsByTagName("span")[0];
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
			alert(json.idcheck);
			span.innerHTML=json.idcheck;
		}
	}
	function test() {
		span.innerHTML="";
	}
	
</script>
</body>
</html>






