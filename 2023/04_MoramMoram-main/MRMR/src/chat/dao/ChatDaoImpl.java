package chat.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import config.SqlMapClientFactory;
import vo.ChatVO;

public class ChatDaoImpl implements ChatDao{
	private static ChatDao instance;
	private SqlMapClient smc;

	private ChatDaoImpl() {
		smc = SqlMapClientFactory.getSqlMapClient();
	}

	public static ChatDao getInstance() {
		if (instance == null)
			instance = new ChatDaoImpl();
		return instance;
	}

	@Override
	public List<ChatVO> getChat(int chatNo, int target) {
		List<ChatVO> list = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("chatNo", chatNo);
		map.put("target", target);
		
		try {
			list = smc.queryForList("chat.getChat", map);
		} catch (Exception e) {
			list = null;
			System.out.println("getChat 오류");
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int sendChat(ChatVO vo) {
		int res = 0;
		try {
			Object result = smc.insert("chat.sendChat", vo);
			if(result == null) res = 1;
		} catch (Exception e) {
			res = 0;
			System.out.println("sendChat 오류");
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public List<ChatVO> getTargetList(int memNo) {
		List<ChatVO> list = null;
		try {
			list = smc.queryForList("chat.getTargetList", memNo);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	
	
	
}
