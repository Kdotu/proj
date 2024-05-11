package cart.service;

import java.util.List;
import java.util.Map;

import cart.dao.CartDao;
import cart.dao.CartDaoImpl;
import vo.CartVO;

public class CartServiceImpl implements CartService {
	private static CartServiceImpl instance;
	private CartDao dao;
	private CartServiceImpl() {
		dao = CartDaoImpl.getInstance();
	}
	public static CartServiceImpl getInstance() {
		if(instance == null) instance = new CartServiceImpl();
		return instance;
	}
	
	@Override
	public int insertCart(CartVO cartVO) {
		return dao.insertCart(cartVO);
	}
	@Override
	public int updateCartState(Map<String, Object> cartMap) {
		return dao.updateCartState(cartMap);
	}
	@Override
	public List<CartVO> selectCartList(int mem_no) {
		return dao.selectCartList(mem_no);
	}

}
