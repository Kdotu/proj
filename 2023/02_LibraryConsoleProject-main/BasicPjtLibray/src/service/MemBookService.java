package service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import controller.Controller;
import dao.BookDAO;
import dao.MemberDAO;
import dao.MemBookDAO;
import util.ScanUtil;
import util.SpaceUtil;
import util.View;
import vo.MemberVO;
import vo.BookVO;
import vo.MemBookVO;

public class MemBookService {
	private static MemBookService instance = null;
	
	private MemBookService() {}
	
	public static MemBookService getInstance() {
		if(instance == null) instance = new MemBookService();
		return instance;
	}
	MemberDAO memberDAO = MemberDAO.getInstance();
	MemBookDAO memBookDAO = MemBookDAO.getInstance();
	BookDAO bookDAO = BookDAO.getInstance();
	MemberVO loginInfo = (MemberVO) Controller.sessionStorage.get("loginInfo");
	
	BookService bookService = BookService.getInstance();
	int result = 0;
	
	public int memWishBook() {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
		MemberVO loginInfo = (MemberVO) Controller.sessionStorage.get("loginInfo");
		MemBookVO membookVO = new MemBookVO();
		List<MemBookVO> list = new ArrayList<MemBookVO>();
		String bookName;
		int number;
		
		list = memBookDAO.wishBookList(loginInfo);
		
		if(list.size() == 0) {
			System.out.println(" * 신청하신 희망도서가 없습니다.");
		}else {
			System.out.printf("%s%s",loginInfo.getMemName(), SpaceUtil.format("# 님의 희망도서", 55, -1));
			System.out.println();
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			for (int i = 0; i < list.size(); i++) {
				String bookDate = String.valueOf(list.get(i).getMemBookDate());
				bookDate = bookDate.substring(0,11);
				System.out.print(" " +  (i+1));
				System.out.printf("  %s  %s ", SpaceUtil.format(list.get(i).getMemBookWish(),30,0),SpaceUtil.format(bookDate,10,1));
				System.out.println();
			}
		}
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			
		while(true) {
		showList(1);
		switch(ScanUtil.nextInt()) {
		case 1:
			result = 0;
			System.out.printf("%s", SpaceUtil.format("# 희망도서 신청", 55, -1));
			// 책 정보 기입
			System.out.println("\n☞   신청하고 싶은 책 이름 입력 >> ");
			bookName = ScanUtil.nextLine();
			// 기존에 등록된 책과 중복되면 등록x
			
			
			membookVO.setMemBookWish(bookName);
			result = memBookDAO.wishBookInsert(bookName, loginInfo);
			
			if(result > 0) {
				System.out.println("희망도서 신청 완료");
			}else {
				System.out.println("희망도서 신청 실패. 다시 시도해주세요.");
			}
			return View.MEMBER_WISH_BOOK;
			
		case 2:
//			list = memBookDAO.wishBookList(loginInfo);
//			for (int i = 0; i < list.size(); i++) {
//				System.out.print((i+1) + "\t" + list.get(i).getMemBookWish() + "\t\t");
//				System.out.print(list.get(i).getMemBookDate());
//				System.out.println();
//			}
			System.out.printf("%s", SpaceUtil.format("# 희망도서 신청취소", 55, -1));
			
			System.out.println("취소할 희망도서 번호 입력 >> ");
			number = ScanUtil.nextInt();
			if(number > list.size()+1) {
				System.out.println("해당 글이 없습니다. 다시 입력해주세요.");
			}else {
//				System.out.println(list.get(number-1));
				String info = list.get(number-1).getMemBookNum();
				membookVO.setMemBookNum(info);
			}
			int bookMem_num = Integer.valueOf(membookVO.getMemBookNum());
			result = memBookDAO.wishBookDelete(loginInfo,bookMem_num);
			if(result > 0) {
				System.out.println("희망도서 취소 완료");
			}else {
				System.out.println("희망도서 취소 실패. 다시 시도해주세요.");
			}
			return View.MEMBER_WISH_BOOK;		
//		
		case 0: default : 	// 뒤로 가기
		return View.MEMBER_PAGE;
				}
			}
	}

	public int memInterestBook() {
		if(!(boolean)Controller.sessionStorage.get("login")) {
			System.out.println("로그인이 필요합니다.");
			return View.MEMBER_LOGIN;
		}
		List<BookVO> bookVOList = bookService.getBookVoList();
		
		System.out.printf("%s", SpaceUtil.format("# 관심도서 추가", 55, -1));
		System.out.println();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		for(int i = 0; i < bookVOList.size(); i++) {
			System.out.print(" " + SpaceUtil.format((i+1), 5, 0));
			System.out.print(SpaceUtil.format(bookVOList.get(i).getBookName(), 30, 0));
			System.out.print(SpaceUtil.format(bookVOList.get(i).getBookWriter(), 20, 0));
			System.out.print(SpaceUtil.format(bookVOList.get(i).getBookISBN(), 13 , 0));
			System.out.print(SpaceUtil.format(bookVOList.get(i).getBookRentable(), 3, 0));
			System.out.println();
		}
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println();
		System.out.println("☞   도서번호 선택  ( 0.취소 )>> ");
		
		int result = 0;
		int input = ScanUtil.nextInt();
		if(input == 0) {
			return View.BOOK_LIST;
		}
		if(input <= bookVOList.size() && input > 0) {
			BookVO bookVO = new BookVO();
			bookVO = bookVOList.get(input - 1);
			
			MemberVO loginInfo = (MemberVO) Controller.sessionStorage.get("loginInfo");
			result = memBookDAO.insertInterestBook(bookVO.getBookNumber(), Integer.parseInt(loginInfo.getMemNumber()));
			
			if(result > 0) {
				System.out.println("관심도서 추가 성공");
			}else {
				System.out.println("관심도서 추가 실패");
			}
			return View.BOOK_LIST;
		}else {
			System.out.println("잘못 입력");
			return memInterestBook();
		}
		
	}

	public int memInterestBookList() {
			MemberVO loginInfo = (MemberVO) Controller.sessionStorage.get("loginInfo");
			MemBookVO membookVO = new MemBookVO();
			List<MemBookVO> list = new ArrayList<MemBookVO>();
			list = memBookDAO.interestBookList(loginInfo);
			if(list.size() == 0 | list == null) {
				System.out.println();
				System.out.println("등록된 관심도서가 없습니다.");
				return View.MEMBER_PAGE;
			}else { // 목록이 있으면 불러오기
			System.out.printf("# %s%s",loginInfo.getMemName(), SpaceUtil.format("님의 관심도서", 55, -1));
			System.out.println();
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			
				for (int i = 0; i < list.size(); i++) {
					String bookDate = String.valueOf(list.get(i).getMemBookDate());
					bookDate = bookDate.substring(0,11);
					System.out.print(" " +  (i+1));
					System.out.printf("  %s  %s ", SpaceUtil.format(list.get(i).getMemBookName(),30,0),SpaceUtil.format(bookDate,10,1));
					System.out.println();
				}
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			}
			
			deleteInterest:
			while(true) {
				showList(5);
//				System.out.println("1.관심도서 해제 0.뒤로가기");
			switch(ScanUtil.nextInt()) {
			case 1:
				System.out.println("관심도서 해제할 번호 입력 >> ");
				int num = ScanUtil.nextInt();
				membookVO=list.get(num-1);
				
				int bookMem_num = Integer.valueOf(membookVO.getMemBookNum());
				result = memBookDAO.interestBookDelete(loginInfo,bookMem_num);
				
				if(result > 0) {
					System.out.println("삭제 성공");
					return View.MEMBER_INTEREST_BOOK;
				}else {
					System.out.println("삭제 실패. 다시 시도해주세요.");
					continue deleteInterest;
				}
			case 0: default : 
				return View.MEMBER_PAGE;
				}
			}
		}
	public void showList(int number) {
		switch(number) { 
		case 1: // 희망도서
			System.out.printf("%s", SpaceUtil.format("# 희망도서", 55, -1));
			System.out.println();
			System.out.println("────────────────────────────────────────────────────");
			System.out.printf("%s", SpaceUtil.format(" 1.희망도서신청  2.희망도서취소   0.뒤로가기", 44, 0));
			System.out.println("\n────────────────────────────────────────────────────");
			break;
		
		case 2:
			
		
		case 5: // 관심도서
			System.out.println();
			System.out.println("────────────────────────────────────────────");
			System.out.printf("%s", SpaceUtil.format(" 1.관심도서해제  0.뒤로가기", 44, 0));
			System.out.println("\n────────────────────────────────────────────");
			break;
			
			
			
		}
	}
	
	
}