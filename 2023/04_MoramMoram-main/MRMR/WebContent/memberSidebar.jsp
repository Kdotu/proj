<%@page import="vo.MemberVO"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
Map<String, Object> loginInfo = (Map<String, Object>)session.getAttribute("loginInfo");
MemberVO loginMemberInfo = (MemberVO)loginInfo.get("memberInfo");
int existExpert = (Integer) loginInfo.get("existExpert");

int grade = loginMemberInfo.getMem_grade();
String gradeName = "";
String toggleBtn = "";
if(grade == 0){
	gradeName = "일반회원";
	toggleBtn = "전문가";
}else if(grade == 1){
	gradeName = "전문가";
	toggleBtn = "일반회원";
}else if(grade == 9){
	gradeName = "관리자";
}
%>
<script type="text/javascript">
$(function(){
	$("#withdrawal").on("click", function(){
		if(confirm("정말로 탈퇴하시겠습니까?")){
			location.href = "<%=request.getContextPath()%>/member/withdrawal.do?mem_no=<%=loginMemberInfo.getMem_no()%>";
		}
	})
	
	$("#toggle").on("click", function(){
		$.ajax({
			url : "<%=request.getContextPath()%>/member/existExpert.do",
			type : "get",
			success : function(res){
				if(res == 1){
					location.href = "<%=request.getContextPath()%>/member/toggle.do";
				}else{
					alert("전문가 정보가 없습니다.");
					// 14번
					$.ajax({
						url : "<%=request.getContextPath()%>/member/enrollExpert.do",
						type : "get",
						success : function(enrollExpertForm){
							$("#myPageDiv").html(enrollExpertForm);
						},
						error : function(xhr){
							alert("상태 : " + xhr.status);
						},
						dataType : "html"
					})
					// 14번
				}
			},
			error : function(xhr){
				alert("상태 : " + xhr.status);
			},
			dateType : "text"
		})
		
	})
	
	$("#updateMemberInfo").on("click", function(){
		$.ajax({
			url : "<%=request.getContextPath()%>/member/updateMemberInfo.do",
			type : "get",
			success : function(updateMemberForm){
				$("#myPageDiv").html(updateMemberForm);
			},
			error : function(xhr){
				alert("상태 : " + xhr.status);
			},
			dataType : "html"
		})
	})
	
	$("#enrollExpert").on("click", function(){
		$.ajax({
			url : "<%=request.getContextPath()%>/member/enrollExpert.do",
			type : "get",
			success : function(enrollExpertForm){
				$("#myPageDiv").html(enrollExpertForm);
			},
			error : function(xhr){
				alert("상태 : " + xhr.status);
			},
			dataType : "html"
		})
	})
	
	$("#chat").on("click", function(){
		$.ajax({
			url : "<%=request.getContextPath()%>/chat/main.do",
			type : "get",
			success : function(chat){
				$("#myPageDiv").html(chat);
			},
			error : function(xhr){
				alert("상태 : " + xhr.status);
			},
			dataType : "html"
		})
	})
	
	$(".myBoard").on("click", function(){
		$.ajax({
			url : "<%=request.getContextPath()%>/member/myBoard.do?mem_no=<%=loginMemberInfo.getMem_no()%>&page=1",
			type : "get",
			success : function(myBoard){
				$("#myPageDiv").html(myBoard);
			},
			error : function(xhr){
				alert("상태 : " + xhr.status);
			},
			dataType : "html"
		})
	})
	
	$(".schedule").on("click", function(){
		$.ajax({
			url : "<%=request.getContextPath()%>/member/schedule.do?mem_no=<%=loginMemberInfo.getMem_no()%>",
			type : "get",
			success : function(schedule){
				$("#myPageDiv").html(schedule);
			},
			error : function(xhr){
				alert("상태 : " + xhr.status);
			},
			dataType : "html"
		})
	})
	
	$(".serviceHistory").on("click", function(){
		$.ajax({
			url : "<%=request.getContextPath()%>/member/serviceHistory.do?mem_no=<%=loginMemberInfo.getMem_no()%>&page=1",
			type : "get",
			success : function(serviceHistory){
				$("#myPageDiv").html(serviceHistory);
			},
			error : function(xhr){
				alert("상태 : " + xhr.status);
			},
			dataType : "html"
		})
	})
	
	$("#wishlist").on("click", function(){
		$.ajax({
			url : "<%=request.getContextPath()%>/member/wishlist.do?mem_no=<%=loginMemberInfo.getMem_no()%>",
			type : "get",
			success : function(wishlist){
				$("#myPageDiv").html(wishlist);
			},
			error : function(xhr){
				alert("상태 : " + xhr.status);
			},
			dataType : "html"
		})
	})
	
	//----------------------------------

	// 설아씨가 바꿔달라함
	$("#selectMemberlist").on("click", function(){
	$.ajax({
		url : "<%=request.getContextPath() %>/admin/selectMemberlist.do",
			type : "get",
			data : {
				page : 1
			},
			success : function(selectMemberlist){
				$("#myPageDiv").html(selectMemberlist);
			},
			error : function(xhr){
				alert("상태 : " + xhr.status);
			},
			dataType : "html"
		})
	})
	// 여기까지
	
	$("#report").on("click", function(){
		$.ajax({
			url : "<%=request.getContextPath() %>/admin/report.do",
			type : "get",
			success : function(report){
				$("#myPageDiv").html(report);
			},
			error : function(xhr){
				alert("상태 : " + xhr.status);
			},
			dataType : "html"
		})
	})
	$("#cartList").on("click", function(){
		$.ajax({
			url : "<%=request.getContextPath() %>/admin/cartList.do",
			type : "get",
			success : function(cartList){
				$("#myPageDiv").html(cartList);
			},
			error : function(xhr){
				alert("상태 : " + xhr.status);
			},
			dataType : "html"
		})
	})
	$("#serviceApprovedlist").on("click", function(){
		$.ajax({
			url : "<%=request.getContextPath() %>/admin/serviceApprovedlist.do",
			type : "get",
			success : function(serviceApprovedlist){
				$("#myPageDiv").html(serviceApprovedlist);
			},
			error : function(xhr){
				alert("상태 : " + xhr.status);
			},
			dataType : "html"
		})
	})
	$("#serviceApprovedConfirmlist").on("click", function(){
		$.ajax({
			url : "<%=request.getContextPath() %>/admin/serviceApprovedConfirmlist.do",
			type : "get",
			success : function(serviceApprovedConfirmlist){
				$("#myPageDiv").html(serviceApprovedConfirmlist);
			},
			error : function(xhr){
				alert("상태 : " + xhr.status);
			},
			dataType : "html"
		})
	})
	$("#serviceCategory").on("click", function(){
		$.ajax({
			url : "<%=request.getContextPath() %>/admin/serviceCategory.do",
			type : "get",
			success : function(serviceCategory){
				$("#myPageDiv").html(serviceCategory);
			},
			error : function(xhr){
				alert("상태 : " + xhr.status);
			},
			dataType : "html"
		})
	})
	$("#serviceApprovedConfirmlist").on("click", function(){
		$.ajax({
			url : "<%=request.getContextPath() %>/admin/serviceApprovedConfirmlist.do",
			type : "get",
			success : function(serviceApprovedConfirmlist){
				$("#myPageDiv").html(serviceApprovedConfirmlist);
			},
			error : function(xhr){
				alert("상태 : " + xhr.status);
			},
			dataType : "html"
		})
	})
	$("#serviceApprovedConfirmlist").on("click", function(){
		$.ajax({
			url : "<%=request.getContextPath() %>/admin/serviceApprovedConfirmlist.do",
			type : "get",
			success : function(serviceApprovedConfirmlist){
				$("#myPageDiv").html(serviceApprovedConfirmlist);
			},
			error : function(xhr){
				alert("상태 : " + xhr.status);
			},
			dataType : "html"
		})
	})
	
	
$('#serviceInsert').on("click",function(){
	location.href="<%= request.getContextPath()%>/view/service/serviceInsert.jsp";
});



myservicelist = function(){
	$.ajax({
		url : "<%= request.getContextPath()%>/service/myServiceList.do?mem_no=<%=loginMemberInfo.getMem_no()%>",
		type : "get",
		success : function(myservicelist){
			$('#myPageDiv').html(myservicelist);
		},
		error : function(xhr){
			alert(" 내 서비스 리스트 상태" + xhr.status);
		},
		dataType : "html"
	})
}

//내 서비스 리스트 
$('#myServiceList').on('click',function(){
	myservicelist();
}) 


$('#myWishList').on('click',function(){
	
	$.ajax({
		url : '<%= request.getContextPath()%>/service/myWishlist.do',
		data : { "memNo" : '26' },
		type : 'get',
		success : function(res){
			code="";
			if(res!="찜 목록이 없습니다."){
				$.each(res,function(i,v){
					code += "<a href='<%= request.getContextPath()%>/service/serviceDetail.do?serviceNo=" + v.service_no +"' 서비스디테일";
					code += "<p>서비스 번호:"+ v.service_no +"[서비스이름:]"+v.service_name+"[서비스가격:]"+v.service_price+"</a>";
	  				code += "<button type='button' id='mywishDelete' value='"+v.service_no+"'>찜 삭제</button></p>"; 
				}) 
			}else{
				code = "<p>"+res+"</p>";
			}
			$('#myWish').html(code);
		},
		error : function(xhr){
			alert("에러"+xhr.status)
		},
		dataType : 'json'
	}) //ajax끝
}) //내 위시리스트 가져오기 끝

$(document).on('click','#mywishDelete',function(){
	$.ajax({
		url : '<%= request.getContextPath()%>/service/memWishlist.do',
		data : { "countWishlist" : "1" ,
				 "memNo" : "26",
				 "serviceNo" : $(this).val() },
		type : 'get',
		success : function(res){
			if(res==1){
					alert("성공");
			}
		},
		error : function(xhr){
			alert("상태 : "+xhr.status);
		},
		dataType : 'text'
	})
})
	
})
</script>
<body>
<div class="sidebar pe-4 pb-3">
    <nav class="navbar bg-light navbar-light">
        
		<div>
			<label style="color: white;">moram<br/>moram<br/>moram<br/>moram<br/></label>
		</div>
        <div class="navbar-nav w-100" style="margin-left: 10%;">

        
      <%if("일반회원".equals(gradeName)){ %>
        	<button id="toggle" name="toggle" class="btn btn-primary w-100 m-2" type="button" style="background: darkturquoise;border: teal;">⇔ <%=toggleBtn%> 전환</button>
            <a class="nav-item nav-link" id="updateMemberInfo"><i class="fa fa-user-circle"></i>회원 정보 변경</a>
            <%if(existExpert != 1) {%>
            <a class="nav-item nav-link" id="enrollExpert"><i class="fa fa-user-circle"></i>전문가 정보 등록</a>
            <%} %>
            <a class="nav-item nav-link" id="chat"><i class="fa fa-comments"></i>채팅내역</a>
            <a class="nav-item nav-link myBoard"><i class="fa fa-keyboard"></i>작성한 게시글</a>
            <a class="nav-item nav-link schedule"><i class="fa fa-calendar"></i>일정</a>
            <a class="nav-item nav-link serviceHistory" ><i class="fa fa-credit-card"></i>이용서비스 내역</a>
            <a class="nav-item nav-link" id="wishlist"><i class="fa fa-cart-arrow-down"></i>위시리스트</a>
            <hr/>
            <a href="<%=request.getContextPath()%>/member/logout.do" class="nav-item nav-link"><i class="fa fa-plug"></i>로그아웃</a>
            <hr/>
            <a class="nav-item nav-link" id="withdrawal"><i class="fa fa-times-circle"></i>회원탈퇴</a>
            
      <%}else if("전문가".equals(gradeName)){ %>
        	<button id="toggle" name="toggle" class="btn btn-primary w-100 m-2" type="button" style="background: darkturquoise;border: teal;">⇔ <%=toggleBtn%> 전환</button>
            <a class="nav-item nav-link" id="updateMemberInfo"><i class="fa fa-user-circle"></i>회원 정보 변경</a>
            <a class="nav-item nav-link" id="chat"><i class="fa fa-comments"></i>채팅내역</a>
            <a class="nav-item nav-link  myBoard"><i class="fa fa-keyboard"></i>작성한 게시글</a>
            <a class="nav-item nav-link schedule"><i class="fa fa-calendar"></i> 일정</a>
            <a href="<%=request.getContextPath()%>/service/serviceInsert.do" class="nav-item nav-link"><i class="fa fa-plus-circle"></i>서비스 등록</a>
            <a class="nav-item nav-link" id="myServiceList"><i class="fa fa-chart-bar"></i>내 서비스</a>
            <hr/>
            <a href="<%=request.getContextPath()%>/member/logout.do" class="nav-item nav-link"><i class="fa fa-plug"></i>로그아웃</a>
            <hr/>
            <a class="nav-item nav-link" id="withdrawal"><i class="fa fa-times-circle"></i>회원탈퇴</a>
    
      <%}else if("관리자".equals(gradeName)){ %>
            <i class="fa fa-cogs"></i> 회원 관리
            <a class="nav-item nav-link" id="selectMemberlist"><i class="fa fa-address-book"></i>목록 조회</a>
            <a class="nav-item nav-link" id="report"><i class="fa fa-bomb"></i>신고 조회</a>
            <hr/>
            <i class="fa fa-cogs"></i> 서비스 관리
            <a class="nav-item nav-link" id="cartList"><i class="fa fa-keyboard"></i>결제 조회</a>
            <a class="nav-item nav-link" id="serviceApprovedlist"><i class="fa fa-pause"></i>승인 대기</a>
            <a class="nav-item nav-link" id="serviceApprovedConfirmlist"><i class="fa fa-th-list"></i>승인 리스트</a>
            <hr/>
            <i class="fa fa-cogs"></i> 게시판 관리
            <a class="nav-item nav-link" href="<%=request.getContextPath() %>/board/setSession.do?boardCategoryNo=1&page=1" class="dropdown-item"><i class="fa fa-bullhorn"></i>공지사항</a>
            <a class="nav-item nav-link" href="<%=request.getContextPath() %>/board/setSession.do?boardCategoryNo=2&page=1" class="dropdown-item"><i class="fa fa-question"></i>문의게시판</a> 
            <a class="nav-item nav-link" href="<%=request.getContextPath() %>/board/setSession.do?boardCategoryNo=3&page=1" class="dropdown-item"><i class="fa fa-thumbs-up"></i>후기게시판</a>
            <a class="nav-item nav-link" href="<%=request.getContextPath() %>/board/setSession.do?boardCategoryNo=4&page=1" class="dropdown-item"><i class="fa fa-bullhorn"></i>홍보게시판</a>
            <a class="nav-item nav-link" href="<%=request.getContextPath() %>/board/setSession.do?boardCategoryNo=5&page=1" class="dropdown-item"><i class="fa fa-paper-plane"></i>자유게시판</a>
            <hr/>
            <a href="<%=request.getContextPath()%>/member/logout.do" class="nav-item nav-link"><i class="fa fa-plug"></i>로그아웃</a>
      <%} %>
      
      
      
      
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