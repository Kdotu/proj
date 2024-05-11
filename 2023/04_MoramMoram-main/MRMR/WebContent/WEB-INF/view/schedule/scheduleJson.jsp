<%@page import="java.util.List"%>
<%@page import="vo.ScheduleVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
List<ScheduleVO> scheduleList = (List<ScheduleVO>)request.getAttribute("scheduleList");
%>

{

<%
	if(scheduleList != null && scheduleList.size() > 0){ 
%>
		"flag" : "ok",
		"datas" : 
		[
		
		<%
			for(int i = 0; i < scheduleList.size(); i++){
				if(i > 0) out.print(",");
				ScheduleVO vo = scheduleList.get(i);
		%>
			{
				"_id" : "<%=vo.getSchedule_no() %>",
				"title" : "<%=vo.getSchedule_name() %>",
				"start" : "<%=vo.getSchedule_start() %>",
				"end" : "<%=vo.getSchedule_end() %>"
			}	
		<%
			}
		%>
		]
		
		<%
		}else{ 
		%>
		
		"flag" : "no"
		
		<%
		} 
		%>
		
}    