package service;

import java.util.ArrayList;
import java.util.List;

import controller.Controller;
import dao.BookDAO;
import dao.MemBookDAO;
import util.ScanUtil;
import util.SpaceUtil;
import util.View;
import vo.BookVO;
import vo.MemBookVO;
import vo.MemberVO;

public class BookService {
	private static BookService instance = null;
	private BookService() {}
	public static BookService getInstance() {
		if(instance == null) instance = new BookService();
		return instance;
	}
	
	private List<BookVO> bookVOList;
	private int startRowNum = 0; 
	private int endRowNum = 10;
	
	BookDAO bookDAO = BookDAO.getInstance();
	MemBookDAO membookDAO = MemBookDAO.getInstance();
	
	public List<BookVO> getBookVoList() {
		return this.bookVOList;
	}

	public int bookList() {
		List<Integer> rowNum = new ArrayList<>();
		rowNum.add(this.startRowNum);
		rowNum.add(this.endRowNum);
		
		List<BookVO> bookVO = bookDAO.getList(rowNum);
		this.bookVOList = bookVO;
		System.out.printf("%s", SpaceUtil.format("# 도서목록", 55, -1));
		System.out.println();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.printf("%s %s %s %s", SpaceUtil.format("번호", 6, 0), SpaceUtil.format("도서명", 30, 0),
				SpaceUtil.format("저자명", 25, 0), SpaceUtil.format("대여가능여부", 12, 0));
		System.out.println("\n------------------------------------------------------------------------------");
		
		if(bookVO.size() == 0) {
			System.out.println("대여도서가 없습니다.");
		}
		
		for(int i = 0; i < bookVO.size(); i++) {
			System.out.printf("%s",SpaceUtil.format((i + this.startRowNum + 1), 6, 0));
			System.out.printf("%s",SpaceUtil.format(bookVO.get(i).getBookName(), 30, 0));
			System.out.printf("%s",SpaceUtil.format(bookVO.get(i).getBookWriter(), 30, 0));
			System.out.printf("%s",SpaceUtil.format(bookVO.get(i).getBookRentable(), 5, 0));
			System.out.println();
		}
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		menuFormat(1);
		int input = ScanUtil.nextInt();
		switch(input) {
		case 1:
			this.startRowNum -= 10;
			this.endRowNum -= 10;
			if(this.startRowNum < 0) {
				this.startRowNum = 0;
				this.endRowNum = 10;
			}
			return bookList();
		case 2:
			if(bookVO.size() < 10) {
			}else {
				this.startRowNum += 10;
				this.endRowNum += 10;
			}
			return bookList();
		case 3:
			return View.RENT_OR_RESERVE;
		case 4: return View.BOOK_SEARCH;
		case 0:
			return back(View.ADMIN_HOME, View.MEMBER_HOME, View.HOME);
		default:
			System.out.println("보기의 메뉴에서 골라주세요");
			return bookList();
		}
	}

	public int bookSearch() {
		menuFormat(2);
//		System.out.println("========= 도서 검색 ==========");
//		System.out.println("1.제목검색 2.저자검색 3.뒤로가기");
//		System.out.println("==============================");
//		System.out.print("번호 입력 >> ");
		int input = ScanUtil.nextInt();
		String con = null;
		String str = null;
		out:
		switch(input) {
		case 1:
			System.out.println("검색할 내용 입력 >> ");
			str = ScanUtil.nextLine();
			if(str.length() > 0) {
				con =  str; break;
			}else {
				while(true) {
					if(str.length() <= 0) {
						System.out.println("검색할 내용을 입력해주세요.");
						str = ScanUtil.nextLine();
						con =  str;
					}else {break;}
				}
			}
			break;
		case 2:
			System.out.println("검색할 내용 입력 >> ");
			str = ScanUtil.nextLine();
			if(str.length() > 0) {
				con =  str; break;
			}else {
				while(true) {
					if(str.length() <= 0) {
						System.out.println("검색할 내용을 입력해주세요.");
						str = ScanUtil.nextLine();
						con =  str;
					}else {break;}
				}
			}
			break;
		case 0: 
			return back(1, View.BOOK_LIST, View.BOOK_LIST); 
		default: 
			System.out.println("잘못입력");
			return bookSearch();
		}
		
		this.startRowNum = 0; 
		this.endRowNum = 10;
		List<Object> param = new ArrayList<>();
		param.add(con);
		param.add(this.startRowNum);
		param.add(this.endRowNum);
		
		bookVOList = bookDAO.getSerchBook(input, param);
		if(bookVOList.size() == 0) {
			System.out.println("조건에 맞는 검색 결과가 없습니다.");
			return back(View.ADMIN_HOME, View.BOOK_SEARCH, View.BOOK_SEARCH);
		}else {
				while(true) {
					for(int i = 0; i < bookVOList.size(); i++) {
						System.out.print(SpaceUtil.format((i + 1), 5, 0));
						System.out.print(SpaceUtil.format(bookVOList.get(i).getBookName(), 30, 0));
						System.out.print(SpaceUtil.format(bookVOList.get(i).getBookWriter(), 20, 0));
//						System.out.print(SpaceUtil.format(bookVOList.get(i).getBookISBN(), 13 , 0));
						System.out.print(SpaceUtil.format(bookVOList.get(i).getBookRentable(), 3, 0));
						System.out.println();
					}
					if((boolean)Controller.sessionStorage.get("login")) {
						MemberVO memberVO = (MemberVO)Controller.sessionStorage.get("loginInfo");
						if(memberVO.getMemId().equals("admin")){
							System.out.println("────────────────────────────────────────────────────────────");
							System.out.printf("%s", SpaceUtil.format(" 1.이전페이지 │ 2.다음페이지 │ 3.삭제할 도서선택 0.뒤로가기", 44, 0));
							System.out.println("\n────────────────────────────────────────────────────────────");
							System.out.println("메뉴 선택  > ");
							switch(ScanUtil.nextInt()) {
							case 1: 
								this.startRowNum -= 10;
								this.endRowNum -= 10;
								if(this.startRowNum < 0) {
									this.startRowNum = 0;
									this.endRowNum = 10;
									param = new ArrayList<>();
									param.add(con);
									param.add(this.startRowNum);
									param.add(this.endRowNum);
									bookVOList = bookDAO.getSerchBook(input, param);
								}
								break;
							case 2:
								if(bookVOList.size() < 10 || bookVOList.size() == 0) {
								}else {
									this.startRowNum += 10;
									this.endRowNum += 10;
									param = new ArrayList<>();
									param.add(con);
									param.add(this.startRowNum);
									param.add(this.endRowNum);
									bookVOList = bookDAO.getSerchBook(input, param);
								}
								break;
							case 3: return View.DELETE_BOOK;
							case 0: return View.ADMINBOOKSTOCK;
							default:
								System.out.println("잘못입력");
								break;
							}
						}else {
							menuFormat(8);
//							System.out.println("===== 도서 예약 및 대여 ======");
//							System.out.println("1.이전페이지 2.다음페이지 3.예약 4.대여 5.관심도서 0.뒤로");
							switch(ScanUtil.nextInt()) {
							case 1: 
								this.startRowNum -= 10;
								this.endRowNum -= 10;
								if(this.startRowNum < 0) {
									this.startRowNum = 0;
									this.endRowNum = 10;
									param = new ArrayList<>();
									param.add(con);
									param.add(this.startRowNum);
									param.add(this.endRowNum);
									bookVOList = bookDAO.getSerchBook(input, param);
								}
								break;
							case 2:
								if(bookVOList.size() < 10 || bookVOList.size() == 0) {
								}else {
									this.startRowNum += 10;
									this.endRowNum += 10;
									param = new ArrayList<>();
									param.add(con);
									param.add(this.startRowNum);
									param.add(this.endRowNum);
									bookVOList = bookDAO.getSerchBook(input, param);
								}
								break;
							case 3: return View.RESERVE_BOOK;
							case 4: return View.RENT_BOOK;
							case 5: return View.MEMBER_INTEREST_BOOK;
							case 0: return View.BOOK_LIST;
							default:
								System.out.println("잘못입력");
								break;
							}
						}
					}else {
						menuFormat(8);
//						System.out.println("===== 도서 예약 및 대여 ======");
//						System.out.println("1.이전페이지 2.다음페이지 3.예약 4.대여 5.관심도서 0.뒤로");
						switch(ScanUtil.nextInt()) {
						case 1: 
							this.startRowNum -= 10;
							this.endRowNum -= 10;
							if(this.startRowNum < 0) {
								this.startRowNum = 0;
								this.endRowNum = 10;
								param = new ArrayList<>();
								param.add(con);
								param.add(this.startRowNum);
								param.add(this.endRowNum);
								bookVOList = bookDAO.getSerchBook(input, param);
							}
							break;
						case 2:
							if(bookVOList.size() < 10 || bookVOList.size() == 0) {
							}else {
								this.startRowNum += 10;
								this.endRowNum += 10;
								param = new ArrayList<>();
								param.add(con);
								param.add(this.startRowNum);
								param.add(this.endRowNum);
								bookVOList = bookDAO.getSerchBook(input, param);
							}
							break;
						case 3: return View.RESERVE_BOOK;
						case 4: return View.RENT_BOOK;
						case 5: return View.MEMBER_INTEREST_BOOK;
						case 0: return View.BOOK_LIST;
						default:
							System.out.println("잘못입력");
							break;
						}
					
					}
					
				}
			}
		}
	
	public int bookInsert() {
			this.bookVOList = new ArrayList<>();
			insert:
			while(true){
				BookVO bookVO = new BookVO();
				
				
				System.out.println("추가할 도서의 정보 입력 :");
				while(true) {
					menuFormat(4);
					System.out.println("도서명 (50자 이내)");
					String bookName = ScanUtil.nextLine();
					if(bookName.length() <= 50) {
						bookVO.setBookName(bookName);
						break;
					}
					else System.out.println("입력길이를 초과하였습니다.");
				}
				while(true) {
					menuFormat(5);
					System.out.println("저자 (35자 이내)");
					String bookWriter = ScanUtil.nextLine();
					if(bookWriter.length() <= 35) {
						bookVO.setBookWriter(bookWriter);
						break;
					}
					else System.out.println("입력길이를 초과하였습니다.");
				}
				while(true) {
					menuFormat(6);
					System.out.println("ISBN (13자리)");
					String bookISBN = ScanUtil.nextLine();
					if(bookISBN.length() <= 13) {
						if(bookISBN.matches("[+-]?\\d*(\\.\\d+)?")) {
							bookVO.setBookISBN(bookISBN);
							break;
						}else System.out.println("숫자만 입력해주세요.");
					}else System.out.println("입력길이를 초과하였습니다.");
				}
//				System.out.println("도서분류 선택");
//				System.out.println("1.철학 2.종교 3.사회과학 4.자연과학 5.기술과학");
//				System.out.println("6.예술 7.언어 8.문학 9.역사");
				menuFormat(3);
				int type = 0;
				switch(ScanUtil.nextInt()) {
					case 1: type = 100; break;
					case 2: type = 200; break;
					case 3: type = 300; break;
					case 4: type = 400; break;
					case 5: type = 500; break;
					case 6: type = 600; break;
					case 7: type = 700; break;
					case 8: type = 800; break;
					case 9: type = 900; break;
					default:
						System.out.println("잘못입력하셨습니다.");
						return View.ADMIN_HOME;
						}
				bookVO.setBookType(String.valueOf(type));
				bookVOList.add(bookVO);
				// 도서 추가/삭제
				System.out.println("────────────────────────────────");
				System.out.printf("%s", SpaceUtil.format(" 1.도서추가 입력 │ 2. 입력종료", 30, 0));
				System.out.println("\n────────────────────────────────");
				System.out.println("메뉴 선택  > ");
//				System.out.println("1.도서 추가 입력 2.입력 종료");
				switch(ScanUtil.nextInt()) {
				case 1: continue;
				case 2: break insert;
				}
			}
			int result = bookDAO.insertBook(bookVOList);

			if(result > 0) {
				System.out.println("도서 추가 성공!");
			}else {
				System.out.println("도서 추가 실패ㅠ");
			}
			return View.ADMIN_HOME;
	}
	
	public int bookDelete() {
		System.out.printf("%s", SpaceUtil.format("# 삭제도서 선택", 55, -1));
		System.out.println();
		int input = ScanUtil.nextInt();
			System.out.println("선택한 도서 정보 ");
			System.out.print(SpaceUtil.format(bookVOList.get(input - 1).getBookName(), 30, 0));
			System.out.print(SpaceUtil.format(bookVOList.get(input - 1).getBookWriter(), 20, 0));
			System.out.print(SpaceUtil.format(bookVOList.get(input - 1).getBookISBN(), 13 , 0));
			System.out.print(SpaceUtil.format(bookVOList.get(input - 1).getBookRentable(), 3, 0));
			System.out.println();
		if(bookVOList.get(input - 1).getBookRentable().equals("N")) {
			System.out.println("삭제 불가(대여 or 예약중인 도서입니다.)");
			return View.ADMIN_HOME;
		}else {
			System.out.println("삭제하시겠습니까? 1.삭제 2.취소");
			int result = 0;
			switch(ScanUtil.nextInt()) {
			case 1:	
				result = bookDAO.deleteBook(bookVOList.get(input - 1));	
				if(result > 0) {
					System.out.println("도서 삭제 성공!");
				}else {
					System.out.println("도서 삭제 실패ㅠ");
				}
				return View.ADMIN_HOME;
			case 2: return View.ADMIN_HOME;
			default: return View.ADMIN_HOME; 
			}
			
		}
		
	}
	
	public int adminRentBookList() {
		return View.RENT_LIST;
	}
	
	
	public int adminReserveBookList() {
		return View.RESERVE_LIST;
	}
	
	// 뒤로가기-로그인 여부 판단 후 관리자, 회원, 비회원일때 리턴값 지정하는 메소드
	public int back(int admin, int member, int notMember) {
		MemberVO memberVO = (MemberVO)Controller.sessionStorage.get("loginInfo");
		if((boolean)Controller.sessionStorage.get("login")) {
			if(memberVO.getMemName().equals("admin")) {
				return admin;
			}else {
				return member;
			}
		}else {
			return notMember;
		}
	}
	
	private void menuFormat(int number) {
		switch(number) {
		case 1:
//			System.out.println("1.이전페이지 2.다음페이지 3.예약 4.대여 5.관심도서 0.뒤로");
			System.out.printf("%s", SpaceUtil.format("# 도서 검색", 55, -1));
			System.out.println();
			System.out.println("───────────────────────────────────────────────────────────────────────────");
			System.out.printf("%s", SpaceUtil.format(" 1.이전페이지 │ 2.다음페이지 │ 3.대여/예약/관심도서 │ 4.검색 │ 0.뒤로가기", 44, 0));
			System.out.println("\n───────────────────────────────────────────────────────────────────────────");
			System.out.println("메뉴 선택  > ");		
			break;
		case 2:
			System.out.printf("%s", SpaceUtil.format("# 도서 검색", 55, -1));
			System.out.println();
			System.out.println("──────────────────────────────────────");
			System.out.printf("%s", SpaceUtil.format(" 1.제목검색 │ 2.저자검색 │ 0.뒤로가기", 44, -1));
			System.out.println("\n──────────────────────────────────────");
			System.out.println("메뉴 선택  > ");
			break;
		case 3:
			System.out.printf("%s", SpaceUtil.format("# 도서분류 선택", 55, -1));
			System.out.println();
			System.out.println("───────────────────────────────────────────────────");
			System.out.printf("%s", SpaceUtil.format(" 1.철학  2.종교  3.사회과학  4.자연과학  5.기술과학\n", 44, 0));
			System.out.printf("%s", SpaceUtil.format(" 6.예술  7.언어  8.문학      9.역사     0.선택취소", 44, 0));
			System.out.println("\n───────────────────────────────────────────────────");
			System.out.println("메뉴 선택  > ");		
			break;
		case 4:
			System.out.printf("%s", SpaceUtil.format("# 추가도서 정보입력", 55, -1));
			System.out.println();
			System.out.println("────────────────────────────────────────────");
			System.out.printf("%s", SpaceUtil.format("  □ 도서명 │ □ 저자 │ □ ISBN", 44, 0));
			System.out.println("\n────────────────────────────────────────────");
		case 5:
			System.out.printf("%s", SpaceUtil.format("# 추가도서 정보입력", 55, -1));
			System.out.println();
			System.out.println("────────────────────────────────────────────");
			System.out.printf("%s", SpaceUtil.format("  ■ 도서명 │ □ 저자 │ □ ISBN", 44, 0));
			System.out.println("\n────────────────────────────────────────────");
			break;
		case 6:
			System.out.printf("%s", SpaceUtil.format("# 추가도서 정보입력", 55, -1));
			System.out.println();
			System.out.println("────────────────────────────────────────────");
			System.out.printf("%s", SpaceUtil.format("  ■ 도서명 │ ■ 저자 │ □ ISBN", 44, 0));
			System.out.println("\n────────────────────────────────────────────");
			break;
		case 7:
			System.out.printf("%s", SpaceUtil.format("# 삭제도서 선택", 55, -1));
			System.out.println();
			System.out.println("────────────────────────────────────────────");
			System.out.printf("%s", SpaceUtil.format("  ■ 도서명 │ ■ 저자 │ □ ISBN", 44, 0));
			System.out.println("\n────────────────────────────────────────────");
			break;
			
		case 8:	
			// 도서 대여 예약 관심도서
			System.out.printf("%s", SpaceUtil.format("# 도서 대여/예약", 55, -1));
			System.out.println();
			System.out.println("────────────────────────────────────────────────────────────────────────");
			System.out.printf("│%s", SpaceUtil.format(" 1.이전페이지 | 2.다음페이지 3.예약 │ 4.대여 │ 5.관심도서 │ 0.뒤로가기", 44, 0));
			System.out.println("\n────────────────────────────────────────────────────────────────────────");
			System.out.println("메뉴 선택  > ");
			break;
		}
	}
	
	public int bookWishList() {
		List<MemBookVO> list = new ArrayList<>();
		list = membookDAO.bookWish();
		System.out.printf("%s", SpaceUtil.format("# 희망도서목록", 55, -1));
		System.out.println();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		if(list.size()==0) {
			System.out.println("-- 신청된 희망도서가 없습니다. --");
			return View.ADMINBOOKSTOCK;
		}

		for (int i = 0; i < list.size(); i++) {
			String date = list.get(i).getMemBookDate();
			date = date.substring(0,11);
			
			// 글번호
			System.out.print(list.get(i).getMemBookNum() + " \t"); 
			System.out.print(list.get(i).getMemBookWish() + " \t\t");
//			System.out.print(list.get(i).getMemNum() + " ");
			System.out.print(list.get(i).getMemBookName() + " \t");
			System.out.print(date + " ");
			System.out.println();
		}
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	
		System.out.println("삭제할 글 번호 선택 (0.뒤로가기)>> ");
		int num = ScanUtil.nextInt();
		if(num == 0) {
			return View.ADMINBOOKSTOCK;
		}else {
			int result = membookDAO.bookWishDelete(num);
			
			if(result > 0) {
				System.out.println("삭제 성공");
				return View.ADMINBOOK_WISHLIST;
			}else {
				System.out.println("삭제 실패");
				return View.ADMINBOOK_WISHLIST;
			}
		}
	}
	
}