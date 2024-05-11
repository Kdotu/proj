<%@page import="java.text.DecimalFormat"%>
<%@page import="vo.MemberVO"%>
<%@page import="java.util.Map"%>
<%@page import="vo.ServiceVO"%>
<%@page import="java.util.List"%>
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
<link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;500&family=Roboto:wght@500;700&display=swap" rel="stylesheet">
    
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
</head>
<style>
.table table-sm{
	height: 100%;
}
</style>
<body>
	<jsp:include page="../../../serviceSidebar.jsp"></jsp:include>
   	<jsp:include page="../../../header.jsp"></jsp:include>

    <div class="container-xxl position-relative bg-white d-flex p-0 container-center-align">

        <!-- Content Start -->
        <div class="content col-sm-1 w-85 mx-3">
                <div class="bg-light text-center rounded p-4" style="width: 1300px;">
               <div class="main-panel">
                       <div class="content-wrapper">
                         <div class="row">
                           <div class="col-xl grid-margin stretch-card">
                             <div class="card">
                               <div class="card-body border-bottom">
                                 <div class="d-flex justify-content-between align-items-center flex-wrap">
                                   <h3 class="mb-2 mb-md-0 text-uppercase font-weight-medium">Service List</h3>
                                   
                                 </div>
                               </div>
                            </div>
                               <%  
                              List<ServiceVO> serviceList = (List<ServiceVO>)request.getAttribute("serviceList");
                              List<ServiceVO> serviceSubList = (List<ServiceVO>)request.getAttribute("serviceSubList");
                              
                      	      DecimalFormat formatter = new DecimalFormat("###,###,###,###");
                              
                           %>
                             <div class="card">
                               <div class="card-body border-bottom imageList">
                                     <%
                                    if(serviceList==null || serviceList.size()==0){
                                       for(ServiceVO serviceVO : serviceSubList){
                                 	      int price = serviceVO.getService_price();
                                	      String service_price = formatter.format(price);
                                    	   
                                    	   
                                 %>    
									<div id="serviceContent1" class="serviceContents">
										<a href="<%= request.getContextPath() %>/service/serviceDetail.do?serviceNo=<%= serviceVO.getService_no()%>">
											 <img alt="profileImage" id="preview" style="width: 100%; height: 250px; padding:5px;" src="<%=request.getContextPath() %>/files/imageView.do?file_category_no=3&col=SERVICE_NO&no=<%=serviceVO.getService_no()%>">
											<div class="serviceName"><%= serviceVO.getService_name() %></div>	
											<div class="servicePrice"><%= service_price %>원</div>
										</a>
									</div>
                                 <%
                                       }
                                    }else{
                                       for(ServiceVO serviceVO : serviceList){
                                  	      int price = serviceVO.getService_price();
                                 	      String service_price = formatter.format(price);
                                    	   
                                 %>      
                                    <div id="serviceContent1" class="serviceContents">
										<a href="<%= request.getContextPath() %>/service/serviceDetail.do?serviceNo=<%= serviceVO.getService_no()%>">
											 <img alt="profileImage" id="preview" style="width: 100%; height: 250px; padding:5px;" src="<%=request.getContextPath() %>/files/imageView.do?file_category_no=3&col=SERVICE_NO&no=<%=serviceVO.getService_no()%>">
											<div class="serviceName"><%= serviceVO.getService_name() %></div>	
											<div class="servicePrice"><%=service_price%>원</div>
										</a>
									</div>
                                 <%
                                       }
                                    }
                                 %>   
                               </div>
                               </div>
							</div>   
	                    </div>
	               </div>                       
	              </div>
	           </div>
   
           <!-- Back to Top -->
           <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top" style="width: 50px;"><i class="bi bi-arrow-up"></i></a>
       </div>
       </div>
       
     
 	<!-- 	footer.jsp include -->
	<jsp:include page="../../../footer.jsp"></jsp:include>

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