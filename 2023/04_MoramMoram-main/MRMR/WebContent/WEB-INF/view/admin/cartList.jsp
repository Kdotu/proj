<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.Map"%>
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
	<%-- 	$("tr").on("click", function(){
		location.href = "<%=request.getContextPath()%>/board/Read.do?board_no=" + $("#boardNo").text();
	});
	$("#searchMy").on("click", function(){
		vstype = $('#stype').val();
		vsword = $('#sword').val();
		location.href = "<%=request.getContextPath()%>/member/myBoard.do?page=1&stype=" + vstype + "&sword=" + vsword;
	});
   $('.pnums').on('click', function() {
		currentpage = parseInt($(this).text().trim());
		location.href = "<%=request.getContextPath()%>/member/myBoard.do?page="+currentpage; 
	});--%>
	
	
	$('#searchCartList').on('click',function(){
		vstype = $('#stype').val();
		vsword = $('#sword').val();
		location.href = "<%=request.getContextPath()%>/admin/cartList.do?stype="+ vstype + "&sword=" + vsword; 
	})
}); 

</script>
</head>
<%
	List<Map<String, Object>> cartList = (List<Map<String, Object>>) request.getAttribute("cartList");
%>
<body>
	<section class="content boardList" style="width: 100%;">
			<%-- <form name="boardForm" id="boardForm" action="<%=request.getContextPath()%>/member/myBoard.do?page=1"> --%>
				<div class="card">
					<div class="card-header">
						<div class="card-tools">
							<div class="" style="width: 440px; float: right; padding-top: 20px;">
								<select class="form-select" id="stype" aria-label="Floating label select example" style="width:120px; float:right; height:35px;">
									<option selected value="m.mem_no">회원번호</option>
									<option value="m.mem_name">회원명</option>
									<option value="c.service_no">서비스번호</option>
                               	</select>
								<input type="text" id="sword" name="table_search" class="form-control float-right" placeholder="Search" style="width : 250px; display : inline-block; float : right;height:35px;">
								<button type="button" id="searchCartList" class="btn btn-default" style="border:1px solid #999; float : right;">
									<i class="fas fa-search"></i>검색
								</button>
							</div>
						</div>
						<h3 class="card-title">회원 결제내역</h3>
					</div>
					<!-- card-header   -->
	
					<div class="card-body">
						<table id="cartList" class="table table-bordered contentTable">
							<thead>
								<tr>
									<th style="width: 7%; text-align: center;">주문번호</th>
									<th style="width: 7%; text-align: center;">회원번호</th>
									<th style="width: 10%; text-align: center;">회원명</th>
									<th style="width: 9%; text-align: center;">서비스번호</th>
									<th style="width: 20%; text-align: center;">서비스명</th>
									<th style="width: 10%; text-align: center;">서비스가격</th>
									<th style="width: 10%; text-align: center;">주문일자</th>
								</tr>
							</thead>
							<tbody>
								<%
								if (cartList != null || cartList.size() > 0) {
								for (Map<String, Object> cart : cartList) {
									
								    DecimalFormat formatter = new DecimalFormat("###,###,###,###");
								    int price = Integer.parseInt(String.valueOf(cart.get("SERVICE_PRICE")));
								    String servicePrice = formatter.format(price);
								%>
								<tr class="contentTr">
	                                <td id="cartNo"><%=cart.get("CART_NO")%></td>
									<td><%=cart.get("MEM_NO")%></td>
									<td><%=cart.get("MEM_NAME")%></td>
									<td><%=cart.get("SERVICE_NO")%></td>
									<td><a href="<%= request.getContextPath() %>/service/serviceDetail.do?serviceNo=<%=cart.get("SERVICE_NO")%>"><%=cart.get("SERVICE_NAME")%></a></td>
									<td><%=servicePrice%>원</td>
									<td><%=cart.get("CART_DATE")%></td> 
	                             </tr>
								<%
									}
								} else {
								%>
								<tr>
									<td colspan="6">결제 내역이 없습니다.</td>
								</tr>
								<%
									}
								%>
							</tbody>
						</table>
					</div>
					<!-- card-body -->
				<%-- 	<div class="card-footer clearfix">
						<ul class="pagination pagination-sm m-0 float-right">
							<%
							int startpage = (int)request.getAttribute("startpage"); //spage
							System.out.println("startpage" + startpage);
							
							int endpage = (int)request.getAttribute("endpage"); //epage
							System.out.println("endpage" +endpage);
							
							int totalpage = (int)request.getAttribute("totalpage"); //tpage
							System.out.println("totalpage" +totalpage);
							
							for(int i = startpage; i<= endpage; i++){ %>
								<li class="page-item"><a class="page-link pnums" href="#"><%=i %></a></li>
							<%} %>
						</ul>
					</div> --%>
				</div>
<!-- 			</form> -->
		</section>
	

</body>
</html>