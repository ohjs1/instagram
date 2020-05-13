<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<input type="text" id="search" onkeyup="searchUser()" />
	<input type="hidden" id="member_no" value="${ member_no }" />
	<div id="result"></div>
</body>
<script type="text/javascript">
	var xhr =null;
	var result =document.getElementById("result");
	
	function searchUser(){
		xhr =new XMLHttpRequest();
		xhr.onreadystatechange =onSearchUser;
		
		var keyword =document.getElementById("search").value;
		var member_no =document.getElementById("member_no").value;
		if(!keyword==""){
			xhr.open('get', '${cp}/dm/usersearch?keyword=' + keyword + "&member_no=" + member_no);
			xhr.send();
		} else {
			result.innerHTML ="";
		}
	}
	
	function onSearchUser(){
		if(xhr.readyState==4 && xhr.status==200){
			var data =xhr.responseText;
			var json =JSON.parse(data);
			
			result.innerHTML ="";
			
			for(var i=0; i<json.length; i++){
			console.log(json[i].member_no);
				result.innerHTML += "맴버번호 : " + json[i].member_no + 
				" 아이디:" + json[i].id + " 이름: <a href='${cp}/dm/client?member_no=" + json[i].member_no + "'>" + json[i].name + "</a>" + " 닉네임:" + json[i].nickname 
				+ " 프로필:" + json[i].profile +"<br>";
			}
		}
	}
</script>
</html>