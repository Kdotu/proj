package member.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import member.dao.MemberDao;
import member.dao.MemberDaoImpl;
import vo.ExpertVO;
import vo.MemberVO;
import vo.PageVO;
import vo.ReportVO;

public class MemberServiceImpl implements MemberService {
	private static MemberServiceImpl instance;
	private MemberDao dao;

	private MemberServiceImpl() {
		dao = MemberDaoImpl.getInstance();
	}

	public static MemberServiceImpl getInstance() {
		if (instance == null)
			instance = new MemberServiceImpl();
		return instance;
	}

	@Override
	public int MemberJoin(MemberVO memberVo) {
		return dao.MemberJoin(memberVo);
	}

	@Override
	public int MemberIdCheck(String memberId) {
		return dao.MemberIdCheck(memberId);
	}

	@Override
	public int MemberLogin(MemberVO memberVo) {
		int cnt = dao.MemberLogin(memberVo);
		if(cnt != 0) {
			// 로그인한 회원의 정보를 가져온다
			memberVo = dao.getMemberInfo(memberVo.getMem_id());
			int memberNo = memberVo.getMem_no();
			
			if(memberVo.getMem_grade() != 9) {
				// 로그인한 회원이 전문가인지를 판별한다.
				int result = dao.existExpert(memberNo);
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("mem_no", memberNo);
				
				if(result == 1) {	// 전문가이면
					param.put("mem_grade", 1);
				}else {	// 전문가가 아니면
					param.put("mem_grade", 0);
				}
				dao.updateMemGrade(param);	// 회원 등급 변경
			}
			return cnt;
		}else {
			return cnt;
		}
	}
	
	@Override
	public int existExpert(int memberNo) {
		int cnt = dao.existExpert(memberNo);
		return cnt;
	}

	@Override
	public MemberVO getMemberInfo(String memberId) {
		return dao.getMemberInfo(memberId);
	}

	@Override
	public ExpertVO getExperInfo(String memberId) {
		return dao.getExperInfo(memberId);
	}

	@Override
	public int updateMemGrade(Map<String, Object> param) {
		return dao.updateMemGrade(param);
	}

	@Override
	public int updateMember(MemberVO memberVo) {
		return dao.updateMember(memberVo);
	}

	@Override
	public List<Map<String, Object>> selectMyCart(Map<String, Object> param) {
		return dao.selectMyCart(param);
	}

	@Override
	public List<Map<String, Object>> selectMyBoard(Map<String, Object> map) {
		return dao.selectMyBoard(map);
	}

	@Override
	public List<Map<String, Object>> selectWishList(int memberNo) {
		return dao.selectWishList(memberNo);
	}

	@Override
	public void deleteWish(int wishNo) {
		dao.deleteWish(wishNo);
	}

	@Override
	public List<Map<String, Object>> selectChat(int memberNo) {
		return dao.selectChat(memberNo);
	}

	@Override
	public String MemberIdSearch(MemberVO memberVo) {
		return dao.MemberIdSearch(memberVo);
	}

	@Override
	public String MemberPassSearch(MemberVO memberVo) {
		return dao.MemberPassSearch(memberVo);
	}

	@Override
	public int insertExpert(ExpertVO expertVo) {
		return dao.insertExpert(expertVo);
	}

	@Override
	public List<Map<String, Object>> selectExpertServieHistory(int memberNo) {
		return dao.selectExpertServieHistory(memberNo);
	}

	@Override
	public List<Map<String, Object>> selectSalesStatistics(Map<String, Object> param) {
		// dateType으로 dao에서 호출할 쿼리를 선택
		switch((String)param.get("dateType")) {
		case "year":
			param.put("mapper", "selectYearSalesStatistics");
			break;
		case "month":
			param.put("mapper", "selectMonthSalesStatistics");
			break;
		case "week":
			param.put("mapper", "selectWeekSalesStatistics");
			break;
		case "day":
			param.put("mapper", "selectDaySalesStatistics");
			break;
		}
		return dao.selectSalesStatistics(param);
	}

	@Override
	public List<Map<String, Object>> selectReportCategory() {
		return dao.selectReportCategory();
	}

	@Override
	public PageVO pageInfo(String title, int page, String stype, String sword, int mem_no) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("stype", stype);
		map.put("sword", sword);
		map.put("mem_no", mem_no);
		if("서비스".equals(title)) {
			map.put("table", "CART");
		}else {
			map.put("table", "BOARD");
		}
		int count = this.getTotalCount(map);
		
		int start = (page-1) * PageVO.getPerList() + 1;
		int end = start + PageVO.getPerList() - 1;
		if(end > count)  end = count;
			
		int totalPage =  (int)Math.ceil((double)count / PageVO.getPerList());
		
		int startPage = ((page-1) /PageVO.getPerPage() * PageVO.getPerPage() ) + 1;
		int endPage = startPage + PageVO.getPerPage() - 1;
		if(endPage > totalPage ) endPage = totalPage;
		
		PageVO  vo = new PageVO();
		vo.setStart(start);
		vo.setEnd(end);
		vo.setStartPage(startPage);
		vo.setEndPage(endPage);
		vo.setTotalPage(totalPage);
		
		return vo;
	}

	@Override
	public int getTotalCount(Map<String, Object> map) {
		int   count = 0;
		try {
			count = dao.getTotalCount(map);
		} catch (SQLException e) {
			System.out.println("getTotalCount 오류");
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int insertReport(ReportVO vo) {
		return dao.insertReport(vo);
	}

	@Override
	public MemberVO selectMemberInfoByNo(int memberNo) {
		return dao.selectMemberInfoByNo(memberNo);
	}

}
