package chat.service;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import chat.dao.ChatDao;
import chat.dao.ChatDaoImpl;
import config.SqlMapClientFactory;
import member.dao.MemberDao;
import member.dao.MemberDaoImpl;
import vo.ChatVO;

public class ChatServiceImpl implements ChatService{
	private static ChatService instance;
	private ChatDao dao;

	private ChatServiceImpl() {
		dao = ChatDaoImpl.getInstance();
	}

	public static ChatService getInstance() {
		if (instance == null)
			instance = new ChatServiceImpl();
		return instance;
	}

	@Override
	public List<ChatVO> getChat(int chatNo, int target) {
		return dao.getChat(chatNo, target);
	}

	@Override
	public int sendChat(ChatVO vo) {
		return dao.sendChat(vo);
	}

	@Override
	public List<ChatVO> getTargetList(int memNo) {
		return dao.getTargetList(memNo);
	}

}
