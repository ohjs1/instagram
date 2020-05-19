<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>${msg }</h1>
	<a href="javascript:history.back()">뒤로가기</a>

<!-- 

	String msg = request.getParameter("msg");
	if(msg=="success"){
	%>
		goBack();
	}else{
		alert("팔로우실패");
	}
 -->
</body>
<!-- 
	<script type="text/javascript">
		function goBack() {
			window.history.back()
		}
	</script>
 -->
</html>