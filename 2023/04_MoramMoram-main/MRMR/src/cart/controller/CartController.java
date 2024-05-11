package cart.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cart.service.CartService;
import cart.service.CartServiceImpl;
import service.service.IServiceService;
import service.service.ServiceServiceImpl;
import vo.CartVO;
import vo.ServiceVO;

public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CartService cartService = CartServiceImpl.getInstance();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 주문내역 가져오기
		String requestURI = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = requestURI.substring(contextPath.length());
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=utf-8");
		
		if(command.equals("/cart/cartList.do")) {
			cartList(req,resp);
		}
	}

	private void cartList(HttpServletRequest req, HttpServletResponse resp) {
		List<CartVO> cartList = new ArrayList<CartVO>();
		Map<String,Object> map = new HashMap<String, Object>();

		int mem_no = Integer.parseInt(req.getParameter("mem_no"));
		cartList = cartService.selectCartList(mem_no);
		
		req.setAttribute("cartList", cartList);
		
		try {
			req.getRequestDispatcher("/member/myPage.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 주문내역 출력창으로 이동
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String requestURI = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = requestURI.substring(contextPath.length());
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=utf-8");
		
		System.out.println(command + " 페이지 요청");
		// 주문 정보 저장
		if(command.equals("/cart/order.do")) { 
			cartOrder(req,resp);
		}
		
		if(command.equals("/cart/orderModify.do")) {
			cartModify(req,resp);
		}
	}
	private void cartOrder(HttpServletRequest req, HttpServletResponse resp) {
		// 주문할 때 필요한 정보
		int serviceNo = Integer.parseInt(req.getParameter("serviceNo")); 
		int memNo =  Integer.parseInt(req.getParameter("memNo")); 
		
		CartVO cartVO = new CartVO();
		cartVO.setService_no(serviceNo);
		cartVO.setMem_no(memNo);
		
		cartService.insertCart(cartVO);
//		cartVO.setCart_no(cart_no);
		
		// 상품 정보 불러오기
		IServiceService service = ServiceServiceImpl.getservice();
		ServiceVO serviceVO = service.serviceDetail(serviceNo);
		
		req.setAttribute("serviceVO", serviceVO);
		req.setAttribute("cartVO", cartVO);
		
		// 등록완료 후 서비스결제완료페이지로 이동
		try {
			req.getRequestDispatcher("/WEB-INF/view/service/serviceComplete.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private void cartModify(HttpServletRequest req, HttpServletResponse resp) {
		Map<String,Object> cartMap = new HashMap<String, Object>();
		
		// 주문상태를 변경할 주문 번호
		int cartNo = Integer.parseInt(req.getParameter("cart_no")); 
		String state = req.getParameter("cart_state");
		int cartState = 0;
		
		switch(state) {
		case "orderComplete" : // 주문 완료
			cartState = 1;
			break;
		case "refundRequest" : // 환불요청
			cartState = 2;
			break;
		case "refundComplete" : // 환불완료
			cartState = 3;
			break;
		case "serviceComplete" : // 서비스완료
			cartState = 4;
			break;
		}
		
		cartMap.put("cart_no", cartNo);
		cartMap.put("cart_state_no", cartState);
		
		cartService.updateCartState(cartMap);
	}


}
