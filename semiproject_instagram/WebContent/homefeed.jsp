<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HomeFeed</title>

</head>
<body onload="addStorys()">
	<h1>홈피드입니다</h1>
	계정설정 :
	<a href="${ cp }/member/memberInfo">프로필 수정</a>
	<br>
	<br>
	
	<a href="${ cp }/story/list"><img id="myprofile"
		style="width: 50px; height: 50px;border-radius: 50%;"></a>
	<a href="${ cp }/story/insert"><img src="${ cp }/upload/plus.png"></a>
	<div id="storys"></div>

</body>
<script>
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
		xhr.setRequestHeader('Content-Type',
				'application/x-www-form-urlencoded');
		xhr.send();
	}

		function addStorys() {
			var xhr = new XMLHttpRequest();
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4 && xhr.status == 200) {
					var sto = document.getElementById("storys");
					var data = xhr.responseText;
					var json = JSON.parse(data);
					for (var i = 0; i < json.length; i++) {

						var div = document.createElement("div");
						div.innerHTML = "<a href='${ cp }/story/opponentlist?member_no="
								+ json[i].member_no
								+ "'><img src='${cp}/upload/"+json[i].profile+"' style='width: 50px; height: 50px;border-radius: 50%;'>"
								+ "</a>  &nbsp;";
						div.style.display = "inline";
						div.className = "sto"
						sto.appendChild(div);
					}
					getProfile();
				}
			}
			xhr.open('post', '${cp}/story/opponentlist', true);
			xhr.setRequestHeader('Content-Type',
					'application/x-www-form-urlencoded');
			xhr.send();
			
		}

	
</script>
</html>