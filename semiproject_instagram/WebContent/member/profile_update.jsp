<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#img{
		width: 120px;
	}
</style>
</head>
<body>
<h1>프로필 편집</h1>
<form name="fileForm" enctype="multipart/form-data" method="post" action="${cp }/member/profileUpdate">
	<input type="file" name="profile" style="display: none"
  	onchange="fileupdate(this.files[0].name)" id="profile"><br>
	<label for="profile">
	<img id="img">
	</label><br>
	<input type="text" name="name" value="${name }" id="name"><br>
	<input type="text" name="nickname" value="${nickname }" id="nickname"><br>
	<input type="button" value="제출" onclick="update()"><br>
	<input type="hidden" value="${profile }" name="profile1" id="profile1">
</form>
	<input type="button" value="프로필 삭제" id="delProfile" onclick="delProfile()"><br>

<a href="${cp }/member/memberInfo">프로필 편집</a><br>
<a href="${cp }/member/pwdupdate">비밀번호 변경</a><br>
<a href="${cp }/member/logout">로그아웃</a><br>

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
		var profile=document.getElementById("profile1");
		var name=document.getElementById("name").value;
		var nickname=document.getElementById("nickname").value;
		xhrupdate=new XMLHttpRequest();
		xhrupdate.onreadystatechange;
		xhrupdate.open('post','/member/update',true);
		xhrupdate.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		xhrupdate.send('name=' + name + '&nickname=' + nickname);
	}
	var xhrdel=null;
	function delProfile() {
		var img=document.getElementById("img");
		img.src=img.src="${cp}/upload/profile.jpg";
		xhrdel=new XMLHttpRequest();
		xhrdel.onreadystatechange;
		xhrdel.open('get','/member/delProfile?profile=profile.jpg',true);
		xhrdel.send();
	}
</script>
</html>





