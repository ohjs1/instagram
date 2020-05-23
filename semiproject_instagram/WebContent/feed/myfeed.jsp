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
<link rel="stylesheet" type="text/css" href="${cp}/css/myfeed.css"> <!-- myfeed CSS -->
</head>
<body>
	<h1>${id }</h1>
	${youmember_no } ${sessionScope.member_no }
	<c:choose>
		<c:when test="${sessionScope.member_no != youmember_no }">
			<a href="${cp }/follow/insert?youmember_no=${member_no}">팔로우</a>
			<a href="${cp }/follow/select?youmember_no=${member_no}">팔로워</a>
			<a href="${cp }/follow/select?mymember_no=${member_no}">팔로잉</a>
		</c:when>
		<c:otherwise>
			<a href="${cp }/follow/select?youmember_no=${member_no}">팔로워</a>
			<a href="${cp }/follow/select?mymember_no=${member_no}">팔로잉</a>
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

<div id="modal1"></div> <!-- 게시글 모달 -->
<div id="boardOptionModal"></div> <!-- 삭제,수정,취소 모달 -->
<div id="likeModal"></div> <!-- 좋아요 모달 -->
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
	//게시글 띄우기
	function getBoardListOk(){
		if(boardList.readyState==4 && boardList.status==200){
			var data=boardList.responseText;
			var json=JSON.parse(data); //게시글에 대한 member정보,board정보를 가져옴
			var modal1=document.getElementById("modal1");
			var boardOptionModal=document.getElementById("boardOptionModal");
			modal1.innerHTML=""; //댓글삭제시 게시글 모달창을 다시 띄워야하므로 초기화
			boardOptionModal.innerHTML=""; //댓글삭제시 삭제,수정,취소 모달창을 다시 띄워야하므로 초기화 
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
			var regdate=new Date(json.regdate);
			var date= new Date();
			var dd=Math.floor((date-regdate)/1000);
			var realDate="";
			if(dd<60){
				realDate="<p>"+dd+"초 전</p>";
			}else if(dd<3600){
				realDate="<p>"+Math.floor((dd/60))+"분 전</p>";
			}else if(dd<86400){
				realDate="<p>"+Math.floor((dd/60/60))+"시간 전</p>";
			}else if(dd<604800){
				realDate="<p>"+Math.floor((dd/60/60/24))+"일 전</p>";
			}else if(dd<2592000){
				realDate="<p>"+Math.floor((dd/60/60/24/7))+"주 전</p>";
			}else if(dd<31536000){
				realDate="<p>"+Math.floor((dd/60/60/24/7/4.3))+"달 전</p>";
			}else{
				realDate="<p>"+Math.floor((dd/60/60/24/7/4.2/12))+"년 전</p>";
			}
			
			var div=document.createElement("div");
			div.innerHTML="<div class='modal_overlay' onclick='closeBoard();'></div>"+//모달창의 배경색
								"<div class='modal_content'>"+
								"<div class='image-wrap' id='image-wrap'>"+ //이미지들이 들어갈 div
									"<button class='prev' onclick='prevImg()'>&#60;</button>"+
								    "<button class='next' onclick='nextImg()'>&#62;</button>"+
								"</div>"+
								"<div class='meta-info'>"+//modal 우측의 사용자,글내용,댓글,댓글달기를 감싸는 div
									"<div class='meta-wrap'>"+
										"<div class='meta' id='meta'>"+//프로필사진,닉네임이 들어갈 div
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
										//게시글 글쓴이 프사,닉네임,글내용이 들어갈 공간
										"<div class='meta comments' id='meta_comments'>"+
										"</div>"+
										"<div class='comments-wrap'></div>"+//작성된 댓글이 들어갈 div
									"</div>"+
									//좋아요,댓글,게시글저장 버튼 및 좋아요 수, 게시일이 저장될 공간
									"<div class='items'>"+
										"<span class='like'>"+
											//좋아요버튼
											"<button class='btn1' id='likeBtn'>"+
												"<div id='likeImg'></div>"+ //좋아요버튼 이미지가 들어갈 div
											"</button>"+
										"</span>"+
										//댓글버튼
										"<span class='comm'>"+
											"<button class='btn1' onclick='commFocus()'>"+
												"<img src='${cp}/upload/comm.PNG'>"+
											"</button>"+
										"</span>"+
										"<div class='likeLink'>"+
										"</div>"+
										"<div class='time' id='time'>"+
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
			var time=document.getElementById("time");
			time.innerHTML=realDate;
			getImgList(board_no); //이미지리스트
			commentList(board_no); //댓글리스트 가져오기
			getLikeCnt(board_no); //좋아요리스트 가져오기
			
			
			//글내용이 있을경우 meta comment에 내용추가
			var meta_comments=document.getElementById("meta_comments");
			if(content!=null && content!=""){
				meta_comments.innerHTML="<div>"+
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
												"<div class='time'><p>"+realDate+"</p></div>"+//작성시간
											"</p>"+
										"</div>";
			}else{
				meta_comments.innerHTML="";
				meta_comments.style.margin=0;
			}
			
			//자기게시글일경우 삭제,수정,업데이트 버튼 생성
			var meta=document.getElementById("meta");
			if(${sessionScope.member_no}==member_no){
				meta.innerHTML+= "<div class='boardoption'>"+
									 "<button class='boardbtn'>···</button>"+
								 "</div>";
			}
////////////////////////////////// 이벤트구역 /////////////////////////////////////////			
			
			//회원정보 옆의 ...버튼 클릭시 게시글,댓글 삭제,수정,취소버튼 모달 함수호출
			var boardbtn=document.getElementsByClassName("boardbtn");
			for(let i=0;i<boardbtn.length;i++){
				boardbtn[i].addEventListener('click',function(){
					showBoardOptionModal(board_no,member_no,ref);
				});
			}
			//좋아요버튼 클릭시 좋아요테이블에 추가or삭제 ajax함수 호출
			var likeBtn=document.getElementById("likeBtn");
			likeBtn.addEventListener('click',function(){
				likeAction(board_no);
			});
			
			//댓글 엔터버튼 누를 시 댓글생성
			var commtext=document.getElementById("commtext");
			commtext.addEventListener('keyup',function(e){
				if(e.keyCode==13){
					var commtext=document.getElementById("commtext");
					if(commtext.value!=null && commtext.value!=""){ //댓글 input에 내용이 있으면 댓글생성 ajax수행 
						insertComment(json);
						commtext.value="";
					}
				}else{
					//댓글 입력시 전송버튼활성화
					var sendComment=document.getElementById("sendComment");
					var commtext=document.getElementById("commtext");
					if(commtext.value!=null && commtext.value!=""){
						sendComment.style.opacity=1;
					}else{
						sendComment.style.opacity=.3;
					}
				}
			});
			
			//전송버튼 클릭시 댓글생성 ajax함수 호출
			var sendComment=document.getElementById("sendComment");
			sendComment.addEventListener('click',function(){
				var commtext=document.getElementById("commtext");
				if(commtext.value!=null && commtext.value!=""){ //댓글 input에 내용이 있으면 댓글생성 ajax수행 
					insertComment(json);
					commtext.value="";
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
				var data=likeaction.responseText;
				var json=JSON.parse(data);
				getLikeCnt(json.board_no);
			}
		}
		likeaction.open('get','${cp}/good/insertdelete?board_no='+board_no+'&member_no=${sessionScope.member_no}',true);
		likeaction.send();
	}
	
	//좋아요 갯수
	var likeList=null;
	function getLikeCnt(num){
		var board_no=num;
		likeList=new XMLHttpRequest();
		likeList.onreadystatechange=function(){
			if(likeList.readyState==4 && likeList.status==200){
				var likeLink=document.getElementsByClassName("likeLink")[0];
				var data=likeList.responseText;
				var json=JSON.parse(data);
				var likeChk=false;
				if(json!=null && json!=""){
					for(let i=0; i<json.length; i++){
						var member_no=json[i].member_no;
						if(member_no==${sessionScope.member_no}){
							likeChk=true;
						}
					}
					likeLink.innerHTML="<button class='likeListBtn' id='likeListBtn'>좋아요 "+json.length+"개</button>";
				}else{
					likeLink.innerHTML="<p>가장 먼저 <label for='likeBtn' style='display:inline-block;font-weight:600'>좋아요</label>를 눌러보세요.</p>";
				}
				var likeImg=document.getElementById("likeImg");
				if(likeChk==true){
					likeImg.innerHTML="<img src='${cp}/upload/like.PNG'>";
				}else{
					likeImg.innerHTML="<img src='${cp}/upload/likenull.PNG'>";
				}
				//생성된 좋아요 리스트클릭 이벤트 생성 및 좋아요 모달함수 호출
				var likeListBtn=document.getElementById("likeListBtn");
				likeListBtn.addEventListener('click', function() {
					showLikeModal(json); //좋아요 모달함수 호출
				});
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
					var regdate=new Date(json[i].regdate);
					var date= new Date();
					var dd=Math.floor((date-regdate)/1000);
					var realDate="";
					if(dd<60){
						realDate="<p>"+dd+"초 전</p>";
					}else if(dd<3600){
						realDate="<p>"+Math.floor((dd/60))+"분 전</p>";
					}else if(dd<86400){
						realDate="<p>"+Math.floor((dd/60/60))+"시간 전</p>";
					}else if(dd<604800){
						realDate="<p>"+Math.floor((dd/60/60/24))+"일 전</p>";
					}else if(dd<2592000){
						realDate="<p>"+Math.floor((dd/60/60/24/7))+"주 전</p>";
					}else if(dd<31536000){
						realDate="<p>"+Math.floor((dd/60/60/24/7/4.3))+"달 전</p>";
					}else{
						realDate="<p>"+Math.floor((dd/60/60/24/7/4.2/12))+"년 전</p>";
					}
					var comments=document.getElementById("comments");
					if(${sessionScope.member_no}==member_no){
					comments_wrap.innerHTML+="<div class='comments' id='comments'>"+
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
														"<div class='time'><p>"+realDate+"</p></div>"+//작성시간
													"</p>"+
												"</div>"+
												"<div class='boardoption'>"+
													"<button class='boardbtn' onclick='showBoardOptionModal("+board_no+","+member_no+","+ref+");'>···</button>"+
												"</div>";
											"</div>";
					}else{
					comments_wrap.innerHTML+="<div class='comments' id='comments'>"+
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
														 "<div class='time'><p>"+realDate+"</p></div>"+//작성시간
													 "</p>"+
												 "</div>"+
											 "</div>";
					}
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
		var modal1=document.getElementById("modal1");
		modal1.innerHTML="";
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
		var prev=document.getElementsByClassName("prev")[0];
		var next=document.getElementsByClassName("next")[0];
		for(var i=0; i<images.length; i++){
			images[i].className="hideImage";
		}
		images[index].className ="showImage";
		if(counter==0 && counter==images.length-1){
			prev.style.display="none";
			next.style.display="none";
		}else if(counter==images.length-1){
			prev.style.display="inline";
			next.style.display="none";
		}else if(counter==0){
			prev.style.display="none";
			next.style.display="inline";
		}else{
			prev.style.display="inline";
			next.style.display="inline";
		}
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
	
	/////////////////////////////* 삭제,수정,취소 모달창 *////////////////////////////////////////
	// 삭제,수정,취소 모달창 오픈
	function showBoardOptionModal(board_no,member_no,ref){
		var boardOptionModal=document.getElementById("boardOptionModal");
		var div=document.createElement("div");
		div.innerHTML="<div class='option_overlay' onclick='closeOptionModal();'></div>"+//모달창의 배경색
					  "<div class='option_content'>"+
						  "<div class='delete_option'>"+
							  "<button class='option_btn' id='delete_btn'>삭제하기</button>"+
						  "</div>"+
						  "<div class='update_option'>"+
						 	  "<button class='option_btn' id='update_btn'>수정하기</button>"+
						  "</div>"+
						  "<div class='cancle_option'>"+
					 	 	  "<button class='option_btn' onclick='closeOptionModal();'>취소</button>"+
					  	  "</div>"+
					  "</div>";
		div.className="boardOptionModal1";
		div.setAttribute("id","boardOptionModal1");
		boardOptionModal.appendChild(div);
		var delete_btn=document.getElementById("delete_btn");
		delete_btn.addEventListener('click', function() {
			deleteBoard(board_no,member_no,ref);
		});
	}
	//게시글 삭제버튼 ajax
	var boardDelete=null;
	function deleteBoard(board_no,member_no,ref){
		boardDelete=new XMLHttpRequest();
		boardDelete.onreadystatechange=function(){
			if(boardDelete.readyState==4 && boardDelete.status==200){
				var data=boardDelete.responseText;
				var json=JSON.parse(data);
				var result=json.result;
				if(result>0 && board_no==ref){
					location.href="${cp}/follow/move?youmember_no="+member_no;
				}else if(result>0 && board_no!=ref){
					getBoardList(ref);
				}else{
					alert("삭제실패!");
				}
			}
		}
		boardDelete.open('get','${cp}/board/delete?board_no='+board_no,true);
		boardDelete.send();
	}
	
	//삭제,수정,취소 모달 닫기버튼
	function closeOptionModal(){
		var boardOptionModal=document.getElementById("boardOptionModal");
		boardOptionModal.innerHTML="";
	}
	
	/////////////////////////////* 좋아요 리스트 모달창 *////////////////////////////////////////
	
	//좋아요 모달창 오픈
	function showLikeModal(json){
		var likeModal=document.getElementById("likeModal");
		var div=document.createElement("div");
		div.innerHTML="<div class='like_overlay' onclick='closeLikeModal();'></div>"+//모달창의 배경색
						"<div class='like_content'>"+
							"<div class='like_header'>좋아요"+
								"<span class='likemodal_close' onclick='closeLikeModal();'>"+
									"<button>❌</button>"+
								"</span><br>"+
							"</div>"+
							"<div class='like_list'></div>"+
						"</div>";
		div.className="likeModal1";
		div.setAttribute("id","likeModal1");
		likeModal.appendChild(div);
		getLikeList(json);
	}
	
	//좋아요한 회원리스트 추가
	function getLikeList(json){
		var like_list=document.getElementsByClassName("like_list")[0];
		for(let i=0; i<json.length; i++){
			var id=json[i].id;
			var pwd=json[i].pwd;
			var name=json[i].name;
			var nickname=json[i].nickname;
			var profile=json[i].profile;
			var good_no=json[i].good_no;
			var member_no=json[i].member_no;
			var board_no=json[i].board_no;
			like_list.innerHTML+="<div class='likeusers'>"+
									"<div>"+
										"<a href='${cp}/follow/move?youmember_no="+member_no+"'>"+
											"<img src='../upload/"+profile+"'>"+//프로필사진
										"</a>"+
									"</div>"+
									"<div style='text-align:left;'>"+
										"<p>"+
											"<a href='${cp}/follow/move?youmember_no="+member_no+"'>"+
												"<span class='handle'>"+nickname+"</span> "+//닉네임
											"</a>"+
										"</p>"+
										"<p>"+
											"<a href='${cp}/follow/move?youmember_no="+member_no+"'>"+
												"<span class='name'>"+name+"</span> "+//이름
											"</a>"+
										"</p>"+
									"</div>"+
								"</div>";
		}
	}
	//좋아요 모달 닫기버튼
	function closeLikeModal(){
		var likeModal=document.getElementById("likeModal");
		likeModal.innerHTML="";
	}
	
</script>
</html>






