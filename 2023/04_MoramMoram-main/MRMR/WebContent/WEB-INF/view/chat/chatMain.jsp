<%@page import="java.util.ArrayList"%>
<%@page import="java.lang.reflect.Array"%>
<%@page import="vo.ChatVO"%>
<%@page import="java.util.List"%>
<%@page import="vo.MemberVO"%>
<%@page import="java.util.Map"%>
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
<link href="<%=request.getContextPath() %>img/favicon.ico" rel="icon">

<!-- Google Web Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin="">
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@700&display=swap" rel="stylesheet">

<!-- Icon Font Stylesheet -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

<!-- Libraries Stylesheet -->
 <link href="<%=request.getContextPath() %>lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
 <link href="<%=request.getContextPath() %>/lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/lib/animate/animate.min.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

<!-- Customized Bootstrap Stylesheet -->
<link href="<%=request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">

<!-- Template Stylesheet -->
<link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet">

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/slick/slick.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/slick/slick-theme.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/style/public.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/style/chat.css">
<script src="https://code.jquery.com/jquery-2.2.0.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/slick/slick.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath() %>/js/chat.js" type="text/javascript" charset="utf-8"></script>
<!-- 링크 끝 -->
</head>
<script type="text/javascript">
<%
Map<String, Object> loginInfo = (Map<String, Object>)session.getAttribute("loginInfo");
MemberVO loginMemberInfo = (MemberVO)loginInfo.get("memberInfo");
List<ChatVO> getTargetList = (List<ChatVO>) request.getAttribute("getTargetList");
List<Integer> memNo = new ArrayList<Integer>();
%>
</script>
<body>

<div class="content col-sm-1 w-85 mx-3" style="overflow: auto;">
	
	<div id="chatList" class="h-100 bg-light rounded chatList" style="display: inline-block; width: 20%; overflow: auto;">
	
	<div class="d-flex align-items-center justify-content-between mb-2">
		<h6 class="mb-0">Messages</h6>
	</div>
		
	<%
	if(getTargetList!=null || getTargetList.size() > 0){
		for(ChatVO list : getTargetList){
			if(list.getMem_no_sender() == loginMemberInfo.getMem_no()){
			%>
			
			<div chatNo="<%=list.getMem_no_sender() %>" target="<%=list.getMem_no_target() %>" class="d-flex align-items-center border-bottom py-3" onclick="chatWinOpen(this);">
				<img class="rounded-circle flex-shrink-0" src="<%=request.getContextPath() %>/files/imageView.do?mem_no=<%=list.getMem_no_target() %>&file_category_no=4" alt="" style="width: 50px; height: 40px;">
				<div class="w-100 ms-3">
					<div class="d-flex w-100 justify-content-between">
						<h6 class="mb-0"><%=list.getMem_target_name() %></h6>
					</div>
					<span>chatList</span>
				</div>
			</div>
			
			<%}	else {%>
				<div chatNo="<%=list.getMem_no_target() %>" target="<%=list.getMem_no_sender() %>" class="d-flex align-items-center border-bottom py-3" onclick="chatWinOpen(this);">
					<img class="rounded-circle flex-shrink-0" src="<%=request.getContextPath() %>/files/imageView.do?mem_no=<%=list.getMem_no_sender() %>&file_category_no=4" alt="" style="width: 50px; height: 40px;">
					<div class="w-100 ms-3">
						<div class="d-flex w-100 justify-content-between">
							<h6 class="mb-0"><%=list.getMem_sender_name() %></h6>
						</div>
						<span>chatList</span>
					</div>
				</div>
			<%}
			
		}
	}%>
	</div>
	
	
	<div style="width: 80%; float: right; height: 600px;">
		<div id="chatspace" class="h-100 bg-light rounded p-4 chatList" style="height: 500px;">
			<!--  채팅내역 -->
			<div id=chatWindow class="h-100 bg-light rounded p-4 chatList" style="overflow: auto;height: 90%;"></div>
			<!--  채팅하단 -->
			<div class="h-10 bg-light rounded p-4 chatText" style="width: 100%; text-align: right;">
				<input type="text" id="chatMessage" onkeyup="enterKey()" style="width: 80%;"/>
				<button type="button" id="sendBtn" class="btn btn-link rounded-pill m-2" onclick="sendMessage()">전송</button>
				<button type="button" id="listBtn" class="btn btn-link rounded-pill m-2" onclick="chatWinClose()">목록</button>
			</div>
		</div>
	</div>
</div>
</body>
</html>