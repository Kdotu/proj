package cart.dao;

import java.util.List;
import java.util.Map;

import vo.CartVO;

public interface CartDao {

	// 주문내역 불러오기
	//public List<CartVO> selectCartList();
	/**
	 * CartVO의 정보를 이용해 CART 테이블에 insert하는 메서드
	 * @param cartVo 주문 시 필요한 정보를 가지고 있는 CartVO객체
	 * @return 성공 : 1, 실패 : 0
	 */
	public int insertCart(CartVO cartVO);
	
	// 주문상태 변경하기
	/**
	 * 주문번호와 주문상태를 인수값으로 받아 주문 상태를 변경하는 메소드
	 * @param 주문번호와 주문 상태를 가진 Map
	 * @return 성공 : 1, 실패 : 0
	 */
	public int updateCartState(Map<String,Object> cartMap);
	
	
	/**
	 * 회원번호를 인수값으로 받아 주문내역을 가져오는 메소드
	 * @param mem_no 회원번호
	 * @return 주문정보를 담은 List
	 */
	public List<CartVO> selectCartList(int mem_no);
}
