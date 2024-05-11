<%@page import="vo.MemberVO"%>
<%@page import="java.util.Map"%>
<%@page import="vo.CartVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.datatables.net/t/bs-3.3.6/jqc-1.12.0,dt-1.10.11/datatables.min.css"/> 
<script src="https://cdn.datatables.net/t/bs-3.3.6/jqc-1.12.0,dt-1.10.11/datatables.min.js"></script>

<!-- Favicon -->
    <link href="img/favicon.ico" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@700&display=swap" rel="stylesheet">
    
    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="<%=request.getContextPath()%>/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

    <!-- Customized Bootstrap Stylesheet -->
    <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet">

<title>Insert title here</title>

</head>
<% 
List<Map<String, Object>> expertServiceHistoryList = (List<Map<String, Object>>) request.getAttribute("expertServiceHistory");
%>

<body>
<h2>서비스 내역</h2>
<table id="serviceTable" class="table table-bordered">
	<thead>
		<tr>
			<td>번호</td><td>서비스 이름</td><td>서비스 날짜</td><td>서비스 받은 회원</td><td>상태</td><td>가격</td>
		</tr>
	</thead>
	<tbody>
<%
if(expertServiceHistoryList == null || expertServiceHistoryList.size() == 0){
%>
	<tr><td colspan="6">서비스 내역이 없습니다</td></tr>
<%
}else{
	for(Map<String, Object> expertServiceHistory : expertServiceHistoryList){
%>
	<tr>
		<td><%=expertServiceHistory.get("CART_NO")%></td>
		<td><%=expertServiceHistory.get("SERVICE_NAME")%></td>
		<td><%=expertServiceHistory.get("CART_DATE")%></td>
		<td><%=expertServiceHistory.get("MEM_NAME")%></td>
		<td><%=expertServiceHistory.get("CART_STATE_NAME")%></td>
		<td><%=expertServiceHistory.get("SERVICE_PRICE")%></td>
	</tr>
<%
	}
}
%>
	</tbody>
</table>
<input type="button" id="yearBtn" value="년">
<div class="container-xxl position-relative bg-white d-flex p-0" >
<!-- Content Start -->
	<div class="content">
		<!-- Chart Start -->
		<div class="container-fluid pt-4 px-4">
			<div class="row g-4">	
				<div class="col-sm-12 col-xl-6">
					<div class="bg-light rounded h-100 p-4">
						<h6 class="mb-4">Single Bar Chart</h6>
						<canvas id="bar-chart"></canvas>
					</div>
				</div>
			
				<div class="col-sm-12 col-xl-6">
					<div class="bg-light rounded h-100 p-4">
						<h6 class="mb-4">Pie Chart</h6>
						<canvas id="pie-chart"></canvas>
					</div>
				</div>
			
			</div>
		</div>
		<!-- Chart End -->
	</div>
</div>

    <!-- JavaScript Libraries -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="<%=request.getContextPath()%>/lib/chart/chart.min.js"></script>
    <script src="<%=request.getContextPath()%>/lib/easing/easing.min.js"></script>
    <script src="<%=request.getContextPath()%>/lib/waypoints/waypoints.min.js"></script>
    <script src="<%=request.getContextPath()%>/lib/owlcarousel/owl.carousel.min.js"></script>
    <script src="<%=request.getContextPath()%>/lib/tempusdominus/js/moment.min.js"></script>
    <script src="<%=request.getContextPath()%>/lib/tempusdominus/js/moment-timezone.min.js"></script>
    <script src="<%=request.getContextPath()%>/lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>
</body>
</html>