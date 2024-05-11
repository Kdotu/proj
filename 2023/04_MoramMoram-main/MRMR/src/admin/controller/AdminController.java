package admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.service.AdminServiceImpl;
import admin.service.IAdminService;
import vo.MemberVO;
import vo.PageVO;
import vo.ServiceCategorySubVO;

public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IAdminService service;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		service = AdminServiceImpl.getInstance();
	}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		
	    //서비스 승인할 리스트를 가져오는 메서드
	     if(command.equals("/admin/serviceApprovedlist.do")){
	    	  serviceApprovedlist(request,response);
	      }
	     
	     //관리자가 서비스를 승인해줄 메서드
	     if(command.equals("/admin/serviceApproved.do")){
	    	 serviceApproved(request,response);
	      }
	     
		 //관리자가 승인된 서비스 목록을 조회하시는 메서드
	     if(command.equals("/admin/serviceApprovedConfirmlist.do")){
	    	 serviceApprovedConfirmlist(request,response);
	     }
	      
		//관리자가 회원목록을 조회하시는 메서드
		if(command.equals("/admin/selectMemberlist.do")){
			selectMemberlist(request,response);
		}
		    
		//블랙리스트 회원등급 변경
		if(command.equals("/admin/memGradeChange.do")){
			try {
				memGradeChange(request,response);
			} catch (SQLException e) {
				System.out.println("memGradeChange 오류");
				e.printStackTrace();
			}
		}
		    
		// 신고내역 페이지로 이동
	    if(command.equals("/admin/report.do")){
	    	reportList(request,response);
	    }
	    	
		// 결재내역 페이지로 이동
		if(command.equals("/admin/cartList.do")){
		    cartList(request,response);	
		}
		
		// 카테고리 관리 페이지로 이동
		if(command.equals("/admin/serviceCategory.do")){
			categoryList(request,response);
		}
    }
    
    
    
	//블랙리스트 회원등급 변경
	private void memGradeChange(HttpServletRequest request, HttpServletResponse response)  throws SQLException, ServletException, IOException{
		int memNo = Integer.parseInt(request.getParameter("memNo"));
		service.memGradeChange(memNo);
		request.getRequestDispatcher("/WEB-INF/view/member/myPage.jsp").forward(request,response);
	}

	//관리자가 승인된 서비스 목록을 조회하는 메서드
    private void serviceApprovedConfirmlist(HttpServletRequest request, HttpServletResponse response) {
    	List<Map<String,Object>> list  = service.serviceApprovedConfirmlist();
		request.setAttribute("serviceApprovedConfirmlist", list);
		
		 try {
			request.getRequestDispatcher("/WEB-INF/view/admin/serviceApprovedConfirm.jsp").forward(request,response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void selectMemberlist(HttpServletRequest request, HttpServletResponse response) {
		int page = Integer.parseInt(request.getParameter("page"));
		String stype = request.getParameter("stype");
		String sword = request.getParameter("sword");
		
		PageVO pvo = service.pageInfo(page, stype, sword);
		
		Map<String, Object>  map = new HashMap<String, Object>();
		map.put("start", pvo.getStart());
		map.put("end", pvo.getEnd());
		map.put("stype", stype);
		map.put("sword", sword);
		
		List<Map<String, Object>> memberlist = (List<Map<String, Object>>) service.selectMemberlist(map);
		
    	request.setAttribute("memberlist", memberlist);
		request.setAttribute("startpage", pvo.getStartPage());
		request.setAttribute("endpage", pvo.getEndPage());
		request.setAttribute("totalpage", pvo.getTotalPage());
    	try {
			request.getRequestDispatcher("/WEB-INF/view/admin/memberList.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//관리자가 서비스를 승인해줄 메서드
    private void serviceApproved(HttpServletRequest request, HttpServletResponse response) {
		
		int serviceNo = Integer.parseInt(request.getParameter("serviceNo")); 
		System.out.println(serviceNo);
    	int res = service.serviceApproved(serviceNo);

    	PrintWriter out;
		try {
			out = response.getWriter();
			out.print(res);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//서비스 승인할 리스트를 가져오는 메서드
	private void serviceApprovedlist(HttpServletRequest request, HttpServletResponse response) {
		
		List<Map<String,Object>> list  = service.serviceApprovedlist();
		request.setAttribute("serviceApprovedlist", list);
		
		 try {
			request.getRequestDispatcher("/WEB-INF/view/admin/serviceApproved.jsp").forward(request,response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void categoryList(HttpServletRequest request, HttpServletResponse response) {
		String mainName = request.getParameter("mainName");
		String subName = request.getParameter("subName");
		String No = request.getParameter("mainNo");
		if(mainName != null || subName != null) {
			
			if(mainName != null && mainName.length() > 0) { // 대분류명이 입력됨
				service.insertMainCategoryList(mainName);
			}else { // 소분류명이 입력됨
				ServiceCategorySubVO subVO = new ServiceCategorySubVO();
				int mainNo = Integer.parseInt(No);
				subVO.setService_category_main_no(mainNo);
				subVO.setService_category_sub_name(subName);
				service.insertSubCategoryList(subVO);
			}
		}
		
		List<Map<String, Object>> categoryList = (List<Map<String, Object>>) service.selectCategoryList();
		request.setAttribute("categoryList", categoryList);
		
		try {
			request.getRequestDispatcher("/WEB-INF/view/admin/serviceCategory.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void cartList(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> map = new HashMap<String, Object>();
		String stype = request.getParameter("stype");
		String sword = request.getParameter("sword");
		map.put("stype", stype);
		map.put("sword", sword);
		// 검색한 주문 내역 가져오기
		List<Map<String, Object>> cartList = (List<Map<String, Object>>) service.selectCartList(map);
		
		request.setAttribute("cartList", cartList);
		
		try {
			request.getRequestDispatcher("/WEB-INF/view/admin/cartList.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void reportList(HttpServletRequest request, HttpServletResponse response) {
		// 전체 내용 다 받아오기
		List<Map<String, Object>> reportList = (List<Map<String, Object>>) service.selectAllBadMemInfo();
		
		// 멤버별 신고받은 횟수 카운트
		List<Map<String, Object>> reportCount = (List<Map<String, Object>>) service.selectReportCount();
		
		request.setAttribute("reportCount", reportCount);
		request.setAttribute("reportList", reportList);
		try {
			request.getRequestDispatcher("/WEB-INF/view/admin/reportList.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		
	}

}
