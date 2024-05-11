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
	
	$('.serviceApproved').on('click',function(){
		
		$.ajax({
			url : '<%= request.getContextPath()%>/admin/serviceApproved.do',
			data : { "serviceNo" : $(this).val() },
			success : function(res){
				if(res==1){
					alert("승인 성공");
				}else{
					alert("승인 실패");
				}
				location.href="<%=request.getContextPath() %>/member/myPage.do";
			},
			error : function(xhr){
				alert("실패"+xhr.status);
			},
			dataType : 'text'
		})
	})
	
	
});
</script>
</head>
<%
List<Map<String,Object>> serviceApprovedlist = (List<Map<String,Object>>)request.getAttribute("serviceApprovedlist");
%>
<body>

	<section class="content boardList" style="width: 100%;">
			<form name="boardForm" id="boardForm" action="">
				<div class="card">
					<div class="card-header">
						<div class="card-tools">
							<div class="input-group input-group-sm" style="width: 440px; float: right; padding-top: 20px;">
								<!-- <select id="stype" class="form-control stype">
									<option value="board_title">제목</option>
									<option value="board_content">내용</option>
								</select>  -->
								<!-- <input type="text" id="sword" name="table_search" class="form-control float-right" placeholder="Search"> -->
								<!-- <div class="input-group-append">
									<button type="button" id="searchMy" class="btn btn-default">
										<i class="fas fa-search"></i>검색
									</button>
								</div> -->
							</div>
						</div>
						<h3 class="card-title">서비스 승인 대기</h3>
					</div>
					<!-- card-header   -->
	
					<div class="card-body">
						<table id="boardList" class="table table-bordered contentTable">
							<thead>	
								<tr class="contentTr">
									<th style="width: 6%; text-align: center;">서비스등록번호</th>
									<th style="width: 12%; text-align: center;">서비스카테고리</th>
									<th style="width: 12%; text-align: center;">서비스이름</th>
									<th style="width: 10%; text-align: center;">서비스승인</th>
								</tr>
							</thead>
							<tbody>
								<%
								if (serviceApprovedlist != null || serviceApprovedlist.size() > 0) {
								for (Map<String,Object> map : serviceApprovedlist) {
								%>
								
								<tr class="contentTr">
	                                <td><%=map.get("SERVICE_NO")%></td>
									<td><%=map.get("SERVICE_CATEGORY_SUB_NAME")%></td>
									<td><a href="<%= request.getContextPath() %>/service/serviceDetail.do?serviceNo=<%= map.get("SERVICE_NO")%>"><%=map.get("SERVICE_NAME")%></a></td>
									<td><button type="button" class="serviceApproved" value="<%= map.get("SERVICE_NO")%>">서비스승인</button></td>
	                             </tr>
								<%
									}
								} else {
								%>
								<tr>
									<td colspan="4">승인하실 서비스가 없습니다.</td>
								</tr>
								<%
									}
								%>
							</tbody>
						</table>
					</div>
					<!-- card-body -->
<%-- 					<div class="card-footer clearfix">
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
			</form>
		</section>
	

</body>
</html>