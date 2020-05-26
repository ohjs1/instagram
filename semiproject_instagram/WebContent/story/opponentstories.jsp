<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Opponents_Stories</title>
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

#exit {
	width: 40px;
	height: 40px;
	top: 10px;
	right: 0px;
	position: absolute;
}

.date{
	width: 200px;
	height: 100px;
	position: absolute;
	top: -68px;
	left: 150px;
	color: white;
	font-family: 맑은고딕;
	font-size: 15px;
}

#stop{
	width: 77px;
	height: 25px;
	position: absolute;
	top: -68px;
	right: 50px;
	color: white;
	font-family: 맑은고딕;
	font-size: 15px;
	border: 1px solid white;
	border-radius:10%;
	display: none;
}
.dots{
	width:17px;
	height: 17px;
	display:inline-block;
}

#wrap2{
	width: 400px;
	height:20px;
	text-align:center;
	margin:0 auto;
}
</style>

</head>
<body onload="showStory()">
<div id="main">
<div id="prebtn"><img src="${cp }/upload/pre.png" style="width:30px;height: 30px" onclick="previousImg()"></div>
	
	<div id="wrap">
		<div id="oppprofile"></div>
		<div id="exit">
			<input type="image" style="width: 32px; height: 32px"
				src="../upload/whitex.png" onclick="closeStory()">
		</div>
<br>
		<div id="wrap2">
		<c:forEach var="vo" items="${list }">
			<div class="dots"><img src="../upload/dot.png" style="width:15px;height: 15px;">
			</div>
		</c:forEach>
		</div>
		<c:forEach var="vo" items="${list }">


			<c:set var="file2" value="${vo.getFilepath()}" />


			<div id="file"
				style="width: 400px; height: 700px; position: absolute; display: none"
				class="divs">
				<div class="date"></div>
				<div id="stop">일시정지됨</div>
				<c:choose>
					<c:when
						test="${fn:endsWith(file2,'.png') or fn:endsWith(file2,'.jpg') }">
						<img src="${cp }/upload/${file2 }"
							style="width: 400px; height: 700px; display: none" id="output"
							class="imgs">

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
					<input type="hidden" class="readuser" name="readuser" value="${member_no }"> 
					<input type="hidden" class="readstory" name="readstory" value="${vo.getStory_no()}">
					<input type="hidden" id="oppnickname" name="oppnickname" value="${vo.getNickname()}">

				</div>

			</div>

		</c:forEach>


	</div>
	<div id="nextbtn"><img src="${cp }/upload/next.png" style="width:30px;height:30px;padding-left: 18px;" onclick="nextImg()"></div>
</div>

</body>
<script type="text/javascript">
	function getProfile() {
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				var opp = document.getElementById("oppprofile");
				var oppnickname = document.getElementById("oppnickname").value;

				var data = xhr.responseText;
				var json = JSON.parse(data);
				for (var i = 0; i < json.length; i++) {
					var nick = json[i].nickname;

					if (oppnickname == nick) {
						if (opp.firstChild != null) {
							opp.removeChild(opp.firstChild);
						}
						var div = document.createElement("div");
						div.innerHTML = "<a href='${cp }/follow/move?youmember_no=" + json[i].member_no+ "' style='color:white;text-decoration: none'>"+
						"<img src='${cp}/upload/"
								+ json[i].profile
								+ "' style='width: 32px; height: 32px;border-radius: 50%;'></a>"+
								"<div id='topm' style='text-align: center;padding-left:5px;line-height:32px; width: 70px;height: 32px;display: inline;'><a href='${cp }/follow/move?youmember_no=" + json[i].member_no+ "' style='color:white;text-decoration: none'>"
								+ nick  +"</a></div>";
						div.style.display = "inline";
						div.style.height="54px";
						div.style.paddingBottom="15px";
						div.className = "opp"

						opp.appendChild(div);

					}

				}
			}
		}
		xhr.open('post', '${cp}/story/opponentlist', true);
		xhr.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
		xhr.send();
	}

	var index = 0;
	var timeout = null;
	var timeout2 = null;
	function showStory() {
		getProfile();

		const divs = document.getElementsByClassName("divs");
		const cdivs = document.getElementsByClassName("cdivs");
		const imgs = document.getElementsByClassName("imgs");
		const dots = document.getElementsByClassName("dots");
		var readuser = document.getElementsByClassName("readuser");
		var readstory = document.getElementsByClassName("readstory");
		for (let i = 0; i < imgs.length; i++) {
			if (i == index) {
				insertReadUser(readuser[i].value, readstory[i].value);
				getStorydate(readstory[i].value,i);
				dots[i].firstChild.src="../upload/dotr.png";
				divs[i].style.display = "block";
				imgs[i].style.display = "block";
				cdivs[i].style.display = "block";
			} else {
				dots[i].firstChild.src="../upload/dot.png";
				divs[i].style.display = "none";
				imgs[i].style.display = "none";
				cdivs[i].style.display = "none";
			}
		}
		index++;
		timeout = setTimeout(showStory, 3000);
		if (index > imgs.length - 1) {
			timeout2=setTimeout(closeStory, 3000);
		}
	}
	
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

	function closeStory() {
		location.href = "${cp}/board/homefeed";
	}

	function previousImg() {
		clearTimeout(timeout);
		index = index - 2;
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

	const imgs = document.getElementsByClassName("imgs");
	const stop = document.getElementById("stop");
	for (var i = 0; i < imgs.length; i++) {
		imgs[i].addEventListener('mousedown', function() {
			console.log("imgs/////down");
			
			clearTimeout(timeout);
			clearTimeout(timeout2);
			stop.style.display="inline-block";
			
		});

		imgs[i].addEventListener('mouseup', function() {
			console.log("imgs/////up");
			timeout = setTimeout(showStory, 3000);
			stop.style.display="none";
		});
	}

	function insertReadUser(readuser, readstory) {
		let xhr1 = new XMLHttpRequest();
		console.log(readuser + "///" + readstory);
		xhr1.onreadystatechange = function() {
			if (xhr1.readyState == 4 && xhr1.status == 200) {
				var data = xhr1.responseText;
				var json = JSON.parse(data);
				if (json.bl) {
					console.log("insert >> readuser table << !");
				}
			}
		}
		xhr1.open('get', '${cp}/readuser/insert?member_no=' + readuser + "&story_no=" + readstory, true);
		xhr1.send();
	}
</script>
</html>