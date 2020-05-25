<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My_Stories</title>
<style>
* {
	padding: 0px;
	margin: 0px
}

body {
	background-color: #292929;
}
#main{
	width:504px;
	height:1000px;
	margin-top: 100px;
	margin:0 auto;
	overflow: hidden;
}

#prebtn{
	width: 50px;
	height: 600px;
	float:left;
	margin-top: 100px;
	padding-top: 400px;
	position: relative;
}

#wrap {
	width: 400px;
	height: 850px;
	float:left;
	margin-top: 100px;
	position: relative;
}

#nextbtn{
	width: 50px;
	height: 600px;
	float:left;
	margin-top: 100px;
	padding-top: 400px;
}

#content {
	width: 350px;
	height: 400px;
	position: absolute;
	top: 300px;
	left: 10px;
	display: none;
	color: white;
	font-family: 맑은고딕;
	font-size: 20px;
	text-shadow: -1px 0 black, 0 1px black, 1px 0 black, 0 -1px black;
}

.date{
	width: 200px;
	height: 100px;
	position: absolute;
	top: -45px;
	left: 150px;
	color: white;
	font-family: 맑은고딕;
	font-size: 15px;
}

#exit {
	width: 32px;
	height: 32px;
	top: 10px;
	right: 0px;
	position: absolute;
}

#del{
	width: 30px;
	height: 30px;
	top: 5px;
	right: 5px;
	position: absolute;
}

.modal_overlay {
	background-color: rgba(0,0,0,0.6);
	width: 100%;
	height: 100%;
	position: absolute;
	z-index:10;
	}
/*스크롤바 기능은 하면서 보이지는 않게 하기*/
::-webkit-scrollbar {
	display:none;
}	
	
.modal_content{
	display: grid;
	border-radius:10px;
	text-align:center;
	grid-template-columns: 300px;
	grid-template-rows: 50px 250px;
	background: white;
	z-index:10000;
	font-family: 맑은고딕;
}
	
.modal2 {
	position: fixed;
	bottom:0px;
	left:0px;
	width: 100%;
	height: 100%;
	display: flex;
	justify-content: center;
	align-items: center;
}

.read_title{
	font-family: 맑은고딕;
	padding: 10px;
	border-bottom: 1px solid rgba(var(--ce3,239,239,239),1);
	font-weight: 600;
}

.readmodal_close{
	width: 18px;
	height: 24px;
	float:right;
}

.read_list{
	display: flex;    
	align-items: center;
	padding-bottom: 8px;
    padding-top: 8px;
	padding-left: 16px;
    padding-right: 16px;

}
.read_list img{
	align-self: center;
    max-width: 40px;
    border-radius: 50%;
    margin-right: 12px;
}
 .handle {
    text-decoration: lowercase;
    font-weight: 600;
    font-size: 14px;
  }  
  
 .read_list p{
 	margin:0;

 }
 
  .read_list p a{
 	margin:0;
	text-decoration: none;
	color:black;
 }
 
 .name{
	color: rgba(var(--f52,142,142,142),1);
	font-weight: 400;
	font-size:12px;
	
}
</style>

</head>
<body onload="showStory()">
<div id="main">
<div id="prebtn"><img src="${cp }/upload/pre.png" style="width:30px;height: 30px" onclick="previousImg()"></div>

	<div id="wrap">
	<a href="${cp }/feed/myfeed?mymember_no=${member_no}" style="color:white;text-decoration: none;display: inline;">
		<img id="myprofile" style="width: 32px; height: 32px; border-radius: 50%;"> </a>
		<div id="topm" style="text-align: center;line-height:32px; width: 70px;height: 32px;display: inline;">
			<a href="${cp }/feed/myfeed?mymember_no=${member_no}" style="color:white;text-decoration: none">
			${nickname} 
			</a>

		</div>

		<div id="exit">
			<input type="image" style="width: 32px; height: 32px"
				src="../upload/whitex.png" onclick="closeStory()">
		</div>
		<br><br>

		<c:forEach var="vo" items="${list }">

			<c:set var="file2" value="${vo.getFilepath()}" />


			<div id="file" style="width: 400px; height: 700px; position: absolute; display: none" class="divs">
			<a href="#" onclick="delchk(${vo.getStory_no()})">	
			<img src="../upload/del.png" id="del"></a>
			<div class="date"></div>
				<c:choose>
					<c:when
						test="${fn:endsWith(file2,'.png') or fn:endsWith(file2,'.jpg') }">
						
						<img src="${cp }/upload/${file2 }"
							style="width: 400px; height: 700px; display: none" id="output" class="imgs">

					</c:when>
					<c:otherwise>
						<video src="${cp }/upload/${file2 }"
							style="width: 400px; height: 700px; display: none" class="imgs"
							autoplay="autoplay" muted="muted" loop="loop"></video>
					</c:otherwise>
				</c:choose>

				<div id="content" class="cdivs">
				<c:set var="cont" value="${vo.getContent() }"/>
					<c:choose>
						<c:when test="${fn:contains(cont,'#') }">
						<c:set var="tag" value="${fn:substringAfter(cont, '#') }"/>
							<c:set var="start_tag" value="${fn:indexOf(cont, '#') }"/>
							<c:set var="end_tag" value="${fn:indexOf(tag, ' ') }"/> 
							<c:set var="realtag" value="${fn:substring(tag,0,end_tag) }"/>
							<c:set var="cont1" value="${fn:substringBefore(cont, '#') }"/>
							<c:set var="cont2" value="${fn:substringAfter(cont, realtag) }"/>

							${cont1 } <a href="${cp }/tag/list?keyword=%23${realtag}" style="text-decoration: none; color:white;background-color: #FFFACD;"> #${realtag } </a> ${cont2 }
							
						</c:when>
						<c:otherwise>
							${cont }
						</c:otherwise>
						
					</c:choose>
					<input type="hidden" class="readstory" name="readstory" value="${vo.getStory_no()}">
					<input type="hidden" class="content_tag" name="content_tag" value="${vo.getContent()}">
					<div class="readuser"></div>
	
				</div>
				
				
			</div>

		</c:forEach>

	</div>
	<div id="nextbtn"><img src="${cp }/upload/next.png" style="width:30px;height:30px;padding-left: 18px;" onclick="nextImg()"></div>
</div>
<div id="modal1"></div>
</body>
<script type="text/javascript">

	function delete24story(readstory){
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				console.log("24시간 지난 스토리 삭제됨");
			}
		}
		xhr.open('get', '${cp}/story/delete?story_no='+readstory, true);
		xhr.send();
	}
	
	//스토리 올린 후 지난 시간 표시
	function getStorydate(readstory,i){
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				var day=document.getElementsByClassName("date")[i];
				var data = xhr.responseText;
				var json = JSON.parse(data);
				day.innerHTML="";
				var storydate=new Date(json.storydate);
				var date=new Date();
				var dd=Math.floor((date-storydate)/1000);
				console.log("스토리올린시간:"+storydate);
				console.log(date);
				console.log(dd);
				var realDate="";
				if(dd<60){
					realDate="<p>"+dd+"초 전</p>";
					console.log(realDate);
				}else if(dd<3600){
					realDate="<p>"+Math.floor((dd/60))+"분 전</p>";
					console.log(realDate);
				}else if(dd<86400){
					realDate="<p>"+Math.floor((dd/60/60))+"시간 전</p>";
					console.log(realDate);
				}else{
					console.log(realDate+"삭제됨");
					delete24story(readstory);
				}
				var div = document.createElement("div");
				div.innerHTML = realDate;
				div.style.position="absolute";
				div.style.width="80px";
				div.style.height="40px";
				div.className = "day";
				day.appendChild(div);
			
				
			}
		}
		xhr.open('get', '${cp}/story/storydate?story_no='+readstory, true);
		xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		xhr.send();	
		
	}
	//기본
	var index = 0;
	var timeout = null;
	var timeout2 = null;
	function showStory() {
		getProfile();		
		const divs = document.getElementsByClassName("divs");
		const cdivs = document.getElementsByClassName("cdivs");
		const imgs = document.getElementsByClassName("imgs");
		var readstory = document.getElementsByClassName("readstory");
		for (let i = 0; i < imgs.length; i++) {
			if (i == index) {
				getReaduser(readstory[i].value,i);
				getStorydate(readstory[i].value,i);
				getContent();
				divs[i].style.display = "block";
				imgs[i].style.display = "block";
				cdivs[i].style.display = "block";
			} else {
				divs[i].style.display = "none";
				imgs[i].style.display = "none";
				cdivs[i].style.display = "none";
			}
		}
		index++;
		timeout = setTimeout(showStory, 3000);
		if (index > imgs.length - 1) {
			timeout2 = setTimeout(closeStory, 3000);
		}
	}
	
	// 내용/태그 가져오기
	function getContent(){
		
	}

	
	//내 프로필 가져오기
	function getProfile() {
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				var myprofile = document.getElementById("myprofile");
				var data = xhr.responseText;
				var json = JSON.parse(data);
				myprofile.src = "${cp}/upload/" + json.profile;
			}
		}
		xhr.open('post', '${cp}/member/memberInfo', true);
		xhr.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
		xhr.send();
	}
	
	//모달 실행
	function showModal(readstory,i){
		clearTimeout(timeout);
		
				var modal1=document.getElementById("modal1");
				var div=document.createElement("div");
				div.innerHTML="<div class='modal_overlay' onclick='closeModal();'></div>"+//모달창의 배경색
									"<div class='modal_content'><div class='read_title'>조회한 사람"+
										"<span class='readmodal_close' onclick='closeModal();'>"+
											"<button>X</button>"+
										"</span><br>"+
									"</div>"+
									"<div class='modal_list'></div></div>"+
								"</div>";								
				div.className="modal2";
				div.setAttribute("id","modal2");
				modal1.appendChild(div);
				getReaduserList(readstory);
		
	}


	//조회한 사람 리스트
	function getReaduserList(readstory){
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				var modal_list=document.getElementsByClassName("modal_list")[0];
				modal_list.innerHTML="";
				var data=xhr.responseText;
				var json=JSON.parse(data);
				for(let i=0; i<json.length; i++){
					var nickname=json[i].nickname;
					var profile=json[i].profile;
					var member_no=json[i].member_no;
					var name=json[i].name;
					modal_list.innerHTML+="<div class='read_list'>"+
					"<div>"+
					"<a href='${cp}/follow/move?youmember_no="+member_no+"'>"+
						"<img src='../upload/"+profile+"' style='width:32px;height:32px'>"+//프로필사진
					"</a>"+
				"</div>"+
				"<div style='text-align:left;'>"+
					"<p>"+
						"<a href='${cp}/follow/move?youmember_no="+member_no+"'>"+
							"<span class='handle'>"+nickname+"</span> "+//닉네임
						"</a>"+
					"</p>"+
					"<p>"+
						"<a href='${cp}/follow/move?youmember_no="+member_no+"'>"+
							"<span class='name'>"+name+"</span> "+//이름
						"</a>"+
					"</p>"+
				"</div>"+
			"</div>";
				}
			}
		}
		xhr.open('post','${cp}/readuser/list',true);
		xhr.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
		xhr.send('story_no='+readstory);
		
	}
	
	//읽은 사람 수 링크
	function getReaduser(readstory,i){
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				var read=document.getElementsByClassName("readuser")[i];
				var data = xhr.responseText;
				var json = JSON.parse(data);
				read.innerHTML="";
				if(!(json.length==0)){
					var div = document.createElement("div");
					div.innerHTML = "<a href='#' onclick='showModal("+readstory+","+i+")' style='font-size:12px;text-decoration:none;color:white'>"
					+json.length +"명이 읽음</a>";
					div.style.display = "inline-block";
					div.style.position="absolute";
					div.style.width="200px";
					div.style.height="40px";
					div.style.bottom="0";
					div.className = "read";
					read.appendChild(div);
				}
				
			}
		}
		xhr.open('get', '${cp}/readuser/list?story_no='+readstory, true);
		xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		xhr.send();		
	}
	

	//모달 닫기버튼
	function closeModal(){
		var modal1=document.getElementById("modal1");
		modal1.innerHTML="";
		counter=0;
	}
	
	
	//스토리 종료 : index가 끝에 도달했을 때 실행되는데.. mousedown/up 안됨 
	function closeStory() {
		location.href = "${cp}/board/homefeed";
	}

	function previousImg() {
		clearTimeout(timeout);
		index=index-2;
		if (index < 0) {
			index = 0;
		}
		showStory();
	}

	function nextImg() {
		clearTimeout(timeout);	
		if (index > imgs.length - 1) {
			closeStory();
		}
		showStory();
	}
	
	//마우스 클릭 중: 멈춤 / 클릭 떼면 다시 재생
	const imgs = document.getElementsByClassName("imgs");
	for (var i = 0; i < imgs.length; i++) {
		console.log("imgs:" + imgs);
		imgs[i].addEventListener('mousedown', function() {
			console.log("imgs/////down");
			clearTimeout(timeout);
			clearTimeout(timeout2);
		});

		imgs[i].addEventListener('mouseup', function() {
			console.log("imgs/////up");
			timeout = setTimeout(showStory, 3000);
			
		});
	}

	// 스토리 삭제 *cascade readuser delete 
	function delchk(story_no){
		if(confirm("이 스토리를 정말 삭제하시겠습니까?")){
			location.href="${cp }/story/delete?story_no="+story_no;
			alert("삭제되었습니다!")
			
		}else{
			alert("삭제 실패!");
		}
	}
	/*function deleteStory() {
		var xhr = new XMLHttpRequest();
		var story_no = document.getElementById("story_no").value;
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				var data = xhr.responseText;
				var json = JSON.parse(data);
				if (json.bl) {
					showStory();
				} else {
					alert(index + '//삭제실패!');
				}
			}
		}
		xhr.open('post', '${cp}/story/delete', true);
		xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		xhr.send('story_no=' + story_no);

	}*/
</script>
</html>