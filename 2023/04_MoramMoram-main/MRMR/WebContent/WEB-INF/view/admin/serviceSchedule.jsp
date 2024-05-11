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
	$("tr").on("click", function(){
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
	});
});
</script>
</head>
<%
List<Map<String, Object>> myBoardList = (List<Map<String, Object>>) request.getAttribute("boardList");
%>
<body>

	<section class="content boardList" style="width: 100%;">
			<form name="boardForm" id="boardForm" action="<%=request.getContextPath()%>/member/myBoard.do?page=1">
				<div class="card">
					<div class="card-header">
						<div class="card-tools">
							<div class="input-group input-group-sm" style="width: 440px; float: right; padding-top: 20px;">
								<select id="stype" class="form-control stype">
									<option value="board_title">제목</option>
									<option value="board_content">내용</option>
								</select> 
								<input type="text" id="sword" name="table_search" class="form-control float-right" placeholder="Search">
								<div class="input-group-append">
									<button type="button" id="searchMy" class="btn btn-default">
										<i class="fas fa-search"></i>검색
									</button>
								</div>
							</div>
						</div>
						<h3 class="card-title">내가 쓴 글</h3>
					</div>
					<!-- card-header   -->
	
					<div class="card-body">
						<table id="boardList" class="table table-bordered">
							<thead>
								<tr>
									<th style="width: 6%; text-align: center;">번호</th>
									<th style="width: 12%; text-align: center;">게시판 종류</th>
									<th style="width: 12%; text-align: center;">제목</th>
									<th style="width: 10%; text-align: center;">작성일</th>
								</tr>
							</thead>
							<tbody>
								<%
								if (myBoardList != null || myBoardList.size() > 0) {
								for (Map<String, Object> board : myBoardList) {
								%>
								<tr>
	                                <td id="boardNo"><%=board.get("BOARD_NO")%></td>
									<td><%=board.get("BOARD_CATEGORY_NAME")%></td>
									<td><%=board.get("BOARD_TITLE")%></td>
									<td><%=board.get("BOARD_DATE")%></td>
	                             </tr>
								<%
									}
								} else {
								%>
								<tr>
									<td colspan="4">작성된 게시글이 없습니다.</td>
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
							System.out.println("startpage" + startpage);
							
							int endpage = (int)request.getAttribute("endpage"); //epage
							System.out.println("endpage" +endpage);
							
							int totalpage = (int)request.getAttribute("totalpage"); //tpage
							System.out.println("totalpage" +totalpage);
							
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