<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
body{background-color: black;}
div{margin:auto;}
label {
	margin:auto;
}
</style>
<script type="text/javascript">
	var index=0;
	function showStory(){	
		
		const divs=document.getElementsByClassName("divs");		
		const cdivs=document.getElementsByClassName("cdivs");
		const imgs=document.getElementsByClassName("imgs");	
		for(let i=0;i<imgs.length;i++){
			if(i==index){
				divs[i].style.display="block";
				imgs[i].style.display="block";
				cdivs[i].style.display="block";
			}else{
				divs[i].style.display="none";
				imgs[i].style.display="none";
				cdivs[i].style.display="none";
			}
		}
				
		index++;	
		if (index > imgs.length-1) {index = 0}   
		setTimeout(showStory,2000);
	}
	
</script>
</head>
<body onload="showStory()">
<h1>마이스토리 리스트</h1>
<label><img src="../upload/profile.png"></label>

<br>


<c:forEach var="vo" items="${list }">
<c:set var="file2" value="${vo.getFilepath()}"/>
<div id="file" style="width:400px;height:700px;position:relative;display:none" class="divs">

<c:choose>
	<c:when test="${fn:endsWith(file2,'.png') or fn:endsWith(file2,'.jpg') }">
		<img src="${cp }/upload/${file2 }" style="width:400px;height:700px;display:none" id="output" class="imgs">
	</c:when>
	<c:otherwise>
		<video src="${cp }/upload/${file2 }" style="width:400px;height:700px;display:none" class="imgs" autoplay="autoplay" muted="muted" loop="loop"></video>
	</c:otherwise>
</c:choose>
	
		<div id="content" style="width:280px;height:200px;position:absolute;top:250px;left:10px;display:none" class="cdivs">
			${vo.getContent() }
	</div>
</div>
</c:forEach>


</body>
</html>