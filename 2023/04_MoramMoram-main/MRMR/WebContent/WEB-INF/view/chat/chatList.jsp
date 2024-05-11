<%@page import="com.google.gson.Gson"%>
<%@page import="vo.ChatVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//서블릿에서 저장한 데이타 꺼내기 
	List<ChatVO> chatList = (List<ChatVO>)request.getAttribute("chatList");

	Gson gson = new Gson();
	String result = gson.toJson(chatList);
	
	out.print(result);
	out.flush();
%>