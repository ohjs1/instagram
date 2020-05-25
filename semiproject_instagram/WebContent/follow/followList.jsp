<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>팔로우 리스트</title>
<style>
#list{width:50%;height:70%;margin: auto;}
#list1{width: 330px;
    height: 500px;
    margin-left: 244px;
    margin-top: 40px;}
h1{position: relative; left:25% ;margin-bottom: 28px;}
#list2{margin: auto;}
#list3{margin: auto;}
.mem{width:100px;}
#back{width: 400px;
    position: relative;
    left: 337px; margin-left: -200px;}
table {
  border-collapse: separate;
  border-spacing: 0 10px;
}
</style>
</head>
<body>

<div id="list">
	<div id="list1">
	<c:if test="${check == 'true' }">
		<c:if test="${bl==true}">
	<table id="list2">
		<tr>
		<td colspan="3">
		<h1>팔로잉</h1></td></tr>
			<c:forEach var="vo" items='${list }'>
				<c:set var="i" value="${i+1 }"/>
				<tr id="result${i }">
					<td class="mem"><a href="${cp }/follow/move?youmember_no=${vo.getMember_no()}">
						<img src="${cp }/upload/${vo.getProfile()}"style="width: 50px; height: 50px;border-radius: 50%;"></a></td>
					<td class="mem"><a href="${cp }/follow/move?youmember_no=${vo.getMember_no()}">${vo.getNickname() }</a></td>
					<c:choose>
						<c:when test="${sessionScope.member_no == mymember_no }">
							<td><a href="javascript:callInsert(${vo.getMember_no()},${i });">
								<input type="button" value="팔로우" class="folbtn" name="btn"></a>
								</td>
						</c:when>		
					</c:choose>
					</tr>
			</c:forEach>
		</table>
		</c:if>
		<c:if test="${bl==false}">
			<table id="list2">
		<tr>
		<td colspan="3">
			<h1>팔로워</h1></tr>
			<c:forEach var="vo" items='${list }'>
				<c:set var="i" value="${i+1 }"/>
			<tr>
				<td class="mem"><a href="${cp }/follow/move?youmember_no=${vo.getMember_no()}">
					<img src="${cp }/upload/${vo.getProfile()}"style="width: 50px; height: 50px;border-radius: 50%;"></a>
				</td>
				<td class="mem"><a href="${cp }/follow/move?youmember_no=${vo.getMember_no()}">${vo.getNickname() }</a></td>
				<c:choose>
						<c:when test="${sessionScope.member_no != vo.getMember_no() }">
							<td><a href="javascript:callDelete(${vo.getMember_no()},${i });">삭제</a></td>	
						</c:when>			
				</c:choose>
			</tr>
			</c:forEach>
		</table><br>
		</c:if>
	</c:if>
	
	<c:if test="${check !='true' }">
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
						<c:otherwise>
							<c:if test="${sessionScope.member_no != vo.getMember_no() }">
								<c:set var="j" value="${j+1 }"/>
								<td><a href="javascript:callInsert(${vo.getMember_no()},${j });">
								<input type="button" value="팔로우" class="folbtn" name="btn"></a>
								</td>
							</c:if>
						</c:otherwise>			
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
				<c:choose>
						<c:when test="${sessionScope.member_no != vo.getMember_no() }">
							<td><a href="javascript:callInsert(${vo.getMember_no()},${j });">
							<input type="button" value="팔로우" class="folbtn" name="btn"></a>
							</td>			
						</c:when>			
				</c:choose>
			</tr>
			</c:forEach>
		</table><br>
		</c:if>
	</c:if>	
	
	<a href="javascript:history.back()" id="back">뒤로가기</a>
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
	
	function callInsert(member_no,j){
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4 && xhr.status==200){
				var xml = xhr.responseXML;
				var json = JSON.parse(xml);
					var btn=document.getElementsByName("btn")[j-1];
					btn.value="팔로우됨";
					btn.disabled=true;
					btn.style.backgroundColor="black";
					btn.style.color="white";
			}
		};
		xhr.open('get','${cp }/follow/insert?youmember_no='+member_no,true);
		xhr.send();
	}
</script>
</html>