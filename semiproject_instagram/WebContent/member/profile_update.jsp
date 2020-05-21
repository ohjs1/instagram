<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${cp }/css/member_profile.css" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
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
			<form name="fileForm" enctype="multipart/form-data" method="post" action="${cp }/member/profileUpdate">
				<div>
					<input type="file" name="profile" style="display: none"
				  	onchange="fileupdate(this.files[0].name)" id="profile"><br>
			  		<div id="div_imgs">
						<label for="profile"><img id="img"></label>
					</div>
					<div id="div_nic">
						<h1>${sessionScope.nickname }</h1>
					</div>
				</div>
				<div>
					<label for="name"><strong>이름</strong></label>
					<input type="text" name="name" value="${name }" id="name" class="input"><br>
					<label for="nickname"><strong>닉네임</strong></label>
					<input type="text" name="nickname" value="${nickname }" id="nickname" class="input"><br>
					<label></label>
					<input type="button" value="제출" onclick="update()" id="btn"><br>
					<input type="hidden" value="${sessionScope.profile }" name="profile1" id="profile1">
				</div>
			</form>
				<input type="button" value="-" id="delProfile" onclick="delProfile()">
		</div>
	</div><!-- wrap2 -->
</div>
<script src="https://code.jquery.com/jquery.js"></script>
<script src="${cp }/bootstrap/js/bootstrap.min.js"></script>
</body>
<script type="text/javascript">
	var img=document.getElementById("img");
	var profile=document.getElementById("profile1");
	if(profile.value!="" || profile.value!=null){
		img.src="${cp}/upload/"+profile.value;
	}
	function fileupdate(filename) {
		document.fileForm.submit();
	}
	var xhrupdate=null;
	function update() {
		var name=document.getElementById("name").value;
		var nickname=document.getElementById("nickname").value;
		xhrupdate=new XMLHttpRequest();
		xhrupdate.onreadystatechange;
		xhrupdate.open('post','${cp}/member/update',true);
		xhrupdate.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		xhrupdate.send('name=' + name + '&nickname=' + nickname);
	}
	var xhrdel=null;
	function delProfile() {
		var img=document.getElementById("img");
		img.src="${cp}/upload/profile.jpg";
		xhrdel=new XMLHttpRequest();
		xhrdel.onreadystatechange;
		xhrdel.open('get','${cp}/member/delProfile?profile=profile.jpg',true);
		xhrdel.send();
	}
</script>
</html>





