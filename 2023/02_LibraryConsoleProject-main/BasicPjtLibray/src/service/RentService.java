package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import controller.Controller;
import dao.BookDAO;
import dao.RentDAO;
import util.ScanUtil;
import util.SpaceUtil;
import util.View;
import vo.BookVO;
import vo.MemberVO;
import vo.RentVO;

public class RentService {
	private static RentService instance = null;

	private RentService() {
	}

	public static RentService getInstance() {
		if (instance == null)
			instance = new RentService();
		return instance;
	}

	private List<List<Object>> rentVOList;
	private List<List<Object>> reserveVOList;

	public List<List<Object>> getRentVOList() {
		return this.rentVOList;
	}

	public List<List<Object>> getReserveVOList() {
		return this.reserveVOList;
	}

	BookService bs = BookService.getInstance();
	MemberService ms = MemberService.getInstance();

	RentDAO rentDAO = RentDAO.getInstance();

	public int reserve() {
		if ((boolean) Controller.sessionStorage.get("login")) {
			List<BookVO> bookVO = bs.getBookVoList();
			System.out.printf("%s", SpaceUtil.format("# 예약도서 선택목록", 55, -1));
			System.out.println();
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.printf("%s %s %s %s", SpaceUtil.format("번호", 6, 0), SpaceUtil.format("도서명", 30, 0),
					SpaceUtil.format("저자명", 25, 0), SpaceUtil.format("대여가능여부", 12, 0));
			System.out.println("\n------------------------------------------------------------------------------");
			for (int i = 0; i < bookVO.size(); i++) {
				System.out.printf("%s", SpaceUtil.format((i+1), 6, 0));
				System.out.printf("%s",SpaceUtil.format(bookVO.get(i).getBookName(), 30, 0));
				System.out.printf("%s",SpaceUtil.format(bookVO.get(i).getBookWriter(), 30, 0));
//				System.out.print(SpaceUtil.format(bookVO.get(i).getBookISBN(), 13, 0));
				System.out.printf("%s",SpaceUtil.format(bookVO.get(i).getBookRentable(), 5, 0));
				System.out.println();
			}
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			
			MemberVO memberVO = (MemberVO) Controller.sessionStorage.get("loginInfo");
			List<List<Object>> row = new ArrayList<>();
			String param = memberVO.getMemId();
			row = rentDAO.showReserveList(param);
			this.reserveVOList = new ArrayList<>();
			this.reserveVOList = row;
			System.out.println(" \t\t\t\t\t현재 예약가능 권수 : " + this.reserveVOList.size() + " / 3 권");
			System.out.println("---------------------------------------------------");
			
			if (3 <= this.reserveVOList.size()) {
				System.out.println("더 이상 책을 예약할 수 없습니다.");
				return View.MEMBER_HOME;
			} else {
				System.out.println("예약할 책이 포함된 행을 선택하세요 >>");
				int list = ScanUtil.nextInt();
				String Rentable = bookVO.get(list - 1).getBookRentable();
				if (Rentable.equals("N")) {
					System.out.println("예약 불가능한 도서입니다.");
				} else {
					int bookNum = bookVO.get(list - 1).getBookNumber();
					String memNum = memberVO.getMemNumber();
					int result = rentDAO.doReserve(memNum, bookNum);
					if (result > 0) {
						System.out.println("예약이 완료되었습니다.");
					} else {
						System.out.println("예약이 실패했습니다.");
					}
				}
			}
			return View.MEMBER_HOME;
		} else {
			System.out.println("로그인이 필요한 서비스입니다.");
			return View.MEMBER_LOGIN;
		}
	}

	public int rent() {
		if ((boolean) Controller.sessionStorage.get("login")) {
			List<BookVO> bookVO = bs.getBookVoList();
			System.out.printf("%s", SpaceUtil.format("# 대여도서 선택목록", 55, -1));
			System.out.println();
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.printf("%s %s %s %s", SpaceUtil.format("번호", 6, 0), SpaceUtil.format("도서명", 30, 0),
					SpaceUtil.format("저자명", 25, 0), SpaceUtil.format("대여가능여부", 12, 0));
			System.out.println("\n------------------------------------------------------------------------------");
			for (int i = 0; i < bookVO.size(); i++) {
				System.out.printf("%s", SpaceUtil.format((i+1), 6, 0));
				System.out.printf("%s",SpaceUtil.format(bookVO.get(i).getBookName(), 30, 0));
				System.out.printf("%s",SpaceUtil.format(bookVO.get(i).getBookWriter(), 30, 0));
//				System.out.print(SpaceUtil.format(bookVO.get(i).getBookISBN(), 13, 0));
				System.out.printf("%s",SpaceUtil.format(bookVO.get(i).getBookRentable(), 5, 0));
				System.out.println();
			}
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			
			MemberVO memberVO = (MemberVO) Controller.sessionStorage.get("loginInfo");
			List<List<Object>> row = new ArrayList<>();
			List<List<Object>> row1 = new ArrayList<>();
			String param = memberVO.getMemId();
			
			row = rentDAO.showRentList(param);
			this.rentVOList = new ArrayList<>();
			this.rentVOList = row; 
			
			row1 = rentDAO.showReserveList(param);
			this.reserveVOList = new ArrayList<>();
			this.reserveVOList = row1; 
			
			System.out.println(" \t\t\t\t\t 현재 대여가능 권수 : " + this.rentVOList.size() + " / 5 권");
			System.out.println("---------------------------------------------------");
			if (5 <= this.rentVOList.size()) {
				System.out.println("더 이상 책을 빌릴 수 없습니다.");
				return View.MEMBER_HOME;
			} else {
				System.out.println("대여할 책이 포함된 행을 선택하세요 >> ");
				int list = ScanUtil.nextInt();
				String rentable = bookVO.get(list - 1).getBookRentable();
				int rentBookNum = bookVO.get(list - 1).getBookNumber();
				int rentMemNum = rentDAO.showReservedBookList(memberVO.getMemNumber(), rentBookNum);
				if (reserveVOList.size() != 0) {
					if (rentable.equals("N") && !(Integer.parseInt(memberVO.getMemNumber()) == rentMemNum)) {
						System.out.println("이미 대여중인 도서입니다.");
					} else {
						int bookNum = bookVO.get(list - 1).getBookNumber();
						int result = rentDAO.doRent(bookNum);
						if (result > 0) {
							System.out.println("대여가 완료되었습니다.");
						} else {
							System.out.println("대여가 실패했습니다.");
						}
					}
					System.out.println();
					return View.MEMBER_HOME;
				} else {
					rentable = bookVO.get(list - 1).getBookRentable();
					if (rentable.equals("N")) {
						System.out.println("이미 대여중인 도서입니다.");
					} else {
						int bookNum = bookVO.get(list - 1).getBookNumber();
						String memNum = memberVO.getMemNumber();
						int result = rentDAO.doRent(memNum, bookNum);
						if (result > 0) {
							System.out.println("대여가 완료되었습니다.");
						} else {
							System.out.println("대여가 실패했습니다.");
						}
					}
					System.out.println();
					return View.MEMBER_HOME;
				}
			}
		} else {
			System.out.println("로그인이 필요한 서비스입니다.");
			return View.MEMBER_LOGIN;
		}
	}

	public int rentList() {
		MemberVO memberVO = (MemberVO) Controller.sessionStorage.get("loginInfo");
		String param = memberVO.getMemId();
		List<List<Object>> rentBookList = new ArrayList<>();
		rentBookList = rentDAO.showRentList();
		if (rentBookList.size() != 0) {
			if (param.equals("admin")) {
				System.out.printf("%s", SpaceUtil.format("# 대여중인 도서목록", 55, -1));
				System.out.println();
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.printf("%s %s %s %s %s", SpaceUtil.format("번호", 6, 0), SpaceUtil.format("도서명", 30, 0),
						SpaceUtil.format("저자명", 23, 0),SpaceUtil.format("대여자", 5, 0), SpaceUtil.format("연장여부", 3, 0));
				System.out.println("\n------------------------------------------------------------------------------");
				for (int i = 0; i < rentBookList.size(); i++) {
					BookVO bookVO = (BookVO) rentBookList.get(i).get(0);
					RentVO rentVO = (RentVO) rentBookList.get(i).get(1);
					MemberVO memVO = (MemberVO) rentBookList.get(i).get(2);
					System.out.print(" " + SpaceUtil.format((i+1), 5, 0));
					System.out.print(SpaceUtil.format(bookVO.getBookName(), 35, 0));
					System.out.print(SpaceUtil.format(bookVO.getBookWriter(), 20, 0));
					System.out.print(SpaceUtil.format(memVO.getMemName(), 10, 0));
					System.out.print(SpaceUtil.format(rentVO.getRentExtend(), 5, 0));
					
					System.out.println();
				}
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				this.rentVOList = new ArrayList<>();
				this.rentVOList = rentBookList;
				return View.RETURN_BOOK;
			} else {
				rentBookList = rentDAO.showRentList(param);
				System.out.printf("%s", SpaceUtil.format("# 대여중인 도서목록", 55, -1));
				System.out.println();
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.printf("%s %s %s %s %s", SpaceUtil.format("번호", 6, 0), SpaceUtil.format("도서명", 30, 0),
						SpaceUtil.format("저자명", 23, 0),SpaceUtil.format("대여일", 5, 0), SpaceUtil.format("연장여부", 3, 0));
				System.out.println("\n------------------------------------------------------------------------------");
				for (int i = 0; i < rentBookList.size(); i++) {
					BookVO bookVO = (BookVO) rentBookList.get(i).get(0);
					RentVO rentVO = (RentVO) rentBookList.get(i).get(1);
					String startDate = rentVO.getRentStart();
					startDate =startDate.substring(0,11);
					System.out.print(" " + SpaceUtil.format((i+1), 5, 0));
					System.out.print(SpaceUtil.format(bookVO.getBookName(), 35, 0));
					System.out.print(SpaceUtil.format(bookVO.getBookWriter(), 20, 0));
					System.out.print(SpaceUtil.format(startDate, 10, 0));
					System.out.print(SpaceUtil.format(rentVO.getRentExtend(), 5, 0));
					System.out.println();
				}
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				this.rentVOList = new ArrayList<>();
				this.rentVOList = rentBookList;
				
				System.out.println();
				System.out.println("───────────────────────────────────");
				System.out.printf("%s", SpaceUtil.format(" 1.도서연장  2.도서반납  0.뒤로가기", 40, 0));
				System.out.println("\n───────────────────────────────────");
				System.out.println("메뉴 선택  > ");
//				System.out.println("1.연장 2.반납 0.뒤로가기");
				switch (ScanUtil.nextInt()) {
				case 1:
					return View.MEM_EXTEND;
				case 2:
					return View.RETURN_BOOK;
				case 0: default:
					return View.MEMBER_PAGE;
				}
			}
		} else {
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("대여중인 책이 없습니다.");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			if (memberVO.getMemId().equals("admin")) {
				return View.ADMIN_HOME;
			} else {
				return View.MEMBER_PAGE;
			}
		}
	}

	public int reserveList() {
		MemberVO memberVO = (MemberVO) Controller.sessionStorage.get("loginInfo");
		String param = memberVO.getMemId();
		List<List<Object>> reserveBookList = new ArrayList<>();
		reserveBookList = rentDAO.showReserveList();
		if (reserveBookList.size() != 0) {
			if (param.equals("admin")) {
				System.out.printf("%s", SpaceUtil.format("# 예약 도서목록", 55, -1));
				System.out.println();
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.printf("%s %s %s %s %s", SpaceUtil.format("번호", 6, 0), SpaceUtil.format("도서명", 30, 0),
						SpaceUtil.format("저자명", 23, 0),SpaceUtil.format("예약자", 5, 0), SpaceUtil.format("연장여부", 3, 0));
				System.out.println("\n------------------------------------------------------------------------------");
				for (int i = 0; i < reserveBookList.size(); i++) {
					BookVO bookVO = (BookVO) reserveBookList.get(i).get(0);
					RentVO rentVO = (RentVO) reserveBookList.get(i).get(1);
					MemberVO memVO = (MemberVO) reserveBookList.get(i).get(2);
					System.out.print(" " + SpaceUtil.format((i+1), 5, 0));
					System.out.print(SpaceUtil.format(bookVO.getBookName(), 35, 0));
					System.out.print(SpaceUtil.format(bookVO.getBookWriter(), 20, 0));
					System.out.print(SpaceUtil.format(memVO.getMemName(), 10, 0));
					System.out.print(SpaceUtil.format(rentVO.getRentExtend(), 10, 1));
					System.out.println();
				}
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				this.reserveVOList = new ArrayList<>();
				this.reserveVOList = reserveBookList;
				return View.CANCEL_RESERVE;
			} else {
				reserveBookList = rentDAO.showReserveList(param);
				System.out.printf("%s", SpaceUtil.format("# 예약 도서목록", 55, -1));
				System.out.println();
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.printf("%s %s %s %s", SpaceUtil.format("번호", 6, 0), SpaceUtil.format("도서명", 30, 0),
						SpaceUtil.format("저자명", 23, 0), SpaceUtil.format("예약 일자", 5, 0));
				System.out.println("\n------------------------------------------------------------------------------");
				for (int i = 0; i < reserveBookList.size(); i++) {
					BookVO bookVO = (BookVO) reserveBookList.get(i).get(0);
					RentVO rentVO = (RentVO) reserveBookList.get(i).get(1);
					System.out.print(" " + SpaceUtil.format((i+1), 5, 0));
					System.out.print(SpaceUtil.format(bookVO.getBookName(), 35, 0));
					System.out.print(SpaceUtil.format(bookVO.getBookWriter(), 20, 0));
					System.out.print(SpaceUtil.format(rentVO.getBookReservation(), 10, 1));
					System.out.println();
				}
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				this.reserveVOList = new ArrayList<>();
				this.reserveVOList = reserveBookList;
				return View.CANCEL_RESERVE;
			}
		} else {
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("예약중인 책이 없습니다.");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			if (memberVO.getMemId().equals("admin")) {
				return View.ADMIN_HOME;
			} else {
				return View.MEMBER_PAGE;
			}
		}
	}

	public int extend() {
		System.out.println("기간 연장할 책이 포함된 행번호를 입력해주세요.");
		int input = ScanUtil.nextInt();
		if (input > rentVOList.size() || input < 0) {
			System.out.println("다른 행 번호를 입력해주세요");
			return extend();
		} else {
			RentVO rentVO1 = (RentVO) this.rentVOList.get(input - 1).get(1);
			if (rentVO1.getRentExtend().equals("X")) {
				int result = rentDAO.setextend(rentVO1.getBookNumber());
				if (result > 0) {
					System.out.println("기간 연장이 완료되었습니다.");
				} else {
					System.out.println("기간 연장이 실패되었습니다.");
				}
				return View.MEMBER_PAGE;
			} else {
				System.out.println("더 이상 연장할 수 없는 책입니다.");
				return View.RENT_LIST;
			}
		}
	}

	public int bookReturn() {
		MemberVO memberVO = (MemberVO) Controller.sessionStorage.get("loginInfo");
//		System.out.println("1.도서반납 2.뒤로가기");

		System.out.println();
		System.out.println("───────────────────────────────────");
		System.out.printf("%s", SpaceUtil.format(" 반납할 도서의 번호를 입력해주세요. (0. 뒤로가기)", 44, 0));
		System.out.println("\n───────────────────────────────────");
		System.out.println("메뉴 선택  > ");
//		System.out.println("반납할 책을 선택하세요. ");
		int input = ScanUtil.nextInt();
		switch (input) {
		default :
			break;
		case 0: 
			if (memberVO.getMemId().equals("admin")) {
				return View.ADMIN_HOME;
			} else {
				return View.MEMBER_HOME;
			}
		}
		
		if (rentVOList.size() >= input && input > 0) {
			BookVO bookVO = (BookVO) rentVOList.get(input - 1).get(0);
			rentDAO.setReturn(bookVO.getBookNumber());
			System.out.println("-----------------------------------------------------─");
			System.out.println(bookVO.getBookName() + " 이(가) 반납되었습니다.");
			System.out.println("-----------------------------------------------------─");
			System.out.println();
		} else {
			System.out.println("잘못 입력하였습니다.");
			return bookReturn();
		}
		if (memberVO.getMemId().equals("admin")) {
			return View.ADMIN_HOME;
		} else {
			return View.MEMBER_HOME;
		}
	}

	public int rserveCancel() {
		MemberVO memberVO = (MemberVO) Controller.sessionStorage.get("loginInfo");
		System.out.println();
		System.out.println("─────────────────────");
		System.out.printf("%s", SpaceUtil.format(" 1.예약취소  0. 뒤로가기", 20, 0));
		System.out.println("\n─────────────────────");
		System.out.println("메뉴 선택  > ");
//		System.out.println("1.예약취소 2.뒤로가기");
		switch (ScanUtil.nextInt()) {
		case 1:
			break;
		case 0:
			if (memberVO.getMemId().equals("admin")) {
				return View.ADMIN_HOME;
			} else {
				return View.MEMBER_HOME;
			}
		default:
			System.out.println("잘못 입력하였습니다.");
			return rserveCancel();
		}
		
		System.out.println("☞    목록에서 예약을 취소할 도서 번호 입력 >> ");
		int input = ScanUtil.nextInt();
		if (reserveVOList.size() >= input && input > 0) {
			BookVO bookVO = (BookVO) reserveVOList.get(input - 1).get(0);
			rentDAO.cancelReserve(bookVO.getBookNumber());
			System.out.println("-----------------------------------------------------─");
			System.out.println(bookVO.getBookName() + " 이(가) 예약취소되었습니다.");
			System.out.println("-----------------------------------------------------─");
			System.out.println();
			if (memberVO.getMemId().equals("admin")) {
				return View.ADMIN_HOME;
			} else {
				return View.MEMBER_HOME;
			}
		} else {
			System.out.println("잘못 입력하였습니다.");
			return rserveCancel();
		}
	}

}