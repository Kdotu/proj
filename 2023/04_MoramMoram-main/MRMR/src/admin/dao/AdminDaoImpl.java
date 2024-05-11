package admin.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import config.SqlMapClientFactory;
import vo.MemberVO;
import vo.ServiceCategorySubVO;

public class AdminDaoImpl implements IAdminDao {
	private static AdminDaoImpl instance;
	private SqlMapClient smc;

	private AdminDaoImpl() {
		smc = SqlMapClientFactory.getSqlMapClient();
	}

	public static IAdminDao getInstance() {
		if (instance == null)
			instance = new AdminDaoImpl();
		return instance;
	}

	@Override
	public List<Map<String, Object>> serviceApprovedlist() {
		List<Map<String, Object>> list = null;
		try {
			list = smc.queryForList("admin.serviceApprovedlist");
			System.out.println("list22" + list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int serviceApproved(int serviceNo) {
		int res = 0;
		try {
			res = smc.update("admin.serviceApproved", serviceNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	@Override
	public List<Map<String, Object>> selectMemberlist(Map<String, Object> map) {
		List<Map<String, Object>> list = null;
		try {
			list = smc.queryForList("admin.selectMemberlist",map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> serviceApprovedConfirmlist() {
		List<Map<String, Object>> list = null;
		try {
			list = smc.queryForList("admin.serviceApprovedConfirmlist");
			System.out.println("list" + list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> selectReportCount() {
		List<Map<String, Object>> list = null;

		try {
			list = smc.queryForList("admin.selectReportCount");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> selectBadMemInfo(int num) {
		List<Map<String, Object>> list = null;
		try {
			list = smc.queryForList("admin.selectBadMemInfo", num);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> selectAllBadMemInfo() {
		List<Map<String, Object>> list = null;
		try {
			list = smc.queryForList("admin.selectAllBadMemInfo");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> selectCartList(Map<String, Object> keyMap) {
		List<Map<String, Object>> list = null;
		try {
			list = smc.queryForList("admin.selectCartList", keyMap);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> selectCategoryList() {
		List<Map<String, Object>> list = null;
		try {
			list = smc.queryForList("admin.selectCategoryList");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int insertMainCategoryList(String categoryName) {
		int cnt = 0;

		try {
			cnt = (int) smc.insert("admin.insertMainCategoryList", categoryName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int insertSubCategoryList(ServiceCategorySubVO subVO) {
		int cnt = 0;
		
		try {
			Object obj = smc.insert("admin.insertSubCategoryList", subVO);
			if(obj == null) cnt = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int getTotalCount(Map<String, Object> map) throws SQLException {
		return (int) smc.queryForObject("admin.getTotalCount", map);
	}

	@Override
	public void memGradeChange(int memNo) throws SQLException {
		smc.update("admin.memGradeChange", memNo);
	}

}
