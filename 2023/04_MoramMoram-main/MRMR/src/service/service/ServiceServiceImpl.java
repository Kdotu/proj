package service.service;

import java.util.List;
import java.util.Map;

import service.dao.IServiceDao;
import service.dao.ServiceDaoImpl;
import vo.AddressVO;
import vo.ExpertVO;
import vo.MemWishlistVO;
import vo.ServiceCategoryMainVO;
import vo.ServiceCategorySubVO;
import vo.ServiceVO;


public class ServiceServiceImpl implements IServiceService {
	private IServiceDao dao;
	private static ServiceServiceImpl service;

	private ServiceServiceImpl() {
		dao = ServiceDaoImpl.getDao();
	}

	public static ServiceServiceImpl getservice() {
		if (service == null)
			service = new ServiceServiceImpl();
		return service;
	}
	
	@Override
	public List<ServiceVO> selectServiceList(int num) {
		return dao.selectServiceList(num);
	}

	@Override
	public List<ServiceVO> serviceSubList(int num) {
		// TODO Auto-generated method stub
		return dao.serviceSubList(num);
	}

	@Override
	public ServiceVO serviceDetail(int num) {
		// TODO Auto-generated method stub
		return dao.serviceDetail(num);
		
	}
	@Override
	public List<ServiceVO> selectServiceAllList() {
		return dao.selectServiceAllList();
	}
	

	@Override
	public int selectServiceCount() {
		return dao.selectServiceCount();
	}

	@Override
	public List<ServiceVO> selectServicePage(Map<String, Object> pageMap) {
		return dao.selectServicePage(pageMap);
	}

	@Override
	public ExpertVO selectServiceExpert(int service_no) {
		return dao.selectServiceExpert(service_no);
	}

	@Override
	public double selectServiceRate(int service_no) {
		return dao.selectServiceRate(service_no);
	}

	@Override
	public AddressVO selectServiceAddress(int service_no) {
		return dao.selectServiceAddress(service_no);
	}

	@Override
	public List<ServiceCategoryMainVO> selectMainCategory() {
		return dao.selectMainCategory();
	}
	
	@Override
	public List<ServiceCategorySubVO> serviceSubCategory(int mainNum) {
		return dao.serviceSubCategory(mainNum);
	}

	@Override
	public int serviceInsert(ServiceVO serviceVO) {
		// TODO Auto-generated method stub
		return dao.serviceInsert(serviceVO);
	}

	@Override
	public int serviceDelete(int serviceNo) {
		// TODO Auto-generated method stub
		return dao.serviceDelete(serviceNo);
	}

	@Override
	public List<ServiceVO> myServiceList(int memNo) {
		// TODO Auto-generated method stub
		return dao.myServiceList(memNo);
	}

	@Override
	public int addressInsert(AddressVO addVO) {
		// TODO Auto-generated method stub
		return dao.addressInsert(addVO);
	}

	@Override
	public String updateSubCategoryName(int serviceNo) {
		// TODO Auto-generated method stub
		return dao.updateSubCategoryName(serviceNo);
	}

	@Override
	public AddressVO updateAddr(int serviceNo) {
		// TODO Auto-generated method stub
		return dao.updateAddr(serviceNo);
	}

	@Override
	public int addressUpdate(AddressVO addVO) {
		// TODO Auto-generated method stub
		return dao.addressUpdate(addVO);
	}

	@Override
	public int serviceUpdate(ServiceVO serviceVO) {
		// TODO Auto-generated method stub
		return dao.serviceUpdate(serviceVO);
		
	}

	@Override
	public int memWishList(MemWishlistVO memWishVO) {
		// TODO Auto-generated method stub
		return dao.memWishList(memWishVO);
	}

	@Override
	public int countWishlist(MemWishlistVO memWishVO) {
		// TODO Auto-generated method stub
		return dao.countWishlist(memWishVO);
	}

	@Override
	public int deleteMemWishlist(MemWishlistVO memWishVO) {
		// TODO Auto-generated method stub
		return dao.deleteMemWishlist(memWishVO);
	}

	@Override
	public List<ServiceVO> myWishlist(int memNo) {
		// TODO Auto-generated method stub
		return dao.myWishlist(memNo);
	}


	
}
