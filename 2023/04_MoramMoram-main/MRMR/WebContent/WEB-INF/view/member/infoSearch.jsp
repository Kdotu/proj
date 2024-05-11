<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>

<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta content="" name="description">
<link href="<%=request.getContextPath() %>/img/favicon.ico" rel="icon">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@700&display=swap" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/lib/animate/animate.min.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
<link href="<%= request.getContextPath() %>/lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />
<link href="<%=request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/style/public.css">
<script src = '<%= request.getContextPath() %>/js/jquery-3.6.1.min.js'></script>
<script src="<%=request.getContextPath() %>/js/public.js" type="text/javascript" charset="utf-8"></script>
<!-- 링크 끝 -->
<style type="text/css">
.loginform {
	width: 100%;
}
</style>
<script>
$(function(){
	$("#idSearch").on("click", function(){
		memName = $("#id_mem_name").val();
		$.ajax({
			url : "<%=request.getContextPath()%>/member/memberIdSearch.do",
			data : {
				"mem_name" : memName,
				"mem_email" : $("#id_mem_email").val()
			},
			type : "post",
			success : function(memId){
				if(memId == "false"){
					alert("일치하는 회원 정보가 없습니다. 다시 시도해주세요.");
				}else{
					alert(memName + "님의 ID : " + memId + " 입니다.");
				}
			},
			error : function(xhr){
				alert("상태 : " + xhr.status);
			},
			dataType : "text"
		})
	})
	
	$("#pwSearch").on("click", function(){
		$.ajax({
			url : "<%=request.getContextPath()%>/member/memberPasswordSearch.do",
			data : {
				"mem_id" : $("#pw_mem_id").val(),
				"mem_name" : $("#pw_mem_name").val(),
				"mem_email" : $("#pw_mem_email").val()
			},
			type : "post",
			success : function(result){
				if(result == "false"){
					alert("일치하는 회원 정보가 없습니다. 다시 시도해주세요.");
				}else{
					alert("비밀번호를 이메일로 전송하였습니다. 확인해주세요.");
				}
			},
			error : function(xhr){
				alert("상태 : " + xhr.status);
			},
			dataType : "text"
		})
	})
})
</script>
</head>
<body>

	<!-- header.jsp include -->
	<jsp:include page="../../../header.jsp"></jsp:include>

    <div class="container-xxl position-relative bg-white d-flex p-0">
        <!-- Sign In Start -->
        <div class="container-fluid">
            <div class="row h-100 align-items-center justify-content-center" style="min-height: 100vh;">
                <div class="col-10">
                    <div class="bg-light rounded p-4 p-sm-5 my-4 mx-3">
                        <div class="d-flex align-items-center justify-content-between mb-3">
                            <a href="http://localhost/MRMR/main.jsp"" class="">
                                <h3 class="text-primary"><i class="fa fa-hashtag me-2"></i>MORAM</h3>
                            </a>
							<h4>ID찾기</h4>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" name="mem_name" id="id_mem_name"><br>
                            <label for="floatingInput">이름</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" name="mem_email" id="id_mem_email"style="padding: 0px 10px;">
                            <label for="floatingInput">이메일</label>
                        </div>
                        <input type="button" class="btn btn-primary py-3 w-100 mb-4" id="idSearch" value="ID 찾기">
                    </div>
                </div>
            </div>
        </div>
        <!-- Sign In End -->   
		 	
		<!-- Sign In Start -->
        <div class="container-fluid">
            <div class="row h-100 align-items-center justify-content-center" style="min-height: 100vh;">
                <div class="col-10">
                    <div class="bg-light rounded p-4 p-sm-5 my-4 mx-3">
                        <div class="d-flex align-items-center justify-content-between mb-3">
                            <a href="http://localhost/MRMR/main.jsp"" class="">
                                <h3 class="text-primary"><i class="fa fa-hashtag me-2"></i>MORAM</h3>
                            </a>
							<h4>PW찾기</h4>
                        </div>
						
                        <div class="form-floating mb-4">
                            <input type="text" class="form-control" name="mem_id" id="pw_mem_id" >
                            <label for="floatingInput">아이디</label>
                        </div>
						
                        <div class="form-floating mb-4">
                            <input type="text" class="form-control" name="mem_name" id="pw_mem_name">
                            <label for="floatingInput">이름</label>
                        </div>
						
						<div class="form-floating mb-4">
                            <input type="text" class="form-control" name="mem_email" id="pw_mem_email">
                            <label for="floatingInput">이메일</label>
                        </div>
						
                        <input type="button" class="btn btn-primary py-3 w-100 mb-4" id="pwSearch" value="PW 찾기">
                    </div>
                </div>
            </div>
        </div>
        <!-- Sign In End -->
		
		
    </div>

    <!-- JavaScript Libraries -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="<%=request.getContextPath() %>/lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

    <!-- Template Javascript -->
    <script src="<%=request.getContextPath()%>/js/main.js"></script>
	
	<!-- 	footer.jsp include -->
	<jsp:include page="../../../footer.jsp"></jsp:include>
</body>

</html>
