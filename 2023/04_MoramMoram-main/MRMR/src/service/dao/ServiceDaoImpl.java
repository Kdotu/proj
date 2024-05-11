package service.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import config.SqlMapClientFactory;
import vo.AddressVO;
import vo.ExpertVO;
import vo.MemWishlistVO;
import vo.ServiceCategoryMainVO;
import vo.ServiceCategorySubVO;
import vo.ServiceVO;

public class ServiceDaoImpl implements IServiceDao {
	private SqlMapClient smc;
	private static ServiceDaoImpl dao;

	private ServiceDaoImpl() {
		smc = SqlMapClientFactory.getSqlMapClient();
	}

	public static ServiceDaoImpl getDao() {
		if (dao == null)
			dao = new ServiceDaoImpl();
		return dao;
	}

	
	@Override
	public List<ServiceVO> selectServiceList(int num) {
		List<ServiceVO> serviceList = null;
		
		try {
			serviceList = smc.queryForList("service.selectServiceList", num);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return serviceList;
	}

	@Override
	public List<ServiceVO> serviceSubList(int num) {
		List<ServiceVO> serviceList = null;
		
		try {
			serviceList = smc.queryForList("service.serviceSubList", num);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return serviceList;
	}

	@Override
	public ServiceVO serviceDetail(int num) {
		ServiceVO serviceVO = null;
		
		try {
			serviceVO = (ServiceVO)smc.queryForObject("service.serviceDetail", num);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return serviceVO;
	}

	@Override
	public List<ServiceVO> selectServiceAllList() {
		List<ServiceVO> serviceVO =null;
		
		try {
			serviceVO = smc.queryForList("service.selectServiceAllList");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return serviceVO;
	}
	
	@Override
	public List<ServiceCategoryMainVO> selectMainCategory() {
		List<ServiceCategoryMainVO> list = null;
		
		try {
			list = smc.queryForList("service.selectMainCategory");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<ServiceCategorySubVO> serviceSubCategory(int mainNum) {
		List<ServiceCategorySubVO> list = null;
		
		try {
			list = smc.queryForList("service.serviceSubCategory", mainNum);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int serviceInsert(ServiceVO serviceVO) {
		int cnt = 0;
		
		try {
			cnt = (int) smc.insert("service.serviceInsert",serviceVO);
		} catch (SQLException e) {
			e.printStackTrace();
			cnt=0;
		}
		return cnt;
	}

	@Override
	public int serviceDelete(int serviceNo) {
		int cnt = 0;
		try {
			cnt = smc.delete("service.serviceDelete",serviceNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public List<ServiceVO> myServiceList(int memNo) {
		List<ServiceVO> list = null;
		try {
			list = smc.queryForList("service.myServiceList", memNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int addressInsert(AddressVO addVO) {
		int cnt = 0;
		
		
		try {
			cnt = (int)smc.insert("service.addressInsert",addVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int selectServiceCount() {
		int cnt = 0;
		try {
			cnt = (Integer)smc.queryForObject("service.selectServiceCount");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public List<ServiceVO> selectServicePage(Map<String, Object> pageMap) {
		List<ServiceVO> serviceList = null;
		
		try {
			serviceList = (List<ServiceVO>)smc.queryForList("service.selectServicePage", pageMap);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return serviceList;
	}

	@Override
	public ExpertVO selectServiceExpert(int service_no) {
		ExpertVO vo = null;
		try {
			vo =(ExpertVO)smc.queryForObject("service.selectServiceExpert",service_no);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public double selectServiceRate(int service_no) {
		double rate_point = 0;
		
		try {
			rate_point = (Double) smc.queryForObject("service.selectServiceRate",service_no);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rate_point;
	}

	
	@Override
	public AddressVO selectServiceAddress(int service_no) {
		AddressVO addrVO = null;
		
		try {
			addrVO = (AddressVO) smc.queryForObject("service.selectServiceAddress", service_no);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return addrVO;
	}

	@Override
	public String updateSubCategoryName(int serviceNo) {
		String updateSubCategoryName = null;
		
		try {
			updateSubCategoryName = (String)smc.queryForObject("service.updateSubCategoryName", serviceNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return updateSubCategoryName;
	}

	@Override
	public AddressVO updateAddr(int serviceNo) {
		AddressVO addrVO = null;
		
		try {
		 addrVO = (AddressVO)smc.queryForObject("service.updateAddr", serviceNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return addrVO;
	}

	@Override
	public int addressUpdate(AddressVO addVO) {
		// TODO Auto-generated method stub
		int res= 0;
		
		try {
			res = smc.update("service.addressUpdate",addVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public int serviceUpdate(ServiceVO serviceVO) {
		int res= 0;
		
		try {
			res= smc.update("service.serviceUpdate", serviceVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
		
	}

	@Override
	public int memWishList(MemWishlistVO memWishVO) {
		int res = 0;
		try {
			Object obj = smc.insert("service.memWishList",memWishVO);
			if(obj==null) res=1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int countWishlist(MemWishlistVO memWishVO) {
		int res = 0;
		
		try {
			res= (int)smc.queryForObject("service.countWishlist", memWishVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public int deleteMemWishlist(MemWishlistVO memWishVO) {
		int res =0;
		
		try {
			res = smc.delete("service.deleteMemWishlist",memWishVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public List<ServiceVO> myWishlist(int memNo) {
		// TODO Auto-generated method stub
		List<ServiceVO> myWishlist = null;
		
		try {
			myWishlist = smc.queryForList("service.myWishlist", memNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return myWishlist;
	}


}
