package board_comment.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import vo.BoardCommentVO;

public interface BoardCommentService {
	// 댓글 저장
	public void insertComment(BoardCommentVO comment_vo) throws SQLException;

	// 댓글리스트
	public List<BoardCommentVO> selectComment(int board_no) throws SQLException;

	// 댓글 수정
	public int updateComment(Map<String, Object> map) throws SQLException;

	// 댓글 삭제
	public int deleteComment(int comment_no) throws SQLException;

	public int insertReComment(BoardCommentVO boardCommentVo);

	public List<BoardCommentVO> selectReComment(int boardNo);
}
