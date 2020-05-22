<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>story_upload</title>
<style>
#wrap{
	width:450px;
	height:900px;
	margin-top: 50px;
	padding-top:20px;
	margin:0 auto;

}
textarea{
	color:white;
	font-family: HY견고딕;
	font-size: 15px;
	text-shadow: -1px 0 black, 0 1px black, 1px 0 black, 0 -1px black;
}
#btns{
	width:450px;
	height: 50px;
	position: relative;
}

#upbtn{
	width:40px;
	height: 40px;
	right: 4px;
	position:absolute;
}

#lb{
	width:40px;
	height: 40px;
	left:4px;
	position:absolute;
}

#text{
	width:150px;
	height: 40px;
	margin-left:45px;
	position:absolute;
}
</style>
<script type="text/javascript">

	function getFile(event){
		var output=document.getElementById("output");
		outputI.src=URL.createObjectURL(event.target.files[0]);
		/*
		var filename=URL.createObjectURL(event.target.files[0]);
		console.log(filename);
		if(filename.contains(".png") || filename.contains(".jpg")){
			outputI.src=URL.createObjectURL(event.target.files[0]);
		}else if(filename.contains(".mp4")) {
			outputV.src=URL.createObjectURL(event.target.files[0]);
		}
		
	*/
		var btn=document.getElementById("upbtn");
		var text=document.getElementById("text");
		if(file==null || file==""){
			return false;
		 	btn.style.visibility='hidden';
		}else{
			btn.style.visibility="visible";
			text.style.visibility='hidden';
		}
	}
	
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
<div id="wrap">

	<form method="post" action="${cp }/story/insert" onsubmit="return fileChk()" enctype="multipart/form-data">
	<div id="btns">
		<label id="lb" for="file1"><img src="${ cp }/upload/plus.png"></label>
		<input type="file" name="file1" style="display:none" id="file1" onchange="getFile(event)" >
		<label id="text">사진을 추가해주세요 ! </label>
		<input type="submit" value="등록" id="upbtn" style="visibility:hidden;"> 
	</div>
		<div id="file" style="width:450px;height:750px;border:2px solid gray;position:relative;">
			<img id="outputI" style="width:450px;height:750px"> 
			<!-- <video id="outputV" style="width:300px;height:500px"></video>  -->
			<div id="content" style="width:400px;height:300px;position:absolute;top:350px;left:10px;">
				<textarea rows="15" cols="120" name="content" style="border:0;background-color: transparent;"></textarea>
			</div>
		</div>
		
		
	</form>

</div>
</body>

</html>