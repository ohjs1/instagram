<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function getFile(event){
	var output=document.getElementById("output");
	output.src=URL.createObjectURL(event.target.files[0]);
	
}


</script>
</head>
<body>
<h1>마이스토리 리스트</h1>
<label><img src="../upload/profile.png"></label>

<br>
<div id="file" style="width:300px;height:500px;border:3px solid black;position:relative">
	<img id="output" style="width:300px;height:500px">
	<div id="content" style="width:280px;height:200px;position:absolute;top:250px;left:10px;">
		
	</div>
</div>
</body>
</html>