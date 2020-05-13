<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>프로필 편집</h1>
<form id="uploadForm" encType="multipart/form-data">
	<input type="file" name="profile" style="display: none"
  	onchange="fileupdate(this.files[0].name)" id="profile"><br>
</form>
<form>
	<label for="profile" id="div"></label><br>
	<input type="text" name="name" value="${vo.name }" id="name"><br>
	<input type="text" name="nickname" value="${vo.nickname }" id="nickname"><br>
	<input type="button" value="제출" onclick="update()"><br>
	<input type="hidden" value="${vo.profile }" id="profile1">
</form>

<a href="${cp }/member/memberInfo">프로필 편집</a><br>
<a href="${cp }/member/pwdupdate">비밀번호 변경</a><br>
<a href="${cp }/member/logout">로그아웃</a><br>

</body>
<script type="text/javascript">
	var form = document.getElementById("uploadForm");
	var div=document.getElementById("div");
	var profile=document.getElementById("profile1");
	if(profile.value=="" || profile.value==null){
		div.innerHTML="<img src=${cp}/images/profile.jpg width='120px'>";
	}else{
		div.innerHTML="<img src=${cp}/images/"+profile.value+" width='120px'>";
	}
	var xhrfileupdate=null;
	function fileupdate(filename) {
		xhrfileupdate=new XMLHttpRequest();
		xhrfileupdate.onreadystatechange=fileupdateOk;
		xhrfileupdate.open('post','/member/profileUpdate',true);
		xhrfileupdate.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		xhrfileupdate.send('profile=' + filename);
	}
	function fileupdateOk() {
		if(xhrfileupdate.readyState==4 && xhrfileupdate.status==200){
			var data=xhrfileupdate.responseText;
			var json=JSON.parse(data);
			div.innerHTML="<img src=${cp}/images/"+json.profile+" width='120px'><br>";
		}
	}
	var xhrupdate=null;
	function update() {
		var name=document.getElementById("name").value;
		var nickname=document.getElementById("nickname").value;
		xhrupdate=new XMLHttpRequest();
		xhrupdate.onreadystatechange;
		xhrupdate.open('post','/member/update',true);
		xhrupdate.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		xhrupdate.send('name=' + name + '&nickname=' + nickname);
	}
</script>
</html>





