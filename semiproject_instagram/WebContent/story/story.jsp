<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>story_upload</title>
<script type="text/javascript">
function getFile(event){
	var output=document.getElementById("output");
	output.src=URL.createObjectURL(event.target.files[0]);
	
}


</script>
</head>
<body>
<% 
	String cp=application.getContextPath();
%>

<form method="post" action="${cp }/story/insert" enctype="multipart/form-data">

<label for="file1"><img src="../upload/plus.png"></label>
<input type="file" name="file1" style="display:none" id="file1" onchange="getFile(event)" >
 
<br>
<div id="file" style="width:300px;height:500px;border:3px solid black;position:relative">
	<img id="output" style="width:300px;height:500px">
	<div id="content" style="width:280px;height:200px;position:absolute;top:250px;left:10px;">
		<textarea rows="8" cols="26" name="content" style="border:0;background-color: transparent;"></textarea>
	</div>
</div>

<input type="submit" value="등록"> 
</form>
</body>

</html>