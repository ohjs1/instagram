<%@page import="com.vo.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	MemberVo vo = (MemberVo)session.getAttribute("memberVo");
%>
<h2>로그인 유저 Number : ${ member_no }</h2>
<c:set target="vo" var="vo"/>
<h3>유저 이름 : <%=vo.getName() %></h3>
<br />
<body>
	<input type="text" id="search" onkeyup="searchUser()" />
	<input type="hidden" id="myMember_no" value="${ member_no }" />
	<div id="result"></div>
</body>
<script type="text/javascript">
	var xhr =null;
	var result =document.getElementById("result");
	
	function searchUser(){
		xhr =new XMLHttpRequest();
		xhr.onreadystatechange =onSearchUser;
		
		var keyword =document.getElementById("search").value;
		var myMember_no =document.getElementById("myMember_no").value;
		if(!keyword==""){
			xhr.open('get', '${cp}/dm/usersearch?keyword=' + keyword + "&myMember_no=" + myMember_no, true);
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
				result.innerHTML += "맴버번호 : " + json[i].yourMember_no + 
				" 아이디:" + json[i].id + " 이름: <a href='${cp}/dm/connectClient?yourMember_no=" + json[i].yourMember_no 
						+ "&myMember_no=" + json[0].myMember_no + "'>" + json[i].name + "</a>" + " 닉네임:" + json[i].nickname 
				+ " 프로필사진:" + json[i].profile +"<br>";
			}
		}
	}
</script>
</html>