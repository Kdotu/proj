package admin.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import vo.MemberVO;
import vo.ServiceCategorySubVO;

public interface IAdminDao {
    //서비스 승인할 리스트를 가져오는 메서드
	public List<Map<String,Object>> serviceApprovedlist();
	
	//서비스 승인 메서드
	public int serviceApproved(int serviceNo);
	
	//회원목록을 가져오는 메서드
	public List<Map<String, Object>> selectMemberlist(Map<String, Object> map);
	
	 //관리자가 승인된 서비스 목록을 조회하는 메서드
	public List<Map<String,Object>> serviceApprovedConfirmlist();
	
	
	/**
	 * 신고받은 수와 신고받은 사람의 회원번호, 신고받은 사람의 이름를 담은
	 * 리스트를 반환하는 메소드
	 * @return 
	 */
	public List<Map<String,Object>> selectReportCount();	
	
	/**
	 * 회원번호를 입력받아 회원이 신고한 내역을 가져오는 메소드
	 * @param num 신고한 회원의 번호
	 * @return 회원이 신고한 신고내역
	 */
	public List<Map<String,Object>> selectBadMemInfo(int num);
	
	/**
	 * 전체 신고내역을 가져하는 메소드
	 * @return
	 */
	public List<Map<String,Object>> selectAllBadMemInfo();

	/**
	 * 결제 정보를 담은 map을 입력받아 해당 결제내역을 가져오는 메소드
	 * @param keyMap
	 * @return
	 */
	public List<Map<String,Object>> selectCartList(Map<String,Object> keyMap);
	
	/**
	 * 카테고리 전체 정보를 가져오는 메소드
	 * @return
	 */
	public List<Map<String,Object>> selectCategoryList();
	
	/**
	 * 대분류 카테고리 추가 메소드
	 * @param categoryName 대분류 카테고리명
	 * @return 성공 : 1, 실패 : 0
	 */
	public int insertMainCategoryList(String categoryName);
	
	/**
	 * 소분류 카테고리 추가 메소드
	 * @param categoryMap 소분류명과 대분류 번호를 가진 Map
	 * @return 성공 : 1, 실패 : 0
	 */
	public int insertSubCategoryList(ServiceCategorySubVO subVO);
	
	//전체 갯수 
	public int getTotalCount(Map<String, Object>  map) throws SQLException;

	public void memGradeChange(int memNo) throws SQLException;

}



