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
		<c:if test="${bl==true}">
	<table id="list2">
		<tr>
		<td colspan="3">
		<h1>팔로워</h1></td></tr>
			<c:forEach var="vo" items='${list }'>
				<c:set var="i" value="${i+1 }"/>
				<tr id="result${i }">
					<td class="mem"><a href="${cp }/follow/move?youmember_no=${vo.getMember_no()}">
						<img src="${cp }/upload/${vo.getProfile()}"style="width: 50px; height: 50px;border-radius: 50%;"></a></td>
					<td class="mem"><a href="${cp }/follow/move?youmember_no=${vo.getMember_no()}">${vo.getNickname() }</a></td>
					<c:choose>
						<c:when test="${sessionScope.member_no == mymember_no }">
							<td><a href="javascript:callDelete(${vo.getMember_no()},${i });">삭제</a></td>	
						</c:when>			
					</c:choose>
					</tr>
			</c:forEach>
		</table>
		</c:if>
		
		<c:if test="${bl==false}">
			<table id="list3">
		<tr>
		<td colspan="3">
			<h1>팔로잉</h1></tr>
			<c:forEach var="vo" items='${list }'>
				<c:set var="j" value="${j+1 }"/>
			<tr>
				<td class="mem"><a href="${cp }/follow/move?youmember_no=${vo.getMember_no()}">
					<img src="${cp }/upload/${vo.getProfile()}"style="width: 50px; height: 50px;border-radius: 50%;"></a>
				</td>
				<td class="mem"><a href="${cp }/follow/move?youmember_no=${vo.getMember_no()}">${vo.getNickname() }</a></td>
				<td><a href="${cp }/follow/insert?youmember_no=${vo.getMember_no() }">
				<input type="button" value="팔로우" class="folbtn"></a>
				</td>			
			</tr>
			</c:forEach>
		</table><br>
		</c:if>
	<a href="javascript:history.back()">뒤로가기</a>
	</div>
</div>
</body>
<script type="text/javascript">
	function callDelete(member_no,i){
		console.log(member_no+":"+i);
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4 && xhr.status==200){
				console.log("삭제");
				var list2=document.getElementById("list2");
				list2.deleteRow(i);
			}
		};
		xhr.open('get','${cp}/follow/delete?youmember_no='+member_no,true);
		xhr.send();
	}
	
	function callinsert(member_no,j){
		
	}
</script>
</html>