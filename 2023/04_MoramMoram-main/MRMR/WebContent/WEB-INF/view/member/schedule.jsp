<%@page import="vo.MemberVO"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MRMR</title>

<link href='<%=request.getContextPath() %>/calendar/packages/core/main.css' rel='stylesheet' />
<link href='<%=request.getContextPath() %>/calendar/packages/daygrid/main.css' rel='stylesheet' />
<link href='<%=request.getContextPath() %>/calendar/packages/timegrid/main.css' rel='stylesheet' />
<script src='<%=request.getContextPath() %>/calendar/packages/core/main.js'></script>
<script src='<%=request.getContextPath() %>/calendar/packages/interaction/main.js'></script>
<script src='<%=request.getContextPath() %>/calendar/packages/daygrid/main.js'></script>
<script src='<%=request.getContextPath() %>/calendar/packages/timegrid/main.js'></script>
<script src = '<%=request.getContextPath() %>/js/jquery-3.6.1.min.js'></script>
<script src = '<%=request.getContextPath() %>/js/calendar.js'></script>
<style>
#calendar {
	max-width: 900px;
	margin: 0 auto;
}
</style>
<%
Map<String, Object> map = new HashMap<String, Object>();
MemberVO memberVo = new MemberVO();
Map<String, Object> loginInfo = (Map<String, Object>) session.getAttribute("loginInfo");
memberVo = (MemberVO)loginInfo.get("memberInfo");
%>
<script>
	selectSchedule(<%=memberVo.getMem_no()%>);
</script>
</head>
<body>
	<div id="calendar"></div>
</body>
</html>