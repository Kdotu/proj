<%@page import="vo.MemberVO"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<%=request.getContextPath() %>/js/jquery-3.6.1.min.js"></script>

<%-- <% Map<String,Object> loginInfo = (Map<String,Object>)session.getAttribute("loginInfo");  //회원정보, 전문가 정보 
	MemberVO memberVO = (MemberVO)loginInfo.get("memberInfo"); //로그인한사람 회원 
%>  
 --%>
 
 
 
 <%

 String res = (String)request.getAttribute("res");
 
 %>

<script type="text/javascript">



</script>

<style>
div{
	width : 200px;
	height : 100px;
	border : 1px solid blue;
	float : left;
	text-align : center;
}
h3{
	clear : both;
}
</style>

</head>

<body>

<div>
<p><a href="<%= request.getContextPath() %>/service/categoryMain.do?main_no=0">서비스전체</a><p>
</div> 

<div>
<p><a href="<%= request.getContextPath() %>/service/categoryMain.do?main_no=1">디자인</a><p>
</div> 

<div>
<p><a href="<%= request.getContextPath() %>/service/categoryMain.do?main_no=2">it,프로그래밍</a><p>
</div>

<div>
<p><a href="<%= request.getContextPath() %>/service/categoryMain.do?main_no=3">영상, 사진, 음향</a><p>
</div>

<div>
<p><a href="<%= request.getContextPath() %>/service/categoryMain.do?main_no=4">번역, 통역</a><p>
</div>




<h3><input type="button" value="서비스등록" id="serviceInsert" ></h3>
<!-- 서비스삭제 세션정보 및 서비스번호정보 끌어오기  -->


<h3><input type="button" id="myServiceList" value="나의serviceList"></h3>
<p id="myservice">요기에내서비스리스트붙이기</p>

<h3><input type="button" id="myWishList" value="내찜목록"></h3>
<p id="myWish">요기에내위시리스트리스트붙이기</p>

</body>
</html>