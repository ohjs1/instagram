<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HomeFeed</title>
<link rel="stylesheet" type="text/css" href="${cp}/css/myfeed.css"> <!-- myfeed CSS -->
<style>
#wrap{width:60%;height: 100%;margin:auto; margin-top: 50px;}
#s{ 
	width: 97%;
    height: 12%;
    max-width: 650px;
    border: 1px solid #d3d3d3;
    border-radius: 5px;
    margin: auto;
    padding-top: 20px;
    display: flex;
}
#my {width:100px;height:100px; text-align: center;position:relative;}
#plus {width:50px;height:50px;position:absolute;top:1px;right:1px;}
.sto {width:100px;height:50px;text-align: center;}

/*******************************게시글 공간*****************************/
.board2 {
	position: relative;
	top:0;
	left:0;
	max-width:650px;
	width: 97%%;
	height: 100%;
	max-width
	display: flex;
	justify-content: center;
	align-items: center;
	margin:auto;
	margin-top: 50px;
	
}
/*게시글 헤더부분*/
.board{
	align-items: center;
    padding-bottom: 20px;
}
.board.user{
	position:relative;
	display:block;
}
.board img{
	align-self: center;
    max-width: 40px;
    border-radius: 50%;
    margin-right: 12px;
}
.board .boardoption{
	position: absolute;
	right: 0;
	margin-top: 7px;
	align-items: center;
}
.board .boardbtn{
	background: 0;
    border: 0;
    font-weight: 1000;
    text-align: center;
}
.board_footer{
	display: grid;
    grid-template-rows: 40px 20px 90px 20px 56px;
	border: 1px solid rgba(var(--ce3,239,239,239),1);
}
/* 좋아요,댓글 아이템 CSS */
.item .btn1{
	border: 0;
    background: 0 0;
    padding: 8px;
}
/* 댓글리스트 CSS */
.scroll_wrap {
    margin: 0;
    margin-top: 0;
    overflow: scroll;
    -ms-overflow-style: none;
}

.board_content {
    display: grid;
    width:100%;
    grid-template-columns: 100%;
    background: white;
    z-index: 10;
    border:1px solid rgba(var(--b6a,219,219,219),1);
}

.board_content .image-wrap {
    position: relative;
    width:100%;
    height:100%;
    max-width: 650px;
    max-height: 650px;
    border: 1px solid rgba(var(--ce3,239,239,239),1);
}
.board-wrap {
    padding: 20px;
    border: 1px solid rgba(var(--ce3,239,239,239),1);
}
.board.comments {
    padding: 0;
}
#img{
	height: 100%;
}
.board_info {
    display: grid;
    grid-template-rows: 80px 650px 226px;
    border-top: 1px solid $offWhite;
}
/* 댓글달기 및 전송버튼 CSS */
#commtext{
	background: 0 0;
    border: 0;
    width: 85%;
    height: 100%;
    padding: 0;
    margin: 0;
    margin-left: 20px;
}
.sendComment{
	margin-left: 10px;
    border: 0;
    color: #0095f6;
    color: rgba(var(--d69,0,149,246),1);
    display: inline;
    padding: 0;
    position: relative;
    opacity: .3;
}
.commwrite{
	border-top: 1px solid rgba(var(--ce3,239,239,239),1);
}
</style>
</head>
<body onload="addStorys();getBoardList();">
<div id="wrap">
	<div id="s">
		<div id="my">
			<a href="${ cp }/story/list"><img id="myprofile"
				style="width: 50px; height: 50px;border-radius: 50%;"></a><br>
			${nickname }
				<div id="plus">
					<a href="${ cp }/story/insert"><img src="${ cp }/upload/plus.png" style="width:20px;height: 20px"></a><br>
				</div>
		</div>
		<div id="storys"></div>
	</div>
	<div id="board1"></div> <!-- 내 게시글 및 내가 팔로우한 회원들의 게시글이 추가될 div -->
	<div id="boardOptionModal"></div> <!-- 삭제,수정,취소 모달 -->
	<div id="likeModal"></div> <!-- 좋아요 모달 -->
</div>
</body>
<script>
	function getProfile() {
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				var myprofile = document.getElementById("myprofile");
				var data = xhr.responseText;
				var json = JSON.parse(data);
				myprofile.src = "${cp}/upload/" + json.profile;
			}
		}
		xhr.open('post', '${cp}/member/memberInfo', true);
		xhr.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
		xhr.send();
	}

		function addStorys() {
			var xhr = new XMLHttpRequest();
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4 && xhr.status == 200) {
					var sto = document.getElementById("storys");
					var data = xhr.responseText;
					var json = JSON.parse(data);
					for (var i = 0; i < json.length; i++) {
						var div = document.createElement("div");						
						div.innerHTML = "<a href='${ cp }/story/opponentlist?member_no="
								+ json[i].member_no
								+ "'><img src='${cp}/upload/"+json[i].profile+"' style='width: 50px; height: 50px;border-radius: 50%;'>"
								+ "</a><br><label style='color:black;'>"+json[i].nickname+"</label>&nbsp";
						div.style.display = "inline-block";
						div.style.margin = "auto";
						div.className = "sto";
						sto.appendChild(div)						
					}
					getProfile();
				}
			}
			xhr.open('post', '${cp}/story/opponentlist', true);
			xhr.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
			xhr.send();		
		}
/////////////////////////////////// 게시글 파트 //////////////////////////////////////////

	//게시글 리스트 불러오기
	function getBoardList(){
		//게시글파트(송영현)
		//var list="<c:out value='${boardList}'/>";
		var halist= new Array();
		var list = "${boardList}";

		<c:forEach items="${boardList}" var="vo" varStatus="i">
			var id="${vo.id}";
			var pwd="${vo.pwd}";
			var name="${vo.name}";
			var nickname="${vo.nickname}";
			var profile="${vo.profile}";
			var board_no="${vo.board_no}";
			var member_no="${vo.member_no}";
			var content="${vo.content}";
			var ref="${vo.ref}";
			var lev="${vo.lev}";
			var step="${vo.step}";
			
			var board1=document.getElementById("board1");
			var div=document.createElement("div");
			div.innerHTML=	"<div class='board_content'>"+
								"<div class='board_info'>"+//modal 우측의 사용자,글내용,댓글,댓글달기를 감싸는 div
									"<div class='board-wrap'>"+
										"<div class='board user' id='meta'>"+//프로필사진,닉네임이 들어갈 div
											"<div style='display:inline-block'>"+
												"<a href='${cp}/follow/move?youmember_no="+member_no+"'>"+
													"<img src='../upload/"+profile+"'>"+//프로필사진
												"</a>"+
											"</div>"+
											"<div style='display:inline-block'>"+
												"<a href='${cp}/follow/move?youmember_no="+member_no+"'>"+
													"<p class='handle'>"+nickname+"</p>"+//닉네임
												"</a>"+
											"</div>"+
										"</div>"+
									"</div>"+
									"<div class='image-wrap' id='image-wrap'>"+ //이미지들이 들어갈 div
										"<button class='prev' onclick='prevImg(${i.index})'>&#60;</button>"+
								    	"<button class='next' onclick='nextImg(${i.index})'>&#62;</button>"+
									"</div>"+
									
									//좋아요,댓글,게시글저장 버튼 및 좋아요 수, 게시일이 저장될 공간
									"<div class='board_footer'>"+
										"<div class='item'>"+
											"<span class='like'>"+
												//좋아요버튼
												"<button class='btn1 likeBtn' id='likeBtn'>"+
													"<div class='likeImg' id='likeImg'></div>"+ //좋아요버튼 이미지가 들어갈 div
												"</button>"+
											"</span>"+
											//댓글버튼
											"<span class='comm'>"+
												"<button class='btn1' onclick='commFocus()'>"+
													"<img src='${cp}/upload/comm.PNG'>"+
												"</button>"+
											"</span>"+
										"</div>"+
										"<div class='likeLink'>"+
										"</div>"+
										//게시글내용,댓글내용이 들어갈 wrap
										"<div class='scroll_wrap'>"+
											"<div class='board comments' id='board_comments'>"+ //게시글 글쓴이 프사,닉네임,글내용이 들어갈 공간
												content+ 
											"</div>"+
											"<div class='comments-wrap'></div>"+//작성된 댓글이 들어갈 div
										"</div>"+
										"<div class='time' id='time'>"+
										"</div>"+
										"<div class='commwrite'>"+//댓글작성하기
											"<input type='text' placeholder='댓글 달기...' class='commtext' id='commtext'>"+
											"<input type='button' class= 'sendComment' id='sendComment' value='전송'>"+
										"</div>"+
									"</div>"+
								"</div>"+
							"</div>";
			div.className="board2";
			div.setAttribute("id","board2");
			board1.appendChild(div);
			
			
			//글내용이 있을경우 board comment에 내용추가
			var board_comments=document.querySelectorAll(".board.comments")[${i.index}];
			if(content!=null && content!=""){
				board_comments.innerHTML="<div>"+
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
												"<div class='time2'></div>"+//작성시간
											"</p>"+
										"</div>";
			}else{
				meta_comments.innerHTML="";
				meta_comments.style.margin=0;
			}
			
			//자기게시글일경우 삭제,수정,업데이트 버튼 생성
			var boardBtnNum=-1; //...버튼의 갯수를 카운트할 변수
			var meta=document.getElementsByClassName("user")[${i.index}];
			if(${sessionScope.member_no}==member_no){
				meta.innerHTML+= "<div class='boardoption' style='display:inline-block'>"+
									 "<button class='boardbtn'>···</button>"+
								 "</div>";
			//회원정보 옆의 ...버튼 클릭시 게시글,댓글 삭제,수정,취소버튼 모달 함수호출
			boardBtnNum++;
			var boardbtn=document.getElementsByClassName("boardbtn")[boardBtnNum];
			boardbtn.addEventListener('click',function(){
				showBoardOptionModal(board_no,member_no,ref);
				});
			}
			
			
////////////////////////////////// 이벤트구역 /////////////////////////////////////////			
			
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
			//게시글번호 배열에 담기
			halist.push(board_no);
		</c:forEach>
		console.log(halist)
		getImgList(halist); //이미지리스트 가져오기
		getLikeCnt(halist); //좋아요리스트 가져오기
		boardBtnNum=-1; //...버튼의 갯수 초기화
		getTime();//게시글의 게시날짜 삽입
	}
	
	//좋아요 추가or삭제 ajax
	var likeaction=null;
	function likeAction(board_no,halist){
		likeaction=new XMLHttpRequest();

		halist = halist.split(",");
		likeaction.onreadystatechange=function(){
			if(likeaction.readyState==4 && likeaction.status==200){
				var data=likeaction.responseText;
				var json=JSON.parse(data);
				getLikeCnt(halist);
			}
		}
		likeaction.open('get','${cp}/good/insertdelete?board_no='+board_no+'&member_no=${sessionScope.member_no}',true);
		likeaction.send();
	}
	
	//좋아요리스트 가져오기
	var likeList=null;
	function getLikeCnt(halist){
		console.log(halist)
		likeList=new XMLHttpRequest();
		likeList.onreadystatechange=function(){
			if(likeList.readyState==4 && likeList.status==200){
				var data=likeList.responseText;
				var json=JSON.parse(data);
				console.log(json);
				var likeLink=document.getElementsByClassName("likeLink");
				var likeImg=document.getElementsByClassName("likeImg");
				if(json!=null && json!=""){
					
					for(var k=0;k<halist.length;k++){
						var likeChk=false;
						//var notLike = true;
						for(var i=0; i<json.length; i++){
							
							if(json[i][0].board_no==halist[k]){
								//좋아요가 있을경우
								for(var j=0;j<json[i].length;j++){
									if(halist[k]==json[i][j].board_no){
										//게시글번호가 같을경우
										var member_no=json[i][j].member_no;
										if(member_no==${sessionScope.member_no}){
											//내가 좋아요 눌렀을경우
											//likeChk=true;
											likeImg[k].innerHTML="<img src='${cp}/upload/like.PNG' onclick=\"likeAction("+halist[k]+",'"+halist+"')\">";
											break;
										}
									}
								}
								likeLink[k].innerHTML="<button class='likeListBtn' id='likeListBtn'>좋아요 "+json[i].length+"개</button>";
								break;
							}else{
								//좋아요가 없을경우
								likeLink[k].innerHTML="<p>가장 먼저 <label for='likeBtn' style='display:inline-block;font-weight:600'>좋아요</label>를 눌러보세요.</p>";
								likeImg[k].innerHTML="<img src='${cp}/upload/likenull.PNG' onclick=\"likeAction("+halist[k]+",'"+halist+"')\">";
							}
						}
					}
				}else{ //게시글중에서 좋아요가 하나도 없을경우
					for(var k=0;k<halist.length;k++){
						likeLink[k].innerHTML="<p>가장 먼저 <label for='likeBtn' style='display:inline-block;font-weight:600'>좋아요</label>를 눌러보세요.</p>";
						likeImg[k].innerHTML="<img src='${cp}/upload/likenull.PNG' onclick=\"likeAction("+halist[k]+",'"+halist+"')\">";
					}
				}
				
				
				//생성된 좋아요 리스트클릭 이벤트 생성 및 좋아요 모달함수 호출
				var likeListBtn=document.getElementsByClassName("likeListBtn");
				for(let i=0; i<likeListBtn.length; i++){
					likeListBtn.addEventListener('click', function() {
						showLikeModal(json); //좋아요 모달함수 호출
					});
				}
			}
		}
		likeList.open('get','${cp}/good/listhomefeed?halist='+halist,true);
		likeList.send();
	}
	
	//게시물 이미지 가져오기
	var imgList=null;
	function getImgList(halist){
		imgList=new XMLHttpRequest();
		imgList.onreadystatechange=function (){
			if(imgList.readyState==4 && imgList.status==200){
				var data=imgList.responseText;
				var json=JSON.parse(data);
				for (var i=0;i<json.length;i++){
					var image_wrap=document.getElementsByClassName("image-wrap");
						for(var k=0;k<json[i].length;k++){
							var imagePath = json[i][k].imagePath;
							var img=document.createElement("img");
							img.className="board_imgs";
							img.setAttribute("id","img");
							img.src="../upload/"+imagePath;
							image_wrap[i].appendChild(img);
						}
					showImage(0,i);
				}
			}			
		}
		imgList.open('post','${cp}/image/listhomefeed',true);
		imgList.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
		imgList.send('halist='+halist);
	}	
	
	//이미지 슬라이더
	var counter=0;
	var slider=null;
	var images=null;
	function showImage(cnt,index){
		counter=cnt;
		slider=document.getElementsByClassName("image-wrap")[index];
		images=slider.getElementsByTagName("img");
		var prev=document.getElementsByClassName("prev")[index];
		var next=document.getElementsByClassName("next")[index];
		for(var i=0; i<images.length; i++){
			images[i].className="hideImage";
		}
		images[cnt].className ="showImage";
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
	//다음버튼
	function nextImg(index){
		if (counter<images.length-1){
			counter+= 1;
		}else{
			counter=0;
		}
		showImage(counter,index);
	}
	//이전버튼
	function prevImg(index){
		if(counter > 0){
			counter-=1;
		}else{ 
			counter=images.length-1;
		}
		showImage(counter,index);
	}
	
	//게시글의 게시날짜 삽입
	function getTime(){
		<c:forEach items="${regdate}" var="longtime" varStatus="i">
			//게시글 시간 삽입
			var regdate=new Date(${longtime});
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
			var time=document.getElementsByClassName("time")[${i.index}];
			time.innerHTML=realDate;
		</c:forEach>
	}
	
</script>
</html>



























