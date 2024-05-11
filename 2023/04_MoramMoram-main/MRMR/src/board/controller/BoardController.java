package board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import board.service.BoardService;
import board.service.BoardServiceImpl;
import board_comment.service.BoardCommentSerciveImpl;
import board_comment.service.BoardCommentService;
import files.service.FilesService;
import files.service.FilesServiceImpl;
import vo.BoardCommentVO;
import vo.BoardVO;
import vo.FilesVO;
import vo.MemberVO;
import vo.PageVO;

@MultipartConfig()
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BoardService service = BoardServiceImpl.getInstance();
	private BoardCommentService commentBoardService = BoardCommentSerciveImpl.getInstance();
	private FilesService fileService = FilesServiceImpl.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 내가 요청한 모든 Method 'get'방식의 URL을 다 받는다.
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		// command로 남는 값은 /board/list.do, /board/read.do 와 같은 URL이다.
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		
		if(command.equals("/board/setSession.do")) {
			try {
				setSession(request, response);
			} catch (Exception e) {
				System.out.println("setSession 오류");
				e.printStackTrace();
			}
		}else if(command.equals("/board/List.do")) {
			try {
				getBoardList(request, response);
			} catch (Exception e) {
				System.out.println("get boardList 오류");
				e.printStackTrace();
			}
		}else if(command.equals("/board/Read.do")) {
			try {
				boardRead(request, response);
			} catch (NumberFormatException | SQLException e) {
				System.out.println("boardRead 오류");
				e.printStackTrace();
			}
		}else if(command.equals("/board/Update.do")) {
				boardUpdateGet(request, response);
		}else if(command.equals("/board/Delete.do")) {
			try {
				boardDelete(request, response);
			} catch (NumberFormatException | SQLException e) {
				System.out.println("boardDelete 오류");
				e.printStackTrace();
			}
		}else if(command.equals("/board/Insert.do")) {
			request.getRequestDispatcher("/WEB-INF/view/board/boardInsert.jsp").forward(request, response);
		}else if(command.equals("/board/InsertComment.do")) {
			try {
				commentInsert(request, response);
			} catch (Exception e) {
				System.out.println("commentInsert 오류");
				e.printStackTrace();
			}
		}else if(command.equals("/board/ReadComment.do")) {
			try {
				commentRead(request, response);
			} catch (Exception e) {
				System.out.println("commentRead 오류");
				e.printStackTrace();
			}
		}else if(command.equals("/board/ReplyDelete.do")) {
			try {
				replyDelete(request,response);
			} catch (Exception e) {
				System.out.println("ReplyDelete 오류");
				e.printStackTrace();
			}
		}else if (command.equals("/board/ReplyUpdate.do")) {
			try {
				replyUpdate(request, response);
			} catch (Exception e) {
				System.out.println("replyUpdate 오류");
				e.printStackTrace();
			}
		}
	}
	
	private void boardUpdateGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("boardVo"));
		BoardVO boardVo = null;
		try {
			boardVo = service.getBoard(boardNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("boardVo", boardVo);
		request.getRequestDispatcher("/WEB-INF/view/board/boardUpdate.jsp").forward(request, response);
	}

	// 게시판 댓글 삭제
	private void replyDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, NumberFormatException, SQLException {
		String commentNo = request.getParameter("reNo");
		int result = commentBoardService.deleteComment(Integer.parseInt(commentNo));
		
		PrintWriter out = response.getWriter();
		out.write(result + "");
		response.flushBuffer();
	}

	// 세션에 게시판 카테고리번호 등록
	private void setSession(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int boardCategoryNo = Integer.parseInt(request.getParameter("boardCategoryNo"));
		session.setAttribute("boardCategoryNo", boardCategoryNo);
		request.getRequestDispatcher("/board/List.do?page=1").forward(request, response);
	}

	// 댓글 전체 리스트
	private List<BoardCommentVO> commentRead(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		int boardNo = Integer.parseInt(request.getParameter("board_no"));
		List<BoardCommentVO> boardCommentList = commentBoardService.selectComment(boardNo);

		return boardCommentList;
	}

	// 게시판 댓글 추가
	private void commentInsert(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		BoardCommentVO boardCommentVO = new BoardCommentVO();

		String content = request.getParameter("content");
		String boardNo = request.getParameter("boardVo");
		int memNo = getMemNo(request,response);

		boardCommentVO.setBoard_comment_content(content);
		boardCommentVO.setBoard_no(Integer.parseInt(boardNo));
		boardCommentVO.setMem_no(memNo);

		commentBoardService.insertComment(boardCommentVO);
		response.sendRedirect(request.getContextPath() + "/board/Read.do?board_no=" + boardNo + "&memNo=" + memNo);
	}

	// 게시판 글 상세
	private void boardRead(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("board_no"));
		Map<String, Object>  map = new HashMap<String, Object>();
		String memNo = request.getParameter("memNo");
		
		map.put("file_category_no", 2);
		map.put("col", "BOARD_NO");
		map.put("no", boardNo);
		map.put("mem_no", Integer.parseInt(memNo));
		
		List<BoardCommentVO> boardCommentList = commentRead(request, response);
		service.setCountIncrement(boardNo);
		BoardVO boardVo = service.getBoard(boardNo);
		boardVo.setBoard_content(boardVo.getBoard_content().replaceAll("\n", "<br/>"));
		HttpSession session = request.getSession();
		session.setAttribute("boardCategoryNo", boardVo.getBoard_category_no());

		int check = fileService.searchFile(map);//파일의 모든정보
		
		List<BoardCommentVO> boardReCommentList = commentBoardService.selectReComment(boardNo);
		
		request.setAttribute("boardInfo", boardVo);
		request.setAttribute("boardCommentList", boardCommentList);
		request.setAttribute("boardReCommentList", boardReCommentList);
		request.setAttribute("filecheck", check);
		
		request.getRequestDispatcher("/WEB-INF/view/board/boardInfo.jsp").forward(request, response);
	}

	// 게시판 글 목록
	private void getBoardList(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		HttpSession session = request.getSession();
		
		int page = Integer.parseInt(request.getParameter("page"));
		String stype = request.getParameter("stype");
		String sword = request.getParameter("sword");
		int boardCategoryNo = (int) session.getAttribute("boardCategoryNo");
		session.setAttribute("boardCategoryNo", boardCategoryNo); // 게시판 카테고리 번호 세션에 저장
		
		PageVO pvo = service.pageInfo(page, stype, sword, boardCategoryNo);
		
		Map<String, Object>  map = new HashMap<String, Object>();
		map.put("start", pvo.getStart());
		map.put("end", pvo.getEnd());
		map.put("stype", stype);
		map.put("sword", sword);
		map.put("boardCategoryNo", boardCategoryNo);
		
		List<BoardVO> boardList = service.getAllBoardList(map);
		
		request.setAttribute("boardList", boardList);
		request.setAttribute("startpage", pvo.getStartPage());
		request.setAttribute("endpage", pvo.getEndPage());
		request.setAttribute("totalpage", pvo.getTotalPage());
		
		request.getRequestDispatcher("/WEB-INF/view/board/board.jsp").forward(request, response);
	}

	// 게시판 글 삭제
	private void boardDelete(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		String boardNo = request.getParameter("boardVo");
		int boardCategoryNo = Integer.parseInt(request.getParameter("boardCategoryNo"));

		int boardVo = service.deleteBoard(Integer.parseInt(boardNo));
		response.sendRedirect(request.getContextPath() + "/board/List.do?boardCategoryNo=" + boardCategoryNo+"&page=1");
	}

//////////////////////////////// post방식////////////////////////////////////////

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		List<BoardVO> boardList = new ArrayList<BoardVO>();

		if (command.equals("/board/Update.do")) {
			try {
				boardUpdatePost(request, response);
			} catch (NumberFormatException | SQLException e) {
				System.out.println("boardUpdatePost 오류");
				e.printStackTrace();
			}
		} else if (command.equals("/board/Insert.do")) {
			try {
				insertBoardPost(request, response);
			} catch (NumberFormatException | SQLException e) {
				System.out.println("insertBoardPost 오류");
				e.printStackTrace();
			}
		}else if(command.equals("/board/ReplyUpdate.do")) {
			try {
				replyUpdate(request,response);
			} catch (Exception e) {
				System.out.println("ReplyUpdate 오류");
				e.printStackTrace();
			}
		}else if(command.equals("/board/ReplyReplyUpdate.do")) {
			try {
				replyreplyUpdate(request,response);
			} catch (Exception e) {
				System.out.println("ReplyReplyUpdate 오류");
				e.printStackTrace();
			}
		}

	}

	// 게시판 댓글 수정
	private void replyUpdate(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		int board_comment_no = Integer.parseInt(request.getParameter("board_comment_no"));
		String board_comment_content = request.getParameter("board_comment_content");
		
		//map으로 설정 
		Map<String, Object>  map = new HashMap<String, Object>();
		map.put("board_comment_no" , board_comment_no);
		map.put("board_comment_content", board_comment_content );
		
		int result = commentBoardService.updateComment(map);
		
		request.getRequestDispatcher("/board/Read.do").forward(request, response);
		
	}
	
	private void replyreplyUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int memNo = getMemNo(request,response);
		
		BoardCommentVO boardCommentVo = new BoardCommentVO();
		boardCommentVo.setBoard_comment_parents(Integer.parseInt(request.getParameter("board_comment_no")));
		boardCommentVo.setBoard_comment_content(request.getParameter("board_comment_content"));
		boardCommentVo.setMem_no(memNo);
		boardCommentVo.setBoard_no(Integer.parseInt(request.getParameter("board_no")));
		
		int res = commentBoardService.insertReComment(boardCommentVo);
		
		PrintWriter out = response.getWriter();
		out.print(res);
		out.flush();
	}
	
	// 게시판 글 수정
	private void boardUpdatePost(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		BoardVO boardVo = new BoardVO();
		int boardNo = Integer.parseInt(request.getParameter("boardVo"));
		boardVo.setBoard_no(boardNo);
		try {
			BeanUtils.populate(boardVo, request.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}

		int res = service.updateBoard(boardVo);
		if(request.getParameter("files") != "") {
			request.getRequestDispatcher("/files/updateFiles.do?col=board_no&file_category_no=2&board_no="+boardNo).forward(request, response);
		}else {
			response.sendRedirect(request.getContextPath() + "/board/List.do?page=1");
		}
	}

	// 게시판 글 추가
	private void insertBoardPost(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		
		BoardVO boardVo = new BoardVO();
		HttpSession session = request.getSession();
		int boardCategoryNo = (int) session.getAttribute("boardCategoryNo");
		
		try {
			BeanUtils.populate(boardVo, request.getParameterMap());
			boardVo.setBoard_category_no(boardCategoryNo);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		int boardNo = service.insertBoard(boardVo);
		if(request.getParameter("files") != "") {
			request.getRequestDispatcher("/files/insertFiles.do?col=board_no&file_category_no=2&board_no="+boardNo).forward(request, response);
		}else {
			response.sendRedirect(request.getContextPath() + "/board/List.do?page=1");
		}
	}

	private int getMemNo(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		boolean check = (boolean)session.getAttribute("isLogined");
		
		Map<String, Object> loginInfo = (Map<String, Object>)session.getAttribute("loginInfo");
		MemberVO memberInfo = (MemberVO)loginInfo.get("memberInfo");
		
		return memberInfo.getMem_no();
	}
}
