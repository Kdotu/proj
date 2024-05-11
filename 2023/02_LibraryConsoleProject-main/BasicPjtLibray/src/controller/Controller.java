package controller;

import java.util.HashMap;
import java.util.Map;

import service.BoardService;
import service.BookService;
import service.ComentService;
import service.MemBookService;
import service.MemberService;
import service.RentService;
import util.ScanUtil;
import util.SpaceUtil;
import util.View;

public class Controller {
	
	// 세션 : 사용자와 서버의 연결 파이프라인 이라고 생각하면 쉬움
		static public Map<String, Object> sessionStorage = new HashMap<>();
		
		MemberService memberService = MemberService.getInstance();
		BoardService boardService = BoardService.getInstance();
		BookService bookService = BookService.getInstance();
		RentService rentService = RentService.getInstance();
		ComentService comentService =ComentService.getInstance();
		MemBookService memberBookService =MemBookService.getInstance();
		
		public static void main(String[] args) {
			new Controller().start();
		}
		
		private void start() { // 메인
			sessionStorage.put("login", false);
			sessionStorage.put("loginInfo", null);
			int view = View.HOME; // static final이어서 view라는 객체를 만들지 않아도 꺼낼 수 있음
			main:
			while(true) {
				switch(view) {
				case View.MEMBER_HOME: view = memHome(); break;
				case View.ADMIN_HOME : view = adminHome(); break;
				
				case View.MEMBER_JOIN: view = memberService.join(); break;
				case View.MEMBER_PAGE: view = memberService.myPage(); break;
				case View.MEMBER_INFO: view = memberService.memInfo(); break;
				case View.MEMBER_UPDATE: view = memberService.memInfoUpdate(); break;
				
				case View.MEMBER_WISH_BOOK: view = memberBookService.memWishBook(); break;
				case View.MEMBER_INTEREST_BOOK: view = memberBookService.memInterestBook(); break;
				case View.MEMBER_INTEREST_LIST: view = memberBookService.memInterestBookList(); break;
				
				case View.MEMBER_LOGIN: view = memberService.login(); break;
				case View.MEMBER_LOGOUT: view = memberService.logout(); break;
				case View.MEMBER_FIND: view = memberService.memberFind(); break;

				case View.ADMINBOOKLIST : view = memberService.adminBookList(); break;
				case View.ADMINBOOKSTOCK : view = memberService.adminBookStock(); break;
				
				case View.BOOK_LIST: view = bookService.bookList(); break;
				case View.BOOK_SEARCH: view = bookService.bookSearch(); break;
				case View.INSERT_BOOK: view = bookService.bookInsert(); break;
				case View.DELETE_BOOK: view = bookService.bookDelete(); break;
				case View.ADMINBOOK_WISHLIST : view = bookService.bookWishList(); break;
				
				case View.RENT_OR_RESERVE: view = rentReserveHome(); break;
				case View.RESERVE_BOOK: view = rentService.reserve(); break;
				case View.RENT_BOOK: view = rentService.rent(); break;
				case View.RENT_LIST: view = rentService.rentList(); break;
				case View.RETURN_BOOK: view = rentService.bookReturn(); break;
				case View.MEM_EXTEND: view = rentService.extend(); break;
				case View.RESERVE_LIST: view = rentService.reserveList(); break;
				case View.CANCEL_RESERVE: view = rentService.rserveCancel(); break;
				
				case View.BOARD_LIST: view = boardService.list(); break;
				case View.BOARD_DETAIL: view = boardService.detail(); break;
				case View.BOARD_INSERT: view = boardService.insert(); break;
				case View.BOARD_DELETE: view = boardService.delete();break;
				case View.BOARD_UPDATE: view = boardService.update(); break;
				case View.BOARD_SEARCH: view = boardService.search(); break;
				case View.BOARD_MYPOST: view = boardService.mypost(); break;
				case View.BOARD_SHOW: view = boardService.show(); break;
				case View.BOARD_DETAIL2: view = boardService.detail2(); break;
				
				case View.COMMENT_INSERT: view = comentService.insertComent(); break;
				case View.COMMENT_UPDATE: view = comentService.updateComent(); break;
				case View.COMMENT_DELETE: view = comentService.deleteComent(); break;
				
				case View.CLOSE: break main;
				default: view = home(); break;
				}
			}
		}
		
		private int home() {
			menuFormat(1,0);
//			System.out.println("====== 도서관리 시스템 ======");
//			System.out.println("1.회원가입 2.로그인 3.도서 4.게시판 0.종료");
//			System.out.println("=======================");
//			System.out.print("번호 입력 >> ");
			switch(ScanUtil.nextInt()) {		
			case 0: return View.CLOSE;
			case 1: return View.MEMBER_JOIN;
			case 2: return View.MEMBER_LOGIN;
			case 3: return View.BOOK_LIST;
			case 4: return View.BOARD_LIST;
			case 5: return View.MEMBER_FIND;
			default: return View.HOME;
			}
		}
			
		private int memHome() {
			menuFormat(2,0);
//			System.out.println("====== 도서관리 시스템 ======");
//			System.out.println("1.마이페이지 2.로그아웃 3.도서 4.게시판 0.종료");
//			System.out.println("=======================");
//			System.out.print("번호 입력 >> ");
			switch(ScanUtil.nextInt()) {		
			case 0: return View.CLOSE;
			case 1: return View.MEMBER_PAGE;
			case 2: return View.MEMBER_LOGOUT;
			case 3: return View.BOOK_LIST;
			case 4: return View.BOARD_LIST;
			default: return View.MEMBER_HOME;
			}
		}
		
		
		private int adminHome() {
			menuFormat(4,0);
//			System.out.println("====== 도서관리 시스템 ======");
//			System.out.println("1.대출도서목록 2.예약 도서목록 3.도서재고 관리 4.게시판 5.회원모드전환 0.종료");
//			System.out.println("=======================");
//			System.out.print("번호 입력 >> ");
			switch(ScanUtil.nextInt()) {		
			case 0: return View.CLOSE;
			case 1: return View.RENT_LIST;
			case 2: return View.RESERVE_LIST;
			case 3: return View.ADMINBOOKSTOCK;
			case 4: return View.BOARD_LIST;
			case 5: return View.MEMBER_LOGOUT;
			default: return View.ADMIN_HOME;
			}
		}
		
		private int rentReserveHome() {
			menuFormat(5,0);
//			System.out.println("===== 도서 예약 및 대여 ======");
//			System.out.println("1.예약 2.대여 3.관심도서 0.뒤로");
//			System.out.println("======================");
//			System.out.print("번호 입력 >> ");
			int input = ScanUtil.nextInt();
			switch (input) {
			case 1: return View.RESERVE_BOOK;
			case 2: return View.RENT_BOOK;
			case 3: return View.MEMBER_INTEREST_BOOK;
			case 0: return View.BOOK_LIST;
			default: 
				System.out.println("다시 입력해 주세요");
				return rentReserveHome();
			}
		}
		
		public int adminBookStock() { 
			menuFormat(6,0);
//			System.out.println("도서 1.추가   2. 삭제");
			BookService bookService = BookService.getInstance();
			switch(ScanUtil.nextInt()) {
			case 1 :
				return bookService.bookInsert();
			case 2:
				return bookService.bookDelete();
			case 0: default:
				return View.ADMIN_HOME;
			}
		}

		private static void clearLine(int number) {
			for (int i = 0; i < number; i++) {
				System.out.println();
			}
		}
		private void menuFormat(int number,int line) {
			switch(number) {
			case 1:
			// 비회원 모드
			System.out.printf("%s", SpaceUtil.format("# 도서관리 시스템", 55, -1));
			System.out.println();
			System.out.println("────────────────────────────────────────────────────────────────────────");
			System.out.printf(" %s", SpaceUtil.format(" 1.회원가입 │ 2.로그인 │ 3.도서 │ 4.게시판 │ 5.회원정보찾기 │ 0.종료", 44, 0));
			System.out.println("\n────────────────────────────────────────────────────────────────────────");
			System.out.println("메뉴 선택  > ");		
			clearLine(line);
			break;
			
			case 2:
			// 회원 모드
			System.out.printf("%s", SpaceUtil.format("# 도서관리 시스템", 55, -1));
			System.out.println();
			System.out.println("───────────────────────────────────────────────────────────");
			System.out.printf(" %s", SpaceUtil.format(" 1.마이페이지 │ 2.로그아웃 │ 3.도서 │ 4.게시판 │ 0.종료", 44, 0));
			System.out.println("\n───────────────────────────────────────────────────────────");
			System.out.println("메뉴 선택  > ");		
			clearLine(line);
			break;
			
			case 3:	
			// 관리자 모드
			System.out.printf("%s", SpaceUtil.format("# 도서관리 시스템", 55, -1));
			System.out.println();
			System.out.println("────────────────────────────────────────────────────────────────");
			System.out.printf(" %s", SpaceUtil.format(" 1.대여/예약 │ 2.도서재고 │ 3.게시판 │ 4.회원모드전환 │ 0.종료", 44, 0));
			System.out.println("\n────────────────────────────────────────────────────────────────");
			System.out.println("메뉴 선택  > ");
			clearLine(line);
			break;
			
			case 4:	
				// 관리자 모드
				System.out.printf("%s", SpaceUtil.format("# 도서관리 시스템", 55, -1));
				System.out.println();
				System.out.println("──────────────────────────────────────────────────────────────────────────────────────────");
				System.out.printf(" %s", SpaceUtil.format(" 1.대여도서목록 │ 2.예약도서목록 │ 3.도서재고관리 │ 4.게시판 │ 5.회원모드 전환 │ 0.종료", 44, 0));
				System.out.println("\n──────────────────────────────────────────────────────────────────────────────────────────");
				System.out.println("메뉴 선택  > ");
				clearLine(line);
				break;
				
			case 5:	
				// 도서 대여 예약 관심도서
				System.out.printf("%s", SpaceUtil.format("# 도서 대여/예약", 55, -1));
				System.out.println();
				System.out.println("───────────────────────────────────────────────");
				System.out.printf(" %s", SpaceUtil.format(" 1.예약 │ 2.대여 │ 3.관심도서 │ 0.뒤로가기", 44, 0));
				System.out.println("\n───────────────────────────────────────────────");
				System.out.println("메뉴 선택  > ");
				clearLine(line);
				break;
				
			case 6:	
				// 도서 추가/삭제
				System.out.printf("%s", SpaceUtil.format("# 도서 재고", 55, -1));
				System.out.println();
				System.out.println("───────────────────────────────────────────");
				System.out.printf("%s", SpaceUtil.format(" 1.도서추가 │ 2.도서삭제 │ 0.뒤로가기", 43, 0));
				System.out.println("\n───────────────────────────────────────────");
				System.out.println("메뉴 선택  > ");
				clearLine(line);
				break;
				
			
			
			
			
			}
		}
		
		




}