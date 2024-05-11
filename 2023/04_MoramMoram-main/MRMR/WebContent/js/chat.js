
/**
 * 
 */
var ws = null;
var url = "ws://192.168.145.36:80/MRMR/ChatServer"; // 변경하기
webSocket = new WebSocket(url);
webSocket.binaryType="arraybuffer";

function chatWinOpen(chatting){
	$('#chatspace').show();
	$('#chatWindow').scrollTop($('#chatWindow').prop('scrollHeight'));
	chatWindow = $('#chatWindow');
	chatMessage = $('#chatMessage').val();
	chatNo = $(chatting).attr('chatNo');
	target = $(chatting).attr('target');
	
	$.ajax({
		url: "http://192.168.145.36:80/MRMR/chat/Read.do", // 변경하기
		method: "GET",
		data : {
		  chatNo : chatNo,
		  target : target
    	},
		success : function(res){
			$('#chatWindow').empty();
		  	code = "";
			$.each(res, function(i,v){
				vcont = v.chat_content;

				if(v.mem_no_sender == chatNo){
					code += "<div class='p-2 mb-2 bg-primary text-white myMsg'>"+ vcont +"</div><br/><br/>";
				}else {
					code += "<div class='p-2 mb-2 bg-secondary text-white targetMsg'>"+ vcont +"</div><br/><br/>";
				}
			})
			$('#chatWindow').append(code);
			$('#chatWindow').scrollTop($('#chatWindow').prop('scrollHeight'));
		},
		error : function(xhr){
			alert("chatWinOpen 상태 : " + xhr);
			console.log(xhr);
		},
        dataType: "json"
    });
}

function sendMessage(){
	code = "<div class='p-2 mb-2 bg-primary text-white myMsg'>"+ $('#chatMessage').val() +"</div><br/><br/>";
	$('#chatWindow').append(code);
	
	webSocket.send(chatNo + '|' + $('#chatMessage').val() + '>'+ target);
	
	$('#chatWindow').scrollTop($('#chatWindow').prop('scrollHeight'));
	$('#chatMessage').val("");
}

function chatWinClose(){
	$('#chatspace').hide();
	$('#chatList').show();
}

function disconn(){
	webSocket.close();
}

function enterKey(){
	if(window.event.keyCode == 13){
		sendMessage();
	}
}

webSocket.onopen = function(event){
	code = "연결되었습니다.<br/>";
	$('#chatWindow').html(code);
}

webSocket.onclose = function(event){
	code = "종료되었습니다.<br/>";
	$('#chatWindow').html(code);
}

webSocket.onerror = function(event){
	code = "에러가 발생하였습니다.<br/>";
	$('#chatWindow').append(code);
}

//20|근데 왜 화면에 안뜰까요 ㅠㅠ>31
webSocket.onmessage = function(event){
	var message = event.data.substring(event.data.indexOf("|")+1, event.data.indexOf(">"));
	var sender = event.data.substring(0, event.data.indexOf("|"));
	var content = message[1];
 console.log("message1", event.data);
 console.log("message2", message);
	if(content != ""){
		if(content.match("/")){
			if(content.match("/"+chatNo)){
				var temp = content.replace(("/"+chatNo), "[귓속말] ");
				text = "<div class='p-2 mb-2 bg-secondary text-white targetMsg'>"+sender+""+temp+"</div><br/>";
				$('#chatWindow').append(text);
			}
		}else{
			text = "<div class='p-2 mb-2 bg-secondary text-white targetMsg'> 윤여백 : " + message + "</div><br/>";
			$('#chatWindow').append(text);
			$('#chatWindow').scrollTop($('#chatWindow').prop('scrollHeight'));
		}
	}
}
