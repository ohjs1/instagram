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
	
	#img{
		width: 100%;
		height: auto%;
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
.meta img{
	align-self: center;
    max-width: 40px;
    border-radius: 50%;
    margin-right: 12px;
}
.meta {
  display: flex;    
  align-items: center;
  padding-bottom: 20px;
  border-bottom: 1px solid $offWhite;
  
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
	
.handle {
    text-decoration: lowercase;
    font-weight: 600;
  }
/* ------------------- 슬라이더 CSS ---------------------*/
	img.showImage {
	  display: inline-block;
	}
	
	img.hideImage {
	  display: none;
	}
	
	button:focus {outline:0;}
	
	button.next, button.prev {
	  position: fixed;
	  height: 100%;
	  top: 0;
	  
	  background-color: #212121;
	  color: #fff;
	  border: none;
	  font-size: 15px;
	  font-weight: 700;
	  padding: 20px;
	}
	.image-wrap button:hover {
	  opacity: 0.6;
	  cursor: pointer;
	}
	
	.next {
	  right: 0;
	  
	  opacity: 0.4;
	}
	
	.prev {
	  left: 0;
	  
	  opacity: 0.4;
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
<c:forEach var="imageVo1" items="${mainImgList }" varStatus="i">
		<c:set var="imageVo" value="${imageVo1 }" scope="request"/>
		<c:if test="${i.index!=0 and i.index%3==0 }">
			<tr>
		</c:if> 
		<td>
			<img src="../upload/${imageVo.imagepath }" class="board" onclick="getBoardList(${imageVo.board_no})">
		</td>
	</c:forEach>
	</tr>
</table>
<div id="modal1"></div>
</body>
<script type="text/javascript">
	const modal=document.querySelector(".modal");
	const overlay=modal.querySelector(".modal_overlay");
	const closeBtn=modal.querySelector("button");
	
	//게시글 modal
	var boardList=null;
	function getBoardList(board_no){
		//const board_no=${imageVo.board_no};
		boardList=new XMLHttpRequest();
		boardList.onreadystatechange=getBoardListOk;
		boardList.open('post','../board/list',true);
		boardList.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
		boardList.send('board_no='+board_no);
	}
	
	function getBoardListOk(){
		if(boardList.readyState==4 && boardList.status==200){
			var data=boardList.responseText;
			var json=JSON.parse(data);
			var modal1=document.getElementById("modal1");
			for(var i=0;i<json.length;i++){
				var id=json[i].id;
				var pwd=json[i].pwd;
				var name=json[i].name;
				var nickname=json[i].nickname;
				var profile=json[i].profile;
				var board_no=json[i].board_no;
				var member_no=json[i].member_no;
				var content=json[i].content;
				var ref=json[i].ref;
				var lev=json[i].lev;
				var step=json[i].step;
				var regdate=json[i].regdate;
				var div=document.createElement("div");
				div.innerHTML="<div class='modal_overlay' onclick='closeBoard();'></div>"+//모달창의 배경색
									"<div class='modal_content'>"+
									"<div class='image-wrap' id='image-wrap'>"+ //이미지들이 들어갈 div
										"<button class='prev' onclick='prevImg ()'>&#60;</button>"+
									    "<button class='next' onclick='nextImg ()'>&#62;</button>"+
									"</div>"+
									"<div class='meta-info'>"+//modal 우측의 사용자,글내용,댓글,댓글달기를 감싸는 div
										"<div class='meta-wrap'>"+
											"<div class='meta'>"+//프로필사진,닉네임이 들어갈 div
												"<div>"+
													"<img src='../upload/"+profile+"'>"+//프로필사진
												"</div>"+
												"<div>"+
													"<p class='handle'>"+nickname+"</p>"+//닉네임
												"</div>"+
											"</div>"+
										"</div>"+
										"<div class='meta-comments'>"+
											"<p>"+
												"<span class='handle'>"+nickname+"</span>"+//닉네임
												"<span>"+content+"</span>"+//글내용
											"</p>"+
										"</div>"+
										"<div class='comments-wrap'></div>"+//작성된 댓글
										"<div class='comments-wrap'>"+//댓글작성하기
											"<input type='text' placeholder='댓글 달기...' id='comment'>"+
											"<input type='button' id='sendComment' value='전송'>"+
										"</div>"+
									"</div>"+
								"</div>"+
								"<div class='modal_close' onclick='closeBoard();'>"+
									"<button>❌</button>"+
								"</div>";
				div.className="modal";
				div.setAttribute("id","modal");
				modal1.appendChild(div);
				getImgList(board_no);
			}
			//전송버튼 클릭시 댓글생성 ajax함수 호출
			var sendComment=document.getElementById("sendComment");
			sendComment.addEventListener('click',function(){
				insertComment(json[0]);
			});
		}
	}
	
	//댓글생성 ajax
	/* var commInsert=null;
	function insertComment(json){
		var comment=document.getElementById("comment").value;
		commInsert=new XMLHttpRequest();
		commInsert.onreadystatechange=commInsertOk;
		commInsert.open('post','../board/insert',true);
		commInsert.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
		commInsert.send('board_no='+json.board_no+'&member_no='+json.member_no+'&comment='comment+'&ref='+json.ref+'&lev='+json.lev+'&step='+json.step+'&regdate='+json.regdate);
	}
	function commInsertOk(){
		if(commInsert.readyState==4 && commInsert.status==200){
			
		}
	} */
	
	
	//게시물클릭시 이미지 가져오기
	var imgList=null;
	function getImgList(board_no){
		imgList=new XMLHttpRequest();
		imgList.onreadystatechange=getImgListOk;
		imgList.open('get','../image/list?board_no='+board_no,true);
		imgList.send();
	}
	function getImgListOk(){
		if(imgList.readyState==4 && imgList.status==200){
			var data=imgList.responseText;
			var json=JSON.parse(data);
			var image_wrap=document.getElementById("image-wrap");
			for(var i=0;i<json.length;i++){
				var image_no=json[i].image_no;
				var board_no=json[i].board_no;
				var imagePath=json[i].imagePath;
				var img=document.createElement("img");
				img.setAttribute("id","img");
				img.src="../upload/"+imagePath;
				image_wrap.appendChild(img);
			}
			showImage(0);
		}
	}
	
	function showBoard(){
		var modal=document.getElementById("modal");
		modal.classList.remove("hidden");
	}
	//모달 닫기버튼
	function closeBoard(){
		var modal=document.getElementById("modal");
		var modal1=document.getElementById("modal1");
		modal1.innerHTML="";
		//$("#modal").html("");
		counter=0;
	}
	//이미지 슬라이더
	var counter=0;
	var slider=null;
	var images=null;
	function showImage(index){
		counter=index;
		slider=document.getElementById("image-wrap");
		images=slider.getElementsByTagName("img");
		for(var i=0; i<images.length; i++){
			images[i].className="hideImage";
		}
		images[index].className ="showImage";
	}
	
	function nextImg(){
		if (counter<images.length-1){
			counter+= 1;
		}else{
			counter=0;
		}
		showImage(counter);
	}
	 
	function prevImg(){
		if(counter > 0){
			counter-=1;
		}else{
			counter=images.length-1;
		}
		showImage(counter);
	}
</script>
</html>






