
//유저가 채팅읽은지 확인해주는 함수
	var ccxhr = null;
	function chatConfirm(myMember_no) {
		ccxhr = new XMLHttpRequest();
		
		ccxhr.onreadystatechange = function(){
			
		}
		ccxhr.open('get', 'http://localhost:8081/semiproject_instagram/dm/chatChecked?myMember_no=' + myMember_no, true);
		ccxhr.send();
	}
	
//개별 유저 채팅 확인
	var ccu_xhr = null;
	function chatConfirmUser(myMember_no) {
		ccu_xhr = new XMLHttpRequest();
		//alert('test111');
		ccu_xhr.onreadystatechange = function(){
			
		}
		ccu_xhr.open('get', 'http://localhost:8081/semiproject_instagram/dm/member/checked?myMember_no=' + myMember_no, true);
		ccu_xhr.send();
	}