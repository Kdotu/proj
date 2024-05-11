package member.service;

import java.util.List;
import java.util.Map;

import vo.ExpertVO;
import vo.MemberVO;
import vo.PageVO;
import vo.ReportVO;

public interface MemberService {
	/**
	 * MemberVO의 정보를 이용해 회원가입하는 메서드
	 * 
	 * @param memberVo 회원가입시 필요한 회원 정보를 가지고 있는 MemberVO객체
	 * @return 성공 : 1, 실패 : 0
	 */
	public int MemberJoin(MemberVO memberVo);
	
	/**
	 * MemberVO객체를 인수로 받아 회원의 아이디를 찾는 메서드
	 * 
	 * @param memberVo 회원의 이름과 이메일을 저장한 MemberVO객체
	 * @return 회원의 아이디
	 */
	public String MemberIdSearch(MemberVO memberVo);
	
	/**
	 * MemberVO객체를 인수로 받아 회원의 비밀번호를 찾는 메서드
	 * 
	 * @param memberVo 회원의 아이디와 이름, 이메일을 저장한 MemberVO객체
	 * @return 회원의 비밀번호
	 */
	public String MemberPassSearch(MemberVO memberVo);
	
	/**
	 * 회원가입하려는 회원의 아이디를 인수로 받아 입력한 아이디가 테이블에 존재하는지 확인하는 메서드
	 * 
	 * @param memberId 중복체크 할 아이디
	 * @return 중복이면 1, 중복이 아니면 0
	 */
	public int MemberIdCheck(String memberId);
	
	/**
	 * MemberVO객체를 인수로 받아 아이디와 비밀번호가 일치하는지를 체크해서 결과는 반환하는 메서드
	 * 
	 * @param memberVo 입력받은 아이디와 비밀번호 정보를 저장하고 있는 MemberVO객체 
	 * @return 로그인 성공 : 1, 실패 : 0
	 */
	public int MemberLogin(MemberVO memberVo);
	
	/**
	 * 회원 번호를 인수로 받아 해당 회원이 전문가정보가 존재하는지 확인하는 메서드
	 * 
	 * @param memberNo 회원 번호
	 * @return 존재하면  1, 존재하지 않으면 0
	 */
	public int existExpert(int memberNo);
	
	/**
	 * 회원의 아이디를 인수로 받아 회원 정보를 조회하는 메서드
	 * 
	 * @param memberId 조회할 회원의 아이디
	 * @return 회원 정보를 저장한 MemberVO객체
	 */
	public MemberVO getMemberInfo(String memberId);
	
	/**
	 * 전문가의 아이디(회원아이디)를 인수로 받아 전문가의 정보를 조회하는 메서드
	 * 
	 * @param memberId 조회할 전문가의 아이디
	 * @return 전문가 정보를 저장한 ExpertVO객체
	 */
	public ExpertVO getExperInfo(String memberId);
	
	/**
	 * Map객체를 인수로 받아 회원등급을 변경하는 메서드
	 * 
	 * @param param 회원 아이디와 변경할 회원 등급이 저장된 Map객체
	 * @return 성공 : 1, 실패 : 0
	 */
	public int updateMemGrade(Map<String, Object> param);
	
	/**
	 * MemberVO객체를 인수로 받아 회원의 정보를 수정하는 메서드
	 * 
	 * @param memberVo 수정할 회원의 정보를 저장한 MemberVO객체
	 * @return 성공 : 1, 실패 : 0
	 */
	public int updateMember(MemberVO memberVo);
	
	/**
	 * 회원의 회원번호를 인수로 받아 회원의 서비스이용내역을 조회하는 메서드
	 * 
	 * @param memberNo 회원 번호
	 * @return 
	 */
	public List<Map<String, Object>> selectMyCart(Map<String, Object> param);
	
	/**
	 * 회원의 회원번호를 인수로 받아 회원이 작성한 게시글을 조회하는 메서드
	 * 
	 * @param memberNo
	 * @return
	 */
	public List<Map<String, Object>> selectMyBoard(Map<String, Object> map);
	
	/**
	 * 회원번호를 인수로 받아 회원의 위시리스트를 조회하는 메서드
	 * 
	 * @param memberNo 회원 번호
	 * @return 회원의 위시리스트정보를 가지고 있는 Map객체
	 */
	public List<Map<String, Object>> selectWishList(int memberNo);
	
	/**
	 * 위시번호를 인수로 받아 위시리스트에서 삭제하는 메서드
	 * 
	 * @param wishNo 삭제할 위시리스트 번호
	 */ 
	public void deleteWish(int wishNo);
	
	/**
	 * 회원번호를 인수로 받아 채팅내역을 조회하는 메서드
	 * 
	 * @param memberNo 회원 번호
	 * @return 채팅내역을 저장한 Map객체
	 */
	public List<Map<String, Object>> selectChat(int memberNo);
	
	/**
	 * ExpertVo를 인수로 받아 전문가 테이블에 저장하는 메서드
	 * 
	 * @param expertVo 전문가의 정보를 저장하고 있는 ExpertVo
	 * @return 성공 : 1, 실패 : 0
	 */
	public int insertExpert(ExpertVO expertVo);
	
	/**
	 * 회원번호를 인수로 받아 전문가의 서비스 내역을 조회하는 메서드
	 * 
	 * @param memberNo 전문가의 회원번호
	 * @return 전문가의 서비스내역을 저장한 Map객체
	 */
	public List<Map<String, Object>> selectExpertServieHistory(int memberNo);
	
	/**
	 * 조회할 날짜와 회원번호를 인수로 받아 매출의 통계를 조회하는 메서드
	 * 
	 * @param param 조회할 날짜와 회원번호가 저장된 Map객체
	 * @return 매출액 통계를 저장한 Map객체 List
	 */
	public List<Map<String, Object>> selectSalesStatistics(Map<String, Object> param);
	
	/**
	 * 신고 카테고리를 조회하는 메서드
	 * 
	 * @return 신고카테고리
	 */
	public List<Map<String, Object>> selectReportCategory();
	
	//전체 글갯수 
	public int getTotalCount(Map<String, Object> map);

	public PageVO pageInfo(String string, int page, String stype, String sword, int mem_no);
	
	/** 
	 * 신고내용을 저장하는 메소드
	 * @param vo 신고 정보를 담은 vo 객체
	 * @return 성공 : 1, 실패 : 0
	 */
	public int insertReport(ReportVO vo);
	
	/**
	 * 회원의 번호 인수로 받아 회원 정보를 조회하는 메서드
	 * 
	 * @param memberNo 조회할 회원의 회원번호
	 * @return 회원 정보를 저장한 MemberVO객체
	 */
	public MemberVO selectMemberInfoByNo(int memberNo);
}
