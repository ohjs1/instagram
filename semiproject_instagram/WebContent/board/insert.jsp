<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.insert_wrap{
	margin: auto;
	align-items: center;
	text-align: center;
}
#next,#prev{
    width: 30px;
    height: 30px;
    text-align: center;
    background-color: #fff;
    color: #212121;
    border-radius: 50px;
    border: none;
    font-size: 15px;
    font-weight: 700;
    z-index: 999;
}
#submit{
	width:300px;
	height:35px;
	border:0;
}

</style>
<script type="text/javascript">
	var index=0;
	//업로드하려는 이미지 미리보기
	function showImg(files){
		delImg(); //보여주기 전에 이미지 비워주기
		for(let i=0; i<files.length; i++){
			const file=files[i];
			
			if(!file.type.startsWith('image/')){continue}
			
			const span=document.getElementById("imgList");
			
			const img=document.createElement("img");
			img.style.width="200px";
			img.style.height="200px";
			img.style.display="none";
			img.classList.add("slide");
			img.file=file;
			span.appendChild(img);
			
			const reader = new FileReader();
		    reader.onload = (function(aImg) { return function(e) { aImg.src = e.target.result; }; })(img);
		    reader.readAsDataURL(file);
		}
		const imgs=document.getElementsByClassName("slide");
		imgs[index].style.display="inline";
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
		index--;
		if(index<0){
			index=imgs.length-1;
		}
		viewImg();
	}
	//다음이미지 보여주기
	function nextImg(){
		const imgs=document.getElementsByClassName("slide");
		index++;
		if(index>imgs.length-1){
			index=0;
		}
		viewImg();
	}
	//현재 이미지는 inline, 나머지는 none으로 display 설정
	function viewImg(){
		const imgs=document.getElementsByClassName("slide");
		for(let i=0;i<imgs.length;i++){
			if(i==index){
				imgs[i].style.display="inline";
			}else{
				imgs[i].style.display="none";
			}
		}
	}
	//파일 유효성 체크
	function fileChk(){
		const file=document.getElementById("file1").value;
		if(file==null || file==""){
			return false;
		}else{
			return true;
		}
	}
</script>
</head>
<body> 
<div class="insert_wrap">
	<h1>게시글 작성</h1><br>
	<form method="post" action="<c:url value='/board/insert'/>" onsubmit="return fileChk()" enctype="multipart/form-data">
		<input type="hidden" name="board_no" value="${param.board_no }">
		<input type="hidden" name="ref" value="${param.ref }">
		<input type="hidden" name="lev" value="${param.lev }">
		<input type="hidden" name="step" value="${param.step }">
		
		<input type="button" onclick="previousImg()" id="prev" value="<">
		<span id="imgList"></span>
		<input type="button" onclick="nextImg()" id="next" value=">">
		<textarea rows="5" cols="50" name="content" placeholder="문구 입력..." ></textarea><br>
		<h4>이미지 추가</h4><br>
		<input type="file" id="file1" name="file1" accept=".jpg,.jpeg,.png,.gif,.mp4" multiple onchange="showImg(this.files)"><br>
		<input type="submit" value="추가하기" id="submit">
	</form>
	<c:set var="file1" value="${fileList[0] }"/>
</div>
</body>
</html>