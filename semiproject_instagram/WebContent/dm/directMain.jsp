<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#dmtId {
		border: 4px solid black;
		width: 100%;
	}
	
	#maxSize {
		width: 1280px;
		margin: auto;
	}
	
	
	/* The Close Button */
	.close {
	  color: #aaaaaa;
	  float: right;
	  font-size: 28px;
	  font-weight: bold;
	}
	
	.close:hover,
	.close:focus {
	  color: #000;
	  text-decoration: none;
	  cursor: pointer;
	}
	#selectedMsg {
		display: block;
	}
	
	.modal2 {
		display: none; /* Hidden by default */
		position: fixed; /* Stay in place */
		z-index: 1; /* Sit on top */
		padding-top: 100px; /* Location of the box */
		left: 0;
		top: 0;
		width: 100%; /* Full width */
		height: 100%; /* Full height */
		overflow: auto; /* Enable scroll if needed */
		background-color: rgb(0,0,0); /* Fallback color */
		background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
	}
	
	/* Modal Content */
	.modal-content {
	  background-color: #fefefe;
	  margin: auto;
	  padding: 20px;
	  border: 1px solid #888;
	  width: 40%;
	}
	
	
	tr th {
		border: 1px solid black;
	}
	
	#userlisthidden {
		width: 70%;
	}
	
	#userlist {
		width: 30%;
	}
	
	.showTextf {
		width: 90%;
	}
	
	#msgTextBox {
		width: 95%;
		background-color: white;
		border: 2px solid white;
		height: 450px;
		margin: auto;
		overflow: auto; 
		border: 1px solid silver;
		margin-bottom: 15px;
	}
	
	#showMsg {
		display: none;
		padding: 15px;
		width: 85%;
	}
	
	#mText {
		width: 95%;
		margin: auto;
	}
	
	#mtextff {
		width: 95%;
		margin: auto;
	}
	 
	#msgboxdel {
		text-align: right;
	}
	
	.textb {
		display: inline-block;
		background-color: gray;
		width: 20em;
	}
	
	#searchUser {
		width: 15%;
	}
</style>
<!-- Bootstrap -->
    <link href="${ cp }/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <script src="${ cp }/dm/js/chat_module.js"></script>
</head>
<body>
<div id="maxSize">
	<table id="dmtId">
		<tr>
			<th>Direct<a href="javascript:showModal();">&nbsp;&nbsp;&nbsp;&nbsp;<img src="${ cp }/images/icon/dmmsg.png" alt="다이렉트 메시지 보내기" /></a></th>
			<th rowspan='5' id="userlisthidden">
				<div id="selectedMsg">
					<h2>내 메시지</h2>
					<h3>친구나 그룹에 비공개 사진과 메시지를 보내보세요.</h3>
					<input type="button" value="메시지 보내기" id="btn"/>
				</div>
				<div id="showMsg">
				<div id="msgboxdel"><a href="${ cp }/dm/delete?myMember_no=${member_no}&yourMember_no=${yourMember_no}">DM 삭제하기</a></div>
					<div id="msgTextBox">
						<div id="mText"><div id="mtextff"></div></div>
					</div>
					<input type="text" class="showTextf" placeholder="메시지 입력..." onkeyup="enterkey()"/>
				</div>
			</th>
		</tr>
		<tr>
		<tr>
			<div id="userlist">
			<td rowspan='5'>
				로그인된 아이디 번호 : ${ member_no }<br>
				검색되어 선택된 유저 : [ ${ nickname } ]
			
				<br><br>
				DM 보낼 유저목록
				<ul>
				<c:if test="${ dmlist != null }">
					<c:forEach var="n" items="${ dmlist }">
				<li>
						<div id="dmuserlist">
							<a href="${cp}/dm/connectClient?yourMember_no=${n.getMember_no()}&myMember_no=${member_no}">
								<img src="${ cp }/upload/${n.getProfile()}" alt="프로필사진" style="width:55px; height:55px; border-radius: 70%; margin-top:5px;" />
							</a>
							${n.getNickname()}
				</li>
						<br>
						</div>
					</c:forEach>
				</c:if>
				</ul>
			</td>
			</div>
		</tr>
		</tr>
	</table>
	
	<div id="myModal" class="modal2">
		 <!-- Modal content -->
		 <div class="modal-content">
		   <span class="close">&times;</span>
			<p>
				보낼 유저 : 
				<input type="text" id="searchUser" class="input-medium search-query" onkeyup="searchUser()" />
				<hr />
				<div id="result"></div>
			</p>
	</div>
</div>
	<script src="https://code.jquery.com/jquery.js"></script>
    <script src="${ cp }/bootstrap/js/bootstrap.min.js"></script>
    <!--  <script src="${ cp }/dm/js/chat_module.js"></script>-->
</body>

<script type="text/javascript">
	var btn = document.getElementById("btn");
	var modal = document.getElementById("myModal");
	var span = document.getElementsByClassName("close")[0];
	var myMember_no = '${member_no}';
	var yourMember_no = '${yourMember_no}';
	var checkAuto = false; //자동새로고침 인터발
	var msgTextBox = document.getElementById("msgTextBox"); //DM윈도우 DOM
	var showboxCheck = '${showboxCheck}'; //showbox보여주게하는 체트변수 기본값 off;
	//$(document).ready(chatConfirm(myMember_no)); //시작할때, 채팅상태 검사 함수 실행
	
	//유저검색버튼 액션 생성
	btn.onclick = function(){
		modal.style.display = "block";
	}
	
	function showModal(){
		modal.style.display = "block";
	}
	
	//close 버튼 닫기 기능
	span.onclick = function(){
		modal.style.display = "none";
	}
	
	//화면 아무데나 클릭해도 창닫히게함
	window.onclick = function(event){
		if(event.target == modal){
			modal.style.display = "none";
		}
	}
	
	//showbox 체크
	window.onload = function(){
		if(showboxCheck != null && showboxCheck == 'on'){
			showMsgBox();
		}
	}
	
	
	//  ajax 시작 (유저검색)
	var xhr =null;
	var result =document.getElementById("result");
	
	function searchUser(){
		xhr =new XMLHttpRequest();
		xhr.onreadystatechange =onSearchUser;
		
		var keyword =document.getElementById("searchUser").value;
		//var myMember_no =document.getElementById("myMember_no").value;
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
				result.innerHTML += "맴버번호 : " + json[i].yourMember_no + 
				" 아이디:" + json[i].id + " 이름: <a href='${cp}/dm/connectClient?yourMember_no=" + json[i].yourMember_no 
						+ "&myMember_no=" + json[i].myMember_no + "&nickname=" 
						+ json[i].nickname + "&showboxCheck=" + showboxCheck + "'>"
						+ json[i].name + "</a>"
						+ " 닉네임:" + json[i].nickname 
				+ " 프로필사진:" + json[i].profile +"<br>";
				
			}
		}
	}
	
	//채팅  DOM 감추기
	function showMsgBox(){
		var selectedMsg = document.getElementById("selectedMsg");
		var showMsg = document.getElementById("showMsg");
		
		selectedMsg.style.display = "none";
		
		//채팅 DOM 보이게 하기
		showMsg.style.display = "block";
		
		//DB내용 가져와 텍스트박스에 보여주기
		dshowBox();
		
		//자동새로고침 시작
		if(!checkAuto){
			startRefresh();
		}
	}
	
	//채팅 받기 기능 구현 ajax
	var showMsg_xhr = null;
	var onescroll = 0;

	function dshowBox(){
		showMsg_xhr = new XMLHttpRequest();
		
		showMsg_xhr.onreadystatechange = function(){
			var data = showMsg_xhr.responseText;
			var json = JSON.parse(data);
			var mText = document.getElementById("mText");
			var mtextff = document.getElementById("mtextff");
			
			if(showMsg_xhr.readyState==4 && showMsg_xhr.status==200){
				//초기화
				mtextff.innerHTML = "";
				
				var logintimef = '${logintimef}'; //로그인 시간
				//console.log(logintimef);
				for(var i=0; i<json.length; i++){
					if(myMember_no == json[i].smember_no){
						mtextff.innerHTML += "<div style='width:100%; text-align:right; margin-top:15px;'>" +
						"<div class='tbox_dm' style='padding: 10px; background-color: white;" +
						" border:1px solid gray; width:12em; display:inline; border-radius:50%; margin-top:10px;'>" +
						json[i].content + "</div><a href='${cp}/follow/move?youmember_no=" + json[i].smember_no +
								"'><img src='${cp}/upload/" + json[i].myProfile +
						"' style='width:40px; height:40px; border-radius:70%;'></a></div>";
					} else if(myMember_no == json[i].rmember_no){
						mtextff.innerHTML += "<div style='width:100%; text-align:left; margin-top:15px;'><a href='${cp}/follow/move?youmember_no=" + json[i].smember_no +
						"'><img src='${cp}/upload/" + json[i].yourProfile +
						"' style='width:40px; height:40px; border-radius:70%;'></a>" +
						"<div class='tbox_dm' style='padding: 10px; background-color: white;" +
						" border:1px solid gray; width:12em; display:inline; border-radius:50%; margin-top:10px;'>" +
						json[i].content + "</div></div>";
					}
				}

				
				if(onescroll == 0){
					msgTextBox.scrollTop = msgTextBox.scrollHeight;
					onescroll++;
				}
				//스크롤바 제일아래로
				var _scrollTop = msgTextBox.scrollY || msgTextBox.scrollTop;
				//console.log(_scrollTop + '>>_scrollTop');
				//console.log(msgTextBox.scrollHeight + '>>scrollHeight');
				if(_scrollTop+505 == msgTextBox.scrollHeight){
					msgTextBox.scrollTop = msgTextBox.scrollHeight;
				}
			}			
		}
		
		showMsg_xhr.open('post', '${cp}/dm/chatReceive', true);
		showMsg_xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		showMsg_xhr.send('myMember_no=${myMember_no}' + '&yourMember_no=${yourMember_no}');
	}
	
	
	
	//채팅 보내기 기능 구현 ajax
	var dmsg_xhr = null;
	var showTextf = null;
	function dchatBox(){
		dmsg_xhr = new XMLHttpRequest();
		showTextf = document.querySelectorAll(".showTextf");
		
		dmsg_xhr.onreadystatechange = function(){
			if(dmsg_xhr.readyState==4 && dmsg_xhr.status==200){
				dshowBox();
			}
		}
		dmsg_xhr.open('post', '${cp}/dm/dmmsg', true);
		dmsg_xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		dmsg_xhr.send('content=' + showTextf[0].value + '&myMember_no=${sessionScope.myMember_no}' 
				+ '&yourMember_no=${sessionScope.yourMember_no}');
	}
	
	
	//엔터칠때 채팅창 내용 실행
	function enterkey(){
		if(window.event.keyCode == 13){
			dchatBox();
			showTextf[0].value = "";
			
			//스크롤바 제일아래로
			//msgTextBox.scrollTop = msgTextBox.scrollHeight;
		}
	}
	
	
	//자동 새로고침으로 내용 업데이트
	var timeId = null;
	function startRefresh(){
		timeId = setInterval(dshowBox, 1000);
		checkAuto = true;
	}
	
	//새로고침 중지
	function stopRefresh(){
		if(timeId != null){
			clearInterval(timeId);
		}
	}
	
	
	
</script>
</html>