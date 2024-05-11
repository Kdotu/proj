<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="vo.CartVO"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="vo.ServiceVO"%>
<%@page import="java.util.List"%>
<%@page import="service.service.IServiceService"%>
<%@page import="service.service.ServiceServiceImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>MORAM MORAM</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">
    <!-- Favicon -->
    <link href="img/favicon.ico" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600;700&display=swap" rel="stylesheet">
    
    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="<%= request.getContextPath() %>/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

    <!-- Customized Bootstrap Stylesheet -->
    <link href="<%= request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="<%= request.getContextPath() %>/css/style.css" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/css/jquery.dataTables.min.css" rel="stylesheet">
    <script src = '<%= request.getContextPath() %>/js/jquery-3.6.1.min.js'></script>
    <%
    	// 주문 정보를 갖고 있는 cartVO, 결제한 서비스 정보를 가진 serviceVO
 // 	 	CartVO cvo = (CartVO)request.getAttribute("cartVO");
    	ServiceVO serviceVO = (ServiceVO)request.getAttribute("serviceVO"); 
    	
	    DecimalFormat formatter = new DecimalFormat("###,###,###,###");
	    int price = serviceVO.getService_price();
	    String service_price = formatter.format(price);
	    
	    Date today = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyymmdd");
		String cartNum = sdf.format(today) + "";
		
		for(int i = 0; i < 10; i ++) {
			cartNum += (char)((int)(Math.random()*26)+65);
		}
		System.out.println("check : " + cartNum);
    %>
    
</head>
<body>
	<jsp:include page="../../../header.jsp"></jsp:include>
	<jsp:include page="../../../serviceSidebar.jsp"></jsp:include>
	
	
        <div class="content col-sm-1 w-85 mx-3">
            <div class="container-fixed pt-4 px-4 container-center-align">
                <div class="bg-light text-center rounded p-4">
                    <div class="d-flex align-items-center justify-content-between border-bottom pb-3 mb-4">
                        <h6 class="mb-1"><b>결제내역</b></h6>
                    </div>
                    
					<div class="main-panel">
					        <div class="content-wrapper">
					          <div class="row g-4">
					            <div class="col-sm grid-margin stretch-card">
					              <div class="card">
					                <div class="card-body border-bottom">
      					              <div class="card" style="text-align : left">
					                	<div class="card-body border-bottom" style="text-align:left;"> <!--구역 나눔선-->
							                <div id="serviceInfo" class="container mb-4" style="width : 100%;">
							                	<img alt="profileImage" id="preview" style="width: 100%; height: 500px;" src="<%=request.getContextPath() %>/files/imageView.do?file_category_no=3&col=SERVICE_NO&no=<%=serviceVO.getService_no()%>">
							                	<div class="col-xl" style="padding-left : 30px;">
							                		<br>
							                		<br>
							                		<h3>주문내역</h3><br>
							                		<div><b>주문번호 : </b></div>
							                		<p><%=cartNum %></p><br>
							                		
							                		<div><b>상품명 : </b></div>
							                		<p><%=serviceVO.getService_name() %></p><br>
							                		
							                		<div><b>결제금액 : </b></div>
							                		<p><%=service_price %>원</p><br>
							                		
							                	</div>
							                </div>
							                <br>
							                
			                			</div>
			                		</div>
					                
					                <a href="<%= request.getContextPath() %>/member/myPage.do"><input type="button" class="btn btn-outline-primary m-2" value="구매내역 확인하러 가기"></a>
					                <a href="<%= request.getContextPath() %>/service/serviceDetail.do?serviceNo=<%= serviceVO.getService_no()%>"><input type="button" class="btn btn-outline-primary m-2" value="서비스 계속 둘러보기"></a>
					                <a href="<%= request.getContextPath() %>/service/categoryMain.do?main_no=0&page=1"><input type="button" class="btn btn-outline-primary m-2" value="다른 서비스 둘러보기"></a>
					                
					                
			                        <!-- <a href="">Show All</a> -->
					                
					                </div>
				                </div>

					           </div>
							</div>				           
			           </div>
		           </div>
			        <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
			    </div>
		    </div>
	    </div>
     </div>
</body>
</html>