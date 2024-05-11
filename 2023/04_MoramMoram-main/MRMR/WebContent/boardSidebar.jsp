<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div class="sidebar pe-4 pb-3">
    <nav class="navbar bg-light navbar-light">
		<div>
			<label style="color: white;">moram<br/>moram<br/>moram<br/>moram<br/></label>
		</div>
        <div class="navbar-nav w-100">
            <a href="<%=request.getContextPath() %>/board/setSession.do?boardCategoryNo=1&page=1" class="nav-item nav-link active"><i class="fa fa-tachometer-alt me-2"></i>공지사항</a>
            <a href="<%=request.getContextPath() %>/board/setSession.do?boardCategoryNo=2&page=1" class="nav-item nav-link"><i class="fa fa-th me-2"></i>문의게시판</a>
            <a href="<%=request.getContextPath() %>/board/setSession.do?boardCategoryNo=3&page=1" class="nav-item nav-link"><i class="fa fa-keyboard me-2"></i>후기게시판</a>
            <a href="<%=request.getContextPath() %>/board/setSession.do?boardCategoryNo=4&page=1" class="nav-item nav-link"><i class="fa fa-table me-2"></i>홍보게시판</a>
            <a href="<%=request.getContextPath() %>/board/setSession.do?boardCategoryNo=5&page=1" class="nav-item nav-link"><i class="fa fa-chart-bar me-2"></i>자유게시판</a>
        </div>
         
    </nav>
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