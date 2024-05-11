package chat.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServlet;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import chat.service.ChatService;
import chat.service.ChatServiceImpl;
import member.service.MemberService;
import member.service.MemberServiceImpl;
import vo.ChatVO;

@ServerEndpoint("/ChatServer")
public class ChatServer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());
	ChatService chat_service = ChatServiceImpl.getInstance();
	MemberService mem_service = MemberServiceImpl.getInstance();

	BufferedOutputStream bos;
	String path = "C:/Users/seolah/logs";

	@OnMessage
	public void onMessage(String message, Session session) throws IOException {
		System.out.println("[메세지]" + session.getId() + " : " + message);
		
		if (message.contains("|")) {
			int sender = Integer.parseInt(message.substring(0, message.indexOf("|")));
			String msg = message.substring(message.indexOf("|") + 1, message.indexOf(">"));
			int target = Integer.parseInt(message.substring(message.indexOf(">") + 1));
			
			System.out.println("sender >> " + sender);
			System.out.println("msg >> " + msg);
			System.out.println("target >> " + target);

			ChatVO vo = new ChatVO();
			vo.setMem_no_sender(sender);
			vo.setChat_content(msg);
			vo.setMem_no_target(target);

			chat_service.sendChat(vo);
		}

		synchronized (clients) {
			for (Session s : clients) {
				if (!s.equals(session)) {
					s.getBasicRemote().sendText(message);
				}
			}
		}
	}

	@OnOpen
	public void onOpen(Session session) {
		clients.add(session);
		System.out.println("웹소켓 연결 : " + session.getId());
	}
	
	@OnClose
	public void onClose(Session session) {
		clients.remove(session);
		System.out.println("웹소켓 종료 : " + session.getId());
	}

	@OnError
	public void onError(Throwable e) {
		System.out.println("에러 발생");
		e.printStackTrace();
	}
}
