<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>story_upload</title>
<style>
textarea{color:white;font-family: HY견고딕;font-size: 15px;
text-shadow: -1px 0 black, 0 1px black, 1px 0 black, 0 -1px black;}
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
<% 
	String cp=application.getContextPath();
%>

<form method="post" action="${cp }/story/insert" onsubmit="return fileChk()" enctype="multipart/form-data">

<label for="file1"><img src="${ cp }/upload/plus.png"></label>
<input type="file" name="file1" style="display:none" id="file1" onchange="getFile(event)" >
 
<br>
<div id="file" style="width:300px;height:500px;border:3px solid black;position:relative">
	<img id="outputI" style="width:300px;height:500px"> 
	<!-- <video id="outputV" style="width:300px;height:500px"></video>  -->
	<div id="content" style="width:280px;height:200px;position:absolute;top:250px;left:10px;">
		<textarea rows="8" cols="20" name="content" style="border:0;background-color: transparent;"></textarea>
	</div>
</div>

<input type="submit" value="등록"> 
</form>
</body>

</html>