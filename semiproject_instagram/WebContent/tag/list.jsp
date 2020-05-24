<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	#headerTag{
		width:940px;
		margin:auto;
	}
	#headerTag img{
		padding: 30px;
		margin-right: 30px;
		border-radius: 70%;
		float: left;
		width: 152px;
		height: 152px;
	}
	#dd{
		padding-top: 50px;
	}
	strong{
		font-size: 2.0em;
	}
	.list_img{
		display: block;
		float: left;
		padding: 10px;
		width: 293px;
		height: 293px;'
	}
	#div_ajax{
		width: 50%;
		margin: auto;
	}
	#div_img{
		display: inline-block;
		width: 950px;
	}
</style>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body onload="test()">
<div id="headerTag">
	<a href="#" id="alink" onclick="storytaglist()"><img src="${cp}/upload/${vo[0].imagepath}" id="storyTagImg"></a>

	<div id="dd"><strong>${keyword }</strong></div>
	<h3>게시물<span id="cnt"> ${vo.size() }</span><h3>
</div>
<div id="imglist">
	<c:set var="c" value="0" />
	<div id="div_ajax">
		<div id="div_img">
			<c:forEach var="vo" items="${vo}">
				<c:if test="${c<9 }">
					<img src="${cp }/upload/${vo.imagepath}" class="list_img">
					<c:set var="c" value="${c+1 }"/>
				</c:if>
			</c:forEach>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
	
	
	var storyTagImg=document.getElementById("storyTagImg");
	var xhr=null;

	function test() {
		xhr=new XMLHttpRequest();
		xhr.onreadystatechange=function(){
		var k='${keyword}';
	    var test=k.replace("#", "%23");
			if(xhr.readyState==4 && xhr.status==200){
				var data=xhr.responseText;
				var json=JSON.parse(data);
				var alink=document.getElementById("alink");
				if(json[0].filepath!="" & json[0].filepath!=null){
					storyTagImg.src="${cp}/upload/"+json[0].filepath;
					if(alink.clicked){
						storytaglist(test);
					}
				}else{
					storyTagImg.src="${cp }/upload/${vo[0].imagepath}";
				}
			}
		}
		xhr.open('get','${cp}/tag/showStroy?keyword='+test,true);
		xhr.send();
	}
	
	function storytaglist(test){
		console.log(test);
		location.href="${cp}/story/storytaglist?keword="+test;
		
	}
	//무한스크롤
	xhrImg=null;
	var snum=7;
	var endnum=9;
	$(window).scroll(function() {
	    if ($(window).scrollTop() == $(document).height() - $(window).height()) {
	    	xhrImg=new XMLHttpRequest();
	    	xhrImg.onreadystatechange=function(){
	    		if(xhrImg.readyState==4 && xhrImg.status==200){
	    			setTimeout(function() {}, 2000);
	    			data=JSON.parse(xhrImg.responseText);
	    			var str="";
	    			for(var i=0;i<data.length;i++){
    					str+="<img src='${cp}/upload/"+data[i].filepath+"'class='list_img'>";
	    			}
	    				$("#div_img").append(str);
	    				snum+=3;
	    				endnum+=3;
	    		}
	    	}
	    	
	    	var k='${keyword}';
	    	var test=k.replace("#", "%23");
	    	xhrImg.open('get','${cp}/tag/list?keyword='+test+'&snum='+snum+'&endnum='+endnum,true);
	    	xhrImg.send();
	    }
	});
</script>
</html>












