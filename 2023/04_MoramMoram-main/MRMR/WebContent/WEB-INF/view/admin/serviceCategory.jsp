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

<!-- modal -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
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
	}); --%>
	
	$('#action').on('click',function(){
	 	 $('#exampleModal').show();
	 	 
	 	 // 선택이 되면
	 	 $('.category_sort').on('click',function(){
	 		 sortName = $(this).val() + "";
	 		 
	 		 if(sortName == "service_category_sub"){
	 			 console.log('소분류를 누름')
	 			 $('#modalMainInput').attr('style','display:none;');
	 			 $('#modalSubInput').attr('style','display:block;');
	 		 }
	 		 if(sortName =="service_category_main"){
	 			 $('#modalMainInput').attr('style','display:block;');
	 			 $('#modalSubInput').attr('style','display:none;');
	 		 }
	 	 })
		 $.ajax({
			url : '<%=request.getContextPath() %>/service/selectMainCategory.do',
			type : 'get',
			success : function(res){
				code="";
				$.each(res,function(i,v){
					code += "<option value='" + v.service_category_main_no + "'>" + v.service_category_main_name + "</option>";
				})
				$('#mainCategoryName').html(code);
				$('#mainCategoryName').trigger('change');
				
			},
			error : function(xhr){
				alert("action 상태" + xhr.status);
			},
			dataType : 'json'
		 })//ajax끝
	})
	
	
	$('#categoryAdd').on('click',function(){
		mainName = $('#service_category_main_name').val()+"";
		subName = $('#service_category_sub_name').val()+"";
		mainNo = $('#mainCategoryName option:selected').val()+"";
		
		console.log(mainName + " : " + subName + " : " + mainNo)
		
		
		location.href = "<%=request.getContextPath()%>/admin/serviceCategory.do?mainName="+mainName+"&subName="+subName+"&mainNo="+mainNo;
	})
})


</script>
</head>
<%
List<Map<String, Object>> categoryList = (List<Map<String, Object>>) request.getAttribute("categoryList");
%>
<body>

	<section class="content boardList" style="width: 100%;">
			<form name="categoryForm" id="categoryForm" action="">
				<div class="card">
					<div class="card-header">
						<div class="card-tools">
							<div class="" style="width: 440px; float: right; padding-top: 20px;">
		                		<div class="mb-3">
									<button type="button" class="btn btn-primary btn" data-bs-toggle="modal" data-bs-target="#exampleModal" id="action">
									  서비스 카테고리 등록
									</button>
				                    <input type="text" class="form-control" id="servicecategory" aria-describedby="emailHelp" required disabled>
				                    <input type="hidden" id="service_category_sub_no" name="service_category_sub_no"> 
				                </div>
							</div>
							</div>
						</div>
						<h3 class="card-title">카테고리 관리</h3>
					</div>
					<!-- card-header   -->
	
					<div class="card-body" style="align-content: center;">
						<table id="category	List" class="table table-bordered" style="width:80%; text-align:center;">
							<thead>
								<tr>
									<th colspan="2"style="width: 12%; text-align: center;">대분류명</th>
									<th colspan="2" style="width: 12%; text-align: center;">소분류명</th>
								</tr>
							</thead>
							<tbody>
								<%
								if (categoryList != null || categoryList.size() > 0) {
								for (Map<String, Object> category : categoryList) {
								%>
								<tr>
									<%-- <td><%=category%></td> --%>
	                                
	                                <td id="categoryListNo"><%=category.get("MAIN_NAME")%></td>
	                                <td></td>
                   					<td><%=category.get("SUB_NAME")%></td>
	                                <td>
	                                </td>
	                             </tr>
	                             <tr>
	                             </tr>
								<%
									}
								} else {
								%>
								<tr>
									<td colspan="4">등록된 카테고리가  없습니다.</td>
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
			</form>
		</section>
		
		
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">서비스 카테고리 등록창</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <form action="" method="post">
	      <div class="modal-body">
	      <input type="radio" class="category_sort" name="category_sort" value="service_category_main" checked>대분류
	      <input type="radio" class="category_sort" name="category_sort" value="service_category_sub">소분류
	      
	      <div id="modalMainInput" style="display:block;">
	      	<input type="text" id="service_category_main_name" placeholder="카테고리 대분류명 입력...">
	      </div>
	      
	      <div id="modalSubInput" style="display:none;">
		      <select class="form-select mb-3" aria-label="Default select example" id="mainCategoryName">
		      </select>
	      	<input type="text" id="service_category_sub_name" placeholder="카테고리 소분류명 입력...">
	      </div>
	      
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
	        <button type="button" class="btn btn-primary" id="categoryAdd">카테고리 추가</button>
     	 </div>
      </form>
    </div>
  </div>
</div>

</body>
</html>