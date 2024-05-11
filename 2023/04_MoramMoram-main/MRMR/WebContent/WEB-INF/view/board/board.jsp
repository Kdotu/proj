
<%@page import="vo.MemberVO"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="vo.BoardVO"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="kr">
<head>
<meta charset="utf-8">
<title>MORAM MORAM</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta content="" name="description">
<!--       Favicon   -->
<link href="<%=request.getContextPath() %>/img/favicon.ico" rel="icon">

<!--       Google Web Fonts   -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@700&display=swap" rel="stylesheet">

<!--       Icon Font Stylesheet   -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

<!--       Libraries Stylesheet   -->
<link href="<%=request.getContextPath() %>/lib/animate/animate.min.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

<!--       Customized Bootstrap Stylesheet   -->
<link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">

<!--       Template Stylesheet   -->
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet">
<script src='<%=request.getContextPath()%>/js/jquery-3.6.1.min.js'></script>
</head>
<%  
List<BoardVO> boardList = (List<BoardVO>) request.getAttribute("boardList");
int categoryNo = (int)session.getAttribute("boardCategoryNo");
MemberVO memberVo = new MemberVO();

String boardCategory = "";
if(categoryNo == 1){
	boardCategory = "공지사항";
}else if(categoryNo == 2){
	boardCategory = "문의게시판";
}else if(categoryNo == 3){
	boardCategory = "후기게시판";
}else if(categoryNo == 4){
	boardCategory = "홍보게시판";
}else if(categoryNo == 5){
	boardCategory = "자유게시판";
}
%>
<style>
.content boardList{
	width: width: calc(100% - 1%);
}
</style>
<script>  
$(function(){
   $('.prev').on('click', function() {
		currentpage = parseInt($('.pnums').first().text().trim()) - 1;
		location.href = "<%=request.getContextPath()%>/board/List.do?page="+currentpage; 
	});
   
   $('.pnums').on('click', function() {
		currentpage = parseInt($(this).text().trim());
		location.href = "<%=request.getContextPath()%>/board/List.do?page="+currentpage; 
	});
   
   $('.next').on('click', function() {
		currentpage = parseInt($('.pnums').last().text().trim()) + 1;
		location.href = "<%=request.getContextPath()%>/board/List.do?page="+currentpage; 
	});
   
   $("#searchMy").on("click", function(){
		vstype = $('#stype').val();
		vsword = $('#sword').val();
		location.href = "<%=request.getContextPath()%>/board/List.do?page=1&stype=" + vstype + "&sword=" + vsword;
	});
});
</script>
<body>
	<!-- header.jsp include -->
	<jsp:include page="../../../header.jsp"></jsp:include>
	
	<div class="container-xxl position-relative bg-white d-flex p-0">
		<!-- boardSidebar.jsp include -->
		<jsp:include page="../../../boardSidebar.jsp"></jsp:include>
		
		<section class="content boardList" style="width: 100%;">
			<form name="boardForm" id="boardForm">
				<div class="card">
					<div class="card-header">
						<h3 class="card-title"><%=boardCategory %></h3>
					</div>
					<!-- card-header   -->
	
					<div class="card-body">
						<table class="table table-bordered">
							<thead>
								<tr>
									<th style="width: 6%; text-align: center;">번호</th>
									<th style="width: px; text-align: center;">제목</th>
									<th style="width: 12%; text-align: center;">작성자</th>
									<th style="width: 12%; text-align: center;">작성일</th>
									<th style="width: 10%; text-align: center;">조회수</th>
								</tr>
							</thead>
							<tbody>
								<%
								if (boardList != null || boardList.size() > 0) {
								for (BoardVO boardVo : boardList) {
									
								int boardInfo = boardVo.getMem_no();
								%>
								<tr class ="boardInfo" writer ="<%=boardVo.getMem_no() %>" idx="<%=boardVo.getBoard_no()%>">
	                                <td id="<%=boardVo.getMem_no() %>"><%=boardVo.getBoard_no()%></td>
	                                <td><%=boardVo.getBoard_title()%></td>
	                                <td><%=boardVo.getMem_name()%></td>
	                                <td><%=boardVo.getBoard_date()%></td>
	                                <td><%=boardVo.getBoard_hit()%></td>
	                             </tr>
								<%
									}
								} else {
								%>
								<tr>
									<td colspan="5">작성된 게시글이 없습니다.</td>
								</tr>
								<%
									}
								%>
							</tbody>
						</table>
					</div>
					<div class="card-body">
						<input type="button" id="addBtn" value="글쓰기" class="btn btn-success float-right" style="float: right;">
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
	</div>

    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="<%=request.getContextPath()%>/lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/main.js"></script>
    
	<!-- 	footer.jsp include -->
	<jsp:include page="../../../footer.jsp"></jsp:include>
</body>

<script>
<%
	Map<String, Object> loginInfo = (Map<String, Object>) session.getAttribute("loginInfo");
	boolean check = (boolean)session.getAttribute("isLogined");
	if(check){
		memberVo = (MemberVO)loginInfo.get("memberInfo");
	}
	boolean isWriter = true;	// 글쓰기에 대한 권한   true가 권한이 있다.
	
	if(categoryNo == 1){			// 공지사항
		if(memberVo.getMem_grade() != 9){		// 관리자가 아닌애들 
			isWriter = false;		// 관리자가 아니면 글쓰기 권한이 없다.%>
			$("#addBtn").hide();
		<%}%>
		$(function(){
			$("#addBtn").on("click", function(){
				if(<%=check%>){
					if(<%=isWriter%>){
						location.href = "<%=request.getContextPath()%>/board/Insert.do"; 
					}else{
						alert("접근권한이 없습니다.")
					}
				}else{
					alert("로그인이 필요합니다.");
					location.href = "<%=request.getContextPath()%>/member/login.do"; 
				}
			});
			
			$(".boardInfo").on("click", function(){
				boardNo = $(this).attr("idx");
				location.href = "<%=request.getContextPath()%>/board/Read.do?board_no=" + boardNo+"&memNo="+$(this).attr("writer");
			});
		});
	
	<%}else if(categoryNo == 2){		// 문의게시판
			if(memberVo.getMem_grade() == 9){		// 관리자가 아닌애들 
				isWriter = false;		// 관리자가 아니면 글쓰기 권한이 없다.
			}%>
		$(function(){
			$("#addBtn").on("click", function(){
				if(<%=check%>){
					if(<%=isWriter%> || <%=memberVo.getMem_grade() ==9%>){
						location.href = "<%=request.getContextPath()%>/board/Insert.do"; 
					}else{
						alert("접근권한이 없습니다.")
					}
				}else{
					alert("로그인이 필요합니다.");
					location.href = "<%=request.getContextPath()%>/member/login.do"; 
				}
			});
	
			$(".boardInfo").on("click", function(){
				if(<%=check%>){
					boardNo = $(this).attr("idx");
					if(<%=memberVo.getMem_no()%> == $(this).attr("writer") || <%=memberVo.getMem_grade()%> ==9 ) {
						location.href = "<%=request.getContextPath()%>/board/Read.do?board_no=" + boardNo+"&memNo="+$(this).attr("writer");
					}else{
						alert("회원님이 작성하신 글이 아닙니다.");
					}
				}else{
					alert("비회원 접근 권한이 없습니다.");
				}
			});
		});<%
	}else if(categoryNo == 3){			// 후기게시판
		if(memberVo.getMem_grade() == 9){		// 관리자가 아닌애들 
			isWriter = false;		// 관리자가 아니면 글쓰기 권한이 없다.
		}
		%>
		$(function(){
			$("#addBtn").on("click", function(){
				if(<%=check%>){
					if(<%=isWriter%> || <%=memberVo.getMem_grade()%> ==9 ) {
						location.href = "<%=request.getContextPath()%>/board/Insert.do"; 
					}else{
						alert("접근권한이 없습니다.");
					}
				}else{
					alert("로그인이 필요합니다.");
					location.href = "<%=request.getContextPath()%>/member/login.do"; 
				}
			});
			
			$(".boardInfo").on("click", function(){
				boardNo = $(this).attr("idx");
				location.href = "<%=request.getContextPath()%>/board/Read.do?board_no=" + boardNo+"&memNo="+$(this).attr("writer");
			});
		});
	<%}else if(categoryNo == 4){		// 홍보 게시판
		if(memberVo.getMem_grade() != 1){		// 관리자가 아닌애들 
			isWriter = false;		// 전문가가 아니면 글쓰기 권한이 없다.
		}
		%>
		
		$(function(){
			$("#addBtn").on("click", function(){
				if(<%=check%>){
					if(<%=isWriter%> || <%=memberVo.getMem_grade()%> ==9 ) {
						location.href = "<%=request.getContextPath()%>/board/Insert.do"; 
					}else{
						alert("전문가만 글을 쓸 수 있습니다.")
					}
				}else{
					alert("로그인이 필요합니다.");
					location.href = "<%=request.getContextPath()%>/member/login.do"; 
				}
			});
			$(".boardInfo").on("click", function(){
				boardNo = $(this).attr("idx");
				location.href = "<%=request.getContextPath()%>/board/Read.do?board_no=" + boardNo+"&memNo="+$(this).attr("writer");
			});
		});
	 <%}else if(categoryNo == 5){			//자유게시판
		if(memberVo.getMem_grade() == 9){		// 관리자가 아닌애들 
			isWriter = false;		// 관리자가 아니면 글쓰기 권한이 없다.
		}%>
		
		$(function(){
			$("#addBtn").on("click", function(){
				if(<%=check%>){
					if(<%=isWriter%> || <%=memberVo.getMem_grade()%> ==9 ) {
						location.href = "<%=request.getContextPath()%>/board/Insert.do"; 
					}else{
						alert("접근권한이 없습니다.");
					}
				}else{
					alert("로그인이 필요합니다.");
					location.href = "<%=request.getContextPath()%>/member/login.do"; 
				}
			});
			
			$(".boardInfo").on("click", function(){
				boardNo = $(this).attr("idx");
				location.href = "<%=request.getContextPath()%>/board/Read.do?board_no=" + boardNo+"&memNo="+$(this).attr("writer");
			});
		});
<%}%>
</script>
</html>
