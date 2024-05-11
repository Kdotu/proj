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
		serviceNo = $(this).find("td").eq(0).text();
		location.href = "<%=request.getContextPath()%>/service/serviceDetail.do?serviceNo=" + serviceNo;
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


	//서비스삭제
	$('.serviceDelete').on('click',function(){
		if ( confirm("삭제하시겠습니까?") ) { 
			val = $(this).parents("tr").find("td").eq(0).text();
			console.log(val);
			$.ajax({
				url : '<%= request.getContextPath()%>/service/serviceDelete.do',
				type : 'get',
				data : { "serviceNo" : val },
				success : function(res){
					if(res=='1'){
						alert('삭제가 완료되었습니다.');
					}
					myservicelist();
				},
				error : function(xhr){
					alert("상태" + xhr.status);				
				},
				dataType : 'text'
			})
		} else {
		    alert("취소를 누르셨습니다.");
		    // 취소 클릭시 false 가 리턴 되어 실행​    
		} 
	}) //서비스삭제끝
	
	//서비스 수정
	$('.serviceUpdate').on('click',function(){
		
		if(confirm("수정하시겠습니까?")){
			val = $(this).parents("tr").find("td").eq(0).text();
			location.href= '<%= request.getContextPath() %>/service/serviceUpdate.do?serviceNo='+ val;
		}else{
			alert("취소를 누르셨습니다.")
		}
	}) 
});
</script>
</head>
<%
List<ServiceVO> myServiceList = (List<ServiceVO>) request.getAttribute("myServiceList");
%>
<body>

   <section class="content boardList" style="width: 100%;">
      <div class="card">
         <div class="card-header">
            <h3 class="card-title">내 서비스</h3>
         </div>
         <!-- card-header   -->

         <div class="card-body">
            <table id="boardList" class="table table-bordered contentTable">
               <thead>
                  <tr class="contentTr">
                     <th style="width: 5%; text-align: center;">번호</th>
                     <th style="width: 20%; text-align: center;">카테고리</th>
                     <th style="width: px; text-align: center;">서비스</th>
                     <th style="width: px; text-align: center;">서비스수정</th>
                     <th style="width: px; text-align: center;">서비스삭제</th>
                     <th style="width: 10%; text-align: center;">가격</th>
                     <th style="width: 10%; text-align: center;">승인</th>
                     <th style="width: 10%; text-align: center;">승인일자</th>
                  </tr>
               </thead>
               <tbody>
                  <%
                  if (myServiceList != null || myServiceList.size() > 0) {
                  for (ServiceVO service : myServiceList) {
                  %>
                  <tr class="contentTr">
                     <td class="serviceNo" style="text-align: center;"><%=service.getService_no()%></td>
                     <td><%=service.getService_category_main_name()%> - <%=service.getService_category_sub_name() %></td>
                     <td><%=service.getService_name()%></td>
                     <td onclick="event.cancelBubble=true"><button type="button" class="btn btn-outline-dark m-2 serviceUpdate">수정</button></td>
                     <td onclick="event.cancelBubble=true"><button type="button" class="btn btn-outline-dark m-2 serviceDelete">삭제</button></td>
                     <%DecimalFormat formatter = new DecimalFormat("###,###,###,###");
                      int price = service.getService_price();
                      String service_price = formatter.format(price);%>
                     <td style="text-align: right;"><%=service_price%>원</td>
                     <%int check = service.getService_approved_no();
                     String approved = "미승인";
                     if(check == 1 ){
                        approved="승인";
                     }%>
                     <td style="text-align:center;"><%=approved%></td>
                     <%if(check==1){ %>
                        <td style="text-align:center;"><%=service.getService_approved_date()%></td>
                     <%}else{ %>
                    	 <td style="text-align:center;">미승인</td>
                     <%} %>
                  </tr>
                  <%}
                  } else { %>
                  <tr>
                     <td colspan="8">작성된 게시글이 없습니다.</td>
                  </tr>
                  <%
                     }
                  %>
               </tbody>
            </table>
         </div>
         <!-- card-body -->

      </div>
   </section>
</body>
</html>