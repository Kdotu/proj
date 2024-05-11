<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>

<script>
</script>

<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta content="" name="description">

<!-- Favicon -->
<link href="<%=request.getContextPath()%>/img/favicon.ico" rel="icon">

<!-- Google Web Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@700&display=swap" rel="stylesheet">

<!-- Icon Font Stylesheet -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

<!-- Libraries Stylesheet -->
<link href="<%=request.getContextPath() %>/lib/animate/animate.min.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
<link href="<%= request.getContextPath() %>/lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

<!-- Customized Bootstrap Stylesheet -->
<link href="<%=request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">

<!-- Template Stylesheet -->
<link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet">
  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/style/public.css">
<script src = '<%= request.getContextPath() %>/js/jquery-3.6.1.min.js'></script>
<script src="<%=request.getContextPath() %>/js/public.js" type="text/javascript" charset="utf-8"></script>
<!-- 링크 끝 -->
<style type="text/css">
.loginform{
	width: 100%;
}
*{
	font-family: 'Noto Sans KR', sans-serif;
}
</style>

</head>
<body>

	<!-- header.jsp include -->
	<jsp:include page="../../../header.jsp"></jsp:include>

	<form action="<%=request.getContextPath()%>/member/login.do" method="post">
	    <div class="container-xxl position-relative bg-white d-flex p-0">
	        <!-- Sign In Start -->
	        <div class="container-fluid">
	            <div class="row h-100 align-items-center justify-content-center" style="min-height: 100vh;">
	                <div class="col-12 col-sm-8 col-md-6">
	                    <div class="bg-light rounded p-4 p-sm-5 my-4 mx-3" style="border: 3px solid teal;width: 600px;">
	                        <div class="d-flex align-items-center justify-content-between mb-3">
	                            <a href="http://localhost/MRMR/main.jsp"" class="">
	                                <h3 class="text-primary"><i class="fa fa-hashtag me-2"></i>MORAM</h3>
	                            </a>
	                            <h3>로그인</h3>
	                        </div>
                            <label>아이디</label>
	                        <div class="form-floating mb-3">
	                            <input type="id" class="form-control loginform" id="floatingInput" name="userId">
	                        </div>
                            <label>비밀번호</label>
	                        <div class="form-floating mb-4">
	                            <input type="password" class="form-control loginform" id="floatingPassword" name="userPassword">
	                        </div>
	                        <div class="d-flex align-items-center justify-content-between mb-4">
	                            <a href="<%=request.getContextPath() %>/member/infoSearch.do">ID/PW찾기</a>
	                        </div>
	                        <button type="submit" class="btn py-3 w-100 mb-4" style="background-color: #198754; color : white;">로그인</button>
	                        <p class="text-center mb-0">계정이 없으신가요? <a href="<%=request.getContextPath() %>/member/join.do">회원가입</a></p>
	                    </div>
	                </div>
	            </div>
	        </div>
	        <!-- Sign In End -->
	    </div>
	</form>

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
