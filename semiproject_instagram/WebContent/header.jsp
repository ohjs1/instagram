<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>instagram header</title>
<style>
	*{
		padding: 0;
		margin: 0;
	}
	#wrap_search_list{
		width: 100%;
	}
	#search_list{
		width: 100%;
		margin: auto;
	}
	#tag{
		display: inline-block;
	    overflow: auto;
	    width: 241px;
	    height: 362px;
	    margin:auto;
    	background-color: white;
	}
	#myAccount{
		border-radius: 70%;
		width: 35px;
		height: 35px;
	}
	#header_logo{
		display: inline-block;
    	float: left;
	}
	#header_menu{
		display: inline-block;
    	float: right;
	}
	#header_search{
		display: inline-block;
	}
	#header_wrap{
		width: 60%;
	    margin: auto;
	    margin-top: 10px;
	}
	.search_div{
		border-bottom: 1px solid gray;
	}
	.search_img{
		width:32px;
		height:32px;
		border-radius: 70%;
	}
</style>
<!-- Bootstrap -->
    <link href="${ cp }/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>
<div id="header_wrap">
	<div id="header_logo">
		<img src="${ cp }/images/logo.png" alt="logo" id="logo">
	</div>
	<div id="header_search">
		<input type="text" placeholder="검색" name="search" id="search" class='input-medium search-query'
		onkeyup="search()">
	</div>
	<div id="header_menu">
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
		<a href="${ cp }/feed/myfeed">
		<img src="${cp}/upload/${sessionScope.profile}" alt="계정설정" id="myAccount">
		</a>
	</div>
</div>
<div id="wrap_search_list">
	<div id="search_list">
		<div id="tag"></div>
	</div>
</div>
	<script src="https://code.jquery.com/jquery.js"></script>
    <script src="${ cp }/bootstrap/js/bootstrap.min.js"></script>
</body>
<script type="text/javascript">
	var myAccount=document.getElementById("myAccount");
	var xhr =null;
	var div=document.getElementById("tag");
	div.style.display="none";
	function search() {
		var search=document.getElementById("search").value;
		var keyword=search.replace("#", "%23");//#을 못씀
		xhr=new XMLHttpRequest();
		xhr.onreadystatechange=searchTag;
		if(!search==""){
			div.style.display="block";
			xhr.open('get','${cp}/tag/search?search='+keyword, true);
			xhr.send();
		} else {
			div.style.display="none";
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
				if(json[i].result==1){
				var keyword=json[i].keyword.replace("#", "%23");
					div.innerHTML+=
					"<div class='search_div'>"+
						"<div class='search_profile'>"+
							"<img src='${cp}/upload/tag.PNG' class='search_img'>"+
						"</div>"+
						"<div class='search_link'>"+
							"<a href='${cp}/tag/list?keyword="+ keyword +"'>"+
							"#"+keyword.substr(3, keyword.length) +"</a><br>"+
							"게시물"+json[i].board_cnt+
						"</div>"+
					"</div>";
				}else if(json[i].result==2){
					div.innerHTML+=
					"<div class='search_div'>"+
						"<div class='search_profile'>"+
							"<img src='${cp}/upload/"+json[i].profile+"' class='search_img'>"+
						"</div>"+
						"<div class='search_link'>"+
							"<a href='${cp}/follow/move?youmember_no="+ json[i].member_no +"'>"+
							"@"+json[i].keyword +"</a><br>"+
							json[i].name+
						"</div>"+
					"</div>";
				}else if(json[i].result==3){
					if(json[i].cnt>0){
					var keyword=json[i].keyword.replace("#", "%23");
						div.innerHTML+=
						"<div class='search_div'>"+
							"<div class='search_profile'>"+
								"<img src='${cp}/upload/tag.PNG' class='search_img'>"+
							"</div>"+
							"<div class='search_link'>"+
								"<a href='${cp}/tag/list?keyword="+ keyword +"'>"+
								"#"+keyword.substr(3, keyword.length) +"</a><br>"+
								"게시물"+json[i].cnt+
							"</div>"+
						"</div>";
					}else{
						div.innerHTML+=
						"<div class='search_div'>"+
							"<div class='search_profile'>"+
								"<img src='${cp}/upload/"+json[i].profile+"' class='search_img'>"+
							"</div>"+
							"<div class='search_link'>"+
								"<a href='${cp}/follow/move?youmember_no="+ json[i].member_no +"'>"+
								"@"+json[i].nickname +"</a><br>"+
								json[i].name+
							"</div>"+
						"</div>";
					}
						
				}
			}
		}
		div.style.border="1px solid gray";
		div.style.fontSize="1.1em";
	}
	
	
</script>
</html>
