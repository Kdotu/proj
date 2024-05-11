package service.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.google.gson.Gson;

import member.service.MemberService;
import member.service.MemberServiceImpl;
import service.service.IServiceService;
import service.service.ServiceServiceImpl;
import vo.AddressVO;
import vo.ExpertVO;
import vo.MemWishlistVO;
import vo.MemberVO;
import vo.ServiceCategoryMainVO;
import vo.ServiceCategorySubVO;
import vo.ServiceVO;

@MultipartConfig(
		fileSizeThreshold = 1024 * 1024 * 10, 
		maxFileSize = 1024 * 1024 * 30,
		maxRequestSize = 1024 * 1024 * 100
	)
public class ServiceController extends HttpServlet {

	IServiceService service = ServiceServiceImpl.getservice();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 내가 요청한 모든 Method 'get'방식의 url을 다 받는다.
		String requestURI = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = requestURI.substring(contextPath.length());

		// command로 남는값은 /board/List.do, /board/read.do와 같은 url이다

		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=utf-8");

		// 메인카테고리의 리스트(상품) 가져오기
		if (command.equals("/service/categoryMain.do")) {
			ServiceList(req, resp);
		}

		// 서브카테고리 리스트(상품) 가져오기
		if (command.equals("/service/categorySub.do")) {
			serviceSubList(req, resp);
		}

		// 서비스클릭시 상세페이지 정보 가져오기 ==> detail페이지로 이동
		if (command.equals("/service/serviceDetail.do")) {
			serviceSendDetail(req, resp);
		}

		// 서비스등록시 전체 카테고리 가져오기
		if (command.equals("/service/selectMainCategory.do")) {
			selectMainCategory(req, resp);
		}

		// 서비스등록시 서브 카테고리 가져오기
		if (command.equals("/service/serviceSubCategory.do")) {
			serviceSubCategory(req, resp);
		}

		// 마이페이지에서 내 서비스 리스트 가져오기
		if (command.equals("/service/myServiceList.do")) {
			myServiceList(req, resp);
		}

		// 서비스삭제
		if (command.equals("/service/serviceDelete.do")) {
			serviceDelete(req, resp);
		}

		// 서비스 수정폼으로 가기
		if (command.equals("/service/serviceUpdate.do")) {
			serviceSendUpdate(req, resp);
		}

		// 위시리스트 추가,삭제 버튼 (하트)
		if (command.equals("/service/memWishlist.do")) {
			memWishList(req, resp);
		}

		// 내 위시리스트 목록을 가져오는 메서드
		if (command.equals("/service/myWishlist.do")) {
			myWishlist(req, resp);
		}

		// 내 위시리스트 삭제
		if (command.equals("/service/mywishDelete.do")) {
			memWishList(req, resp);
		}

		// 서비스 등록(get)
		if (command.equals("/service/serviceInsert.do")) {
			req.getRequestDispatcher("/WEB-INF/view/service/serviceInsert.jsp").forward(req, resp);
		}
	}

	// 내 위시리스트 서비스 목록을 가져오는 메서드
	private void myWishlist(HttpServletRequest req, HttpServletResponse resp) {
		int memNo = getMemNo(req, resp);

		List<ServiceVO> Wishlist = service.myWishlist(memNo);
		System.out.println(Wishlist.toString());
		req.setAttribute("Wishlist", Wishlist);
		
		try {
			req.getRequestDispatcher("/WEB-INF/view/member/wishList.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		String wishJson = null;
//
//		Gson gson = new Gson();
//
//		if (Wishlist == null || Wishlist.size() == 0) {
//			wishJson = "찜 목록이 없습니다.";
//			wishJson = gson.toJson(wishJson);
//		} else {
//			wishJson = gson.toJson(Wishlist);
//		}
//
//		PrintWriter out;
//		try {
//			out = resp.getWriter();
//			out.write(wishJson);
//			out.flush();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

	}
	

	private int getMemNo(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		Map<String, Object> loginInfo = (Map<String, Object>)session.getAttribute("loginInfo");
		MemberVO memberInfo = (MemberVO)loginInfo.get("memberInfo");
		
		return memberInfo.getMem_no();
	}
	
	//서비스 클릭시 정보를 가져오는 메서드 
	private void serviceSendDetail(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		int serviceNo = Integer.parseInt(req.getParameter("serviceNo"));
		
		if((boolean)session.getAttribute("isLogined")) {
			int memNo = getMemNo(req, resp);

			// 찜한목록....
			MemWishlistVO memWishVO = new MemWishlistVO();
			memWishVO.setMem_no(memNo);
			memWishVO.setService_no(serviceNo);

			// 위시리스트여부를 가져오는 메서드
			int countWishlist = service.countWishlist(memWishVO);
			req.setAttribute("countWishlist", countWishlist);
		}
		
		ServiceVO serviceVO = service.serviceDetail(serviceNo);
		serviceVO.setService_info(serviceVO.getService_info().replaceAll("\n", "<br>"));
		
		ExpertVO evo = service.selectServiceExpert(serviceNo); // 전문가정보 가져오기
		
		MemberService mservice = MemberServiceImpl.getInstance();
		MemberVO mvo = mservice.selectMemberInfoByNo(evo.getMem_no()); // 서비스 제공 전문가의 정보
		
		AddressVO avo = service.selectServiceAddress(serviceNo); // 주소정보
		double rate_point = service.selectServiceRate(serviceNo);// 평점정보
		
		req.setAttribute("MemberVO", mvo);
		req.setAttribute("serviceVO", serviceVO);
		req.setAttribute("ExpertVO", evo);
		req.setAttribute("AddressVO", avo);
		req.setAttribute("rate_point", rate_point);

		try {
			req.getRequestDispatcher("/WEB-INF/view/service/serviceDetail.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private void memWishList(HttpServletRequest req, HttpServletResponse resp) {

		int serviceNo = Integer.parseInt(req.getParameter("serviceNo"));
		int memNo = getMemNo(req, resp);
		int countWishlist = Integer.parseInt(req.getParameter("countWishlist"));

		int res = 0;

		MemWishlistVO memWishVO = new MemWishlistVO();
		memWishVO.setMem_no(memNo);
		memWishVO.setService_no(serviceNo);

		if (countWishlist == 1) { // 찜한상태
			// 삭제
			res = service.deleteMemWishlist(memWishVO);

		} else { // 찜 안한상태
			// insert
			res = service.memWishList(memWishVO);
		}

		try {
			PrintWriter out = resp.getWriter();
			out.print(res);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	private void serviceSendUpdate(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int serviceNo = Integer.parseInt(req.getParameter("serviceNo"));
		ServiceVO serviceVo = service.serviceDetail(serviceNo);
		serviceVo.setService_info(serviceVo.getService_info().replaceAll("\r", "").replaceAll("\n", "<br>"));
//		serviceVo.setService_info(serviceVo.getService_info().replaceAll("<br>", "\n"));
		req.setAttribute("serviceInfo", serviceVo);
		String updateSubCategoryName = service.updateSubCategoryName(serviceNo);
		req.setAttribute("selectSubCategoryName", updateSubCategoryName);
		AddressVO addrVo = service.updateAddr(serviceNo);
		req.setAttribute("updateAddr", addrVo);

		req.getRequestDispatcher("/WEB-INF/view/service/serviceUpdate.jsp").forward(req, resp);
	}

	private void myServiceList(HttpServletRequest req, HttpServletResponse resp) {
		int memNo = getMemNo(req, resp);
		List<ServiceVO> list = service.myServiceList(memNo);
		
		req.setAttribute("myServiceList", list);
		try {
			req.getRequestDispatcher("/WEB-INF/view/member/myServiceList.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 서비스등록시 서브 카테고리 가져오기
	private void serviceSubCategory(HttpServletRequest req, HttpServletResponse resp) {

		int mainNum = Integer.parseInt(req.getParameter("mainCategory"));

		List<ServiceCategorySubVO> list = service.serviceSubCategory(mainNum);

		Gson gson = new Gson();
		try {
			PrintWriter out = resp.getWriter();
			out.write(gson.toJson(list));

			out.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// 서비스등록시 전체 카테고리 가져오기
	private void selectMainCategory(HttpServletRequest req, HttpServletResponse resp) {
		List<ServiceCategoryMainVO> list = service.selectMainCategory();

		Gson gson = new Gson();
		try {
			PrintWriter out = resp.getWriter();
			out.write(gson.toJson(list));

			out.flush();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private void serviceSubList(HttpServletRequest req, HttpServletResponse resp) {
		int num = Integer.parseInt(req.getParameter("sub_no"));
		List<ServiceVO> serviceSubList = new ArrayList<>();

		serviceSubList = service.serviceSubList(num);

		req.setAttribute("serviceSubList", serviceSubList);

		try {
			req.getRequestDispatcher("/WEB-INF/view/service/serviceMain.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void ServiceList(HttpServletRequest req, HttpServletResponse resp) {
		int num = Integer.parseInt(req.getParameter("main_no"));
		List<ServiceVO> serviceList = new ArrayList<>();

		if (num == 0) {
			serviceList = service.selectServiceAllList();
		} else {
			serviceList = service.selectServiceList(num);
		}
		req.setAttribute("serviceList", serviceList);

		try {
			req.getRequestDispatcher("/WEB-INF/view/service/serviceMain.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	// 서비스 삭제
	private void serviceDelete(HttpServletRequest req, HttpServletResponse resp) {

		int serviceNo = Integer.parseInt(req.getParameter("serviceNo"));
		int result = service.serviceDelete(serviceNo);

		try {
			PrintWriter out = resp.getWriter();
			out.print(result);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String requestURI = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = requestURI.substring(contextPath.length());

		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=utf-8");

		if (command.equals("/service/serviceInsert2.do")) {
			serviceInsert(req, resp);
		}

		if (command.equals("/service/serviceUpdate2.do")) {
			serviceUpdate(req, resp);

		}

	}

	// 서비스 등록(post)
	private void serviceInsert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String longitude = req.getParameter("longitude");
		String latitude = req.getParameter("latitude");
		String zip = req.getParameter("addr_zip");
		String base = req.getParameter("addr_base");
		String detail = req.getParameter("addr_detail");
//		String file = req.getParameter("addr_detail");
		String file = req.getParameter("files");

		int addrNo = 0;
		if (longitude == null || !"".equals(longitude)) {
			AddressVO addVO = new AddressVO();
			addVO.setAddr_longi(longitude);
			addVO.setAddr_lati(latitude);
			addVO.setAddr_zip(zip);
			addVO.setAddr_road(base);
			addVO.setAddr_dong(detail);

			addrNo = service.addressInsert(addVO);
		}

		int memNo = getMemNo(req, resp);
		int servicesubcategory = Integer.parseInt(req.getParameter("service_category_sub_no"));
		String serviceName = req.getParameter("serviceName");
		String serviceInfo = req.getParameter("serviceInfo");
		int servicePrice = Integer.parseInt(req.getParameter("servicePrice").replaceAll(",", ""));

		ServiceVO serviceVO = new ServiceVO();

		serviceVO.setService_name(serviceName);
		serviceVO.setService_info(serviceInfo);
		serviceVO.setService_price(servicePrice);
		serviceVO.setMem_no(memNo);
		serviceVO.setService_category_sub_no(servicesubcategory);
		serviceVO.setAddr_no(addrNo);

		int service_no = service.serviceInsert(serviceVO);

		List<ServiceVO> list = service.myServiceList(memNo);

		req.setAttribute("myServiceList", list);

		// 마이페이지로 이동
//		req.getRequestDispatcher("/WEB-INF/view/member/myPage.jsp").forward(req, resp);
		req.getRequestDispatcher("/files/insertFiles.do?mem_no="+memNo+"&col=SERVICE_NO&file_category_no=3&service_no="+service_no).forward(req, resp);

	}

	// 서비스 수정(post)
	private void serviceUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String longitude = req.getParameter("longitude");
		String latitude = req.getParameter("latitude");
		String zip = req.getParameter("addr_zip");
		String base = req.getParameter("addr_base");
		String detail = req.getParameter("addr_detail");
		int addrNO = Integer.parseInt(req.getParameter("addrNO"));
		
		
		int serviceNo = Integer.parseInt(req.getParameter("serviceNo"));

		if (!(longitude == null) || !"".equals(longitude)) {
			AddressVO addVO = new AddressVO();
			addVO.setAddr_no(addrNO);
			addVO.setAddr_longi(longitude);
			addVO.setAddr_lati(latitude);
			addVO.setAddr_zip(zip);
			addVO.setAddr_road(base);
			addVO.setAddr_dong(detail);

			service.addressUpdate(addVO);
		}

		int memNo = getMemNo(req, resp);
		int servicesubcategory = Integer.parseInt(req.getParameter("service_category_sub_no"));
		String serviceName = req.getParameter("serviceName");
		String serviceInfo = req.getParameter("serviceInfo");
		int servicePrice = Integer.parseInt(req.getParameter("servicePrice").replaceAll(",", ""));

		

		ServiceVO serviceVO = new ServiceVO();

		serviceVO.setService_no(serviceNo);
		serviceVO.setService_name(serviceName);
		serviceVO.setService_info(serviceInfo);
		serviceVO.setService_price(servicePrice);
		serviceVO.setMem_no(memNo);
		serviceVO.setService_category_sub_no(servicesubcategory);
		serviceVO.setAddr_no(addrNO);
		int res1 = service.serviceUpdate(serviceVO);

		if(req.getParameter("files") != null) {
			req.getRequestDispatcher("/files/updateFiles.do?col=SERVICE_NO&file_category_no=3&service_no="+serviceNo).forward(req, resp);
		}else {
			resp.sendRedirect(req.getContextPath() + "/member/myPage.do");
		}

		
	}
}