<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border=4 width=100%>
	<tr>
		<th>Direct</th>
		<th rowspan='5'>
		<h2>내 메시지</h2>
		<h3>친구나 그룹에 비공개 사진과 메시지를 보내보세요.</h3>
		<div id="myModal" class="modal">
					
		</div>
			<input type="button" value="메시지 보내기" id="btn"/>
		</th>
	</tr>
	<tr>
		<td rowspan='5'>유저 목록</td>
	</tr>
</table>
</body>
<script type="text/javascript">
	var btn = document.getElementById("btn");
	var modal = document.getElementById("myModal");
	
</script>
</html>