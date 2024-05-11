package util;

public interface View {
	int HOME = 1;
	int CLOSE = 0;
	
	/* 모든 기능들은 Controller에서 요청이 있을떄 처리되도록 설계
	 * Controller -> Service -> DAO -> VO -> Service -> Controller
	 * Controller에서 받은 요청을 Service에서 처리
	 * Service에서 필요한 데이터를 DAO에 요청
	 * DAO에서 받아온 데이터를 VO객체에 저장 후 Service처리
	 * ** 각각의 테이블별로 저장하고 있는 데이터를 다른 서비스에서 사용해야 할수도 있기때문에 
	 * 필요시 각 파트 담당자에게 요청-> 중간 과정 공유폴더에 업로드해서 공유**
	 * */
	
	// 회원관련
	int MEMBER = 2;
	
	int MEMBER_HOME = 200;		// 멤버화면
	int MEMBER_JOIN = 201; 		// 회원가입
	int MEMBER_INFO = 202;		// 회원정보
	int MEMBER_PAGE = 203; 		// 마이페이지
	int MEMBER_UPDATE = 204; 	// 회원정보 수정
	
	int MEMBER_LOGIN = 211; 	// 로그인
	int MEMBER_LOGOUT = 212; 	// 로그아웃
	int MEMBER_FIND = 213;		// 회원정보 찾기
	
	int ADMIN_HOME = 231;		// 관리자
	int ADMINBOOKLIST = 232;	// 대출/예약 리스트
	int ADMINBOOKSTOCK = 233;	// 도서 재고관리
	int ADMINBOARD = 234;		// 관리자모드 게시판
	int ADMINBOOK_WISHLIST = 235;	// 희망도서
	
	
	/* 
	 * 회원 관련 기능
	 * 회원가입
	 * 마이페이지
	 * 
	 * 관리자 관련 기능
	 * 대여/연체관리
	 * 반납관리
	 * 재고관리
	 * 
	 * 공통 기능
	 * 로그인
	 * 로그아웃
	 * */
	
	// 도서 관련
	int BOOK = 3;
	/* 검색
	 * 예약
	 * 대여
	 * 반납
	 * */
	int BOOK_LIST = 300;// 전체 도서 리스트
	int BOOK_SEARCH = 310;	// 도서 검색
	int INSERT_BOOK = 320;	// 도서 추가
	int DELETE_BOOK = 330;	// 도서 삭제
	
	// 희망 도서 , 관심도서
	int MEMBER_WISH_BOOK = 350;		// 희망도서 신청
	int MEMBER_INTEREST_BOOK = 351; // 관심도서
	int MEMBER_INTEREST_LIST = 352; // 관심도서 리스트
		
		
	// 대여 관련
	int RENT = 4;
	int RENT_OR_RESERVE = 400; // 예약/ 대여 선택
	int RENT_BOOK = 410; // 도서 대여 실행 
	int RENT_LIST = 411; // 통합 대여 목록
	int RESERVE_BOOK = 420; // 도서 예약 실행 
	int RESERVE_LIST = 421; // 통합 예약 목록
	int CANCEL_RESERVE = 430; // 예약 취소 실행
	int MEM_EXTEND = 440; // 도서 연장 실행
	int RETURN_BOOK = 450; // 도서 반납 실행
		
	
	/* 회원이 도서를 예약, 대여, 반납하면 수행할 기능 구현
	 * 예약, 대여시 테이블에 데이터 삽입 
	 * 반납시 반납일자 업데이트
	 * 대여 연장시 반납일자, 연장여부 업데이트
	 * */
	
	// 게시판 관련
	int BOARD = 5;
	int BOARD_LIST = 51;
	int BOARD_DETAIL =52;
	int BOARD_INSERT =53;
	int BOARD_DELETE = 54;
	int BOARD_UPDATE = 55;
	int BOARD_SEARCH = 56 ;
	int BOARD_MYPOST = 57;
	int BOARD_SHOW = 58;
	int BOARD_DETAIL2 = 5999;


	/* 게시글 작성, 수정, 삭제
	 * 게시판은 3종류중에 한가지 - 공지사항은 관리자만 작성 가능
	 * 공지사항
	 * 문의게시판
	 * 자유게시판
	 * 각 글은 작성자만 수정할수 있고 관리자는 모두 삭제가능
	 * 
	 * */
	
	// 댓글 관련
	int COMMENT = 6;
	int COMMENT_SHOW = 62;
	int COMMENT_INSERT = 63;
	int COMMENT_DELETE = 64;
	int COMMENT_UPDATE = 65;
	/* 댓글 작성
	 * 댓글 삭제
	 * 댓글 수정?
	 * 각 게시글마다 댓글 관리
	 * 댓글 수정은 작성자만 가능하게
	 * */
	
	
}