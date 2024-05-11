<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String res = (String)request.getAttribute("res");
	int serviceNo = (int)request.getAttribute("serviceNo");
%>
<script type="text/javascript">
	alert("<%=res%>");
	location.href="<%=request.getContextPath()%>/service/serviceDetail.do?serviceNo=<%=serviceNo%>" ;
</script>
</body>
</html>