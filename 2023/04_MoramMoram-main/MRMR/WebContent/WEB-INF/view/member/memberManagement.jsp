<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="<%=request.getContextPath() %>/img/favicon.ico" rel="icon">

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
<script src="<%=request.getContextPath() %>/js/public.js" type="text/javascript" charset="utf-8"></script>

<link href='<%=request.getContextPath() %>/packages/core/main.css' rel='stylesheet' />
<link href='<%=request.getContextPath() %>/packages/daygrid/main.css' rel='stylesheet' />
<link href='<%=request.getContextPath() %>/packages/timegrid/main.css' rel='stylesheet' />
<script src='<%=request.getContextPath() %>/packages/core/main.js'></script>
<script src='<%=request.getContextPath() %>/packages/interaction/main.js'></script>
<script src='<%=request.getContextPath() %>/packages/daygrid/main.js'></script>
<script src='<%=request.getContextPath() %>/packages/timegrid/main.js'></script>
<script src = '<%=request.getContextPath() %>/js/jquery-3.6.1.min.js'></script>
</head>

<body>
<jsp:include page="/header.jsp"></jsp:include>
<h2>회원관리</h2>
<ul>
	<li><a href="<%=request.getContextPath()%>/member/blackListManagement.do">블랙리스트 관리</a></li>
	<li><a href="<%=request.getContextPath()%>/member/serviceApproved.do">서비스 등록 승인</a></li>
	<li><a href="<%=request.getContextPath()%>/member/serviceCategoryManagement.do">서비스 카테고리 관리</a></li>
	<li><a href="<%=request.getContextPath()%>/member/salesStatistics.do">매출 통계</a></li>
</ul>
<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>