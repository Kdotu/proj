<%@page import="vo.MemberVO"%>
<%@page import="java.util.Map"%>
<%@page import="vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>MORAM MORAM</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta content="" name="description">
<link href="<%=request.getContextPath() %>/img/favicon.ico" rel="icon">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@700&display=swap" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/lib/animate/animate.min.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
<script src='<%=request.getContextPath()%>/js/jquery-3.6.1.min.js'></script>
<style type="text/css">
textarea{
   resize: none;
}
</style>
</head>
<body>
<%
   BoardVO boardVo = (BoardVO)request.getAttribute("boardVo");
   Map<String, Object> loginInfo = (Map<String, Object>)session.getAttribute("loginInfo");
   MemberVO loginMemberInfo = (MemberVO)loginInfo.get("memberInfo");
   int categoryNo = (int)session.getAttribute("boardCategoryNo");
%>
   <!-- header.jsp include -->
   <jsp:include page="../../../header.jsp"></jsp:include>
    <div class="container-xxl position-relative bg-white d-flex p-0">
        <div class="content">
            <div class="container-fluid pt-4 px-4">
            <div class="col-sm-12">
               <div class="bg-light rounded h-100 p-4" style="width: 1300px;">
                  <form name="boardForm" id="boardForm" action="<%=request.getContextPath()%>/board/Insert.do" method="post" enctype="multipart/form-data">
                     <input type="hidden" name="mem_no" value="<%=loginMemberInfo.getMem_no()%>">
                     <input type="hidden" name="board_date" value="sysdate">
                     <input type="hidden" name="board_hit" value="0">
                     <h6 class="mb-4"></h6>
                     <div class="form-floating mb-3">
                        <input type="text" class="form-control" id="floatingInput" name="board_title" placeholder="제목을 입력해주세요." style="width:100%;">
                        <label for="floatingInput">제목</label>
                     </div>
                     <div class="form-floating">
                                <textarea class="form-control" placeholder="내용을 입력하세요" id="floatingTextarea" name="board_content" style="width:100%; height: 500px;"></textarea>
                                <label for="floatingTextarea">내용</label>
                            </div>
                     <div class="mb-3">
                        <label for="formFile" class="form-label">파일업로드</label>
                        <input class="form-control" type="file" id="formFile" name="files" >
                     </div>
                     <input type="submit" id ="btnUpdate" class="btn btn-info"  value = "등록">
                  </form>
               </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="<%=request.getContextPath()%>/lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/main.js"></script>
   
   <!--    footer.jsp include -->
   <jsp:include page="../../../footer.jsp"></jsp:include>
</body>
</html>