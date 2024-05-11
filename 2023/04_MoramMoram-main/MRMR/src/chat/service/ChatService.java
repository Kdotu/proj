package chat.service;

import java.util.List;

import vo.ChatVO;

public interface ChatService {
	public List<ChatVO> getChat(int chatNo, int target);
	
	public int sendChat(ChatVO vo);

	public List<ChatVO> getTargetList(int memNo);
}
