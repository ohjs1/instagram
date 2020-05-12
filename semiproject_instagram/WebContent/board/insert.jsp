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
	//업로드하려는 이미지 미리보기
	function getImg(files){
		delImg(); //보여주기 전에 이미지 비워주기
		for(let i=0; i<files.length; i++){
			const file=files[i];
			
			if(!file.type.startsWith('image/')){continue}
			
			const span=document.getElementById("imgList");
			
			const img=document.createElement("img");
			img.style.width="110px";
			img.style.hight="110px";
			img.classList.add("slide");
			img.file=file;
			span.appendChild(img);
			
			const reader = new FileReader();
		    reader.onload = (function(aImg) { return function(e) { aImg.src = e.target.result; }; })(img);
		    reader.readAsDataURL(file);
		}
	}
	//span의 자식노드(이미지) 전체 삭제
	function delImg(){
		const span=document.getElementById("imgList");
		while(span.firstChild){
			span.removeChild(span.firstChild);
		}
	}
	//이전이미지 보여주기
	function previousImg(){
		const imgs=document.getElementsByClassName("slide");
		if(index<0){
			index=imgs.length-1;
		}else{
			index--;
		}
		viewImg();
	}
	//다음이미지 보여주기
	function nextImg(){
		const imgs=document.getElementsByClassName("slide");
		if(index>imgs.length-1){
			index=0;
		}else{
			index++;
		}
		viewImg();
	}
	//현재선택된 이미지만 보여주기
	function viewImg(){
		const imgs=document.getElementsByClassName("slide");
		for(let i=0;i<imgs.length;i++){
			if(i==index){
				imgs[i].style.display="block";
			}else{
				imgs[i].style.display="none";
			}
		}
	}
</script>
</head>
<body>
<form method="post" action="insert.jsp">
	<input type="button" onclick="previousImg()" value="이전">
	<span id="imgList"></span>
	<input type="button" onclick="nextImg()" value="다음">
	<textarea rows="5" cols="50" name="content" placeholder="문구 입력..." ></textarea><br>
	첨부파일<br>
	<input type="file" id="file1" name="file1" accept=".jpg,.jpeg,.png,.gif" multiple onchange="getImg(this.files)"><br>
	<input type="submit" value="확인">
</form>
</body>
</html>