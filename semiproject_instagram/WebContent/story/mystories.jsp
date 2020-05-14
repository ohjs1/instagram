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

</style>
<script type="text/javascript">
	var index=0;
	var timeout=null;
	function showStory(){	
		const wrap=document.getElementsByclassName("wrap");
		const divs=document.getElementsByClassName("divs");		
		const cdivs=document.getElementsByClassName("cdivs");
		const imgs=document.getElementsByClassName("imgs");	
		for(let i=0;i<imgs.length;i++){
			if(i==index){
				wrap[i].style.display="block";
				divs[i].style.display="block";
				imgs[i].style.display="block";
				cdivs[i].style.display="block";
			}else{
				wrap[i].style.display="none";
				divs[i].style.display="none";
				imgs[i].style.display="none";
				cdivs[i].style.display="none";
			}
		}
				
		index++;	
		if (index > imgs.length-1) {/*location.href="layout.jsp";*/}   
		timeout=setTimeout(showStory,2000);
	}
	
	function previousImg(){
		const wrap=document.getElementsByclassName("wrap");
		const divs=document.getElementsByClassName("divs");		
		const cdivs=document.getElementsByClassName("cdivs");
		const imgs=document.getElementsByClassName("imgs");	
		
		index--;
		if(index<0){
			index=imgs.length-1;
		}
		
		showStory();
	}
	
	function nextImg(){
		const wrap=document.getElementsByclassName("wrap");
		const divs=document.getElementsByClassName("divs");		
		const cdivs=document.getElementsByClassName("cdivs");
		const imgs=document.getElementsByClassName("imgs");	
		
		index++;
		if(index>imgs.length-1){
			index=0;
		}
		
		showStory();
	}
	
	
	
	const imgs=document.getElementsByClassName("imgs");
	imgs.onmouseover=function(){
		timeout=clearTimeout(showStory)
	};
	
	imgs.onmouseout=function(){
		timeout=setTimeout(showStory,2000);
	};
	
</script>
</head>
<body onload="showStory()">
<form method="post" action="${cp }/story/delete">
<label><img src="../upload/profile.png"></label>
<input type="button" value="이전" onclick="previousImg()">
<input type="button" value="다음" onclick="nextImg()">
<input type="hidden" name="story_no" value="${vo.getStory_no() }">

<c:forEach var="vo" items="${list }">
<c:set var="file2" value="${vo.getFilepath()}"/>
<div id="btndiv" style="width:430px;height:800px;position:relative;border:1px solid white;display: none" class="wrap">

	<div id="file" style="width:400px;height:700px;position:absolute;display:none" class="divs">
	
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
</div>
</c:forEach>

<input type="submit" value="삭제">
</form>

</body>
</html>