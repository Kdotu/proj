package board_comment.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import board_comment.dao.BoardCommentDao;
import board_comment.dao.BoardCommentDaoImpl;
import vo.BoardCommentVO;

public class BoardCommentSerciveImpl implements BoardCommentService {
	private static BoardCommentService instance;
	private BoardCommentDao dao = null;

	private BoardCommentSerciveImpl() {
		dao = BoardCommentDaoImpl.getInstance();
	}

	public static BoardCommentService getInstance() {
		if (instance == null)
			instance = new BoardCommentSerciveImpl();
		return instance;
	}

	@Override
	public void insertComment(BoardCommentVO comment_vo) throws SQLException {
		dao.insertComment(comment_vo);
	}

	@Override
	public List<BoardCommentVO> selectComment(int board_no) throws SQLException {
		return dao.selectComment(board_no);
	}

	@Override
	public int updateComment(Map<String, Object> map) throws SQLException {
		return dao.updateComment(map);
	}

	@Override
	public int deleteComment(int comment_no) throws SQLException {
		return dao.deleteComment(comment_no);
	}

	@Override
	public int insertReComment(BoardCommentVO boardCommentVo) {
		return dao.insertReComment(boardCommentVo);
	}

	@Override
	public List<BoardCommentVO> selectReComment(int boardNo) {
		return dao.selectReComment(boardNo);
	}

}
