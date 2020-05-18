<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	table{
		display: inline-block;
	}
	th{
		padding: 10px;
	}
	#imgTable{
		text-align: center;
	}
	#tag{
		width: 
	}
</style>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
<div id="tag">
</div>
<div id="imgTable">
	# ${keyword }<br>
	<c:set var="i" value="0" />
	<c:set var="j" value="3" />
	<table>
		<c:forEach var="vo" items="${vo}">
			<c:if test="${i%j == 0 }">
				<tr>
					</c:if>
						<th>
							<img src="${cp }/upload/${vo.imagepath}" style='width: 293px;height: 293px'>
						</th>
					<c:if test="${i%j == j-1 }">
				</tr>
			</c:if>
			<c:set var="i" value="${i+1 }" />
		</c:forEach>
	</table>
</div>
</body>
</html>