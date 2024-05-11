package chat.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import chat.service.ChatService;
import chat.service.ChatServiceImpl;
import member.service.MemberService;
import member.service.MemberServiceImpl;
import vo.ChatVO;
import vo.MemberVO;

public class ChatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ChatService chat_service = ChatServiceImpl.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		
		switch(command) {
		case "/chat/main.do":
			HttpSession session = request.getSession();
			Map<String, Object> loginInfo = (Map<String, Object>)session.getAttribute("loginInfo");
			MemberVO loginMemberInfo = (MemberVO)loginInfo.get("memberInfo");
			
			
			List<ChatVO> getTargetList = chat_service.getTargetList(loginMemberInfo.getMem_no());
			request.setAttribute("getTargetList", getTargetList);
			
			request.getRequestDispatcher("/WEB-INF/view/chat/chatMain.jsp").forward(request, response);
			break;
		case "/chat/Read.do":
			chatList(request, response);
			break;
		}
	}

	private void chatList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ChatVO> chatList = new ArrayList<ChatVO>();
		int chatNo = Integer.parseInt(request.getParameter("chatNo"));
		int target = Integer.parseInt(request.getParameter("target"));
		
		System.out.println("chatNo : " + chatNo);
		System.out.println("target : " + target);
		
		chatList = chat_service.getChat(chatNo,target);
		
		request.setAttribute("chatList", chatList);
		request.getRequestDispatcher("/WEB-INF/view/chat/chatList.jsp").forward(request, response);
	}

}
