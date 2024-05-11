<%@page import="vo.FilesVO"%>
<%@page import="java.util.List"%>
<%@page import="vo.BoardCommentVO"%>
<%@page import="vo.MemberVO"%>
<%@page import="java.util.Map"%>
<%@page import="vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>모람모람</title>
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
<style type="text/css">
 #boardModifyForm{ 
 	display: none; 
 } 
 
input idx{
	width : calc(12% - 1%);
	height:
	float : right;
}
</style>
<%
	BoardVO boardVo = (BoardVO) request.getAttribute("boardInfo");
	List<BoardCommentVO> boardCommentList = (List<BoardCommentVO>) request.getAttribute("boardCommentList");
	List<BoardCommentVO> boardReCommentList = (List<BoardCommentVO>) request.getAttribute("boardReCommentList");
	
	boolean isWriter = true;
	int categoryNo = (int)session.getAttribute("boardCategoryNo");
	
	boolean isLogin = (boolean)session.getAttribute("isLogined");
	
	MemberVO memberVo = null;
	Map<String, Object> loginInfo = null;
	if(isLogin){
		loginInfo = (Map<String, Object>) session.getAttribute("loginInfo");
		memberVo = (MemberVO)loginInfo.get("memberInfo");
	}
%>
<script>
$(function(){
	$("#btnUpdate").on("click", function() {
		var form = document.getElementById("detailForm");
		form.method = "GET";
		form.action = "<%=request.getContextPath()%>/board/Update.do";
		form.submit();
	});
	
	$("#btnDelete").on("click", function(){
		var form = document.getElementById("detailForm");
		form.action = "<%=request.getContextPath()%>/board/Delete.do";
		form.submit();
	});
	
	$("#btnList").on("click", function(){
		var form = document.getElementById("detailForm");
		form.action = "<%=request.getContextPath()%>/board/List.do";
		form.submit();
	});
	
	$("#addReply").on("click",function(){
		var form = document.getElementById("boardCommentForm");
		form.method = "GET";
		form.action = "<%=request.getContextPath()%>/board/InsertComment.do";
		form.submit();
	});
	
	$(document).on('click', '.action', function(){	// 댓글 수정, 삭제
		 vidx =  $(this).attr('idx');
		 vname =  $(this).attr('name');
		 p2 = $(".p2").html();
		 if(vname == "r_modify"){  //댓글 수정 메서드
			if( $('#boardModifyForm').css('display') != "none"){
		    	replyReset();
			}
			p3tag =  $(this).parents('.p4').find('.p3');
			
			modicont = $(p3tag).text().trim();  //br태그가 있다 
			mcont = modicont.replace(/<br>/g, "\n");
			$('#boardModifyForm #text').val(mcont);
			$(p3tag).empty();
			$(p3tag).append($('#boardModifyForm'));
			$('#boardModifyForm').show();
			
		}else if(vname == "r_delete"){		// 댓글 삭제 메서드
			$.ajax({
				url : '<%=request.getContextPath()%>/board/ReplyDelete.do',
				data : { "reNo" : vidx },
				type : 'get',
				success : function(res){
					if(res=="1"){
						location.href="/MRMR/board/Read.do?board_no=<%=boardVo.getBoard_no()%>&memNo=<%=boardVo.getMem_no()%>";
					}
				},
				error : function(xhr){
					alert("상태 : " + xhr.status)
				}
			})
		}
	});

	$('#btnok').on('click', function(){
		modicont = $('#boardModifyForm #text').val();  //엔터가 포함 
		modi = modicont.replace(/\r/g,"").replace(/\n/g, "<br>");
		vp3 = $('#boardModifyForm').parent();
		$('body').append($('#boardModifyForm'));
		$('#boardModifyForm').hide();
		$.ajax({
			url : '<%=request.getContextPath()%>/board/ReplyUpdate.do',
			data : {
				"board_comment_no" : vidx,
				"board_comment_content" : modicont
			},
			type : 'post',
			success : function(res){
				$(vp3).html(modi); 
				$(vp3).append(p2);
			},
			error : function(xhr){
				alert("상태 : " + xhr.status)
			}
		})
	});
	
	replyReset = function(){
		vp3 =  $('#boardModifyForm').parent();
		$('body').append($('#boardModifyForm'));
		$('#boardModifyForm').hide();
		vp3.html(modicont);
		vp3.append(p2);
	}

	$('#btnreset').on('click', function(){
		replyReset();
	})

	
});

</script>
</head>
<body>
    <jsp:include page="../../../header.jsp"></jsp:include>
	<!-- boardSidebar.jsp include -->
	<jsp:include page="../../../boardSidebar.jsp"></jsp:include>
	<!-- //댓글 수정 편집창 -->
	<div id="boardModifyForm">
		<textarea id="text" rows="5" cols="40"></textarea>
		<input type="button" value="확인" id="btnok"> <input
			type="button" value="취소" id="btnreset">
	</div>
	<div class="container-fluid pt-4 px-4" style="width: 1300px;">
		<div style="text-align: right;">
			<%if(isLogin){
				if((memberVo.getMem_no() == boardVo.getMem_no()) || memberVo.getMem_grade() == 9){%>
				<input id="btnUpdate" type="button" class="btn btn-outline-success m-2" value="수정">
				<input id="btnDelete" type="button" class="btn btn-outline-success m-2" value="삭제">
			<% }}%>
				<input id="btnList" type="button" class="btn btn-outline-success m-2" value="목록보기">
		</div>
		<div class="rounded h-100 p-4" style="border: 5px solid teal; width: 100%; overflow: auto;"">
			<form name="detailForm" id="detailForm" style="">
				<input type="hidden" id="boardVo" name="boardVo" value="<%=boardVo.getBoard_no()%>"> 
	<%-- 					<input type="hidden" id="memNo" name="memNo" value="<%=memberVo.getMem_no()%>"> --%>
				<input type="hidden" id="page" name="page" value="1">
				<input type="hidden" id="boardCategoryNo" name="boardCategoryNo" value="<%=boardVo.getBoard_category_no()%>">
				
				<div style="border-bottom: 1px solid #DCDCDC; width:100%; height:50px; padding-top:5px;" class="row">
					<div class="col-1" style="padding-top : 10px; padding-left : 15px; font-size : 1.2em;">제목</div>
					<div class="col-9" style="font-size: 1.7em;"><%=boardVo.getBoard_title()%></div>
				</div>
				<div style="border-bottom: 1px solid #DCDCDC; width:100%; height:50px; padding-top:12px; padding-left : 5px; font-size : 1.2em;" class="row bg-light">
					<div class="col-1" style="">날짜</div>
					<div class="col-2" style="font-size: 1.0em;"><%=boardVo.getBoard_date()%></div>
					<div class="col-1" style="">작성자</div>
					<div class="col-1" style="font-size: 1.0em;"><%=boardVo.getMem_name()%></div>
					<div class="col-1" style="">조회수</div>
					<div class="col-1" style="font-size: 1.0em;"><%=boardVo.getBoard_hit()%></div>
				</div><br>
				
				<%int filecheck = (int)request.getAttribute("filecheck");
				if(filecheck==1){%>
					<img alt="profileImage" id="preview"  src="<%=request.getContextPath() %>/files/imageView.do?file_category_no=2&col=BOARD_NO&no=<%=boardVo.getBoard_no()%>" style="width:50%;"><br><br>
				<%} %>
				<div style="width:100%; height:500px; margin-left:auto; margin-right:auto;'">
					<%=boardVo.getBoard_content()%>
				</div>
			</form>
		</div>
	</div>

	<div class="container-fluid pt-4 px-4 reply-body bg-light" style="width: 1250px;">
		<%if (boardCommentList != null || boardCommentList.size() > 0) { // 댓글 보여주는 부분
			for (BoardCommentVO boardCommentVO : boardCommentList) {
				if(boardCommentVO.getBoard_comment_parents() == 0){%>
					<div class="p4">
						<%if(isLogin) {%>
						<%if (memberVo.getMem_no() == boardCommentVO.getMem_no()) {%>
							<span class="p3"><%=boardCommentVO.getBoard_comment_content().replaceAll("\r", "").replaceAll("\n", "<br>")%>
							<div class="p2">
							<input idx="<%=boardCommentVO.getBoard_comment_no()%>"  type="button" name="r_modify" class="btn btn-outline-success m-2 action" value="수정"> 
							<input idx="<%=boardCommentVO.getBoard_comment_no()%>"  type="button" name="r_delete" class="btn btn-outline-success m-2 action" value="삭제">
							</div>
							</span><hr>
							</div>
						<%}else{%>
							<span class="p3 "><%=boardCommentVO.getBoard_comment_content().replaceAll("\r", "").replaceAll("\n", "<br>")%></span><hr></div>
						<%}
						}
					}
				}
			} else { %>
			작성된 댓글이 없습니다.
		<%}%>

		<%if(isLogin){
			if(categoryNo !=1){%>
			<form name="modifyForm" id="boardCommentForm">
				<div class="form-floating mb-3" id="modifyForm">
					<input type="hidden" id="boardVo" name="boardVo" value="<%=boardVo.getBoard_no()%>">
<%-- 					 <input type="hidden" id="memNo" name="memNo" value="<%=memberVo.getMem_no()%>"> --%>
					<textarea cols="110" rows="3" name="content" id="text"></textarea>
					<button id="addReply" class="btn btn-success rounded-pill m-2" style="vertical-align: top;">댓글등록</button>
				</div>
			</form>
		<% }}%>
	</div>
	
    <!--    footer.jsp include -->
   <jsp:include page="../../../footer.jsp"></jsp:include>

    <!-- JavaScript Libraries -->
    
    <script src="<%=request.getContextPath() %>/lib/chart/chart.min.js"></script>
    <script src="<%=request.getContextPath() %>/lib/easing/easing.min.js"></script>
    <script src="<%=request.getContextPath() %>/lib/waypoints/waypoints.min.js"></script>
    <script src="<%=request.getContextPath() %>/lib/owlcarousel/owl.carousel.min.js"></script>
    <script src="<%=request.getContextPath() %>/lib/tempusdominus/js/moment.min.js"></script>
    <script src="<%=request.getContextPath() %>/lib/tempusdominus/js/moment-timezone.min.js"></script>
    <script src="<%=request.getContextPath() %>/lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

     <!-- iamport.payment.js -->
   <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>

    <!-- Template Javascript -->
    <script src="<%= request.getContextPath() %>/js/main.js"></script>
    
</body>
</html>