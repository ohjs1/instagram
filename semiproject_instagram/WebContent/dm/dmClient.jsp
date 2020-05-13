<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Direct Msg - Instagram</title>
</head>
<body>
	<form>
		<input type="hidden" id="myuser_no" value="${ member_no }" />
		<input type="hidden" id="your_member_no" value="${ myMember_no }" />
		<textarea id="chatarea" cols="30" rows="10">
			<c:forEach var="list" items="${ list }">
				${ list.getContent() }
			</c:forEach>
		</textarea><br>
		<input type="text" id="msgtext"/>
		<input type="button" value="전송" onclick="sendMsg()"/>
	</form>
</body>
<script type="text/javascript">
	//채팅 서버에 접속
	var websocket =new WebSocket("ws://localhost:8081/semiproject_instagram/websocket");
	
	websocket.onopen =function(){
		console.log("서버에 접속됨");
	}
	
	function sendMsg(){
		var chatarea =document.getElementById("chatarea");
		var msgtext =document.getElementById("msgtext");
		
		var myuser_no =document.getElementById("myuser_no").value; //로그인 회원 번호 들어옴
		var youruser_no =document.getElementById("your_member_no").value; //DM보낼 회원번호 들어옴
		
		websocket.send(msgtext.value + "#" + myuser_no + "#" + youruser_no);
		chatarea.value +=msgtext.value + "\n";
		msgtext.value ="";
	}
</script>
</html>