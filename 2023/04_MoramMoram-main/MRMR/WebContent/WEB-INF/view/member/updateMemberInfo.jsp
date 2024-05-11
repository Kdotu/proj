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
   $("#searchAddr").on("click", function(){
      new daum.Postcode({
           oncomplete: function(data) {
               $("input[name=mem_addr_base]").val(data.address);
           }
       }).open();
   }) 
      
   $("#memberForm").on("submit", function(){
      if($("#mem_pass").val()!= $("#mem_pass_check").val()){
         alert("비밀번호와 비밀번호 확인이 다릅니다. 다시 확인하세요.");
         return false;   // 서버로 전송을 하지 않는다.
      }else{
         $("#mem_pass_check").css("border", "2px soild blue");
      }
      
      if($("#mem_pass").val()=="" || $("#mem_pass").val()==null){
         alert("비밀번호를 입력해주세요");
         return false;
      }
      
      if($("#passCheckResult").html()!="OK"){
         alert("비밀번호형식이 올바르지 않습니다. 확인해주세요")
         return false;
      }
      
      if($("#hpCheckResult").html()!="OK"){
         alert("전화번호형식이 올바르지 않습니다. 확인해주세요")
         return false;
      }
      if($("#emailCheckResult").html()!="OK"){
         alert("이메일형식이 올바르지 않습니다. 확인해주세요")
         return false;
      }
      
      if($("#files").val() == ""){
         $(this).attr("action", "<%=request.getContextPath()%>/member/updateMemberInfo.do");
      }else{
         $(this).attr("action", "<%=request.getContextPath()%>/files/updateFiles.do");
      }
      return true;
   });
   
   // 정규식 체크
   $("#mem_pass").on("keyup", function(){
	  $("#passCheckResult").show();
      regText = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/;
      if(regText.test($(this).val().trim())){
         $("#passCheckResult").html("OK");
      }else{
         $("#passCheckResult").html("사용할 수 없는 비밀번호입니다.");
      }
   })
   $("#mem_pass_check").on("keyup", function(){
	  $("#passDoubleCheckResult").show();
      if($(this).val().trim() != $("#mem_pass").val()){
         $("#passDoubleCheckResult").html("비밀번호가 일치하지 않습니다.");
      }else{
         $("#passDoubleCheckResult").html("OK");
      }
   })
   $("#mem_hp").on("keyup", function(){
	   $("#hpCheckResult").show();
      regText = /^01(?:0|1|[6-9])-(?:\d{3}|\d{4})-\d{4}$/;
      if(regText.test($(this).val().trim())){
         $("#hpCheckResult").html("OK");
      }else{
         $("#hpCheckResult").html("전화번호 형식을 맞춰주세요.");
      }
   })
   $("#mem_email").on("keyup", function(){
	   $("#emailCheckResult").show();
      regText = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
      if(regText.test($(this).val().trim())){
         $("#emailCheckResult").html("OK");
      }else{
         $("#emailCheckResult").html("이메일 형식이 올바르지 않습니다.");
      }
   })
});

function readURL(input) {
   if (input.files && input.files[0]) {
      var reader = new FileReader();
      reader.onload = function(e) {
         document.getElementById('preview').src = e.target.result;
      };
      reader.readAsDataURL(input.files[0]);
   } else {
      document.getElementById('preview').src = "";
   }
};
</script>
<style type="text/css">
#preview{
	width: 70%;
}
</style>
</head>
<%
Map<String, Object> loginInfo = (Map<String, Object>)session.getAttribute("loginInfo");
MemberVO loginMemberInfo = (MemberVO) loginInfo.get("memberInfo");
%>
<body>
<form id="memberForm" enctype="multipart/form-data" method="post" >
<input type="hidden" name="file_category_no" value="4">
<input type="hidden" name="mem_id" value="<%=loginMemberInfo.getMem_id()%>">
<input type="hidden" name="mem_no" value="<%=loginMemberInfo.getMem_no()%>">

	<div class="container-xxl position-relative bg-white d-flex p-0">
	<!-- Sign Up Start -->
		<div class="container-fluid">
			<div class="row h-100 align-items-center justify-content-center" style="min-height: 100vh;">
				<div class="col-sm-8">
					<div class="bg-light rounded p-4 p-sm-5 my-4 mx-3" style="border: 3px solid teal;">
						<div class ="d-flex align-items-center justify-content-between mb-3">
							<a href="http://localhost/MRMR/main.jsp"" class="">
							<h3 class="text-primary"><i class="fa fa-hashtag me-2"></i>MORAM</h3></a>
							<h3>회원정보 수정</h3>
						</div>
						<label for="floatingText">프로필 사진</label><br>
						<div style="display: inline-block;">
							<img alt="profileImage" id="preview" >
						</div>
						<div>
							<input type="file" onchange="readURL(this);" value="사진선택"  name="files" id="files"> 
						</div>
						<br/>        
						<label for="floatingText">비밀번호</label>
						<div class="form-floating mb-3">
							<input type="password" class="form-control" name="mem_pass" id="mem_pass"><br>
							<span id="passCheckResult" style="display:none;"></span>
						</div>
						<label for="floatingText">비밀번호확인</label>
						<div class="form-floating mb-3">
							<input type="password" class="form-control" name="mem_pass_check" id="mem_pass_check"><br>
							<span id="passDoubleCheckResult" style="display:none;">OK</span>
						</div>
						<label for="floatingInput">연락처</label>
						<div class="form-floating mb-3">
							<input type="text" class="form-control" name="mem_hp" id="mem_hp" value="<%=loginMemberInfo.getMem_hp() %>"><br>
							<span id="hpCheckResult" style="display:none;">OK</span>
						</div>
						<label for="floatingInput">이메일</label>
						<div class="form-floating mb-3">
							<input type="text" class="form-control" name="mem_email" id="mem_email" style="padding: 20px 0px 0px 13px;" value="<%=loginMemberInfo.getMem_email()%>"><br>
							<span id="emailCheckResult" style="display:none;">OK</span>
						</div>
						<label for="floatingInput">기본주소</label><br>
						<div class="form-floating mb-3">
							<button id="searchAddr" class="btn btn-outline-secondary m-2" style="float: right;">주소찾기</button>
							<input type="text" class="form-control" name="mem_addr_base" id="mem_addr_base" value="<%=loginMemberInfo.getMem_addr_base()%>" readonly>
							<span id="baseAddrCheckResult" ></span>
						</div>
						<label for	="floatingInput">상세주소</label><br>
						<div class="form-floating mb-3">
							<input type="text" class="form-control" name="mem_addr_detail" id="mem_addr_detail" value="<%=loginMemberInfo.getMem_addr_detail()%>">
							<span id="detailAddrCheckResult" ></span>
						</div>
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
</body>
</html>