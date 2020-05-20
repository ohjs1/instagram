<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>OpponentStories</title>
<style>
* {
	padding: 0px;
	margin: 0px
}

body {
	background-color: #292929;
}

#wrap {
	width: 400px;
	height: 850px;
	margin: auto;
	margin-top: 100px;
	position: relative;
}

#content {
	width: 280px;
	height: 200px;
	position: absolute;
	top: 350px;
	left: 10px;
	display: none;
	color: white;
	font-family: HY견고딕;
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
</style>

</head>
<body onload="showStory()">
	<div id="wrap">
		<div id="oppprofile"></div>
		<input type="button" value="이전" onclick="previousImg()"> 
		<input type="button" value="다음" onclick="nextImg()">
		 
		<div id="exit">
			<input type="image" style="width: 50px; height: 50px"
				src="../upload/whitex.png" onclick="closeStory()">
		</div>

		<c:forEach var="vo" items="${list }">


			<c:set var="file2" value="${vo.getFilepath()}" />


			<div id="file"
				style="width: 400px; height: 700px; position: absolute; display: none"
				class="divs">

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
					${vo.getContent() } <br> 저장될 읽은사람(현재로그인한 나) ${nickname } <br>
					${vo.getNickname()} 의 현재 스토리 번호 : ${vo.getStory_no() }
					<!--  <c:set var="snolist">${vo.getStory_no() }</c:set>  -->
					<input type="hidden" class="readuser" name="readuser" value="${member_no }"> 
					<input type="hidden" class="readstory" name="readstory" value="${vo.getStory_no()}">
					<input type="hidden" id="oppnickname" name="oppnickname" value="${vo.getNickname()}">

				</div>

			</div>

		</c:forEach>


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
						div.innerHTML = "<img src='${cp}/upload/"
								+ json[i].profile
								+ "' style='width: 50px; height: 50px;border-radius: 50%;'><label style='color:white'>"
								+ nick + "</label>";
						div.style.display = "inline";
						div.className = "opp"

						opp.appendChild(div);

					}

				}
			}
		}
		xhr.open('post', '${cp}/story/opponentlist', true);
		xhr.setRequestHeader('Content-Type',
				'application/x-www-form-urlencoded');
		xhr.send();
	}

	var index = 0;
	var timeout = null;
	function showStory() {
		getProfile();

		const divs = document.getElementsByClassName("divs");
		const cdivs = document.getElementsByClassName("cdivs");
		const imgs = document.getElementsByClassName("imgs");
		var readuser = document.getElementsByClassName("readuser");
		var readstory = document.getElementsByClassName("readstory");
		for (let i = 0; i < imgs.length; i++) {
			if (i == index) {
				insertReadUser(readuser[i].value, readstory[i].value);
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
			setTimeout(closeStory, 3000);
		}
	}

	function closeStory() {
		location.href = "../layout.jsp";
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
	for (var i = 0; i < imgs.length; i++) {
		imgs[i].addEventListener('mousedown', function() {
			console.log("imgs/////down");
			clearTimeout(timeout)
		});

		imgs[i].addEventListener('mouseup', function() {
			console.log("imgs/////up");
			timeout = setTimeout(showStory, 3000);
		});
	}

	function stopImg() {
		clearTimeout(timeout);
	}

	function showImg() {
		timeout = setTimeout(showStory, 3000);
	}

	function insertReadUser(readuser, readstory) {
		console.log("dddddddddddddddddddd")
		let xhr1 = new XMLHttpRequest();
		console.log(readuser + "///" + readstory);

		xhr1.onreadystatechange = function() {
			console.log("1111111111");
			if (xhr1.readyState == 4 && xhr1.status == 200) {

				var data = xhr1.responseText;
				var json = JSON.parse(data);
				if (json.bl) {

				}
			}
		}
		xhr1.open('get', '${cp}/readuser/insert?member_no=' + readuser
				+ "&story_no=" + readstory, true);
		//		xhr1.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
		console.log("2222222222222");
		xhr1.send();
	}
</script>
</html>