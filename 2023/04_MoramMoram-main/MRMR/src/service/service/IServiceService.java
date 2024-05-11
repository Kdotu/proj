package service.service;

import java.util.List;
import java.util.Map;

import vo.AddressVO;
import vo.ExpertVO;
import vo.MemWishlistVO;
import vo.ServiceCategoryMainVO;
import vo.ServiceCategorySubVO;
import vo.ServiceVO;

public interface IServiceService {

	//서비스 메인 카테고리 리스트 가져오기
	public List<ServiceVO> selectServiceList(int num);
	
	//서비스 서브 카테고리 리스트 가져오기 
	public List<ServiceVO> serviceSubList(int num);
	
	//서비스하나의 상세정보 가져오기
	public ServiceVO serviceDetail(int num);


	//모든 서비스(상품) 가져오기
	public List<ServiceVO> selectServiceAllList();

	//메인 카테고리 목록 가져오기
	public List<ServiceCategoryMainVO> selectMainCategory();

	//서비스카테고리 목록 가져오기
	public List<ServiceCategorySubVO> serviceSubCategory(int mainNum);

	//서비스등록
	public int serviceInsert(ServiceVO serviceVO);

	//서비스 삭제
	public int serviceDelete(int serviceNo);

	//마이페이지에 내 서비스목록 불러오기
	public List<ServiceVO> myServiceList(int memNo);


	//주소 추가 메서드
	public int addressInsert(AddressVO addVO);
	
	// 등록된 전체 서비스 갯수 (승인 o)
	/**
	 * 
	 * @return 전체 서비스의 갯수 
	 */
	public int selectServiceCount();
	
	/**
	 * 서비스 페이징 처리 메서드
	 * @param 페이지의 정보를 담은 pageMap
	 * @return 페이지 내에 있는 Service정보를 담고 있는 List객체
	 */
	public List<ServiceVO> selectServicePage(Map<String,Object> pageMap);
	
	
	/**
	 * 서비스 번호를 가진 전문가의 정보를 불러오는 메서드
	 * @param service_no
	 * @return 전문가의 정보가 담긴 ExpertVO객체
	 */
	public ExpertVO selectServiceExpert(int service_no);
	
	/**
	 * 서비스 번호를 인수값으로 서비스의 평점 정보를 가져오는 메서드
	 * @param service_no
	 * @return 서비스 평점
	 */
	public double selectServiceRate(int service_no);
	
	/**
	 * 서비스 번호를 인수값으로 받아 지도api의 정보를 가져오는 메서드
	 * @param service_no
	 * @return 해당 서비스의 지도 정보가 담긴 AddressVO 
	 */
	public AddressVO selectServiceAddress(int service_no);
	
	
	//서비스 번호로 서비스 서브 카테고리 이름 가져오기 
	public String updateSubCategoryName(int serviceNo);
	
	//서비스 수정시 서비스 번호로 주소정보 가져오기
	public AddressVO updateAddr(int serviceNo);

	
	//서비스 수정시 주소를 수정하는 메서드
	public int addressUpdate(AddressVO addVO);

	
	//서비스 수정시 서비스를 수정하여 재등록하는 메서드
	public int serviceUpdate(ServiceVO serviceVO);

	//위시리스트 여부를 가져오는 메서드
	public int countWishlist(MemWishlistVO memWishVO);
	
	//위시리스트 추가 
	public int memWishList(MemWishlistVO memWishVO);
	
	//위시리스트삭제하는 메서드
	public int deleteMemWishlist(MemWishlistVO memWishVO);

	//내 위시리스트를 가져오는 메서드
	public List<ServiceVO> myWishlist(int memNo);
	

	
}
