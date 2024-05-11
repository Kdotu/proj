<%@page import="java.text.DecimalFormat"%>
<%@page import="vo.ServiceVO"%>
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
<link href="img/favicon.ico" rel="icon">

<!-- Google Web Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;500&family=Roboto:wght@500;700&display=swap" rel="stylesheet">

<!-- Icon Font Stylesheet -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

<!-- Libraries Stylesheet -->
<link href="<%=request.getContextPath()%>/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/lib/animate/animate.min.css" rel="stylesheet">
<!-- Customized Bootstrap Stylesheet -->
<link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">

<!-- Template Stylesheet -->
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/slick/slick.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/slick/slick-theme.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style/public.css">
<script src="<%=request.getContextPath()%>/slick/slick.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/js/public.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.6.1.min.js"></script>
</head>
<%
List<Map<String, Object>> Wishlist = (List<Map<String, Object>>) request.getAttribute("wishList");
%>
<script>
<%-- $(function(){
	$("#deleteWish").on("click", function(){
		idx = $(this).parent().attr(idx);
		location.href="<%=request.getContextPath()%>/member/deleteWish.do?wish_no=" + $('#boardNo').text();
	})
}) --%>

$("tbody tr").on("click", function(){
	serviceNo = $(this).find("td").eq(1).text();
	location.href = "<%=request.getContextPath()%>/service/serviceDetail.do?serviceNo=" + serviceNo;
});
</script>
<body>

	<section class="content boardList" style="width: 100%;">
			<form name="boardForm" id="boardForm" action="<%=request.getContextPath()%>/member/myBoard.do?page=1">
				<div class="card">
					<div class="card-header">
						<h3 class="card-title">위시리스트</h3>
					</div>
					<!-- card-header   -->
	
					<div class="card-body">
						<table id="boardList" class="table table-bordered">
							<thead>
								<tr>
									<th style="width: 6%; text-align: center;">번호</th>
									<th style="width: 6%; text-align: center;">서비스 번호</th>
									<th style="width: px; text-align: center;">이름</th>
									<th style="width: 10%; text-align: center;">가격</th>
									<th style="width: 10%; text-align: center;">전문가</th>
									<th style="width: 10%; text-align: center;">승인여부</th>
		<!-- 							<th style="width: 10%; text-align: center;">서비스삭제</th> -->
								</tr>
							</thead>
							<tbody>
								<%
								if (Wishlist != null || Wishlist.size() > 0) {
									for (Map<String, Object> list : Wishlist) {
								%>
								<tr>
	                                <td style="text-align: center;"><%=list.get("WISH_NO")%></td>
	                                <td style="text-align: center;"><%=list.get("SERVICE_NO")%></td>
									<td><%=list.get("SERVICE_NAME") %></td>
									<td style="text-align: center;"><%=list.get("SERVICE_PRICE")%>원</td>
									<td style="text-align: center;"><%=list.get("MEM_NAME")%></td>
									<td style="text-align: center;"><%=list.get("SERVICE_APPROVED")%></td>
<!-- 								 	<td><button type='button' id='deleteWish' value='"+v.service_no+"'>서비스삭제</button></td>  -->
	                             </tr>
								<%
									}
								} else {
								%>
								<tr>
									<td colspan="4">찜목록이 없습니다.</td>
								</tr>
								<%
									}
								%>
							</tbody>
						</table>
					</div>
					<!-- card-body -->

				</div>
			</form>
		</section>

</body>
</html>