<%@page import="com.dao.ImageDao"%>
<%@page import="com.vo.Board_MemberVo"%>
<%@page import="com.vo.BoardVo"%>
<%@page import="com.vo.ImageVo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.dao.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 피드</title>
<style type="text/css">
	.board{width: 100%; height: 100%;}
	td{width:200px;height:200px;}
	
	.modal {
		position: fixed;
		top:0;
		left:0;
		width: 100%;
		height: 100%;
		display: flex;
		justify-content: center;
		align-items: center;
	}
	
	.modal_overlay {
		background-color: rgba(0,0,0,0.6);
		width: 100%;
		height: 100%;
		position: absolute;
	}
	
	.img{
		width: 100%;
		height: auto%;
		display: block;
	}
	
	.modal_content{
		display: grid;
		grid-template-columns: 600px 335px;
		background: white;
		z-index:10000;
		.image-wrap {
			max-width: 600px;
		}
	}
	
	.mata-wrap{
		padding:20px;
	}
	
.meta-wrap {
  padding: 20px;
}

.meta {
  display: flex;    
  align-items: center;
  padding-bottom: 20px;
  border-bottom: 1px solid $offWhite;
  img {
    align-self: center;
    max-width: 40px;
    border-radius: 50%;
    margin-right: 12px;
  }
  p {
    margin: 0;
    color: $black;
  }
  .handle {
    text-decoration: lowercase;
    font-weight: 600;
  }
  .location {
    font-size: 12px;
    margin-top: 2px;
  }
  &.comments {
    padding: 20px;
    border-bottom: none;
  }
}

.meta-info {
  display: grid;
  grid-template-rows: 80px 80px 1fr 55px;
  border-top: 1px solid $offWhite;
}

.comments-wrap {
  margin: 0 20px 20px;
  height: 329px;
  margin-top: -10px;
  input {
    border: none;
    width: 100%;
    outline: 0;
  }
  .handle {
    text-decoration: lowercase;
    font-weight: 600;
  }  
  &:last-child {
    border-top: 1px solid $offWhite;
    padding-top: 20px;
  }
}
	
	
/* 	.image-wrap{
		display:inline-block;
		float:left;
		width:50%;
		height: auto;
	} */
		
	.modal_close{
		position:fixed;
		right:0;
		top:0;
		z-index:10000;
	}
 	.hidden{
		display: none;
	}
	
</style>
</head>
<body>
	<h1>${id }</h1>
	<a href="${cp }/follow/select?mymember_no=${member_no}">팔로워</a>
	<a href="${cp }/follow/select?youmember_no=${member_no}">팔로잉</a>
<h1>게시물</h1>
<table border="1">
	<tr>
<c:forEach var="board_memberVo" items="${board_memberList }" varStatus="i">
	<c:forEach var="boardImgList" items="boardImgList" varStatus="j">
		<c:if test="${i!=0 and i%3==0 }">
	</tr>
	<tr>
		</c:if>
		<td>
			<c:forEach var="imageList" items="${boardImgList }" varStatus="k">
			<img src="../upload/${images[0].getImagepath() }" class="board" onclick="showBoard();">
			</c:forEach>
			<div class="modal hidden" id="modal">
				<div class="modal_overlay" onclick="closeBoard();"></div> <%--모달창의 배경색 --%>
				<div class="modal_content">
					<div class="image-wrap">
						<img src="../upload/${images[0].getImagepath() }" class="img">
					</div>
					<div class="meta-info"><%--modal 우측의 사용자,글내용,댓글,댓글달기를 감싸는 div --%>
						<div class="meta-wrap">
							<div class="meta"><%--프로필사진,닉네임이 들어갈 div --%>
								<div>
									<img src="../upload/${board_memberVo.getProfile() }"> <%--프로필사진 --%>
								</div>
								<div>
									<p class="handle">${board_memberVo.getNickname() }</p><%--닉네임 --%>
								</div>
							</div>
						</div>
						<div class="meta-comments">
							<p>
								<span class="handle">${board_memberVo.getNickname() }</span><%--닉네임 --%>
								<span>${board_memberVo.getContent() }</span><%--글내용 --%>
							</p>
						</div>
						<div class="comments-wrap"></div><%--작성된 댓글 --%>
						<div class="comments-wrap"><%--댓글작성하기 --%>
							<input type="text" placeholder="댓글 달기...">
						</div>
					</div>
				</div>
				<div class="modal_close" onclick="closeBoard();">
					<button>❌</button>
				</div>
			</div>
		</td>
		</c:forEach>
	</c:forEach>
	</tr>
</table>
</body>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script>
	const modal=document.querySelector(".modal");
	const overlay=modal.querySelector(".modal_overlay");
	const closeBtn=modal.querySelector("button");
	function showModal(imageList,board_memberVo){
		const text="<div class='modal_overlay' onclick='closeBoard();'></div>"//모달창의 배경색
						"<div class='modal_content'>"+
						"<div class='image-wrap'>"+
							"<img src='../upload/+"+imageList.get(0).getImagepath(); +"'class='img'>"+
						"</div>"+
						"<div class='meta-info'>"+//modal 우측의 사용자,글내용,댓글,댓글달기를 감싸는 div
							"<div class='meta-wrap'>"+
								"<div class='meta'>"+//프로필사진,닉네임이 들어갈 div
									"<div>"+
										"<img src='../upload/'+"+board_memberVo.getProfile();+">"+//프로필사진
									"</div>"+
									"<div>"+
										"<p class='handle'>"+board_memberVo.getNickname();+"</p>"+//닉네임
									"</div>"+
								"</div>"+
							"</div>"+
							"<div class='meta-comments'>"+
								"<p>"+
									"<span class='handle'>"+board_memberVo.getNickname();+"</span>"+//닉네임
									"<span>"+board_memberVo.getContent();+"</span>"+//글내용
								"</p>"+
							"</div>"+
							"<div class='comments-wrap'></div>"+//작성된 댓글
							"<div class='comments-wrap'>"+//댓글작성하기
								"<input type='text' placeholder='댓글 달기...'>"+
							"</div>"+
						"</div>"+
					"</div>"+
					"<div class='modal_close' onclick='closeBoard();'>"+
						"<button>❌</button>"+
					"</div>";
	$("#modal").html("");
	}
	
	function showBoard(){
		modal.classList.remove("hidden");
	}
	function closeBoard(){
		//modal.classList.add("hidden");
		$("#modal").html("");
	}
</script>
</html>
































