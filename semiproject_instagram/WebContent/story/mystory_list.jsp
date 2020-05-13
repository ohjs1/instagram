<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	var index=0;
	function showStory(){	
		
		const divs=document.getElementsByClassName("divs");		
		const cdivs=document.getElementsByClassName("cdivs");		
		const imgs=document.getElementsByClassName("imgs");	
	
			divs[index].style.display="block";
			imgs[index].style.display="block";
			cdivs[index].style.display="block";
			
			
			
		
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
<div id="file" style="width:300px;height:500px;position:relative;display:none" class="divs">

	<img src='../upload/${vo.getFilepath()}' style="width:300px;height:500px;display:none" id="output" class="imgs">
		<div id="content" style="width:280px;height:200px;position:absolute;top:250px;left:10px;display:none" class="cdivs">
			${vo.getContent() }
	</div>
</div>
</c:forEach>


</body>
</html>