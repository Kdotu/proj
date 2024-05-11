<%@page import="vo.MemberVO"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta charset="utf-8">
<title>MORAM MORAM</title>
<%
Map<String, Object> loginInfo = (Map<String, Object>)session.getAttribute("loginInfo");
boolean isLogined = false;
MemberVO loginMemberInfo = null;
boolean isLeft = false;
String gradeName = "";

if(loginInfo != null){
	loginMemberInfo = (MemberVO)loginInfo.get("memberInfo");
	int grade = loginMemberInfo.getMem_grade();
	if(grade == 0){
		gradeName = "일반회원";
	}else if(grade == 1){
		gradeName = "전문가";
	}else if(grade == 9){
		gradeName = "관리자";
	}
	
	if(loginMemberInfo.getMem_grade() == 13){
		isLogined = false;
		isLeft = true;
		session.removeAttribute("loginInfo");
	}else{
		isLogined = true;
	}
}
%>
<script type="text/javascript">
$(function(){
	if(<%=isLogined%>) {
		$("#loginButton").hide();
		$("#joinButton").hide();
		$("#myPageButton").show();
		$("#chatButton").show();
	}
	if(<%=isLeft%>){
		alert("탈퇴한 회원입니다.");
	}
})
</script>
<header>
	<nav class="navbar navbar-expand-lg bg-white navbar-light sticky-top p-0 px-4 px-lg-5 shadow-sm">
<!--      <a href="http://localhost/MRMR/main.jsp" class="navbar-brand d-flex align-items-center"> -->
     <a href="http://192.168.145.36/MRMR/main.jsp" class="navbar-brand d-flex align-items-center">   
       	<h3 class="text-primary pt-2"><i class="fa fa-hashtag me-2"></i></h3>
        <h2 class="m-0 text-primary titlecolor">MoramMoram</h2></a>

        <button type="button" class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <div class="navbar-nav ms-auto py-4 py-lg-0">
				
                <a href="#" class="nav-item nav-link" id="chatButton" style="display:none;">About</a>
                <a href="<%= request.getContextPath() %>/service/categoryMain.do?main_no=0&page=1" class="nav-item nav-link active">Service</a>
                
                
                <div class="nav-item dropdown">
                    <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">커뮤니티</a>
                    <div class="dropdown-menu shadow-sm m-0">
                        <a href="<%=request.getContextPath() %>/board/setSession.do?boardCategoryNo=1&page=1" class="dropdown-item">공지사항</a>
                        <a href="<%=request.getContextPath() %>/board/setSession.do?boardCategoryNo=2&page=1" class="dropdown-item">문의게시판</a> 
                        <a href="<%=request.getContextPath() %>/board/setSession.do?boardCategoryNo=3&page=1" class="dropdown-item">후기게시판</a>
                        <a href="<%=request.getContextPath() %>/board/setSession.do?boardCategoryNo=4&page=1" class="dropdown-item">홍보게시판</a>
                        <a href="<%=request.getContextPath() %>/board/setSession.do?boardCategoryNo=5&page=1" class="dropdown-item">자유게시판</a>
                    </div>
                </div>
                    
                <a href="<%=request.getContextPath()%>/member/login.do" class="nav-item nav-link" id="loginButton">로그인</a>
				<a href="<%=request.getContextPath()%>/member/join.do" class="nav-item nav-link active" id="joinButton">회원가입</a>
				<a href="<%=request.getContextPath()%>/member/myPage.do" class="nav-item nav-link active" id="myPageButton" style="display:none;">마이페이지</a>
            
           <%if(isLogined){ %>
		        <div class="position-relative">
		            <img class="rounded-circle" src="<%=request.getContextPath() %>/files/imageView.do?mem_no=<%=loginMemberInfo.getMem_no() %>&file_category_no=4" alt="" style="width: 50px; height: 40px;">
		            <div class="bg-success rounded-circle border border-2 border-white position-absolute end-0 bottom-0 p-1"></div>
		        </div>
		        <div class="ms-3">
		            <h6 class="mb-0"><%=loginMemberInfo.getMem_name() %></h6>
		            <span><%=gradeName %></span>
		        </div>
	  	  <%} %>
		  	  </div>
        </div>
    </nav> 
    
    
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
</header>
