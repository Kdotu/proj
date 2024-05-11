package board_comment.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import config.SqlMapClientFactory;
import vo.BoardCommentVO;

public class BoardCommentDaoImpl implements BoardCommentDao {
	private static BoardCommentDao instance = null;
	private SqlMapClient smc = null;

	private BoardCommentDaoImpl() {
		smc = SqlMapClientFactory.getSqlMapClient();
	}

	public static BoardCommentDao getInstance() {
		if (instance == null)
			instance = new BoardCommentDaoImpl();
		return instance;
	}

	@Override
	public void insertComment(BoardCommentVO comment_vo) throws SQLException {
		smc.insert("boardComment.insertComment", comment_vo);
	}

	@Override
	public List<BoardCommentVO> selectComment(int board_no) throws SQLException {
		return smc.queryForList("boardComment.selectComment", board_no);
	}

	@Override
	public int updateComment(Map<String, Object> map) throws SQLException {
		return smc.update("boardComment.updateComment", map);
	}

	@Override
	public int deleteComment(int comment_no) throws SQLException {
		return smc.delete("boardComment.deleteComment", comment_no);
	}

	@Override
	public int insertReComment(BoardCommentVO boardCommentVo) {
		int res = 0;
		try {
			Object obj = smc.insert("boardComment.insertReComment", boardCommentVo);
			if (obj == null)
				res = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public List<BoardCommentVO> selectReComment(int boardNo) {
		List<BoardCommentVO> boardCommentList = null;
		try {
			boardCommentList = smc.queryForList("boardComment.selectReComment", boardNo);
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return boardCommentList;
	}

}
