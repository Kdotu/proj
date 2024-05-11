package admin.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import admin.dao.AdminDaoImpl;
import admin.dao.IAdminDao;
import vo.MemberVO;
import vo.PageVO;
import vo.ServiceCategorySubVO;

public class AdminServiceImpl implements IAdminService{
	private static AdminServiceImpl instance;
	private IAdminDao dao;

	private AdminServiceImpl() {
		dao = AdminDaoImpl.getInstance();
	}

	public static IAdminService getInstance() {
		if (instance == null)
			instance = new AdminServiceImpl();
		return instance;
	}

	@Override
	public List<Map<String,Object>> serviceApprovedlist() {
		return dao.serviceApprovedlist();
	}

	@Override
	public int serviceApproved(int serviceNo) {
		return dao.serviceApproved(serviceNo);
	}

	@Override
	public List<Map<String, Object>> selectMemberlist(Map<String, Object> map) {
		return dao.selectMemberlist(map);
	}

	@Override
	public List<Map<String, Object>> serviceApprovedConfirmlist() {
		return dao.serviceApprovedConfirmlist();
	}

	@Override
	public List<Map<String, Object>> selectReportCount() {
		return dao.selectReportCount();
	}

	@Override
	public List<Map<String, Object>>  selectBadMemInfo(int num) {
		return dao.selectBadMemInfo(num);
	}

	@Override
	public List<Map<String, Object>>  selectAllBadMemInfo() {
		return dao.selectAllBadMemInfo();
	}

	@Override
	public List<Map<String, Object>> selectCartList(Map<String,Object> keyMap) {
		return dao.selectCartList(keyMap);
	}

	@Override
	public List<Map<String, Object>> selectCategoryList() {
		return dao.selectCategoryList();
	}

	@Override
	public int insertMainCategoryList(String categoryName) {
		return dao.insertMainCategoryList(categoryName);
	}

	@Override
	public int insertSubCategoryList(ServiceCategorySubVO subVO) {
		return dao.insertSubCategoryList(subVO);
	}

	@Override
	public PageVO pageInfo(int page, String stype, String sword) {
		Map<String, Object>  map = new HashMap<String, Object>();
		map.put("stype", stype);
		map.put("sword", sword);
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
	public void memGradeChange(int memNo) throws SQLException{
		dao.memGradeChange(memNo);
	}
}
