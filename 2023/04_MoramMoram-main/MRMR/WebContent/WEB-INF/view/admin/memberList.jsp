<%@page import="vo.MemberVO"%>
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

</head>
<%
List<Map<String, Object>> memberlist = (List<Map<String, Object>>)request.getAttribute("memberlist");
%>
<script>  
$(function(){
   $('.prev').on('click', function() {
		currentpage = parseInt($('.pnums').first().text().trim()) - 1;
		location.href = "<%=request.getContextPath()%>/admin/selectMemberlist.do?page="+currentpage; 
	});
   
   $('.pnums').on('click', function() {
		currentpage = parseInt($(this).text().trim());
		location.href = "<%=request.getContextPath()%>/admin/selectMemberlist.do?page="+currentpage; 
	});
   
   $('.next').on('click', function() {
		currentpage = parseInt($('.pnums').last().text().trim()) + 1;
		location.href = "<%=request.getContextPath()%>/admin/selectMemberlist.do?page="+currentpage; 
	});
   
   $("#searchMy").on("click", function(){
		vstype = $('#stype').val();
		vsword = $('#sword').val();
		location.href = "<%=request.getContextPath()%>/admin/selectMemberlist.do?page=1&stype=" + vstype + "&sword=" + vsword;
	});
   
	$("tbody tr").on("click", function(){
		if(confirm("블랙리스트로 변경하시겠습니까?")){	
			memNo = $(this).find("td").eq(0).text();
			location.href = "<%=request.getContextPath()%>/admin/memGradeChange.do?memNo=" + memNo;
		}
	});
	
});
</script>
<body>

	<section class="content boardList" style="width: 100%;">
		<div class="card">
			<div class="card-header">
				<div class="card-tools">
					<div class="input-group input-group-sm" style="width: 440px; float: right; padding-top: 20px;">
						<select id="stype" class="form-control stype">
							<option value="MEM_NO">회원번호</option>
							<option value="MEM_NAME">이름</option>
							<option value="MEM_ID">아이디</option>
						</select> 
						<input type="text" id="sword" name="table_search" class="form-control float-right" placeholder="Search">
						<div class="input-group-append">
							<button type="button" id="searchMy" class="btn btn-default">
								<i class="fas fa-search"></i>검색
							</button>
						</div>
					</div>
				</div>
				<h3 class="card-title">회원목록</h3>
			</div>
			<!-- card-header   -->
	
			<div class="card-body">
				<table id="boardList" class="table table-bordered contentTable">
					<thead>
						<tr class="contentTr">
							<th style="width: 6%; text-align: center;">회원번호</th>
							<th style="width: 12%; text-align: center;">회원이름</th>
							<th style="width: 12%; text-align: center;">회원아이디</th>
							<th style="width: 10%; text-align: center;">회원이메일</th>
							<th style="width: 10%; text-align: center;">회원전화번호</th>
							<th style="width: 10%; text-align: center;">구분</th>
							<th style="width: 10%; text-align: center;">신고횟수</th>
							<th style="width: 10%; text-align: center;">관리</th>
						</tr>
					</thead>
					<tbody>
					
						<%if (memberlist != null || memberlist.size() > 0) {
							for (Map<String, Object> member : memberlist) {%>
							<tr class="contentTr">
	                             <td onclick="event.cancelBubble=true"><%= member.get("MEM_NO")%></td>
	                             <td onclick="event.cancelBubble=true"><%= member.get("MEM_NAME")%></td>
	                             <td onclick="event.cancelBubble=true"><%= member.get("MEM_ID")%></td>
	                             <td onclick="event.cancelBubble=true"><%= member.get("MEM_EMAIL")%></td>
	                             <td onclick="event.cancelBubble=true"><%= member.get("MEM_HP")%></td>
	                             <td onclick="event.cancelBubble=true"><%= member.get("MEM_GRADE")%></td>
	                             <td onclick="event.cancelBubble=true"><%= member.get("REPORT_COUNT")%></td>
	                             <td>등급변경</td>
	                        </tr>
							<%}
						} else {
						%>
						<tr class="contentTr">
							<td colspan="6">회원이 없습니다.</td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
			</div>
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
</section>


    <!-- JavaScript Libraries -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="lib/chart/chart.min.js"></script>
    <script src="lib/easing/easing.min.js"></script>
    <script src="lib/waypoints/waypoints.min.js"></script>
    <script src="lib/owlcarousel/owl.carousel.min.js"></script>
    <script src="lib/tempusdominus/js/moment.min.js"></script>
    <script src="lib/tempusdominus/js/moment-timezone.min.js"></script>
    <script src="lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

    <!-- Template Javascript -->
    <script src="js/main.js"></script>
</body>
</html>