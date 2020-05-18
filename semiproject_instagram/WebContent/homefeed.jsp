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

	
	<a href="${ cp }/story/insert">스토리올리기</a>
	<a href="${ cp }/story/list">내 스토리</a>
	<div id="storys"></div>
	
</body>
<script>


function addStorys(){
	var xhr=new XMLHttpRequest();
	xhr.onreadystatechange=function(){
		if(xhr.readyState==4&&xhr.status==200){
			var sto=document.getElementById("storys");
			var data=xhr.responseText;
			var json=JSON.parse(data);
			for(var i=0;i<json.length;i++){
				
				var div=document.createElement("div");
				div.innerHTML="<a href='${ cp }/story/opponentlist?member_no="+json[i].member_no+"'>전체 스토리"+(i+1)+"</a>  &nbsp;";
				div.style.display="inline";
				div.className="sto"
				sto.appendChild(div);
			}
			}
		}
	xhr.open('post','${cp}/story/opponentlist',true);
	xhr.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
	xhr.send();
	
}
</script>
</html>