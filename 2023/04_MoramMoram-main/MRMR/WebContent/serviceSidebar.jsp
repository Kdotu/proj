<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<meta charset="utf-8">
<title>MORAM MORAM</title>
<style>
.serviceSidebar{
	background-color: white;
}
</style>
<body>
<!-- Sidebar Start -->
<div class="sidebar pe-4 pb-3">
    <nav class="navbar bg-light navbar-light serviceSidebar" style="background-color: white;">
   		<div>
			<label style="color: white;">moram<br/>moram<hr/>moram<br/>moram</label>
		</div>
		<div class="navbar-nav w-100" style="margin-left: 10%;">
		    <a href="<%= request.getContextPath() %>/service/categoryMain.do?main_no=0&page=1" style="color:teal"><i class="fa fa-tachometer-alt me-2" style="color:teal"></i>서비스전체목록</a>
		    <div class="nav-item dropdown">
		     <a href="<%= request.getContextPath() %>/service/categoryMain.do?main_no=1&page=1" class="nav-link"><i class="fa fa-laptop me-2"></i>디자인</a>
		        <div class="bg-transparent border-0">
		            <a href="<%= request.getContextPath() %>/service/categorySub.do?sub_no=1&page=1" class="nav-item nav-link">로고디자인</a>
		            <a href="<%= request.getContextPath() %>/service/categorySub.do?sub_no=2&page=1" class="nav-item nav-link">웹디자인</a>
		        </div>
		    </div>
		    <div class="nav-item dropdown">
		        <a href="<%= request.getContextPath() %>/service/categoryMain.do?main_no=2&page=1" class="nav-link"><i class="fa fa-laptop me-2"></i>IT,프로그래밍</a>
		        <div class="bg-transparent border-0">
		            <a href="<%= request.getContextPath() %>/service/categorySub.do?sub_no=3&page=1" class="nav-item nav-link">홈페이지제작</a>
		            <a href="<%= request.getContextPath() %>/service/categorySub.do?sub_no=4&page=1" class="nav-item nav-link">앱 제작</a>
		        </div>
		    </div>
		    <div class="nav-item dropdown">
		        <a href="<%= request.getContextPath() %>/service/categoryMain.do?main_no=3&page=1" class="nav-link"><i class="fa fa-laptop me-2"></i>영상, 사진, 음향</a>
		        <div class="bg-transparent border-0">
		            <a href="<%= request.getContextPath() %>/service/categorySub.do?sub_no=5&page=1" class="nav-item nav-link">광고,홍보영상</a>
		            <a href="<%= request.getContextPath() %>/service/categorySub.do?sub_no=6&page=1" class="nav-item nav-link">교육 영상</a>
		        </div>
		    </div>
		    <div class="nav-item dropdown">
		        <a href="<%= request.getContextPath() %>/service/categoryMain.do?main_no=4&page=1" class="nav-link"><i class="fa fa-laptop me-2"></i>번역, 통역</a>
		        <div class="bg-transparent border-0">
		            <a href="<%= request.getContextPath() %>/service/categorySub.do?sub_no=7&page=1" class="nav-item nav-link">영어 통,번역</a>
		            <a href="<%= request.getContextPath() %>/service/categorySub.do?sub_no=8&page=1" class="nav-item nav-link">중국어 통,번역</a>
		            <a href="<%= request.getContextPath() %>/service/categorySub.do?sub_no=9&page=1" class="nav-item nav-link">일본어 통,번역</a>
		        </div>
		    </div>
		</div>
    </nav>
</div>
<!-- Sidebar End -->

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