/**
 * 
 */
/*

앱 키
네이티브 앱 키	805d878b68d776fe79d5d22dc9bc90cf
REST API 키	7e09d39bf92f116aef953386110ba221
JavaScript 키	805f435fff584ac35854286c3ec0233d
Admin 키	eb9b8f54640cc29b24e5d169bda5efb1

*/

/* 서비스 결제 */
PayService = function(memNo,serviceName){
	
	$.ajax({
	url : 'el/paymember',
	contentType : 'application/x-www-form-urlencoded; charset=utf-8',
	data : {"id" : memNo},
	type : 'POST',
	success : function(data){
		//alert("통신성공" + data);
		console.log(data+"");
		var IMP = window.IMP;
		var code ="imp60365831"; // 가맹점 식별코드
		IMP.init(code);
		IMP.request_pay(
		{
		    pg : 'kakaopay',
		    pay_method : 'card',
		    merchant_uid : 'merchant_' + new Date().getTime(),
		    name : '모람모람:' + serviceName , //결제창에서 보여질 이름
		    amount : lostpay, //실제 결제되는 가격
		}, function(rsp) {
			console.log(rsp);
		    if ( rsp.success ) {
		    	var msg = '결제가 완료되었습니다.';
				modalForm = $('#modalForm');
				modalForm.submit();
		    } else {
		    	 var msg = '결제에 실패하였습니다.\n';
		         msg += '에러내용 : ' + rsp.error_msg;
		    }
		    alert(msg);
			}
			)
		}
	});
	
}	


/*
PayKakaoService = function(){
	function pay(){
		var = lostpay = $("input[name=Lost_Pay]:checked").val();
			if(lostpay == "direct"){
				lostpay = $('#paybox').val();
				lostpay = lostpay.replace("direct","").replace(",","");
			} // 숫자만 걸러주기
			
			var id = ''; // 회원정보 받는 곳
			
			$.ajax({
				url : "el/paymember",
				contentType : 'application/x-www-form-urlencoded; charset=utf-8',
				data : {"id" : id},
				type : "POST",
				success : function(res){
					alert("통신성공");
					
					var IMP = window.IMP;
					var code =""; // 가맹점 식별코드
					IMP.init(code);
					IMP.request_pay(
						{
							// name과 amount만 있어도 결제 진행 가능
							pg : 'kakao',
							pay_method : 'card',
							merchant_uid : 'merchant_' + new Date().getTime(),
							name : "서비스명",
							amount : lostpay,
							buyer_name : data[0].name,
							buyer_email : data[0].emial,
							buyer_tel : data[0].contact
						},
						function(rsp){
							if(rsp.success){
								var msg = "결제가 완료되었습니다.";
								var result = {
									"PayCode" : rsp.merchant_uid,
									"Pay_Way" : rsp.pay_method,
									"Pay_Amount" : rsp.paid_amount,
									"Pay_State" : rsp.status,
									"Pay_date" : getFormatDate(new Date()),
									"Id" : id
								};
								
								$.ajax({
									url : "",
									dataType : 'json',
									contentType : 'application/json; charset=utf-8',
									data : JSON.stringify(result),
									type : 'post',
									success : function(data){
										console.log(data);
									}
								}); // sub ajax
								alert("결제성공!");
								addboard();
								}else{
									var msg = "결제에 실패하였습니다. 에러내용 : " + rsp.error_msg;
									alert(msg);
								}
							} //function
						); // IMP.request_pay
						return true;
						
						}, // main ajax success
				)} // main ajax
			}) // pay
		}
*/


