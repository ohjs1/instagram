
//유저가 채팅읽은지 확인해주는 함수
	var ccxhr = null;
	function chatConfirm(myMember_no) {
		ccxhr = new XMLHttpRequest();
		
		ccxhr.onreadystatechange = function(){
			
		}
		alert('dd');
		ccxhr.open('get', 'http://localhost:8081/semiproject_instagram/dm/chatChecked?myMember_no=' + myMember_no, true);
		ccxhr.send();
	}