<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Direct Msg - Instagram</title>
<style>
	#result {
		border: 2px solid black;
		width: 600px;
		height: 600px;
	}
	
	#userlist {
		display: inline-block;
		border: 1px solid black;
	}
</style>
</head>
<body>
	<div id="userlist">
		<h1>유저목록 리스트</h1>
		<div id="list"></div>
	</div>
	<div>
		<div id="result"></div>
		<input type="hidden" id="myMember_no" value="${ myMember_no }"/>
		<input type="hidden" id="yourMember_no" value="${ yourMember_no }" />
		<input type="hidden" id="chat_no" value="${ chat_no }" />
		<input type="text" id="content" />
		<input type="button" value="전송" onclick="dmMsg()"/>
	</div>
	
	<div id="del"></div>
</body>
<script type="text/javascript">

var xhr =null;
function dmMsg(){
	xhr =new XMLHttpRequest();
	xhr.onreadystatechange =OnDmMsg;
	
	var content =document.getElementById("content");
	
	xhr.open('post', '${cp}/dm/dmmsg', true);
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhr.send('myuser_no=${myMember_no}' + '&your_member_no=${yourMember_no}' + '&content=' + content.value);
}

function OnDmMsg(){
	if(xhr.readyState==4 && xhr.status==200){
		var data =xhr.responseText;
		var json =JSON.parse(data);
		var result =document.getElementById("result");
		var myMember_no =document.getElementById("myMember_no").value;
		var yourMember_no =document.getElementById("yourMember_no").value;
		var chat_no =document.getElementById("chat_no").value;
		var del =document.getElementById("del");
		var list =document.getElementById("list");
			
		var str ="";
		for(var i=0; i<json.length; i++){
			if(str != json[i].smember_no){
				list.innerHTML += json[i].smember_no + "<br>";
				str =json[i].smember_no;
			}
		}
		
		
		result.innerHTML ="";
		var chk =0;
		for(var i=0; i<json.length; i++){
			if(json[i].chat_no==chat_no){
				console.log(json[i].chat_no + ": json[i].chat_no");
				result.innerHTML +=json[i].content + "<br>";
				chk =1;
			}
			
			if(chk==1){
				del.innerHTML ="<input type='button' value='삭제하기' onclick='delchat()'>";
			}
		}
		content.value ="";
	}
}


var delxhr =null;
function delchat(){
	delxhr =new XMLHttpRequest();
	delxhr.open('get', '${cp}/dm/delete?chatroomNumber=' + chat_no.value + "&yourmember_no=" + yourMember_no.value, true);
	delxhr.send();
}
</script>
</html>