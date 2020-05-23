<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HomeFeed</title>
<style>
#wrap{width:60%;height: 100%;margin:auto;}
#s{width:97%;height: 25%; border:1px solid #d3d3d3; border-radius:5px;  margin:10px;padding:5px;display: flex;}
#my {width:100px;height:100px; text-align: center;position:relative;}
#plus {width:50px;height:50px;position:absolute;top:1px;right:1px;}
.sto {width:100px;height:50px;text-align: center;}
</style>
</head>
<body onload="addStorys()">
<div id="wrap">
	<h1>홈피드입니다</h1>
	계정설정 :
	<a href="${ cp }/member/memberInfo">프로필 수정</a>
	<a href="${cp }/member/logout">로그아웃</a><br>
	<br>
	<br>
	<div id="s">
		<div id="my">
			<a href="${ cp }/story/list"><img id="myprofile"
				style="width: 50px; height: 50px;border-radius: 50%;"></a><br>
			${nickname }
				<div id="plus">
					<a href="${ cp }/story/insert"><img src="${ cp }/upload/plus.png" style="width:20px;height: 20px"></a><br>
				</div>
		</div>
		<div id="storys"></div>
	</div>
	<div id="modal1">
		<c:forEach var="vo" items="${boardList }" varStatus="i">
		
		</c:forEach>
	</div> <!-- 내 게시글 및 내가 팔로우한 회원들의 게시글이 추가될 div -->
	
</div>
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
				
				//게시글파트(송영현)
				for(let i=0; i<${boardList.size()}; i++){
					
				}
			}
		}
		xhr.open('post', '${cp}/member/memberInfo', true);
		xhr.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
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
								+ "</a><br><label style='color:black;'>"+json[i].nickname+"</label>&nbsp";
						div.style.display = "inline-block";
						div.style.margin = "auto";
						div.className = "sto";
						sto.appendChild(div)						
					}
					getProfile();
				}
			}
			xhr.open('post', '${cp}/story/opponentlist', true);
			xhr.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
			xhr.send();		
		}
/////////////////////////////////// 게시글 파트 //////////////////////////////////////////
	//게시글 리스트 불러오기
	var boardList=null;
	function getBoardList(board_no){
		//const board_no=${imageVo.board_no};
		boardList=new XMLHttpRequest();
		boardList.onreadystatechange=getBoardListOk;
		boardList.open('post','../board/list',true);
		boardList.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
		boardList.send('board_no='+board_no);
	}

</script>
</html>