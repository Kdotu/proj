package service;

import java.util.ArrayList;
import java.util.List;

import controller.Controller;
import dao.ComentDAO;
import oracle.net.ns.SessionAtts;
import util.ScanUtil;
import util.View;
import vo.BoardVO;
import vo.ComentVO;
import vo.MemberVO;

public class ComentService {
	private static ComentService instance = null;

	private ComentService() {
	}

	public static ComentService getInstance() {
		if (instance == null)
			instance = new ComentService();
		return instance;
	}

	ComentDAO comentDAO = ComentDAO.getInstance();
	ComentVO comentVO = new ComentVO();
	MemberService memberservice = MemberService.getInstance();
	MemberVO loginInfo = (MemberVO) Controller.sessionStorage.get("loginInfo");
	List<ComentVO> comentList;
	int showNum;

	

	public List<ComentVO> comentlist() {
 
		BoardService boardservice = BoardService.getInstance();
		
		showNum = boardservice.getShowNum();
		comentList = comentDAO.comentlist(showNum);

		return comentList;

	}

	public int insertComent() { // 등록
		if (!(boolean) Controller.sessionStorage.get("login")) {
			System.out.println("로그인되어있지 않습니다.");
			return View.MEMBER_LOGIN;
		}
		
//		post_num = boardservice.getPost_num();
		System.out.println("등록할 댓글을 입력해주세요 ");
		String comment = ScanUtil.nextLine();
		while(true) {
			if(comment.equals("")) {
				System.out.println("댓글 내용을 입력해주세요 ");
				comment = ScanUtil.nextLine();
			}else {break;}
		}
		
		
		List<Object> param = new ArrayList<Object>();
		param.add(comment);
		MemberVO loginInfo = (MemberVO) Controller.sessionStorage.get("loginInfo");
		param.add(loginInfo.getMemNumber());
		param.add(showNum);

		int result = comentDAO.insertComent(param);
		if (result > 0) {
			System.out.println("댓글이 등록되었습니다.");
		} else {
			System.out.println("댓글등록에 실패하셨습니다. ");
		}

		return View.BOARD_DETAIL2;
	}

	public int deleteComent() { // 삭제
		if (!(boolean) Controller.sessionStorage.get("login")) {
			System.out.println("로그인되어있지 않습니다.");
			return View.MEMBER_LOGIN;
		}
		int comentNum = 0;
		int delcomentNum = 0;
		// 댓글단사람 = 로그인한사람(나)이니 ?

		if(comentList.size() > 0) {
			System.out.println("삭제하실 댓글번호를 입력해주세요");
			comentNum = ScanUtil.nextInt();
			while(true) {
				if(comentNum > 0 && comentNum <= comentList.size()) {
					delcomentNum = comentList.get(comentNum - 1).getComentNum();
					break;
				}else {
					System.out.println("다시 입력하세요");
					comentNum = ScanUtil.nextInt();				
				}
			}
			// 내정보
			int mynum = Integer.parseInt(((MemberVO) Controller.sessionStorage.get("loginInfo")).getMemNumber());

			// 댓글쓴정보
			int writer = comentList.get(comentNum - 1).getMemNum();

			if (mynum == writer) {
				int result = comentDAO.deleteComent(delcomentNum);
				if (result > 0) {
					System.out.println("댓글삭제가 완료되었습니다.");
				} else {
					System.out.println("댓글삭제 실패");
				}
			} else {
				System.out.println("댓글작성자만 댓글 삭제 가능합니다.");
			}
		} else {
			System.out.println("댓글이 없습니다");
		}
		

		

		return View.BOARD_DETAIL2;
	}

	public int updateComent() {
		if (!(boolean) Controller.sessionStorage.get("login")) {
			System.out.println("로그인되어있지 않습니다.");
			return View.MEMBER_LOGIN;
		}
		if(comentList.size() > 0) {
			System.out.println("수정할 댓글을 선택하여 주세요");
			int comentNum = ScanUtil.nextInt();
			while(true) {
				if(comentNum < 0 || comentNum >= comentList.size()) {
					System.out.println("댓글번호를 다시 입력하세요");
					comentNum = ScanUtil.nextInt();
				}else {break;}
			}
			

			int delcomentNum = comentList.get(comentNum - 1).getComentNum(); //댓글의 인덱스

			System.out.println("수정할 내용 >>");
			String comment = ScanUtil.nextLine();
			while(true) {
				if(comment.equals("")) {
					System.out.println("내용을 입력해주세요");
					comment = ScanUtil.nextLine();
				} else {break;}
			}
			
			// 내정보
			int mynum = Integer.parseInt(((MemberVO) Controller.sessionStorage.get("loginInfo")).getMemNumber());

			// 댓글쓴정보
			int writer = comentList.get(comentNum - 1).getMemNum();
			
			if (mynum == writer) {
				int result = comentDAO.updateComent(comment,delcomentNum);
				if (result > 0) {
					System.out.println("댓글수정이 완료되었습니다.");
				} else {
					System.out.println("댓글수정 실패");
				}
			} else {
				System.out.println("댓글작성자만 댓글 수정이 가능합니다.");
			}
		}else {
			System.out.println("댓글이 존재하지 않습니다.");
		}
		return View.BOARD_DETAIL2;
	}
}