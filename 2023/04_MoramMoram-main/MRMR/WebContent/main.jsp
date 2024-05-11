<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
<link href="<%=request.getContextPath() %>/lib/animate/animate.min.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

<!-- Customized Bootstrap Stylesheet -->
<link href="<%=request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">

<!-- Template Stylesheet -->
<link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/slick/slick.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/slick/slick-theme.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/style/public.css">
<script src="<%=request.getContextPath() %>/slick/slick.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath() %>/js/public.js" type="text/javascript" charset="utf-8"></script>
<script src ="<%=request.getContextPath() %>/js/jquery-3.6.1.min.js"></script>
<script  src="<%=request.getContextPath() %>/js/jquery.serializejson.js" type="text/javascript"></script>
<!-- 링크 끝 -->

</head>
<%
	Map<String, Object> loginInfo = (Map<String, Object>) session.getAttribute("loginInfo");
	if(loginInfo==null){
		session.setAttribute("isLogined", false);
	}
%>
<body>

	<!-- header.jsp include -->
 	<jsp:include page="./header.jsp"></jsp:include>
    
	<div class="banner-container">
	  <div class="banner ">
	    <div data-index=1 style="text-align: center; margin-left: 5% auto;">
	   		 <img src="./img/slide1.png" style="text-align: center; margin-left: 5% auto;">
	    </div>
	    <div data-index=2 style="text-align: center;">
	    	<img src="./img/slide2.png" style="text-align: center; margin-left: 5% auto;">
	    </div>
	    <div data-index=3 style="text-align: center;">
	    	<img src="./img/slide3.png" style="text-align: center; margin-left: 5% auto;">
	    </div>
	    <div data-index=4 style="text-align: center;"> 
	    	<img src="./img/slide4.png" style="text-align: center; margin-left: 5% auto;">
	    </div>
	  </div>
	</div>

   <!-- 슬라이드 끝 -->
	<nav id="nav" style="text-align: center;">
		<a href="<%= request.getContextPath() %>/service/categoryMain.do?main_no=1"><img src=./img/아이콘-디자인.png><span></span></a>
		<a href="<%= request.getContextPath() %>/service/categoryMain.do?main_no=2""><img src=./img/아이콘-IT프로그래밍.png><span></span></a>
		<a href="<%= request.getContextPath() %>/service/categoryMain.do?main_no=3"><img src=./img/아이콘-영상사진음향.png><span></span></a>
		<a href="<%= request.getContextPath() %>/service/categoryMain.do?main_no=4"><img src=./img/아이콘-번역통역.png><span></span></a>
	</nav>
	<!-- 아이콘 끝 -->
	   
	<!-- JavaScript Libraries -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="<%=request.getContextPath() %>/lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

    <!-- Template Javascript -->
    <script src="<%=request.getContextPath() %>/js/main.js"></script>
	
    <!-- Swiper JS -->
    <script src="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.js"></script>
  	<script src="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.js"></script>
    
	<script>
	var swiper = new Swiper(".mySwiper", {
		slidesPerView: 7,
		grid: {
		  rows: 1,
		},
		spaceBetween: 30,
		pagination: {
		  el: ".swiper-pagination",
		  clickable: true,
		},
	});
	
	let appendNumber = 600;
    let prependNumber = 1;
    const swiper = new Swiper('.swiper', {
      slidesPerView: 3,
      centeredSlides: true,
      spaceBetween: 30,
      pagination: {
        el: '.swiper-pagination',
        type: 'fraction',
      },
      navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
      },
      virtual: {
        slides: (function () {
          const slides = [];
          for (var i = 0; i < 600; i += 1) {
            slides.push('Slide ' + (i + 1));
          }
          return slides;
        })(),
      },
    });
	</script>

    <!-- Swiper -->
	<jsp:include page="./slide.jsp"></jsp:include>
	<jsp:include page="./slide2.jsp"></jsp:include>
    
  	<!-- 	footer.jsp include -->
	<jsp:include page="./footer.jsp"></jsp:include>

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