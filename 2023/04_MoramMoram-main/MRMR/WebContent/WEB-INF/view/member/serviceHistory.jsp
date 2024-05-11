<%@page import="java.util.Map"%>
<%@page import="vo.CartVO"%>
<%@page import="java.util.List"%>
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
<link href="<%=request.getContextPath() %>/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

<!-- Customized Bootstrap Stylesheet -->
<link href="<%=request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">

<!-- Template Stylesheet -->
<link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet">

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.6.1.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
$(function(){
	$("tbody tr").on("click", function(){
		location.href = "<%=request.getContextPath()%>/service/serviceDetail.do?serviceNo=" + $("#cartNo").text();
	});
	$("#searchMy").on("click", function(){
		vstype = $('#stype').val();
		vsword = $('#sword').val();
		location.href = "<%=request.getContextPath()%>/member/myBoard.do?page=1&stype=" + vstype + "&sword=" + vsword;
	});
   $('.pnums').on('click', function() {
		currentpage = parseInt($(this).text().trim());
		location.href = "<%=request.getContextPath()%>/member/myBoard.do?page="+currentpage; 
	});
});
</script>
</head>
<% // 여기서 보여줘야 하는건 서비스 이용내역(서비스 이름, 날짜, 주문상태)
List<Map<String, Object>> cartList = (List<Map<String, Object>>) request.getAttribute("cartList");
%>
<body>

<section class="content boardList" style="width: 100%;">
			<form name="boardForm" id="boardForm" action="<%=request.getContextPath()%>/member/myBoard.do?page=1">
				<div class="card">
					<div class="card-header">
						<div class="card-tools">
							<div class="input-group input-group-sm" style="width: 440px; float: right; padding-top: 20px;">
								<select id="stype" class="form-control stype">
									<option value="service_name">제목</option>
								</select> 
								<input type="text" id="sword" name="table_search" class="form-control float-right" placeholder="Search">
								<div class="input-group-append">
									<button type="button" id="searchMy" class="btn btn-default">
										<i class="fas fa-search"></i>검색
									</button>
								</div>
							</div>
						</div>
						<h3 class="card-title">서비스 이용 내역</h3>
					</div>
					<!-- card-header   -->
	
					<div class="card-body">
						<table id="boardList" class="table table-bordered">
							<thead>
								<tr>
									<th style="width: 6%; text-align: center;">번호</th>
									<th style="width: px; text-align: center;">서비스</th>
									<th style="width: 20%; text-align: center;">이용날짜</th>
									<th style="width: 10%; text-align: center;">상태</th>
									<th style="width: 10%; text-align: center;">회원</th>
								</tr>
							</thead>
							<tbody>
								<%
								if (cartList != null || cartList.size() > 0) {
									for(Map<String, Object> cart : cartList){
								%>
								<tr>
									<td id="cartNo"><%=cart.get("CART_NO")%></td>
									<td><%=cart.get("SERVICE_NAME")%></td>
									<td><%=cart.get("CART_DATE")%></td>
									<td><%=cart.get("CART_STATE_NAME")%></td>
									<td><%=cart.get("MEM_NAME")%></td> 
								</tr>
								<%
									}
								} else {
								%>
								<tr>
									<td colspan="5">서비스 이용내역이 없습니다.</td>
								</tr>
								<%
									}
								%>
							</tbody>
						</table>
					</div>
					<!-- card-body -->
					<div class="card-footer clearfix">
						<ul class="pagination pagination-sm m-0 float-right">
							<%
							int startpage = (int)request.getAttribute("startpage"); //spage
							int endpage = (int)request.getAttribute("endpage"); //epage
							int totalpage = (int)request.getAttribute("totalpage"); //tpage
							for(int i = startpage; i<= endpage; i++){ %>
								<li class="page-item"><a class="page-link pnums" href="#"><%=i %></a></li>
							<%} %>
						</ul>
					</div>
				</div>
			</form>
		</section>
</body>
</html>