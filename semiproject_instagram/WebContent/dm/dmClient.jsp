<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Direct Msg - Instagram</title>
</head>
<body>
	<form>
		<textarea id="chatarea" cols="30" rows="10"></textarea><br>
		<input type="text" id="msgtext"/>
		<input type="button" value="전송" onclick="sendMsg()"/>
	</form>
</body>
<script type="text/javascript">
	var websocket =new WebSocket("ws://localhost:8081/semiproject_instagram/websocket");
	
	
	websocket.onopen =function(message){
		console.log("서버에 접속됨");
	}
	
	function sendMsg(){
		var chatarea =document.getElementById("chatarea");
		var msgtext =document.getElementById("msgtext");
		websocket.send(msgtext.value);
		chatarea.value +=msgtext.value + "\n";
		msgtext.value ="";
	}
</script>
</html>