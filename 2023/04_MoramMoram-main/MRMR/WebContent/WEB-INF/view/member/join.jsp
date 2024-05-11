<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>모람모람</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta content="" name="description">

<!-- Favicon -->
<link href="<%=request.getContextPath() %>/img/favicon.ico" rel="icon">

<!-- Google Web Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@700&display=swap" rel="stylesheet">

<!-- Icon Font Stylesheet -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

<!-- Libraries Stylesheet -->
<link href="<%=request.getContextPath() %>/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

<!-- Customized Bootstrap Stylesheet -->
<link href="<%=request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">

<!-- Template Stylesheet -->
<link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet">

<style type="text/css">
	table, tr, td { border:1px solid blue; border-collapse: collapse; }
	td { padding:5px;}
</style>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.6.1.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
$(function(){
	$("#searchAddr").on("click", function(e){
		new daum.Postcode({
	        oncomplete: function(data) {
	            $("input[name=mem_addr_base]").val(data.address);
	        }
	    }).open();
	}) 
	
	// 아이디 중복확인
	$("#idCheck").on("click", function(e){
		var memId = $("#mem_id").val();
		if(memId==""){
			alert("ID를 입력하세요");
			return;
		}
		
		$.ajax({
	    	 url : '<%=request.getContextPath()%>/member/memberIdCheck.do',
	    	 data : { "mem_id" : memId },
	    	 dataType : "json",
	    	 type : "get",
	    	 success : function(result){
	    		 if(result=="true"){
	    			 $("#idCheckResult").html("OK");
		    		 $("#idCheck").prop("disabled", true);
	    		 }else{
	    			 $("#idCheckResult").html("이미 사용중인 id입니다. 사용이 불가능합니다.");
	    		 }
	    	 },
	    	 error : function(xhr){
	    		 alert("status :" + xhr.status);
	    	 }
	     })
	});
	
	// 정규식 결과 체크 후 서브밋하는거 만들어야 함 //// 욕심을 버리자!! 일단 구현해
	$("#memberForm").on("submit", function(){
		var idchk = $("#idCheckResult").html();
		
		if(idchk!="중복확인 해주세요."){
			alert("ID 중복확인을 해주세요");
			return false;  // 전송 중단.
		}
		
		if($("#mem_pass").val()!= $("#mem_pass_check").val()){
			alert("비밀번호와 비밀번호 확인이 다릅니다. 다시 확인하세요.");
			return false;   // 서버로 전송을 하지 않는다.
		}else{
			$("#mem_pass_check").css("border", "2px soild blue");
		}
		
		if($("#mem_pass").val()=="" || $("#mem_pass").val()==null){
			alert("비밀번호를 입력해주세요");
			return false;
		}
		
		if($("#passCheckResult").html()!="OK"){
			alert("비밀번호형식이 올바르지 않습니다. 확인해주세요")
			return false;
		}
		
		if($("#nameCheckResult").html()!="OK"){
			alert("이름형식이 올바르지 않습니다. 확인해주세요")
			return false;
		}
		if($("#hpCheckResult").html()!="OK"){
			alert("전화번호형식이 올바르지 않습니다. 확인해주세요")
			return false;
		}
		if($("#regnoCheckResult").html()!="OK"){
			alert("주민번호형식이 올바르지 않습니다. 확인해주세요")
			return false;
		}
		if($("#emailCheckResult").html()!="OK"){
			alert("이메일형식이 올바르지 않습니다. 확인해주세요")
			return false;
		}
		
		return true;
	});
	
	// 정규식 체크
	$("#mem_id").on("keyup", function(){
		regText = /^[a-z]+[a-z0-9]{5,20}$/g;
		if(regText.test($(this).val().trim())){
			$("#idCheckResult").html("ID 중복확인 해주세요.");
		}else{
			$("#idCheckResult").html("사용할 수 없는 ID입니다.");
		}
	})
	$("#mem_pass").on("keyup", function(){
		regText = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/;
		if(regText.test($(this).val().trim())){
			$("#passCheckResult").html("OK");
		}else{
			$("#passCheckResult").html("사용할 수 없는 비밀번호입니다.");
		}
		
		if($("#mem_pass_check").val() != null){
			if($("#mem_pass_check").val().trim() != $("#mem_pass").val()){
				$("#passDoubleCheckResult").html("비밀번호가 일치하지 않습니다.");
			}else{
				$("#passDoubleCheckResult").html("OK");
			}	
		}
	})
	$("#mem_pass_check").on("keyup", function(){
		if($(this).val().trim() != $("#mem_pass").val()){
			$("#passDoubleCheckResult").html("비밀번호가 일치하지 않습니다.");
		}else{
			$("#passDoubleCheckResult").html("OK");
		}
	})
	$("#mem_name").on("keyup", function(){
		regText = /^[가-힣]{2,5}$/;
		if(regText.test($(this).val().trim())){
			$("#nameCheckResult").html("OK");
		}else{
			$("#nameCheckResult").html("형식에 맞게 정확히 입력해주세요.");
		}
	})
	$("#mem_hp").on("keyup", function(){
		regText = /^01(?:0|1|[6-9])-(?:\d{3}|\d{4})-\d{4}$/;
		if(regText.test($(this).val().trim())){
			$("#hpCheckResult").html("OK");
		}else{
			$("#hpCheckResult").html("형식에 맞게 정확히 입력해주세요.");
		}
	})
	$("#mem_regno").on("keyup", function(){
		val = $(this).val();
		regText = /^[0-9]{6}[1-4]{1}$/;
		if(regText.test(val.trim())){
			$("#regnoCheckResult").html("OK");
		}else{
			$("#regnoCheckResult").html("형식에 맞게 정확히 입력해주세요.");
		}
	})
	$("#mem_email").on("keyup", function(){
		regText = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
		if(regText.test($(this).val().trim())){
			$("#emailCheckResult").html("OK");
		}else{
			$("#emailCheckResult").html("형식에 맞게 정확히 입력해주세요.");
		}
	})
	
	
});

function readURL(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
			document.getElementById('preview').src = e.target.result;
		};
		reader.readAsDataURL(input.files[0]);
	} else {
		document.getElementById('preview').src = "";
	}
};

</script>
</head>

<body>
<jsp:include page="../../../header.jsp"></jsp:include>

    <div class="container-xxl position-relative bg-white d-flex p-0">
        <!-- Sign Up Start -->
        <div class="container-fluid">
            <div class="row h-100 align-items-center justify-content-center" style="min-height: 100vh;">
                <div class="col-sm-8">
                    <div class="bg-light rounded p-4 p-sm-5 my-4 mx-3"  style="border:3px solid teal;">
                        <div class="d-flex align-items-center justify-content-between mb-3">
                            <a href="http://localhost/MRMR/main.jsp"" class="">
                                <h3 class="text-primary"><i class="fa fa-hashtag me-2"></i>MORAM</h3>
                            </a>
                            <h3>회원가입</h3>
                        </div>
                        <form action="<%=request.getContextPath() %>/member/join.do" method="POST" >
                            <span>아이디 : 알파벳 영문자 + 숫자 5~20자</span><br>
	                        <div class="form-floating mb-3">
	                            <input type="button" id="idCheck" class="btn btn-outline-secondary m-2" style="float: right;" value="중복확인">
								<input type="text" class="form-control" name="mem_id" id="mem_id" required><br>
								<span id="idCheckResult"></span>
	                        </div>           
                            <span>비밀번호 : 알파벳 + 숫자 + 특수문자 8~16자</span>
							<div class="form-floating mb-3">
	                            <input type="password" class="form-control" name="mem_pass" id="mem_pass" required><br>
								<span id="passCheckResult"></span>
	                        </div>
	                        <div class="form-floating mb-3">
	                            <input type="password" class="form-control" name="mem_pass_check" id="mem_pass_check" required><br>
								<span id="passDoubleCheckResult"></span>
	                        </div>
                            <span>이름 : 한글 2~5자</span>
	                        <div class="form-floating mb-3">
	                            <input type="text" class="form-control" name="mem_name" id="mem_name" required><br>
								<span id="nameCheckResult"></span>
	                        </div>
                            <span>전화번호 : 010-0000-0000</span>
	                        <div class="form-floating mb-3">
	                            <input type="text" class="form-control" name="mem_hp" id="mem_hp" required><br>
								<span id="hpCheckResult"></span>
	                        </div>
                            <span>주민등록번호 : 생년월일 + 주민번호 앞 1자리 ex) 0001013</span>
	                        <div class="form-floating mb-3">
	                            <input type="text" class="form-control" name="mem_regno" id="mem_regno" style="padding: 20px 0px 0px 13px;" required><br>
								<span id="regnoCheckResult"></span>
	                        </div>
                            <span>이메일 : name@example.com</span>
	                        <div class="form-floating mb-3">
	                            <input type="text" class="form-control" name="mem_email" id="mem_email" style="padding: 20px 0px 0px 13px;" required><br>
								<span id="emailCheckResult"></span>
	                        </div>
	                        <div class="form-floating mb-3">
								<input type="button" id="searchAddr" class="btn btn-outline-secondary m-2" style="float: right;" value="주소찾기">
	                            <input type="text" class="form-control" name="mem_addr_base" id="mem_addr_base" readonly><br>
			                    <span id="baseAddrCheckResult"></span>
	                        </div>
	                        <div class="form-floating mb-3">
	                            <input type="text" class="form-control" name="mem_addr_detail" id="mem_addr_detail" required><br>
								<span id="detailAddrCheckResult"></span>
	                        </div>
			                <div class="form-floating mb-3" style="text-align: center;">
								<button type="submit" class="btn btn-primary m-2" style="background-color: #198754; color : white;">저장</button>
								<button type="reset" class="btn m-2" style="border-color: #198754; color:#198754; width: 200px; height: 50px; ">취소</button>
								<p class="text-center mb-0">계정이 있으신가요? <a href="<%=request.getContextPath()%>/member/login.do">로그인</a></p>
							</div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!-- Sign Up End -->
    </div>

    <!-- JavaScript Libraries -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="<%=request.getContextPath() %>/lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>
    
    <script src="<%=request.getContextPath() %>/lib/chart/chart.min.js"></script>
    <script src="<%=request.getContextPath() %>/lib/easing/easing.min.js"></script>
    <script src="<%=request.getContextPath() %>/lib/waypoints/waypoints.min.js"></script>
    <script src="<%=request.getContextPath() %>/lib/owlcarousel/owl.carousel.min.js"></script>
    <script src="<%=request.getContextPath() %>/lib/tempusdominus/js/moment.min.js"></script>
    <script src="<%=request.getContextPath() %>/lib/tempusdominus/js/moment-timezone.min.js"></script>
    <script src="<%=request.getContextPath() %>/lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

	  <!-- iamport.payment.js -->
	<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>

    <!-- Template Javascript -->
    <script src="<%= request.getContextPath() %>/js/main.js"></script>
</body>
</html>