<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>팔로우 리스트</title>
<style>
#list{width:50%;height:70%;margin: auto; border: 2px solid black;}
#list1{width:50%;height:100%;margin:auto; border: 2px solid black;}
h1{position: relative; left:35% ;margin-bottom: 28px;}
#list2{margin: auto;}
.mem{
	width:100px;
}
</style>
</head>
<body>

<div id="list">
	<div id="list1">
	<table id="list2">
		<tr>
		<c:if test="${bl==true}">
		${sessionScope.member_no }
		${mymember_no }
		${list.size() }
		<h1>팔로워 ${list.size() }</h1>
			<c:forEach var="vo" items='${list }'>
				<td class="mem"><a href="${cp }/follow/move?youmember_no=${vo.getMember_no()}">
					<img src="${cp }/upload/${vo.getProfile()}"style="width: 50px; height: 50px;border-radius: 50%;"></a></td>
				<td class="mem"><a href="${cp }/follow/move?youmember_no=${vo.getMember_no()}">${vo.getNickname() }</a></td>
				<c:choose>
					<c:when test="${sessionScope.member_no == mymember_no }">
						<td><a href="${cp }/follow/delete?youmember_no=${vo.getMember_no() }">삭제</a><br></td>			
					</c:when>			
				</c:choose>
				</tr><tr>		
			</c:forEach>
		</c:if>
		<c:if test="${bl==false}">
		${list.size() }
		<h1>팔로잉 ${list.size() }</h1>
			<c:forEach var="vo" items='${list }'>
				<td class="mem"><a href="${cp }/follow/move?youmember_no=${vo.getMember_no()}">
					<img src="${cp }/upload/${vo.getProfile()}"style="width: 50px; height: 50px;border-radius: 50%;"></a>
				</td>
				<td class="mem"><a href="${cp }/follow/move?youmember_no=${vo.getMember_no()}">${vo.getNickname() }</a></td>
				<td><a href="${cp }/follow/insert?youmember_no=${vo.getMember_no() }">
				<input type="button" value="팔로우" class="folbtn">
				</a></td>			
				</tr><tr>
			</c:forEach>
		</c:if>
		</tr>
	</table><br>
	<a href="javascript:history.back()">뒤로가기</a>
	</div>
</div>
</body>
</html>