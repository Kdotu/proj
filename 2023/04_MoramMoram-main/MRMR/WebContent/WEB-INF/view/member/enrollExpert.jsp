<%@page import="java.util.Map"%>
<%@page import="vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
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

<style type="text/css">
	table, tr, td { border:1px solid blue; border-collapse: collapse; }
	td { padding:5px;}
</style>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.6.1.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
$(function(){
	
});

</script>
</head>
<%
Map<String, Object> loginInfo = (Map<String, Object>)session.getAttribute("loginInfo");
MemberVO loginMemberInfo = (MemberVO)loginInfo.get("memberInfo");
%>
<body>
	<form action="<%=request.getContextPath()%>/member/enrollExpert.do" method="post">
    <div class="container-xxl position-relative bg-white d-flex p-0">
        <!-- Sign Up Start -->
        <div class="container-fluid">
            <div class="row h-100 align-items-center justify-content-center" style="min-height: 100vh;">
                <div class="col-sm-8">
                    <div class="bg-light rounded p-4 p-sm-5 my-4 mx-3">
                        <div class="d-flex align-items-center justify-content-between mb-3">
                            <a href="http://localhost/MRMR/main.jsp" class="">
                                <h3 class="text-primary"><i class="fa fa-hashtag me-2"></i>MORAM</h3>
                            </a>
                            <h3>전문가 정보 입력</h3>
                        </div>
                        <div class="form-floating mb-3">
							<textarea class="form-control" name="expert_career" id="expert_career"></textarea><br>
                            <label for="floatingText">경력</label>
                        </div>           
						<div class="form-floating mb-3">
                            <textarea class="form-control" name="expert_info" id="expert_info"></textarea><br>
                            <label for="floatingText">소개</label>
                        </div>
                        <div class="form-floating mb-3">
                            <textarea class="form-control" name="expert_contact" id="expert_contact"></textarea><br>
                            <label for="floatingText">연락가능시간?</label>
                        </div>
                        <div class="form-floating mb-3">
                            <textarea class="form-control" name="expert_license" id="expert_license"></textarea><br>
                            <label for="floatingInput">자격증</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" name="expert_education" id="expert_education"><br>
                            <label for="floatingInput">학력</label>
                        </div>
                        <input type="hidden" name="mem_no" value="<%=loginMemberInfo.getMem_no()%>">
		                <div class="form-floating mb-3" style="text-align: center;">
							<button type="submit" class="btn btn-primary m-2">저장</button>
							<button type="reset" class="btn btn-primary m-2">취소</button>
						</div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Sign Up End -->
    </div>
	</form>
	
    <!-- JavaScript Libraries -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

    <!-- Template Javascript -->
    <script src="js/main.js"></script>
	
</body>
</html>