<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Google Web Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@700&display=swap" rel="stylesheet">

<!-- Icon Font Stylesheet -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

<!-- Libraries Stylesheet -->
<link href="<%=request.getContextPath() %>/lib/animate/animate.min.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

<!-- Customized Bootstrap Stylesheet -->
<link href="<%=request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">

<!-- Template Stylesheet -->
<link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet">
  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/public.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/slick/slick.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/slick/slick-theme.css">
<script src="<%=request.getContextPath() %>/slick/slick.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath() %>/js/public.js" type="text/javascript" charset="utf-8"></script>

<link href='<%=request.getContextPath() %>/packages/core/main.css' rel='stylesheet' />
<link href='<%=request.getContextPath() %>/packages/daygrid/main.css' rel='stylesheet' />
<link href='<%=request.getContextPath() %>/packages/timegrid/main.css' rel='stylesheet' />
<script src='<%=request.getContextPath() %>/packages/core/main.js'></script>
<script src='<%=request.getContextPath() %>/packages/interaction/main.js'></script>
<script src='<%=request.getContextPath() %>/packages/daygrid/main.js'></script>
<script src='<%=request.getContextPath() %>/packages/timegrid/main.js'></script>
<script src = '<%=request.getContextPath() %>/js/jquery-3.6.1.min.js'></script>
<script>
$(function(){
	$("#selectReprot").on("click", function(){
		$.ajax({
			url : "<%=request.getContextPath()%>/member/selectReportList.do",
			date : {reportCategoryNo : $("selectReport :selected").val()},
			type : "get",
			success : function(res){
				$("#view").empty();
				code = "<table border='1'>";
				code +="<thead>";
				code +="<tr>";
				code +="<td>번호</td><td>신고일자</td><td>신고내용</td><td>신고받은 회원</td><td>신고한 회원</td><td></td>";
				code +="</tr>";
				code +="</thead>";
				code +="<tbody>";
				code +="$.each(res, function(i, v){";
				code +="<tr>";
				code +="	<td>v.report_no</td><td>v.report_date</td><td>v.report_content</td><td>v.mem_no_bad</td><td>v.mem_no_do</td>";
				code +="	<td><input type=''</td>";
				code +="	</tr>";
				code +="})";
				code +="</tbody></table>";
				$("#view").html(code);
			},
			error : function(xhr){
				alert("상태 : " + xhr.status);
			},
			dataType : "json"
		})
	})
	
})
</script>
</head>
<%
List<Map<String, Object>> reportCategoryList = (List<Map<String, Object>>) request.getAttribute("reportCategoryList");
%>
<body>
	<jsp:include page="../../../header.jsp"></jsp:include>
	<h2>블랙리스트 관리</h2>
	<select id="selectReport">
	<%
	for(Map<String, Object> reprotCategory : reportCategoryList){
	%>
		<option value="<%=reprotCategory.get("REPORT_CATEGORY_NO")%>"><%=reprotCategory.get("REPORT_CATEGORY_NAME")%></option>
	<%}%>
	</select>

	<div id="view"></div>
<jsp:include page="../../../footer.jsp"></jsp:include>
</body>
</html>