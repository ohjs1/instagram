<%@page import="com.vo.ImageVo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.dao.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 피드</title>
<style type="text/css">
	#board{width: 200px; height: 200px;}
	th{width:200px;height:200px;}
	
	
</style>
</head>
<body>
	<h1>${id }</h1>
	<a href="${cp }/follow/select?mymember_no=${member_no}">팔로워</a>
	<a href="${cp }/follow/select?youmember_no=${member_no}">팔로잉</a>
<%
	String upload=application.getRealPath("upload");
	int member_no=(int)session.getAttribute("member_no");
	BoardDao dao=new BoardDao();
	ArrayList<ImageVo> list=dao.selectImg(member_no);
%>
<h1>게시물</h1>
<table border="1" width="600px">
	<tr>
<%
	if(list!=null){
		for(int i=0; i<list.size(); i++){
			ImageVo vo=list.get(i);
			if(i!=0 && i%3==0){
%>
	</tr>
	<tr>
<%
			}
%>
		<th>
			<div class="modal">
				<div class="modal_overlay">
					<a href="">
						<img src="../upload/<%=vo.getImagepath() %>" id="board">
					</a>
				</div>
				<div class="modal_content">
					
				</div>
			</div>
		</th>
<%
		}
	}
%>
	</tr>
</table>
</body>
</html>