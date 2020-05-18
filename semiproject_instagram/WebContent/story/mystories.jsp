<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyStoriess</title>
<style>
*{padding:0px;margin:0px}
body{background-color: black;}
div{margin:auto;}

</style>

</head>
<body onload="showStory()">

<label><img src="${cp }/upload/profile.png"></label>
<input type="button" value="이전" onclick="previousImg()">
<input type="button" value="다음" onclick="nextImg()">
<input type="button" value="정지" onclick="stopImg()">
<input type="button" value="재생" onclick="showImg()">


<c:forEach var="vo" items="${list }">

<c:set var="file2" value="${vo.getFilepath()}"/>


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
				${vo.getStory_no() }
				내 스토리 읽은 사람 : ${sessionScope.id }
				<input type="hidden" id="story_no" name="story_no" value="${vo.getStory_no() }">
				<input type="button" value="삭제" onclick="deleteStory()">
		</div>
		
	</div>

</c:forEach>




</body>
<script type="text/javascript">


	var index=0;
	var timeout=null;
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
		timeout=setTimeout(showStory,3000);
		if (index > imgs.length-1) {
			setTimeout(closeStory,3000);
			
			}   
		
	}
	
	function closeStory(){
		location.href="../layout.jsp";
	}
	
	function previousImg(){
		clearTimeout(timeout);
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
		clearTimeout(timeout);
		const divs=document.getElementsByClassName("divs");		
		const cdivs=document.getElementsByClassName("cdivs");
		const imgs=document.getElementsByClassName("imgs");	
		
		index++;
		if(index>imgs.length-1){
			index=0;
		}
		
		showStory();
	}
	
	function stopImg(){
		clearTimeout(timeout);
	}
	
	function showImg(){
		timeout=setTimeout(showStory,3000);
	}
	
	
	const imgs=document.getElementsByClassName("imgs");
	imgs.addEventListener('onmousedown',function(){
		clearTimeout(timeout)
	});
	
	
	imgs.addEventListener('onmouseup',function(){
		timeout=setTimeout(showStory,3000);
	});
	
	
	function deleteStory(){
		var xhr=new XMLHttpRequest();
		var story_no=document.getElementById("story_no").value;
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4&&xhr.status==200){
				
				var data=xhr.responseText;
				var json=JSON.parse(data);
					if(json.bl){
						showStory();
					}else{
						alert('삭제실패!');
					}
				}
			}
		xhr.open('post','${cp}/story/delete',true);
		xhr.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
		xhr.send('story_no='+story_no);
		
	}
	
</script>
</html>