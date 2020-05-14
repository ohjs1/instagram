<%@page import="com.vo.ImageVo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.dao.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	
</script>
</head>
<body>
<%
	String upload=application.getRealPath("upload");
	int member_no=(int)session.getAttribute("member_no");
	BoardDao dao=new BoardDao();
	ArrayList<ImageVo> list=dao.selectImg(member_no);
	System.out.println(list);
%>
<h1>게시물</h1>
<%
	for(int i=0; i<list.size(); i++){
		ImageVo vo=list.get(i);
		if(i!=0 && i%3==0){
%>
			<img src="upload/<%=vo.getImagepath() %>">
<%
		}
	}
%>

</body>
</html>