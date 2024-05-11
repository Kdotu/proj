package board.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import config.SqlMapClientFactory;
import vo.BoardVO;

public class BoardDaoImpl implements BoardDao {
	private static BoardDao instance = null;
	private SqlMapClient smc = null;

	private BoardDaoImpl() {
		smc = SqlMapClientFactory.getSqlMapClient();
	}

	public static BoardDao getInstance() {
		if (instance == null)
			instance = new BoardDaoImpl();
		return instance;
	}

	@Override
	public int insertBoard(BoardVO boardVo) throws SQLException {
		return (int) smc.insert("board.insertBoard",boardVo);
	}

	@Override
	public int deleteBoard(int boardVo) throws SQLException {
		return smc.delete("board.deleteBoard",boardVo);
	}

	@Override
	public int updateBoard(BoardVO boardVo) throws SQLException {
		return smc.update("board.updateBoard",boardVo);
	}
	
	@Override
	public BoardVO getBoard(int boardNo) throws SQLException {
		return (BoardVO) smc.queryForObject("board.getBoard",boardNo);
	}

	@Override
	public int setCountIncrement(int boardNo) throws SQLException {
		return smc.update("board.setCountIncrement",boardNo);
	}

	@Override
	public List<BoardVO> getAllBoardList(Map<String, Object>  map) throws SQLException {
		return smc.queryForList("board.getAllBoardList",map);
	}

	@Override
	public int getTotalCount(Map<String, Object> map) throws SQLException {
		return (int) smc.queryForObject("board.getTotalCount", map);
	}

}
