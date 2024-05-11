<%@page import="java.time.ZoneId"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href='./packages/core/main.css' rel='stylesheet' />
<link href='./packages/daygrid/main.css' rel='stylesheet' />
<link href='./packages/timegrid/main.css' rel='stylesheet' />
<script src='./packages/core/main.js'></script>
<script src='./packages/interaction/main.js'></script>
<script src='./packages/daygrid/main.js'></script>
<script src='./packages/timegrid/main.js'></script>
<script src = './calendar.js'></script>
<script src = '<%=request.getContextPath() %>/js/jquery-3.6.1.min.js'></script>
<script>
var mem_no = 41;

// 편집 가능!
selectSchedule(mem_no);

// 열람만 가능!
viewSchedule(mem_no);

</script>

<style>

  #calendar {
    max-width: 900px;
    margin: 0 auto;
  }
</style>
<title>MRMR</title>
</head>
<body>
  <h3>Calendar</h3>
  <div id='calendar'></div>
</body>
</html>