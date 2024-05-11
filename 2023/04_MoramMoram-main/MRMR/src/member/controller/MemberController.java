package member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.Gson;

import member.service.MemberService;
import member.service.MemberServiceImpl;
import vo.ExpertVO;
import vo.MemberVO;
import vo.PageVO;
import vo.ReportVO;
@MultipartConfig
public class MemberController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private MemberService service;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		service = MemberServiceImpl.getInstance();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		// 내가 요청한 모든 Method 'get'방식의 URL을 다 받는다.
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		
		switch(command) {
		case "/member/join.do":	// 회원가입 페이지로 이동
			request.getRequestDispatcher("/WEB-INF/view/member/join.jsp").forward(request, response);
			break;
		case "/member/login.do":	// 로그인 페이지로 이동
			request.getRequestDispatcher("/WEB-INF/view/member/login.jsp").forward(request, response);
			break;
		case "/member/infoSearch.do":	// id/pw 찾기
			request.getRequestDispatcher("/WEB-INF/view/member/infoSearch.jsp").forward(request, response);
			break;
		case "/member/memberIdCheck.do":	// 회원가입시 id 중복확인
			MemberIdCheck(request, response);
			break;
		case "/member/logout.do":	// 로그아웃
			MemberLogout(request, response);
			break;
		case "/member/myPage.do":	// 마이페이지로 이동
			myPage(request, response);
			break;
		case "/member/updateMemberInfo.do":	// 회원정보 수정
			request.getRequestDispatcher("/WEB-INF/view/member/updateMemberInfo.jsp").forward(request, response);
			break;
		case "/member/serviceHistory.do":	// 서비스 이용내역
			ServiceHistory(request, response);
			break;
		case "/member/schedule.do":	// 일정관리 
			Schedule(request, response);
			break;
		case "/member/myBoard.do":	// 내가 쓴 게시글
			MyBoard(request, response);
			break;
		case "/member/myChatingHistory.do":	// 채팅내역
//			MyChatingHistory(request, response);
			break;
		case "/member/withdrawal.do":	// 회원 탈퇴
			Withdrawal(request, response);
			break;
		case "/member/wishlist.do":	// 위시리스트
			WishList(request, response);
			break;
		case "/member/deleteWish.do":	// 위시리스트 삭제
			DeleteWishList(request, response);
			break;
		case "/member/enrollExpert.do":	// 전문가 등록
			request.getRequestDispatcher("/WEB-INF/view/member/enrollExpert.jsp").forward(request, response);
			break;
		case "/member/expertServiceHistory.do":
			expertServiceHistory(request, response);
			break;
		case "/member/selectSalesStatistics.do":
			selectSalesStatistics(request, response);
			break;
		case "/member/memberManageMent.do":
			request.getRequestDispatcher("/WEB-INF/view/member/memberManagement.jsp").forward(request, response);
			break;
		case "/member/blackListManagement.do":
			selectReportCategory(request, response);
			break;
		case "/member/selectReportList.do":
			selectReprotList(request, response);
			break;
		case "/member/toggle.do":
			toggle(request, response);
			break;
		case "/member/existExpert.do":
			existExpert(request, response);
			break;
		}
				
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		// 내가 요청한 모든 Method 'post'방식의 URL을 다 받는다.
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		
		// command로 남는 값은 /board/list.do, /board/read.do 와 같은 URL이다.
		switch(command) {
		case "/member/join.do":
			this.MemberJoin(request, response);
			break;
		case "/member/memberIdSearch.do":	// id 찾기
			MemberIdSearch(request, response);
			break;
		case "/member/memberPasswordSearch.do":	// password 찾기
			MemberPasswordSearch(request, response);
			break;
		case "/member/login.do":
			MemberLogin(request, response);
			break;
		case "/member/updateMemberInfo.do":	// 회원정보 수정
			this.UpdateMember(request, response);
			break;
		case "/member/enrollExpert.do":	// 전문가 등록
			enrollExpert(request, response);
			break;
		case "/member/insertReport.do":
			insertReport(request,response);
		}
	}
	
	private void UpdateMember(HttpServletRequest request, HttpServletResponse response) {
		String memId = request.getParameter("mem_id");
		MemberVO memberVo = new MemberVO();
		memberVo.setMem_id(memId);
		try {
			BeanUtils.populate(memberVo, request.getParameterMap());
			service.updateMember(memberVo);
			response.sendRedirect(request.getContextPath() + "/member/myPage.do");
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ID중복확인
	private void MemberIdCheck(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("application/json; charset=utf-8");
		
		// 검색할 회원ID를 받는다.
		String memId = request.getParameter("mem_id");
		
		MemberService service = MemberServiceImpl.getInstance();
		int cnt = service.MemberIdCheck(memId);
		
		Gson gson = new Gson();
		String result = null;  // 결과가 JSON으로 저장될 변수
		
		if(cnt>0) {  // 회원ID가 중복
			result = gson.toJson("false");
		}else {		// 회원ID가 중복되지 않음
			result = gson.toJson("true");
		}
		
		try {
			response.getWriter().write(result);
			response.flushBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	// 회원가입
	private void MemberJoin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		MemberVO memberVo = new MemberVO();
		try {
			BeanUtils.populate(memberVo, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		memberVo.setMem_regno(memberVo.getMem_regno().replace("-", ""));
		int joinResult = service.MemberJoin(memberVo);	// 회원가입
		if(joinResult == 1) {	// 회원가입이 성공하면
			response.sendRedirect(request.getContextPath() + "/member/login.do");	// 로그인 페이지로 이동
		}
		
	}
	
	// 로그인
	private void MemberLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String userId = request.getParameter("userId");	// 로그인 페이지에서 입력받은 아이디
		String userPassword = request.getParameter("userPassword");	// 로그인 페이지에서 입력받은 비밀번호
		
		MemberVO memberVo = new MemberVO();	// 아이디와 비밀번호를 저장할 MemberVO객체
		memberVo.setMem_id(userId);
		memberVo.setMem_pass(userPassword);
		
		int isLogined = service.MemberLogin(memberVo);	// 아이디와 비밀번호가 일치하는지 여부 확인
		
		HttpSession session = request.getSession();	// 세션 객체 
		
		Map<String, Object> loginInfo = new HashMap<String, Object>();	// 로그인한 회원의 정보를 저장할 Map객체
		
		if(isLogined == 1) {	// 로그인 성공시
			memberVo = service.getMemberInfo(memberVo.getMem_id());	// 로그인한 회원의 정보를 MemberVO객체에 저장
			loginInfo.put("memberInfo", memberVo);	// 로그인 한 회원정보를 Map객체에 저장
			session.setAttribute("isLogined", true);	// true면 로그인
			int cnt = service.existExpert(memberVo.getMem_no());
			loginInfo.put("existExpert", cnt);
			if(memberVo.getMem_grade() == 1) {	// 로그인한 회원이 전문가라면
				ExpertVO expertVo = service.getExperInfo(memberVo.getMem_id());	// 전문가 정보를 저장할 ExpertVO객체
				loginInfo.put("expertInfo", expertVo);	// 전문가 정보를 Map객체에 저장
			}
			session.setAttribute("loginInfo", loginInfo);	// 로그인 회원의 정보를 가지고 있는 Map객체를 세션에 저장
			
			response.sendRedirect(request.getContextPath() + "/main.jsp");// 로그인 성공 후 메인페이지로 이동
			
		}else {
			response.sendRedirect(request.getContextPath() + "/member/login.do");	// 로그인 실패시 로그인 창으로 이동
		}
		
	}
	
	// 로그아웃
	private void MemberLogout(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		HttpSession session = request.getSession();	// 세션 객체
		session.removeAttribute("loginInfo");	// 로그인 정보를 가지고 있는 세션 삭제
		response.sendRedirect(request.getContextPath() + "/main.jsp");	// 메인 페이지로 이동
	}
	
	// 회원 탈퇴
	private void Withdrawal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberVO memberInfo = getLoginMemberInfo(request, response);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("mem_no", memberInfo.getMem_no());
		param.put("mem_grade", 13);
		
		service.updateMemGrade(param);
		
		this.MemberLogout(request, response);
//		try {
//			response.sendRedirect(request.getContextPath() + "/member/logout.do");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	
	// 서비스 이용내역
	private void ServiceHistory(HttpServletRequest request, HttpServletResponse response) {
		MemberVO memberInfo = getLoginMemberInfo(request, response);
		
		int page = Integer.parseInt(request.getParameter("page"));
		String stype = request.getParameter("stype");
		String sword = request.getParameter("sword");
		PageVO pvo = service.pageInfo("서비스", page, stype, sword, memberInfo.getMem_no());
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("mem_no", memberInfo.getMem_no());
		if(memberInfo.getMem_grade() == 0) {	// 일반 회원
			String col = "cart.mem_no";
			param.put("stype", stype);
		}else if(memberInfo.getMem_grade() == 1){	// 전문가
			String col = "service.mem_no";
			param.put("col", col);
		}
		param.put("start", pvo.getStart());
		param.put("end", pvo.getEnd());
		
		List<Map<String, Object>> cartList = service.selectMyCart(param);
		request.setAttribute("cartList", cartList);
		request.setAttribute("startpage", pvo.getStartPage());
		request.setAttribute("endpage", pvo.getEndPage());
		request.setAttribute("totalpage", pvo.getTotalPage());
		
		try {
			request.getRequestDispatcher("/WEB-INF/view/member/serviceHistory.jsp").forward(request, response);
		}  catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 작성한 게시글
	private void MyBoard(HttpServletRequest request, HttpServletResponse response) {
		MemberVO memberInfo = getLoginMemberInfo(request, response);
		
		int page = Integer.parseInt(request.getParameter("page"));
		String stype = request.getParameter("stype");
		String sword = request.getParameter("sword");
		PageVO pvo = service.pageInfo("게시판", page, stype, sword, memberInfo.getMem_no());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mem_no", memberInfo.getMem_no());
		map.put("start", pvo.getStart());
		map.put("end", pvo.getEnd());
		
		List<Map<String, Object>> boardList = service.selectMyBoard(map);
		
		request.setAttribute("boardList", boardList);
		request.setAttribute("startpage", pvo.getStartPage());
		request.setAttribute("endpage", pvo.getEndPage());
		request.setAttribute("totalpage", pvo.getTotalPage());
		
		try {
			request.getRequestDispatcher("/WEB-INF/view/member/myBoard.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}

	// 위시리스트
	private void WishList(HttpServletRequest request, HttpServletResponse response) {
		MemberVO memberInfo = getLoginMemberInfo(request, response);
		List<Map<String, Object>> wishList = service.selectWishList(memberInfo.getMem_no());
		
		request.setAttribute("wishList", wishList);
		try {
			request.getRequestDispatcher("/WEB-INF/view/member/wishList.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	// 위시리스트 삭제
	private void DeleteWishList(HttpServletRequest request, HttpServletResponse response) {
		int wishNo = Integer.parseInt(request.getParameter("wish_no"));
		MemberVO memberInfo = getLoginMemberInfo(request, response);
		service.deleteWish(wishNo);
		try {
			response.sendRedirect(request.getContextPath() + "/wishlist.do?mem_no" + memberInfo.getMem_no());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// id찾기 
	private void MemberIdSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memName = request.getParameter("mem_name");
		String memEmail = request.getParameter("mem_email");
		
		MemberVO memberVo = new MemberVO();
		memberVo.setMem_name(memName);
		memberVo.setMem_email(memEmail);
		
		String memId = service.MemberIdSearch(memberVo);
		PrintWriter out = response.getWriter();
		String result = "false";
		if(memId != null) {
			result = memId;
		}
		out.print(result);
		out.flush();
	}
	
	// password 찾기 
	private void MemberPasswordSearch(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String memId = request.getParameter("mem_id");
		String memName = request.getParameter("mem_name");
		String memEmail = request.getParameter("mem_email");
		
		MemberVO memberVo = new MemberVO();
		memberVo.setMem_id(memId);
		memberVo.setMem_name(memName);
		memberVo.setMem_email(memEmail);
		
		String memPass = service.MemberPassSearch(memberVo);
		
		memberVo = service.getMemberInfo(memId);
		//-----------------------------------------------------------------
		PrintWriter out = response.getWriter();
		String result = "false";
		
		if(memPass != null) {
			result = "true";
			
			// 이메일 보내는 api 사용해야함**************************************************
			String recipient = memberVo.getMem_email();			// 받는사람
//			String code = "abc";

			// 1. 발신자의 메일 계정과 비밀번호 설정
			final String user = "duqor02@naver.com";			// 보내는사람
			final String password = "yb753649";

			// 2. Property에 SMTP 서버 정보 설정
			Properties prop = new Properties();
			prop.put("mail.smtp.host", "smtp.naver.com");
			prop.put("mail.smtp.port", 465);
			prop.put("mail.smtp.auth", "true");
			prop.put("mail.smtp.ssl.enable", "true");
			prop.put("mail.smtp.ssl.trust", "smtp.naver.com");

			// 3. SMTP 서버정보와 사용자 정보를 기반으로 Session 클래스의 인스턴스 생성
			Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(user, password);
				}
			});

			// 4. Message 클래스의 객체를 사용하여 수신자와 내용, 제목의 메시지를 작성한다.
			// 5. Transport 클래스를 사용하여 작성한 메세지를 전달한다.

			MimeMessage message = new MimeMessage(session);
			try {
				message.setFrom(new InternetAddress(user));

				// 수신자 메일 주소
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

				// Subject
				message.setSubject("모람모람 비밀번호");	// 제목

				// Text
				message.setText("안녕하세요 모람모람입니다.\n" + memName + "님의 비밀번호는 '" + memPass + "' 입니다.");	// 내용을 여기에 입력

				Transport.send(message); // send message

			} catch (AddressException e) {
				e.printStackTrace();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
		out.print(result);
		out.flush();
	}
	
	private void enrollExpert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ExpertVO expertVo = new ExpertVO();
		try {
			BeanUtils.populate(expertVo, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		int result = service.insertExpert(expertVo);
		System.out.println("result : " + result);
		if(result == 1) {
			// 회원등급 1(전문자)로 변경
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("mem_no", expertVo.getMem_no());
			param.put("mem_grade", 1);
			service.updateMemGrade(param);
			HttpSession session = request.getSession();
			Map<String, Object> loginInfo = (Map<String, Object>) session.getAttribute("loginInfo");
			MemberVO memberVo = (MemberVO) loginInfo.get("memberInfo");
			memberVo = service.getMemberInfo(memberVo.getMem_id());
			loginInfo.put("memberInfo", memberVo);
			session.setAttribute("loginInfo", loginInfo);
		}
		response.sendRedirect(request.getContextPath() + "/member/myPage.do");
	}
	
	private void expertServiceHistory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberVO memberInfo = getLoginMemberInfo(request, response);
		
		List<Map<String, Object>> expertServiceHistory = service.selectExpertServieHistory(memberInfo.getMem_no());
		request.setAttribute("expertServiceHistory", expertServiceHistory);
		request.getRequestDispatcher("/WEB-INF/view/member/expertServiceHistory.jsp").forward(request, response);
	}

	private MemberVO getLoginMemberInfo(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Map<String, Object> loginInfo = (Map<String, Object>) session.getAttribute("loginInfo");
		MemberVO memberInfo = (MemberVO) loginInfo.get("memberInfo");
		return memberInfo;
	}

	private void selectSalesStatistics(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String date = request.getParameter("date");
		String memberNo = request.getParameter("mem_no");
		String dateType = request.getParameter("dateType");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("date", date);
		param.put("mem_no", memberNo);
		param.put("dateType", dateType);
		
		List<Map<String, Object>> result = null;
		result = service.selectSalesStatistics(param);
		
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		out.print(gson.toJson(result));
		out.flush();
	}
	
	private void selectReportCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Map<String, Object>> reportCategoryList = service.selectReportCategory();
		request.setAttribute("reportCategoryList", reportCategoryList); 
		request.getRequestDispatcher("/WEB-INF/view/member/blackListManagement.jsp").forward(request, response);
	}
	
	private void selectReprotList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json charset=utf-8");
		int reportCategoryNo = Integer.parseInt(request.getParameter("reportCategoryNo"));
		
//		List<ReportVO> reportList = service.selectReport(reportCategoryNo);
//		Gson gson = new Gson();
//		PrintWriter out = response.getWriter();
//		out.print(gson.toJson(reportList));
//		out.flush();
	}
	
	// 회원 <-> 전문가 토글
	private void toggle(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		Map<String, Object> loginInfo = (Map<String, Object>) session.getAttribute("loginInfo");
		MemberVO memberVo = (MemberVO) loginInfo.get("memberInfo");
		int memberNo = memberVo.getMem_no();
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("mem_no", memberNo);
		String requestUri = "";
		if(memberVo.getMem_grade() == 1) {
			param.put("mem_grade", 0);
			service.updateMemGrade(param);
			requestUri = "/member/myPage.do";
		}else {
			int existExpert = service.existExpert(memberNo);
			if(existExpert == 1) {
				param.put("mem_grade", 1);
				service.updateMemGrade(param);
				requestUri = "/member/myPage.do";
			}else {
				requestUri = "/member/enrollExpert.do";
			}
		}
		System.out.println(requestUri);
		memberVo = service.getMemberInfo(memberVo.getMem_id());
		loginInfo.put("memberInfo", memberVo);
		session.setAttribute("loginInfo", loginInfo);
		response.sendRedirect(request.getContextPath() + requestUri);
	}
	
	private void Schedule(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/member/schedule.jsp").forward(request, response);
	}
	
	private void existExpert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		MemberVO memberVo = getLoginMemberInfo(request, response);
		int cnt = service.existExpert(memberVo.getMem_no());
		
		PrintWriter out = response.getWriter();
		out.print(cnt);
		out.flush();
	}
	
	private void myPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberVO memberVo = getLoginMemberInfo(request, response);
		int memGrade = memberVo.getMem_grade();
		Map<String, Object> param = new HashMap<String, Object>();
		
		if(memGrade == 0) {	// 일반 회원
			param.put("mem_no", memberVo.getMem_no());
			param.put("start", 1);
			param.put("end", 5);
			
			List<Map<String, Object>> cartList = service.selectMyCart(param);
			request.setAttribute("cartList", cartList);
			
			List<Map<String, Object>> boardList = service.selectMyBoard(param);
			request.setAttribute("boardList", boardList);
		}else if(memGrade == 1) { // 전문가
			param.put("mem_no", memberVo.getMem_no());
			param.put("date", 2022);
			param.put("dateType", "month");
			List<Map<String, Object>> statistics = service.selectSalesStatistics(param);
			System.out.println(statistics.toString());
			request.setAttribute("statistics", statistics);
		}else if(memGrade == 9) { // 관리자
			
		}
		
		request.getRequestDispatcher("/WEB-INF/view/member/myPage.jsp").forward(request, response);
	}
	
	private void insertReport(HttpServletRequest request, HttpServletResponse response) {
		ReportVO rvo = new ReportVO();
		String reportContent = request.getParameter("reportContent");
		int reportCategoryNo = Integer.parseInt(request.getParameter("reportCategoryNo"));
		int memNoBad = Integer.parseInt(request.getParameter("memNoBad")); // 신고받은회원
		int memNoDo = Integer.parseInt(request.getParameter("memNoDo")); // 신고한 회원
		
		int serviceNo = Integer.parseInt(request.getParameter("serviceNo"));
		
		rvo.setReport_content(reportContent);
		rvo.setReport_category_no(reportCategoryNo);
		rvo.setMem_no_bad(memNoBad);
		rvo.setMem_no_do(memNoDo);
		
		String res = "";
		int cnt = service.insertReport(rvo);
		if(cnt > 0) {
			res = "신고가 성공적으로 접수되었습니다.";
		}else {
			res = "신고 접수 실패! 관리자에게 문의해주세요.";
		}
		
		request.setAttribute("ReportVO", rvo);
		
		request.setAttribute("res", res);
		request.setAttribute("serviceNo", serviceNo);
		try {
			request.getRequestDispatcher("/WEB-INF/view/service/test.jsp").forward(request, response);
		} catch (ServletException | IOException e1) {
			e1.printStackTrace();
		}

	}
	
}
