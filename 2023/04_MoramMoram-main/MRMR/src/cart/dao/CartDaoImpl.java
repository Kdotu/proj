package cart.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import config.SqlMapClientFactory;
import vo.CartVO;

public class CartDaoImpl implements CartDao {
	private static CartDaoImpl instance;
	private SqlMapClient smc;
	
	private CartDaoImpl() {
		smc = SqlMapClientFactory.getSqlMapClient();
	}
	public static CartDaoImpl getInstance() {
		if(instance == null) instance = new CartDaoImpl();
		return instance;
	}
	
	@Override
	public int insertCart(CartVO cartVO) {
		int cnt = 0;
		try {
			cnt = smc.update("cart.insertCart",cartVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	@Override
	public int updateCartState(Map<String,Object> cartMap) {
		int cnt = 0;
		
		try {
			cnt = smc.update("cart.updateCartState", cartMap);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	@Override
	public List<CartVO> selectCartList(int mem_no) {
		List<CartVO> cartList = null;
		
		try {
			cartList = (List<CartVO>)smc.queryForList("cart.selectCartList", mem_no);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return cartList;
	}

}
