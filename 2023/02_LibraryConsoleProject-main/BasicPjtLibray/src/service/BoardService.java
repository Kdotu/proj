package service;

import java.util.ArrayList;
import java.util.List;

import controller.Controller;
import dao.BoardDAO;
import util.ScanUtil;
import util.SpaceUtil;
import util.View;
import vo.BoardVO;
import vo.ComentVO;
import vo.MemberVO;

public class BoardService {
	private static BoardService instance = null;

	private BoardService() {
	}

	public static BoardService getInstance() {
		if (instance == null)
			instance = new BoardService();
		return instance;
	}

	int boardNum;
	int postNum;
	int showNum;
	private int startRowNum = 0; 
	private int endRowNum = 10;
	
	public int getShowNum() {
		return showNum;
	}

	public void setShowNum(int showNum) {
		this.showNum = showNum;
	}

	BoardDAO boardDAO = BoardDAO.getInstance();
	MemberService memberservice = MemberService.getInstance();
	ComentService comentservice = ComentService.getInstance();

	MemberVO loginInfo;

	List<BoardVO> boardList;

	public int list() {

		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.printf("  %s\n", SpaceUtil.format("# 게시판이름", 30, -1));
		System.out.println("-----------------------");
		System.out.printf("  %s\n", SpaceUtil.format("1. 공지사항", 30, -1));
		System.out.printf("  %s\n", SpaceUtil.format("2. 문의게시판", 30, -1));
		System.out.printf("  %s\n", SpaceUtil.format("3. 자유게시판 ", 30, -1));
		System.out.printf("  %s\n", SpaceUtil.format("0. 뒤로가기 ", 30, -1));
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━");

		boardNum = ScanUtil.nextInt();
		while(true) {
			if (boardNum == 1 || boardNum == 2 || boardNum == 3) {
				return View.BOARD_SHOW;

			} else if (boardNum == 0 && (boolean) Controller.sessionStorage.get("login")) {
				MemberVO memVO = (MemberVO) Controller.sessionStorage.get("loginInfo");
				System.out.println(memVO.getMemId().equals("admin"));
				if(memVO.getMemId().equals("admin")) return View.ADMIN_HOME;
				else return View.MEMBER_HOME;
			} else if (boardNum == 0) {
			  return View.HOME;
			}
			else {
				System.out.println("다시 입력해주세요");
				boardNum = ScanUtil.nextInt();
			}
		}
	}

	public int show() {
		List<Integer> param = new ArrayList<>();
		param.add(this.boardNum);
		param.add(this.startRowNum);
		param.add(this.endRowNum);
		List<BoardVO> list = boardDAO.getBoardList(param);
		boardList = list;
		
		switch(boardNum) {
		case 1: System.out.printf("%s", SpaceUtil.format("# 공지게시판", 55, -1)); break;
		case 2: System.out.printf("%s", SpaceUtil.format("# 문의게시판", 55, -1)); break;
		case 3: System.out.printf("%s", SpaceUtil.format("# 자유게시판", 55, -1)); break;
		}
		
		System.out.println();
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
//		System.out.println("===게시판===");
		System.out.printf("%s %s %s %s", SpaceUtil.format("글번호", 6, 0), SpaceUtil.format("글제목", 30, 0),
				SpaceUtil.format("게시날짜", 12, 0), SpaceUtil.format("회원명", 12, 0));
		System.out.println();
		System.out.println("-------------------------------------------------------------");

		for (int i = 0; i < boardList.size(); i++) {
			System.out.printf("%s %s %s %s", SpaceUtil.format((i + this.startRowNum + 1), 6, 0),
					SpaceUtil.format(boardList.get(i).getPostTitle(), 30, 0),
					SpaceUtil.format(boardList.get(i).getPostDate(), 12, 0),
					SpaceUtil.format(boardList.get(i).getMemName(), 12, 0));
			System.out.println();
		}
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

		MemberVO userInfo = (MemberVO) Controller.sessionStorage.get("loginInfo");

		if (userInfo != null) {
			String memId = userInfo.getMemId();
			if (boardNum == 1) {
				if (memId.equals("admin")) {
					System.out.println();
					System.out.println("───────────────────────────────────────────────────────────");
					System.out.printf(" %s", SpaceUtil.format(" 1.이전 │ 2.다음 │ 3.글열람 │ 4.검색 │ 5.글등록 │ 0.게시판목록", 44, 0));
					System.out.println("\n───────────────────────────────────────────────────────────");
				} else {
					System.out.println("────────────────────────────────────────────────");
					System.out.printf(" %s", SpaceUtil.format(" 1.이전 │ 2.다음 │ 3.글열람 | 4.검색 │ 0.게시판목록", 44, 0));
					System.out.println("\n────────────────────────────────────────────────");
				}
			} else {
				System.out.println("───────────────────────────────────────────────────────────");
				System.out.printf(" %s", SpaceUtil.format(" 1.이전 │ 2.다음 │ 3.글열람 │ 4.검색 │ 5.글등록 │ 0.게시판목록", 44, 0));
				System.out.println("\n───────────────────────────────────────────────────────────");
			}

		} else if(boardNum ==1){
			System.out.println("────────────────────────────────────────────────");
			System.out.printf(" %s", SpaceUtil.format(" 1.이전 │ 2.다음 │ 3.글열람 | 4.검색 │ 0.게시판목록", 44, 0));
			System.out.println("\n────────────────────────────────────────────────");
		}else {
			System.out.println("────────────────────────────────────────────────");
			System.out.printf(" %s", SpaceUtil.format(" 1.이전 │ 2.다음 │ 3.글열람 | 4.검색 │ 0.게시판목록", 44, 0));
			System.out.println("\n────────────────────────────────────────────────");
		}
		
		while(true) {
		int input = ScanUtil.nextInt();
		switch (input) {
		case 1:
			this.startRowNum -= 10;
			this.endRowNum -= 10;
			if(this.startRowNum < 0) {
				this.startRowNum = 0;
				this.endRowNum = 10;
			}
			return View.BOARD_SHOW;
		case 2:
			if(boardList.size() < 10) {
			}else {
				this.startRowNum += 10;
				this.endRowNum += 10;
			}
			return View.BOARD_SHOW;
			
		case 3:
			return View.BOARD_DETAIL;
		case 4:
			return View.BOARD_SEARCH;
		case 5:
			return View.BOARD_INSERT;
		case 0:
			return View.BOARD_LIST;
		default:
			System.out.println("다시 입력바랍니다.");
		}
	}
	}		

	public int insert() {
		if (!(boolean) Controller.sessionStorage.get("login")) {
			System.out.println("로그인되어있지 않습니다.");
			return View.MEMBER_LOGIN;
		}
		System.out.printf("%s", SpaceUtil.format("# 게시글 등록", 55, -1));
		System.out.println();
		System.out.println("────────────────────────────────────────────────────");
//		System.out.println("--게시물등록--");
		System.out.println("제목 >>");
		String title = ScanUtil.nextLine();
		while(true) {
			if(title.equals("")) {
			System.out.println("제목을 입력해주세요!");
			title = ScanUtil.nextLine();
			}else {
				break;
			}
		}
		System.out.println("내용 >>");
		String content = ScanUtil.nextLine();
		while(true) {
			if(content.equals("")) {
			System.out.println("제목을 입력해주세요!");
			content = ScanUtil.nextLine();
			}else {
				break;
			}
		}
		System.out.println();

		List<Object> param = new ArrayList<Object>();
		param.add(title);
		param.add(content);

		MemberVO loginInfo = (MemberVO) Controller.sessionStorage.get("loginInfo");

		param.add(loginInfo.getMemNumber());
		param.add(boardNum);

		int result = boardDAO.insert(param);
		List<BoardVO> list = boardDAO.getBoardList(boardNum);
		boardList = list;
		postNum = 1;
		
		if (result > 0) {
			System.out.println("등록에 성공하셨습니다.");
		} else {
			System.out.println("등록에 실패하셨습니다.");
		}

		return View.BOARD_DETAIL2;
	}

	public int detail() {
		System.out.print("열람하실 번호를 입력하여 주세요 >>");
		postNum = ScanUtil.nextInt() - this.startRowNum;
		while(true) {
			if(postNum > boardList.size() + this.startRowNum|| postNum <= 0) {
				System.out.print("다시 입력해주세요 >>");
				postNum = ScanUtil.nextInt() - this.startRowNum;
			}
			else {
				return detail2();
			}
		}
	}
	
	public int detail2() {	
		
		showNum = boardList.get(postNum - 1).getPostNum();
		List<BoardVO> boardInfo = boardDAO.getboardInfo(showNum);
		
		for (int i = 0; i < boardInfo.size(); i++) {
		System.out.printf("# %s", SpaceUtil.format("제목 : " + boardInfo.get(i).getPostTitle(), 55, -1));
//			System.out.println("글 제목 : " + boardInfo.get(i).getPost_title());
		System.out.println();
		System.out.println("─────────────────────────────────────────────────────────────────────────────────────────");
		System.out.println("*  작성자 : " + boardInfo.get(i).getMemName());
		System.out.println("*  작성일자 : " + boardInfo.get(i).getPostDate());
		System.out.println("-----------------------------------------------------------------------------------------");
			System.out.println("내용 : " + boardInfo.get(i).getPostContent());
			System.out.println();
		}


		List<ComentVO> comment = comentservice.comentlist();

		if (!(comment.size() == 0)) {
			System.out.println("─────────────────────────────────────────────────────────────────────────────────────────");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.printf("%s %s %s %s", SpaceUtil.format("댓글번호", 10, 0),
					SpaceUtil.format("작성자",10,0),
					SpaceUtil.format("날짜",10,0),
					SpaceUtil.format("댓글내용",30,0));
			System.out.println();
			System.out.println("-----------------------------------------------------------------------------------------");
//			System.out.println(" 댓글번호 작성자      시간정보      댓글내용                       ");
			
			for (int i = 0; i < comment.size(); i++) {
				System.out.printf("%s %s %s %s", SpaceUtil.format(i + 1, 10, 0),
						 SpaceUtil.format(comment.get(i).getMemName(),10,0),
						 SpaceUtil.format(comment.get(i).getComentDate(),10,0),
						 SpaceUtil.format(comment.get(i).getComentContent(),30,0));
				System.out.println();
			}
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		} else {
			System.out.println("─────────────────────────────────────────────────────────");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("댓글이 없습니다.");
			System.out.println("─────────────────────────────────────────────────────────");
		}

		MemberVO loginInfo = (MemberVO) Controller.sessionStorage.get("loginInfo");

		if ((boolean) Controller.sessionStorage.get("login")) {
			String mem_id = loginInfo.getMemId();
			if (boardNum == 1) {
				if (mem_id.equals("admin")) {
					System.out.println("────────────────────────────────────────────────────────────────────────────");
					System.out.printf(" %s", SpaceUtil.format(" 1.뒤로가기 │ 2.글수정 │ 3.글삭제 │ 4.댓글달기 │ 5.댓글수정 │ 6.댓글삭제", 44, 0));
					System.out.println("\n────────────────────────────────────────────────────────────────────────────");
				} else {
					System.out.println("───────────────────────────────────────────────");
					System.out.printf(" %s", SpaceUtil.format(" 1.뒤로가기 ", 44, 0));
					System.out.println("\n───────────────────────────────────────────────");
//					System.out.println("1. 뒤로가기");
				}
			} else if (boardNum == 2) {
				if (mem_id.equals("admin")) {
					System.out.println("────────────────────────────────────────────────────────────────────────────");
					System.out.printf(" %s", SpaceUtil.format(" 1.뒤로가기 │ 2.글수정 │ 3.글삭제 │ 4.댓글달기 │ 5.댓글수정 │ 6.댓글삭제", 44, 0));
					System.out.println("\n────────────────────────────────────────────────────────────────────────────");
//					System.out.println("1.뒤로가기 2.글수정 3.글삭제 4.댓글달기 5.댓글수정 6.댓글삭제");
				} else {
					System.out.println("───────────────────────────────────────────────");
					System.out.printf(" %s", SpaceUtil.format(" 1.뒤로가기 │ 2.글수정 │ 3.글삭제 ", 44, 0));
					System.out.println("\n───────────────────────────────────────────────");
//					System.out.println("1.뒤로가기 2.글수정 3.글삭제 ");
				}

			} else {
				System.out.println("────────────────────────────────────────────────────────────────────────────");
				System.out.printf(" %s", SpaceUtil.format(" 1.뒤로가기 │ 2.글수정 │ 3.글삭제 │ 4.댓글달기 │ 5.댓글수정 │ 6.댓글삭제", 44, 0));
				System.out.println("\n────────────────────────────────────────────────────────────────────────────");
//				System.out.println(" 1. 뒤로가기 2. 글수정 3. 글삭제 4.댓글달기 5.댓글수정 6.댓글삭제 ");
			}

		} else {
			System.out.println("───────────────────────────────────────────────");
			System.out.printf(" %s", SpaceUtil.format(" 1.뒤로가기 │ 0.로그인 ", 44, 0));
			System.out.println("\n───────────────────────────────────────────────");
//			System.out.println(" 1. 뒤로가기 0. 로그인");
		}

		while(true) {
			int input2 = ScanUtil.nextInt();
			switch (input2) {
			case 1:
				return View.BOARD_SHOW;
			case 2:
				return View.BOARD_UPDATE;
			case 3:
				return View.BOARD_DELETE;
			case 4:
				return View.COMMENT_INSERT;
			case 5:
				return View.COMMENT_UPDATE;
			case 6:
				return View.COMMENT_DELETE;
			case 0:
				return View.MEMBER_LOGIN;
			default:
				System.out.println("숫자만 입력하세요");
				break;
			}
		}
	}

	public int delete() {
		// 관리자는 모든 글을 삭제할수 있어야 한다.
		if (!(boolean) Controller.sessionStorage.get("login")) {
			System.out.println("로그인되어있지 않습니다.");
			return View.MEMBER_LOGIN;
		}

		// 내가 쓴글만 삭제가능
		// 1. 글작성자가 누군지

		int deleteBoard = boardList.get(postNum - 1).getPostNum();

		List<BoardVO> boardInfo = boardDAO.getboardInfo(deleteBoard); // 글가져오기
		int writer = boardInfo.get(0).getMemNum();
		// 2. 내번호는 몇번인가
		int mynum = Integer.parseInt(((MemberVO) Controller.sessionStorage.get("loginInfo")).getMemNumber());
		// 3. 글작성자의 번호와 내 번호가 일치하는지
		String id = (((MemberVO) Controller.sessionStorage.get("loginInfo")).getMemId());
		if (writer == mynum) {
			int result = boardDAO.deletePost(showNum);
			if (result > 0) {
				System.out.println("글 삭제가 완료되었습니다.");
			} else {
				System.out.println("글 삭제에 실패하셨습니다. ");
			}
		}else if(id.equals("admin")){
			int result = boardDAO.deletePost(showNum);
			if (result > 0) {
				System.out.println("글 삭제가 완료되었습니다.");
			} else {
				System.out.println("글 삭제에 실패하셨습니다. ");
			}
		}
		else {
			System.out.println("작성자만 글삭제 가능합니다.");
		}

		return View.BOARD_SHOW;
	}

	public int update() {
		if (!(boolean) Controller.sessionStorage.get("login")) {
			System.out.println("로그인되어있지 않습니다.");
			return View.MEMBER_LOGIN;
		}

		// 1. 내 로그인정보를 불러온다
		int mynum = Integer.parseInt(((MemberVO) Controller.sessionStorage.get("loginInfo")).getMemNumber());

		// 2. 글정보의 글쓴이를 불러온다.

		// int updatePost = boardList.get(post_num-1).getPost_num();

		List<BoardVO> boardInfo = boardDAO.getboardInfo(showNum);
		int writer = boardInfo.get(0).getMemNum();

		// 3. 둘이맞는지 확인!

		if (mynum == writer) {
			System.out.println("수정할 제목 입력 >>");
			String title = ScanUtil.nextLine();
			while(true) {
				if(title.equals("")) {
				System.out.println("제목을 입력해주세요!");
				title = ScanUtil.nextLine();
				}else {
					break;
				}
			}
			System.out.println("수정할 내용 입력 >>");
			String content = ScanUtil.nextLine();
			while(true) {
				if(content.equals("")) {
				System.out.println("제목을 입력해주세요!");
				content = ScanUtil.nextLine();
				}else {
					break;
				}
			}
			List<Object> param = new ArrayList<>();

			param.add(title);
			param.add(content);
			param.add(showNum);

			int result = boardDAO.modifyPost(param);
			if (result > 0) {
				System.out.println("수정이 완료되었습니다.");
			} else {
				System.out.println("수정에 실패하셨습니다. ");
			}
		} else {
			System.out.println("작성자만 글 수정이 가능합니다.");
		}

		return View.BOARD_DETAIL2;

	}


	public int search() {

		MemberVO loginInfo = (MemberVO) Controller.sessionStorage.get("loginInfo");
		System.out.println("─────────────────────────────────────────────────");
		System.out.printf("%s", SpaceUtil.format(" 1.제목키워드 검색 │ 2.작성자 검색 │ 0.뒤로가기", 44, -1));
		System.out.println("\n─────────────────────────────────────────────────");
		System.out.println("메뉴 선택  > ");

//		System.out.println("1. 제목키워드로 검색 2. 작성자이름으로 검색 ");
		int input = ScanUtil.nextInt();
		String con = "";
		out:
		while(true) {
			switch(input) {
			case 1: case 2:
				System.out.println("검색하실 내용을 입력하여 주세요");
				con = ScanUtil.nextLine();
				while(true) {
					if(con.equals("")) {
						System.out.println("내용을 입력해주세요!");
						con = ScanUtil.nextLine();
					}else {
						break out;
					}
				}
			case 0: return View.BOARD_SHOW;
			default: 
				System.out.println("메뉴에서 골라주세요.");
				break ;
			}
		}
		List<BoardVO> list;
		if(input != 0) {
			list = boardDAO.search(input, con, boardNum);
			boardList = list;
		}
		
		
		switch(boardNum) {
		case 1: System.out.printf("%s", SpaceUtil.format("# 공지게시판", 55, -1)); break;
		case 2: System.out.printf("%s", SpaceUtil.format("# 문의게시판", 55, -1)); break;
		case 3: System.out.printf("%s", SpaceUtil.format("# 자유게시판", 55, -1)); break;
		}
		
		System.out.println();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.printf("%s %s %s %s", SpaceUtil.format("글번호", 6, 0), SpaceUtil.format("글제목", 30, 0),
				SpaceUtil.format("게시날짜", 12, 0), SpaceUtil.format("회원명", 12, 0));
		System.out.println();
		
		if (boardList.size() > 0) {
			for (int i = 0; i < boardList.size(); i++) {
				System.out.printf("%s|%s|%s|%s|", SpaceUtil.format(i + 1, 6, 0),
						SpaceUtil.format(boardList.get(i).getPostTitle(), 25, 0),
						SpaceUtil.format(boardList.get(i).getPostDate(), 12, 0),
						SpaceUtil.format(boardList.get(i).getMemName(), 12, 0));
				System.out.println();
			}
			if ((boolean) Controller.sessionStorage.get("login")) {
				String memId = loginInfo.getMemId();
				if (boardNum == 1 && !memId.equals("admin")) {
					System.out.println("───────────────────────────────────────────────");
					System.out.printf(" %s", SpaceUtil.format(" 1.뒤로가기 │ 2.글열람", 44, 0));
					System.out.println("\n───────────────────────────────────────────────");
				} else {
					System.out.println("───────────────────────────────────────────────");
					System.out.printf(" %s", SpaceUtil.format(" 1.뒤로가기 │ 2.글열람 │ 3.글삭제 │ 4.글수정", 44, 0));
					System.out.println("\n───────────────────────────────────────────────");
				}
			
			} else {
				System.out.println("───────────────────────────────────────────────");
				System.out.printf(" %s", SpaceUtil.format(" 1.뒤로가기 │ 2.글열람 │ 3.로그인", 44, 0));
				System.out.println("\n───────────────────────────────────────────────");
			}
		} else {
			System.out.println("찾으시는글이 없습니다.");
			System.out.println("───────────────────────────────────────────────");
			System.out.printf(" %s", SpaceUtil.format(" 1.뒤로가기", 44, 0));
			System.out.println("\n───────────────────────────────────────────────");
		}
		
		
		while(true) {
			int input2 = ScanUtil.nextInt();
			switch (input2) {
			case 1:
				return View.BOARD_SHOW;
			case 2:
				System.out.println("열람할 글번호 선택");
				postNum = ScanUtil.nextInt();
				while(true) {
					if(postNum < 0 || postNum > boardList.size()) {
						System.out.println("글번호 다시 선택");
						postNum = ScanUtil.nextInt();
					}else { break;}
				}
				return View.BOARD_DETAIL2;
			case 3:
				return View.BOARD_DELETE;
			case 4:
				return View.BOARD_UPDATE;
			case 0:
				return View.MEMBER_LOGIN;
			default:
				System.out.println("다시 입력바랍니다.");
			}
		}

	}

	public int mypost() {
		if (!(boolean) Controller.sessionStorage.get("login")) {
			System.out.println("로그인되어있지 않습니다.");
			return View.MEMBER_LOGIN;
		}

		// 로그인한 정보의 mem_num을 불러오기

		int memNum = Integer.parseInt(((MemberVO) Controller.sessionStorage.get("loginInfo")).getMemNumber());
		List<BoardVO> list = boardDAO.mypost(memNum);
		boardList = list;
		System.out.println("━━━━━━━━━━");
		System.out.println(" 내글목록");
		System.out.println("━━━━━━━━━━");
		System.out.printf("%s|%s|%s|%s|", 
				SpaceUtil.format("글번호", 6, 0), 
				SpaceUtil.format("글제목", 25, 0),
				SpaceUtil.format("게시날짜", 12, 0), 
				SpaceUtil.format("회원명", 12, 0));
		System.out.println();
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				System.out.printf("%s|%s|%s|%s|", SpaceUtil.format(i + 1, 6, 0),
						SpaceUtil.format(list.get(i).getPostTitle(), 25, 0),
						SpaceUtil.format(list.get(i).getPostDate(), 12, 0),
						SpaceUtil.format(list.get(i).getMemName(), 12, 0));
				System.out.println();
			}
			System.out.println("───────────────────────────────────────────────");
			System.out.printf(" %s", SpaceUtil.format(" 1.뒤로가기 │ 2.글열람 │ 3.글쓰기", 44, 0));
			System.out.println("\n───────────────────────────────────────────────");
			int input2 = ScanUtil.nextInt();
			switch (input2) {
			case 1:
				return View.MEMBER_HOME;
			case 2:
				return View.BOARD_DETAIL;
			case 3:
				return View.BOARD_INSERT;

			}
			return View.BOARD_DETAIL;

		} else {
			System.out.println("작성된 게시글이 없습니다.");
			System.out.println("───────────────────────────────────────────────");
			System.out.printf(" %s", SpaceUtil.format(" 1.뒤로가기 │ 2.글쓰기 ", 44, 0));
			System.out.println("\n───────────────────────────────────────────────");
			int input3 = ScanUtil.nextInt();
			switch (input3) {
			case 1:
				return View.MEMBER_HOME;
			case 2:
				return View.BOARD_INSERT;
			default:
				return View.BOARD_SHOW;
			}
		}
	}

	

}
