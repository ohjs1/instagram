<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>instagram header</title>
<style>
	#tag{
		display: inline-block;
	    overflow: auto;
	    width: 171px;
	    height: 90px;
	    margin: auto;
	    margin-right: 57px;
	}
</style>
</head>
<body>
<div>
	<img src="${ cp }/images/logo.png" alt="logo" id="logo">
	
	<input type="text" placeholder="검색" name="search" id="search"
		onkeyup="search()">
	<a href="${ cp }/board/insert"> <img
		src="${ cp }/images/icon/writer.jpg" alt="글쓰기" />
	</a>
	<a href="${ cp }/layout.jsp"><img
		src="${ cp }/images/icon/home.jpg" alt="홈" /></a>
	<a href="${ cp }/dm/inbox?member_no=${member_no}"><img
		src="${ cp }/images/icon/dm.jpg" alt="다이렉트 메시지" /></a>
	<a href="${ cp }/feed/myfeed"> <img
		src="${ cp }/images/icon/location.jpg" alt="내피드로가게함(임시)" />
	</a>
	<img src="${ cp }/images/icon/likes.jpg" alt="좋아요" />
</div>

<div id="tag"></div>

</body>
<script type="text/javascript">
	var xhr =null;
	var div=document.getElementById("tag");
	function search() {
		var search=document.getElementById("search").value;
		var keyword=search.replace("#", "%23");//#을 못씀
		console.log("keyword---"+keyword);
		xhr=new XMLHttpRequest();
		xhr.onreadystatechange=searchTag;
		if(!search==""){
			xhr.open('get','${cp}/tag/search?search='+keyword, true);
			xhr.send();
		} else {
			div.innerHTML="";
			div.style.border="";
		}
	}
	function searchTag(){
		if(xhr.readyState==4 && xhr.status==200){
			var data=xhr.responseText;
			var json=JSON.parse(data);
			div.innerHTML="";
			for(var i=0; i<json.length; i++){
				var keyword=json[i].keyword.replace("#", "");
				div.innerHTML+="<a href='${cp}/tag/list?keyword="+ keyword +"'>"+ keyword +"</a><br>";
			}
			div.style.border="1px solid gray";
		}
	}
</script>
</html>
