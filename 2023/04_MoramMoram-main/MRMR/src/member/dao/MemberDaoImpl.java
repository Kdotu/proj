package member.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import config.SqlMapClientFactory;
import vo.ExpertVO;
import vo.MemberVO;
import vo.ReportVO;

public class MemberDaoImpl implements MemberDao {
	private static MemberDaoImpl instance;
	private SqlMapClient smc;

	private MemberDaoImpl() {
		smc = SqlMapClientFactory.getSqlMapClient();
	}

	public static MemberDaoImpl getInstance() {
		if (instance == null)
			instance = new MemberDaoImpl();
		return instance;
	}

	@Override
	public int MemberJoin(MemberVO memberVo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("member.insertMember", memberVo);
			if(obj == null) cnt = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int MemberIdCheck(String memberId) {
		int cnt = 0;
		try {
			cnt = (int) smc.queryForObject("member.memberIdCheck", memberId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int MemberLogin(MemberVO memberVo) {
		int cnt = 0;
		try {
			cnt = (int) smc.queryForObject("member.memberLogin", memberVo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	@Override
	public int existExpert(int memberNo) {
		int cnt = 0;
		try {
			cnt = (int) smc.queryForObject("member.existExpert", memberNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public MemberVO getMemberInfo(String memberId) {
		MemberVO memberVo = new MemberVO();
		try {
			memberVo = (MemberVO) smc.queryForObject("member.selectMemberInfo", memberId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memberVo;
	}

	@Override
	public ExpertVO getExperInfo(String memberId) {
		ExpertVO expertVo = new ExpertVO();
		try {
			expertVo = (ExpertVO) smc.queryForObject("member.selectExpertInfo", memberId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return expertVo;
	}

	@Override
	public int updateMemGrade(Map<String, Object> param) {
		int cnt = 0;
		try {
			cnt = smc.update("member.updateMemGrade", param);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateMember(MemberVO memberVo) {
		int cnt = 0;
		try {
			cnt = smc.update("member.updateMember", memberVo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<Map<String, Object>> selectMyCart(Map<String, Object> param) {
		List<Map<String, Object>> cartList = null;
		try {
			cartList = smc.queryForList("member.selectMyCart", param);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cartList;
	}

	@Override
	public List<Map<String, Object>> selectMyBoard(Map<String, Object> map) {
		List<Map<String, Object>> boardList = null;
		try {
			boardList = smc.queryForList("member.selectMyBoard", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boardList;
	}

	@Override
	public List<Map<String, Object>> selectWishList(int memberNo) {
		List<Map<String, Object>> wishList = null;
		try {
			wishList = smc.queryForList("member.selectWishList", memberNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return wishList;
	}

	@Override
	public void deleteWish(int wishNo) {
		try {
			smc.delete("member.deleteWishList", wishNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Map<String, Object>> selectChat(int memberNo) {
		List<Map<String, Object>> chatList = null;
		try {
			chatList = smc.queryForList("member.selectChat", memberNo);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return chatList;
	}

	@Override
	public String MemberIdSearch(MemberVO memberVo) {
		String memName = null;
		try {
			memName = (String) smc.queryForObject("member.memberIdSearch", memberVo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memName;
	}

	@Override
	public String MemberPassSearch(MemberVO memberVo) {
		String memPass = null;
		try {
			memPass = (String) smc.queryForObject("member.memberPasswordSearch", memberVo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memPass;
	}

	@Override
	public int insertExpert(ExpertVO expertVo) {
		int result = 0;
		try {
			Object obj = smc.insert("member.insertExpert", expertVo);
			if(obj == null) result = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> selectExpertServieHistory(int memberNo) {
		List<Map<String, Object>> expertServiceHitory = null;
		try {
			expertServiceHitory = (List<Map<String, Object>>) smc.queryForList("member.selectExpertServiceHistory", memberNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return expertServiceHitory;
	}

	@Override
	public List<Map<String, Object>> selectSalesStatistics(Map<String, Object> param) {
		List<Map<String, Object>> result = null;
		try {
			result = smc.queryForList("member." + param.get("mapper"), param);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> selectReportCategory() {
		List<Map<String, Object>> reportCategory = null;
		try {
			reportCategory = smc.queryForList("member.selectReportCategory");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reportCategory;
	}

	@Override
	public int getTotalCount(Map<String, Object> map) throws SQLException {
		return (int) smc.queryForObject("member.getTotalCount", map);
	}
	
	@Override
	public int insertReport(ReportVO vo) {
		int cnt = 0;
		
		try {
			Object obj = smc.insert("member.insertReport",vo);
			if(obj == null) cnt = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public MemberVO selectMemberInfoByNo(int memberNo) {
		MemberVO vo = null;
		
		try {
			vo = (MemberVO)smc.queryForObject("member.selectMemberInfoByNo", memberNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vo;
	}

}
