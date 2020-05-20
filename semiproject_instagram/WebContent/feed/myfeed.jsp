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
	
	.modal2 {
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
		height: 638px;
	}
	
	.modal_content{
		display: grid;
		grid-template-columns: 600px 335px;
		background: white;
		z-index:10000;
	}
	.modal_content .image-wrap {
			max-width: 600px;
			max-height: 600px;
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

.meta p {
    margin: 0;
    color: $black;
}
.handle {
    text-decoration: lowercase;
    font-weight: 600;
    color:black;
}

.meta .location {
    font-size: 12px;
    margin-top: 2px;
}
.meta.comments {
	margin-top:20px;
    border-bottom: none;
}

.meta {
  display: flex;    
  align-items: center;
  padding-bottom: 20px;
  border-bottom: 1px solid rgba(var(--ce3,239,239,239),1);
}

.comments{
	display: flex;    
	align-items: center;
}
.comments-wrap img{
	align-self: center;
    max-width: 40px;
    border-radius: 50%;
    margin-right: 12px;
}
.comments-wrap .comments{
	padding-top:5px;
	padding-bottom: 20px;
    border-bottom: none;
}

.meta-info {
  display: grid;
  grid-template-rows: 80px 400px 1fr 55px;
  border-top: 1px solid $offWhite;
}
#commtext{
	background: 0 0;
    border: 0;
    width: 80%;
}
/*댓글목록 css*/
.scroll1{
	margin: 0 20px 20px;
	margin-top: -10px;
	overflow:scroll;
	-ms-overflow-style: none; /*스크롤바 기능은 하면서 보이지는 않게 하기*/
}
/*스크롤바 기능은 하면서 보이지는 않게 하기*/
::-webkit-scrollbar {
	display:none;
}
.comments-wrap {

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
    border-top: 1px solid rgba(var(--ce3,239,239,239),1);
    padding-top: 20px;
  }
}

/* 게시일 css */
.regdate{
	font-size: 10px;
    letter-spacing: .2px;
    color: gray;
}
 	.image-wrap{
		display:inline-block;
		float:left;
		width:100%;
		height: 100%;
	}
		
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
/* .items */

  .items{
  	border-top: 1px solid rgba(var(--ce3,239,239,239),1);
  	border-bottom: 1px solid rgba(var(--ce3,239,239,239),1);
  }
  .items .btn1{
  	border:0;
  	background: 0 0;
  	padding: 8px;
  }
  .commwrite{
  	margin-top: 8px;
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
	${youmember_no } ${sessionScope.member_no }
	<c:choose>
		<c:when test="${sessionScope.member_no != youmember_no }">
			<a href="${cp }/follow/insert?youmember_no=${member_no}">팔로우</a>
			<a href="${cp }/follow/select?mymember_no=${member_no}">팔로워</a>
			<a href="${cp }/follow/select?youmember_no=${member_no}">팔로잉</a>
		</c:when>
		<c:otherwise>
			<a href="${cp }/follow/select?mymember_no=${member_no}">팔로워</a>
			<a href="${cp }/follow/select?youmember_no=${member_no}">팔로잉</a>
			<a href="${cp }/member/memberInfo">프로필편집</a>
		</c:otherwise>
	</c:choose>
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
			var json=JSON.parse(data); //게시글에 대한 member정보,board정보를 가져옴
			var modal1=document.getElementById("modal1");
			
			var id=json.id;
			var pwd=json.pwd;
			var name=json.name;
			var nickname=json.nickname;
			var profile=json.profile;
			var board_no=json.board_no;
			var member_no=json.member_no;
			var content=json.content;
			var ref=json.ref;
			var lev=json.lev;
			var step=json.step;
			var regdate=json.regdate;
			
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
												"<a href='${cp}/follow/move?youmember_no="+member_no+"'>"+
													"<img src='../upload/"+profile+"'>"+//프로필사진
												"</a>"+
											"</div>"+
											"<div>"+
												"<a href='${cp}/follow/move?youmember_no="+member_no+"'>"+
													"<p class='handle'>"+nickname+"</p>"+//닉네임
												"</a>"+
												
											"</div>"+
										"</div>"+
									"</div>"+
									"<div class='scroll1'>"+
										"<div class='meta comments'>"+
											"<div>"+
												"<a href='${cp}/follow/move?youmember_no="+member_no+"'>"+
													"<img src='../upload/"+profile+"'>"+//프로필사진
												"</a>"+
											"</div>"+
											"<div>"+
												"<p>"+
													"<a href='${cp}/follow/move?youmember_no="+member_no+"'>"+
														"<span class='handle'>"+nickname+"</span> "+//닉네임
													"</a>"+
													"<span>"+content+"</span>"+//글내용
												"</p>"+
											"</div>"+
										"</div>"+
										"<div class='comments-wrap'></div>"+//작성된 댓글
									"</div>"+
									//좋아요,댓글,게시글저장 버튼 및 좋아요 수, 게시일이 저장될 공간
									"<div class='items'>"+
										"<span class='like'>"+
											//좋아요버튼
											"<button class='btn1' id='likeBtn'>"+
												"<div id='likeImg'></div>"+ //좋아요버튼 이미지가 들어갈 공간
											"</button>"+
										"</span>"+
										//댓글버튼
										"<span class='comm'>"+
											"<button class='btn1' onclick='commFocus()'>"+
												"<img src='${cp}/upload/comm.PNG'>"+
											"</button>"+
										"</span>"+
										"<div class='likeList'>"+
											"<p>가장 먼저 <label for='likeBtn' style='display:inline-block'>좋아요</label>를 눌러보세요.</p>"+
										"</div>"+
										"<div class='time'>"+
											"<p><time class='regdate'datetime='2016-05-25'>2016년 5월 25일</time></p>"+
										"</div>"+
									"</div>"+
									"<div class='commwrite'>"+//댓글작성하기
										"<input type='text' placeholder='댓글 달기...' id='commtext'>"+
										"<input type='button' id='sendComment' value='전송'>"+
									"</div>"+
								"</div>"+
							"</div>"+
							"<div class='modal_close' onclick='closeBoard();'>"+
								"<button>❌</button>"+
							"</div>";
			div.className="modal2";
			div.setAttribute("id","modal2");
			modal1.appendChild(div);
			getImgList(board_no); //이미지리스트
			commentList(board_no); //댓글리스트 가져오기
			getLikeList(board_no); //좋아요리스트 가져오기
			
			//좋아요버튼 클릭시 좋아요테이블에 추가or삭제 ajax함수 호출
			var likeBtn=document.getElementById("likeBtn");
			likeBtn.addEventListener('click',function(){
				likeAction(board_no);
			});
			
			//댓글 엔터버튼 누를 시 댓글생성
			var commtext=document.getElementById("commtext");
			commtext.addEventListener('keypress',function(e){
				if(e.keyCode==13){
					var commtext=document.getElementById("commtext").value;
					if(commtext!=null && commtext!=""){ //댓글 input에 내용이 있으면 댓글생성 ajax수행 
						insertComment(json);
					}
				}
			});
			
			//전송버튼 클릭시 댓글생성 ajax함수 호출
			var sendComment=document.getElementById("sendComment");
			sendComment.addEventListener('click',function(){
				var commtext=document.getElementById("commtext").value;
				if(commtext!=null && commtext!=""){ //댓글 input에 내용이 있으면 댓글생성 ajax수행 
					insertComment(json);
				}
			});
		}
	}
	//좋아요 추가or삭제 ajax
	var likeaction=null;
	function likeAction(num){
		likeaction=new XMLHttpRequest();
		var board_no=num;
		likeaction.onreadystatechange=function(){
			if(likeaction.readyState==4 && likeaction.status==200){
				var likeImg=document.getElementById("likeImg");
				var data=likeaction.responseText;
				var json=JSON.parse(data);
				if(json.result==0){
					likeImg.innerHTML="<img src='${cp}/upload/likenull.PNG'>";
				}else if(json.result==1){
					likeImg.innerHTML="<img src='${cp}/upload/like.PNG'>";
				}
			}
		}
		likeaction.open('get','${cp}/good/insertdelete?board_no='+board_no+'&member_no=${sessionScope.member_no}',true);
		likeaction.send();
	}
	
	//좋아요 리스트
	var likeList=null;
	function getLikeList(num){
		var board_no=num;
		likeList=new XMLHttpRequest();
		likeList.onreadystatechange=function(){
			if(likeList.readyState==4 && likeList.status==200){
				var data=likeList.responseText;
				var json=JSON.parse(data);
				//////////////////////////////////////////////////////작업할공간
			}
		}
		likeList.open('get','${cp}/good/list?board_no='+board_no,true);
		likeList.send();
	}
	
	//댓글생성 ajax
	var commInsert=null;
	function insertComment(json){
		var board_no=json.board_no;
		var commtext=document.getElementById("commtext").value;
		var ref=json.ref;
		var lev=json.lev;
		var step=json.step;
		commInsert=new XMLHttpRequest();
		commInsert.onreadystatechange=function(){ //board_no받아서 commentList(board_no)으로 보내주기
			if(commInsert.readyState==4 && commInsert.status==200){
				var data=commInsert.responseText;
				var json=JSON.parse(data);
				if(json.chk>0){
					commentList(board_no);
				}else{
					alert("댓글작성 실패!");
				}
			}
		}
		commInsert.open('post','../board/commentInsert',true);
		commInsert.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
		commInsert.send('board_no='+board_no+'&member_no=${member_no}&comment='+commtext+'&ref='+ref+'&lev='+lev+'&step='+step);
	}
	
	//댓글 리스트 가져오기
	var commList=null;
	function commentList(board_no){
		commList=new XMLHttpRequest();
		commList.onreadystatechange=function(){
			if(commList.readyState==4 && commList.status==200){
				var comments_wrap=document.getElementsByClassName("comments-wrap")[0];
				comments_wrap.innerHTML="";
				var data=commList.responseText;
				var json=JSON.parse(data);
				for(let i=0; i<json.length; i++){
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
					comments_wrap.innerHTML+="<div class='comments'>"+
												"<div>"+
													"<a href='${cp}/follow/move?youmember_no="+member_no+"'>"+
														"<img src='../upload/"+profile+"'>"+//프로필사진
													"</a>"+
												"</div>"+
												"<div>"+
													"<p>"+
														"<a href='${cp}/follow/move?youmember_no="+member_no+"'>"+
															"<span class='handle'>"+nickname+"</span> "+//닉네임
														"</a>"+
														"<span>"+content+"</span>"+//글내용
													"</p>"+
												"</div>"+
											"</div>";
				}
			}
		}
		commList.open('get','../board/commentList?board_no='+board_no);
		commList.send();
	}
	
	//댓글아이콘 클릭시 댓글 입력창으로 포커스주기
	function commFocus(){
		var commtext=document.getElementById("commtext");
		commtext.focus();
	}
	
	
	//댓글 입력창에서 엔터입력시 전송버튼눌리게하기
/* 	var commtext=document.getElementById("commtext");
	commtext.addEventListener('keyup',function(e){
		alert("엔터");
		if(e.keyCode==13){
			var sendComment=document.getElementById("sendComment");
			alert("엔터");
		}
	});
	 */
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






