<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	table{
		display: inline-block;
	}
	th{
		padding: 10px;
	}
	#imglist{
		text-align: center;
		clear: both;
	}
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
</style>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body onload="stroyTagImg()">
<div id="headerTag">
	<img src="${cp }/upload/${vo[0].imagepath}" id="storyTagImg"><!-- 스토리 ajax로 보여지기 -->
	<div id="dd"><strong>#${keyword }</strong></div>
	<h3>게시물<span id="cnt"> ${vo.size() }</span><h3>
</div>
<div id="imglist">
	<c:set var="i" value="0" />
	<c:set var="j" value="3" />
	<table>
		<c:forEach var="vo" items="${vo}">
			<c:if test="${i%j == 0 }">
				<tr>
			</c:if>
				<th>
					<img src="${cp }/upload/${vo.imagepath}" style='width: 293px;height: 293px'>
				</th>
			<c:if test="${i%j == j-1 }">
				</tr>
			</c:if>
			<c:set var="i" value="${i+1 }" />
		</c:forEach>
	</table>
</div>
</body>
<script type="text/javascript">
	var storyTagImg=document.getElementById("storyTagImg");
	var xhr=null;
	function stroyTagImg(){
		xhr=new XMLHttpRequest();
		xhr.onreadystatechange=showTagImg;
		xhr.open('get','/tag/showStroy?keyword=${keyword }',true);
		xhr.send();
	}
	function showTagImg() {
		if(xhr.readyState==4 && xhr.status==200){
			var data=xhr.responseText;
			var json=JSON.parse(data);
			if(json[0].filepath!="" & json[0].filepath!=null){
				storyTagImg.src="${cp}/upload/"+json[0].filepath;
			}else{
				storyTagImg.src="${cp }/upload/${vo[0].imagepath}";
			}
		}
	}
</script>
</html>












