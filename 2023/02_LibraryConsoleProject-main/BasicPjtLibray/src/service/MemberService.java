package service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import controller.Controller;
import dao.BookDAO;
import dao.MemberDAO;
import dao.RentDAO;
import util.ScanUtil;
import util.SpaceUtil;
import util.View;
import vo.BookVO;
import vo.MemberVO;
import vo.MemBookVO;

public class MemberService {
	private static MemberService instance = null;
	private MemberService() {}
	public static MemberService getInstance() {
		if(instance == null) instance = new MemberService();
		return instance;
	}
	MemberDAO memberDAO = MemberDAO.getInstance();
	BookDAO bookDAO = BookDAO.getInstance();
	RentDAO rentDAO = RentDAO.getInstance();
	MemberVO loginInfo = new MemberVO();
	
	int result = 0;
	int input = 0;
	static int checkNum;
	
	public int join(){ 		// 회원가입
		// 회원의 정보를 기입받음
		List<Object> param = new ArrayList<>();
		String id = "";
		String pw = "";
		int status = 0;
//		System.out.println("============ 회원가입창 ============");
		joinMenu(1,0);
		// 필수정보
		idCheck:
		while(true) { // 아이디 체크
		System.out.println("* ID : 영어와 숫자, 5-15글자로 입력해주세요.");
		System.out.print("☞   아이디  (0.회원가입종료) >> ");
		id = ScanUtil.nextLine();
		if(id.equals("0")) {
			return View.HOME;
		}
		
		boolean checkId = PatternChecker(id,1); // 아이디 조건 체크
		if(checkId == false) {
			System.out.println("아이디가 조건에 맞지 않습니다. 재입력해주세요.");
			continue idCheck;
		}else {
			status = memberDAO.idCheck(id);		// 아이디 중복 체크
			if(status > 0) {
				System.out.println("사용가능한 아이디 입니다.");
				param.add(id);
				break idCheck;
			}else {
				System.out.println("이미 사용중인 아이디입니다.");
				continue idCheck;
				}
			}
		}
		pwCheck:
		while(true) { // 비밀번호 체크
		System.out.println("* PW : 영어와 숫자를 혼합하여  8-20글자로 설정");
		System.out.print("☞   비밀번호  (0.회원가입종료) >> ");
		pw = ScanUtil.nextLine();
		if(pw.equals("0")) {
			return View.HOME;
		}
		
		boolean checkPw = PatternChecker(pw,2);
		if(checkPw == false) {
			System.out.println("비밀번호가 조건에 맞지 않습니다. 재입력해주세요.");
			continue pwCheck;
		}else {
			param.add(pw);
			break pwCheck;
			}
		}
		joinMenu(2,0);
		nameCheck:
		while(true) {
		System.out.println("☞   이름 (0.회원가입종료) >> ");
		String name = ScanUtil.nextLine();
		if(name.equals("0")) {
			return View.HOME;
		}
		boolean checkName = PatternChecker(name,4);
		if(checkName == false) {
			System.out.println("한글만 입력해주세요. (숫자 입력 오류)");
			continue nameCheck;
		}else {
			param.add(name);
			break nameCheck;
			}
		}
		joinMenu(3,0);
		
		hpCheck:
		while(true) {
		System.out.println("☞   연락처 (숫자만 입력) (0.회원가입종료)>>  ");
		String hp = ScanUtil.nextLine();
		if(hp.equals("0")) {
			return View.HOME;
		}
		String[] number = {"010","011","012","031","032","033","041","042","043","044","051","052","053","054","055","061","062","063","064"};
		int check = 0;
		
		boolean isNumeric = PatternChecker(hp,5);
		if(!isNumeric) {
			System.out.println("숫자만 입력해주세요!");
			continue;
		}else {
			for (int i = 0; i < number.length; i++) {
				if(hp.substring(0,3).equals(number[i]))  check = 1;	
			}
			if(check == 0) {	
				System.out.println("전화번호 앞자리가 유효하지 않습니다. 확인 후 다시 입력해주세요.");
				continue;
			}
			if(hp.length() > 11 | hp.length() <10) {
				System.out.println("전화번호 길이가 맞지 않습니다. 확인 후 다시 입력해주세요.");
			}else if(hp.length() == 11 && (hp.substring(0,3).equals(number[0]))
					|hp.length() == 11 && (hp.substring(0,3).equals(number[1]))
					|hp.length() == 11 && (hp.substring(0,3).equals(number[2]))){
			}else {
				param.add(hp);
				break;
				}
			}
		}
		MemberVO memberVO = new MemberVO();
		
		memberVO.setMemId(param.get(0).toString());
		memberVO.setMemPass(param.get(1).toString());
		memberVO.setMemName(param.get(2).toString());
		memberVO.setMemHp(param.get(3).toString());
		
		joinMenu(4,0);
		// 선택정보 기입창 (건너뛰기 가능)
		
		System.out.println("선택 정보 입력하시겠습니까 ?");
		System.out.println("☞    1.네 │ 2. 아니오");
		switch(ScanUtil.nextInt()) {
		case 1:
			// 성별
			System.out.printf("%s", SpaceUtil.format("# 선택정보 추가기입", 55, -1));
			System.out.println("\n☞   성별  ( 1.여성 │ 2.남성  )");
			
			while(true) {
				int gender = ScanUtil.nextInt();
				if(gender == 1) {
					param.add("여성");
					break;
				}else if(gender == 2) {
					param.add("남성");
					break;
				}else {
					System.out.println("1번, 2번 중에서 선택해주세요.");
				}
			}
			 // 생년월일
			
			dateCheck:
			while(true) {
				Date today = new Date();
				SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
				// 날짜타입에 맞는지 체크
				MemberService dateFormatCheck = new MemberService();
				checkNum = 0;
				System.out.println("☞   생년월일 (ex)19941008 8자리 숫자만 입력) >>");
				String birth = ScanUtil.nextLine();
				// 날짜 형식으로 변환
				if(!(birth.length() == 8)) {
					System.out.println("생년월일을 8글자로 정확히 입력해주세요.");
					continue dateCheck;
				}
				if(checkNum == 1) {
					System.out.println("재입력해주세요.");
					continue dateCheck;
				}else {
					String birthDate = (birth.substring(0,4) + "-" + birth.substring(4,6) + "-" + birth.substring(6,8));
					dateFormatCheck.formatCheck(birthDate);
					Date tmp;
					try {
						 // 오늘 이후 날짜 조건이 맞는지 체크
						tmp = dateformat.parse(birth);
						boolean checkBirth = today.before(tmp);
						
						if(checkBirth) {
							System.out.println("미래일자는 생년월일로 입력할 수 없습니다.");
							continue dateCheck;
						}else {
//							System.out.println("입력일자  : " + birthDate);
							param.add(birth);
							break dateCheck;
						}
					}catch (ParseException e) {
						e.printStackTrace();
					}
					 break dateCheck;
					}
				}
			
			// 주소
			System.out.println("☞   주소	(ex. 대전시 유성구 ㅇㅇㅇ) >> ");
			param.add(ScanUtil.nextLine());
			emailCheck:
			while(true) { // 비밀번호 체크
				System.out.println("☞   이메일주소  (ex. asdf@naver.com) >>");
				String email = ScanUtil.nextLine();
			
				boolean checkEmail = PatternChecker(email,3);
				if(checkEmail == false) {
					System.out.println("이메일 주소에 '@'를 포함해주세요.");
					continue emailCheck;
				}else {
					param.add(email);
					break emailCheck;
					}
				}
			memberVO.setMemGender(param.get(4).toString());
			memberVO.setMemBir(param.get(5).toString());
			memberVO.setMemAdd(param.get(6).toString());
			memberVO.setMemEmail(param.get(7).toString());
			
			result = memberDAO.joinPlus(memberVO);
			break;
			
		case 0: default:
			result = memberDAO.join(memberVO);
			break;
		}
		if(result > 0) {
			joinMenu(6,0);
//			System.out.println("회원가입 완료");
		}else {
			joinMenu(0,0);
//			System.out.println("정보 다시 입력");
			return View.MEMBER_JOIN;
		}
		return View.HOME;
//		}
	}
	public int login() {   // 로그인
	      // 1. 아이디와 비밀번호 기입
	      // 성공 시 시작 페이지로 돌아감
	      if((boolean)Controller.sessionStorage.get("login")) { // == true
	         System.out.println("이미 로그인 되어 있습니니다.");
	         return View.HOME;
	      }
	      List<String> param = new ArrayList<>();
	      
	      loginMenu(1);
	      String id = ScanUtil.nextLine();
	      if(id.equals("0")){
	    	  return View.HOME;
	      }else {
	    	  param.add(id);
	      }
	      loginMenu(2);
	      String pw = ScanUtil.nextLine();
	      if(pw.equals("0")){
	    	  return View.HOME;
	      }else {
	    	  param.add(pw);
	      }
	      
	      Map<String,Object> userInfo =memberDAO.login(param); 
	      
	      if(userInfo == null) {
	         System.out.println("아이디 혹은 비밀번호를 잘못 입력했습니다.");
	         return View.MEMBER_LOGIN;
	      }else {
	         if(param.get(0).equals("admin")) {
//	            System.out.println("========== 관리자모드 로그인 ==========");
	   	        loginMenu(4);
	            loginInfo.setMemId(param.get(0));
	            List<Object> memberList = memberDAO.memberInfo(loginInfo);
	            MemberVO memberVO = (MemberVO) memberList.get(0);
		         Controller.sessionStorage.put("loginInfo", loginInfo);
		         Controller.sessionStorage.put("login", true);
	            return View.ADMIN_HOME;
	            
	         }else {
	        	 loginMenu(3);
		         loginInfo.setMemId(param.get(0));
		         List<Object> memberList = memberDAO.memberInfo(loginInfo);
		         MemberVO memberVO = (MemberVO) memberList.get(0);
		         
		         Controller.sessionStorage.put("loginInfo", loginInfo);
		         Controller.sessionStorage.put("login", true);
		         System.out.println(userInfo.get("MEM_NAME") + "님 어서오세요." );
		         return View.MEMBER_HOME;
	         }
	      }
	   }
	public int logout() {  // 로그아웃
		loginMenu(5);
//		System.out.println("로그아웃 성공");
		Controller.sessionStorage.put("login", false);
		Controller.sessionStorage.put("loginInfo", null);
		// 시작페이지로 돌아감
		return View.HOME;
	}
	public int myPage() {  // 마이페이지
		menuFormat(1,0);
		// 1. 대여 도서	 - 대여확인  /연장	
		// 2. 예약 도서   - 예약확인 /취소
		// 3. 회원정보 수정	
		// 4. 희망도서 신청
		// 5. 선호도서 목록 확인
//		System.out.println("1.대여도서  2.예약도서  3.회원정보 수정 4.희망도서 5.선호도서  0.뒤로가기"); 
		input = ScanUtil.nextInt();
		int result = 0;

		switch(input) {
		case 1: 
			return View.RENT_LIST;
			
		case 2: 
			return View.RESERVE_LIST;
			
		case 3:
		// 회원정보 리스트 보여줌
			return View.MEMBER_INFO;
		case 4:
			// 희망도서 신청
			return View.MEMBER_WISH_BOOK;
		case 5:
			// 선호도서 목록
			return View.MEMBER_INTEREST_LIST;
		case 6:
			// 내글 목록
			return View.BOARD_MYPOST;
			
		case 0: default:
			return View.MEMBER_HOME;
		}
	}
	public int memInfoUpdate() { // 회원정보 수정
		int result = 0;
		String updateInfo = "";
		String updateContent = "";
		List<String> param = new ArrayList<String>();
		
		while(true) {
			menuFormat(2,0);
//		System.out.println("1.이름 2.성별 3.생년월일 4.연락처 5.주소 6.이메일주소 0.뒤로가기");
		int input = ScanUtil.nextInt();
		switch(input) {
			case 1:
			updateInfo += "MEM_NAME";
			
			nameCheck:
			while(true) {
				System.out.println("☞ 수정할 이름 입력 >> ");
				String name = ScanUtil.nextLine();
				
				boolean checkName = PatternChecker(name,4);
				if(checkName == false) {
					System.out.println("한글만 입력해주세요. (숫자 입력 오류)");
					continue nameCheck;
				}else {
					updateContent = name;
					break nameCheck;
					}
				}
			break;
			
			case 2:
			updateInfo += "MEM_GENDER";

			genderCheck:
			while(true) {
				System.out.println("☞ 수정할 정보선택  ( 1.여성 │ 2.남성  )");
					int gender = ScanUtil.nextInt();
					if(gender == 1) {
						updateContent = "여성";
						break genderCheck;
					}else if(gender == 2) {
						updateContent = "남성";
						break genderCheck;
					}else {
						System.out.println("재입력");
						continue genderCheck;
					}
				}
			break;
			
			case 3:
			updateInfo += "MEM_BIR";
			
			dateCheck:
				while(true) {
					Date today = new Date();
					SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
					// 날짜타입에 맞는지 체크
					MemberService dateFormatCheck = new MemberService();
					checkNum = 0;
					System.out.println("☞   생년월일 (ex)19941008 8자리 숫자만 입력) >> ");
					String birth = ScanUtil.nextLine();
					boolean isNumber = PatternChecker(birth,5);
					
					if(!isNumber) {
						System.out.println("숫자만 입력해주세요!");
						continue dateCheck;
					}
					// 날짜 형식으로 변환
					if(!(birth.length() == 8)) {
						System.out.println("생년월일을 8자리 숫자로 정확히 입력해주세요.");
						continue dateCheck;
					}
					if(checkNum == 1) {
						System.out.println("재입력해주세요.");
						continue dateCheck;
					}else {
						String birthDate = (birth.substring(0,4) + "-" + birth.substring(4,6) + "-" + birth.substring(6,8));
						dateFormatCheck.formatCheck(birthDate);
						Date tmp;
						try {
							 // 오늘 이후 날짜 조건이 맞는지 체크
							tmp = dateformat.parse(birth);
							boolean checkBirth = today.before(tmp);
							
							if(checkBirth) {
								System.out.println("미래일자는 생년월일로 입력할 수 없습니다.");
								continue dateCheck;
							}else {
//								System.out.println("입력일자  : " + birthDate);
								updateContent = birth;
								break dateCheck;
							}
						}catch (ParseException e) {
							e.printStackTrace();
							}
						}
					}
			break;
			
			case 4:
			updateInfo += "MEM_HP";
			hpCheck:
			while(true) {
			System.out.println("☞   수정할 연락처 (숫자만 입력) >>  ");
			String hp = ScanUtil.nextLine();
			String[] number = {"010","011","012","031","032","033","041","042","043","044","051","052","053","054","055","061","062","063","064"};
			int check = 0;
//				boolean isNumeric = hp.matches("[+-]?\\d*(\\.\\d+)?");
			boolean isNumeric = PatternChecker(hp,5);
			if(!isNumeric) {
				System.out.println("숫자만 입력해주세요!");
				continue;
			}else {
				for (int i = 0; i < number.length; i++) {
					if(hp.substring(0,3).equals(number[i])) check = 1;
				}
				if(check == 0) {	
					System.out.println("전화번호 앞자리가 유효하지 않습니다. 확인 후 다시 입력해주세요.");
					continue;
				}
				if(hp.length() > 11 | hp.length() <10) {
					System.out.println("전화번호 길이가 맞지 않습니다. 확인 후 다시 입력해주세요.");
				}else if(hp.length() == 11 && (hp.substring(0,3).equals(number[0]))
						|hp.length() == 11 && (hp.substring(0,3).equals(number[1]))
						|hp.length() == 11 && (hp.substring(0,3).equals(number[2]))){
				}else {
					updateContent = hp;
					break;
				}
			}
		}
			break;
			
			case 5:
				updateInfo += "MEM_ADD";
				System.out.println("☞   수정할 주소	(ex. 대전시 유성구 ㅇㅇㅇ) >>");
				updateContent = ScanUtil.nextLine();
				break;
			case 6:
				updateInfo += "MEM_EMAIL";
				emailCheck:
				while(true) { // 비밀번호 체크
					System.out.println("☞   수정할 이메일주소  (ex. asdf@naver.com) >> ");
					String email = ScanUtil.nextLine();
					boolean checkEmail = PatternChecker(email,3);
				if(checkEmail == false) {
					System.out.println("이메일 주소가 조건에 맞지 않음. 재입력");
					continue emailCheck;
				}else {
					updateContent = email;
					break emailCheck;
					}
				}
				break;
				
			case 0: default :
				return View.MEMBER_INFO;
			}
			
		param.add(updateInfo);
		param.add(updateContent);
		
		String gender = "-";
		String birth = "-";
		String hp = "-";
		String add = "-";
		String email = "-";
		
		if(loginInfo.getMemGender() != null) {
			gender = loginInfo.getMemGender();
		}
		if(loginInfo.getMemBir() != null) {
			birth = loginInfo.getMemBir();
			
			if(birth.length() > 11) {
				birth = birth.substring(0,11);
			}
			if(birth.length() == 8) {
				birth = birConvert(birth);
			}
		}
		if(loginInfo.getMemHp() != null) {
			hp = loginInfo.getMemHp();
		}
		if(loginInfo.getMemAdd() != null) {
			add = loginInfo.getMemAdd();
		}
		if(loginInfo.getMemEmail() != null) {
			email = loginInfo.getMemEmail();
		}
		
			switch (input) {
			case 1: // 이름
				System.out.println("[변경 전 정보] : " + loginInfo.getMemName() + " ---→ [변경 후 정보] : " + updateContent);
				break;
			case 2: // 성별
				System.out.println("[변경 전 정보] : " + gender + " ---→ [변경 후 정보] : " + updateContent);
				break;
			case 3: // 생년월일
//				String beforeBir = loginInfo.getMemBir();
//				beforeBir = beforeBir.substring(0,10);
				String afterBir = birConvert(updateContent);
				
				System.out.println("[변경 전 정보] : " + birth + " ---→ [변경 후 정보] : " + afterBir);
				break;
			case 4: // 연락처
				
				String afterHp = updateContent;
				System.out.println("[변경 전 정보] : " + hp + " ---→ [변경 후 정보] : " + hpConvert(afterHp));
				break;
			case 5: // 주소
				System.out.println("[변경 전 정보] : " + add + " ---→ [변경 후 정보] : " + updateContent);
				
			case 6:	// 이메일주소
				System.out.println();
				System.out.println("[변경 전 정보] : " + email + " ---→ [변경 후 정보] : " + updateContent);
				break;
			default:
				break;
			}
		
		
		System.out.println();
		System.out.println("* 정보를 수정하시겠습니까?");
		System.out.println("☞   1.수정 │ 2.취소 ");
		
		switch (ScanUtil.nextInt()) {
		case 1:
			result = memberDAO.memberUpdate(param,loginInfo);
			if(result > 0) {
				switch(input) {
				case 1: loginInfo.setMemName(updateContent); break;
				case 2: loginInfo.setMemGender(updateContent); break;
				case 3: loginInfo.setMemBir(updateContent); break;
				case 4: loginInfo.setMemHp(updateContent); break;
				case 5: loginInfo.setMemAdd(updateContent); break;
				case 6: loginInfo.setMemEmail(updateContent); break;
				}
				return View.MEMBER_INFO;
			}else {
				System.out.println("정보수정실패..");
				return View.MEMBER_INFO;
			}
		case 2: case 0: default:
			break;
			}
		}
	}
	public int memInfo() { // 회원정보
		
			String gender = "-";
			String birth = "-";
			String hp = "-";
			String add = "-";
			String email = "-";
			
			if(loginInfo.getMemGender() != null) {
				gender = loginInfo.getMemGender();
			}
			if(loginInfo.getMemBir() != null) {
				birth = loginInfo.getMemBir();
				
				if(birth.length() > 11) {
					birth = birth.substring(0,11);
				}
				if(birth.length() == 8) {
					birth = birConvert(birth);
				}
			}
			if(loginInfo.getMemHp() != null) {
				hp = loginInfo.getMemHp();
			}
			if(loginInfo.getMemAdd() != null) {
				add = loginInfo.getMemAdd();
			}
			if(loginInfo.getMemEmail() != null) {
				email = loginInfo.getMemEmail();
			}
			
			
			// 회원정보 정리해서 보여주기
			System.out.printf("%s", SpaceUtil.format("# 회원정보", 55, -1));
			System.out.println();
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.printf("%s %s\n", SpaceUtil.format("회원명", 10, 0),SpaceUtil.format(loginInfo.getMemName(), 30, 0));
			System.out.printf("%s %s\n", SpaceUtil.format("성별", 10, 0),SpaceUtil.format(gender, 30, 0));
			System.out.printf("%s %s\n", SpaceUtil.format("생년월일", 10, 0),SpaceUtil.format(birth, 30, 0));
			System.out.printf("%s %s\n", SpaceUtil.format("연락처", 10, 0),SpaceUtil.format(hpConvert(hp), 30, 0));
			System.out.printf("%s %s\n", SpaceUtil.format("주소", 10, 0),SpaceUtil.format(add, 30, 0));
			System.out.printf("%s %s\n", SpaceUtil.format("이메일", 10, 0),SpaceUtil.format(email, 30, 0));
			
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("---------------------------------------");
			System.out.println("────────────────────────────");
			System.out.printf("%s", SpaceUtil.format(" 1.회원정보 수정 │ 0.뒤로가기", 25, 0));
			System.out.println("\n└────────────────────────────");
			System.out.println("메뉴 선택  > ");
			
		while(true) {
		switch(ScanUtil.nextInt()) {
		case 1:
			return View.MEMBER_UPDATE;
		case 0: default:
			return View.MEMBER_PAGE;
			}
		}
	}
	public int adminBookList() {
		System.out.printf("%s", SpaceUtil.format("# 대여/예약 관리", 55, -1));
		System.out.println();
		System.out.println("────────────────────────────────────────");
		System.out.printf("%s", SpaceUtil.format(" 1.반납/예약취소 도서선택 │ 0.뒤로가기", 25, 0));
		System.out.println("\n────────────────────────────────────────");
		System.out.println("메뉴 선택  > ");
//		System.out.println("대여/예약중인 책의 목록을 가져옵니다.");
		List<BookVO> rentBook = bookDAO.getRentBook();
		if(rentBook.size() > 0) {
			for(int i = 0; i < rentBook.size(); i++) {
				System.out.print(SpaceUtil.format((i + 1), 5, 0));
				System.out.print(SpaceUtil.format(rentBook.get(i).getBookName(), 30, 0));
				System.out.print(SpaceUtil.format(rentBook.get(i).getBookWriter(), 20, 0));
				System.out.print(SpaceUtil.format(rentBook.get(i).getBookISBN(), 13 , 0));
				System.out.print(SpaceUtil.format(rentBook.get(i).getBookRentable(), 3, 0));
				System.out.println();
			}
//			System.out.println("반납/예약 취소할 책을 선택하세요. ");
			int input = ScanUtil.nextInt();
			if(rentBook.size() >= input && input > 0) {
				rentDAO.setReturn(rentBook.get(input - 1).getBookNumber());
				System.out.println(rentBook.get(input - 1).getBookName() + " 이(가) 반납되었습니다.");
				
				return View.ADMIN_HOME;
			}else {
				System.out.println("잘못 입력하였습니다.");
				return adminBookList();
			}
		}
		else {
			System.out.println("현재 대여/예약중인 책이 없습니다.");
			return View.ADMIN_HOME;
		}
	}
	public int adminBookStock() {
		System.out.printf("%s", SpaceUtil.format("# 도서 관리", 55, -1));
		System.out.println();
		System.out.println("────────────────────────────────────────────────────────");
		System.out.printf("%s", SpaceUtil.format(" 1.도서추가 │ 2.도서삭제 │ 3.희망도서목록 │ 0.뒤로가기", 25, 0));
		System.out.println("\n────────────────────────────────────────────────────────");
		System.out.println("메뉴 선택  > ");
		
//		System.out.println("도서 1.추가   2. 삭제");
		BookService bookService = BookService.getInstance();
		switch(ScanUtil.nextInt()) {
	      case 1 :
	         return View.INSERT_BOOK;
	      case 2:
	         return View.BOOK_SEARCH;
		case 3:
			return View.ADMINBOOK_WISHLIST;
		default:
			return View.ADMIN_HOME;
		}
	}
	public int memberFind() { // 회원정보찾기
		List<String> param = new ArrayList<>();
		// 필수정보로 찾음 (이름, 연락처)
		menuFormat(3,0);
		String id = "";
		String name = "";
		String hp = "";
		
		switch(ScanUtil.nextInt()) {
		case 1:
			menuFormat(4,0);
			nameCheck: // 이름 한글체크
			while(true) {
				name = ScanUtil.nextLine();
				if(name.equals("0")) {
					return View.HOME;
				}
				boolean checkName = PatternChecker(name,4);
				if(checkName == false) {
					System.out.println("한글만 입력해주세요. (입력 오류)");
					continue nameCheck;
				}else {
					param.add(name);
					break nameCheck;
					}
				}
			menuFormat(5,0);
			hpCheck:
				while(true) {
				hp = ScanUtil.nextLine();
				if(hp.equals("0")) {
					return View.HOME;
				}
				String[] number = {"010","011","012","031","032","033","041","042","043","044","051","052","053","054","055","061","062","063","064"};
				int check = 0;
				
				boolean isNumeric = PatternChecker(hp,5);
				if(!isNumeric) {
					System.out.println("숫자만 입력해주세요!");
					continue;
				}else {
					for (int i = 0; i < number.length; i++) {
						if(hp.substring(0,3).equals(number[i]))  check = 1;	
					}
					if(check == 0) {	
						System.out.println("전화번호 앞자리가 유효하지 않습니다. 확인 후 다시 입력해주세요.");
						continue;
					}
					if(hp.length() > 11 | hp.length() <10) {
						System.out.println("전화번호 길이가 맞지 않습니다. 확인 후 다시 입력해주세요.");
					}else if(hp.length() == 11 && (hp.substring(0,3).equals(number[0]))
							|hp.length() == 11 && (hp.substring(0,3).equals(number[1]))
							|hp.length() == 11 && (hp.substring(0,3).equals(number[2]))){
					}else {
						param.add(hp);
						break;
						}
					}
				}
			
			Map<String, Object> idMap =memberDAO.findId(param);
			if(idMap != null) {
				System.out.println(name + "님의 아이디는 " + idMap.get("MEM_ID") + "입니다.");
			return View.HOME;
			}else {
				System.out.println("존재하지 않는 회원입니다.");
			return View.MEMBER_FIND;
			}
			
		case 2:
			menuFormat(6,0);
			id = ScanUtil.nextLine();
			if(id.equals("0")) {
				return View.HOME;
			}
			menuFormat(7,0);
			hp = ScanUtil.nextLine();
			if(hp.equals("0")) {
				return View.HOME;
			}
			param.add(id);
			param.add(hp);
			Map<String, Object> pwMap =memberDAO.findPw(param);
			if(pwMap != null) {
				System.out.println(id + "님의 비밀번호는 " + pwMap.get("MEM_PASS") + " 입니다");
				System.out.println();
				return View.HOME;
			}else {
				System.out.println("존재하지 않는 회원입니다.");
				System.out.println();
				return View.MEMBER_FIND;
			}
		case 0: default:
			return View.HOME;
			}
		}
	
	private void memPossible() { // 대여/예약가능권수 표시
		List<List<Object>> row = new ArrayList<>();
		String param = loginInfo.getMemId();
		row = rentDAO.showRentList(param);
		List<List<Object>> rentPossible =  new ArrayList<>();
		rentPossible = row; 
		System.out.println("\t\t\t\t\t현재 대여가능 권수 : " + rentPossible.size() + " / 5 권");
		
		row = new ArrayList<>();
		row = rentDAO.showReserveList(loginInfo.getMemId());
		List<List<Object>> reservePossible = new ArrayList<>();
		reservePossible = row;
		System.out.println("\t\t\t\t\t현재 예약가능 권수 : " + reservePossible.size() + " / 3 권");
		
	}
	public void formatCheck(String date) {
		try {
		    DateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
		    parser.setLenient(false);
		    parser.parse(date);
		} catch(IllegalArgumentException e) {
			checkNum = 1;
			System.out.println("날짜형식에 맞지않습니다. 다시 입력해주세요. (입력하신 날짜: " + date + ")");
//			e.printStackTrace();
		} catch(ParseException e) {
			checkNum = 1;
			System.out.println("날짜형식에 맞지않습니다. 다시 입력해주세요. (입력하신 날짜: " + date + ")");
//			e.printStackTrace();
		}
	}
	private void menuFormat(int number,int line) {
		switch(number) {
		case 1:
			// 마이페이지
			System.out.printf("%s", SpaceUtil.format("# 마이페이지", 55, -1));
			System.out.println();
			memPossible();
			System.out.println("─────────────────────────────────────────────────────────────────────────────────────────");
			System.out.printf("%s", SpaceUtil.format(" 1.대여도서 │ 2.예약도서 │ 3.회원정보 │ 4.희망도서  │ 5.관심도서 │ 6.내글목록│ 0.뒤로가기", 44, 0));
			System.out.println("\n─────────────────────────────────────────────────────────────────────────────────────────");
			System.out.println("☞   메뉴 선택  > ");
			break;
		
		case 2:
			// 회원정보 수정
			System.out.printf("%s", SpaceUtil.format("# 회원정보 수정", 55, -1));
			System.out.println();
			System.out.println("───────────────────────────────────────────────────────────────────────────────");
			System.out.printf("│%s", SpaceUtil.format(" 1.이름 │ 2.성별 │ 3.생년월일 │ 4.연락처 │ 5.주소 │ 6.이메일주소 │ 0.뒤로가기", 44, 0));
			System.out.println("\n───────────────────────────────────────────────────────────────────────────────");
			System.out.println("☞   메뉴 선택  > ");
			break;
		case 3:
			// 회원정보찾기
			System.out.printf("%s", SpaceUtil.format("# 회원정보 찾기", 55, -1));
			System.out.println();
			System.out.println("────────────────────────────────────────────");
			System.out.printf("%s", SpaceUtil.format(" 1.아이디찾기 │ 2.비밀번호찾기 │ 0.뒤로가기", 44, 0));
			System.out.println("\n────────────────────────────────────────────");
			System.out.println("☞   메뉴 선택  > ");
			break;
			
		case 4:	
			System.out.printf("%s", SpaceUtil.format("# 아이디 찾기", 55, -1));
			System.out.println();
			System.out.println("───────────────────");
			System.out.printf("%s", SpaceUtil.format(" □ 이름 │ □ 연락처 ", 20, 0));
			System.out.println("\n───────────────────");
			System.out.println("찾고 싶은 회원의 이름과 연락처를 입력해주세요.");
			System.out.println("☞   이름 입력   (0.뒤로가기) >> ");		
			break;
		case 5:
			System.out.printf("%s", SpaceUtil.format("# 아이디 찾기", 55, -1));
			System.out.println();
			System.out.println("───────────────────");
			System.out.printf("%s", SpaceUtil.format(" ■ 이름 │ □ 연락처 ", 20, 0));
			System.out.println("\n───────────────────");
			System.out.println("☞   연락처 입력   (숫자만 입력) (0.뒤로가기) >> ");		
			break;
			
		
		case 6:	
			System.out.printf("%s", SpaceUtil.format("# 비밀번호 찾기", 55, -1));
			System.out.println();
			System.out.println("───────────────────");
			System.out.printf("%s", SpaceUtil.format(" □ 아이디 │ □ 연락처 ", 20, 0));
			System.out.println("\n───────────────────");
			System.out.println("찾고 싶은 회원의 아이디와 연락처를 입력해주세요.");
			System.out.println("☞   아이디 입력 (0.뒤로가기) >> ");		
			break;
			
		case 7:
			System.out.printf("%s", SpaceUtil.format("# 회원 로그인", 55, -1));
			System.out.println();
			System.out.println("───────────────────");
			System.out.printf("%s", SpaceUtil.format(" ■ 아이디 │ □ 연락처 ", 20, 0));
			System.out.println("\n───────────────────");
			System.out.println("☞   연락처 입력 (숫자만 입력) (0.뒤로가기) >> ");		
			break;
			
			
			
		}
		clearLine(line);
	}
	public void loginMenu(int number) {
		switch(number) {
		case 1:
			System.out.printf("%s", SpaceUtil.format("# 회원 로그인", 55, -1));
			System.out.println();
			System.out.println("───────────────────────");
			System.out.printf("%s", SpaceUtil.format(" □ 아이디 │ □ 비밀번호 ", 20, 0));
			System.out.println("\n───────────────────────");
			System.out.println("☞   아이디 입력   (0.뒤로가기)> ");		
			break;
		case 2:
			System.out.printf("%s", SpaceUtil.format("# 회원 로그인", 55, -1));
			System.out.println();
			System.out.println("───────────────────────");
			System.out.printf("%s", SpaceUtil.format(" ■ 아이디 │ □ 비밀번호 ", 20, 0));
			System.out.println("\n───────────────────────");
			System.out.println("☞   비밀번호 입력  (0.뒤로가기)> ");		
			break;
		case 3:
			System.out.println();
			System.out.println("───────────────");
			System.out.printf("%s", SpaceUtil.format(" 로그인 성공! ", 15, 0));
			System.out.println("\n───────────────");
			break;
		case 4:

			System.out.println();
			System.out.println("────────────────────────");
			System.out.printf("%s", SpaceUtil.format(" 관리자모드 로그인 성공! ", 20, 0));
			System.out.println("\n────────────────────────");
			break;
		case 5:
			System.out.println();
			System.out.println("────────────");
			System.out.printf("%s", SpaceUtil.format("로그아웃", 10, 0));
			System.out.println("\n────────────");
			break;
		}
	}
	public void joinMenu(int number, int line) {
		switch(number) {
		case 1:
			System.out.printf("%s", SpaceUtil.format("# 회원가입", 55, -1));
			System.out.println();
			System.out.println("───────────────────────────────────────────────────────────────");
			System.out.printf("%s", SpaceUtil.format(" □ ID/PW │ □ 회원명 │ □ 연락처 │ □ 선택정보 기입 │ □ 가입완료", 44, 0));
			System.out.println("\n───────────────────────────────────────────────────────────────");
			break;
		case 2:
			System.out.printf("%s", SpaceUtil.format("# 회원가입", 55, -1));
			System.out.println();
			System.out.println("───────────────────────────────────────────────────────────────");
			System.out.printf("%s", SpaceUtil.format(" ■ ID/PW │ □ 회원명 │ □ 연락처 │ □ 선택정보 기입 │ □ 가입완료", 44, 0));
			System.out.println("\n───────────────────────────────────────────────────────────────");
			break;
		case 3:
			System.out.printf("%s", SpaceUtil.format("# 회원가입", 55, -1));
			System.out.println();
			System.out.println("───────────────────────────────────────────────────────────────");
			System.out.printf("%s", SpaceUtil.format(" ■ ID/PW │ ■ 회원명 │ □ 연락처 │ □ 선택정보 기입 │ □ 가입완료", 44, 0));
			System.out.println("\n───────────────────────────────────────────────────────────────");
			break;
		case 4:
			System.out.printf("%s", SpaceUtil.format("# 회원가입", 55, -1));
			System.out.println();
			System.out.println("───────────────────────────────────────────────────────────────");
			System.out.printf("%s", SpaceUtil.format(" ■ ID/PW │ ■ 회원명 │ ■ 연락처 │ □ 선택정보 기입 │ □ 가입완료", 44, 0));
			System.out.println("\n───────────────────────────────────────────────────────────────");
			break;
		case 5:
			System.out.printf("%s", SpaceUtil.format("# 회원가입", 55, -1));
			System.out.println();
			System.out.println("───────────────────────────────────────────────────────────────");
			System.out.printf("%s", SpaceUtil.format(" ■ ID/PW │ ■ 회원명 │ ■ 연락처 │ ■ 선택정보 기입 │ □ 가입완료", 44, 0));
			System.out.println("\n───────────────────────────────────────────────────────────────");
			break;
		case 6:
			System.out.println();
			System.out.println("──────────────────────────────────────────");
			System.out.printf("%s", SpaceUtil.format("회원가입 완료! 환영합니다.", 40, 0));
			System.out.println("\n──────────────────────────────────────────");
		}
		clearLine(line);
	}
	public static boolean PatternChecker(String input,int num) { // 정규형 체크
		switch(num) {
		case 1: // id 체크
			Pattern id = Pattern.compile("[A-Za-z0-9]{5,15}");
			Matcher a = id.matcher(input);
			if(a.matches() == true){
				return true;
			}
			return false;
			
		case 2: // pw 체크
			Pattern pw = Pattern.compile("^(?=.*[a-zA-Z0-9])(?=.*[0-9]+).{8,20}");
			Matcher b = pw.matcher(input);
			if(b.matches() == true){
				return true;
			}
			return false;
			
		case 3: // email 체크
			Pattern email = Pattern.compile("^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-z]+$");
			Matcher c = email.matcher(input);
			if(c.matches() == true){
				return true;
			}
			return false;
		
		case 4: // 이름 체크
			Pattern name = Pattern.compile("^[가-힣]*$");
			Matcher d = name.matcher(input);
			if(d.matches() == true){
				return true;
			}
			return false;
			
		case 5: // 숫자인지 체크
//			Pattern number = Pattern.compile("[+-]?d*(.d+)?");
			Pattern number = Pattern.compile("[+-]?\\d*(\\.\\d+)?");
			Matcher e = number.matcher(input);
			if(e.matches() == true){
				return true;
			}
			return false;
		}
		return false;
	}
	
	public static void clearLine(int number) {
		for (int i = 0; i < number; i++) {
			System.out.println();
		}
	}
	public String hpConvert(String hp) {
		if(hp.length() == 10) {
			hp =  hp.substring(0,3) + "-" + hp.substring(3,6) + "-" + hp.substring(6,10);
		}else if(hp.length() == 11) {
			hp = hp.substring(0,3) + "-" + hp.substring(3,7) + "-" + hp.substring(7,11);
		}
		return hp;
	}
	public String birConvert(String bir) {
		bir = bir.substring(0,4) + "-" + bir.substring(4,6) + "-" + bir.substring(6,8);
		return bir;
	}

}